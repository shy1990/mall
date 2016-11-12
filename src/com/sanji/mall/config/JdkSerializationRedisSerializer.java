package com.sanji.mall.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

/**
 * Java Serialization Redis serializer. Delegates to the default (Java based) {@link DefaultSerializer serializer} and
 * {@link DefaultDeserializer}. This {@link RedisSerializer serializer} can be constructed with either custom
 * {@link ClassLoader} or own {@link Converter converters}.
 *
 * @author Mark Pollack
 * @author Costin Leau
 * @author Mark Paluch
 */
public class JdkSerializationRedisSerializer implements RedisSerializer<Object> {

  private final Converter<Object, byte[]> serializer;
  private final Converter<byte[], Object> deserializer;

  /**
   * Creates a new {@link JdkSerializationRedisSerializer} using the default class loader.
   */
  public JdkSerializationRedisSerializer() {
    this(new SerializingConverter(), new DeserializingConverter());
  }

  /**
   * Creates a new {@link JdkSerializationRedisSerializer} using a {@link ClassLoader}.
   * @since 1.7
   */
  public JdkSerializationRedisSerializer(ClassLoader classLoader) {
    this(new SerializingConverter(), new DeserializingConverter(classLoader));
  }

  /**
   * Creates a new {@link JdkSerializationRedisSerializer} using a {@link Converter converters} to serialize and
   * deserialize objects.
   * @param serializer must not be {@literal null}
   * @param deserializer must not be {@literal null}
   * @since 1.7
   */
  public JdkSerializationRedisSerializer(Converter<Object, byte[]> serializer, Converter<byte[], Object> deserializer) {

    Assert.notNull("Serializer must not be null!");
    Assert.notNull("Deserializer must not be null!");

    this.serializer = serializer;
    this.deserializer = deserializer;
  }

  public Object deserialize(byte[] bytes) {
    if (bytes == null || bytes.length == 0) {
      return null;
    }

    try {
      return deserializer.convert(bytes);
    } catch (Exception ex) {
      throw new SerializationException("Cannot deserialize", ex);
    }
  }

  public byte[] serialize(Object object) {
    if (object == null) {
      return new byte[0];
    }
    try {
      return serializer.convert(object);
    } catch (Exception ex) {
      throw new SerializationException("Cannot serialize", ex);
    }
  }
}