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

    //备份短信
    public void backupMsgs(View v) {
        startMakingXMLFile();
    }

    //还原短信
    public void recoverMsgs(View v) {
        startDecodingXMLFile();
    }

    /**
     * 启动解析短信界面
     */
    private void startDecodingXMLFile() {
        Intent intent = new Intent(MainActivity.this,
                ModuleDecodingXMLFile.class);
        startActivity(intent);
    }

    /**
     * 启动备份短信界面
     */
    private void startMakingXMLFile() {
        Intent intent = new Intent(MainActivity.this,
                ModuleMakingXMLFile.class);
        startActivity(intent);
    }

}
