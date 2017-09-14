package com.cn.lasion.savepicdemo;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.lasion.savepicdemo.util.AppUtil;
import com.cn.lasion.savepicdemo.util.SavePictureUtil;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.dialog.core.DialogBuilder;

public class MainActivity extends AppCompatActivity {
    Bitmap mBitmap;
    ImageView pic;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUtil.getInstance().verifyStoragePermissions(MainActivity.this);
        mBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.loading);
        list.add("保存图片");
        list.add("删除图片");

        pic = (ImageView) findViewById(R.id.pic);
        pic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogBuilder.listDialog(MainActivity.this).setChoiceItems(list)
                        .setChoiceType(DialogBuilder.TYPE_CHOICE_SINGLE)
                        .setOnChoiceListener(new DialogBuilder.OnChoiceListener() {
                            @Override
                            public void onChoiceItem(int index, Object item) {
//                                // 对话框关闭后回调的一个方法，返回选择的条目
//                                Toast.makeText(MainActivity.this, "最终选择了：" + item, Toast.LENGTH_LONG).show();
                            }
                        }).setOnChoiceClickListener(new DialogInterface.OnClickListener() {
                    // 选择某一个条目的时候回调的一个方法，返回选择的是哪一个条目
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "点击了第" + (which + 1) + "个条目",
//                                Toast.LENGTH_LONG).show();
                    }
                }).setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_LONG).show();
                        SavePictureUtil.getInstance().savePic(MainActivity.this, mBitmap
                                , SavePictureUtil.getInstance().getFilePath("/Zhx/chat"),
                                SavePictureUtil.getInstance().getFileNameByTime());
                    }
                }).show();
                return true;
            }
        });
    }

    public void savePic(View v) {
        SavePictureUtil.getInstance().savePic(this, mBitmap
                , SavePictureUtil.getInstance().getFilePath("/Zhx/chat"),
                SavePictureUtil.getInstance().getFileNameByTime());
    }
}
