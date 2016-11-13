package com.sanji.mall.config;

//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ImportAware;
//import org.springframework.core.annotation.AnnotationAttributes;
//import org.springframework.core.type.AnnotationMetadata;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
//import org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration;
//import org.springframework.session.data.redis.RedisFlushMode;
//import org.springframework.session.data.redis.config.ConfigureNotifyKeyspaceEventsAction;
//import org.springframework.session.data.redis.config.ConfigureRedisAction;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.util.Assert;
//import org.springframework.util.StringUtils;
//
//import java.util.Arrays;
//import java.util.Map;
//import java.util.concurrent.Executor;
//
///**
// * 描述: TODO:
// * 包名: com.sanji.mall.config.
// * 作者: barton.
// * 日期: 16-11-9.
// * 项目名称: mall
// * 版本: 1.0
// * JDK: since 1.8
// */
////@EnableSpringHttpSession
//public class SessionConfig extends SpringHttpSessionConfiguration
// implements ImportAware {
//  @Bean
//  public JedisConnectionFactory connectionFactory() {
//    JedisConnectionFactory connection = new JedisConnectionFactory();
//    connection.setPort(6379);
//    connection.setHostName("localhost");
//    return connection;
//  }
//
//  private Integer maxInactiveIntervalInSeconds = 1800;
//
//  private ConfigureRedisAction configureRedisAction = new ConfigureNotifyKeyspaceEventsAction();
//
//  private String redisNamespace = "";
//
//  private RedisFlushMode redisFlushMode = RedisFlushMode.ON_SAVE;
//
//  private RedisSerializer<Object> defaultRedisSerializer;
//
//  private Executor redisTaskExecutor;
//
//  private Executor redisSubscriptionExecutor;
//
//  @Bean
//  public RedisMessageListenerContainer redisMessageListenerContainer(
//   RedisConnectionFactory connectionFactory,
//   RedisOperationsSessionRepository messageListener) {
//
//    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//    container.setConnectionFactory(connectionFactory);
//    if (this.redisTaskExecutor != null) {
//      container.setTaskExecutor(this.redisTaskExecutor);
//    }
//    if (this.redisSubscriptionExecutor != null) {
//      container.setSubscriptionExecutor(this.redisSubscriptionExecutor);
//    }
//    container.addMessageListener(messageListener,
//     Arrays.asList(new PatternTopic("__keyevent@*:del"),
//      new PatternTopic("__keyevent@*:expired")));
//    container.addMessageListener(messageListener, Arrays.asList(new PatternTopic(
//     messageListener.getSessionCreatedChannelPrefix() + "*")));
//    return container;
//  }
//
//  @Bean
//  public SanjiRedisTemplate<Object, Object> sessionRedisTemplate(
//   RedisConnectionFactory connectionFactory) {
//    SanjiRedisTemplate<Object, Object> template = new SanjiRedisTemplate<Object, Object>();
//    template.setKeySerializer(new StringRedisSerializer());
//    template.setHashKeySerializer(new StringRedisSerializer());
//    if (this.defaultRedisSerializer != null) {
//      template.setDefaultSerializer(this.defaultRedisSerializer);
//    }
//    template.setConnectionFactory(connectionFactory);
//    return template;
//  }
//
//  @Bean
//  public RedisOperationsSessionRepository sessionRepository(
//   @Qualifier("sessionRedisTemplate") RedisOperations<Object, Object> sessionRedisTemplate,
//   ApplicationEventPublisher applicationEventPublisher) {
//    RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(
//     sessionRedisTemplate);
//    sessionRepository.setApplicationEventPublisher(applicationEventPublisher);
//    sessionRepository
//     .setDefaultMaxInactiveInterval(this.maxInactiveIntervalInSeconds);
//    if (this.defaultRedisSerializer != null) {
//      sessionRepository.setDefaultSerializer(this.defaultRedisSerializer);
//    }
//
//    String redisNamespace = getRedisNamespace();
//    if (StringUtils.hasText(redisNamespace)) {
//      sessionRepository.setRedisKeyNamespace(redisNamespace);
//    }
//
//    sessionRepository.setRedisFlushMode(this.redisFlushMode);
//    return sessionRepository;
//  }
//
//  public void setMaxInactiveIntervalInSeconds(int maxInactiveIntervalInSeconds) {
//    this.maxInactiveIntervalInSeconds = maxInactiveIntervalInSeconds;
//  }
//
//  public void setRedisNamespace(String namespace) {
//    this.redisNamespace = namespace;
//  }
//
//  public void setRedisFlushMode(RedisFlushMode redisFlushMode) {
//    Assert.notNull(redisFlushMode, "redisFlushMode cannot be null");
//    this.redisFlushMode = redisFlushMode;
//  }
//
//  private String getRedisNamespace() {
//    if (StringUtils.hasText(this.redisNamespace)) {
//      return this.redisNamespace;
//    }
//    return System.getProperty("spring.session.redis.namespace", "");
//  }
//
//  public void setImportMetadata(AnnotationMetadata importMetadata) {
//
//    Map<String, Object> enableAttrMap = importMetadata
//     .getAnnotationAttributes(EnableRedisHttpSession.class.getName());
//    AnnotationAttributes enableAttrs = AnnotationAttributes.fromMap(enableAttrMap);
//    this.maxInactiveIntervalInSeconds = enableAttrs
//     .getNumber("maxInactiveIntervalInSeconds");
//    this.redisNamespace = enableAttrs.getString("redisNamespace");
//    this.redisFlushMode = enableAttrs.getEnum("redisFlushMode");
//  }
//
//  @Bean
//  public InitializingBean enableRedisKeyspaceNotificationsInitializer(
//   RedisConnectionFactory connectionFactory) {
//    return new SessionConfig.EnableRedisKeyspaceNotificationsInitializer(connectionFactory,
//     this.configureRedisAction);
//  }
//
//  /**
//   * Sets the action to perform for configuring Redis.
//   * @param configureRedisAction the configureRedis to set. The default is
//   * {@link ConfigureNotifyKeyspaceEventsAction}.
//   */
//  @Autowired(required = false)
//  public void setConfigureRedisAction(ConfigureRedisAction configureRedisAction) {
//    this.configureRedisAction = configureRedisAction;
//  }
//
//  @Autowired(required = false)
//  public void setDefaultRedisSerializer(
//   RedisSerializer<Object> defaultRedisSerializer) {
//    this.defaultRedisSerializer = defaultRedisSerializer;
//  }
//
//  @Autowired(required = false)
//  public void setRedisTaskExecutor(Executor redisTaskExecutor) {
//    this.redisTaskExecutor = redisTaskExecutor;
//  }
//
//  @Autowired(required = false)
//  public void setRedisSubscriptionExecutor(Executor redisSubscriptionExecutor) {
//    this.redisSubscriptionExecutor = redisSubscriptionExecutor;
//  }
//
//  /**
//   * Ensures that Redis is configured to send keyspace notifications. This is important
//   * to ensure that expiration and deletion of sessions trigger SessionDestroyedEvents.
//   * Without the SessionDestroyedEvent resources may not get cleaned up properly. For
//   * example, the mapping of the Session to WebSocket connections may not get cleaned
//   * up.
//   */
//  static class EnableRedisKeyspaceNotificationsInitializer implements InitializingBean {
//    private final RedisConnectionFactory connectionFactory;
//
//    private ConfigureRedisAction configure;
//
//    EnableRedisKeyspaceNotificationsInitializer(
//     RedisConnectionFactory connectionFactory,
//     ConfigureRedisAction configure) {
//      this.connectionFactory = connectionFactory;
//      this.configure = configure;
//    }
//
//    public void afterPropertiesSet() throws Exception {
//      RedisConnection connection = this.connectionFactory.getConnection();
//      this.configure.configure(connection);
//    }
//  }
//
//}
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.ConfigureNotifyKeyspaceEventsAction;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Exposes the {@link SessionRepositoryFilter} as a bean named
 * "springSessionRepositoryFilter". In order to use this a single
 * {@link RedisConnectionFactory} must be exposed as a Bean.
 *
 * @author Rob Winch
 * @since 1.0
 *
 */
@Configuration
@EnableScheduling
public class SessionConfig extends SpringHttpSessionConfiguration
 implements ImportAware {

  private Integer maxInactiveIntervalInSeconds = 1800;

  private ConfigureRedisAction configureRedisAction = new ConfigureNotifyKeyspaceEventsAction();

  private String redisNamespace = "";

  private RedisFlushMode redisFlushMode = RedisFlushMode.ON_SAVE;

  private RedisSerializer<Object> defaultRedisSerializer;

  private Executor redisTaskExecutor;

  private Executor redisSubscriptionExecutor;

  @Bean
  public RedisMessageListenerContainer redisMessageListenerContainer(
   RedisConnectionFactory connectionFactory,
   RedisOperationsSessionRepository messageListener) {

    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    if (this.redisTaskExecutor != null) {
      container.setTaskExecutor(this.redisTaskExecutor);
    }
    if (this.redisSubscriptionExecutor != null) {
      container.setSubscriptionExecutor(this.redisSubscriptionExecutor);
    }
    container.addMessageListener(messageListener,
     Arrays.asList(new PatternTopic("__keyevent@*:del"),
      new PatternTopic("__keyevent@*:expired")));
    container.addMessageListener(messageListener, Arrays.asList(new PatternTopic(
     messageListener.getSessionCreatedChannelPrefix() + "*")));
    return container;
  }

  @Bean
  public SanjiRedisTemplate<Object, Object> sessionRedisTemplate(
   RedisConnectionFactory connectionFactory) {
    SanjiRedisTemplate<Object, Object> template = new SanjiRedisTemplate<Object, Object>();
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    if (this.defaultRedisSerializer != null) {
      template.setDefaultSerializer(this.defaultRedisSerializer);
    }
    template.setConnectionFactory(connectionFactory);
    return template;
  }

  @Bean
  public RedisOperationsSessionRepository sessionRepository(
   @Qualifier("sessionRedisTemplate") RedisOperations<Object, Object> sessionRedisTemplate,
   ApplicationEventPublisher applicationEventPublisher) {
    RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(
     sessionRedisTemplate);
    sessionRepository.setApplicationEventPublisher(applicationEventPublisher);
    sessionRepository
     .setDefaultMaxInactiveInterval(this.maxInactiveIntervalInSeconds);
    if (this.defaultRedisSerializer != null) {
      sessionRepository.setDefaultSerializer(this.defaultRedisSerializer);
    }

    String redisNamespace = getRedisNamespace();
    if (StringUtils.hasText(redisNamespace)) {
      sessionRepository.setRedisKeyNamespace(redisNamespace);
    }

    sessionRepository.setRedisFlushMode(this.redisFlushMode);
    return sessionRepository;
  }

  public void setMaxInactiveIntervalInSeconds(int maxInactiveIntervalInSeconds) {
    this.maxInactiveIntervalInSeconds = maxInactiveIntervalInSeconds;
  }

  public void setRedisNamespace(String namespace) {
    this.redisNamespace = namespace;
  }

  public void setRedisFlushMode(RedisFlushMode redisFlushMode) {
    Assert.notNull(redisFlushMode, "redisFlushMode cannot be null");
    this.redisFlushMode = redisFlushMode;
  }

  private String getRedisNamespace() {
    if (StringUtils.hasText(this.redisNamespace)) {
      return this.redisNamespace;
    }
    return System.getProperty("spring.session.redis.namespace", "");
  }

  public void setImportMetadata(AnnotationMetadata importMetadata) {

    Map<String, Object> enableAttrMap = importMetadata
     .getAnnotationAttributes(EnableRedisHttpSession.class.getName());
    AnnotationAttributes enableAttrs = AnnotationAttributes.fromMap(enableAttrMap);
    this.maxInactiveIntervalInSeconds = enableAttrs
     .getNumber("maxInactiveIntervalInSeconds");
    this.redisNamespace = enableAttrs.getString("redisNamespace");
    this.redisFlushMode = enableAttrs.getEnum("redisFlushMode");
  }

  @Bean
  public InitializingBean enableRedisKeyspaceNotificationsInitializer(
   RedisConnectionFactory connectionFactory) {
    return new EnableRedisKeyspaceNotificationsInitializer(connectionFactory,
     this.configureRedisAction);
  }

  /**
   * Sets the action to perform for configuring Redis.
   *
   * @param configureRedisAction the configureRedis to set. The default is
   * {@link ConfigureNotifyKeyspaceEventsAction}.
   */
  @Autowired(required = false)
  public void setConfigureRedisAction(ConfigureRedisAction configureRedisAction) {
    this.configureRedisAction = configureRedisAction;
  }

  @Autowired(required = false)
  @Qualifier("springSessionDefaultRedisSerializer")
  public void setDefaultRedisSerializer(
   RedisSerializer<Object> defaultRedisSerializer) {
    this.defaultRedisSerializer = defaultRedisSerializer;
  }

  @Autowired(required = false)
  @Qualifier("springSessionRedisTaskExecutor")
  public void setRedisTaskExecutor(Executor redisTaskExecutor) {
    this.redisTaskExecutor = redisTaskExecutor;
  }

  @Autowired(required = false)
  @Qualifier("springSessionRedisSubscriptionExecutor")
  public void setRedisSubscriptionExecutor(Executor redisSubscriptionExecutor) {
    this.redisSubscriptionExecutor = redisSubscriptionExecutor;
  }

  /**
   * Ensures that Redis is configured to send keyspace notifications. This is important
   * to ensure that expiration and deletion of sessions trigger SessionDestroyedEvents.
   * Without the SessionDestroyedEvent resources may not get cleaned up properly. For
   * example, the mapping of the Session to WebSocket connections may not get cleaned
   * up.
   */
  static class EnableRedisKeyspaceNotificationsInitializer implements InitializingBean {
    private final RedisConnectionFactory connectionFactory;

    private ConfigureRedisAction configure;

    EnableRedisKeyspaceNotificationsInitializer(
     RedisConnectionFactory connectionFactory,
     ConfigureRedisAction configure) {
      this.connectionFactory = connectionFactory;
      this.configure = configure;
    }

    public void afterPropertiesSet() throws Exception {
      RedisConnection connection = this.connectionFactory.getConnection();
      this.configure.configure(connection);
    }
  }
  @Bean
  public JedisConnectionFactory connectionFactory() {
    JedisConnectionFactory connection = new JedisConnectionFactory();
    connection.setPort(6379);
    connection.setHostName("localhost");
    return connection;
  }
}
