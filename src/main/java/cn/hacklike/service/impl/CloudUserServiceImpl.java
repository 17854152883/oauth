package cn.hacklike.service.impl;

import cn.hacklike.mapper.CloudUserMapper;
import cn.hacklike.model.CloudUser;
import cn.hacklike.service.CloudUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CloudUserServiceImpl implements CloudUserService {

    @Resource
    private CloudUserMapper cloudUserMapper;

    @Override
    public CloudUser queryOneUser(String username) {

        List<CloudUser> cloudUsers = cloudUserMapper.selectList(new QueryWrapper<CloudUser>().eq("username", username));

        if(cloudUsers.size() > 0){
            return cloudUsers.get(0);
        }
        return null;
    }

}
