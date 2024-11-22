package de.bbs_technik_koblenz.jl.superverdopplerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText eT_numberToDouble;
    private Button btn_start;
    private int numberToDouble;
    private TextView display_doubledNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        eT_numberToDouble = findViewById(R.id.editTextNumberToDouble);
        btn_start = findViewById(R.id.btn_startDouble);
        display_doubledNumber = findViewById(R.id.display_doubledNumber);

        btn_start.setOnClickListener(view -> startDoublingActivity());

    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    private Integer getInput () {
        String userInput = eT_numberToDouble.getText().toString();
        if (!userInput.isEmpty() || !userInput.isBlank()) {
            return Integer.parseInt(userInput);
        }
        Toast.makeText(this, "Keine Zahl eingeben", Toast.LENGTH_SHORT).show();
        return null;
    }

    private void startDoublingActivity () {
        if (getInput() != null) {
            numberToDouble = getInput();
            Intent verdopplerActivity = new Intent(MainActivity.this, VerdopplerActivity.class);
            verdopplerActivity.putExtra("numberToDouble", numberToDouble);
            startActivity(verdopplerActivity);
        }
    }

    private Integer getDoubledNumber(Intent intent) {
        return intent.getIntExtra("doubledNumber", 0);
    }

    private void setDisplayDoubledNumber(Intent intent) {
        display_doubledNumber.setText("Das Ergebnis lautet: " +getDoubledNumber(intent));
        display_doubledNumber.setVisibility(View.VISIBLE);
    }

    private void update() {
        Intent mainActivity = getIntent();
        if(mainActivity != null && mainActivity.hasExtra("doubledNumber")) {
            setDisplayDoubledNumber(mainActivity);
            return;
        }
        Toast.makeText(this, "Keine verdoppelte Zahl übergeben", Toast.LENGTH_SHORT).show();
    }

}