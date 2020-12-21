package com.android.custom.filelib.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.custom.filelib.R;
import com.android.custom.filelib.activity.FileActivity;

/**
 * @ProjectName: CustomOpenFile
 * @Package: com.android.custom.filelib.util
 * @ClassName: FileContentUtil
 * @Author: 1984629668@qq.com
 * @CreateDate: 2020/12/21 14:34
 */
public class FileContentUtil {
    public static void getView(final Activity activity, String file, LinearLayout content) {
        if (TextUtils.isEmpty(file) || content == null) return;
        content.setOrientation(LinearLayout.VERTICAL);
        if (file.contains(",")) {
            String[] split = file.split(",");
            for (final String s : split) {
                if (!TextUtils.isEmpty(s)) {
                    @SuppressLint("InflateParams") View view = LayoutInflater.from(activity).inflate(R.layout.activity_file_item, null);
                    ImageView ivIcon = view.findViewById(R.id.iv_icon);
                    TextView tvTitle = view.findViewById(R.id.tv_title);
                    String type = s.substring(s.lastIndexOf(".") + 1);
                    String s1 = s.contains("/") ? s.substring(s.lastIndexOf("/") + 1) : s;
                    tvTitle.setText(s1);
                    switch (type) {
                        case "item_doc":
                        case "docx":
                            ivIcon.setImageResource(R.mipmap.item_doc);
                            break;
                        case "item_ppt":
                        case "pptx":
                            ivIcon.setImageResource(R.mipmap.item_ppt);
                            break;
                        case "item_xls":
                        case "xlsx":
                            ivIcon.setImageResource(R.mipmap.item_xls);
                            break;
                        case "item_txt":
                            ivIcon.setImageResource(R.mipmap.item_txt);
                            break;
                        case "item_pdf":
                            ivIcon.setImageResource(R.mipmap.item_pdf);
                            break;
                        case "png":
                        case "jpg":
                        case "jpeg":
                            ivIcon.setImageResource(R.mipmap.item_pic);
                            break;
                    }
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FileActivity.startActivity(activity, "文件详情", s);
                        }
                    });

                    if (view.getParent() != null) {
                        ViewGroup viewGroup = (ViewGroup) view.getParent();
                        viewGroup.removeView(view);
                    }
                    content.addView(view);
                }
            }
        }
    }
}
