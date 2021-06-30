package com.example.player2.main_windows;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.player2.R;
import com.example.player2.ui.GlavStranitsa;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {
    private Button exit;
    private static final long Start_Time_IN_MILLIS = 86400000;
    private TextView mTextViewCountDown;
    private boolean mTimeRunning;
    private long mTimeLeftInMillis;
    private final String KEYMillisLeft = "mils";
    private final String KEYTimeRunning = "boolean";
    private final String KEYTimeEndTime = "herb";
    private long mEndTime;
    View v;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        ini();
        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.menu_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.glavstr) {
                startActivity(new Intent(getActivity(), GlavStranitsa.class));
                return true;
            } else if (item.getItemId() == R.id.menu_telek) {
                startActivity(new Intent(getActivity(), TeleChannelActivity.class));
                return true;
            } else return item.getItemId() == R.id.menu_profile;
        });
        mTextViewCountDown = v.findViewById(R.id.textView3);

        exit.setOnClickListener(v -> {

            SharedPreferences preferences = requireActivity().getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("remember", "false");
            editor.apply();
            Intent intent = new Intent(getActivity(), PromotionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        });

       new  Handler (Looper.getMainLooper()).postDelayed(this::testToast, 1000);
        return v;
    }

    private void testToast() {
        startTimer();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimeRunning = false;
                SharedPreferences preferences = requireActivity().getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            }
        }.start();
        mTimeRunning = true;

    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;

        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;

        int seconds = (int) mTimeLeftInMillis / 1000 % 60;
        String timeLeftFormat = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        String TrueTimeLeftFormat=getString(R.string.blank) + timeLeftFormat;
        mTextViewCountDown.setText(TrueTimeLeftFormat);
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        mTimeLeftInMillis = prefs.getLong(KEYMillisLeft, Start_Time_IN_MILLIS);
        mTimeRunning = prefs.getBoolean(KEYTimeRunning, false);
        updateCountDownText();
        if (mTimeRunning) {
            mEndTime = prefs.getLong(KEYTimeEndTime, 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimeRunning = false;
                updateCountDownText();
            } else {
                startTimer();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(KEYMillisLeft, mTimeLeftInMillis);
        editor.putBoolean(KEYTimeRunning, mTimeRunning);
        editor.putLong(KEYTimeEndTime, mEndTime);
        editor.apply();

    }


    void ini() {

        exit = v.findViewById(R.id.exit);
        SharedPreferences getPreferences = requireActivity().getSharedPreferences("phone", MODE_PRIVATE);
        String checkbox = getPreferences.getString("member", "+996709872197");
        TextView profilePhone = v.findViewById(R.id.textView6);
        profilePhone.setText(checkbox);
        Button button = v.findViewById(R.id.button2);
        button.setOnClickListener(v -> startActivity(new Intent(getActivity(), Dialo.class)));
        Button dialog = v.findViewById(R.id.button3);
        dialog.setOnClickListener(v -> startActivity(new Intent(getActivity(), PromotionActivity.class)));
//
//      TextView textView=findViewById(R.id.textView5);
//       String text="Ваш баланс:";
//       String balance="0 сом";
//      Spannable spannable=new SpannableString(balance);
//spannable.setSpan(new ForegroundColorSpan(Color.YELLOW),text.length(),balance.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//textView.setText(spannable,TextView.BufferType.SPANNABLE);
    }
}