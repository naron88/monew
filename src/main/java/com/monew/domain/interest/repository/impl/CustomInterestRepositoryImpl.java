package com.monew.domain.interest.repository.impl;

import com.monew.domain.interest.dto.InterestFindRequest;
import com.monew.domain.interest.entity.Interest;
import com.monew.domain.interest.entity.QInterest;
import com.monew.domain.interest.entity.QKeyword;
import com.monew.domain.interest.repository.CustomInterestRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomInterestRepositoryImpl implements CustomInterestRepository {

  private final JPAQueryFactory queryFactory;
  private final QInterest qInterest = QInterest.interest;
  private final QKeyword qKeyword = QKeyword.keyword;

  @Override
  public List<Interest> findByCursor(InterestFindRequest request) {

    log.info("조회 쿼리 시작, request={}", request);
    BooleanBuilder where = new BooleanBuilder();


    // 관심사 이름이나, 키워드로 검색합니다.
    if (request.keyword() != null) {
      if (StringUtils.hasText(request.keyword())) {
        BooleanExpression nameMatch = qInterest.name.containsIgnoreCase(request.keyword());
        BooleanExpression keywordMatch = qKeyword.name.containsIgnoreCase(request.keyword());
        where.and(nameMatch.or(keywordMatch));
      }

//      where.and(
//          qInterest.name.containsIgnoreCase(request.keyword())
//              .or(qKeyword.name.containsIgnoreCase(request.keyword()))
//      );
    }

    // 커서, after이 존재할 경우
    // 커서는 관심사 이름과 구독자 수를 바탕으로
    cursorPagination(request, where);

    // 정렬 - OrderBy로 정렬
    OrderSpecifier<?> orderByOrderBy = request.direction().equals("ASC")
        ? request.orderBy().equals("name")
            ? qInterest.name.asc()
            : qInterest.subscriberCount.asc()
        : request.orderBy().equals("name")
            ? qInterest.name.desc()
            : qInterest.subscriberCount.desc();

    log.info("조회 쿼리 종료");
    return queryFactory
        .select(qInterest).distinct()
        .from(qInterest)
        .leftJoin(qInterest.keywords, qKeyword)
        .where(where)
        .orderBy(orderByOrderBy)
        .limit(request.limit() + 1)
        .fetch();
  }

  private void cursorPagination(InterestFindRequest request, BooleanBuilder where) {
    if (request.cursor() != null && request.after() != null) {

      if (request.orderBy().equals("name")) {

        if (request.direction().equals("ASC")) {

          where.and(
              // 커서보다 같거나 크고 createdAt이후의 interest 반환
              qInterest.name.gt(request.cursor())
                  .or(qInterest.name.eq(request.cursor()).and(qInterest.createdAt.gt(request.after())))
          );
        }

        else {

          where.and(
              qInterest.name.lt(request.cursor())
                  .or(qInterest.name.eq(request.cursor()).and(qInterest.createdAt.lt(request.after())))
          );
        }
      }
      // 커서가 구독자 수일 경우
      else{
        // String을 int로 변경
        int subs = Integer.parseInt(request.cursor());

        if (request.direction().equals("ASC")) {

          where.and(
              qInterest.subscriberCount.gt(subs)
                  .or(qInterest.subscriberCount.eq(subs).and(qInterest.createdAt.gt(request.after())))
          );
        }

        else {

          where.and(
              qInterest.subscriberCount.lt(subs)
                  .or(qInterest.subscriberCount.eq(subs).and(qInterest.createdAt.lt(request.after())))
          );
        }
      }
    }
  }
}
