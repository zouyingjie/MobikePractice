package com.ahri.mobike.dao;

import com.ahri.mobike.bean.MobikeBicycle;
import com.ahri.mobike.constant.Constant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class MobikeDAO extends BaseDAO {

    private static MobikeDAO DAO;

    private MobikeDAO() {
    }

    public static MobikeDAO getInstance() {
        if (DAO == null) {
            synchronized (MobikeDAO.class) {
                if (DAO == null) {
                    DAO = new MobikeDAO();
                }
            }
        }
        return DAO;
    }

    /**
     * 添加一辆新车
     */
    public void insert(MobikeBicycle mobikeBicycle) throws SQLException {
        String sql = "INSERT INTO MOBIKE_BICYCLE (" +
                "mobike_type," +
                "purchase_price," +
                "purchase_ctime," +
                "last_usetime ," +
                "road_distance," +
                "is_using," +
                "latitude," +
                "longitude," +
                "user_id" + ")" +
                "values(" +
                "?,?,?,?,?,?,?,?,?);";


        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setInt(1, mobikeBicycle.getMobikeType());
        prst.setDouble(2, mobikeBicycle.getPurchasePrice());
        prst.setString(3, new Timestamp(mobikeBicycle.getPurchaseCtime().getTime()).toString());
        prst.setString(4, new Timestamp(mobikeBicycle.getLastUsetime().getTime()).toString());
        prst.setDouble(5, mobikeBicycle.getRoadDistance());
        prst.setInt(6, mobikeBicycle.getIsUsing());
        prst.setString(7, mobikeBicycle.getLatitude());
        prst.setString(8, mobikeBicycle.getLongitude());
        prst.setString(9, mobikeBicycle.getUserId());
        System.out.println(sql);
        boolean execute = prst.execute();
        if (prst.getUpdateCount() > 0) {
            System.out.println("Insert Success");
        } else {
            System.out.println("Insert Failure");
        }
    }

    /**
     * 查询附近的车
     */
    public List<MobikeBicycle> searchMobike(String latitude, String longitude) throws SQLException {
        double latitudeValue = Double.valueOf(latitude);
        double longitudeValue = Double.valueOf(longitude);
        String sql = "SELECT " +
                "user_id, " +
                "mobike_id, " +
                "mobike_type, " +
                "latitude, l" +
                "ongitude " +
                "FROM MOBIKE_BICYCLE WHERE " +
                "latitude <=  ?" +
                " AND " + "latitude >=  ?" +
                " AND " + "longitude <=  ?" +
                " AND " + "longitude >=  ?" +
                " AND " + "is_using = ?;";

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setDouble(1, latitudeValue + 0.0001);
        prst.setDouble(2, latitudeValue - 0.0001);
        prst.setDouble(3, longitudeValue + 0.0001);
        prst.setDouble(4, longitudeValue - 0.0001);
        prst.setInt(5, Constant.NOT_USING);
        ResultSet rs = prst.executeQuery();
        List<MobikeBicycle> mobikeBicycleList = new ArrayList<>();
        MobikeBicycle mobikeBicycle = null;
        while (rs.next()) {
            mobikeBicycle = new MobikeBicycle();
            mobikeBicycle.setIsUsing(Constant.NOT_USING);
            mobikeBicycle.setUserId(rs.getString("user_id"));
            mobikeBicycle.setMobikeId(rs.getString("mobike_id"));
            mobikeBicycle.setMobikeType(rs.getInt("mobike_type"));
            mobikeBicycle.setLongitude(rs.getString("longitude"));
            mobikeBicycle.setLatitude(rs.getString("latitude"));
            mobikeBicycleList.add(mobikeBicycle);
        }
        return mobikeBicycleList;
    }

    /**
     * 修改位置经纬度
     */
    public void updateLocation(MobikeBicycle mobikeBicycle) throws SQLException {
        String sql = "UPDATE MOBIKE_BICYCLE SET latitude = ? , longitude = ? WHERE mobike_id = ?;";

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, mobikeBicycle.getLatitude());
        prst.setString(2, mobikeBicycle.getLongitude());
        prst.setString(3, mobikeBicycle.getMobikeId());
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }


    /**
     * 改上次使用使用时间
     */
    public void updateLastUseTime(MobikeBicycle mobikeBicycle) throws SQLException {
        String sql = "UPDATE MOBIKE_BICYCLE SET last_usetime = ?  WHERE mobike_id = ?;";

        PreparedStatement prst = conn.prepareStatement(sql);
        Timestamp lastUseTime = new Timestamp(mobikeBicycle.getLastUsetime().getTime());
        prst.setString(1, lastUseTime.toString());
        prst.setString(2, mobikeBicycle.getMobikeId());
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }

    /**
     * 修改当前使用用户
     */
    public void updateUser(MobikeBicycle mobikeBicycle) throws SQLException {
        String sql = "UPDATE MOBIKE_BICYCLE SET user_id = ? WHERE mobike_id = ?;";

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, mobikeBicycle.getUserId());
        prst.setString(2, mobikeBicycle.getMobikeId());
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }

    /**
     * 修改当前使用状态
     */

    public void updateIsUsing(MobikeBicycle mobikeBicycle) throws SQLException {
        String sql = "UPDATE MOBIKE_BICYCLE SET is_using = ? WHERE mobike_id = ?;";

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setInt(1, mobikeBicycle.getIsUsing());
        prst.setString(2, mobikeBicycle.getMobikeId());
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }

    /**
     * 修改里程
     */

    public void updateRoadDistance(MobikeBicycle mobikeBicycle) throws SQLException {
        String sql = "UPDATE MOBIKE_BICYCLE SET road_distance = ? WHERE mobike_id = ?;" ;

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setDouble(1, mobikeBicycle.getRoadDistance());
        prst.setString(2, mobikeBicycle.getMobikeId());
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }


}
