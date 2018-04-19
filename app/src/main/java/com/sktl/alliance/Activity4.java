package com.sktl.alliance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity4 extends Bar {

    @BindView(R.id.image_view4)
    ImageView mImageView4;

    @BindView(R.id.text_view1)
    TextView mTextView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.button11)
    public void toActivity3() {
        Intent intent = new Intent(this, PopUp3.class);
        startActivity(intent);
    }
}
