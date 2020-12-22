package com.android.custom.filelib.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;

/**
 * @ProjectName: CustomOpenFile
 * @Package: com.android.custom.filelib.view
 * @ClassName: FileContentView
 * @Author: 1984629668@qq.com
 * @CreateDate: 2020/12/22 8:38
 */
public class FileContentView extends LinearLayout {
    private String url;
    private AgentWeb agentWeb;
    private Context context;

    public void setUrl(String url) {
        this.url = url;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        agentWeb = AgentWeb.with((Activity) context)
                .setAgentWebParent(this, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);

        FrameLayout frameLayout = agentWeb.getWebCreator().getWebParentLayout();
        frameLayout.setBackgroundColor(Color.WHITE);
    }

    public FileContentView(Context context) {
        this(context, null);
    }

    public FileContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FileContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setOrientation(VERTICAL);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (agentWeb != null) {
            if (agentWeb.handleKeyEvent(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onPause() {
        if (agentWeb != null) {
            agentWeb.getWebLifeCycle().onPause();
        }
    }

    public void onResume() {
        if (agentWeb != null) {
            agentWeb.getWebLifeCycle().onResume();
        }
    }

    public void onDestroy() {
        AgentWebConfig.clearDiskCache(context);
    }
}
