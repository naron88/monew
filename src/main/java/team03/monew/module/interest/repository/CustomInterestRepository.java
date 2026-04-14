package team03.monew.module.interest.repository;

import java.util.List;
import team03.monew.module.comments.dto.InterestFindRequest;
import team03.monew.module.interest.entity.Interest;

public interface CustomInterestRepository {

  // 조건에 맞는 관심사 검색
  List<Interest> findInterest(InterestFindRequest request);

  // 조건에 맞는 관심사 총 개수
  long totalCountInterest(InterestFindRequest request);
}
