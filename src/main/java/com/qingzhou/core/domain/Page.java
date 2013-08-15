package com.qingzhou.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.common.collect.Lists;
import com.qingzhou.core.util.StringUtils;

/**
 * 与具体ORM实现无关的分页查询结果封装.
 * 
 * @param <T>
 *            Page中记录的类型.
 */
@JSONType(ignores = { "resultMap","pageNo", "pageSize", "countTotal", "orderBy", "orderDir", "sSearch", "start", "sEcho", "prefix", "totalItems",
		"prePage", "nextPage", "offset", "totalPages", "firstPage", "lastPage", "orderBySetted", "result" })
public class Page<T> extends PageRequest implements Iterable<T>, Serializable {
	private static final long serialVersionUID = 1L;
	protected List<T> result = null;
	protected long totalItems = -1;
	/**
	 * 数据集合result外的其他数据
	 */
	protected Map<String,Object> resultMap=null;

	public Page() {
	}

	public Page(PageRequest request) {
		this.pageNo = request.pageNo;
		this.pageSize = request.pageSize;
		this.countTotal = request.countTotal;
		this.orderBy = request.orderBy;
		this.orderDir = request.orderDir;
		this.sSearch = request.sSearch;
		this.start = request.start;
		this.sEcho = request.sEcho;
		this.prefix = request.prefix;
	}

	
	/**
	 * 
	 * @comment 
	 * @author 陈彦吉
	 * @date 2013-5-31 上午10:11:43
	 * @return
	 */
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	/**
	 * 
	 * @comment 
	 * @author 陈彦吉
	 * @date 2013-5-31 上午10:12:24
	 * @param resultMap
	 */
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	/**
	 * 获得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 获得总记录数, 默认值为-1.
	 */
	public long getTotalItems() {
		return totalItems;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalItems(final long totalItems) {
		this.totalItems = totalItems;
	}

	/**
	 * 实现Iterable接口, 可以for(Object item : page)遍历使用
	 */
	public Iterator<T> iterator() {
		return result.iterator();
	}

	/**
	 * 根据pageSize与totalItems计算总页数.
	 */
	public int getTotalPages() {
		return (int) Math.ceil((double) totalItems / (double) getPageSize());

	}

	/**
	 * 是否还有下一页.
	 */
	public boolean hasNextPage() {
		return (getPageNo() + 1 <= getTotalPages());
	}

	/**
	 * 是否最后一页.
	 */
	public boolean isLastPage() {
		return !hasNextPage();
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (hasNextPage()) {
			return getPageNo() + 1;
		} else {
			return getPageNo();
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean hasPrePage() {
		return (getPageNo() > 1);
	}

	/**
	 * 是否第一页.
	 */
	public boolean isFirstPage() {
		return !hasPrePage();
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (hasPrePage()) {
			return getPageNo() - 1;
		} else {
			return getPageNo();
		}
	}

	/**
	 * 计算以当前页为中心的页面列表,如"首页,23,24,25,26,27,末页"
	 * 
	 * @param count
	 *            需要计算的列表大小
	 * @return pageNo列表
	 */
	public List<Integer> getSlider(int count) {
		int halfSize = count / 2;
		int totalPage = getTotalPages();

		int startPageNo = Math.max(getPageNo() - halfSize, 1);
		int endPageNo = Math.min(startPageNo + count - 1, totalPage);

		if (endPageNo - startPageNo < count) {
			startPageNo = Math.max(endPageNo - count, 1);
		}

		List<Integer> result = Lists.newArrayList();
		for (int i = startPageNo; i <= endPageNo; i++) {
			result.add(i);
		}
		return result;
	}

	/**
	 * 与页面datatable分页组件对应的json数据封装
	 * 
	 * @return
	 */
	public String toJsonString() {
		return initJsonString(null);
	}

	/**
	 * 
	 * @comment 当使用到JSONInclude对节点属性与类属性引用时有效
	 * @author 陈彦吉
	 * @date 2013-5-30 下午1:42:09
	 * @param clazz
	 *            已经实例化类对象
	 * @return
	 */
	public String toJsonString(Object clazz) {
		return initJsonString(propertyFilter(clazz));
	}

	/**
	 * 
	 * @comment
	 * @author 陈彦吉
	 * @date 2013-5-30 下午1:42:14
	 * @param filter
	 * @return
	 */
	private String initJsonString(SerializeFilter filter) {
		JSONObject json = new JSONObject();
		// json.put("sEcho",this.getsEcho());
		json.put("iTotalRecords", String.valueOf(this.getTotalItems()));
		json.put("iTotalDisplayRecords", String.valueOf(this.getTotalItems()));
		json.put("aaData", this.getResult());
		if(this.getResultMap()!=null){
			for(Entry<String, Object> entry:this.getResultMap().entrySet()){
				json.put(entry.getKey(), entry.getValue());
			}
		}
		// 序列化配置
		SerializerFeature[] serializerFeatureNull = new SerializerFeature[] { SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty };
		return JSON.toJSONStringWithDateFormat(json, filter, "yyyy-MM-dd HH:mm:ss", serializerFeatureNull);
	}

	/**
	 * 
	 * @comment
	 * @author 陈彦吉
	 * @date 2013-5-30 下午1:42:19
	 * @param clazz
	 * @return
	 */
	private SerializeFilter propertyFilter(Object clazz) {
		SerializeFilter filter = null;
		if(clazz==null){
			return null;
		}
		try {
			JSONFilter jSONGis = (JSONFilter) clazz.getClass().getAnnotation(JSONFilter.class);
			if (jSONGis != null && jSONGis.ignores().length > 0) {
				final String[] property = includeProperty(jSONGis,clazz,jSONGis.ignores());
				filter = new PropertyFilter() {
					@Override
					public boolean apply(Object source, String name, Object value) {
						for (String per : property) {
							if (per.equals(name)) {
								return false;
							}
						}
						return true;
					}
				};
			} else if (jSONGis != null && jSONGis.includeSimpleProperty().length > 0) {
				String strs[] = includeProperty(jSONGis, clazz, jSONGis.includeSimpleProperty());
				if (strs != null) {
					filter = new SimplePropertyPreFilter(clazz.getClass(), strs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return filter;
	}

	/**
	 * 
	 * @comment 解析数组（如果是${}类型则进行反射）
	 * @author 陈彦吉
	 * @date 2013-5-30 下午1:42:26
	 * @param jSONGis
	 * @param clazz
	 * @param property
	 * @return
	 */
	private String[] includeProperty(JSONFilter jSONGis, Object clazz, String[] property) {
		String[] strs = null;
		String[] pro = property;
		List<String> list = new ArrayList<String>();
		for (String include : pro) {
			if (include.indexOf("$") == 0) {
				String[] temp = null;
				try {
					temp = (String[]) clazz.getClass()
							.getMethod("get" + StringUtils.removeUnderscores(include.replace("${", "").replace("}", ""))).invoke(clazz);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (temp != null) {
					for (String in : temp) {
						list.add(in);
					}
				}
			} else {
				list.add(include);
			}
		}
		if (list.size() != 0) {
			strs = list.toArray(new String[list.size()]);
		}
		return strs;
	}
}
