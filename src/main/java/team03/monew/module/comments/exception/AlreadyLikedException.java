package team03.monew.module.comments.exception;

import java.util.UUID;
import team03.monew.module.common.exception.MonewException;
import team03.monew.module.common.exception.ErrorCode;


public class AlreadyLikedException extends MonewException {
    public AlreadyLikedException(UUID commentId, UUID userId) {
        super(ErrorCode.ALREADY_LIKED);
        this.addDetail("commentId", commentId);
        this.addDetail("userId", userId);
    }
}