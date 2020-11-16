package com.takashi.edittextinlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button ;
    boolean isInputScore;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isInputScore = true;

        adapter = new MainAdapter(this);
        adapter.setInputScore(isInputScore);
        final ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        button = findViewById(R.id.button);
        showButtonText();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isInputScore = !isInputScore;
                showButtonText();
                adapter.setInputScore(isInputScore);

                View views = getCurrentFocus();
                if (views != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                //フォーカスを親のレイアウトに移す.
                findViewById(R.id.parent_layout).requestFocus();
            }
        });
    }

    private void showButtonText(){
        button.setText(isInputScore ?"点数入力に変更":"スコア入力に変更");
    }
}