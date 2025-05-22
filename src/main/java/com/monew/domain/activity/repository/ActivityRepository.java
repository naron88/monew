package com.monew.domain.activity.repository;

import com.monew.domain.activity.entity.Activity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {

  boolean existsByUserId(UUID id);

  Optional<Activity> findByUserId(UUID userId);
}
