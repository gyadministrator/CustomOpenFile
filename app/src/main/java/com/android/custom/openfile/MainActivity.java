package com.android.custom.openfile;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.android.custom.filelib.activity.FileActivity;
import com.android.custom.filelib.util.FileContentUtil;


public class MainActivity extends AppCompatActivity {
    private LinearLayout llContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        String s = "http://10.0.0.254:8077/file_temp/20201216/20201216144228-%E5%AE%89%E5%85%A8%E9%9A%90%E6%82%A3%E9%9C%80%E6%B1%82%E9%97%AE%E9%A2%98.docx," +
                "http://10.0.0.254:8077/file_temp/20201216/20201216144228-%E5%AE%89%E5%85%A8%E9%9A%90%E6%82%A3%E9%9C%80%E6%B1%82%E9%97%AE%E9%A2%98.docx," +
                "http://10.0.0.254:8077/file_temp/20201216/20201216144228-%E5%AE%89%E5%85%A8%E9%9A%90%E6%82%A3%E9%9C%80%E6%B1%82%E9%97%AE%E9%A2%98.docx," +
                "http://10.0.0.254:8077/file_temp/20201216/20201216144228-%E5%AE%89%E5%85%A8%E9%9A%90%E6%82%A3%E9%9C%80%E6%B1%82%E9%97%AE%E9%A2%98.pdf," +
                "http://10.0.0.254:8077/file_temp/20201216/20201216144228-%E5%AE%89%E5%85%A8%E9%9A%90%E6%82%A3%E9%9C%80%E6%B1%82%E9%97%AE%E9%A2%98.doc," +
                "http://10.0.0.254:8077/file_temp/20201216/20201216144228-%E5%AE%89%E5%85%A8%E9%9A%90%E6%82%A3%E9%9C%80%E6%B1%82%E9%97%AE%E9%A2%98.ppt," +
                "http://10.0.0.254:8077/file_temp/20201216/20201216144228-%E5%AE%89%E5%85%A8%E9%9A%90%E6%82%A3%E9%9C%80%E6%B1%82%E9%97%AE%E9%A2%98.pptx," +
                "http://10.0.0.254:8077/file_temp/20201216/20201216144228-%E5%AE%89%E5%85%A8%E9%9A%90%E6%82%A3%E9%9C%80%E6%B1%82%E9%97%AE%E9%A2%98.xls," +
                "http://10.0.0.254:8077/file_temp/20201216/20201216144228-%E5%AE%89%E5%85%A8%E9%9A%90%E6%82%A3%E9%9C%80%E6%B1%82%E9%97%AE%E9%A2%98.xlsx," +
                "http://10.0.0.254:8077/file_temp/20201216/测试.xlsx," +
                "http://10.0.0.254:8077/file_temp/20201216/测试.item_txt," +
                "http://10.0.0.254:8077/file_temp/20201216/测试.png,"+
                "http://10.0.0.254:8077/file_temp/20201216/测试.jpg,"+
                "http://10.0.0.254:8077/file_temp/20201216/测试.jpeg";
        FileContentUtil.getView(this, s, llContent, new FileContentUtil.OnFileItemClickListener() {
            @Override
            public void onItemClick(String s, View view) {
                FileActivity.startActivity(MainActivity.this,"文件详情",s);
            }
        });
    }

    private void initView() {
        llContent = findViewById(R.id.ll_content);
    }
}
