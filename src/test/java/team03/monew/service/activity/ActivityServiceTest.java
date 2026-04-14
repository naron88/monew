package team03.monew.service.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team03.monew.module.activity.dto.ArticleViewDto;
import team03.monew.module.article.dto.CommentActivityDto;
import team03.monew.module.article.dto.CommentLikeActivityDto;
import team03.monew.module.comments.dto.SubscriptionDto;
import team03.monew.module.user.dto.ActivityDto;
import team03.monew.module.article.entity.Article;
import team03.monew.module.article.entity.ArticleView;
import team03.monew.module.comments.entity.Comment;
import team03.monew.module.comments.entity.CommentLike;
import team03.monew.module.interest.entity.Interest;
import team03.monew.module.interest.subscription.entity.Subscription;
import team03.monew.module.user.entity.User;
import team03.monew.module.user.entity.User.Role;
import team03.monew.module.activity.service.ActivityServiceImpl;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentLikeRepository commentLikeRepository;
    @Mock
    private ArticleViewRepository articleViewRepository;
    @Mock
    private SubscriptionMapper subscriptionMapper;
    @Mock
    private CommentMapper commentMapper;
    @Mock
    private CommentLikeMapper commentLikeMapper;
    @Mock
    private ArticleMapper articleMapper;

    @InjectMocks
    private ActivityServiceImpl activityService;

    private UUID userId;
    private User user;
    private Interest interest;
    private Subscription subscription;
    private Comment comment;
    private CommentLike commentLike;
    private ArticleView articleView;
    private Article article;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();

        // User 생성자 사용
        user = new User(
            "test",
            "test@example.com",
            "test1234",
            Role.USER
        );

        interest = new Interest("AI");

        // Subscription 생성자 사용
        subscription = new Subscription(user, interest);

        // Article 생성자 사용
        article = new Article(
            "Google",
            "test url",
            "test AI",
            "test summary",
            LocalDateTime.now()
        );

        // Comment 생성자 사용
        comment = new Comment(
            "Test Comment",
            user,
            article
        );

        // CommentLike 생성자 사용
        commentLike = new CommentLike(
            comment,
            user,
            article
        );

        // ArticleView 생성자 사용
        articleView = new ArticleView(
            user,
            article
        );
    }

    @Test
    @DisplayName("존재하지 않는 사용자 ID로 조회 시 예외가 발생한다")
    void findUserActivityWithNonExistingUserIdThrowsException() {
        // Given
        given(userRepository.existsById(userId)).willReturn(false);

        // When & Then
        assertThrows(UserNotFoundException.class, () -> activityService.findUserActivity(userId));
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, never()).findById(any(UUID.class));
    }

    @Test
    @DisplayName("사용자 활동 내역을 정상적으로 조회한다")
    void findUserActivity() {
        // Given
        SubscriptionDto subscriptionDto = subscriptionMapper.toDto(subscription);

        CommentActivityDto commentActivityDto = commentMapper.toActivityDto(comment);

        CommentLikeActivityDto commentLikeActivityDto = commentLikeMapper.toActivityDto(
            commentLike);

        long commentCount = 3;
        ArticleViewDto articleViewDto = articleMapper.toViewDto(articleView, article, commentCount);

        given(userRepository.existsById(userId)).willReturn(true);
        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(subscriptionRepository.findAllByUser(user)).willReturn(List.of(subscription));
        given(commentRepository.findTop10ByUserOrderByCreatedAtDesc(user)).willReturn(
            List.of(comment));
        given(commentLikeRepository.findTop10ByUserOrderByCreatedAtDesc(user)).willReturn(
            List.of(commentLike));
        given(articleViewRepository.findTop10ByUserOrderByViewedAtDesc(user)).willReturn(
            List.of(articleView));

        given(subscriptionMapper.toDto(subscription)).willReturn(subscriptionDto);
        given(commentMapper.toActivityDto(comment)).willReturn(commentActivityDto);
        given(commentLikeMapper.toActivityDto(commentLike)).willReturn(commentLikeActivityDto);
        given(articleMapper.toViewDto(any(ArticleView.class), any(Article.class), anyLong()))
            .willReturn(articleViewDto);
        // When
        ActivityDto result = activityService.findUserActivity(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.email()).isEqualTo(user.getEmail());
        assertThat(result.nickname()).isEqualTo(user.getNickname());
        assertThat(result.subscriptions()).hasSize(1);
        assertThat(result.comments()).hasSize(1);
        assertThat(result.commentLikes()).hasSize(1);
        assertThat(result.articleViews()).hasSize(1);

        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).findById(userId);
        verify(subscriptionRepository, times(1)).findAllByUser(user);
        verify(commentRepository, times(1)).findTop10ByUserOrderByCreatedAtDesc(user);
        verify(commentLikeRepository, times(1)).findTop10ByUserOrderByCreatedAtDesc(user);
        verify(articleViewRepository, times(1)).findTop10ByUserOrderByViewedAtDesc(user);
    }
}