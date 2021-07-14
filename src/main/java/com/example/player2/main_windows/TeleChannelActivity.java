    package com.example.player2.main_windows;

    import android.os.Bundle;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    import com.example.player2.R;

    import java.util.Objects;

    public class TeleChannelActivity extends AppCompatActivity {

        int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_kanal);
Objects.requireNonNull(getSupportActionBar()).setTitle("Телевидение");

    }

        @Override
        public void onBackPressed() {
            if(a==0){
                Toast.makeText(this, "Нажмите кнопку \"Назад\" еще раз, чтобы выйти из приложения.", Toast.LENGTH_SHORT).show();

                a++;
            }
            else {
                super.onBackPressed();

            }
        }
}