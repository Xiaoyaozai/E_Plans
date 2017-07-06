package com.mariomz.e_plan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mariomz.e_plan.R;
import com.mariomz.e_plan.bean.TomatoPlanBean;

import java.util.List;

/**
 * Created by MarioMz on 2017/6/14/ 0014.
 * Function：plan 的 listview的数据适配器
 * ContactInfo:QQ-980390613
 */

public class adapter_listview_plan extends BaseAdapter {


    private List<TomatoPlanBean> mListPlans;
    private Context mContext;

    public adapter_listview_plan() {

    }

    public adapter_listview_plan(List<TomatoPlanBean> tmp) {
        this.mListPlans = tmp;
    }



    @Override
    public int getCount() {
        return mListPlans.size();
    }

    @Override
    public Object getItem(int position) {
        return mListPlans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(mContext,R.layout.item_plan_lists,null);
            holder = new ViewHolder();
            holder.plan = (TextView) convertView.findViewById(R.id.tv_listview_plan_title);
            holder.tv_listview_plans_time = (TextView) convertView.findViewById(R.id.tv_listview_plans_time);

            //TODO 【是否完成？是否确定点击】
            ImageView iv_plan_done = (ImageView) convertView.findViewById(R.id.iv_plan_done);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        //得到数据 ===> 开始赋值
        holder.plan.setText(mListPlans.get(position).getPlan_content());
        holder.tv_listview_plans_time.setText(mListPlans.get(position).getPlan_time());


        return convertView;
    }



    /**
     *  listview的item内部类 ===> 与原item数据属性相同
     */
    class ViewHolder {

        //用户已定义的番茄计划
        public TextView plan;
        //用户已定义的番茄计划的时间
        public TextView tv_listview_plans_time;
        //用户可以自定义添加图片
        //public ImageView iv_listview_news_icon;
    }

}






