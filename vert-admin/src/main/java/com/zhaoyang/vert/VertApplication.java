package com.zhaoyang.vert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/5
 */
@SpringBootApplication
@Slf4j
public class VertApplication {

    public static void main(String[] args) {
        SpringApplication.run(VertApplication.class, args);
        log.info("VertApplication is success");
    }

}
