package team03.monew.module.comments.exception;

import java.util.UUID;
import team03.monew.module.common.exception.MonewException;
import team03.monew.module.common.exception.ErrorCode;


public class LikeNotFoundException extends MonewException {

  private LikeNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

  public static LikeNotFoundException forUserAndComment(UUID userId, UUID commentId) {
    LikeNotFoundException ex = new LikeNotFoundException(ErrorCode.LIKE_NOT_FOUND);
    ex.addDetail("userId", userId);
    ex.addDetail("commentId", commentId);
    return ex;
  }
}
