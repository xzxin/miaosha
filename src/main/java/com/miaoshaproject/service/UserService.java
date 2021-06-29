package com.miaoshaproject.service;

import com.miaoshaproject.service.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserModel getUserById(Integer id);
}
