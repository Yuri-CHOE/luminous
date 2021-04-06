package com.example.luminous;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.util.ArrayList;

public class MyPage extends AppCompatActivity {

    private FirebaseAuth mAuth ;

    private TextView tv_nickName; //닉네임 테스트
    private ImageView iv_profile; //이미지 뷰
    private Button btn_logout;  //로그아웃 버튼
    private TextView tv_email; //이메일 텍스트

    //램프 컬러 변경
    private Button btn_lampcolor;
    private TextView tv_current_color_number;
    private TextView tv_current_color;

    //mp3
    Button btn_next, btn_prev, btn_pause, btn_choose_song;
    TextView songNameText;
    SeekBar songSeekBar;
    String song_name;

    static MediaPlayer myMediaPlayer;
    int posiotion;

    ArrayList<File> mySongs;
    Thread updateseekBar;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName"); //MainActivity로 부터 닉네임 전달 받음
        String photoUrl = intent.getStringExtra("photoUrl");    //MainActivity로 부터 프로필 사진 Url 전달 받음
        String emailTXT = intent.getStringExtra("emailTXT");    //MainActivity로 부터 이메일 전달 받음

        tv_nickName = findViewById(R.id.tv_nickName);
        tv_nickName.setText(nickName+"님");    //닉네임 text를 텍스트뷰테 세팅

        tv_email = findViewById(R.id.tv_email);
        tv_email.setText(emailTXT); //이메일 text를 텍스트뷰테 세팅

        iv_profile = findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile);    //프로필 url을 이미지 뷰에 세팅

        mAuth = FirebaseAuth.getInstance();
        //버튼 클릭 이벤트 세팅
        Button btnlogout = (Button) findViewById(R.id.btn_logout);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼의 id를 가져옴
                switch (v.getId())
                {
                    case R.id.btn_logout:   //로그아웃 버튼
                        FirebaseAuth.getInstance().signOut();
                        finishAffinity();
                        break;
                }

            }
        };
        btnlogout.setOnClickListener(listener);

        //switch on/off버튼
        Switch sw = (Switch) findViewById(R.id.switch_on_off);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Lamp on",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Lamp off",Toast.LENGTH_LONG).show();
                }
            }
        });

        //lamp color optional 코드
        btn_lampcolor = findViewById(R.id.btn_current_color);
        tv_current_color_number = findViewById(R.id.tv_current_color_number);
        tv_current_color = findViewById(R.id.tv_current_color);
        String set_color = "Select Color";
        String set_color_code = "#FFFFFF";

        btn_lampcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_2 = new Intent(MyPage.this, lamp_color_optional.class);
                startActivity(intent_2);  //엑티비티 이동
            }
        });
        Intent intent_color = getIntent();
        set_color = intent_color.getStringExtra("set_color");
        set_color_code = intent_color.getStringExtra("set_color_code");
        tv_current_color.setText(set_color);
        tv_current_color_number.setText(set_color_code);
        //btn_lampcolor.setBackgroundColor(Color.parseColor(set_color_code));

        //mp3

        btn_next = findViewById(R.id.btn_skip_next);
        btn_prev = findViewById(R.id.btn_skip_previous);
        btn_pause = findViewById(R.id.btn_pause);
        btn_choose_song = findViewById(R.id.btn_current_song);

        songNameText = findViewById(R.id.tv_current_music);
        songSeekBar = findViewById(R.id.seek_musicbar);

        btn_choose_song.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent_3 = new Intent(MyPage.this, music_list.class);
                startActivity(intent_3);  //엑티비티 이동
            }
        });

    }
}