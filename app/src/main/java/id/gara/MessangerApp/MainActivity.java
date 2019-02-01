package id.gara.MessangerApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.buttonSend);
        editText = findViewById(R.id.editTextUsername);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editText.getText().toString();
                if (!username.matches("")){
                    Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Mohon Isi Username Dahulu", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
