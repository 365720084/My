package com.fancy.showmedata.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fancy.showmedata.R;
import com.fancy.showmedata.base.BaseActivity;
import com.fancy.showmedata.network.SchedulerUtil;
import com.fancy.showmedata.router.ActivityPath;
import com.fancy.showmedata.utils.ToastUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LanuchActivity extends BaseActivity {
    Disposable disposable;
    int enviroment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanuch);
        //设置用户为没有自动进入过
        enviroment = 0;//0 测试 1正式 2开发
    }

    @Override
    protected void onResume() {
        super.onResume();
//        initViews();
//        changeNet();
            jump();
    }

    private void changeNet() {
        String[] sequences = {"测试", "正式", "开发"};
        boolean[] isChecked = {true, false, false};
        final AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setCancelable(false);
        ab.setTitle("切换环境");
        ab.setSingleChoiceItems(sequences, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enviroment = which;
            }

        });

        ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                if (enviroment == 0) {
//                    EnviromentManager.getInstance().setCurrentUrl(Enviroment.TEXT);
//                    EnviromentManager.UseEncode = true;
//                } else if (enviroment == 2) {
//                    EnviromentManager.getInstance().setCurrentUrl(Enviroment.DEVELOP);
//                    EnviromentManager.UseEncode = true;
//                } else if (enviroment == 1) {
//                    EnviromentManager.getInstance().setCurrentUrl(Enviroment.PRODUCT);
//                    EnviromentManager.UseEncode = true;
//                }
//                UserDataUtil.getInstance().clear();
                jump();
                dialog.dismiss();
//                togame();
            }
        });
        ab.create().show();
    }

    private void initViews() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .compose(SchedulerUtil.compose(this.bindToLifecycle()))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (aLong == 1) {
                            disposable.dispose();
//                            String gameUrl = "wss://itest.jw830.com/wss?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxOTgxNjM3NTcxMjAwOTgzMDQsImV4cCI6MTUyNDkxNDg0NX0.najQl3uc0OyJwytWDu7p0J-Mt1CyrCU7HUDMU5NLILE&room_id=2396";
//                            UserDataUtil.getInstance().setGameUrl(gameUrl);
//                            RouterUtils.routerGoWithOutParma(ActivityPath.COCOS);//todo
//                            EnviromentManager.getInstance().setCurrentUrl(Enviroment.DEVELOP);
//                            EnviromentManager.UseEncode = true;
                            jump();
                        }
                    }
                });
    }

    private void jump() {


        String url = "weixn://webssssview/csossspy";

        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        ToastUtils.showToast(this,isValid?"有效":"无效");

        if (isValid) {
            startActivity(intent);
        }
    }




}
