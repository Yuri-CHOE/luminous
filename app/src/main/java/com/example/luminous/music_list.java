package com.example.luminous;

import android.Manifest;
import android.content.Intent;
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
import android.widget.AdapterView;
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

    public ArrayList<File> findSong (File file){
        ArrayList<File> arrayList = new ArrayList<>();

        File[] files = file.listFiles();

        for(File singleFile: files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findSong((singleFile)));
            }
            else{
                if(singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {
                    arrayList.add(singleFile);
                }
            }
        }

        return  arrayList;
    }

    void display(){
        final ArrayList<File> mySong = findSong(Environment.getExternalStorageDirectory());
        items = new String[mySong.size()];

        for(int i = 0; i < mySong.size();i++){
            items[i] = mySong.get(i).getName().toString().replace(".mp3", "").replace(".wav","");
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
            song_listView.setAdapter(myAdapter);

            song_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                    String songName = song_listView.getItemAtPosition(i).toString();

                    startActivity(new Intent(getApplicationContext(), MyPage.class).putExtra("song", mySong).putExtra("songName", songName).putExtra("pos",i));
                }
            });
        }
    }
}