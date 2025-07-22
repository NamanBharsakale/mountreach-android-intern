package com.example.mysplashscr;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mysplashscr.R;

import java.util.ArrayList;

public class MyCricketActivity extends AppCompatActivity {

    ImageSlider imageSlider;
    VideoView videoView1, videoView2, videoView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cricket);
        setTitle("Cricket");

        imageSlider = findViewById(R.id.CricSlider);
        videoView1 = findViewById(R.id.crickVideo1);
        videoView2 = findViewById(R.id.crickVideo2);
        videoView3 = findViewById(R.id.crickVideo3);

        ArrayList<SlideModel> slideModelArrayList = new ArrayList<>();
        slideModelArrayList.add(new SlideModel(R.drawable.ro_kofinal, "It's Ro-Ko Moment", ScaleTypes.FIT));
        slideModelArrayList.add(new SlideModel(R.drawable.surya_final_catch, "Catching Wt20 not Catch", ScaleTypes.FIT));
        slideModelArrayList.add(new SlideModel(R.drawable.mucullum_highscore, "Pure Destruction", ScaleTypes.FIT));
        slideModelArrayList.add(new SlideModel(R.drawable.raina_101, "Raina's Runs", ScaleTypes.FIT));
        imageSlider.setImageList(slideModelArrayList);
        applyZoomInAnimation();
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(MyCricketActivity.this,"Welcome to Ro-Ko Moments",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MyCricketActivity.this, RoKoActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Toast.makeText(MyCricketActivity.this,"Best Catches of wt20",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(MyCricketActivity.this, CatchActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Toast.makeText(MyCricketActivity.this,"Kiwi Batsman's Power",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(MyCricketActivity.this, BrenDonActivity.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Toast.makeText(MyCricketActivity.this,"Raina's wt20 Hundred",Toast.LENGTH_SHORT).show();
                        Intent intent4 = new Intent(MyCricketActivity.this, RainaActivity.class);
                        startActivity(intent4);
                        break;
                    default:
                        break;
                }
            }
        });
        MediaController mediaController1 = new MediaController(this);
        mediaController1.setMediaPlayer(videoView1);
        videoView1.setMediaController(mediaController1);
        mediaController1.setAnchorView(videoView1);
        videoView1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/ko82"));
        videoView1.start();

        String videoPath2 = "android.resource://" + getPackageName() + "/raw/ro91";

        MediaController mediaController2 = new MediaController(this);
        mediaController1.setMediaPlayer(videoView2);
        videoView2.setMediaController(mediaController2);
        videoView2.setVideoPath(videoPath2);
        videoView2.start();

        String videoPath3 = "android.resource://" + getPackageName() + "/raw/wt20final";
        MediaController mediaController3 = new MediaController(this);
        mediaController3.setAnchorView(videoView3);
        mediaController1.setMediaPlayer(videoView3);
        videoView3.setMediaController(mediaController3);
        videoView3.setVideoPath(videoPath3);
        videoView3.start();

    }

    private void applyZoomInAnimation() {
        // Scale up animation
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageSlider, "scaleX", 0.5f, 1f);
        scaleX.setDuration(500);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageSlider, "scaleY", 0.5f, 1f);
        scaleY.setDuration(500);

        // Fade in animation
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageSlider, "alpha", 0f, 1f);
        fadeIn.setDuration(500);

        // Combine both animations
        scaleX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                imageSlider.setVisibility(View.VISIBLE);
            }
        });

        scaleX.start();
        scaleY.start();
        fadeIn.start();
    }
}
