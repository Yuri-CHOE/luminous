package com.example.luminous;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
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
import java.util.List;

public class music_list extends AppCompatActivity {

    ListView song_listView;
    String[] items;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        song_listView = (ListView)findViewById(R.id.song_listView);
        List<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        song_listView.setAdapter(adapter);

        File fp = new File("C:\\Users\\user\\AndroidStudioProjects\\Luminous\\app\\src\\main\\res\\raw\\");
        if(fp.exists() == false){
            return;
        }
        File[] files = fp.listFiles();
        for(int i = 0; i < files.length; i++){
            if(!files[i].isHidden() && files[i].isFile()){
                data.add(files[i].getName());
            }
        }
        adapter.notifyDataSetChanged();
    }
}