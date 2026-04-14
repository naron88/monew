package team03.monew.module.interest.service;

import java.util.UUID;
import team03.monew.module.common.dto.CursorPageResponse;
import team03.monew.module.comments.dto.InterestDto;
import team03.monew.module.comments.dto.InterestFindRequest;
import team03.monew.module.comments.dto.InterestRegisterRequest;
import team03.monew.module.comments.dto.InterestUpdateRequest;
import team03.monew.module.interest.entity.Interest;

public interface InterestService {

  // 관심사 등록
  InterestDto create(InterestRegisterRequest request);

  // 관심사 키워드 수정
  InterestDto update(UUID interestId, InterestUpdateRequest request, UUID userId);

  // 관심사 삭제
  void delete(UUID interestId);

  // 관심사 검색
  CursorPageResponse<InterestDto> find(InterestFindRequest request, UUID userId);

  // 관심사 구독자 수 변경
  void updateSubscriberCount(Interest interest, boolean increase);
}