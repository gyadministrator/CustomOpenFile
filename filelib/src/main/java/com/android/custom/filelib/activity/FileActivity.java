package com.android.custom.filelib.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.custom.filelib.R;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;

public class FileActivity extends AppCompatActivity {
    RelativeLayout rlBack;
    TextView tvTitle;
    LinearLayout llContent;
    private String title;
    private String url;
    private AgentWeb agentWeb;
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
        openUrl();
    }

    private void initView() {
        rlBack = findViewById(R.id.rl_back);
        tvTitle = findViewById(R.id.tv_title);
        llContent = findViewById(R.id.ll_content);
    }

    private void openUrl() {
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(llContent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);

        FrameLayout frameLayout = agentWeb.getWebCreator().getWebParentLayout();
        frameLayout.setBackgroundColor(Color.WHITE);
    }

    public static void startActivity(Activity activity, String title, String url) {
        Intent intent = new Intent(activity, FileActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (agentWeb != null) {
            if (agentWeb.handleKeyEvent(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        if (agentWeb != null) {
            agentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (agentWeb != null) {
            agentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AgentWebConfig.clearDiskCache(this);
    }
}
