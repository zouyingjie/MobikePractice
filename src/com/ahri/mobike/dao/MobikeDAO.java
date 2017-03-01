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

    private MobikeDAO() {}

    public static MobikeDAO getInstance(){
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
                mobikeBicycle.getMobikeType() + "," +
                mobikeBicycle.getPurchasePrice() + "," +
                "?" + "," +
                "?" + "," +
                mobikeBicycle.getRoadDistance() + "," +
                mobikeBicycle.getIsUsing() + "," +
                "?," +
                "?," +
                "?);";

        PreparedStatement prst = conn.prepareStatement(sql);

        prst.setString(1, new Timestamp(mobikeBicycle.getPurchaseCtime().getTime()).toString());
        prst.setString(2, new Timestamp(mobikeBicycle.getLastUsetime().getTime()).toString());
        prst.setString(3, mobikeBicycle.getLatitude());
        prst.setString(4, mobikeBicycle.getLongitude());
        prst.setString(5, mobikeBicycle.getUserId());
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
        String sql = "SELECT user_id, mobike_id, mobike_type, latitude, longitude FROM MOBIKE_BICYCLE WHERE " +
                "latitude <=  " + (latitudeValue+0.0001) +" AND "+ " latitude >= " + (latitudeValue-0.0001) + " AND " +
                "longitude <= " + (longitudeValue+0.0001) + " AND " + "longitude >= " + (longitudeValue-0.0001) +" AND " +
                "is_using = 0";

        PreparedStatement prst = conn.prepareStatement(sql);
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
        String sql = "UPDATE MOBIKE_BICYCLE SET latitude = ? , longitude = ? WHERE mobike_id = " + mobikeBicycle.getMobikeId();

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, mobikeBicycle.getLatitude());
        prst.setString(2, mobikeBicycle.getLongitude());
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
        String sql = "UPDATE MOBIKE_BICYCLE SET last_usetime = ?  WHERE mobike_id = " + mobikeBicycle.getMobikeId();

        PreparedStatement prst = conn.prepareStatement(sql);
        Timestamp lastUseTime = new Timestamp(mobikeBicycle.getLastUsetime().getTime());
        prst.setString(1, lastUseTime.toString());
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
        String sql = "UPDATE MOBIKE_BICYCLE SET user_id = ? WHERE mobike_id = " + mobikeBicycle.getMobikeId();

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, mobikeBicycle.getUserId());
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
        String sql = "UPDATE MOBIKE_BICYCLE SET is_using = " + mobikeBicycle.getIsUsing() + " WHERE mobike_id = " + mobikeBicycle.getMobikeId();

        PreparedStatement prst = conn.prepareStatement(sql);
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
        String sql = "UPDATE MOBIKE_BICYCLE SET road_distance = " + mobikeBicycle.getRoadDistance() + " WHERE mobike_id = " + mobikeBicycle.getMobikeId();

        PreparedStatement prst = conn.prepareStatement(sql);
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }


}
