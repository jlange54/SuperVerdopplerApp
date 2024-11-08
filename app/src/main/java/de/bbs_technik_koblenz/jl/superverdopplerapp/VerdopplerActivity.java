package de.bbs_technik_koblenz.jl.superverdopplerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerdopplerActivity extends AppCompatActivity {

    private TextView display_numberToDouble;
    private Button btn_execDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verdoppler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        display_numberToDouble = findViewById(R.id.display_zuVerdopplendeZahl);
        btn_execDouble = findViewById(R.id.btn_execDouble);

        display_numberToDouble.setText("Du hast " +getNumberToDouble() + " eingeben... Cool!");

        btn_execDouble.setOnClickListener(view -> startMainActivity());

    }

    private int getNumberToDouble(){
        Intent verdopplerActivity = getIntent();
        return verdopplerActivity.getIntExtra("numberToDouble", 0);
    }

    private void startMainActivity () {
        int doubledNumber = getNumberToDouble()*2;
        Intent mainActivity = new Intent(VerdopplerActivity.this, MainActivity.class);
        mainActivity.putExtra("doubledNumber", doubledNumber);
        startActivity(mainActivity);
    }

}