package com.example.luminous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.SignInButton;

public class MyPage extends AppCompatActivity {

    private TextView tv_nickName; //닉네임 테스트
    private ImageView iv_profile; //이미지 뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName"); //MainActivity로 부터 닉네임 전달 받음
        String photoUrl = intent.getStringExtra("photoUrl");    //MainActivity로 부터 프로필 사진 Url 전달 받음

        tv_nickName = findViewById(R.id.tv_nickName);
        tv_nickName.setText(nickName);    //닉네임 text를 텍스트뷰테 세팅

        iv_profile = findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile);    //프로필 url을 이미지 뷰에 세팅
    }




}