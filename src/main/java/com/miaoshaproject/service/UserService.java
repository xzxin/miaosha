package com.miaoshaproject.service;

import com.miaoshaproject.error.BussinessException;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BussinessException;
}
