package com.sktl.alliance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity2 extends Bar {

    @BindView(R.id.image_view2)
    ImageView mImageView2;

    @BindView(R.id.text_view2)
    TextView mTextView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button4)
    public void toActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }
}
