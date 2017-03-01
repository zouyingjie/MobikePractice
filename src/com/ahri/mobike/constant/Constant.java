package com.ahri.mobike.constant;

/**
 * Created by zouyingjie on 2017/2/22.
 */
public class Constant {

    //用户是否已经有单车租借
    public static final int IS_CYCLING = 1;//有租借
    public static final int NOT_CYCLING = 0;//没有租借

    //单车使用状态
    public static final int IS_USING = 1; //使用中
    public static final  int NOT_USING = 0; //未使用


    //单车类型
    public static final int MOBIKE_TYPE_NORMAL = 1;
    public static final int MOBIKE_TYPE_LITE = 2;

    //交易支付类型
    public static final int PAY_WEIXIN = 0;//微信支付
    public static final int PAY_ALI = 1;//支付宝支付
    public static final int PAY_BALANCE = 2;//骑车时使用余额

    //交易类型
    public static final int DEAL_TYPE_RECHARGE = 0;//充值
    public static final int DEAL_TYPE_REFUND = 1;//退款
    public static final int DEAL_TYPE_COMSUME = 2;//消费
    public static final int DEAL_TYPE_ANTECEDENT = 3;//充押金

    //交易状态
    public static final int DEAL_START = 0;//创建交易订单
    public static final int DEAL_FINISH__SUCCESS = 1;//交易完成,成功
    public static final int DEAL_FINISH__FAILURE = 2;//交易完成，失败
    public static final int DEAL_WAIT_PAY = 3;//正在进行,待支付
    public static final int DEAL_WAIT_REFUND = 4;//正在进行,待退款


}
