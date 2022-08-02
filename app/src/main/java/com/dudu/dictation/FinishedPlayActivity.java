package com.dudu.dictation;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;

import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import java.util.List;
import java.io.File;
import android.content.Intent;


public class FinishedPlayActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishedplay);
        Button backbt = (Button)findViewById(R.id.backbt);
        Button answer = (Button)findViewById(R.id.answer);
        TextView text = (TextView)findViewById(R.id.text);
        Intent getData = getIntent();
        String dataFileName = getData.getStringExtra("dataFileName");

        List<String> wordsList= FileUtils.readFile2List(Environment.getExternalStorageDirectory() + "/dictation"+File.separator+dataFileName,"UTF-8");
        text.setText("本次听写"+wordsList.size()+"个单词");
        SharedPreferences getSPData = getSharedPreferences("settings",MODE_PRIVATE);
        boolean autoDel = getSPData.getBoolean("autoDel",false);
        if(autoDel){FileUtils.deleteFile(Environment.getExternalStorageDirectory() + "/dictation"+File.separator+dataFileName);}
        backbt.setOnClickListener(v -> finish());
        answer.setOnClickListener(v ->{
            Intent intent=new Intent(FinishedPlayActivity.this,AnswerActivity.class);
            intent.putExtra("dataFileName",dataFileName);
            startActivity(intent);
            finish();
        });
    }
    
}
