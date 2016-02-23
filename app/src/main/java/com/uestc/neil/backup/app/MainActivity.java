package com.uestc.neil.backup.app;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import com.uestc.neil.backup.app.ui.ModuleDecodingXMLFile;
import com.uestc.neil.backup.app.ui.ModuleMakingXMLFile;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //���ݶ���
    public void backupMsgs(View v) {
        startMakingXMLFile();
    }

    //��ԭ����
    public void recoverMsgs(View v) {
        startDecodingXMLFile();
    }

    /**
     * �����������Ž���
     */
    private void startDecodingXMLFile() {
        Intent intent = new Intent(MainActivity.this,
                ModuleDecodingXMLFile.class);
        startActivity(intent);
    }

    /**
     * �������ݶ��Ž���
     */
    private void startMakingXMLFile() {
        Intent intent = new Intent(MainActivity.this,
                ModuleMakingXMLFile.class);
        startActivity(intent);
    }

}
