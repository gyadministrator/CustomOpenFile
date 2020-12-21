package com.android.custom.filelib.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.Locale;

/**
 * @ProjectName: CustomOpenFile
 * @Package: com.android.custom.openfile.util
 * @ClassName: OpenFileUtil
 * @Author: 1984629668@qq.com
 * @CreateDate: 2020/12/21 10:00
 */
public class OpenFileUtil {
    private static final String TAG = "OpenFileUtil";

    public static Intent openFile(File file) {
        String filePath = file.getPath();
        if (!file.exists())
            return null;
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase(Locale.getDefault());
        /* 依扩展名的类型决定MimeType */
        switch (end) {
            case "m4a":
            case "mp3":
            case "mid":
            case "xmf":
            case "ogg":
            case "wav":
                return getAudioFileIntent(filePath);
            case "3gp":
            case "mp4":
                return getVideoFileIntent(filePath);
            case "jpg":
            case "gif":
            case "png":
            case "jpeg":
            case "bmp":
                return getImageFileIntent(filePath);
            case "apk":
                return getApkFileIntent(filePath);
            case "ppt":
            case "pptx":
                return getPptFileIntent(filePath);
            case "xls":
            case "xlsx":
                return getExcelFileIntent(filePath);
            case "doc":
            case "docx":
                return getWordFileIntent(filePath);
            case "pdf":
                return getPdfFileIntent(filePath);
            case "chm":
                return getChmFileIntent(filePath);
            case "txt":
                return getTextFileIntent(filePath, false);
            default:
                return getAllIntent(filePath);
        }
    }

    public static Intent openFile(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file == null || !file.exists()) {
            downloadFile(filePath);
            return null;
        }
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase(Locale.getDefault());
        /* 依扩展名的类型决定MimeType */
        switch (end) {
            case "m4a":
            case "mp3":
            case "mid":
            case "xmf":
            case "ogg":
            case "wav":
                return getAudioFileIntent(filePath);
            case "3gp":
            case "mp4":
                return getVideoFileIntent(filePath);
            case "jpg":
            case "gif":
            case "png":
            case "jpeg":
            case "bmp":
                return getImageFileIntent(filePath);
            case "apk":
                return getApkFileIntent(filePath);
            case "ppt":
            case "pptx":
                return getPptFileIntent(filePath);
            case "xls":
            case "xlsx":
                return getExcelFileIntent(filePath);
            case "doc":
            case "docx":
                return getWordFileIntent(filePath);
            case "pdf":
                return getPdfFileIntent(filePath);
            case "chm":
                return getChmFileIntent(filePath);
            case "txt":
                return getTextFileIntent(filePath, false);
            default:
                return getAllIntent(filePath);
        }
    }

    private static void downloadFile(String filePath) {
        OkHttpUtils.get()
                .url(filePath)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "test.docx") {
                    @Override
                    public void inProgress(float progress) {
                        Log.e(TAG, "inProgress :" + progress);
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file) {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                        openFile(file.getAbsolutePath());
                    }
                });
    }

    // Android获取一个用于打开APK文件的intent
    public static Intent getAllIntent(String param) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    // Android获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent(String param) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    // Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    // Android获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    // Android获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(String param) {

        Uri uri = Uri.parse(param).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    // Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    // Android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    // Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    // Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    // Android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    // Android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(String param, boolean paramBoolean) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            Uri uri1 = Uri.parse(param);
            intent.setDataAndType(uri1, "text/plain");
        } else {
            Uri uri2 = Uri.fromFile(new File(param));
            intent.setDataAndType(uri2, "text/plain");
        }
        return intent;
    }

    // Android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }
}
