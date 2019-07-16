package com.example.administrator.myviewdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //导包的快捷键  alt+enter
    //权限 控件类型 控件的名称
    private ProgressBar mProgressBar;
    private Button mButton;
    private Handler mHandler = new Handler(){
        //在主线程中接受子线程中handler发送的消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //通过setProgress（）方法更新进度

            mProgressBar.setProgress(msg.arg1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //把activity_main.layout作为当前activity的布局页面
        setContentView(R.layout.activity_main);
        //通过findViewById方法绑定控件
        // R文件 存储项目中所有的资源文件，完全不需要我们手动更改
        //存储数据的方式都是以int类型保存
        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);
        mButton = findViewById(R.id.button);
        //实现监听第一步：先实现View.OnClickListener接口并重写里面的方法
        //第二步：给控件设置监听 this代表当前的activity
        mButton.setOnClickListener(this);
    }

    //在Ui主线程中不能进行任何复杂的操作。
    @Override
    public void onClick(View v) {
        //开启子线程,更新进度
        new Thread(new Runnable() {
            @Override
            public void run() {
                //定义一个int类型的值作为进度
                int progress = 0;
                while (progress<=100){
                    progress++;
                    //创建一个message消息
                    Message message = new Message();
                    //把进度值赋值给message
                    message.arg1 = progress;
                    //通过mHandler.sendMessage给主线程发送消息
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
