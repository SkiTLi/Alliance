package com.sktl.alliance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopUp2 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_2);

        ButterKnife.bind(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .98), (int) (height * .9));

    }

    @OnClick(R.id.button9)
    public void Close() {
        this.finish();
    }

    @OnClick(R.id.button10)
    public void LikeAndClose() {
        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);
        this.finish();
    }


}
