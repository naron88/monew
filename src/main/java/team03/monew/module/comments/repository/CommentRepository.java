package team03.monew.module.comments.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import team03.monew.module.article.entity.Article;
import team03.monew.module.user.entity.User;
import team03.monew.module.comments.entity.Comment;

@Repository
public interface CommentRepository
        extends JpaRepository<Comment, UUID>,
        JpaSpecificationExecutor<Comment> {

    Page<Comment> findByArticleIdAndDeletedAtIsNull(UUID articleId, Pageable pageable);
    Optional<Comment> findByIdAndUserId(UUID commentId, UUID userId);

    Long countByArticle(Article article);

    @EntityGraph(attributePaths = {"article"})
    List<Comment> findTop10ByUserOrderByCreatedAtDesc(User user);
}
