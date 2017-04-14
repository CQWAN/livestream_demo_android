package cn.ucai.live.data.restapi;

import java.util.List;

import cn.ucai.live.I;
import cn.ucai.live.data.model.Gift;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LPP on 2017/4/13.
 */

public interface LiveService {
    @GET("live/getAllGifts")
    Call<List<Gift>> getAllGifts();
    @GET("findUserByUserName")
    Call<String> loadUserInfo(@Query(I.User.USER_NAME)String username);
}
