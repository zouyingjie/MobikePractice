package com.ahri.mobike.dao;

import com.ahri.mobike.bean.DealDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class DetailDAO extends BaseDAO {
    private static DetailDAO DAO;

    private DetailDAO() {
    }

    public static DetailDAO getInstance() {
        if (DAO == null) {
            synchronized (DetailDAO.class) {
                if (DAO == null) {
                    DAO = new DetailDAO();
                }
            }
        }
        return DAO;
    }

    /**
     * 查询交易列表
     */
    public List<DealDetail> quary(String userId) throws SQLException {

        String sql = "SELECT deal_type, " +
                "deal_money, " +
                "deal_finish_time, " +
                "deal_start_time, " +
                "deal_pay, deal_desc " +
                " FROM DEAL_DETAIL_LIST WHERE user_id = ? ORDER BY deal_start_time;";

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, userId);
        System.out.println(sql);
        ResultSet rs = prst.executeQuery();
        List<DealDetail> list = new ArrayList<>();
        DealDetail dealDetail = null;
        while (rs.next()) {
            dealDetail = new DealDetail();
            dealDetail.setDealDesc(rs.getString("deal_desc"));
            dealDetail.setDealMoney(rs.getDouble("deal_money"));
            dealDetail.setDealPay(rs.getInt("deal_pay"));
            dealDetail.setDealStartTime(rs.getDate("deal_start_time"));
            list.add(dealDetail);
        }
        return list;
    }

    /**
     * 增加一条交易信息
     */

    public void insert(DealDetail dealDetail) throws SQLException {


        String sql = "INSERT INTO DEAL_DETAIL_LIST (" +
                "user_id, " +
                "deal_money, " +
                "deal_type, " +
                "deal_pay ,  " +
                "deal_start_time, " +
                "deal_state)" +
                "values" + "(" +
                dealDetail.getUserId() + "?,?,?,?,?,?);";

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, dealDetail.getUserId());
        prst.setDouble(2, dealDetail.getDealMoney());
        prst.setInt(3, dealDetail.getDealType());
        prst.setDouble(4, dealDetail.getDealPay());
        Timestamp timestamp = new Timestamp(dealDetail.getDealStartTime().getTime());
        prst.setString(5, timestamp.toString());
        prst.setInt(6, dealDetail.getDealState());

        System.out.println(sql);
        boolean execute = prst.execute();
        if (prst.getUpdateCount() > 0) {
            System.out.println("Insert Success");
        } else {
            System.out.println("Insert Failure");
        }
    }


}
