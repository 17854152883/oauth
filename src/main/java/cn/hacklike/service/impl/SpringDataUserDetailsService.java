package cn.hacklike.service.impl;

import cn.hacklike.mapper.UserDao;
import cn.hacklike.model.CloudUser;
import cn.hacklike.model.UserDto;
import cn.hacklike.service.CloudUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Resource
    BCryptPasswordEncoder encode;

    @Autowired
    private CloudUserService cloudUserService;


    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //将来连接数据库根据账号查询用户信息
        CloudUser cloudUser = cloudUserService.queryOneUser(username);

        if(cloudUser == null){
            //如果用户查不到，返回null，由provider来抛出异常
            return null;
        }
        //根据用户的id查询用户的权限
//        List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
        List<String> permissions = new ArrayList<String>();
        permissions.add("/*");
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        //将userDto转成json
        String principal = JSON.toJSONString(cloudUser);
        UserDetails userDetails = User.withUsername(principal).password(cloudUser.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }
}