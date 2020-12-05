package com.ppf.springboot.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Product {

    private Long id;
    private String name;
    private Integer age;
    private Date date;




}
