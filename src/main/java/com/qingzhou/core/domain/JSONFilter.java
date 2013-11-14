package com.qingzhou.core.domain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解析JSON时的引用范围，与@JSONType(ignores={""})相反，优先级高于排除（其属性二选一）
 * @author niuyaning
 * @date 2013-5-29
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface JSONFilter {

	/**
	 * 
	 * @comment 动态过滤节点、属性名（优先级高于simpleProperty）
	 * @author 陈彦吉
	 * @date 2013-5-30 下午1:45:37
	 * @return
	 */
	public abstract String[] ignores() default {};

	/**
	 * 
	 * @comment 动态包含数据对象属性
	 * @author 陈彦吉
	 * @date 2013-5-30 下午1:46:03
	 * @return
	 */
	public abstract String[] includeSimpleProperty() default {};
}
