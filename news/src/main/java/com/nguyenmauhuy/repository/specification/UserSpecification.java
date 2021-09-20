package com.nguyenmauhuy.repository.specification;

import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.model.request.user.AuthRequest;
import com.nguyenmauhuy.orm.repository.builder.Query;
import com.nguyenmauhuy.orm.repository.builder.QueryFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class UserSpecification {
    public static Query<User> authFilter(AuthRequest authRequest){
        return QueryFactory.and(List.of(withUserName(authRequest.getUserName()), withPassword(authRequest.getPassword())));
    }

    private static Query<User> withUserName(String userName){
        if (StringUtils.isEmpty(userName)) return null;

        return QueryFactory.equal("user_name", userName);
    }

    private static Query<User> withPassword(String password){
        if (StringUtils.isEmpty(password)) return null;

        return QueryFactory.equal("password", password);
    }

}
