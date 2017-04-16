package cn.ucai.live.data.restapi;

import cn.ucai.live.I;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LPP on 2017/4/13.
 */

public interface LiveService {
    @GET("live/getAllGifts")
    Call<String> getAllGifts();
    @GET("findUserByUserName")
    Call<String> loadUserInfo(@Query(I.User.USER_NAME)String username);
    // http://101.251.196.90:8080/SuperWeChatServerV2.0/live/createChatRoom?auth=1IFgE&name=LPP&description=LPPZB&owner=LPP&maxusers=300&members=LPP
    @GET("live/createChatRoom")
    Call<String> createLiveRoom(@Query("auth") String auth,
                                @Query("name") String name,
                                @Query("description") String description,
                                @Query("owner") String owner,
                                @Query("maxusers") int maxusers,
                                @Query("members") String members);
}
