package com.uestc.neil.backup.app.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.uestc.neil.backup.app.bean.SMS;

import java.util.List;

/**
 * Created by Neil on 2016/2/23.
 */
public class InsertSMSs {
    public static void insertSMSs(List<SMS> listSMSs, Context context) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        for (int i = 0; i < listSMSs.size(); i++) {
            SMS sms = listSMSs.get(i);
            ContentValues cv = new ContentValues();
//            cv.put("_id", sms.getId());
            cv.put("date", System.currentTimeMillis() - i * 10000);
            cv.put("body", sms.getBody());
            cv.put("address", sms.getAddress());
            cv.put("type", 1);
            cr.insert(uri, cv);

        }
    }
}
