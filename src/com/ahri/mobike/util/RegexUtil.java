package com.ahri.mobike.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zouyingjie on 2017/2/22.
 */
public class RegexUtil {

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

    public static boolean matchIdCardNumber(String idCardNumber) {
        if (idCardNumber == null || idCardNumber.length() == 0) {
            return false;
        }
        String regEx = "^(\\d{6})(19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X|x)?$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(idCardNumber);
        boolean matches = matcher.matches();
        return matches;
    }

    public static boolean matchCheckCode(String checkCode){
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

}
