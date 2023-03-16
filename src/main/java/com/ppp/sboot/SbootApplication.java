package com.ppp.sboot;

import com.ppp.sboot.entity.User;
import com.ppp.sboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//p6      23min
@SpringBootApplication

public class SbootApplication {


    public static void main(String[] args) {
        SpringApplication.run(SbootApplication.class, args);
    }

}
