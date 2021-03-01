package com.example.luminous;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class lamp_color_optional extends AppCompatActivity {

    //램프컬러 변경 확인
    private Button btn_color_section_close;
    private Button btn_romantic;

    private String current_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_color_optional);

        btn_romantic = findViewById(R.id.btn_romantic);
        btn_romantic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_color = "Romantic";
            }
        });

        btn_color_section_close = findViewById(R.id.btn_color_section_close);
        btn_color_section_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lamp_color_optional.this, MyPage.class);
                intent.putExtra("currentColor", current_color);
                startActivity(intent);
            }
        });
    }
}