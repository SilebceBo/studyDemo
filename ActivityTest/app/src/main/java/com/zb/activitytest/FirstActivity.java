package com.zb.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    //活动
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button button1 = (Button) findViewById(R.id.button_1);
        //点击显示一个Toast
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
            }
        });*/

        //销毁一个活动
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
        //显式intent启动活动
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });*/
        //隐式intent启动活动
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.zb.activitytest.ACTION_START");
                intent.addCategory("com.zb.activitytest.My_CATEGORY");
                startActivity(intent);
            }
        });*/
        //隐式intent启动浏览器
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });*/
        //隐式intent启动拨号盘
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });*/
        //向下一个活动传递数据
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "Hello SecondActivity";
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);
            }
        });*/
        //得到下个活动返回的数据的启动
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
               startActivityForResult(intent,1);
            }
        });*/

        //启动模式：standard默认
        /*Log.d("FirstActivity", this.toString());
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });*/

        //启动模式：singleTop
        /*Log.d("FirstActivity", this.toString());
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });*/

        //启动模式：singleInstance
        /*Log.d("FirstActivity", "Task id is" + getTaskId());
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });*/

        //使用设计的启动方法启动
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SecondActivity.actionStart(FirstActivity.this,"data1", "data2");
            }
        });
    }

    //启动模式：singleTask
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("FirstActivity", "onRestart");
    }

    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //得到下一个活动的返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("FirstActivity", String.valueOf(requestCode));
        switch (requestCode){
            case 1:
                String returnedData = data.getStringExtra("data_return");
                Log.d("FirstActivity", returnedData);
                break;
            default:
        }
    }
}
