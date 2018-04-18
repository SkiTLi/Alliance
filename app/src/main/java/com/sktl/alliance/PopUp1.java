package com.sktl.alliance;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopUp1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_1);

        ButterKnife.bind(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .98), (int) (height * .9));
    }

    @OnClick(R.id.button7)
    public void DislikeAndClose() {
        this.finish();
    }

    @OnClick(R.id.button8)
    public void LikeAndClose() {
        this.finish();
    }

}
