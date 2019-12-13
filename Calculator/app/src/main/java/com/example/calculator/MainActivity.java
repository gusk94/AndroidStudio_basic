package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Edit1, Edit2;
    Button BtnAdd, BtnMinus, BtnMul, BtnDivide;
    String str1, str2;
    Integer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Edit1 = findViewById(R.id.Edit1);
        Edit2 = findViewById(R.id.Edit2);
        BtnAdd = findViewById(R.id.BtnAdd);
        BtnMinus = findViewById(R.id.BtnMinus);
        BtnMul = findViewById(R.id.BtnMul);
        BtnDivide = findViewById(R.id.BtnDivide);
        final TextView textView = (TextView) findViewById(R.id.Result) ;

        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = Edit1.getText().toString();
                str2 = Edit2.getText().toString();
                result = Integer.parseInt(str1) + Integer.parseInt(str2);
                textView.setText("Result: "+result);
//                Toast.makeText(MainActivity.this, "결과 값: "+result, Toast.LENGTH_SHORT).show();
            }
        });

        BtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = Edit1.getText().toString();
                str2 = Edit2.getText().toString();
                result = Integer.parseInt(str1) - Integer.parseInt(str2);
                textView.setText("Result: "+result);
//                Toast.makeText(MainActivity.this, "결과 값: "+result, Toast.LENGTH_SHORT).show();
            }
        });

        BtnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = Edit1.getText().toString();
                str2 = Edit2.getText().toString();
                result = Integer.parseInt(str1) * Integer.parseInt(str2);
                textView.setText("Result: "+result);
            }
        });

        BtnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = Edit1.getText().toString();
                str2 = Edit2.getText().toString();
                double result2 = Double.parseDouble(str1+".0") / Integer.parseInt(str2);
                textView.setText("Result: "+result2);
            }
        });
    }
}
