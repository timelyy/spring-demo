package com.example.demo.entity.dto;

import lombok.Data;

@Data
public class Resoult<T> {

    private int code;

    private String msg;

    private T data;
}
