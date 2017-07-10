package com.example.nhdangdh.loadimagedemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DownloadTask.CallBack {
    EditText edtLink;
    Button btnLoad;
    ImageView ivImage;
    MainActivity mainActivity;
    DownloadTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        setContentView(R.layout.activity_main);

        addControls();

        addEvents();

        task = new DownloadTask();
    }

    private void addEvents() {
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.registerCallBack(mainActivity);
                task.execute(edtLink.getText().toString());
            }
        });
    }

    private void addControls() {
        edtLink = (EditText) findViewById(R.id.edtLink);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        ivImage = (ImageView) findViewById(R.id.ivImage);
    }

    @Override
    public void displayImage(Bitmap bitmap) {
        ivImage.setImageBitmap(bitmap);
    }
}