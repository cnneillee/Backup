package com.uestc.neil.backup.app.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.uestc.neil.backup.app.bean.SMS;

import java.util.List;


public class GetSMSs {
    /**
     * 使用contentresolver来获得短信内容
     *
     * @param list    返回SMS的集合
     * @param context
     */
    public static void getSms(List<SMS> list, Context context) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        Cursor cursor = cr.query(uri, new String[]{"body", "_id", "address",
                "date"}, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            SMS sms = new SMS(body, Long.parseLong(id), Long.parseLong(date),
                    address);
            list.add(sms);
        }
        cursor.close();
    }
}
