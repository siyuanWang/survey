package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SysUserVo;

import java.util.List;
import java.util.Map;


public interface SysUserMapper extends ISqlMapper {

    /**
     * @return
     */
    public List<SysUserVo> query(Map<String, Object> param);
    
    /**
     * 将sysUser对象保存到SysUser
     * @param sysUser
     * @return
     */
    public int save(SysUserVo sysUser);
}
