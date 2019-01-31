package id.gara.MessangerApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.JsonNode;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Member;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.util.Random;

public class ChatActivity extends AppCompatActivity implements RoomListener {

    private String channelId =  "u66t3hVY7slkcYpo";
    private String roomName = "room-test";
    private EditText editText;
    private ImageButton buttonSend;

    private Scaledrone scaledrone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        editText = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        MemberData data = new MemberData(getRandomName(), getRandomColor());

        scaledrone = new Scaledrone(channelId, data);
        scaledrone.connect(new Listener() {
            @Override
            public void onOpen() {
                Toast.makeText(ChatActivity.this, "Scaledrone Connection Open", Toast.LENGTH_SHORT).show();
                scaledrone.subscribe(roomName, ChatActivity.this);
            }

            @Override
            public void onOpenFailure(Exception ex) {
                String exception = "Failed :" + ex;
                Toast.makeText(ChatActivity.this, exception, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Exception ex) {
                String exception = "Failed :" + ex;
                Toast.makeText(ChatActivity.this, exception, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClosed(String reason) {

                Toast.makeText(ChatActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage();
            }
        });
    }


    public void SendMessage(){
        String message = editText.getText().toString();
        if (message.length() >0){
            scaledrone.publish(roomName, message);
            editText.getText().clear();
        }
    }

    private String getRandomName() {
        String[] adjs = {"autumn", "hidden", "bitter", "misty", "silent", "empty", "dry", "dark", "summer", "icy", "delicate", "quiet", "white", "cool", "spring", "winter", "patient", "twilight", "dawn", "crimson", "wispy", "weathered", "blue", "billowing", "broken", "cold", "damp", "falling", "frosty", "green", "long", "late", "lingering", "bold", "little", "morning", "muddy", "old", "red", "rough", "still", "small", "sparkling", "throbbing", "shy", "wandering", "withered", "wild", "black", "young", "holy", "solitary", "fragrant", "aged", "snowy", "proud", "floral", "restless", "divine", "polished", "ancient", "purple", "lively", "nameless"};
        String[] nouns = {"waterfall", "river", "breeze", "moon", "rain", "wind", "sea", "morning", "snow", "lake", "sunset", "pine", "shadow", "leaf", "dawn", "glitter", "forest", "hill", "cloud", "meadow", "sun", "glade", "bird", "brook", "butterfly", "bush", "dew", "dust", "field", "fire", "flower", "firefly", "feather", "grass", "haze", "mountain", "night", "pond", "darkness", "snowflake", "silence", "sound", "sky", "shape", "surf", "thunder", "violet", "water", "wildflower", "wave", "water", "resonance", "sun", "wood", "dream", "cherry", "tree", "fog", "frost", "voice", "paper", "frog", "smoke", "star"};
        return (
                adjs[(int) Math.floor(Math.random() * adjs.length)] +
                        "_" +
                        nouns[(int) Math.floor(Math.random() * nouns.length)]
        );
    }

    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }

    @Override
    public void onOpen(Room room) {
        Toast.makeText(ChatActivity.this, "Connected to room", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOpenFailure(Room room, Exception ex) {
        String exception = "Failed :" + ex;
        Toast.makeText(ChatActivity.this, exception, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessage(Room room, JsonNode message, Member member) {

    }
}
