package cn.ucai.live.data.dao;

import java.util.List;

import cn.ucai.live.data.model.Gift;

/**
 * Created by LPP on 2017/4/14.
 */

public class GiftDao {

    private LiveDBManager dbManager;

    public GiftDao() {
        dbManager = new LiveDBManager();
    }

    public void saveGifts(List<Gift> giftList) {
        dbManager.saveGifts(giftList);
    }
    public List<Gift> getGifts() {
        return dbManager.getGifts();
    }
}
