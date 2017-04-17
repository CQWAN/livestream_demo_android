package cn.ucai.live.data;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ucai.live.data.model.Gift;

/**
 * Created by clawpo on 2017/4/14.
 */

public class DBManager {
//    private static final String TAG = "DBManager";
//    static private DBManager dbMgr = new DBManager();
//    private DbOpenHelper dbHelper;
    SQLiteDatabase database;

    public DBManager(){
//        L.e(TAG,"DBManager....");
//        dbHelper = DbOpenHelper.getInstance(LiveApplication.getInstance());
        database = Connector.getDatabase();
    }

//    public static synchronized DBManager getInstance(){
//        L.e(TAG,"DBManager....getInstance....");
//        if(dbMgr == null){
//            L.e(TAG,"DBManager....getInstance....new DBManager()");
//            dbMgr = new DBManager();
//        }
//        return dbMgr;
//    }

    /**
     * save gift list
     * @param giftList
     */
    synchronized public void saveAppGiftList(List<Gift> giftList) {
//        L.e(TAG,"saveAppGiftList....");
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        if (db.isOpen()) {
//            db.delete(GiftDao.GIFT_TABLE_NAME, null, null);
//            for (Gift gift:giftList) {
//                ContentValues values = new ContentValues();
//                if(gift.getId() != null)
//                    values.put(GiftDao.GIFT_COLUMN_ID, gift.getId());
//                if(gift.getGname() != null)
//                    values.put(GiftDao.GIFT_COLUMN_NAME, gift.getGname());
//                if(gift.getGurl() != null)
//                    values.put(GiftDao.GIFT_COLUMN_URL, gift.getGurl());
//                if(gift.getGprice() != null)
//                    values.put(GiftDao.GIFT_COLUMN_PRICE, gift.getGprice());
//                db.replace(GiftDao.GIFT_TABLE_NAME, null, values);
//            }
//        }
        DataSupport.saveAll(giftList);
    }

    /**
     * get gift list
     *
     * @return
     */
    synchronized public Map<Integer, Gift> getAppGiftList() {
//        L.e(TAG,"getAppGiftList....");
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Map<Integer, Gift> gifts = new Hashtable<Integer, Gift>();
//        if (db.isOpen()) {
//            Cursor cursor = db.rawQuery("select * from " + GiftDao.GIFT_TABLE_NAME /* + " desc" */, null);
//            while (cursor.moveToNext()) {
//                Gift gift = new Gift();
//                gift.setId(cursor.getInt(cursor.getColumnIndex(GiftDao.GIFT_COLUMN_ID)));
//                gift.setGprice(cursor.getInt(cursor.getColumnIndex(GiftDao.GIFT_COLUMN_PRICE)));
//                gift.setGname(cursor.getString(cursor.getColumnIndex(GiftDao.GIFT_COLUMN_NAME)));
//                gift.setGurl(cursor.getString(cursor.getColumnIndex(GiftDao.GIFT_COLUMN_URL)));
//                gifts.put(gift.getId(),gift);
//            }
//            cursor.close();
//        }
//        return gifts;
        List<Gift> giftList = DataSupport.findAll(Gift.class);
        Map<Integer, Gift> giftMap = new HashMap<>();
        for (Gift gift : giftList) {
            giftMap.put(gift.getId(), gift);
        }
        return giftMap;

    }

    synchronized public void closeDB(){
        if(database != null){
            database.close();
        }
//        dbMgr = null;
    }
}
