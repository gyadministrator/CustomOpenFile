package com.android.custom.filelib.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.custom.filelib.R;
import com.android.custom.filelib.view.FileContentView;

public class FileActivity extends AppCompatActivity {
    RelativeLayout rlBack;
    TextView tvTitle;
    private FileContentView fileContentView;
    private String title;
    private String url;
    private static final String TAG = "FileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        Log.e(TAG, "url....." + url);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText(title);
        fileContentView.setUrl(url);
    }

    private void initView() {
        rlBack = findViewById(R.id.rl_back);
        tvTitle = findViewById(R.id.tv_title);
        fileContentView = findViewById(R.id.fileContentView);
    }

    public static void startActivity(Activity activity, String title, String url) {
        Intent intent = new Intent(activity, FileActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return fileContentView.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        fileContentView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        fileContentView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileContentView.onDestroy();
    }
}
