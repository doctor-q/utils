package cc.doctor.utils.utils;

import cc.doctor.utils.annotation.Nullable;
import cc.doctor.utils.entity.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by doctor on 2017/5/21.
 */
public class ReflectUtils {
    private static final Logger log = LoggerFactory.getLogger(ReflectUtils.class);

    private ReflectUtils() {
    }

    public static Method getMethodByName(String method, Class clazz) {
        if (method == null || clazz == null) {
            return null;
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().equals(method)) {
                return declaredMethod;
            }
        }
        return null;
    }

    public static List<Tuple<Class, Annotation[]>> getMethodParamTypes(Method method) {
        List<Tuple<Class, Annotation[]>> typeAnnotations = new LinkedList<>();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            typeAnnotations.add(new Tuple<>(parameterTypes[i], parameterAnnotations[i]));
        }
        return typeAnnotations;

    }

    public static Map<String, Field> getObjectAttrNameFields(Class clazz) {
        Map<String, Field> nameTypeMap = new LinkedHashMap<>();
        Field[] fields = clazz.getFields();
        Field[] declaredFields = clazz.getDeclaredFields();
        List<Field> fieldList = new LinkedList<>();
        fieldList.addAll(Arrays.asList(fields));
        fieldList.addAll(Arrays.asList(declaredFields));
        for (Field declaredField : fieldList) {
            String name = declaredField.getName();
            Class<?> type = declaredField.getType();
            if (!Modifier.isStatic(type.getModifiers())) {
                nameTypeMap.put(name, declaredField);
            }
        }
        return nameTypeMap;
    }

    /**
     * set field with set method
     */
    public static void set(String field, Object value, Object instance) {
        set(field, value, value.getClass(), instance);
    }

    public static void set(String field, Object value, Class valueType, Object instance) {
        String setMethod = "set" + Character.toUpperCase(field.charAt(0)) + field.substring(1);
        try {
            if (value != null) {
                Method method = instance.getClass().getMethod(setMethod, valueType);
                method.invoke(instance, value);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("", e);
        }
    }

    /**
     * set field with java set
     */
    public static void set(Field field, Object value, Object instance) {
        field.setAccessible(true);
        try {
            if (value != null) {
                field.set(instance, value);
            }
        } catch (IllegalAccessException e) {
            log.error("", e);
        }
    }

    /**
     * get value with java get
     */
    @Nullable
    public static Object get(Field field, Object instance) {
        try {
            return field.get(instance);
        } catch (IllegalAccessException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * get value with get method
     */
    @Nullable
    public static Object get(String field, Object instance) {
        String getMethod = "get" + Character.toUpperCase(field.charAt(0)) + field.substring(1);
        try {
            Method method = instance.getClass().getMethod(getMethod);
            return method.invoke(instance);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            getMethod = "is" + Character.toUpperCase(field.charAt(0)) + field.substring(1);
            try {
                Method method = instance.getClass().getMethod(getMethod);
                return method.invoke(instance);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e1) {
                log.error("", e1);
            }
            return null;
        }
    }

    public static void copyProperties(Object src, Object dest) {
        Map<String, Field> srcNameFields = getObjectAttrNameFields(src.getClass());
        Map<String, Field> destNameFields = getObjectAttrNameFields(dest.getClass());
        for (Map.Entry<String, Field> entry : srcNameFields.entrySet()) {
            String name = entry.getKey();
            Field field = entry.getValue();
            Field destField = destNameFields.get(name);
            if (destField != null && destField.getType().equals(srcNameFields.get(name).getType())) {
                Object srcValue = get(field, src);
                set(destField, srcValue, dest);
            }
        }
    }

    @Nullable
    public static <T> T[] enumMap(Class<T> enumClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try {
            Method values = enumClass.getMethod("values");
            return (T[]) values.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("", e);
            throw e;
        }
    }

    @Nullable
    public static Class getClassByName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("", e);
        }
        return null;
    }
}
