package com.example.luminous;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class lamp_color_optional extends AppCompatActivity {

    //램프컬러 변경 확인
    private ImageView select_color;

    private TextView color_name;
    private TextView color_name_code;
    private Button btn_back;
    private Button btn_set;

    private Button btn_romantic;
    private Button btn_Good;
    private Button btn_Lively;
    private Button btn_Gloomy;
    private Button btn_Refreshing;
    private Button btn_Lovely;
    private Button btn_Tiring;
    private Button btn_Calm;
    private Button btn_Passionate;
    private Button btn_Cool;

    //private Button btn_color_section_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_color_optional);


        // 색상 선택
        select_color = findViewById(R.id.lamp_click_color);
        color_name = findViewById(R.id.color_name);
        color_name_code = findViewById(R.id.tv_current_color_code);

        btn_romantic = findViewById(R.id.btn_romantic);
        btn_romantic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#F8D6D4"));
                color_name.setText("Romantic_day");
                color_name_code.setText("#F8D6D4");
            }
        });

        btn_Good = findViewById(R.id.btn_Good);
        btn_Good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#FC887B"));
                color_name.setText("Good_day");
                color_name_code.setText("#FC887B");
            }
        });

        btn_Lively =findViewById(R.id.btn_Lively);
        btn_Lively.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#9DE2ED"));
                color_name.setText("Lively_day");
                color_name_code.setText("#9DE2ED");
            }
        });

        btn_Gloomy = findViewById(R.id.btn_Gloomy);
        btn_Gloomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#58094F"));
                color_name.setText("Gloomy_day");
                color_name_code.setText("#58094F");
            }
        });

        btn_Refreshing = findViewById(R.id.btn_Refreshing);
        btn_Refreshing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#FFF488"));
                color_name.setText("Refreshing_day");
                color_name_code.setText("#FFF488");
            }
        });

        btn_Lovely = findViewById(R.id.btn_Lovely);
        btn_Lovely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#FE87AC"));
                color_name.setText("Lovely_day");
                color_name_code.setText("#FE87AC");
            }
        });

        btn_Tiring = findViewById(R.id.btn_Tiring);
        btn_Tiring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#5E6072"));
                color_name.setText("Tiring_day");
                color_name_code.setText("#5E6072");
            }
        });

        btn_Calm =findViewById(R.id.btn_Calm);
        btn_Calm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#6A847A"));
                color_name.setText("Calm_day");
                color_name_code.setText("#6A847A");
            }
        });

        btn_Passionate = findViewById(R.id.btn_Passionate);
        btn_Passionate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#D31D0D"));
                color_name.setText("Passionate_day");
                color_name_code.setText("#D31D0D");
            }
        });

        btn_Cool = findViewById(R.id.btn_Cool);
        btn_Cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_color.setColorFilter(Color.parseColor("#00487A"));
                color_name.setText("Cool_day");
                color_name_code.setText("#00487A");
            }
        });

        btn_back = findViewById(R.id.btn_color_section_close);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_set = findViewById(R.id.btn_color_set);
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_color = new Intent(lamp_color_optional.this, MyPage.class);
                intent_color.putExtra("set_color", color_name.getText());
                intent_color.putExtra("set_color_code", color_name_code.getText());
                startActivity(intent_color);
            }
        });
    }

}