package com.ahri.mobike;

import com.ahri.mobike.action.MobikeAction;
import com.ahri.mobike.bean.MobikeBicycle;
import com.ahri.mobike.bean.User;
import com.ahri.mobike.util.RegexUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class Test {

    private static final String top_tip = "请输入相关指令进行操作: \n" +
            "登录: LOGIN, 注册: REGISTER, 注销: LOGOUT, 退出: EXIT \n" +
            "取车: RENT, 还车: RETURN";

    private static final String REGISTER = "REGISTER";
    private static final String LOGIN = "LOGIN";
    private static final String RENT = "RENT";
    private static final String EXIT = "EXIT";
    private static final String LOGOUT = "LOGOUT";

    private static boolean isLogin = false;
    private static String currentOperation = "";
    private static User currentUser;
    private static Scanner scanner = new Scanner(System.in);

    private static String sessionKey = "adikdiqlhaq88686a8qjdg124dgs";

    public static void main(String[] args) {
        try {
            System.out.println(top_tip);
            while (scanner.hasNext()) {
                String s = scanner.next().toString().toUpperCase();
                switch (s) {
                    case REGISTER:
                        currentOperation = REGISTER;
                        register();
                        break;
                    case LOGIN:
                        currentOperation = LOGIN;
                        login();
                        break;
                    case RENT:
                        currentOperation = RENT;
                        rent();
                        break;
                    case EXIT:
                        return;
                    case LOGOUT:
                        logout();
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static void register() throws SQLException {

        System.out.println("请输入电话号码: ");
        String phone = "";
        while (true) {
            phone = scanner.next().toString();
            if (RegexUtil.matchPhoneNumber(phone)) {
                System.out.println("请输入用户名: ");
                break;
            } else {
                System.out.println("电话输入错误,请重新输入: ");
            }
        }

        String name = "";
        while (true) {
            name = scanner.next().toString();
            if (name == null || name.length() == 0) {
                System.out.println("用户名输入错误,请重新输入: ");
            } else {
                System.out.println("请输入身份证号: ");
                break;
            }
        }

        String idCardNumber = "";
        while (true) {
            idCardNumber = scanner.next().toString();
            if (RegexUtil.matchIdCardNumber(idCardNumber)) {
                System.out.println("请设置密码, 以字母开头，长度在6~18之间，只能包含字符、数字和下划线: ");
                break;
            } else {
                System.out.println("身份证号输入错误,请重新输入: ");
            }

        }

        String password = "";
        while (true) {
            password = scanner.next().toString();
            if (RegexUtil.matchPassword(password)) {
                break;
            } else {
                System.out.println("密码格式错误, 必须以字母开头，长度在6~18之间，只能包含字符、数字和下划线,请重新输入: ");
            }

        }
        Random random = new Random();
        int salt = (int) (random.nextDouble() * 1.5 * 100);
        User user = new User();
        user.setUserName(name);
        user.setIdCardNntecumber(idCardNumber);
        user.setUserPhone(phone);
        user.setPassword(DigestUtils.md5Hex(password + salt + sessionKey));

        MobikeAction action = new MobikeAction();

        boolean registerSuccess = action.register(user, salt);
        if (registerSuccess) {
            System.out.println("注册成功, 请登录");
        } else {
            System.out.println("注册失败, 请检查手机或者身份证号是否已经注册过,请重新注册");
        }
    }

    private static void login() {
        if (isLogin) {
            System.out.println("已经登录, 请注销后再进行登录");
            return;
        }
        System.out.println("请输入手机号");
        String phone = "";
        while (true) {
            phone = scanner.next().toString();
            if (RegexUtil.matchPhoneNumber(phone)) {
                System.out.println("请输入密码: ");
                break;
            } else {
                System.out.println("电话输入格式错误, 请重新输入: ");
            }
        }

        String password = "";
        while (true) {
            password = scanner.next().toString();
            if (RegexUtil.matchPassword(password)) {
                break;
            } else {
                System.out.println("密码格式错误,请重新输入: ");
            }

        }
        Map<String, String> params = new TreeMap<>();
        params.put("PHONE", phone);
        params.put("PASSWORD", password);
        String sign = makeSign(params);
        MobikeAction action = new MobikeAction();

        try {
            User user = action.login(phone, password, sign);
            if (user == null) {
                System.out.println("用户不存在或者密码不正确");
                System.out.println("登录失败,请重新输入 LOGIN 进行登录");
            } else {
                isLogin = true;
                currentUser = user;
                System.out.println("登录成功, 当前用户为: " + currentUser.getUserName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("登录失败,请重新输入 LOGIN 进行登录");
        }

    }

    private static void logout() {
        if (!isLogin) {
            System.out.println("没有用户登录, 请先登录");
            return;
        }
        System.out.println("是否注销 Y/N ?");
        while (true) {
            String s = scanner.next().toString();
            switch (s) {
                case "Y":
                    isLogin = false;
                    currentUser = null;
                    System.out.println("已注销, 请注册或者重新登录");
                    return;
                case "N":
                    System.out.println("取消注销操作");
                    return;
                default:
                    System.out.println("是否注销 Y/N ?");
            }
        }
    }


    private static void rent() throws SQLException {
        if(currentUser == null) {
            System.out.println("请先登录, 若如账号请先注册");
            return;
        }

        MobikeAction action = new MobikeAction();
        List<MobikeBicycle> mobikeBicycles = action.searchMobike(currentUser.getLatitude(), currentUser.getLongitude());
        MobikeBicycle mobikeBicycle = null;
        if (mobikeBicycles != null && mobikeBicycles.size() > 0){
            mobikeBicycle = mobikeBicycles.get(0);
        }else {
            System.out.println("附近无可用车辆, 请稍后再试");
            return;
        }

        boolean openLock = action.openLock(currentUser, mobikeBicycle.getMobikeId());
        if (openLock) {
            System.out.println("取车成功, 开始计费");
        }else {
            System.out.println("取车失败,请重新操作");
        }
    }

    private static String makeSign(Map<String, String> params){

        StringBuilder builder = new StringBuilder();
        builder.append(sessionKey);
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry: entries){
            builder.append(entry.getKey());
            builder.append(entry.getValue());
        }
        builder.append(sessionKey);
        System.out.println("Client Sign String:" + builder.toString());
        return DigestUtils.md5Hex(builder.toString()).toUpperCase();
    }
}
