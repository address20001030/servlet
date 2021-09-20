package com.nguyenmauhuy.service.impl;



import com.nguyenmauhuy.bean.annotation.Autowire;
import com.nguyenmauhuy.bean.annotation.Service;
import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.exception.ObjectNotFoundException;
import com.nguyenmauhuy.model.request.user.AuthRequest;
import com.nguyenmauhuy.model.request.user.UserDelete;
import com.nguyenmauhuy.model.request.user.UserSaveRequest;
import com.nguyenmauhuy.model.request.user.UserUpdateRequest;
import com.nguyenmauhuy.orm.paging.Page;
import com.nguyenmauhuy.orm.paging.PageAble;
import com.nguyenmauhuy.repository.UserRepository;
import com.nguyenmauhuy.repository.specification.UserSpecification;
import com.nguyenmauhuy.service.UserService;
import com.nguyenmauhuy.util.ReflectionUtil;
import com.nguyenmauhuy.util.TimeUtil;


import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowire
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void save(UserSaveRequest request) {

        try {
            User user = new User();
            ReflectionUtil.copy(request, user);
            LocalDate dob = TimeUtil.convertToLocalDate(request.getDob());
            user.setDob(dob);
            userRepository.save(user);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User auth(AuthRequest authRequest) {
        Optional<User> user = userRepository.find(UserSpecification.authFilter(authRequest));

        user.orElseThrow(() -> new ObjectNotFoundException(MessageFormat.format("user not found with condition: {0}", authRequest)));

        return user.get();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User didn't exits"));
    }

    @Override
    public Page<User> findAll(PageAble pageAble) {
        return userRepository.findAll(pageAble);
    }

    @Override
    public void update(UserUpdateRequest updateRequest) {
        User user = findById(updateRequest.getId());
        user.setRole_id(updateRequest.getRole_id());
        user.setUserName(updateRequest.getUserName());
        user.setPassword(updateRequest.getPassword());
        user.setEmail(updateRequest.getEmail());
        user.setPhone(updateRequest.getPhone());
        userRepository.update(updateRequest.getId(), user);
    }

    @Override
    public void deleteUser(UserDelete userDelete) {
        userRepository.delete(userDelete.getId());
    }


}
