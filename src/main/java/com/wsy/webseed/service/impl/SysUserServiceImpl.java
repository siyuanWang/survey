package com.wsy.webseed.service.impl;

import com.wsy.webseed.dao.SysUserMapper;
import com.wsy.webseed.domain.SysUserVo;
import com.wsy.webseed.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
@Service
public class SysUserServiceImpl implements SysUserService{
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUserVo login(String pin, String password) {
        boolean flag = false;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loginName", pin);
        List<SysUserVo> list = sysUserMapper.query(param);
        if(list.size() < 1) {
            LOGGER.warn("loginName:{} 不存在", pin);
            return null;
        } else if(list.size() > 1) {
            LOGGER.warn("loginName:{} 不唯一", list.get(0).getLoginName());
        }
        SysUserVo vo = list.get(0);
        BASE64Encoder encoder = new BASE64Encoder();
        String _password = encoder.encode(password.getBytes());
        LOGGER.info(_password);
        if(_password.equals(vo.getPassword())) {
            return vo;
        }
        return null;
    }

    @Override
    public boolean updatePassword(String pin, String origin, String newPass, String confirmPass) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loginName", pin);
        List<SysUserVo> list = sysUserMapper.query(param);
        if(list.size() < 1) {
            LOGGER.warn("loginName:{} 不存在", pin);
            return false;
        } else if(list.size() > 1) {
            LOGGER.warn("loginName:{} 不唯一", list.get(0).getLoginName());
        }

        SysUserVo vo = list.get(0);
        BASE64Encoder encoder = new BASE64Encoder();
        String _password = encoder.encode(origin.getBytes());
        LOGGER.info(_password);
        if(_password.equals(vo.getPassword())) {
            if(!newPass.equals(confirmPass)) {
                LOGGER.warn("newPass not equal confirmPass, newPass:{}, confirmPass:{}", newPass, confirmPass);
                return false;
            }
            Map<String, Object> param1 = new HashMap<String, Object>();
            param1.put("pin", pin);
            param1.put("password", encoder.encode(newPass.getBytes()));
            sysUserMapper.updPassword(param1);
        } else {
            return false;
        }
        return true;
    }
}
