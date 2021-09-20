package com.nguyenmauhuy.orm.util;

import com.nguyenmauhuy.orm.annotation.Column;
import com.nguyenmauhuy.orm.annotation.Entity;
import com.nguyenmauhuy.orm.annotation.Id;
import org.apache.commons.lang3.StringUtils;

public class AnnotationUtils {

    public static <T> String getClassName(Class<T> t){
        String name  = t.getAnnotation(Entity.class).name();
        name  = StringUtils.isEmpty(name) ? t.getSimpleName().toLowerCase() : name;
        return name;
    }
    public static <T> String getColumnName(Class <T> t, String name) throws NoSuchFieldException {
        String columnName = t.getDeclaredField(name).getAnnotation(Column.class).name();
        columnName = StringUtils.isEmpty(columnName) ? convertToColumnName(name) :columnName;
        return columnName;
    }
    public static <T> boolean getAutoIncrement(Class<T> t, String name) throws NoSuchFieldException {
        return t.getDeclaredField(name).getDeclaredAnnotation(Id.class).increment();
    }



    public static <T> String getPrimaryKey(Class <T> t, String name) throws NoSuchFieldException {
        String primaryKey = t.getDeclaredField(name).getAnnotation(Id.class).name();
        primaryKey = StringUtils.isEmpty(primaryKey) ? convertToColumnName(name) : primaryKey;
        return  primaryKey;
    }
    private static String convertToColumnName(String name){
        StringBuilder columnName = new StringBuilder();
        char[] chars = name.toCharArray();
        for(int i=0; i<chars.length;i++){
            if(chars[i] >= 'A' && chars[i] <= 'Z'){
                columnName.append("_").append((char)(chars[i])+32);
                continue;
            }
            columnName.append((chars[i]));
        }
        return columnName.toString();

    }
}
