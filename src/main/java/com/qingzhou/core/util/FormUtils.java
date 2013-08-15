package com.qingzhou.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;

import com.qingzhou.core.domain.Page;

public class FormUtils {

	/**
	 * 為調用方便提供靜態方法
	 * 
	 * @return
	 */
	public static FormUtils getFormUtilsInstance() {
		return new FormUtils();
	}

	/**
	 * 通过反射方式按照属性名称调用对象的set方法设置该属性的值
	 * 
	 * @param obj
	 *            被调用对象
	 * @param property
	 *            属性名称
	 * @param value
	 *            属性值
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void invokeSetMethod(Object obj, String property, Object value) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method[] mets = obj.getClass().getMethods();
		for (Method m : mets) {
			String n = m.getName();
			if (n.length() > 3 && n.substring(0, 3).equals("set") && n.substring(3).toLowerCase().equals(property.toLowerCase())) {
				m.invoke(obj, value);
				break;
			}
		}
	}

	/**
	 * 从rquest中将页面DataTable分页组件传过的参数绑定至需要分页的对象中
	 * 
	 * @param clazz
	 *            集成Page的domain对象
	 * @return clazz
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("static-access")
	public Object bindDataTableParm(Class<? extends Page> clazz, HttpServletRequest request) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, InstantiationException {
		Object object = this.bindFormToObject(clazz, request);
		this.invokeSetMethod(object, "pageSize", NumberUtils.createInteger(request.getParameter("iDisplayLength")));
		String orderBy = request.getParameter("mDataProp_" + request.getParameter("iSortCol_0"));
		this.invokeSetMethod(object, "orderBy", "0".equalsIgnoreCase(orderBy) ? "" : orderBy);
		this.invokeSetMethod(object, "orderDir", request.getParameter("sSortDir_0"));
		this.invokeSetMethod(object, "sSearch", request.getParameter("sSearch"));
		this.invokeSetMethod(object, "start", NumberUtils.createInteger(request.getParameter("iDisplayStart")));
		return object;
	}

	/**
	 * 将页面传入request按名称绑定至domain对象
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 * @throws InstantiationException
	 */
	@SuppressWarnings("static-access")
	public <T> T bindFormToObject(Class<T> t, HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException {
		Map<String, Object> map = request.getParameterMap();
		return this.bindFormToObject(t, map);
	}

	/**
	 * 将页面传入参数按名称绑定至domain对象
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 * @throws InstantiationException
	 */
	@SuppressWarnings("static-access")
	public <T> T bindFormToObject(Class<T> t, Map map) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException {
		Object object = t.newInstance();
		Field[] fields = getField(t);
		for (Field field : fields) {
			String[] values = (String[]) map.get(field.getName());
			if (values != null && values.length > 0)
				if ("java.lang.String".equals(field.getType().getName())) {
					this.invokeSetMethod(object, field.getName().toLowerCase(), values[0]);
				} else if ("java.math.BigDecimal".equals(field.getType().getName())) {
					if (!"".equals(values[0].trim()))
						this.invokeSetMethod(object, field.getName().toLowerCase(), NumberUtils.createBigDecimal(values[0]));
				} else if ("java.sql.Timestamp".equals(field.getType().getName())) {
					if (!"".equals(values[0].trim()))
						this.invokeSetMethod(object, field.getName().toLowerCase(), Timestamp.valueOf((values[0])));
				} else if ("java.util.Date".equals(field.getType().getName())) {
					if (!"".equals(values[0].trim()))
						this.invokeSetMethod(object, field.getName().toLowerCase(), com.qingzhou.core.util.DateUtils.getDate(values[0]));
				} else if ("java.sql.Date".equals(field.getType().getName())) {
					if (!"".equals(values[0].trim()))
						this.invokeSetMethod(object, field.getName().toLowerCase(), java.sql.Date.valueOf(values[0]));
				} else if ("java.lang.Double".equals(field.getType().getName())) {
					if (!"".equals(values[0].trim()))
						this.invokeSetMethod(object, field.getName().toLowerCase(), NumberUtils.createDouble(values[0]));
				} else if ("java.lang.Integer".equals(field.getType().getName()) || "int".equalsIgnoreCase(field.getType().getName())) {
					if (!"".equals(values[0].trim()))
						this.invokeSetMethod(object, field.getName().toLowerCase(), NumberUtils.createInteger(values[0]));
				}
			// TODO 其他类型
		}
		return (T) object;
	}

	/**
	 * 返回类的域列表
	 * 
	 * @param clazz
	 * @return
	 */
	private Field[] getField(Class clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		for (Field field : clazz.getDeclaredFields()) {
			fieldList.add(field);
		}
		if (clazz.getSuperclass() != null) {
			for (Field field : this.getField(clazz.getSuperclass())) {
				fieldList.add(field);
			}
		}
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}

}
