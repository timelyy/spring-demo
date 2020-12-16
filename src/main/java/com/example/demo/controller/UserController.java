package com.example.demo.controller;

import com.example.demo.service.solo.UserService;
import com.example.demo.service.solo.impl.UserServiceImpl;
import com.example.demo.spring.core.annotation.Autowaire;
import com.example.demo.spring.core.annotation.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController extends HttpServlet {

    @Autowaire
    private UserServiceImpl userService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name","bbbb");
        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req,resp);
    }

    public void testservice(){
        System.out.println(userService);
    }

}
