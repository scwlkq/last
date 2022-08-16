package com.iweb.sp.utils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;





@Component
public class AlipayUtil {

//    @Value("${alipay.appid}")
//    private String appid;
//    @Value("${alipay.url}")
//    private String url;
//    @Value("${alipay.privateKey}")
//    private String privateKey;
//    @Value("${alipay.publicKey}")
//    private String publicKey;
//    @Value("${alipay.notifyUrl}")
//    private String notifyUrl;
//    @Value("${alipay.returnUrl}")
//    private String returnUrl;
    private String appid = "2016102500758295";

    private String url="https://openapi.alipaydev.com/gateway.do";

    private String privateKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCMnuWmyjeqvC6vuqAFlPg+4kZNYGDgJKX+pg8pg/Y6SqbPz6EZKE+Edzsl5s+IFxClHDV+3R6ArOFEC1rBdtyy0bO9mFzhqVncLrnPrd5vXsQVg6e7UvLTsUg9OwIgQBn4gIfgJferMNuv8Yf/mEhdIMvAtH6WHnzTz/IY9bA0FgBd92zIlwTNWprHN3UDZOEwtl42JQ6QbyRzhlDdzvLbjWliG79zZ73QLZYTbRGasZZRMnkaGSWIDg9R97w9hlUCbt2KdSHSnwN9e9ZS8wcWC+TIZJn4ZpsGtJ10g3ehInWIS5jBkiW55DtCbkg7a33HUizDr02zUjTX81GJ/nfnAgMBAAECggEAN56580jUz+FzDerhVJPhxO23JO/UIZQdXCXOpXser1vLszLK46qAGUMX0BwFRizpVoVb23wWjorS5xkeopsjHE+5qmpTgqZ7do28zRpWevKe2GuksoFSrszWZ/GpG5x/KT2dgNlDpyDCJEuCoWRYaw2HygcacsJIWXdMqPa05USf9nj391L4MEq62fGduBcQd6yrV2UfD4WV1Ppgq4knc/JcSyr4QYAC1+oz/xU/1MufsmLzdktf0EmHPZ7xPodYcSZO15KCJphWpA4VqvAU95+MscwqHEdwcr+YUqp5XKpVVbnkMPKQmPgEnPXyjIWXif+M7HgU6S/x7qQRx2EkEQKBgQDNvDjzSGMYoxXwWUlEUwz/r7+UEEeOFFPk68r7WOoGQAUdCVtuNAr5wHlxVEpez7h2DRBxzdvUqdV2rOMHjD3YLQHBewpR8PH8sH0fu+Tx2Ph6JKQL8CPgfGY8E9JarTxoFfYQVImzv15IJqOM2DvKo3+abJsDlW2pKk/rY7vv9QKBgQCu+hBXK3CLfQ7Frg8jnb/T7VlwTCOim/CIK78mS0LLLVIO3V7ZXuIa8v6MbfvbmWHiKskHitLu0xuBwt1LTauSVpgRwEd1oDHS6NZnLb39xtFxddxdrRJ36pg4jHQUmzeWQ93Hro+rPjStFGEqK1jmouSxyNGOG96g/AMwoM8q6wKBgQCfpSowJoC3Mn4otrAy85tHoXIxC/zqlH2RSyOErTdUSENvh75KItpNeX0DYxwevGNwP6z+Enc+a9d4MqotRjcLU8lkAP0+HpE4ux8tC9mZjfsqlNozZVIBtaaDggIeWQVh44DApUkBkUMxC8Kn3XdwRDUVeKdFPaQNvAauKwNALQKBgQCktVnKZGa1t0u/g0BW+3iGSsdFMTbeK8I4vE1cA75lOU0kFvfg0DMwwQoiMKnDJlgAqNvBUMmL7IbZGBBtgTh/MSpGk2Pw8pHUAj9IssNEbvHaxuJf/7oBzX/qwzPF7fJebZYIEGbHAHBfeG6aXmF6AV4biHjSN6PSkEeEb+tzbwKBgQCkMztg9KgyoVVh9wBhuQuUwZvBktYvkL35Jpruk7/E2XpYJPruyjdud7GUHoySTbpQmycXZiJTZcdBHbubhqOKlxc8F41cX7/ijf/PI2TDTDYSalEuI/ebzwUlzh6A99SlAOBYtPdqzLcfqUMmz266T+mJX4vHClc/XP5z0TIW/A==";

    private String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlYsDdTv/8nNDMCaPC2hcJLdovujf+ciYw1b+M3auROu0FZsTl7ysTLdkpNqjher65r3wfBMviCW7Ysc/SFVphsZvw/HInC93TcRXjQZIg7ddy81ogo3JKQPrJ+HCCWvBJfJ1JwEm2j76GgTEP71AEF2Ub9nnMZjhHrMexeaMndgXIVqRTcZNUL85Y4sNfh9rYhYL30JEKegzHpu58VwqE0orBl1GjJTb+LA5hm7+QGv7sWZeG10YGGeJL5b95zJw0tuDBRaj6CwjUc99fnA4ab+kkk1Q9BMCclA1Ugp+UOkXaSJAAkM5OQc2W6Bn+bOxSwdT2s200Jknl3sv8sghAQIDAQAB";

    private String notifyUrl="http://localhost:8800/notify";

    private String returnUrl="http://localhost:8800/return";

    /**
     * @param id 订单id 必须纯数字
     * @param price 订单价格 纯数字 可以带两位小数 如12.34
     * @param title 订单标题
     * @return 返回页面的body页面 直接在body里面添加返回 渲染页面
     */
    public String pay(String id, String price, String title) {
        AlipayClient alipayClient = new DefaultAlipayClient(url, appid, privateKey, "json", "UTf-8", publicKey, "RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", id);
        bizContent.put("total_amount", price);
        bizContent.put("subject", title);
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = null;
        String form = null;
        try {
            response = alipayClient.pageExecute(request);
            form = response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return form;
    }

    public String query(String id) {
        AlipayClient alipayClient = new DefaultAlipayClient(url, appid, privateKey, "json", "UTf-8", publicKey, "RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", id);
        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = null;
        String body = null;
        try {
            response = alipayClient.execute(request);
            body = response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return body;
    }
}
