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

/**
 * @ProjectName: CustomOpenFile
 * @Package: com.android.custom.filelib.util
 * @ClassName: FileContentUtil
 * @Author: 1984629668@qq.com
 * @CreateDate: 2020/12/21 14:34
 */
public class FileContentUtil {
    public static void getView(final Activity activity, String file, LinearLayout content, final OnFileItemClickListener fileItemClickListener) {
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
                        case "doc":
                        case "docx":
                            ivIcon.setImageResource(R.mipmap.item_doc);
                            break;
                        case "ppt":
                        case "pptx":
                            ivIcon.setImageResource(R.mipmap.item_ppt);
                            break;
                        case "xls":
                        case "xlsx":
                            ivIcon.setImageResource(R.mipmap.item_xls);
                            break;
                        case "txt":
                            ivIcon.setImageResource(R.mipmap.item_txt);
                            break;
                        case "pdf":
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
                            if (fileItemClickListener != null) {
                                fileItemClickListener.onItemClick(s, v);
                            }
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

    public interface OnFileItemClickListener {
        void onItemClick(String s, View view);
    }
}
