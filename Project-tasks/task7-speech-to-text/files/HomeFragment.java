

package com.example.mysplashscr;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private Handler handler;
    private Runnable runnable;
    private final List<Integer> imageList = Arrays.asList(R.drawable.good_day,R.drawable.cric,R.drawable.travel_tab, R.drawable.food_tab, R.drawable.shoppingtab,R.drawable.music_tab);
    private final long delayMillis = 2000;

    //Task 13
    TextView tvTxt;
    ImageView ivMic;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;

    TextToSpeech txt_to_speech;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.phone, "Phones"));
        itemList.add(new Item(R.drawable.song, "Music"));
        itemList.add(new Item(R.drawable.news, "News"));
        itemList.add(new Item(R.drawable.cricketlogo, "Cricket"));
        itemList.add(new Item(R.drawable.cosmetics, "Cosmetics"));
        itemList.add(new Item(R.drawable.furniture, "Home Furniture"));

        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);


        tvTxt = view.findViewById(R.id.tvMicTxt);
        ivMic = view.findViewById(R.id.ivMic);

        ivMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Now!");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_SPEECH_INPUT){
            if(resultCode == RESULT_OK && data != null ){
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                tvTxt.setText(result.get(0));

                txt_to_speech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        txt_to_speech.setLanguage(Locale.KOREAN);
                                    txt_to_speech.speak(result.get(0),TextToSpeech.QUEUE_FLUSH,null,null);

                    }
                });
            }
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        handler = new Handler();

        ImagePagerAdapter adapter = new ImagePagerAdapter(imageList);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
        }).attach();

        startAutoScroll();
    }

    private void startAutoScroll() {
        runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem() == imageList.size() - 1) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                handler.postDelayed(this, delayMillis);
            }
        };
        handler.postDelayed(runnable, delayMillis);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable);
    }


}

