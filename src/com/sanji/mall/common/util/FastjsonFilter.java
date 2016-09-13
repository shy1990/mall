package com.sanji.mall.common.util;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * 主要用于过滤不需要序列化的属性，或者包含需要序列化的属性
 * 
 * @ClassName: FastjsonFilter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-16 上午9:54:42
 * 
 */
public class FastjsonFilter implements PropertyFilter {

	private final Set<String> includes = new HashSet<String>();
	private final Set<String> excludes = new HashSet<String>();

	public Set<String> getIncludes() {
		return includes;
	}

	public Set<String> getExcludes() {
		return excludes;
	}

	public boolean apply(Object source, String name, Object value) {
		/*
		 * if (value != null) {// 一切Set属性不予序列化 return false; }
		 */
		if (excludes.contains(name)) {
			return false;
		}
		if (excludes.contains(source.getClass().getSimpleName() + "." + name)) {
			return false;
		}
		if (includes.size() == 0 || includes.contains(name)) {
			return true;
		}
		if (includes.contains(source.getClass().getSimpleName() + "." + name)) {
			return true;
		}
		return false;
	}

}
