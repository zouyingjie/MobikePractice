package com.ahri.mobike.action;

import com.ahri.mobike.bean.DealDetail;
import com.ahri.mobike.bean.JournalInfo;
import com.ahri.mobike.bean.MobikeBicycle;
import com.ahri.mobike.bean.User;
import com.ahri.mobike.constant.Constant;
import com.ahri.mobike.dao.DetailDAO;
import com.ahri.mobike.dao.JournalInfoDAO;
import com.ahri.mobike.dao.MobikeDAO;
import com.ahri.mobike.dao.UserDAO;
import com.ahri.mobike.util.BasicUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zouyingjie on 2017/2/22.
 */
public class MobikeAction {

    private static final String sessionKey = "adikdiqlhaq88686a8qjdg124dgs";

    /**
     * 注册用户
     */

    public boolean register(User user, int salt) throws SQLException {
        UserDAO dao = UserDAO.getInstance();
        return dao.register(user, salt);
    }

    /**
     * 登录
     * 查看用户信息
     * 包括:总骑行公里, 节约碳排放, 运动成就
     * 昵称, 余额
     */
    public User login(String phone, String password, String sign) throws SQLException {
        Map<String, String> params = new TreeMap<>();
        params.put("PHONE", phone);
        params.put("PASSWORD", password);
        String checkSign = BasicUtils.makeSign(params, sessionKey);
        if (!checkSign.equals(sign)) {
            return null;
        }
        UserDAO userDAO = UserDAO.getInstance();
        int salt = userDAO.querySalt(phone);
        return userDAO.query(phone, DigestUtils.md5Hex(password + salt + sessionKey));
    }

    /**
     * 查询附近的车
     */

    public List<MobikeBicycle> searchMobike(String latitude, String longitude) throws SQLException {

        MobikeDAO mobikeDAO = MobikeDAO.getInstance();
        return mobikeDAO.searchMobike(latitude, longitude);
    }

    /**
     * 查看我的钱包
     */
    public String checkWallet(User user) {
        StringBuilder builder = new StringBuilder();
        builder.append("余额:");
        builder.append(user.getBalance());
        builder.append("元, ");
        builder.append("押金:");
        builder.append(user.getAntecedentMoney());
        builder.append("元");
        return builder.toString();
    }

    /**
     * 查看消费列表
     */
    public List<DealDetail> queryDealList(String userId) throws SQLException {
        DetailDAO detailDAO = DetailDAO.getInstance();
        return detailDAO.quary(userId);
    }


    /**
     * 查看行程列表
     */
    public List<JournalInfo> checkJournalList(String userId) throws SQLException {
        JournalInfoDAO journalInfoDAO = JournalInfoDAO.getInstance();
        return journalInfoDAO.queryAll(userId);
    }


    /**
     * 修改电话
     */
    public void updateUserPhone(String userId, String phone) throws SQLException {
        UserDAO userDAO = UserDAO.getInstance();
        User user = new User();
        user.setUserId(userId);
        user.setUserPhone(phone);
        userDAO.updateUserPhone(user);
    }

    /**
     * 修改昵称
     */
    public void updateUserName(String userId, String name) throws SQLException {
        UserDAO userDAO = UserDAO.getInstance();
        User user = new User();
        user.setUserId(userId);
        user.setUserName(name);
        userDAO.updateUserName(user);
    }

    /**
     * 开锁取车,开始交易
     */
    public boolean openLock(User user, String mobikeId)  {
        try {
            //更改单车的使用状态,使用用户,最后使用时间,
            MobikeDAO mobikeDAO = MobikeDAO.getInstance();
            MobikeBicycle mobikeBicycle = new MobikeBicycle();
            mobikeBicycle.setMobikeId(mobikeId);
            mobikeBicycle.setIsUsing(Constant.IS_USING);
            mobikeBicycle.setLastUsetime(new Date());
            mobikeBicycle.setUserId(user.getUserId());
            mobikeDAO.updateIsUsing(mobikeBicycle);
            mobikeDAO.updateUser(mobikeBicycle);
            mobikeDAO.updateLastUseTime(mobikeBicycle);
            mobikeDAO.updateUser(mobikeBicycle);

            //创建一条交易信息,类型为消费,待支付,支付方式为余额
            DealDetail dealDetail = new DealDetail();
            dealDetail.setUserId(user.getUserId());
            dealDetail.setDealStartTime(new Date());
            dealDetail.setDealPay(Constant.PAY_BALANCE);
            dealDetail.setDealType(Constant.DEAL_TYPE_COMSUME);
            dealDetail.setDealState(Constant.DEAL_START);
            DetailDAO detailDAO = DetailDAO.getInstance();
            detailDAO.insert(dealDetail);

            //更改用户的骑车状态
            UserDAO userDAO = UserDAO.getInstance();
            user.setIsCycling(Constant.IS_CYCLING);
            userDAO.updateIsCycling(user);

            //创建一条行程信息
            JournalInfo journalInfo = new JournalInfo();
            journalInfo.setUserId(user.getUserId());
            journalInfo.setMobikeId(mobikeId);
            journalInfo.setStartCtime(new Date());
            JournalInfoDAO journalInfoDAO = JournalInfoDAO.getInstance();
            journalInfoDAO.insert(journalInfo);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    /**
     * 还车,结算交易
     */
    public void lock(User user, MobikeBicycle mobikeBicycle, DealDetail dealDetail, JournalInfo journalInfo) throws SQLException {
        //修改单车状态与里程
        MobikeDAO mobikeDAO = MobikeDAO.getInstance();
        mobikeDAO.updateIsUsing(mobikeBicycle);
        mobikeDAO.updateLastUseTime(mobikeBicycle);
        mobikeDAO.updateUser(mobikeBicycle);
        mobikeDAO.updateRoadDistance(mobikeBicycle);
        mobikeDAO.updateLocation(mobikeBicycle);

        //拿到之前的交易id修改信息,
        DetailDAO detailDAO = DetailDAO.getInstance();


        //修改用户里程/余额/运动成就/节约碳排放
        UserDAO userDAO = UserDAO.getInstance();
        userDAO.updateBalance(user);
        userDAO.updateIsCycling(user);

        //修改行程信息
        JournalInfoDAO journalInfoDAO = JournalInfoDAO.getInstance();
        journalInfoDAO.updateJournalInfo(journalInfo);

    }

}
