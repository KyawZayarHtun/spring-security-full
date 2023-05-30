package com.kz.service;

import com.kz.entity.User;
import com.kz.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
}
