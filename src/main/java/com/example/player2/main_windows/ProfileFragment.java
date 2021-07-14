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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.player2.R;
import com.example.player2.ui.MediaActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {
    private Button exit;
    private  long Start_Time_IN_MILLIS=86400000;

private long AdTime;
    private TextView mTextViewCountDown;
    private long mTimeLeftInMillis;
    private final String KEYMillisLeft = "mils";
    private final String KEYTimeEndTime = "herb";
    private long mEndTime;
   private View v;
    private String phone;
    private DocumentReference documentReference;
private  CountDownTimer countDownTimer;
    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_profile, container, false);

        mTextViewCountDown = v.findViewById(R.id.textView3);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        SharedPreferences sharedPreferences= requireActivity().getSharedPreferences("phone", MODE_PRIVATE);
       phone=   sharedPreferences.getString("phone","");

       ini();

        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.menu_profile);
        new  Handler (Looper.getMainLooper()).postDelayed(this::startTimer, 100);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.glavstr) {
                startActivity(new Intent(getActivity(), MediaActivity.class));
                return true;
            } else if (item.getItemId() == R.id.menu_telek) {
                startActivity(new Intent(getActivity(), TeleChannelActivity.class));
                return true;
            } else return item.getItemId() == R.id.menu_profile;
        });

        exit.setOnClickListener(v -> {


            Intent intent = new Intent(getActivity(), RegisterActivity.class);
            startActivity(intent);

        });



        return v;
    }


    public void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        System.out.println("GoBack");
       countDownTimer=  new CountDownTimer (mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                System.out.println("GoTick");
                if (getActivity()!=null) {
                    SharedPreferences getPreferences = getActivity().getSharedPreferences("checkbox", MODE_PRIVATE);
                    int thirty = getPreferences.getInt("30days", 0);
//                    documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                        @Override
//                        public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
//                        AdTime = value.getLong("AdditionalTime");
//
//                    }
//                });

                    mTimeLeftInMillis=millisUntilFinished;


                    updateCountDownText();

//                    if (333 == thirty) {
//                        System.out.println("Hello");
//                        countDownTimer.cancel();
//
//                        mTimeRunning = false;
//                        Start_Time_IN_MILLIS = Long.parseLong("2592000000");
//                        mTimeLeftInMillis = Start_Time_IN_MILLIS;
//                        updateCountDownText();
//                        SharedPreferences preferences = requireActivity().getSharedPreferences("checkbox", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = preferences.edit();
//                        editor.putInt("30days", 111);
//                        editor.apply();
//                    }
                }
            }


            @Override
            public void onFinish() {
//                SharedPreferences preferences = requireActivity().getSharedPreferences("checkbox", MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putInt("30days",333 );
//                editor.apply();

            }
        }.start();

    }

    private void updateCountDownText() {
        int days = (int) (mTimeLeftInMillis / 1000) / (3600*24);

        int hours = (int) ((mTimeLeftInMillis / 1000)%86400) / 3600;

        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;

        int seconds = (int) mTimeLeftInMillis / 1000 % 60;

        String timeLeftFormat = String.format(Locale.getDefault(), "%02d:%02d:%02d:%02d",days, hours, minutes, seconds);
        String TrueTimeLeftFormat="     " + timeLeftFormat;




        if(days>1){
             String fimeLeftFormat = String.format(Locale.getDefault(), "%02d",days);
            mTextViewCountDown.setText(fimeLeftFormat+" "+"Дней");

        }

        else{
          mTextViewCountDown.setText(TrueTimeLeftFormat);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs=getActivity().getSharedPreferences("profile",MODE_PRIVATE);
        mTimeLeftInMillis=prefs.getLong(KEYMillisLeft,Start_Time_IN_MILLIS);
        updateCountDownText();
        mEndTime=prefs.getLong(KEYTimeEndTime,0);


    }

    @Override
    public void onStop() {
        super.onStop();
//            Map<String,Long> onstopper = new HashMap<>();
//            onstopper.put("timeLeftInMillis", mTimeLeftInMillis);
//            FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
//            firebaseFirestore.collection("Customers").document(phone).set(onstopper,SetOptions.merge());
        SharedPreferences prefs=getActivity().getSharedPreferences("profile",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putLong(KEYMillisLeft,mTimeLeftInMillis);
        editor.putLong(KEYTimeEndTime,mEndTime);
        editor.apply();

    }

    void ini() {

        exit = v.findViewById(R.id.exit);
        SharedPreferences getPreferences = requireActivity().getSharedPreferences("phone", MODE_PRIVATE);
        String checkbox = getPreferences.getString("phone", "+996709872197");
        TextView profilePhone = v.findViewById(R.id.textView6);
        profilePhone.setText(checkbox);
        Button button = v.findViewById(R.id.button2);
        button.setOnClickListener(v -> startActivity(new Intent(getActivity(), Support.class)));
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