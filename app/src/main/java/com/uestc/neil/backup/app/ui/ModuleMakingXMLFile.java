package com.uestc.neil.backup.app.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.uestc.neil.backup.app.R;
import com.uestc.neil.backup.app.bean.SMS;
import com.uestc.neil.backup.app.utils.GetSMSs;
import org.xmlpull.v1.XmlSerializer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

public class ModuleMakingXMLFile extends Activity {
    private ArrayList<SMS> smss = new ArrayList<SMS>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_makingxmlfile);
        // 加载短信
//		for (int i = 0; i < 10; i++) {
//			smss.add(new SMS("abcdefg" + i, i, System.currentTimeMillis() + i
//					* 100000 + i * i, "1234567897" + i));
//		}
        GetSMSs.getSms(smss, this);
    }

    public void back1(View view) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<SMSS>");
        for (int i = 0; i < smss.size(); i++) {
            sb.append("<SMS ");

            sb.append("id=\"");
            sb.append(smss.get(i).getId());
            sb.append("\"");

            sb.append(">");

            sb.append("<body>");
            sb.append(smss.get(i).getBody());
            sb.append("</body>");

            sb.append("<address>");
            sb.append(smss.get(i).getAddress());
            sb.append("</address>");

            sb.append("<date>");
            sb.append(smss.get(i).getDate());
            sb.append("</date>");

            sb.append("</SMS>");
        }
        sb.append("</SMSS>");

        File file = new File(Environment.getExternalStorageDirectory(),
                "back1.xml");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(new String(sb).getBytes());
            fos.close();
            Toast.makeText(this, "方式1备份成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "方式1备份失败！", Toast.LENGTH_SHORT).show();
        }
    }

    public void back2(View view) {
        XmlSerializer serializer = Xml.newSerializer();
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    "back2.xml");
            FileOutputStream os = new FileOutputStream(file);
            // 初始化序列化，指定序列化器得到的xml文件写入路径及编码
            serializer.setOutput(os, "utf-8");
            serializer.startDocument("utf-8", true);
            // 开始节点
            serializer.startTag(null, "SMSS");
            for (SMS sms : smss) {
                serializer.startTag(null, "sms");

                serializer.attribute(null, "id", sms.getId() + "");

                serializer.startTag(null, "body");
                serializer.text(sms.getBody());
                serializer.endTag(null, "body");

                serializer.startTag(null, "address");
                serializer.text(sms.getAddress() + "");
                serializer.endTag(null, "address");

                serializer.startTag(null, "date");
                serializer.text(sms.getDate() + "");
                serializer.endTag(null, "date");

                serializer.endTag(null, "sms");
            }
            serializer.endTag(null, "SMSS");

            serializer.endDocument();
            os.close();
            Toast.makeText(this, "方式2备份成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "方式2备份失败！", Toast.LENGTH_SHORT).show();
        }
    }
}
