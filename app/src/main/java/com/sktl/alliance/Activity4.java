package com.sktl.alliance;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity4 extends Bar {

    @BindView(R.id.image_view4)
    ImageView mImageView4;

    @BindView(R.id.text_view1)
    TextView mTextView1;

    @BindView(R.id.button11)
    Button mButton11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4);

        ButterKnife.bind(this);
    }
}
