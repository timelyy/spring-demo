package com.example.demo.service.solo;

import com.example.demo.entity.bo.User;
import com.example.demo.entity.dto.Resoult;

import java.util.List;

public interface UserService {

    Resoult<List<User>> getUser();

}
