package com.sktl.alliance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Bar {

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

}
