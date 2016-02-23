package com.uestc.neil.backup.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.uestc.neil.backup.app.bean.SMS;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


import android.content.Context;
import android.util.Xml;
import android.widget.Toast;

public class DecodingXMLfileByPull {
	private ArrayList<SMS> smsList = new ArrayList<SMS>();
	private Context context;

	public DecodingXMLfileByPull(InputStream is, Context context)
			throws XmlPullParserException, IOException {
		this.context = context;

		// 初始化解析器
		XmlPullParser pullParser = Xml.newPullParser();
		int type = 0;
		try {
			pullParser.setInput(is, "utf-8");
			type = pullParser.getEventType();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			Toast.makeText(context, "解析错误！", Toast.LENGTH_SHORT).show();
			return;
		}
		decoding(type, pullParser);
	}

	private void decoding(int type, XmlPullParser pullParser)
			throws XmlPullParserException, IOException {

		int i = 0;
		SMS sms = null;
		while (type != XmlPullParser.END_DOCUMENT) {
			i++;
			switch (type) {
			case XmlPullParser.START_TAG:
				String tagName = pullParser.getName();

				if ("SMSS".equals(tagName)) {
					// 解析全局标签
				}
				// 一条短信的开头
				else if ("sms".equals(tagName)) {
					sms = new SMS();
					// 取得id
					String idStr = pullParser.getAttributeValue(0);
					sms.setId(Long.parseLong(idStr));
				} else if ("body".equals(tagName)) {
					// 取得body
					String body = null;
					body = pullParser.nextText();
					sms.setBody(body);
					System.out.println(i + " body:" + body + " g"
							+ pullParser.getText());

				} else if ("address".equals(tagName)) {
					// 取得address
					String address = null;
					address = pullParser.nextText();
					sms.setAddress(address);
					System.out.println(i + " address:" + address + " g"
							+ pullParser.getText());

				} else if ("date".equals(tagName)) {
					// 取得date
					String date = null;
					date = pullParser.nextText();
					sms.setDate(Long.parseLong(date));
					System.out.println(i + " date:" + date + " g"
							+ pullParser.getText());
				}
				break;

			case XmlPullParser.END_TAG:
				String endtagName = pullParser.getName();
				if ("sms".equals(endtagName)) {
					// 单条信息获取完毕
					smsList.add(sms);
					System.out.println(sms);
					sms = null;
				}
				break;
			}
			type = pullParser.next();
		}
	}

	public ArrayList<SMS> getSmsList() {
		return smsList;
	}

}
