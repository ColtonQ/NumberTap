package com.bubblejet.numbertap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ThemeActivity extends AppCompatActivity {

    /* notes:
    to create a new theme:
     - make copy of layouts in xml file, rename ids, append ids in the top layout with "Special"
     - make new lists if necessary
     - add corresponding values to all lists

     - pool ball:
     - stretch circle sticker to end, go back 16, small circle from top right such that no gap appears between circles, arial rounded mt 10

     - pixel numbers:
     - pixel pen 3
     */

    public TextView pointsText;
    public Integer totalPoints;

    public ArrayList<String> themeNameList;
    public ArrayList<Button> themeButtonList;
    public ArrayList<Integer> themeButtonIdList;

    public ArrayList<String> theme00ColourList, theme01ColourList, theme02ColourList, theme03ColourList,
            theme04ColourList, theme05ColourList, theme06ColourList, theme07ColourList;
    public ArrayList<String> themeS0ColourList, themeS1ColourList;
    public ArrayList<ArrayList<String>> colourListList;

    public ArrayList<TextView> themeTextList;
    public ArrayList<ImageButton> sampleButtonList, sampleButtonListSpecial;

    public ArrayList<LinearLayout> themeButtonSpecialList;

    public ArrayList<Integer> priceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        pointsText = findViewById(R.id.pointsText);
        SharedPreferences sharedPreferences = getSharedPreferences("theme", Context.MODE_PRIVATE);
        totalPoints = sharedPreferences.getInt("totalPoints", 0);
        String pointsString = String.valueOf(totalPoints) + getResources().getString(R.string.points);
        pointsText.setText(pointsString);

        themeNameList = new ArrayList<>();
        themeNameList.add("Default");
        themeNameList.add("Dark");
        themeNameList.add("Pastel");
        themeNameList.add("Ocean");
        themeNameList.add("Bubblegum");
        themeNameList.add("Forest");
        themeNameList.add("Walnut");
        themeNameList.add("Grape");

        themeNameList.add("Billiards");
        themeNameList.add("Pixel");


        themeButtonList = new ArrayList<>();
        themeButtonList.add((Button) findViewById(R.id.theme00Button));
        themeButtonList.add((Button)findViewById(R.id.theme01Button));
        themeButtonList.add((Button)findViewById(R.id.theme02Button));
        themeButtonList.add((Button)findViewById(R.id.theme03Button));
        themeButtonList.add((Button)findViewById(R.id.theme04Button));
        themeButtonList.add((Button)findViewById(R.id.theme05Button));
        themeButtonList.add((Button)findViewById(R.id.theme06Button));
        themeButtonList.add((Button)findViewById(R.id.theme07Button));

        themeButtonList.add((Button)findViewById(R.id.themeS0Button));
        themeButtonList.add((Button)findViewById(R.id.themeS1Button));

        /*themeLayoutList = new ArrayList<>();
        themeLayoutList.add((LinearLayout)findViewById(R.id.theme00ButtonE));
        themeLayoutList.add((LinearLayout)findViewById(R.id.theme01ButtonE));
        themeLayoutList.add((LinearLayout)findViewById(R.id.theme02ButtonE));
        themeLayoutList.add((LinearLayout)findViewById(R.id.theme03ButtonE));
        themeLayoutList.add((LinearLayout)findViewById(R.id.theme04ButtonE));
        themeLayoutList.add((LinearLayout)findViewById(R.id.theme05ButtonE));
        themeLayoutList.add((LinearLayout)findViewById(R.id.theme06ButtonE));
        themeLayoutList.add((LinearLayout)findViewById(R.id.theme07ButtonE));
        themeLayoutList.add((LinearLayout)findViewById(R.id.themeS0ButtonE));
        themeLayoutList.add((LinearLayout)findViewById(R.id.themeS1ButtonE));*/

        themeButtonSpecialList = new ArrayList<>();
        themeButtonSpecialList.add((LinearLayout) findViewById(R.id.theme00ButtonSpecial));
        themeButtonSpecialList.add((LinearLayout)findViewById(R.id.theme01ButtonSpecial));
        themeButtonSpecialList.add((LinearLayout)findViewById(R.id.theme02ButtonSpecial));
        themeButtonSpecialList.add((LinearLayout)findViewById(R.id.theme03ThemeButtonSpecial));
        themeButtonSpecialList.add((LinearLayout)findViewById(R.id.theme04ButtonSpecial));
        themeButtonSpecialList.add((LinearLayout)findViewById(R.id.theme05ButtonSpecial));
        themeButtonSpecialList.add((LinearLayout)findViewById(R.id.theme06ButtonSpecial));
        themeButtonSpecialList.add((LinearLayout)findViewById(R.id.theme07ButtonSpecial));

        themeButtonSpecialList.add((LinearLayout) findViewById(R.id.themeS0ButtonSpecial));
        themeButtonSpecialList.add((LinearLayout) findViewById(R.id.themeS1ButtonSpecial));

        themeButtonIdList = new ArrayList<>();
        for (int n = 0; n < themeButtonList.size(); n++) {
            themeButtonIdList.add(themeButtonList.get(n).getId());
        }

        theme00ColourList = new ArrayList<>();
        theme00ColourList.add("#8cfffb"); //default
        theme00ColourList.add("ff0000"); //countDown
        theme00ColourList.add("#000000"); //number
        theme00ColourList.add("#ff0000"); //heart
        theme00ColourList.add("#00a8f3"); //freeze
        theme00ColourList.add("#00ff00"); //shield
        theme00ColourList.add("#9370db"); //blast
        theme00ColourList.add("#ffffff"); //background

        theme01ColourList = new ArrayList<>();
        theme01ColourList.add("#00a8f3"); //default
        theme01ColourList.add("b83dba"); //countDown
        theme01ColourList.add("#ffffff"); //number
        theme01ColourList.add("#ff0000"); //heart
        theme01ColourList.add("#0095d7"); //freeze
        theme01ColourList.add("#266a2e"); //shield
        theme01ColourList.add("#4b0082"); //blast
        theme01ColourList.add("#6e6e6e"); //background

        theme02ColourList = new ArrayList<>();
        theme02ColourList.add("#cff1fb"); //default
        theme02ColourList.add("0e4d92"); //countDown
        theme02ColourList.add("#bb64da"); //number
        theme02ColourList.add("#ffdbdb"); //heart
        theme02ColourList.add("#bdd7ea"); //freeze
        theme02ColourList.add("#ffddd1"); //shield
        theme02ColourList.add("#d1d1f9"); //blast
        theme02ColourList.add("#fcfdcd"); //background

        theme03ColourList = new ArrayList<>();
        theme03ColourList.add("#188a8d"); //default
        theme03ColourList.add("0080ff"); //countDown
        theme03ColourList.add("#008eff"); //number
        theme03ColourList.add("#60dd8e"); //heart
        theme03ColourList.add("#17577e"); //freeze
        theme03ColourList.add("#00ff88"); //shield
        theme03ColourList.add("#141163"); //blast
        theme03ColourList.add("#89cff0"); //background

        theme04ColourList = new ArrayList<>();
        theme04ColourList.add("#a5ece7"); //default
        theme04ColourList.add("f092b3"); //countDown
        theme04ColourList.add("#ff00ff"); //number
        theme04ColourList.add("#fb6a8f"); //heart
        theme04ColourList.add("#76d7d6"); //freeze
        theme04ColourList.add("#c4f4dc"); //shield
        theme04ColourList.add("#b899b8"); //blast
        theme04ColourList.add("#f777c9"); //background

        theme05ColourList = new ArrayList<>();
        theme05ColourList.add("#818c3c"); //default
        theme05ColourList.add("727c36"); //countDown
        theme05ColourList.add("#19270d"); //number
        theme05ColourList.add("#50c878"); //heart
        theme05ColourList.add("#72601b"); //freeze
        theme05ColourList.add("#e3cc89"); //shield
        theme05ColourList.add("#8a9a5b"); //blast
        theme05ColourList.add("#9dc183"); //background

        theme06ColourList = new ArrayList<>();
        theme06ColourList.add("#cdaa7d"); //default
        theme06ColourList.add("b39774"); //countDown
        theme06ColourList.add("#8b5a2b"); //number
        theme06ColourList.add("#ffa54f"); //heart
        theme06ColourList.add("#cd8500"); //freeze
        theme06ColourList.add("#dcd7c0"); //shield
        theme06ColourList.add("#bc8f1f"); //blast
        theme06ColourList.add("#deb887"); //background

        theme07ColourList = new ArrayList<>();
        theme07ColourList.add("#d896ff"); //default
        theme07ColourList.add("c488e8"); //countDown
        theme07ColourList.add("#660066"); //number
        theme07ColourList.add("#dc69ff"); //heart
        theme07ColourList.add("#be29ec"); //freeze
        theme07ColourList.add("#c708c7"); //shield
        theme07ColourList.add("#975597"); //blast
        theme07ColourList.add("#efbbff"); //background

        themeS0ColourList = new ArrayList<>();
        themeS0ColourList.add("#3fb254"); //default
        themeS0ColourList.add("359546"); //countDown
        themeS0ColourList.add("#2a6935"); //number
        themeS0ColourList.add("#ff0000"); //heart
        themeS0ColourList.add("#00a8f3"); //freeze
        themeS0ColourList.add("#00ff00"); //shield
        themeS0ColourList.add("#9370db"); //blast
        themeS0ColourList.add("#79d389"); //background

        themeS1ColourList = new ArrayList<>();
        themeS1ColourList.add("#996611"); //default
        themeS1ColourList.add("ff0000"); //countDown
        themeS1ColourList.add("#996611"); //number
        themeS1ColourList.add("#ff0000"); //heart
        themeS1ColourList.add("#00a8f3"); //freeze
        themeS1ColourList.add("#00ff00"); //shield
        themeS1ColourList.add("#9370db"); //blast
        themeS1ColourList.add("#8cfffb"); //background

        colourListList = new ArrayList<>();
        colourListList.add(theme00ColourList);
        colourListList.add(theme01ColourList);
        colourListList.add(theme02ColourList);
        colourListList.add(theme03ColourList);
        colourListList.add(theme04ColourList);
        colourListList.add(theme05ColourList);
        colourListList.add(theme06ColourList);
        colourListList.add(theme07ColourList);

        colourListList.add(themeS0ColourList);
        colourListList.add(themeS1ColourList);

        themeTextList = new ArrayList<>();
        themeTextList.add((TextView)findViewById(R.id.textViewTheme00));
        themeTextList.add((TextView)findViewById(R.id.textViewTheme01));
        themeTextList.add((TextView)findViewById(R.id.textViewTheme02));
        themeTextList.add((TextView)findViewById(R.id.textViewTheme03));
        themeTextList.add((TextView)findViewById(R.id.textViewTheme04));
        themeTextList.add((TextView)findViewById(R.id.textViewTheme05));
        themeTextList.add((TextView)findViewById(R.id.textViewTheme06));
        themeTextList.add((TextView)findViewById(R.id.textViewTheme07));

        themeTextList.add((TextView)findViewById(R.id.textViewThemeS0));
        themeTextList.add((TextView)findViewById(R.id.textViewThemeS1));

        sampleButtonList = new ArrayList<>();
        sampleButtonList.add((ImageButton)findViewById(R.id.button00Theme00));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01Theme00));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02Theme00));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03Theme00));
        sampleButtonList.add((ImageButton)findViewById(R.id.button00Theme01));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01Theme01));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02Theme01));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03Theme01));
        sampleButtonList.add((ImageButton)findViewById(R.id.button00Theme02));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01Theme02));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02Theme02));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03Theme02));
        sampleButtonList.add((ImageButton)findViewById(R.id.button00Theme03));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01Theme03));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02Theme03));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03Theme03));
        sampleButtonList.add((ImageButton)findViewById(R.id.button00Theme04));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01Theme04));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02Theme04));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03Theme04));
        sampleButtonList.add((ImageButton)findViewById(R.id.button00Theme05));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01Theme05));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02Theme05));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03Theme05));
        sampleButtonList.add((ImageButton)findViewById(R.id.button00Theme06));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01Theme06));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02Theme06));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03Theme06));
        sampleButtonList.add((ImageButton)findViewById(R.id.button00Theme07));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01Theme07));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02Theme07));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03Theme07));

        sampleButtonList.add((ImageButton)findViewById(R.id.button00ThemeS0));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01ThemeS0));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02ThemeS0));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03ThemeS0));
        sampleButtonList.add((ImageButton)findViewById(R.id.button00ThemeS1));
        sampleButtonList.add((ImageButton)findViewById(R.id.button01ThemeS1));
        sampleButtonList.add((ImageButton)findViewById(R.id.button02ThemeS1));
        sampleButtonList.add((ImageButton)findViewById(R.id.button03ThemeS1));

        sampleButtonListSpecial = new ArrayList<>();
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00Theme00Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01Theme00Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02Theme00Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03Theme00Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00Theme01Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01Theme01Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02Theme01Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03Theme01Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00Theme02Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01Theme02Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02Theme02Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03Theme02Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00Theme03Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01Theme03Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02Theme03Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03Theme03Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00Theme04Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01Theme04Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02Theme04Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03Theme04Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00Theme05Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01Theme05Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02Theme05Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03Theme05Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00Theme06Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01Theme06Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02Theme06Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03Theme06Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00Theme07Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01Theme07Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02Theme07Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03Theme07Special));

        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00ThemeS0Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01ThemeS0Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02ThemeS0Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03ThemeS0Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button00ThemeS1Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button01ThemeS1Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button02ThemeS1Special));
        sampleButtonListSpecial.add((ImageButton)findViewById(R.id.button03ThemeS1Special));

        priceList = new ArrayList<>();
        priceList.add(0);
        priceList.add(0);
        priceList.add(500);
        priceList.add(750);
        priceList.add(1000);
        priceList.add(2000);
        priceList.add(5000);
        priceList.add(7500);
        priceList.add(10000);
        priceList.add(20000);

        decorate();
    }

    public void decorate() {
        for (int n = 0; n < themeNameList.size(); n++) {
            themeTextList.get(n).setText(themeNameList.get(n));
            themeTextList.get(n).setTextColor(Color.parseColor("#66" + colourListList.get(n).get(2).substring(1)));
            themeButtonSpecialList.get(n).setBackgroundColor(Color.parseColor(colourListList.get(n).get(7)));

            themeButtonList.get(n).setBackgroundColor(Color.parseColor(colourListList.get(n).get(0)));
            themeButtonList.get(n).setPadding(10,10,10,10);

            for (int m = 0; m < 4; m++) {
                sampleButtonListSpecial.get(4*n + m).setBackgroundColor(Color.parseColor(colourListList.get(n).get(0)));
            }

            if (n == 8) {
                sampleButtonList.get(4*n).setImageResource(R.drawable.pool_num01);
                sampleButtonList.get(4*n + 1).setImageResource(R.drawable.pool_num02);
                sampleButtonList.get(4*n + 2).setImageResource(R.drawable.pool_num03);
                sampleButtonList.get(4*n + 3).setImageResource(R.drawable.pool_num04);

                for (int m = 0; m < 4; m++) {
                    sampleButtonListSpecial.get(4*n + m).setImageResource(R.drawable.blank_transparent);
                }
            } else if (n == 9) {
                sampleButtonList.get(4*n).setImageResource(R.drawable.pixel_num01);
                sampleButtonList.get(4*n + 1).setImageResource(R.drawable.pixel_num02);
                sampleButtonList.get(4*n + 2).setImageResource(R.drawable.pixel_num03);
                sampleButtonList.get(4*n + 3).setImageResource(R.drawable.pixel_num04);

                /*sampleButtonListSpecial.get(4*n).setImageResource(R.drawable.pixel_heart);
                sampleButtonListSpecial.get(4*n).setColorFilter(Color.parseColor(colourListList.get(n).get(3)));

                sampleButtonListSpecial.get(4*n + 1).setImageResource(R.drawable.pixel_freeze);
                sampleButtonListSpecial.get(4*n + 1).setColorFilter(Color.parseColor(colourListList.get(n).get(4)));

                sampleButtonListSpecial.get(4*n + 2).setImageResource(R.drawable.pixel_shield);
                sampleButtonListSpecial.get(4*n + 2).setColorFilter(Color.parseColor(colourListList.get(n).get(5)));

                sampleButtonListSpecial.get(4*n + 3).setImageResource(R.drawable.pixel_blast);
                sampleButtonListSpecial.get(4*n + 3).setColorFilter(Color.parseColor(colourListList.get(n).get(6)));*/

                for (int m = 0; m < 4; m++) {
                    sampleButtonListSpecial.get(4*n + m).setBackgroundResource(R.drawable.pixel_block);
                    sampleButtonListSpecial.get(4*n + m).setImageResource(R.drawable.blank_transparent);
                }

            } else {
                for (int m = 0; m < 4; m++) {
                    sampleButtonList.get(4*n + m).setColorFilter(Color.parseColor(colourListList.get(n).get(2)));
                }

                sampleButtonList.get(4*n).setImageResource(R.drawable.num01);
                sampleButtonListSpecial.get(4*n).setImageResource(R.drawable.heart);
                sampleButtonListSpecial.get(4*n).setColorFilter(Color.parseColor(colourListList.get(n).get(3)));

                sampleButtonList.get(4*n + 1).setImageResource(R.drawable.num02);
                sampleButtonListSpecial.get(4*n + 1).setImageResource(R.drawable.timer);
                sampleButtonListSpecial.get(4*n + 1).setColorFilter(Color.parseColor(colourListList.get(n).get(4)));

                sampleButtonList.get(4*n + 2).setImageResource(R.drawable.num03);
                sampleButtonListSpecial.get(4*n + 2).setImageResource(R.drawable.shield);
                sampleButtonListSpecial.get(4*n + 2).setColorFilter(Color.parseColor(colourListList.get(n).get(5)));

                sampleButtonList.get(4*n + 3).setImageResource(R.drawable.num04);
                sampleButtonListSpecial.get(4*n + 3).setImageResource(R.drawable.blast);
                sampleButtonListSpecial.get(4*n + 3).setColorFilter(Color.parseColor(colourListList.get(n).get(6)));
            }
        }

        setPrices();
        updateButtons();

    }

    public void setPrices() {
        for (int n = 0; n < themeButtonList.size(); n++) {
            if (totalPoints < priceList.get(n)) {
                themeButtonList.get(n).setEnabled(false);
                String buttonString = String.valueOf(priceList.get(n)) + getResources().getString(R.string.points);
                themeButtonList.get(n).setText(buttonString);
                themeButtonList.get(n).setBackgroundColor(Color.parseColor("#88ffffff"));

                themeButtonSpecialList.get(n).setBackgroundColor(Color.parseColor("#c3c3c3"));
                themeTextList.get(n).setText("?????");
                themeTextList.get(n).setTextColor(Color.parseColor("#88ffffff"));

                for (int m = 0; m < 4; m++) {
                    sampleButtonList.get(4*n + m).setImageResource(R.drawable.blank);
                    sampleButtonList.get(4*n + m).setColorFilter(Color.parseColor("#dadada"));
                }
            }
        }

    }

    public void updateButtons() {
        SharedPreferences sharedPreferences = getSharedPreferences("theme", Context.MODE_PRIVATE);
        int currentThemeNumber = sharedPreferences.getInt("themeNumber", 0);

        for (int n = 0; n < themeButtonList.size(); n++) {
            if (n == currentThemeNumber) {
                themeButtonList.get(n).setText(getResources().getString(R.string.selected));
            } else if (themeButtonList.get(n).isEnabled()) {
                themeButtonList.get(n).setText(getResources().getString(R.string.select));
            }
        }
    }

    public void onClickThemeButton(View view) {
        int imageButtonIndex = themeButtonIdList.indexOf(view.getId());

        SharedPreferences sharedPreferences = getSharedPreferences("theme", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("defaultColour", colourListList.get(imageButtonIndex).get(0));
        editor.putString("countDownColour", colourListList.get(imageButtonIndex).get(1));
        editor.putString("numberColour", colourListList.get(imageButtonIndex).get(2));
        editor.putString("heartColour", colourListList.get(imageButtonIndex).get(3));
        editor.putString("freezeColour", colourListList.get(imageButtonIndex).get(4));
        editor.putString("shieldColour", colourListList.get(imageButtonIndex).get(5));
        editor.putString("blastColour", colourListList.get(imageButtonIndex).get(6));
        editor.putString("backgroundColour", colourListList.get(imageButtonIndex).get(7));

        editor.putInt("themeNumber", imageButtonIndex);

        if (imageButtonIndex == 8) {
            editor.putString("specialTheme", "pool");
        } else if (imageButtonIndex == 9) {
            editor.putString("specialTheme", "pixel");
        } else {
            editor.putString("specialTheme", "false");
        }

        editor.apply();

        updateButtons();

    }

    public void onClickMainButton(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
