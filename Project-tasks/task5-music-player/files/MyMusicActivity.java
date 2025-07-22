package com.example.mysplashscr;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MyMusicActivity extends AppCompatActivity {

    TextView tvSongName, tvStartTime, tvTotaltime;
    ImageView ivSongImage;
    SeekBar sbSongProgress;
    ImageView ivPrevious, ivBackward, ivPlay, ivForward, ivNext;

    private int currentIndex = 0;

    MediaPlayer mMediaPlayer;

    private static int sTime = 0, oTime = 0, tTime = 0, fTime = 10000, bTime = 10000;//step 1
    Handler h = new Handler();//step 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_music);


        tvSongName = findViewById(R.id.tvMymusicname);
        tvStartTime = findViewById(R.id.MymusicStartTime);
        tvTotaltime = findViewById(R.id.MymusicTotalTime);
        ivSongImage = findViewById(R.id.tvMyMusicImage);
        sbSongProgress = findViewById(R.id.MyMusicProgress);
        ivPrevious = findViewById(R.id.MyMusicPrevious);
        ivBackward = findViewById(R.id.MyMusicBack10);
        ivPlay = findViewById(R.id.MyMusicPlayPause);
        ivForward = findViewById(R.id.MyMusicForward10);
        ivNext = findViewById(R.id.MyMusicNext);

        tvSongName.setText("Song Name");
        ivSongImage.setImageResource(R.drawable.laree_choote_cover);

        ArrayList<Integer> songArrayList = new ArrayList<>();
        songArrayList.add(0, R.raw.tere_sath_dagar);
        songArrayList.add(1, R.raw.mein_hoon_na);
        songArrayList.add(2, R.raw.dekhta_hu_sapne_tere);
        songArrayList.add(3, R.raw.mere_mehboob);
        songArrayList.add(4, R.raw.piyu_bole);
        songArrayList.add(5, R.raw.laree_choote);


        mMediaPlayer = MediaPlayer.create(MyMusicActivity.this, songArrayList.get(currentIndex));


        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    ivPlay.setImageResource(R.drawable.icon_play);
                } else {
                    mMediaPlayer.start();
                    ivPlay.setImageResource(R.drawable.icon_pause);
                }

                tTime = mMediaPlayer.getDuration();//step 3
                sTime = mMediaPlayer.getCurrentPosition();// step 4

                if (oTime == 0)//step 5
                {
                    sbSongProgress.setMax(tTime);
                    oTime = 1;
                }

                //step 6:-
                tvTotaltime.setText(String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(tTime),
                        TimeUnit.MILLISECONDS.toSeconds(tTime) % 60));

                tvStartTime.setText(String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) % 60));

                h.postDelayed(UpdateSongProgress, 1000);
                songDetails();

            }

            private void updateTotalTime()
            {
                tTime = mMediaPlayer.getDuration();
                tvTotaltime.setText(String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(tTime),
                        TimeUnit.MILLISECONDS.toSeconds(tTime) % 60 ));
            }

            private Runnable UpdateSongProgress = new Runnable() {
                @Override
                public void run() {
                    if(mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        updateTotalTime();
                        sTime = mMediaPlayer.getCurrentPosition();
                        tvStartTime.setText(String.format("%d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(sTime),
                                TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                        TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))));
                        sbSongProgress.setProgress(sTime);
                        h.postDelayed(this, 1000);
                    } else if (sTime + 1000 >= tTime) {
                        updateTotalTime();
                        sTime = tTime;
                        sTime = mMediaPlayer.getCurrentPosition();
                        tvStartTime.setText(String.format("%d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(sTime),
                                TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                        TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))));
                        sbSongProgress.setProgress(sTime);
                    }
                }
            };
        });

        sbSongProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                    sbSongProgress.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < songArrayList.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }

                if (mMediaPlayer != null) {
                    ivPlay.setImageResource(R.drawable.icon_pause);
                }
                mMediaPlayer = MediaPlayer.create(MyMusicActivity.this, songArrayList.get(currentIndex));
                mMediaPlayer.start();
                songDetails();
            }
        });

        ivBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sTime - bTime > 0){
                    sTime = sTime - bTime;
                    mMediaPlayer.seekTo(sTime);
                }else {
                    Toast.makeText(MyMusicActivity.this,"Can't Jump Backward for 10 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sTime + fTime < tTime){
                    sTime = sTime + fTime;
                    mMediaPlayer.seekTo(sTime);
                }else {
                    Toast.makeText(MyMusicActivity.this,"Can't Jump Forward for 10 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < 0) {
                    currentIndex--;
                } else {
                    currentIndex = songArrayList.size() - 1;
                }

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }

                if (mMediaPlayer != null) {
                    ivPlay.setImageResource(R.drawable.icon_pause);
                }
                mMediaPlayer = MediaPlayer.create(MyMusicActivity.this, songArrayList.get(currentIndex));
                mMediaPlayer.start();
                songDetails();
            }

        });
    }
    private void songDetails() {

        if (currentIndex == 0) {
            tvSongName.setText("tere_sath_dagar");
            tTime = mMediaPlayer.getDuration();
            ivSongImage.setImageResource(R.drawable.tere_sath_dagar_cover);
        } else if (currentIndex == 1) {
            tvSongName.setText("mein_hoon_na");
            ivSongImage.setImageResource(R.drawable.mein_hoon_na_cover);
        } else if (currentIndex == 2) {
            tvSongName.setText("dekhta_hu_sapne_tere");
            ivSongImage.setImageResource(R.drawable.dekhta_tere_sapne);
        } else if (currentIndex == 3) {
            tvSongName.setText("mere_mehboob");
            ivSongImage.setImageResource(R.drawable.mere_mehboob_song_cover);
        } else if (currentIndex == 4) {
            tvSongName.setText("piyu_bole");
            ivSongImage.setImageResource(R.drawable.piyu_bole_cover);
        } else if (currentIndex == 5) {
            tvSongName.setText("laree_choote");
            ivSongImage.setImageResource(R.drawable.laree_choote_cover);
        }
    }
}

