package cn.ucai.live.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.utils.EaseUserUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.ucai.live.I;
import cn.ucai.live.LiveHelper;
import cn.ucai.live.R;
import cn.ucai.live.data.model.Gift;

/**
 * Created by wei on 2016/7/25.
 */
public class GiftListDialog extends DialogFragment {
    private static final String TAG = "RoomUserDetailsDialog";
    Unbinder unbinder;
    GridLayoutManager layoutManager;
    RecyclerView mrvGift;
    List<Gift> giftList;
    RecyclerView.Adapter giftAdapter;
    TextView mtvMyBill;
    TextView mtvRecharge;
    public static GiftListDialog newInstance() {
        GiftListDialog dialog = new GiftListDialog();
        return dialog;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_gift_list, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initView(layout);
        layoutManager = new GridLayoutManager(getContext(), I.GIFT_COLUMN_COUNT);
        mrvGift.setLayoutManager(layoutManager);
        customDialog();
        return layout;
    }
    private void customDialog() {
        getDialog().setCanceledOnTouchOutside(true);
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
    }

    private void initView(View layout) {
        mrvGift = (RecyclerView) layout.findViewById(R.id.rv_gift);
        mtvMyBill = (TextView) layout.findViewById(R.id.tv_my_bill);
        mtvRecharge = (TextView) layout.findViewById(R.id.tv_recharge);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        giftList = LiveHelper.getInstance().getGiftList();
        giftAdapter = new GiftAdapter(getContext(), giftList);
        mrvGift.setAdapter(giftAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class GiftAdapter extends RecyclerView.Adapter<GiftViewHolder> {
        Context context;
        List<Gift> giftList;
        public GiftAdapter(Context context, List<Gift> giftList) {
            this.context = context;
            this.giftList = giftList;
        }
        @Override
        public GiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            GiftViewHolder giftViewHolder = new GiftViewHolder(View.inflate(getContext(), R.layout.item_gift, null));
            return giftViewHolder;
        }

        @Override
        public void onBindViewHolder(GiftViewHolder holder, int position) {
            Gift gift = giftList.get(position);
            EaseUserUtils.setAvatar(getContext(),gift.getGurl(),holder.ivGiftThumb);
            holder.tvGiftPrice.setText(String.valueOf(gift.getGprice()));
            holder.tvGiftName.setText(gift.getGname());
        }

        @Override
        public int getItemCount() {
            return giftList==null?giftList.size():0;
        }
    }

    class GiftViewHolder extends RecyclerView.ViewHolder{
        ImageView ivGiftThumb;
        TextView tvGiftName;
        TextView tvGiftPrice;
        public GiftViewHolder(View itemView) {
            super(itemView);
            ivGiftThumb = (ImageView) itemView.findViewById(R.id.ivGiftThumb);
            tvGiftName = (TextView) itemView.findViewById(R.id.tvGiftName);
            tvGiftPrice = (TextView) itemView.findViewById(R.id.tvGiftPrice);
        }
    }
}
