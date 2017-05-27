package cn.sourcecodes.chatterServer.util;

import java.lang.reflect.*;

/**
 * @author sourcecodes.cn
 * @date 2016年10月7日下午7:14:53
 *
 */
public class ReflectionUtils {

	/**
	 * 通过反射, 获得定义Class 时声明的父类的泛型参数的类型
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class<?> getSupperClassGenericType(Class<?> clazz, int index) {
		Type type = clazz.getGenericSuperclass();
		
		if(!(type instanceof ParameterizedType)) {
			return Object.class;
		}
		
		Type[] types = ((ParameterizedType)type).getActualTypeArguments();
		
		if(index < 0 || index >= types.length) {
			return Object.class;
		}
		
		if(!(types[index] instanceof Class)) {
			return Object.class;
		}

		return (Class<?>) types[index];
	}
	

	/**
	 * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型
	 * @param clazz
	 * @return
	 */
	public static Class<?> getSuperGenericType(Class<?> clazz) {
		return getSupperClassGenericType(clazz, 0);
	}
	
	
	/**
	 * 循环向上转型, 获取对象 declaredMethod
	 * @param object  
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
		Class<?> clazz = object.getClass();
		Method method = null;
		
		for( ; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
			} catch (Exception e) {}
		}
		
		return method;
	}
	
 	/**
	 * @param field  变为可访问
	 */

	public static void makeAccessible(Field field) {
		if(!(Modifier.isPublic(field.getModifiers()))) {
			field.setAccessible(true);
		}
	}
	
 	/**
   	 * 循环向上转型, 获取对象的 DeclaredField
   	 * @param object
   	 * @param fieldName
   	 * @return
   	 */

	public static Field getDeclaredField(Object object, String fieldName) {
		Class<?> clazz = object.getClass();
		Field field = null;
		
		for( ; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
			} catch (Exception e) { }
		}
		
		return field;
	}
	
 	/**
   	 * 直接调用对象方法, 而忽略修饰符(private, protected)
   	 * @param object
   	 * @param methodName
  	 * @param parameterTypes
  	 * @param parameters
   	 * @return
   	 */

	public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		
		if(method == null) {
			throw new NullPointerException("method: " + methodName + "does not exist!");
		}

//已经测试过 只有 protected 需要设置 setAccessible, 但是没有测试超类, 所以先用下面那种, 保险点
//		if(Modifier.isPrivate(method.getModifiers())) {
//			method.setAccessible(true);   
//		}
		
		method.setAccessible(true);
		
		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
 	/**
  	 * 直接读取对象属性值, 忽略private protected修饰符, 也不经过getter
  	 * @param object
   	 * @param fieldName
   	 * @return
   	 */
	public static Object getFieldValue(Object object, String fieldName) {
		Field field = getDeclaredField(object, fieldName);
		Object obj = null;
		
		if(field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		
		makeAccessible(field);
		
		try {
			obj = field.get(object);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
 	/**
     * 直接设置对象属性值, 忽略private protected 修饰符, 也不经过setter
     * @param object
     * @param fieldName
     * @param value
     */
	public static void setFieldValue(Object object, String fieldName, Object value) {
		Field field = getDeclaredField(object, fieldName);
		
		if(field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		
		makeAccessible(field);
		
		try {
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
	}
}
