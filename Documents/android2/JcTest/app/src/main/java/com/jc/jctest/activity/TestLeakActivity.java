package com.jc.jctest.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jc.jctest.R;
import com.jc.jctest.databinding.ActivityTestBinding;
import com.jc.jctest.entity.User;
import com.jc.jctest.inject.params.SellMoe;
import com.jc.jctest.model.TestSingle;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * author：jc   2019/5/6 10:51
 */
public class TestActivity extends BaseActivity {
 /*   @Inject
    OkHttpClient okHttpClient;*/

    @Inject
    SellMoe sellMoe;
    private static List<Object> objectList;

    private  static TestLeak testLeak;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityTestBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);

         /*DaggerOkHttpClientComponent.create().inject(this);
        DaggerSellMoeComponent.builder().sellMoeModule(new SellMoeModule(80)).build().inject(this);
        ToastUtil.showLongToast(this, sellMoe.sellMoe());*/
        mBinding.setUser(new User("2222", "3333"));

        //   TestSingle.getInstance(this);
        //  new MyAscnyTask().execute();
     //   new ListLeak().listLeak();

        if (null==testLeak){
            testLeak=new TestLeak();
        }

    }

    /**
     * Activity内部创建了一个非静态内部类的单例，
     * 每次启动Activity时都会使用该单例的数据。
     * 虽然这样避免了资源的重复创建，但是这种写法却会造成内存泄漏
     * 。因为非静态内部类默认会持有外部类的引用，而该非静态内部类又创建了一个静态的实例，
     * 该实例的生命周期和应用的一样长，这就导致了该静态实例一直会持有该Activity的引用，
     * 从而导致Activity的内存资源不能被正常回收。
     */
    class TestLeak{

    }

    /**
     *   集合类引发的内存泄漏
     */
    class ListLeak {

        private void listLeak() {
            objectList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Object obj = new Object();
                objectList.add(obj);
                obj = null;
            }
        }
    }


    /**
     * 非静态内部类 匿名内部类引发的内存泄漏
     */
    class MyAscnyTask extends AsyncTask<Void, Integer, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
