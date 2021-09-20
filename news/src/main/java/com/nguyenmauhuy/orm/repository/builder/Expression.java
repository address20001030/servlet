package com.nguyenmauhuy.orm.repository.builder;

public interface Expression {

    String expression();

    static String equal(){
        return "=";
    }

    static String notEqual(){
        return " <> ";
    }

    static String gt(){
        return " > ";
    }

    static String gte(){
        return " >= ";
    }

    static String like(){
        return " LIKE ";
    }

    static String lt(){
        return " < ";
    }

    static String lte(){
        return " <= ";
    }

    static  String between(){
        return " BETWEEN ";
    }

    static String in(){
        return " in ";
    }

    static String isNull(){
        return " is null ";
    }

    static  String isNotNull(){
        return " is not null ";
    }

    static  String or(){
        return " OR ";
    }

    static  String and(){
        return " AND ";
    }
}
