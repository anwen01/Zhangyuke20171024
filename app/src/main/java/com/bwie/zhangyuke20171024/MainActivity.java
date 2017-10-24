package com.bwie.zhangyuke20171024;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwie.zhangyuke20171024.utils.GsonObjectCallback;
import com.bwie.zhangyuke20171024.utils.OkHttp3Utils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.AcFunFooter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    private RecyclerView rv;
    private int page=1;
    private SpringView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        sv = (SpringView) findViewById(R.id.sv);
        sv.setType(SpringView.Type.FOLLOW);
        sv.setHeader(new AcFunFooter(this,R.mipmap.ic_launcher));
        sv.setFooter(new DefaultFooter(this));


        getinfo();

        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                        page++;
                        getinfo();
                    }
                },2000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                        page++;
                        getinfo();
                    }
                },2000);
            }
        });

    }
    public void getinfo(){
        getData();
    }

    public void getData(){
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request=new Request.Builder()
//                .url("http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&page="+page)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String string = response.body().string();
//                System.out.println("============="+string);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Gson gson = new Gson();
//                        Bean bean = gson.fromJson(string, Bean.class);
//                        List<Bean.DataBean> data = bean.getData();
//                        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
//                        rv.setAdapter(new MyAdapter(MainActivity.this,data));
//
//                    }
//                });
//            }
//        });
        OkHttp3Utils.getInstance().doGet("http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&page="+page, new GsonObjectCallback<Bean>() {
            @Override
            public void onUi(Bean bean) {
                List<Bean.DataBean> data = bean.getData();
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                rv.setAdapter(new MyAdapter(MainActivity.this,data));
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }



}
