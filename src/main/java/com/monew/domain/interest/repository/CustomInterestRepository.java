package com.monew.domain.interest.repository;

import com.monew.domain.interest.dto.InterestFindRequest;
import com.monew.domain.interest.entity.Interest;
import java.util.List;

public interface CustomInterestRepository {

  // 관심사 검색
  List<Interest> findByCursor(InterestFindRequest request);

}
