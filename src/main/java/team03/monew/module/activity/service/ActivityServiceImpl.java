package team03.monew.module.activity.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.monew.module.user.dto.ActivityDto;
import team03.monew.module.activity.dto.ArticleViewDto;
import team03.monew.module.article.dto.CommentActivityDto;
import team03.monew.module.article.dto.CommentLikeActivityDto;
import team03.monew.module.comments.dto.SubscriptionDto;
import team03.monew.module.article.entity.Article;
import team03.monew.module.article.entity.ArticleView;
import team03.monew.module.comments.entity.Comment;
import team03.monew.module.comments.entity.CommentLike;
import team03.monew.module.interest.subscription.entity.Subscription;
import team03.monew.module.user.entity.User;
import team03.monew.module.article.mapper.ArticleMapper;
import team03.monew.module.comments.mapper.CommentLikeMapper;
import team03.monew.module.comments.mapper.CommentMapper;
import team03.monew.module.interest.mapper.SubscriptionMapper;
import team03.monew.module.article.repository.ArticleViewRepository;
import team03.monew.module.comments.repository.CommentLikeRepository;
import team03.monew.module.comments.repository.CommentRepository;
import team03.monew.module.interest.subscription.repository.SubscriptionRepository;
import team03.monew.module.user.repository.UserRepository;
import team03.monew.module.user.exception.UserNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final ArticleViewRepository articleViewRepository;

    private final SubscriptionMapper subscriptionMapper;
    private final CommentMapper commentMapper;
    private final CommentLikeMapper commentLikeMapper;
    private final ArticleMapper articleMapper;

    @Override
    public ActivityDto findUserActivity(UUID userId) {
        if (!userRepository.existsById(userId)) {
            log.error("존재하지 않는 사용자 ID");
            throw UserNotFoundException.withId(userId);
        }

        User user = userRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException.withId(userId));

        log.debug("사용자 활동 내역 조회 시작: 사용자 ID = {}", userId);
        List<Subscription> subscriptions = subscriptionRepository.findAllByUser(user);
        List<SubscriptionDto> subscriptionDtos = subscriptions.stream()
            .map(subscriptionMapper::toDto).toList();

        List<Comment> comments = commentRepository.findTop10ByUserOrderByCreatedAtDesc(user);
        List<CommentActivityDto> commentDtos = comments.stream()
            .map(commentMapper::toActivityDto).toList();

        List<CommentLike> commentLikes = commentLikeRepository.findTop10ByUserOrderByCreatedAtDesc(
            user);
        List<CommentLikeActivityDto> commentLikeDtos = commentLikes.stream()
            .map(commentLikeMapper::toActivityDto).toList();

        List<ArticleView> articleViews = articleViewRepository.findTop10ByUserOrderByViewedAtDesc(
            user);
        List<ArticleViewDto> articleViewDtos = articleViews.stream()
            .map(articleView -> {
                Article article = articleView.getArticle();
                long commentCount = commentRepository.countByArticle(article);
                return articleMapper.toViewDto(articleView, article, commentCount);
            })
            .toList();

        ActivityDto activityDto = new ActivityDto(
            userId,
            user.getEmail(),
            user.getNickname(),
            user.getCreatedAt(),
            subscriptionDtos,
            commentDtos,
            commentLikeDtos,
            articleViewDtos);

        log.info("사용자 활동 내역 조회 완료: 사용자 ID = {}", userId);
        return activityDto;
    }
}
