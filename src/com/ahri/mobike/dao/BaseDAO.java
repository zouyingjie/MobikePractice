package com.ahri.mobike.dao;

import com.ahri.mobike.util.DBUtil;

import java.sql.Connection;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class BaseDAO {
    protected Connection conn;
    protected BaseDAO(){
        conn = DBUtil.getConnection();
    }

}
