package com.sanji.mall.common.util;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Hmac工具
 * 
 * @author wujiming
 * 
 */
public final class Hmac {
	private final static Logger logger = LoggerFactory.getLogger(Hmac.class);

	/**
	 * 生成checksum码
	 * 
	 * @param message
	 * @param key
	 * @return
	 */
	public static String generateChecksum(Object message, String key) {
		Assert.notNull(message);
		Assert.notNull(key);
		String checksum = null;
		try {
			Mac mac = Mac.getInstance("HmacMD5");
			byte[] secretByte = key.getBytes("UTF-8");
			byte[] dataBytes = message.toString().getBytes("UTF-8");
			SecretKey secret = new SecretKeySpec(secretByte, "HmacMD5");
			mac.init(secret);
			byte[] doFinal = mac.doFinal(dataBytes);
			byte[] hexB = new Hex().encode(doFinal);
			checksum = new String(hexB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("message:{} key:{} checksum:{}" + message + key +
		// checksum);
		return checksum;
	}

	/**
	 * Returns a HmacMD5 Message Authentication Code (MAC) as a hex string
	 * (lowercase) for the given key and map.
	 * 
	 * @param key
	 *            They key for the keyed digest (must not be null)
	 * @param valueToDigest
	 *            The value (data) which should to digest (maybe empty or null)
	 * @return HmacMD5 MAC for the given key and value as a hex string
	 *         (lowercase)
	 * @throws IllegalArgumentException
	 *             when a {@link NoSuchAlgorithmException} is caught or key is
	 *             null or key is invalid.
	 */
	@SuppressWarnings("unchecked")
	public static String hmacMd5Hex(String key, Map<String, ?> valueToDigest) {
		StringBuilder s = new StringBuilder();
		for (Object values : valueToDigest.values()) {
			if (values instanceof String[]) {
				for (String value : (String[]) values) {
					s.append(value);
				}
			} else if (values instanceof List) {
				for (String value : (List<String>) values) {
					s.append(value);
				}
			} else {
				s.append(values);
			}
		}
		return HmacUtils.hmacMd5Hex(key, s.toString());
	}

	/**
	 * 验证
	 * 
	 * @param message
	 * @param key
	 * @param checkSum
	 * @return
	 */
	public static boolean verifyCheckSum(String message, String key, String checkSum) {
		return generateChecksum(message, key).equals(checkSum);
	}

	public static void main(String[] args) {
		String generateChecksum = generateChecksum("hello", "nima");
		// System.out.println(generateChecksum);
	}
}
