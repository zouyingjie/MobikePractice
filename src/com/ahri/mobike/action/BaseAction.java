package com.ahri.mobike.action;

import com.ahri.mobike.util.BasicUtils;

import java.util.TreeMap;

/**
 * Created by zouyingjie on 2017/3/4.
 */
public class BaseAction {

    protected static final String sessionKey = "adikdiqlhaq88686a8qjdg124dgs";

    public boolean checkSign(TreeMap<String, String> params, String sign) {
        String checkSign = BasicUtils.makeSign(params, sessionKey);
        return checkSign.equals(sign);
    }

}
