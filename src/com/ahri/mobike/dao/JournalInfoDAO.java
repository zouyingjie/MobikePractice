package com.ahri.mobike.dao;

import com.ahri.mobike.bean.JournalInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class JournalInfoDAO extends BaseDAO{
    private static JournalInfoDAO DAO;
    private JournalInfoDAO(){}

    public static JournalInfoDAO getInstance(){
        if (DAO == null) {
            synchronized (JournalInfoDAO.class) {
                if (DAO == null) {
                    DAO = new JournalInfoDAO();
                }
            }
        }
        return DAO;
    }

    /**
     * 新增一条行程信息
     *
     * 开始骑车时生成,只需要用户id, mobike_id与起始时间
     */
    public void insert(JournalInfo journalInfo) throws SQLException {
        String sql = "INSERT INTO JOURNAL_INFO (" +
                        "user_id ," +
                        "mobike_id ," +
                        "start_ctime)" +
                    " VALUES (" +
                        "?,?,?);";

        PreparedStatement prst = conn.prepareStatement(sql);
        Timestamp startTimeStamp = new Timestamp(journalInfo.getStartCtime().getTime());
        prst.setString(1, journalInfo.getUserId());
        prst.setString(2, journalInfo.getMobikeId());
        prst.setString(3, startTimeStamp.toString());
        boolean execute = prst.execute();
        if (prst.getUpdateCount() > 0) {
            System.out.println("Insert Success");
        }else {
            System.out.println("Insert Failure");
        }
    }

    /**
     * 结束时更新行程信息操作
     * 加入花费,结束时间,运动成就,节约碳排放
     * 卡路里,距离
     */

    public void updateJournalInfo(JournalInfo journalInfo) throws SQLException {

        String sql = "UPDATE JOURNAL_INFO(" +
                "cost," +
                "enery_save, " +
                "distance," +
                "sports_achieve," +
                "?"+
                ");";
        PreparedStatement prst = conn.prepareStatement(sql);
        Timestamp endTimeStamp = new Timestamp(journalInfo.getEndCtime().getTime());
        prst.setString(1, endTimeStamp.toString());
        boolean execute = prst.execute(sql);
        if (prst.getUpdateCount() > 0) {
            System.out.println("Update Success");
        }else {
            System.out.println("Update Failure");
        }
    }
    /**
     * 查询行程列表
     */

    public List<JournalInfo> queryAll(String userId) throws SQLException {

        String sql = "SELECT * FROM JOURNAL_INFO WHERE user_id = ?;";

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, userId);
        ResultSet rs = prst.executeQuery();
        List<JournalInfo> journalInfos = new ArrayList<>();
        JournalInfo journalInfo = null;
        while (rs.next()){
            journalInfo = new JournalInfo();
            journalInfo.setJournalId(rs.getString("journal_id"));
            journalInfo.setUserId(rs.getString("user_id"));
            journalInfo.setMobikeId(rs.getString("mobike_id"));
            journalInfo.setStartCtime(new java.util.Date(rs.getTimestamp("start_ctime").getTime()));
            journalInfo.setEndCtime(new java.util.Date(rs.getTimestamp("end_ctime").getTime()));
            journalInfo.setCost(rs.getDouble("cost"));
            journalInfo.setEnerySave(rs.getDouble("enery_save"));
            journalInfo.setSportsAchieve(rs.getDouble("sports_achieve"));
            journalInfo.setDistance(rs.getDouble("distance"));
            journalInfo.setPath(rs.getString("path"));
            journalInfos.add(journalInfo);
        }
        return journalInfos;
    }
}
