package cn.ucai.live.data.dao;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import cn.ucai.live.data.model.Gift;

/**
 * Created by LPP on 2017/4/14.
 */

public class LiveDBManager {

    SQLiteDatabase database;

    public LiveDBManager() {
        database = Connector.getDatabase();
    }

    public void saveGifts(List<Gift> giftList) {
        DataSupport.saveAll(giftList);
    }

    public List<Gift> getGifts() {
        return DataSupport.findAll(Gift.class);
    }
    public void closeDB() {
        database.close();
    }
}
