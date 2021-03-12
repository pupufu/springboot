package com.ppf.springboot.entity;





import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class User implements Serializable{

//我来改一改，1112

    private Long id;
    private String name;
    private Integer age;//555
    private Float grade;//777
    private Date birthday;
//dsa
//pp99999

}
