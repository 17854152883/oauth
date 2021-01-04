package cn.hacklike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"cn"})
@MapperScan("cn.hacklike.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class Oauth {

    public static void main(String[] args) {
        SpringApplication.run(Oauth.class,args);
    }

}
