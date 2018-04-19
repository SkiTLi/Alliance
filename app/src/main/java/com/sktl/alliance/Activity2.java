package com.sktl.alliance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.ads.InterstitialAd;
public class Activity2 extends Bar {

    private AdView mAdView;
    InterstitialAd mInterstitialAd;

    @BindView(R.id.image_view2)
    ImageView mImageView2;

    @BindView(R.id.text_view2)
    TextView mTextView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        ButterKnife.bind(this);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    @OnClick(R.id.button4)
    public void toActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
        mInterstitialAd.show();
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
