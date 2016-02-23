package com.uestc.neil.backup.app.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Intent;
import android.provider.Telephony;
import com.uestc.neil.backup.app.R;
import com.uestc.neil.backup.app.bean.SMS;
import com.uestc.neil.backup.app.utils.DecodingXMLfileByPull;
import com.uestc.neil.backup.app.utils.InsertSMSs;
import org.xmlpull.v1.XmlPullParserException;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ModuleDecodingXMLFile extends Activity {
    private ArrayList<SMS> list;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decodingxmlfile);
        tv = (TextView) findViewById(R.id.id_tvDecodingResult);
    }

    public void decodingXML(View view) throws XmlPullParserException {

        File file = new File(Environment.getExternalStorageDirectory(),
                "back2.xml");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);

            DecodingXMLfileByPull decoder = null;
            decoder = new DecodingXMLfileByPull(fis, this);
            fis.close();
            list = decoder.getSmsList();
            StringBuffer sb = new StringBuffer();
            for (SMS sms : list) {
                sb.append(sms.toString() + "\n");
                System.out.println(sms);
            }

            tv.setText(sb);
            Toast.makeText(this, "解析成功", Toast.LENGTH_SHORT).show();

//            String defaultSmsApp = Telephony.Sms.getDefaultSmsPackage(this);
//            Intent intent =
//                    new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
//                    getPackageName());
//            startActivity(intent);

            //TODO 短信恢复
            InsertSMSs.insertSMSs(list, this);
            System.out.println("!!!!!!!!!!!!!!!");
//            Intent intent2 =
//                    new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
//                    defaultSmsApp);
//            startActivity(intent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
