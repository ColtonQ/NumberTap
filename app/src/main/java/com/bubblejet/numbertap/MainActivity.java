package com.bubblejet.numbertap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /* notes:
    - to make a Special icon: 50x50, drag sticker from top left all the way to bottom right
    - then go back 8 pixels
    - then center 4 from each side

    - regular font Segoe UI 20, large 24
    - 8 bit: pixel pen 3px
     */

    public TextView testText, scoreText;

    public String defaultColour, freezeColour, countDownColour;
    public String numberColour, heartColour, timerColour, shieldColour, blastColour;
    public String backgroundColour;
    public String specialTheme;

    public Integer imageButtonNumberMin, imageButtonNumberMax;
    public Integer randomMin, randomMax, randomNum;
    public Integer imageButtonIndexMin, imageButtonIndexMax, imageButtonIndex;
    public Integer specialMin, specialMax, specialNum;
    public Integer maxStreams;
    public Integer lives;
    public Integer randomRate, freezeDuration, shieldDuration;
    public Integer score;

    public Boolean gameOver;
    public Boolean freeze, shield, blast;

    public ImageView backgroundImageView;

    public SoundPool soundPool;

    public ArrayList<Integer> numberPictureList;
    public ArrayList<Integer> numberPictureLargeList;
    public ArrayList<Integer> soundList;
    public ArrayList<Integer> numberPicturePoolList, numberPicturePixelList;

    public ArrayList<ImageButton> imageButtonList;
    public ArrayList<Integer> imageButtonIdList;
    public ArrayList<Boolean> imageButtonStateList;
    public ArrayList<Integer> imageButtonNumberList;
    public ArrayList<Integer> imageButtonClicksList;
    public ArrayList<String> imageButtonSpecialList;
    public ArrayList<CountDownTimer> imageButtonTimerList;
    public ArrayList<Long> millisUntilFinishedList;
    public ArrayList<Long> millisInFutureList;
    public ArrayList<ImageButton> imageButtonSpecialBackgroundList;

    public ArrayList<ImageButton> heartList, heartListShield;

    public ProgressBar progressBarFreeze;
    public ProgressBar progressBarShield;

    public ImageButton titleButton, playButton, themeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = findViewById(R.id.testText);
        scoreText = findViewById(R.id.scoreText);

        SharedPreferences sharedPreferences = getSharedPreferences("theme", Context.MODE_PRIVATE);
        defaultColour = sharedPreferences.getString("defaultColour", "#8cfffb");
        freezeColour = sharedPreferences.getString("freezeColour", "#00a8f3");
        countDownColour = sharedPreferences.getString("countDownColour", "ff0000");
        numberColour = sharedPreferences.getString("numberColour", "#000000");
        heartColour = sharedPreferences.getString("heartColour", "#ff0000");
        shieldColour = sharedPreferences.getString("shieldColour", "#00ff00");
        blastColour = sharedPreferences.getString("blastColour", "#9370db");
        backgroundColour = sharedPreferences.getString("backgroundColour", "#ffffff");
        specialTheme = sharedPreferences.getString("specialTheme", "false");

        timerColour = freezeColour;

        backgroundImageView = findViewById(R.id.backgroundImageView);
        backgroundImageView.setBackgroundColor(Color.parseColor(backgroundColour));

        score = 0;
        scoreText.setTextColor(Color.parseColor(numberColour));
        scoreText.setText(String.valueOf(score));

        imageButtonNumberMin = 1;
        imageButtonNumberMax = 8;

        randomMin = 0;
        randomMax = 0;

        imageButtonIndexMin = 0;
        imageButtonIndexMax = 15;

        specialMin = 0;
        specialMax = 20;

        lives = 3;

        randomRate = 300;
        freezeDuration = 2000;
        shieldDuration = 5000;

        gameOver = false;
        freeze = false;
        shield = false;
        blast = false;

        maxStreams = 1;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(maxStreams)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
        }

        numberPictureList = new ArrayList<>();
        numberPictureList.add(R.drawable.num00);
        numberPictureList.add(R.drawable.num01);
        numberPictureList.add(R.drawable.num02);
        numberPictureList.add(R.drawable.num03);
        numberPictureList.add(R.drawable.num04);
        numberPictureList.add(R.drawable.num05);
        numberPictureList.add(R.drawable.num06);
        numberPictureList.add(R.drawable.num07);
        numberPictureList.add(R.drawable.num08);

        numberPicturePoolList = new ArrayList<>();
        numberPicturePoolList.add(R.drawable.pool_num01);
        numberPicturePoolList.add(R.drawable.pool_num01);
        numberPicturePoolList.add(R.drawable.pool_num02);
        numberPicturePoolList.add(R.drawable.pool_num03);
        numberPicturePoolList.add(R.drawable.pool_num04);
        numberPicturePoolList.add(R.drawable.pool_num05);
        numberPicturePoolList.add(R.drawable.pool_num06);
        numberPicturePoolList.add(R.drawable.pool_num07);
        numberPicturePoolList.add(R.drawable.pool_num08);

        numberPicturePixelList = new ArrayList<>();
        numberPicturePixelList.add(R.drawable.pixel_num01);
        numberPicturePixelList.add(R.drawable.pixel_num01);
        numberPicturePixelList.add(R.drawable.pixel_num02);
        numberPicturePixelList.add(R.drawable.pixel_num03);
        numberPicturePixelList.add(R.drawable.pixel_num04);
        numberPicturePixelList.add(R.drawable.pixel_num05);
        numberPicturePixelList.add(R.drawable.pixel_num06);
        numberPicturePixelList.add(R.drawable.pixel_num07);
        numberPicturePixelList.add(R.drawable.pixel_num08);

        numberPictureLargeList = new ArrayList<>();
        numberPictureLargeList.add(R.drawable.num00_large);
        numberPictureLargeList.add(R.drawable.num01_large);
        numberPictureLargeList.add(R.drawable.num02_large);
        numberPictureLargeList.add(R.drawable.num03_large);
        numberPictureLargeList.add(R.drawable.num04_large);
        numberPictureLargeList.add(R.drawable.num05_large);
        numberPictureLargeList.add(R.drawable.num06_large);
        numberPictureLargeList.add(R.drawable.num07_large);
        numberPictureLargeList.add(R.drawable.num08_large);

        soundList = new ArrayList<>();
        soundList.add(soundPool.load(this, R.raw.note_low_b, 1));
        soundList.add(soundPool.load(this, R.raw.note_c, 1));
        soundList.add(soundPool.load(this, R.raw.note_d, 1));
        soundList.add(soundPool.load(this, R.raw.note_e, 1));
        soundList.add(soundPool.load(this, R.raw.note_f, 1));
        soundList.add(soundPool.load(this, R.raw.note_g, 1));
        soundList.add(soundPool.load(this, R.raw.note_a, 1));
        soundList.add(soundPool.load(this, R.raw.note_b, 1));
        soundList.add(soundPool.load(this, R.raw.note_high_c, 1));

        imageButtonList = new ArrayList<>();
        imageButtonList.add((ImageButton)findViewById(R.id.button00));
        imageButtonList.add((ImageButton)findViewById(R.id.button01));
        imageButtonList.add((ImageButton)findViewById(R.id.button02));
        imageButtonList.add((ImageButton)findViewById(R.id.button03));
        imageButtonList.add((ImageButton)findViewById(R.id.button04));
        imageButtonList.add((ImageButton)findViewById(R.id.button05));
        imageButtonList.add((ImageButton)findViewById(R.id.button06));
        imageButtonList.add((ImageButton)findViewById(R.id.button07));
        imageButtonList.add((ImageButton)findViewById(R.id.button08));
        imageButtonList.add((ImageButton)findViewById(R.id.button09));
        imageButtonList.add((ImageButton)findViewById(R.id.button10));
        imageButtonList.add((ImageButton)findViewById(R.id.button11));
        imageButtonList.add((ImageButton)findViewById(R.id.button12));
        imageButtonList.add((ImageButton)findViewById(R.id.button13));
        imageButtonList.add((ImageButton)findViewById(R.id.button14));
        imageButtonList.add((ImageButton)findViewById(R.id.button15));

        imageButtonSpecialBackgroundList = new ArrayList<>();
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button00Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button01Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button02Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button03Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button04Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button05Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button06Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button07Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button08Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button09Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button10Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button11Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button12Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button13Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button14Special));
        imageButtonSpecialBackgroundList.add((ImageButton)findViewById(R.id.button15Special));

        imageButtonIdList = new ArrayList<>();
        imageButtonStateList = new ArrayList<>();
        imageButtonNumberList = new ArrayList<>();
        imageButtonClicksList = new ArrayList<>();
        imageButtonSpecialList = new ArrayList<>();
        imageButtonTimerList = new ArrayList<>();
        millisUntilFinishedList = new ArrayList<>();
        millisInFutureList = new ArrayList<>();

        for (int n = 0; n < imageButtonList.size(); n++) {
            imageButtonIdList.add(imageButtonList.get(n).getId());
            imageButtonStateList.add(true);
            imageButtonNumberList.add(0);
            imageButtonClicksList.add(0);
            imageButtonSpecialList.add("");
            imageButtonTimerList.add(null);
            millisUntilFinishedList.add(null);
            millisInFutureList.add(null);

            imageButtonList.get(n).setEnabled(false);


            switch (specialTheme) {
                case "false":
                    imageButtonList.get(n).setColorFilter(Color.parseColor(numberColour));
                    imageButtonSpecialBackgroundList.get(n).setBackgroundColor(Color.parseColor(defaultColour));
                    break;
                case "pixel":
                    imageButtonSpecialBackgroundList.get(n).setBackgroundResource(R.drawable.pixel_block);
            }

        }

        heartList = new ArrayList<>();
        heartList.add((ImageButton)findViewById(R.id.heart1));
        heartList.add((ImageButton)findViewById(R.id.heart2));
        heartList.add((ImageButton)findViewById(R.id.heart3));
        heartList.add((ImageButton)findViewById(R.id.heart4));
        heartList.add((ImageButton)findViewById(R.id.heart5));

        heartListShield = new ArrayList<>();
        heartListShield.add((ImageButton)findViewById(R.id.heart1Shield));
        heartListShield.add((ImageButton)findViewById(R.id.heart2Shield));
        heartListShield.add((ImageButton)findViewById(R.id.heart3Shield));
        heartListShield.add((ImageButton)findViewById(R.id.heart4Shield));
        heartListShield.add((ImageButton)findViewById(R.id.heart5Shield));

        for (int n = 0; n < heartList.size(); n++) {
            heartList.get(n).setColorFilter(Color.parseColor(heartColour));
            heartListShield.get(n).setColorFilter(Color.parseColor(shieldColour));
        }


        progressBarFreeze = findViewById(R.id.progressBarFreeze);
        progressBarShield = findViewById(R.id.progressBarShield);

        Drawable backgroundDr = new ColorDrawable(Color.parseColor("#00000000"));
        Drawable secProgressDr = new ColorDrawable(Color.parseColor("#e0e0e0"));

        Drawable progressDrFreeze = new ScaleDrawable(new ColorDrawable(Color.parseColor(freezeColour)), Gravity.START, 1, -1);
        LayerDrawable resultDrFreeze = new LayerDrawable(new Drawable[] { backgroundDr, secProgressDr, progressDrFreeze });

        resultDrFreeze.setId(0, android.R.id.background);
        resultDrFreeze.setId(1, android.R.id.secondaryProgress);
        resultDrFreeze.setId(2, android.R.id.progress);

        progressBarFreeze.setProgressDrawable(resultDrFreeze);
        progressBarFreeze.setScaleY(0.25f);
        progressBarFreeze.setVisibility(View.INVISIBLE);

        Drawable progressDrShield = new ScaleDrawable(new ColorDrawable(Color.parseColor(shieldColour)), Gravity.START, 1, -1);
        LayerDrawable resultDrShield = new LayerDrawable(new Drawable[] { backgroundDr, secProgressDr, progressDrShield });

        resultDrShield.setId(0, android.R.id.background);
        resultDrShield.setId(1, android.R.id.secondaryProgress);
        resultDrShield.setId(2, android.R.id.progress);

        progressBarShield.setProgressDrawable(resultDrShield);
        progressBarShield.setScaleY(0.25f);
        progressBarShield.setVisibility(View.INVISIBLE);

        titleButton = findViewById(R.id.titleButton);
        playButton = findViewById(R.id.playButton);
        themeButton = findViewById(R.id.themeButton);
        titleButton.setEnabled(false);
    }

    public void onClickPlayButton(View view) {
        playButton.setVisibility(View.GONE);
        titleButton.setVisibility(View.GONE);
        playButton.setEnabled(false);

        for (int n = 0; n < imageButtonList.size(); n++) {
            imageButtonList.get(n).setEnabled(true);
        }

        randomEvent();
    }

    public void randomEvent() {
        randomNum = randomMin + (int) (Math.random() * ((randomMax - randomMin) + 1));

        new CountDownTimer(500, 100) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (randomNum == 0) {
                    imageButtonIndex = imageButtonIndexMin + (int) (Math.random() * ((imageButtonIndexMax - imageButtonIndexMin) + 1));

                    if (imageButtonStateList.get(imageButtonIndex) & !freeze & !blast) {
                        imageButtonNumberList.set(imageButtonIndex, imageButtonNumberMin + (int) (Math.random() * ((imageButtonNumberMax - imageButtonNumberMin) + 1)));

                        switch (specialTheme) {
                            case "false":
                                imageButtonList.get(imageButtonIndex).setImageResource(numberPictureList.get(imageButtonNumberList.get(imageButtonIndex)));
                                break;
                            case "pool":
                                imageButtonList.get(imageButtonIndex).setImageResource(numberPicturePoolList.get(imageButtonNumberList.get(imageButtonIndex)));
                                break;
                            case "pixel":
                                imageButtonList.get(imageButtonIndex).setImageResource(numberPicturePixelList.get(imageButtonNumberList.get(imageButtonIndex)));
                        }


                        imageButtonStateList.set(imageButtonIndex, false);

                        specialNum = specialMin + (int) (Math.random() * ((specialMax - specialMin) + 1));
                        int potentialLives = 0;

                        for (int n = 0; n < imageButtonList.size(); n++) {
                            if (imageButtonSpecialList.get(n).equals("heart")) {
                                potentialLives += 1;
                            }
                        }

                        boolean potentialFreeze = false;
                        for (int n = 0; n < imageButtonList.size(); n++) {
                            if (imageButtonSpecialList.get(n).equals("freeze")) {
                                potentialFreeze = true;
                            }
                        }

                        boolean potentialShield = false;
                        for (int n = 0; n < imageButtonList.size(); n++) {
                            if (imageButtonSpecialList.get(n).equals("shield")) {
                                potentialShield = true;
                            }
                        }

                        boolean potentialBlast = false;
                        for (int n = 0; n < imageButtonList.size(); n++) {
                            if (imageButtonSpecialList.get(n).equals("blast")) {
                                potentialBlast = true;
                            }
                        }

                        if (specialNum <= 3 & imageButtonSpecialList.get(imageButtonIndex).equals("") & (lives + potentialLives) < 5) {
                            imageButtonSpecialList.set(imageButtonIndex, "heart");
                            switch (specialTheme) {
                                case "false":
                                    imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.heart);
                                    break;
                                case "pixel":
                                    imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.pixel_heart);
                            }
                            imageButtonSpecialBackgroundList.get(imageButtonIndex).setColorFilter(Color.parseColor(heartColour));
                        } else if (specialNum > 19 & imageButtonSpecialList.get(imageButtonIndex).equals("") & !potentialFreeze & !freeze) {
                            imageButtonSpecialList.set(imageButtonIndex, "freeze");
                            switch (specialTheme) {
                                case "false":
                                    imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.timer);
                                    break;
                                case "pixel":
                                    imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.pixel_freeze);
                            }
                            imageButtonSpecialBackgroundList.get(imageButtonIndex).setColorFilter(Color.parseColor(timerColour));
                        } else if (4 <= specialNum & specialNum <= 6 & imageButtonSpecialList.get(imageButtonIndex).equals("") & !potentialShield & !shield) {
                            imageButtonSpecialList.set(imageButtonIndex, "shield");
                            switch (specialTheme) {
                                case "false":
                                    imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.shield);
                                    break;
                                case "pixel":
                                    imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.pixel_shield);
                            }
                            imageButtonSpecialBackgroundList.get(imageButtonIndex).setColorFilter(Color.parseColor(shieldColour));
                        } else if ( specialNum == 7 & imageButtonSpecialList.get(imageButtonIndex).equals("") & !potentialBlast & !blast) {
                            imageButtonSpecialList.set(imageButtonIndex, "blast");
                            switch (specialTheme) {
                                case "false":
                                    imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.blast);
                                    break;
                                case "pixel":
                                    imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.pixel_blast);
                            }
                            imageButtonSpecialBackgroundList.get(imageButtonIndex).setColorFilter(Color.parseColor(blastColour));
                        }

                        setTimer(imageButtonIndex, (long) 2000 + imageButtonNumberList.get(imageButtonIndex) * 400);
                    }

                }

                new CountDownTimer(randomRate, 50) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        if (!gameOver & !freeze) {
                            randomEvent();
                        }
                    }

                }.start();

            }

        }.start();
    }


    public void onClickImageButton(View view) {

        final int imageButtonIndex = imageButtonIdList.indexOf(view.getId());
        final int imageButtonNumber = imageButtonNumberList.get(imageButtonIndex);

        if (imageButtonNumber < 0) {
            soundPool.play(0, 1, 1, 0, 0, 1);
        } else {
            soundPool.play(soundList.get(imageButtonNumber), 1, 1, 0, 0, 1);
        }

        imageButtonNumberList.set(imageButtonIndex, imageButtonNumber - 1);

        if (imageButtonNumber > 0 & imageButtonNumber != 1) {

            updateNumber(imageButtonIndex, imageButtonNumber);
            updateScore();

        } else if (imageButtonNumber == 0) {

            updateNumber(imageButtonIndex, imageButtonNumber);

            imageButtonList.get(imageButtonIndex).setEnabled(false);
            imageButtonStateList.set(imageButtonIndex, false);

            new CountDownTimer(500, 500) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    resetButton(imageButtonIndex);
                }

            }.start();

            if (!shield) {
                lives -= 1;
                updateHearts();
            }

            if (lives == 0) {
                gameOver();
            }
        } else if (imageButtonNumber == 1){
            imageButtonTimerList.get(imageButtonIndex).cancel();

            switch (imageButtonSpecialList.get(imageButtonIndex)) {
                case "heart":
                    lives += 1;
                    if (shield) {
                        updateShield();
                    }
                    updateHearts();
                    break;
                case "freeze":
                    freeze = true;
                    beginFreeze();
                    break;
                case "shield":
                    shield = true;
                    beginShield();
                    break;
                case "blast":
                    blast = true;
                    beginBlast();
                    break;
            }

            updateScore();
            resetButton(imageButtonIndex);

        }
    }

    public void updateScore() {
        score += 1;
        scoreText.setText(String.valueOf(score));
    }

    public void updateNumber(final int imageButtonIndex, final int imageButtonNumber) {
        final android.widget.ImageButton imageButton = imageButtonList.get(imageButtonIndex);

        if (imageButtonNumber == 0) {
            imageButton.setImageResource(R.drawable.numxx_large);
        } else {
            switch (specialTheme) {
                case "false":
                    imageButton.setImageResource(numberPictureLargeList.get(imageButtonNumber - 1));
                    break;
                case "pool":
                    imageButton.setImageResource(numberPicturePoolList.get(imageButtonNumber - 1));
            }

        }

        new CountDownTimer(50, 50) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (imageButtonNumber == 0) {
                    switch (specialTheme) {
                        case "false":
                            imageButton.setImageResource(R.drawable.numxx);
                            break;
                        case "pixel":
                            imageButton.setImageResource(R.drawable.pixel_numxx);
                    }

                } else {
                    switch (specialTheme) {
                        case "false":
                            imageButton.setImageResource(numberPictureList.get(imageButtonNumber - 1));
                            break;
                        case "pool":
                            imageButton.setImageResource(numberPicturePoolList.get(imageButtonNumber - 1));
                            break;
                        case "pixel":
                            imageButton.setImageResource(numberPicturePixelList.get(imageButtonNumber - 1));
                    }
                }
            }

        }.start();
    }

    public void resetButton(final int imageButtonIndex) {
        if (!gameOver) {
            imageButtonList.get(imageButtonIndex).setImageResource(R.drawable.blank_transparent);
            imageButtonList.get(imageButtonIndex).setBackgroundResource(R.drawable.blank_transparent);
            imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.blank_transparent);
            imageButtonSpecialList.set(imageButtonIndex, "");
            imageButtonClicksList.set(imageButtonIndex, 0);
            imageButtonNumberList.set(imageButtonIndex, 0);
            imageButtonTimerList.set(imageButtonIndex, null);
            millisUntilFinishedList.set(imageButtonIndex, null);
            imageButtonSpecialList.set(imageButtonIndex, "");
            millisInFutureList.set(imageButtonIndex, null);

            if (blast) {
                imageButtonSpecialBackgroundList.get(imageButtonIndex).setBackgroundColor(Color.parseColor(blastColour));
            } else if (freeze) {
                imageButtonSpecialBackgroundList.get(imageButtonIndex).setBackgroundColor(Color.parseColor(freezeColour));
            } else {
                switch (specialTheme) {
                    case "false":
                        imageButtonSpecialBackgroundList.get(imageButtonIndex).setBackgroundColor(Color.parseColor(defaultColour));
                        break;
                    case "pixel":
                        imageButtonSpecialBackgroundList.get(imageButtonIndex).setBackgroundResource(R.drawable.pixel_block);
                }
            }

            new CountDownTimer(500, 500) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imageButtonList.get(imageButtonIndex).setEnabled(true);
                    imageButtonStateList.set(imageButtonIndex, true);
                }

            }.start();
        }
    }

    public void updateHearts() {
        for (int n = 0; n < heartList.size(); n++) {
            if (n < lives) {
                heartList.get(n).setImageResource(R.drawable.heart_small);
            } else {
                heartList.get(n).setImageResource(R.drawable.heart_small_empty);
            }
        }
    }

    public void beginFreeze() {
        for (int n = 0; n < imageButtonList.size(); n++) {
            if (imageButtonTimerList.get(n) != null) {
                imageButtonTimerList.get(n).cancel();
            }

            imageButtonSpecialBackgroundList.get(n).setBackgroundColor(Color.parseColor(freezeColour));
        }

        progressBarFreeze.setVisibility(View.VISIBLE);
        progressBarFreeze.setProgress(100);
        final int millisUntilFuture = freezeDuration;
        new CountDownTimer(millisUntilFuture, 50) {

            public void onTick(long millisUntilFinished) {
                progressBarFreeze.setProgress((int) Math.round((double) millisUntilFinished/millisUntilFuture * 100));
            }

            public void onFinish() {
                progressBarFreeze.setVisibility(View.INVISIBLE);

                for (int n = 0; n < imageButtonList.size(); n++) {
                    if (imageButtonTimerList.get(n) != null & millisUntilFinishedList.get(n) != null) {
                        setTimer(n, millisUntilFinishedList.get(n));
                    }

                    switch (specialTheme) {
                        case "false":
                            imageButtonSpecialBackgroundList.get(n).setBackgroundColor(Color.parseColor(defaultColour));
                            break;
                        case "pixel":
                            imageButtonSpecialBackgroundList.get(n).setBackgroundResource(R.drawable.pixel_block);
                    }
                }
                freeze = false;
                randomEvent();
            }

        }.start();
    }

    public void beginShield() {
        progressBarShield.setVisibility(View.VISIBLE);
        progressBarShield.setProgress(100);
        final int millisUntilFuture = shieldDuration;
        new CountDownTimer(millisUntilFuture, 50) {

            public void onTick(long millisUntilFinished) {
                progressBarShield.setProgress((int) Math.round((double) millisUntilFinished/millisUntilFuture * 100));
            }

            public void onFinish() {
                progressBarShield.setVisibility(View.INVISIBLE);
                removeShield();
            }

        }.start();

        updateShield();
    }

    public void updateShield() {
        for (int n = 0; n < heartList.size(); n++) {
            if (n < lives) {
                heartListShield.get(n).setImageResource(R.drawable.shield_small);
            }
        }
    }

    public void removeShield() {
        shield = false;
        for (int n = 0; n < heartList.size(); n++) {
            heartListShield.get(n).setImageResource(R.drawable.blank_small);
        }
    }

    public void beginBlast() {
        for (int n = 0; n < imageButtonList.size(); n++) {
            if (imageButtonTimerList.get(n) != null) {
                imageButtonTimerList.get(n).cancel();
            }

            resetButton(n);
            imageButtonSpecialBackgroundList.get(n).setBackgroundColor(Color.parseColor(blastColour));
        }


        new CountDownTimer(500, 1500) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                for (int n = 0; n < imageButtonList.size(); n++) {
                    switch (specialTheme) {
                        case "false":
                            imageButtonSpecialBackgroundList.get(n).setBackgroundColor(Color.parseColor(defaultColour));
                            break;
                        case "pixel":
                            imageButtonSpecialBackgroundList.get(n).setBackgroundResource(R.drawable.pixel_block);
                    }
                }

                blast = false;
            }

        }.start();
    }


    public void setTimer(final int imageButtonIndex, final long millisInFuture) {

        if (millisInFutureList.get(imageButtonIndex) == null) {
            millisInFutureList.set(imageButtonIndex, millisInFuture);
        }

        imageButtonTimerList.set(imageButtonIndex,
                new CountDownTimer(millisInFuture, 1) {

                    public void onTick(long millisUntilFinished) {
                        millisUntilFinishedList.set(imageButtonIndex, millisUntilFinished);

                        String alpha;
                        if (millisInFutureList.get(imageButtonIndex) != null) {
                            alpha = Integer.toString((int)Math.round((1 - (double)millisUntilFinished/millisInFutureList.get(imageButtonIndex)) * 255), 16);
                        } else {
                            alpha = Integer.toString((int)Math.round((1 - (double)millisUntilFinished/millisInFuture) * 255), 16);
                        }

                        if (Integer.parseInt(alpha, 16) < 16) {
                            imageButtonSpecialBackgroundList.get(imageButtonIndex).setBackgroundColor(Color.parseColor("#" + "0" + alpha + countDownColour));
                        } else {
                            imageButtonSpecialBackgroundList.get(imageButtonIndex).setBackgroundColor(Color.parseColor("#" + alpha + countDownColour));
                        }
                    }

                    public void onFinish() {
                        if (!shield) {
                            lives -= 1;
                            updateHearts();
                        }

                        switch (specialTheme) {
                            case "false":
                                imageButtonSpecialBackgroundList.get(imageButtonIndex).setBackgroundColor(Color.parseColor(defaultColour));
                                break;
                            case "pixel":
                                imageButtonSpecialBackgroundList.get(imageButtonIndex).setBackgroundResource(R.drawable.pixel_block);
                        }
                        imageButtonSpecialBackgroundList.get(imageButtonIndex).setImageResource(R.drawable.blank_transparent);

                        if (!gameOver) {

                            updateNumber(imageButtonIndex, 0);

                            imageButtonList.get(imageButtonIndex).setEnabled(false);
                            imageButtonStateList.set(imageButtonIndex, false);
                            soundPool.play(soundList.get(0), 1, 1, 0, 0, 1);
                        }

                        if (lives == 0) {
                            gameOver();
                        } else {
                            new CountDownTimer(500, 500) {

                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {
                                    resetButton(imageButtonIndex);
                                }

                            }.start();
                        }
                    }

                });

        imageButtonTimerList.get(imageButtonIndex).start();

    }

    public void gameOver() {
        titleButton.setVisibility(View.VISIBLE);
        titleButton.setImageResource(R.drawable.game_over);
        themeButton.setEnabled(false);

        gameOver = true;

        for (int n = 0; n < imageButtonList.size(); n++) {
            imageButtonList.get(n).setEnabled(false);
            if (imageButtonTimerList.get(n) != null) {
                imageButtonTimerList.get(n).cancel();
            }
        }

        addPoints();

        new CountDownTimer(2500, 500) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                switchActivity();
            }

        }.start();
    }

    public void addPoints() {
        SharedPreferences sharedPreferences = getSharedPreferences("theme", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int totalPoints = sharedPreferences.getInt("totalPoints", 0);
        //totalPoints = 0;
        totalPoints += score;
        editor.putInt("totalPoints", totalPoints);

        editor.apply();
    }

    public void onClickThemeButton(View view) {
        switchActivity();
    }

    public void switchActivity() {
        Intent i = new Intent(this, ThemeActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (int n = 0; n < imageButtonList.size(); n++) {
            if (imageButtonTimerList.get(n) != null) {
            imageButtonTimerList.get(n).cancel();
            }
        }
    }
}
