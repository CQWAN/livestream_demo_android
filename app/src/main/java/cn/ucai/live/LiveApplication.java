package cn.ucai.live;

import com.ucloud.ulive.UStreamingContext;

import org.litepal.LitePalApplication;

/**
 * Created by wei on 2016/5/27.
 */
public class LiveApplication extends LitePalApplication {

    private static LiveApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


        LiveHelper.getInstance().init(this);

        //UEasyStreaming.initStreaming("publish3-key");

        UStreamingContext.init(getApplicationContext(), "publish3-key");
    }

    public static LiveApplication getInstance() {
        return instance;
    }
}
