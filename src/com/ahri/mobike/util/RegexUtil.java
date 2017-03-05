package com.ahri.mobike.util;

import com.ahri.mobike.constant.GB2260;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zouyingjie on 2017/2/22.
 */
public class RegexUtil {

    //wi =2(n-1)(mod 11)
    private static final int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
    // verify digit
    private static final int[] vi = {1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2};
    private static int[] ai = new int[18];

    public static boolean matchPhoneNumber(String phone) {
        if (phone == null || phone.length() == 0) {
            return false;
        }
        String regEx = "^1[0-9][0-9]\\d{8}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(phone);
        boolean rs = matcher.matches();
        return rs;
    }

    /**
     * 校验身份证
     *
     * @param idCardNumber
     * @return
     */
    public static boolean matchIdCardNumber(String idCardNumber) {

        if (idCardNumber == null) {
            return false;
        }

        if (idCardNumber.length() != 18 && idCardNumber.length() != 15) {
            return false;
        }

        //15位转换为18位
        if (idCardNumber.length() == 15) {
            idCardNumber = uptoeighteen(idCardNumber);
        }
        String areaCode = idCardNumber.substring(0, 6);
        if (GB2260.verifyAreaCode(areaCode) & verifyBirthdayCode(idCardNumber)
                && verifyMOD(idCardNumber)) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean checkBirth(String date) {
        String regEx = "^(19|20) (0(1-9)|1(1-2)) (0(1-9)|(1|2)(0-9)|3(0-1))";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();

    }

    public static boolean matchCheckCode(String checkCode) {
        if (checkCode == null || checkCode.length() == 0) {
            return false;
        }
        String regEx = "^\\d{4}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(checkCode);
        boolean matches = matcher.matches();
        return matches;
    }

    /**
     * 验证密码格式, 以字母开头，长度在6~18之间，只能包含字符、数字和下划线
     *
     * @param password
     * @return
     */
    public static boolean matchPassword(String password) {
        if (password == null || password.length() == 0) {
            return false;
        }

        String regex = "^[a-zA-Z]\\w{5,17}$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(password);
        boolean matches = matcher.matches();
        return matches;
    }

    /**
     * 判断月份和日期
     * @param idNumber
     * @return
     */
    public static boolean verifyBirthdayCode(String idNumber) {
        HashMap<String, Integer> dateMap = new HashMap<String, Integer>();
        dateMap.put("01", 31);
        dateMap.put("02", null);
        dateMap.put("03", 31);
        dateMap.put("04", 30);
        dateMap.put("05", 31);
        dateMap.put("06", 30);
        dateMap.put("07", 31);
        dateMap.put("08", 31);
        dateMap.put("09", 30);
        dateMap.put("10", 31);
        dateMap.put("11", 30);
        dateMap.put("12", 31);
        //验证月份
        String month = idNumber.substring(10, 12);
        if (!dateMap.containsKey(month)) {
            return false;
        }
        //验证日期
        String dayCode = idNumber.substring(12, 14);
        Integer day = dateMap.get(month);
        String yearCode = idNumber.substring(6, 10);
        Integer year = Integer.valueOf(yearCode);

        //非2月的情况
        if (day != null) {
            if (Integer.valueOf(dayCode) > day || Integer.valueOf(dayCode) < 1) {
                return false;
            }
        }
        //2月的情况
        else {
            //闰月的情况
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                if (Integer.valueOf(dayCode) > 29 || Integer.valueOf(dayCode) < 1) {
                    return false;
                }
            }
            //非闰月的情况
            else {
                if (Integer.valueOf(dayCode) > 28 || Integer.valueOf(dayCode) < 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获得校验位 Sum(wi*ai)%11
     * @param eightcardid
     * @return
     */
    public static String getVerify(String eightcardid) {
        int remaining = 0;

        if (eightcardid.length() == 18) {
            eightcardid = eightcardid.substring(0, 17);
        }

        if (eightcardid.length() == 17) {
            int sum = 0;
            for (int i = 0; i < 17; i++) {
                String k = eightcardid.substring(i, i + 1);
                ai[i] = Integer.parseInt(k);
            }

            for (int i = 0; i < 17; i++) {
                sum = sum + wi[i] * ai[i];
            }
            remaining = sum % 11;
        }

        return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
    }


    /**
     * 十五位身份证转换为18位
     * @param fifteencardid
     * @return
     */
    public static String uptoeighteen(String fifteencardid) {
        String eightcardid = fifteencardid.substring(0, 6);
        eightcardid = eightcardid + "19";
        eightcardid = eightcardid + fifteencardid.substring(6, 15);
        eightcardid = eightcardid + getVerify(eightcardid);
        return eightcardid;
    }

    /**
     * 校验第18位验证码
     *
     * @param code
     * @return
     */
    public static boolean verifyMOD(String code) {
        String verify = code.substring(17, 18);
        if ("x".equals(verify)) {
            code = code.replaceAll("x", "X");
            verify = "X";
        }
        String verifyIndex = getVerify(code);
        if (verify.equals(verifyIndex)) {
            return true;
        }
        return false;
    }
}
