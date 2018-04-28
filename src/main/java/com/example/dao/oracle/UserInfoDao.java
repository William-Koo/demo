package com.example.dao.oracle;

import com.example.model.DataRow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @描述: 二次业务数据表
 * @版权: Copyright(C) 2017
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2017-12-25
 * @创建时间: 13:32
 */
public interface UserInfoDao {

    /**
     *
     * @return
     */
    List<DataRow> getUsers();

}
