package com.monew.domain.interest.controller;

import com.monew.domain.interest.dto.CursorPageResponseInterestDto;
import com.monew.domain.interest.dto.InterestDto;
import com.monew.domain.interest.dto.InterestFindRequest;
import com.monew.domain.interest.dto.InterestRegisterRequest;
import com.monew.domain.interest.dto.InterestUpdateRequest;
import com.monew.domain.interest.service.InterestService;
import com.monew.domain.subscription.dto.SubscriptionDto;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interests")
public class InterestController {

  private final InterestService interestService;

  @GetMapping()
  public ResponseEntity<CursorPageResponseInterestDto> findByCursor(
      @RequestParam(required = false) String keyword,
      @RequestParam String orderBy,
      @RequestParam String direction,
      @RequestParam(required = false) String cursor,
      @RequestParam(required = false) Instant after,
      @RequestParam int limit,
      @RequestHeader("MoNew-Request-User-ID") UUID userId) {
    InterestFindRequest request = new InterestFindRequest(
        keyword, orderBy, direction, cursor, after, limit);
    log.info("조회 시작, keyword={}", keyword);
    CursorPageResponseInterestDto responseInterestDto = interestService.findByCursor(request, userId);
    log.info("조회 완료");
    return ResponseEntity.status(HttpStatus.OK).body(responseInterestDto);
  }

  @PostMapping()
  public ResponseEntity<InterestDto> create(@RequestBody InterestRegisterRequest request) {
    InterestDto interestDto = interestService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(interestDto);
  }

  @PatchMapping("/{interestId}")
  public ResponseEntity<InterestDto> update(@PathVariable UUID interestId,
      @RequestBody InterestUpdateRequest request) {
    InterestDto interestDto = interestService.update(interestId, request);
    return ResponseEntity.status(HttpStatus.OK).body(interestDto);
  }

  @DeleteMapping("/{interestId}")
  public ResponseEntity<Void> delete(@PathVariable UUID interestId) {
    interestService.delete(interestId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping("/{interestId}/subscriptions")
  public ResponseEntity<SubscriptionDto> subscribe(@PathVariable UUID interestId,
      @RequestHeader("MoNew-Request-User-ID") UUID userId) {
    SubscriptionDto subscriptionDto = interestService.createSubs(interestId, userId);
    return ResponseEntity.status(HttpStatus.OK).body(subscriptionDto);
  }

  @DeleteMapping("/{interestId}/subscriptions")
  public ResponseEntity<Void> cancelSubscribe(@PathVariable UUID interestId,
      @RequestHeader("MoNew-Request-User-ID") UUID userId) {
    interestService.deleteSubs(interestId, userId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
