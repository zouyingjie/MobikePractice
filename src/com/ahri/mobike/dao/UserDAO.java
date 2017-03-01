package com.ahri.mobike.dao;

import com.ahri.mobike.bean.User;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class UserDAO extends BaseDAO {

    private static UserDAO DAO;

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (DAO == null) {
            synchronized (UserDAO.class) {
                if (DAO == null) {
                    DAO = new UserDAO();
                }
            }
        }
        return DAO;
    }

    public List<User> queryAll() throws Exception {
        String sql = "select user_name, user_phone from USER";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        User user = null;
        List<User> resultList = new ArrayList<>();
        while (resultSet.next()) {
            user = new User();
            user.setUserName(resultSet.getString("user_name"));
            user.setUserPhone(resultSet.getString("user_phone"));
            resultList.add(user);
        }
        return resultList;
    }


    /**
     * 注册用户
     * 注册时需要信息:用户名，电话，身份证
     */
    public boolean register(User user, int salt) throws SQLException {
        String sql = "INSERT INTO USER (" +
                "user_name, " +
                "user_phone, " +
                "password," +
                "id_card_number," +
                "salt)" +
                "VALUES (" +
                "?,?,?,?,?);";

        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, user.getUserName());
        prst.setString(2, user.getUserPhone());
        prst.setString(3, user.getPassword());
        prst.setString(4, user.getIdCardNntecumber());
        prst.setInt(5, salt);

        try {
            boolean execute = prst.execute();
            int rowsAffected = prst.getUpdateCount();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
        }
    }


    public int querySalt(String phone) throws SQLException {
        String sql = "SELECT salt FROM USER WHERE user_phone = ?;";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, phone);
        ResultSet resultSet = prst.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("salt");
        }else {
            return 0;
        }
    }


    /**
     * 查询用户信息
     * 通过电话查询
     */
    public User query(String userPhone, String password) throws SQLException {

        String sql = "SELECT * FROM USER WHERE user_phone= ? AND " + "password = ?;";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, userPhone);
        prst.setString(2, password);
        ResultSet rs = prst.executeQuery();
        User result = null;
        while (rs.next()) {
            result = new User();
            result.setUserId(rs.getString("user_id"));
            result.setUserName(rs.getString("user_name"));
            result.setUserPhone(rs.getString("user_phone"));
            result.setIdCardNntecumber(rs.getString("id_card_number"));
            result.setBalance(rs.getDouble("balance"));
            result.setAntecedentMoney(rs.getDouble("antecedent_money"));
            result.setLatitude(rs.getString("latitude"));
            result.setLongitude(rs.getString("longitude"));
        }
        return result;
    }

    /**
     * 修改余额
     */
    public void updateBalance(User user) throws SQLException {
        String sql = "UPDATE USER SET balance = " + user.getBalance() + " WHERE user_id = " + user.getUserId();
        PreparedStatement prst = conn.prepareStatement(sql);
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }

    /**
     * 修改用户名
     */
    public void updateUserName(User user) throws SQLException {
        String sql = "UPDATE USER SET user_name = " + "?" + " WHERE user_id = " + user.getUserId();
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, user.getUserName());
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }

    /**
     * 修改电话号码
     */
    public void updateUserPhone(User user) throws SQLException {
        String sql = "UPDATE USER SET user_phone = " + "?" + " WHERE user_id = " + user.getUserId();
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, user.getUserPhone());
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }

    /**
     * 修改押金
     */

    public void updateAntecedentMoney(User user) throws SQLException {
        String sql = "UPDATE USER SET antecedent_money = " + "?" + " WHERE user_id = " + user.getUserId();
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setDouble(1, user.getAntecedentMoney());
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }

    /**
     * 修改经纬度
     */

    public void updateLocation(User user) throws SQLException {
        String sql = "UPDATE USER SET latitude = " + "? , longitude = " + "? WHERE user_id = " + user.getUserId();
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, user.getLatitude());
        prst.setString(2, user.getLongitude());
        int update = prst.executeUpdate();
        if (update > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }

    /**
     * 修改租车状态
     */
    public void updateIsCycling(User user) throws SQLException {
        String sql = "UPDATE USER SET is_cycling = " + user.getIsCycling() + " WHERE user_id = ?;";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, user.getUserId());
        boolean execute = prst.execute();
        if (prst.getUpdateCount() > 0) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failure");
        }
    }

    /**
     * 通过电话 查询UserId
     */
    public String queryUserId(String phone) throws SQLException {
        String sql = "SELECT user_id FROM USER WHERE user_phone = '" + phone + "'";
        PreparedStatement prst = conn.prepareStatement(sql);
        ResultSet resultSet = prst.executeQuery();
        String userId = "";
        while (resultSet.next()) {
            userId = resultSet.getString("user_id");
        }
        return userId;
    }
}
