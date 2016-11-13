package com.sanji.mall.config;

import org.springframework.core.ConfigurableObjectInputStream;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;

/**
 * 描述: TODO:
 * 包名: com.sanji.mall.config.
 * 作者: barton.
 * 日期: 16-11-9.
 * 项目名称: mall
 * 版本: 1.0
 * JDK: since 1.8
 */
public class DefaultDeserializer implements RedisSerializer<Object> {
  private final ClassLoader classLoader;


  /**
   * Create a {@code DefaultDeserializer} with default {@link ObjectInputStream}
   * configuration, using the "latest user-defined ClassLoader".
   */
  public DefaultDeserializer() {
    this.classLoader = null;
  }

  /**
   * Create a {@code DefaultDeserializer} for using an {@link ObjectInputStream}
   * with the given {@code ClassLoader}.
   * @see ConfigurableObjectInputStream#ConfigurableObjectInputStream(InputStream, ClassLoader)
   * @since 4.2.1
   */
  public DefaultDeserializer(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  public byte[] serialize(Object o) throws SerializationException {
    if (o == null) {
      return new byte[0];
    }
    try {
      return o.toString().getBytes();
    } catch (Exception ex) {
      throw new SerializationException("Cannot deserialize", ex);
    }
  }

  @Override
  public Object deserialize(byte[] bytes) throws SerializationException {
    if (bytes == null || bytes.length == 0) {
      return null;
    }

    try {
      return new String(bytes, Charset.forName("UTF-8"));
    } catch (Exception ex) {
      throw new SerializationException("Cannot deserialize", ex);
    }
  }
}
