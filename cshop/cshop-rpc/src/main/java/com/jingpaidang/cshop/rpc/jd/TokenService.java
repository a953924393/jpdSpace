package com.jingpaidang.cshop.rpc.jd;

import com.jingpaidang.cshop.common.utils.JSONUtils;
import com.jingpaidang.cshop.common.utils.StringUtil;
import com.jingpaidang.cshop.dao.user.AccountMapper;
import com.jingpaidang.cshop.domain.user.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/7/13
 * Time: 9:33 AM
 */
@Service
public class TokenService {

    @Value("${jd.url}")
    private String URL;
    @Value("${jd.redirect.uri}")
    private String REDIRECT_URI;
    @Value("${jd.app.key}")
    private String APP_KEY;
    @Value("${jd.app.secret}")
    private String APP_SECRET;
    @Value("${jd.authurl}")
    private String AUTHURL;
    //TODO 增加sku的方法

    @Resource
    AccountMapper accountMapper;

    public String findRefreshByAccess(String access) {
        Account byAccessToken = accountMapper.findByAccessToken(access);
        return byAccessToken.getPlatformRefreshToken();
    }

    /**
     * 用于定时刷新accessToken，一般 刷新频率一般1/24h
     *
     * @return 刷新后的access_token
     */
    public String refreshToken(String refreshToken) throws IOException {

        String html = AUTHURL + "?"
                + "client_id=" + APP_KEY
                + "&client_secret=" + APP_SECRET
                + "&grant_type=refresh_token" + "&refresh_token="
                + refreshToken;
        URL uri;
        uri = new URL(html);

        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

        conn.setRequestProperty("Accept-Charset", "utf-8");

        conn.setRequestMethod("POST");

        InputStream is = conn.getInputStream();

        String jsonStr = StringUtil.inputStream2String(is);
        Map jsonMap = JSONUtils.toObject(jsonStr, HashMap.class);
        String accessToken = (String) jsonMap.get("access_token");

        return accessToken;
    }


    /**
     * 根据授权得到的code，state 取得授权信息
     *
     * @param code
     * @param state
     * @return {  "access_token": "1ea34584-87bb-4d5a-a623-a14b6efc495e",  "code": 0,  "expires_in": 31103999,  "refresh_token": "d0bfc3e5-5469-476d-b155-78645e81edf5",  "scope": "null",  "time": "1372212206893",  "token_type": "bearer",  "uid": "1097741719", "user_nick": "jd_jingpaidang"}
     */
    public Map<String, Object> getAccessToken(String code, String state) throws IOException {
        //用得到的code获取accesstoken

        String html = AUTHURL + "?grant_type=authorization_code" +
                "&client_id=" + APP_KEY +
                "&redirect_uri=" + REDIRECT_URI +
                "&code=" + code +
                "&state=" + state +
                "&client_secret=" + APP_SECRET;
        URL uri;
        uri = new URL(html);

        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

        conn.setRequestProperty("Accept-Charset", "utf-8");

        conn.setRequestMethod("POST");

        InputStream is = conn.getInputStream();

        String jsonStr = StringUtil.inputStream2String(is);

        return JSONUtils.toObject(jsonStr, Map.class);
    }
}
