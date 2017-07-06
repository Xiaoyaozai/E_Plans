package com.mariomz.e_plan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mariomz.e_plan.R;
import com.mariomz.e_plan.bean.TomatoPlanBean;
import com.mariomz.e_plan.utils.ToastUtils;

import java.util.List;

import static com.mariomz.e_plan.R.id.iv_plan_choose;

/**
 * Created by MarioMz on 2017/6/10/ 0010.
 * Function：番茄钟 【导向2】
 * ContactInfo:QQ-980390613
 */

public class TomatoGuide2Activity extends AppCompatActivity implements View.OnClickListener {

    /**
     * item被点击===>寻求置顶
     */
    private static final int PLAN_CHOOSED = 0;

    private boolean isTop;
    private LinearLayout llGrid;
    private ListView lv_PlanlistToImportant;
    private myAdapter adapter_lv_PlanlistToImportant;
    private Button btNext;
    private ImageView mIv_plan_choosed;
    //listview中需要置顶的item的position
    private int mTop_position;
    /**
     * 第一个界面传递过来的展示数据
     */
    private List<TomatoPlanBean> mSerializableLists;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case PLAN_CHOOSED:
                    //选择选定的item===>


                    //1、更改图片 ==> 已选择
//                    if () {
                        mIv_plan_choosed.setImageResource(R.drawable.choose);

//                    }


                    //TODO 【2017-6-16】【2、listview中置顶item】
//                    lv_PlanlistToImportant.setSelection(0);
//                    Object selectedItem = lv_PlanlistToImportant.getSelectedItem();
//                    lv_PlanlistToImportant.addHeaderView((View) selectedItem);
//                    lv_PlanlistToImportant.removeViewAt(mTop_position);

                    //刷新数据适配器
                    adapter_lv_PlanlistToImportant.notifyDataSetChanged();
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();

        //获取传递的数据
        mSerializableLists = (List<TomatoPlanBean>)getIntent().getSerializableExtra(TomatoGuide1Activity.BEAN);
        //传递过来的数据有效
        if (mSerializableLists != null && mSerializableLists.size() > 0) {

            //有传递数据以后才进行listview的操作
            adapter_lv_PlanlistToImportant = new myAdapter();
            lv_PlanlistToImportant.setAdapter(adapter_lv_PlanlistToImportant);
            //TODO 为item置顶做准备
            lv_PlanlistToImportant.setSelected(true);
            //设置item的点击
            lv_PlanlistToImportant.setOnItemClickListener(new myOnItemClickListener());

        }
    }


    /**
     * listview中的Item点击监听
     */
    class myOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //获取点击的item的信息并传递到下一个计时界面显示



        }
    }



    /**
     * 初始化控件
     */
    private void initUI() {

        setContentView(R.layout.activity_tomato_guide2);
        llGrid = (LinearLayout)findViewById( R.id.ll_grid );
        lv_PlanlistToImportant = (ListView) findViewById( R.id.lv_planlist_to_important );
        btNext = (Button)findViewById( R.id.bt_next );

        btNext.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        if ( v == btNext ) {
            // Handle clicks for btNext
            gotoNext();
        }
    }

    /**
     * 下一步进入倒计时页面
     */
    private void gotoNext() {
        ToastUtils.show(this,"下一步...");
    }




    /**2017-6-10
     * listview的数据适配器
     */
    class myAdapter extends BaseAdapter {

        private List<TomatoPlanBean> datas;


        @Override
        public int getCount() {
            return mSerializableLists.size();
        }

        @Override
        public Object getItem(int position) {
            return mSerializableLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = View.inflate(TomatoGuide2Activity.this,R.layout.item_plan_important_lists,null);
                holder = new ViewHolder();
                holder.plan = (TextView) convertView.findViewById(R.id.tv_listview_plan_title);
                holder.tv_listview_plans_time = (TextView) convertView.findViewById(R.id.tv_listview_plans_time);

                //TODO 【是否优先】
                mIv_plan_choosed = (ImageView) convertView.findViewById(iv_plan_choose);
                //为【是否优先】设计点击事件
                mIv_plan_choosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTop_position = position;
                        mHandler.sendEmptyMessage(PLAN_CHOOSED);
                    }
                });

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();

            }

            //得到数据 ===> 开始赋值
            holder.plan.setText(mSerializableLists.get(position).getPlan_content());
            holder.tv_listview_plans_time.setText(mSerializableLists.get(position).getPlan_time());




            return convertView;
        }


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
