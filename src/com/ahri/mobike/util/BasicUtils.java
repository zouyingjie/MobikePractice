package com.ahri.mobike.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.Set;

/**
 * Created by zouyingjie on 2017/2/23.
 */
public class BasicUtils {

    public static String makeSign(Map<String, String> params, String sessionKey){

        StringBuilder builder = new StringBuilder();
        builder.append(sessionKey);
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry: entries){
            builder.append(entry.getKey());
            builder.append(entry.getValue());
        }
        builder.append(sessionKey);

        return DigestUtils.md5Hex(builder.toString()).toUpperCase();
    }

}
