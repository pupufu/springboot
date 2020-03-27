package com.ppf.springboot.entity;





import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

//我来改一改，111
    //888
    private Long id;
    private String name;
    private Integer age;//555
    private Float grade;
    private Date birthday;

//pp123456
////00045
    public User() {
    }


    public Long getId() {
        return id;
    }
//
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", birthday=" + birthday +
                '}';
    }
}
