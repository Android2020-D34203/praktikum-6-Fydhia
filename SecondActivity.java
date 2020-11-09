package id.ac.id.telkomuniversity.tass.praktikactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    private String KEY_DATA = "DATA";
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.Dataa);

        Bundle extras = getIntent().getExtras();
        data = extras.getString(KEY_DATA);
        textView.setText(data);

    }
}