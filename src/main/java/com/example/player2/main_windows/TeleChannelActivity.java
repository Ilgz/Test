    package com.example.player2.main_windows;

    import android.os.Bundle;

    import androidx.appcompat.app.AppCompatActivity;

    import com.example.player2.R;

    import java.util.Objects;

    public class TeleChannelActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_kanal);
Objects.requireNonNull(getSupportActionBar()).setTitle("Телевидение");

    }


}