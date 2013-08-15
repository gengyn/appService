package com.qingzhou.core.service;

import java.util.ArrayList;
import java.util.List;
import com.qingzhou.core.domain.Page;
import com.qingzhou.core.util.GenericsUtils;


@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public abstract class BaseService<T> {

	protected Class<T> entityClass;

	public BaseService() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	protected Class<T> getEntityClass() {
		return entityClass;
	}

	

	public abstract Integer count(T entity);

	public abstract List<T> select(T entity);

}
