package com.example.luminous;

import android.Manifest;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class music_list extends AppCompatActivity {

    ListView song_listView;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        song_listView = findViewById(R.id.song_listView);

        runtimePermission();

    }

    public void runtimePermission(){
        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                display();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                token.continuePermissionRequest();

            }
        }).check();
    }

    public ArrayList<File> findsong(File file){
        ArrayList<File> arrayList = new ArrayList<>();

        File[] files = file.listFiles();
        for(File singFile : files){
            if(singFile.isDirectory() && !singFile.isHidden()){
                arrayList.addAll(findsong((singFile)));
            }
            else{
                if(singFile.getName().endsWith(".mp3") || singFile.getName().endsWith(".wav")){
                    arrayList.add(singFile);
                }
            }
        }

        return  arrayList;
    }

    void display(){
        final ArrayList<File> mysong = findsong(Environment.getExternalStorageDirectory());
        items = new String[mysong.size()];

        for(int i = 0; i < mysong.size();i++){
            items[i] = mysong.get(i).getName().toString().replace(".mp3", "").replace(".wav","");
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
            song_listView.setAdapter(myAdapter);
        }
    }
}