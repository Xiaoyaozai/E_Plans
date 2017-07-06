package com.mariomz.e_plan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mariomz.e_plan.R;
import com.mariomz.e_plan.bean.TomatoPlanBean;
import com.mariomz.e_plan.utils.CacheUtils;
import com.mariomz.e_plan.utils.LogUtil;
import com.mariomz.e_plan.utils.ToastUtils;
import com.mariomz.e_plan.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarioMz on 2017/6/10/
 * Function：番茄钟导向 1
 * ContactInfo:QQ-980390613
 */
public class TomatoGuide1Activity extends AppCompatActivity implements View.OnClickListener {

    public static final int NOTIFYDATA = 1;
    public static final String BEAN = "serializable_bean";
    /**
     * 添加点的布局
     */
    private LinearLayout llGrid;
    private ListView lvPlanList;
    private List<TomatoPlanBean> mListPlans;
    private Button btNext;
    private TextView tv_add_plans;
    /**
     * 用户输入的番茄计划
     */
    private String mPlan = "";
    /**
     * 当前系统时间
     */
    private String systemCurrentTime;
    /**
     * listview的数据适配器
     */
    private PlansListViewAdapter adapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case NOTIFYDATA:

                //刷新数据适配器
                adapter.notifyDataSetChanged();

                break;

        }
    }
    };

    private void initUI() {

        setContentView(R.layout.activity_tomato_guide1);
        llGrid = (LinearLayout)findViewById( R.id.ll_grid );
        lvPlanList = (ListView)findViewById( R.id.lv_plan_list );
        btNext = (Button)findViewById( R.id.bt_next );
        tv_add_plans = (TextView)findViewById(R.id.tv_add_plans);
        //点击函数
        btNext.setOnClickListener( this );
        tv_add_plans.setOnClickListener( this );
        //用户计划数据集合
        mListPlans = new ArrayList<TomatoPlanBean>();
        //listview设置数据适配器
        adapter = new PlansListViewAdapter();
        lvPlanList.setAdapter(adapter);

        //listview的点击适配器
        /*lvPlanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //进入番茄钟的详情
                Intent intent = new Intent();
            }
        });
*/
    }

    @Override
    public void onClick(View v) {
        if ( v == btNext ) {
            next2Guide2();
        } else if(v == tv_add_plans) {
            addPlans();
        }

    }

    /**2017-6-10
     * 用户点击增加【计划】
     *弹出对话框
     */
    private void addPlans() {

        //新建并显示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        final View view_dialog_plan = View.inflate(this, R.layout.dialog_plan, null);
        dialog.setView(view_dialog_plan);
        dialog.setView(view_dialog_plan, 0, 0, 0, 0);// 设置内边距为0，为了兼容低版本，在设置布局时去掉系统默认的内边距
        dialog.show();

        final EditText etPlan = (EditText)view_dialog_plan.findViewById( R.id.et_plan );
        Button btYes = (Button)view_dialog_plan.findViewById( R.id.bt_yes );
        Button btNo = (Button)view_dialog_plan.findViewById( R.id.bt_no );

        //任务输入框
        etPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的计划
                mPlan = etPlan.getText().toString().trim();

            }
        });


        //确定按钮 ===> 只有用户点击了确定以后才可以保存
        btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //缓存用户计划
                CacheUtils.putString(TomatoGuide1Activity.this,"PLAN",mPlan);

                LogUtil.e("++++++++++"+"TomatoGuide1Activity.class"+"  ======>  btYes.setOnClickListener"+"+++++++++++++");

                //获取用户输入的计划
                mPlan = etPlan.getText().toString().trim();

                if (mPlan != null && !TextUtils.isEmpty(mPlan)) {
                    TomatoPlanBean bean = new TomatoPlanBean();
                    //计划
                    bean.setPlan_content(mPlan);
                    //计划时间
                    systemCurrentTime = Utils.getSystemTime();
                    bean.setPlan_time(systemCurrentTime);
                    //保存进list
                    mListPlans.add(bean);



                    mHandler.sendEmptyMessage(NOTIFYDATA);

                    //隐藏对话框
                    dialog.dismiss();


                    //关闭取消当前页面
//                    finish();

                } else {
                    //用户没有正确输入===>提示输入
                    ToastUtils.show(TomatoGuide1Activity.this,"请输入您的番茄计划...");

                }
            }
        });


        //取消按钮
        btNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击取消，直接关闭当前【添加页面】，不进行任何数据的存储与处理
//                finish();
                //隐藏对话框
                dialog.dismiss();

            }
        });



    }

    /**
     * 跳转到下一个页面
     */
    private void next2Guide2() {
        Intent intent = new Intent(TomatoGuide1Activity.this,TomatoGuide2Activity.class);
        //不要用匿名bundle
        Bundle bundle = new Bundle();

        if (mListPlans != null && mListPlans.size() > 0) {
            bundle.putSerializable(BEAN,(Serializable)mListPlans);
            intent.putExtras(bundle);
        }

        if (lvPlanList != null && adapter != null && adapter.getCount() > 1) {
            //如果添加的番茄计划 大于 【2】则进入下一个界面
            startActivity(intent);
            //关闭当前页面
            finish();
        } else {
            ToastUtils.show(getApplication(),"请您至少添加两个番茄计划...");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    /**2017-6-10
     * listview的数据适配器
     */
    class PlansListViewAdapter extends BaseAdapter {

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
                convertView = View.inflate(TomatoGuide1Activity.this,R.layout.item_plan_lists,null);
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
