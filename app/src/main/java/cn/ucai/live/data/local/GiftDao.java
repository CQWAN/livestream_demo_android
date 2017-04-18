package cn.ucai.live.data.local;

import java.util.List;
import java.util.Map;

import cn.ucai.live.data.DBManager;
import cn.ucai.live.data.model.Gift;

/**
 * Created by clawpo on 2017/4/14.
 */

public class GiftDao {
    public static final String GIFT_TABLE_NAME = "t_superwechat_gift";
    public static final String GIFT_COLUMN_ID = "m_gift_id";
    public static final String GIFT_COLUMN_NAME = "m_gift_name";
    public static final String GIFT_COLUMN_URL = "m_gift_url";
    public static final String GIFT_COLUMN_PRICE = "m_gift_price";

    public GiftDao() {
    }

    /**
     * save gift list
     *
     * @param giftList
     */
    public void saveAppGiftList(List<Gift> giftList) {
        DBManager.getInstance().saveAppGiftList(giftList);
    }

    /**
     * get gift list
     *
     * @return
     */
    public Map<Integer, Gift> getAppGiftList() {

        return DBManager.getInstance().getAppGiftList();
    }
}
