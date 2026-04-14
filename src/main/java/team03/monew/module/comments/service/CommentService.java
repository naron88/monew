package team03.monew.module.comments.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import team03.monew.module.article.dto.CommentCreateRequest;
import team03.monew.module.article.dto.CommentDto;
import team03.monew.module.article.dto.CommentLikeDto;
import team03.monew.module.article.dto.CommentUpdateRequest;
import team03.monew.module.common.dto.CursorPageResponse;

import java.time.Instant;
import java.util.UUID;

public interface CommentService {

    CommentDto create(CommentCreateRequest request);

    Page<CommentDto> listByArticle(UUID articleId,
                                   String orderBy,
                                   Sort.Direction direction,
                                   int limit,
                                   UUID requesterId);


    CommentDto update(UUID commentId, UUID userId, CommentUpdateRequest request);


    void softDelete(UUID commentId, UUID userId);


    void hardDelete(UUID commentId, UUID userId);


    CommentLikeDto likeComment(UUID commentId, UUID userId);


    void unlikeComment(UUID commentId, UUID userId);

    CursorPageResponse<CommentDto> listByArticleCursor(
            UUID articleId,
            String orderBy,
            Sort.Direction direction,
            int limit,
            String cursor,
            Instant after,
            UUID requesterId
    );
}