package com.masc.teriteri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;



public class InputActivity  extends Activity {
    private EditText urlEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_url);

        urlEditText = findViewById(R.id.urlEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }
}
