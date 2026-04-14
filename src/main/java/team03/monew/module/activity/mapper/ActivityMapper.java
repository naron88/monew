package team03.monew.module.activity.mapper;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team03.monew.module.activity.document.ActivityDocument;
import team03.monew.module.activity.dto.ArticleViewDto;
import team03.monew.module.article.dto.CommentActivityDto;
import team03.monew.module.article.dto.CommentLikeActivityDto;
import team03.monew.module.comments.dto.SubscriptionDto;
import team03.monew.module.user.dto.ActivityDto;
import team03.monew.module.article.entity.Article;
import team03.monew.module.article.mapper.ArticleMapper;
import team03.monew.module.comments.mapper.CommentLikeMapper;
import team03.monew.module.comments.mapper.CommentMapper;
import team03.monew.module.interest.mapper.SubscriptionMapper;
import team03.monew.module.comments.repository.CommentRepository;

@Component
@AllArgsConstructor
public class ActivityMapper {

    private final SubscriptionMapper subscriptionMapper;
    private final CommentMapper commentMapper;
    private final CommentLikeMapper commentLikeMapper;
    private final ArticleMapper articleMapper;
    private final CommentRepository commentRepository;

    public ActivityDto toDto(ActivityDocument document) {
        if (document == null) {
            return null;
        }

        List<SubscriptionDto> subscriptionDtos = document.getSubscriptions()
            .stream()
            .map(subscriptionMapper::toDto)
            .toList();

        List<CommentActivityDto> commentDtos = document.getComments()
            .stream()
            .map(commentMapper::toActivityDto)
            .toList();

        List<CommentLikeActivityDto> commentLikeDtos = document.getCommentLikes()
            .stream()
            .map(commentLikeMapper::toActivityDto)
            .toList();

        List<ArticleViewDto> articleViewDtos = document.getArticleViews()
            .stream()
            .map(articleView -> {
                Article article = articleView.getArticle();
                long commentCount = commentRepository.countByArticle(article);
                return articleMapper.toViewDto(articleView, article, commentCount);
            })
            .toList();

        return new ActivityDto(
            document.getUserId(),
            document.getEmail(),
            document.getNickname(),
            document.getCreatedAt(),
            subscriptionDtos,
            commentDtos,
            commentLikeDtos,
            articleViewDtos
        );
    }
}