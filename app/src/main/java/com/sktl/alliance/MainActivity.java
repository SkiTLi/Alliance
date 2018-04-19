package com.sktl.alliance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Bar {


    private AdView mAdView;

    @BindView(R.id.image_view1)
    ImageView mImageView1;

    @BindView(R.id.text_view1)
    TextView mTextView1;

    @BindView(R.id.button22)
    Button mButton22 = mButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        ButterKnife.bind(this);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()

//                .addTestDevice(adRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        mButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPageDeveloper();
            }
        });
    }

    @OnClick(R.id.button1)
    public void toActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
    }

    @Override
    protected void onPause() {
        mAdView.pause();
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        mAdView.destroy();
        super.onDestroy();

    }
}
