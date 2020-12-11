package com.example.demo.service.solo.impl;

import com.example.demo.entity.bo.User;
import com.example.demo.entity.dto.Resoult;
import com.example.demo.service.solo.UserService;
import com.example.demo.spring.core.annotation.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public Resoult<List<User>> getUser() {
        return null;
    }



}
