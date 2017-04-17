package cn.ucai.live.data.local;

/**
 * Created by clawpo on 2017/4/14.
 */

//public class DbOpenHelper extends SQLiteOpenHelper {
//    private static final String TAG = "DbOpenHelper";
//
//    private static final int DATABASE_VERSION = 1;
//    private static DbOpenHelper instance;
//
//
//    private static final String GIFT_TABLE_CREATE = "CREATE TABLE "
//            + GiftDao.GIFT_TABLE_NAME + " ("
//            + GiftDao.GIFT_COLUMN_NAME + " TEXT, "
//            + GiftDao.GIFT_COLUMN_URL + " TEXT, "
//            + GiftDao.GIFT_COLUMN_PRICE + " INTEGER, "
//            + GiftDao.GIFT_COLUMN_ID + " INTEGER PRIMARY KEY);";
//
//    private DbOpenHelper(Context context) {
//        super(context, getUserDatabaseName(), null, DATABASE_VERSION);
//        L.e(TAG,"DbOpenHelper....");
//    }
//
//    public static DbOpenHelper getInstance(Context context) {
//        L.e(TAG,"getInstance....");
//        if (instance == null) {
//            L.e(TAG,"getInstance....new DbOpenHelper");
//            instance = new DbOpenHelper(context.getApplicationContext());
//        }
//        return instance;
//    }
//
//    private static String getUserDatabaseName() {
//        L.e(TAG,"getUserDatabaseName....");
//        return LiveApplication.getInstance().getPackageName() + "_demo.db";
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        L.e(TAG,"onCreate....db.execSQL(GIFT_TABLE_CREATE)");
//        db.execSQL(GIFT_TABLE_CREATE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//    }
//
//    public void closeDB() {
//        if (instance != null) {
//            try {
//                SQLiteDatabase db = instance.getWritableDatabase();
//                db.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            instance = null;
//        }
//    }
//}