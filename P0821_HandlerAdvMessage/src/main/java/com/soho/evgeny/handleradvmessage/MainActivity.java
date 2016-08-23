package com.soho.evgeny.handleradvmessage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    String LOG_TAG = "MyLog";

    // Состояния
    final int STATUS_NONE = 0; // нет подключения
    final int STATUS_CONNECTING = 1; // подключаемся
    final int STATUS_CONNECTED = 2; // подключено
    final int STATUS_DOWNLOAD_START = 3; // загрузка началась
    final int STATUS_DOWNLOAD_FILE = 4; // файл загружен
    final int STATUS_DOWNLOAD_END = 5; // загрузка закончена
    final int STATUS_DOWNLOAD_NONE = 6; // нет файлов для загрузки

    Handler myHandler;
    TextView tvStatus;
    ProgressBar pbDownload;
    Button btnConnect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView)findViewById(R.id.tvStatus);
        pbDownload = (ProgressBar)findViewById(R.id.pbDownload);
        btnConnect = (Button)findViewById(R.id.btnConnect);

        myHandler = new Handler() {
            public void handleMessage(android.os.Message msg){
                switch(msg.what){
                    case STATUS_NONE:
                        btnConnect.setEnabled(true);
                        pbDownload.setVisibility(View.GONE);
                        tvStatus.setText("Not connected");
                        break;
                    case STATUS_CONNECTING:
                        btnConnect.setEnabled(false);
                        tvStatus.setText("Connecting ...");
                        break;
                    case STATUS_CONNECTED:
                        tvStatus.setText("Connected");
                        break;
                    case STATUS_DOWNLOAD_START:
                        tvStatus.setText("Start download "+msg.arg1+" files");
                        pbDownload.setMax(msg.arg1);
                        pbDownload.setProgress(0);
                        pbDownload.setVisibility(View.VISIBLE);
                        break;
                    case STATUS_DOWNLOAD_FILE:
                        tvStatus.setText("Downloading left "+msg.arg2+" files");
                        pbDownload.setProgress(msg.arg1);
                        saveFile((byte[])msg.obj);
                        break;
                    case STATUS_DOWNLOAD_END:
                        tvStatus.setText("Download complete");
                        break;
                    case STATUS_DOWNLOAD_NONE:
                        tvStatus.setText("No files for download!");
                        break;
                    default:
                        break;

                }
            }
        };
        myHandler.sendEmptyMessage(STATUS_NONE);
    }

    void onclick(View view){

            final Thread myThread = new Thread(new Runnable() {

                byte[] data;
                Message msg;
                Random random = new Random();
                @Override
                public void run() {
                    try{
                        // Имитация начала подключения
                        myHandler.sendEmptyMessage(STATUS_CONNECTING);
                        TimeUnit.SECONDS.sleep(3);

                        // Имитация стостояния подключено
                        myHandler.sendEmptyMessage(STATUS_CONNECTED);

                        // Генерируем количество файлов
                        TimeUnit.SECONDS.sleep(3);
                        int filesCount = random.nextInt(10);

                        // Если файлов не оказалось переходим к заверению
                        if(filesCount == 0){
                            myHandler.sendEmptyMessage(STATUS_DOWNLOAD_NONE);
                            TimeUnit.SECONDS.sleep(1);
                            myHandler.sendEmptyMessage(STATUS_NONE);
                            return;
                        }

                        // Начинаем закачку файлов
                        msg = myHandler.obtainMessage(STATUS_DOWNLOAD_START,filesCount,0);
                        myHandler.sendMessage(msg);

                        for(int i = 1; i <= filesCount; i++){
                            data = downloadFile();
                            msg = myHandler.obtainMessage(STATUS_DOWNLOAD_FILE,i,filesCount-i,data);
                            myHandler.sendMessage(msg);
                        }

                        // Завершение закачки
                        myHandler.sendEmptyMessage(STATUS_DOWNLOAD_END);

                        TimeUnit.SECONDS.sleep(1);
                        myHandler.sendEmptyMessage(STATUS_NONE);

                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        myThread.start();

    }
    byte[] downloadFile() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return new byte[1024];
    }
    void saveFile(byte[] data){

    }
}
