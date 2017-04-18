package cn.ucai.live.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.ucai.live.LiveApplication;
import cn.ucai.live.utils.L;

/**
 * Created by clawpo on 2017/4/14.
 */

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "DbOpenHelper";

    private static final int DATABASE_VERSION = 1;
    private static DbOpenHelper instance;


    private static final String GIFT_TABLE_CREATE = "CREATE TABLE "
            + GiftDao.GIFT_TABLE_NAME + " ("
            + GiftDao.GIFT_COLUMN_NAME + " TEXT, "
            + GiftDao.GIFT_COLUMN_URL + " TEXT, "
            + GiftDao.GIFT_COLUMN_PRICE + " INTEGER, "
            + GiftDao.GIFT_COLUMN_ID + " INTEGER PRIMARY KEY);";

    private DbOpenHelper(Context context) {
        super(context, getUserDatabaseName(), null, DATABASE_VERSION);
        L.e(TAG,"DbOpenHelper....");
    }

    public static DbOpenHelper getInstance(Context context) {
        L.e(TAG,"getInstance....");
        if (instance == null) {
            L.e(TAG,"getInstance....new DbOpenHelper");
            instance = new DbOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    private static String getUserDatabaseName() {
        L.e(TAG,"getUserDatabaseName....");
        return LiveApplication.getInstance().getPackageName() + "_demo.db";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        L.e(TAG,"onCreate....db.execSQL(GIFT_TABLE_CREATE)");
        db.execSQL(GIFT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void closeDB() {
        if (instance != null) {
            try {
                SQLiteDatabase db = instance.getWritableDatabase();
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }

    /**
     *
     *
     E/Utils: jsonStr={"retCode":0,"retMsg":true,"retData":[{"id":1,"gname":"红包","gurl":"http://101.251.196.90:8080/SuperWeChatServerV2.0/gift/hani_gift_1.png","gprice":1},{"id":2,"gname":"棒棒糖","gurl":"http://101.251.196.90:8080/SuperWeChatServerV2.0/gift/hani_gift_2.png","gprice":10},{"id":3,"gname":"香蕉","gurl":"http://101.251.196.90:8080/SuperWeChatServerV2.0/gift/hani_gift_3.png","gprice":19},{"id":4,"gname":"鲜花","gurl":"http://101.251.196.90:8080/SuperWeChatServerV2.0/gift/hani_gift_4.png","gprice":99},{"id":5,"gname":"去污皂","gurl":"http://101.251.196.90:8080/SuperWeChatServerV2.0/gift/hani_gift_5.png","gprice":199},{"id":6,"gname":"巧克力","gurl":"http://101.251.196.90:8080/SuperWeChatServerV2.0/gift/hani_gift_6.png","gprice":999},{"id":7,"gname":"百威","gurl":"http://101.251.196.90:8080/SuperWeChatServerV2.0/gift/hani_gift_7.png","gprice":1000},{"id":8,"gname":"药丸","gurl":"http://101.251.196.90:8080/SuperWeChatServerV2.0/gift/hani_gift_8.png","gprice":9999}]}
     E/Utils: jsonRetData={"muserName":"xxx456","muserNick":"大魔王","mavatarId":2151,"mavatarPath":"user_avatar","mavatarSuffix":null,"mavatarType":0,"mavatarLastUpdateTime":"1491987233841"}
     E/Utils: jsonRetData={"muserName":"xxx456","muserNick":"大魔王","mavatarId":2151,"mavatarPath":"user_avatar","mavatarSuffix":null,"mavatarType":0,"mavatarLastUpdateTime":"1491987233841"}
     E/UserProfileManager: getCurrentAppUserInfo,currentAppUser=User{muserName='xxx456', muserNick='大魔王', mavatarId=2151, mavatarPath='user_avatar', mavatarSuffix='null', mavatarType=0, mavatarLastUpdateTime='1491987233841', initialLetter='null', avatar='null'}
     E/UserProfileManager: setCurrentAppUserAvatar,avatar=http://101.251.196.90:8080/SuperWeChatServerV2.0/downloadAvatar?name_or_hxid=xxx456&avatarType=user_avatar&m_avatar_suffix=.jpg&updatetime=1491987233841
     E/UserProfileManager: getCurrentAppUserInfo,currentAppUser=User{muserName='xxx456', muserNick='大魔王', mavatarId=2151, mavatarPath='user_avatar', mavatarSuffix='null', mavatarType=0, mavatarLastUpdateTime='1491987233841', initialLetter='null', avatar='null'}
     E/DBManager: DBManager....
     E/DbOpenHelper: getInstance....
     E/DbOpenHelper: getInstance....new DbOpenHelper
     E/DbOpenHelper: getUserDatabaseName....
     E/DbOpenHelper: DbOpenHelper....
     E/DBManager: DBManager....getInstance....
     E/DBManager: saveAppGiftList....
     E/DbOpenHelper: onCreate....db.execSQL(GIFT_TABLE_CREATE)
     E/ApiManager: handleResponseCall,responseCall=retrofit2.ExecutorCallAdapterFactory$ExecutorCallbackCall@7bd7799
     E/RecyclerView: No adapter attached; skipping layout
     E/ApiManager: handleResponseCall,response=Response{protocol=http/1.1, code=200, message=OK, url=http://a1.easemob.com/i/superwechat201612/liverooms?ongoing=true&limit=20}
     E/DBManager: DBManager....getInstance....
     E/DBManager: getAppGiftList....
     *
     *
     *
     */
}