package com.monew.domain.interest.repository;

import com.monew.domain.interest.entity.Interest;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterestRepository extends JpaRepository<Interest, UUID>, CustomInterestRepository {

  @Query("SELECT COUNT(i) FROM Interest i")
  int countAll();
}
