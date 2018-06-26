package blueearth.opendgwdemo;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;

/**
 * 文件打开的工具类
 * OpenFileUtil</br>
 * Author: xialifan </br>
 * Date: 2018/6/25</br>
 * Title:
 */

public class OpenFileUtil {

    private static HashMap<String, String> applicationTypeMap = new HashMap<String, String>();

    static {
        applicationTypeMap.put("pdf", "application/pdf");
        applicationTypeMap.put("doc", "application/msword");
        applicationTypeMap.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        applicationTypeMap.put("xls", "application/vnd.ms-excel");
        applicationTypeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        applicationTypeMap.put("ppt", "application/vnd.ms-powerpoint");
        applicationTypeMap.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        applicationTypeMap.put("dwg", "application/dwg");
        applicationTypeMap.put("apk", "application/vnd.android.package-archive");
    }


    /**
     * 打开文件
     * @param context Activity上下文
     * @param file 文件
     * @param isNeedAllOpen 是否打开文件时，需要选择全部应用
     */
    public static void openFile(Context context, File file, boolean isNeedAllOpen ){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);


        Log.i("xlftest","file.getName():"+file.getName());
        String type = file.getName().split("[.]")[1]; //截取文件尾缀类型

        String actionType = applicationTypeMap.get(type);
        if (actionType == null){
            actionType = "*/*" ;
        }

        if (isNeedAllOpen){
            actionType = "*/*" ;
        }

        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(uri, actionType);

        try{
            context.startActivity(intent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(context,"未发现能够打开该类型文件的应用",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.i("error","",e);

        }
    }


}
