package com.huaan.animationdemo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huaan.animationdemo.R;
import com.huaan.animationdemo.javabean.CatalogBean;

import java.util.List;

/**
 * @author renlei
 * 首页目录adapter
 */
public class CatalogAdapter extends BaseQuickAdapter<CatalogBean,BaseViewHolder> {

    public CatalogAdapter(@Nullable List<CatalogBean> data) {
        super(R.layout.adapter_item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CatalogBean item) {
        helper.setText(R.id.title_tv,item.getTitle());
    }
}
