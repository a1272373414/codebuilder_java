package com.velocity.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BeanMapUtils {

	public static Object getMap2Bean(Map map, Class<?> beanClass) {
		Object inst = null;
		try {
			Field[] fields = beanClass.getDeclaredFields();
			Constructor<?> constructor1 = beanClass.getConstructor(new Class[0]);
			inst = constructor1.newInstance(new Object[0]);
			for (Field field : fields) {
				if (!field.getName().equals("serialVersionUID")) {
					try {
						PropertyDescriptor pd = new PropertyDescriptor(field.getName(), inst.getClass());
						Method method = pd.getWriteMethod();
						if (null != map.get(field.getName())) {
							if ("Long".equals(field.getType().getSimpleName())) {
								method.invoke(inst, new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString()) ? "" : Long.valueOf(Long.parseLong(map.get(field.getName()).toString())) });
							} else if ("Date".equals(field.getType().getSimpleName())) {
								String dateStr = "";
								if (!StringUtils.isEmpty(map.get(field.getName()).toString())) {
									dateStr = map.get(field.getName()).toString();
									if (dateStr.indexOf("T") != -1) {
										dateStr = dateStr.replace("T", " ");
									}
								}
								if (StringUtil.trim(field.getType()).indexOf("java.util.Date") >= 0) {
									method.invoke(inst, new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString()) ? "" : DateUtils.reverse2Date(dateStr) });
								} else {
									method.invoke(inst, new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString()) ? "" : DateUtils.reverse2SqlDate(dateStr) });
								}
							} else if (("Integer".equals(field.getType().getSimpleName())) || ("int".equals(field.getType().getSimpleName()))) {
								method.invoke(inst, new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString()) ? "" : Integer.valueOf(Integer.parseInt(map.get(field.getName()).toString())) });
							} else if ("List".equals(field.getType().getSimpleName())) {
								method.invoke(inst, new Object[] { null == map.get(field.getName()) ? "" : (List) map.get(field.getName()) });
							} else if ("Map".equals(field.getType().getSimpleName())) {
								method.invoke(inst, new Object[] { null == map.get(field.getName()) ? "" : (Map) map.get(field.getName()) });
							} else if (("Double".equals(field.getType().getSimpleName())) || ("double".equals(field.getType().getSimpleName()))) {
								method.invoke(inst, new Object[] { Double.valueOf(Double.parseDouble(map.get(field.getName()).toString())) });
							} else if (("Float".equals(field.getType().getSimpleName())) || ("float".equals(field.getType().getSimpleName()))) {
								method.invoke(inst, new Object[] { Float.valueOf(Float.parseFloat(map.get(field.getName()).toString())) });
							} else {
								method.invoke(inst, new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString()) ? "" : map.get(field.getName()).toString() });
							}
						}
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
		}
		return inst;
	}

	public static Map getBean2Map(Object beanObj) {
		Map map = new HashMap();
		if (null == beanObj) {
			return map;
		}
		try {
			Class<?> classType = beanObj.getClass();
			Field[] fields = classType.getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().equals("serialVersionUID")) {
					try {
						PropertyDescriptor pd = new PropertyDescriptor(field.getName(), beanObj.getClass());
						Method method = pd.getReadMethod();
						Object value = method.invoke(beanObj, new Object[0]);
						if (null != value) {
							map.put(field.getName(), value);
						} else if (("Long".equals(field.getType().getSimpleName())) || ("Integer".equals(field.getType().getSimpleName()))) {
							map.put(field.getName(), Integer.valueOf(-1));
						} else {
							map.put(field.getName(), "");
						}
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
		}
		return map;
	}
}
