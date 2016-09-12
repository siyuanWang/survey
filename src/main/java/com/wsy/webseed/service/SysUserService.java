package com.wsy.webseed.service;

import com.wsy.webseed.domain.SysUserVo;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public interface SysUserService {
    public SysUserVo login(String pin, String password);

    public boolean updatePassword(String pin, String origin, String newPass, String confirmPass);
}
