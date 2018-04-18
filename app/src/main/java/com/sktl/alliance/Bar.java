package com.sktl.alliance;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class Bar extends AppCompatActivity {

    @BindView(R.id.button2)
    Button mButton2;

    @OnClick(R.id.button2)
    public void goToPageDeveloper() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.developer_page)));
        startActivity(intent);
    }

    @OnClick(R.id.button5)
    public void share() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Cool App");
        sendIntent.putExtra(Intent.EXTRA_TITLE, "ссылка на приложение");
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_link));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share with"));
    }

    @OnClick(R.id.button6)
    public void vote() {
        Intent intent = new Intent(this, PopUp1.class);
        startActivity(intent);
    }

}
