package com.jychan.notbad.mapper;

import com.jychan.notbad.domain.RoleInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by chenjinying on 2017/5/7.
 * mail: 415683089@qq.com
 */
public interface RoleInfoMapper {

    @Select("select * from role_info ")
    List<RoleInfo> findAll();
}
