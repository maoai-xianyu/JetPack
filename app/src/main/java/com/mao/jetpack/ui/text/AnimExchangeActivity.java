package com.mao.jetpack.ui.text;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

public class AnimExchangeActivity extends Activity {
    private Button btnEx;
    private LayoutInflater inflater;
    private RelativeLayout myFirst, mySecond, layoutOne, layoutTwo;
    private boolean TAG_firstLayoutTop;

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        btnEx = (Button) findViewById(R.id.button_exchange);
        btnEx.setOnClickListener(new BtnExOnClickListener());
        inflater = getLayoutInflater();
        TAG_firstLayoutTop = true;
        //init layoutOne
        myFirst = (RelativeLayout) inflater.inflate(R.layout.layout_first, null).findViewById(R.id.myFirst);
        layoutOne = (RelativeLayout) findViewById(R.id.LayoutOne);
        layoutOne.removeAllViews();
        layoutOne.addView(myFirst);
        //init layoutTwo
        mySecond = (RelativeLayout) inflater.inflate(R.layout.layout_second, null).findViewById(R.id.mySecond);
        layoutTwo = (RelativeLayout) findViewById(R.id.LayoutTwo);
        layoutTwo.removeAllViews();
        layoutTwo.addView(mySecond);
    }

    public class BtnExOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(getBaseContext(), "exchange!", Toast.LENGTH_SHORT).show();
            if (TAG_firstLayoutTop) {
                //move upward and downward 300
                ObjectAnimator.ofFloat(layoutTwo, "TranslationY", -300).setDuration(1000).start();
                ObjectAnimator.ofFloat(layoutOne, "TranslationY", 300).setDuration(1000).start();
                TAG_firstLayoutTop = false;
            } else { //back to normal position
                ObjectAnimator.ofFloat(layoutOne, "TranslationY", 0).setDuration(1000).start();
                ObjectAnimator.ofFloat(layoutTwo, "TranslationY", 0).setDuration(1000).start();
                TAG_firstLayoutTop = true;
            }
        }
    }*/
}

