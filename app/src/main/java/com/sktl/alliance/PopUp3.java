package com.sktl.alliance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;


import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopUp3 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_3);

        ButterKnife.bind(this);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .98), (int) (height * .9));

    }


    @OnClick(R.id.button12)
    public void LikeAndClose() {
        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);

        this.finish();
    }


}
