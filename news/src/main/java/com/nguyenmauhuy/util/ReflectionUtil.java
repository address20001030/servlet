package com.nguyenmauhuy.util;

import com.nguyenmauhuy.orm.annotation.Id;
import com.nguyenmauhuy.orm.util.AnnotationUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReflectionUtil {
    public static <T> Object get(T t, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String name = field.getName();
        String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        Method method = t.getClass().getMethod(methodName);

        return method.invoke(t);
    }

    public static <T> void set(T t, Field field, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(t, value);
    }

    public static <T> T convertToEntity(ResultSet rs, Class<T> tClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, SQLException {
        var t = tClass.getDeclaredConstructor().newInstance();
        var fields = tClass.getDeclaredFields();
        for (Field field : fields) {

            try {
                if (field.isAnnotationPresent(Id.class)) {
                    set(t, field, rs.getObject(AnnotationUtils.getPrimaryKey(tClass, field.getName())));
                    continue;
                }
                String columnName = AnnotationUtils.getColumnName(tClass, field.getName());
                if (field.getType().equals(LocalDate.class)) {
                    String date = rs.getString(columnName);
                    set(t, field, TimeUtil.convertToLocalDate(date));
                } else if (field.getType().equals(LocalTime.class)) {
                    String date = rs.getString(columnName);
                    set(t, field, TimeUtil.convertToLocalDateTime(date));
                } else {
                    set(t, field, rs.getObject(columnName));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public static <T> void copy(Object resource, T target) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Field[] resourceFields = resource.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (Field resourceField : resourceFields) {
            for (Field targetField : targetFields) {
                if (get(resource, resourceField) != null) {
                    if (resourceField.getName().equals(targetField.getName())) {
                        if (resourceField.getType().equals(targetField.getType())) {
                            targetField.setAccessible(true);
                            targetField.set(target, get(resource, resourceField));
                        }
                    }
                }
            }
        }

    }
}
