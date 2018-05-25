package com.example.aaron.basics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private File galleryPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "gallery_photos");
    private String path = galleryPath.getAbsolutePath();
    private File[] paths = galleryPath.listFiles();
    private int numPicturesDisplayed = 0;
    private boolean change = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayPictures();
        //Toast.makeText(this, path, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                change = false;
                updatePictureList();
                displayPictures();
            }
        }
    }
    public void onClick(View view) {
        // edit image
    }
    public void openCamera(View view) {
        // open camera
        Intent intent = new Intent(this, OpenCamera.class);
        startActivityForResult(intent, 1);
    }
    private void updatePictureList() {
        paths = galleryPath.listFiles();
    }
    private void displayPictures() {
        LinearLayout layout = findViewById(R.id.layout);
        LinearLayout layout1 = findViewById(R.id.layout1);
        LinearLayout layout2 = findViewById(R.id.layout2);
        LinearLayout layout3 = findViewById(R.id.layout3);
        assert layout != null;
        if (!galleryPath.exists()) {
            galleryPath.mkdir();
            path = galleryPath.getPath();
            updatePictureList();
        }
        int numPictures = paths.length;
        if (numPictures == 0 && change) {
            TextView text = new TextView(this);
            text.setText("No pictures found!");
            text.setGravity(Gravity.CENTER);
            text.setTextSize(18);
            layout.addView(text);
        }
        if (numPictures == numPicturesDisplayed)
            return;
        for (int i=numPicturesDisplayed; i<numPictures; i++) {
            File img = new File(paths[i].getAbsolutePath());
            if (img.exists()) {
                Bitmap bit = null;
                while (bit == null) {
                    bit = BitmapFactory.decodeFile(paths[i].getAbsolutePath());
                }
                ImageView image = new ImageView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 300);
                image.setLayoutParams(params);
                image.setImageBitmap(bit);
                image.setOnClickListener(this);
                if (i % 3 == 0)
                    layout1.addView(image);
                else if (i % 3 == 1)
                    layout2.addView(image);
                else layout3.addView(image);
                numPicturesDisplayed++;
            }
        }
    }
    private void clearGallery() {
        int numPictures = paths.length;
        for (int i=0; i<numPictures; i++) {
            File toDelete = new File(paths[i].getPath());
            toDelete.delete();
        }
    }
}