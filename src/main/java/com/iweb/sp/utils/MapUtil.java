package com.iweb.sp.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lenovo
 * @date 2022/8/15 18:28
 */
public class MapUtil {
    /**
     * 根据用户地址返回坐标
     *
     * @param address 地址
     * @return 地址坐标
     */
    public static String getLocation(String address) {
        try {
            String result = getHttp("https://restapi.amap.com/v3/geocode/geo?address=" + address + "&key=5177091011d46df3319a8dc88ae7471d", "utf-8");
            return result;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 返回地址坐标
     *
     * @param url     高德接口路径
     * @param charset 字符编码
     * @return 坐标
     */
    private static String getHttp(String url, String charset) throws HTTPException, IOException, HttpException {
        String json;
        HttpGet httpGet = new HttpGet();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            httpGet.setURI(new URI(url));
        } catch (URISyntaxException e) {
            throw new HttpException("请求url格式错误。" + e.getMessage());
        }
// 发送请求
        HttpResponse httpResponse = client.execute(httpGet);
// 获取返回的数据
        HttpEntity entity = httpResponse.getEntity();
        byte[] body = EntityUtils.toByteArray(entity);
        StatusLine sL = httpResponse.getStatusLine();
        int statusCode = sL.getStatusCode();
        if (statusCode == 200) {
            json = new String(body, charset);
            entity.consumeContent();
        } else {
            throw new HttpException("statusCode=" + statusCode);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        String geocodes = jsonObject.getString("geocodes");
        String geocedesJson = geocodes.substring(1, geocodes.length() - 1);
        JSONObject jsonObject2 = JSONObject.parseObject(geocedesJson);
        String location = jsonObject2.getString("location");
        return location;
    }

    /**
     * 根据坐标计算 距离和时间
     *
     * @param origin      起始坐标
     * @param destination 终点坐标
     * @return distance 距离 单位米 duration 骑行预计时间 单位秒
     */
    public static ConcurrentHashMap<String, String> getDistanceAndDuration(String origin, String destination) {
        try {
            ConcurrentHashMap<String, String> hashMap = getAssist("https://restapi.amap.com/v4/direction/bicycling?origin=" + origin + "&destination=" + destination + "&key=5177091011d46df3319a8dc88ae7471d", "utf-8");

            return hashMap;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 根据坐标计算 距离和时间 辅助类
     *
     * @param url     高德接口路径
     * @param charset 字符集
     * @return distance 距离 单位米 duration 骑行预计时间 单位秒
     */
    private static ConcurrentHashMap<String, String> getAssist(String url, String charset) throws HTTPException, IOException, HttpException {
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap();
        String json;
        HttpGet httpGet = new HttpGet();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            httpGet.setURI(new URI(url));
        } catch (URISyntaxException e) {
            throw new HttpException("请求url格式错误。" + e.getMessage());
        }
// 发送请求
        HttpResponse httpResponse = client.execute(httpGet);
// 获取返回的数据
        HttpEntity entity = httpResponse.getEntity();
        byte[] body = EntityUtils.toByteArray(entity);
        StatusLine sL = httpResponse.getStatusLine();
        int statusCode = sL.getStatusCode();
        if (statusCode == 200) {
            json = new String(body, charset);
            entity.consumeContent();
        } else {
            throw new HttpException("statusCode=" + statusCode);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray paths = data.getJSONArray("paths");
        JSONObject paths0 = (JSONObject) paths.get(0);
        String distance = paths0.getString("distance");
        String duration = paths0.getString("duration");
        hashMap.put("distance", distance);
        hashMap.put("duration", duration);
        return hashMap;
    }


}
