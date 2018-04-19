package com.sktl.alliance;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.InterstitialAd;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Thread.sleep;

public class Activity3 extends Bar {

    private AdView mAdView;

    @BindView(R.id.image_view3)
    ImageView mImageView3;

    @BindView(R.id.text_view1)
    TextView mTextView1;

    @BindView(R.id.button22)
    Button mButton22 = mButton2;

    @BindView(R.id.button3)
    Button mButton3;


    private Button mButton10;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        ButterKnife.bind(this);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPageDeveloper();
            }
        });

        // instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("A message");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);


    }

    @OnClick(R.id.button3)
    public void downloadAndToPopUp2() {
        String filename = "/bonusSktl.zip";
        String urlString = "http://best-of-best.narod.ru/BOOKFORMAT/EXAMPLE/ZIP.zip";
//        String urlString = "http://zaycev.net/download/packie/913835a087debe0686ff27434fde3d22/8845";
//        String urlString = "http://media.comicbook.com/2017/12/marvel-2017-comicbookcom-1070526-1280x0.jpeg";


        String pathToZip = Environment.getExternalStorageDirectory().getPath()
                + File.separator
                + "Android/data/"
                + this.getPackageName()
                + File.separator
                + "temp";
        String zipName = "bonusSktl5.zip";
        String pathToUnZip = Environment.getExternalStorageDirectory().getPath()
                + File.separator
                + "Android/data/"
                + this.getPackageName()
                + File.separator
                + "unZip";

        download2(filename, urlString);

        File unZipDir = new File(pathToUnZip);
        unZipDir.mkdir();

        //задержка небольшая
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        unpackZip(pathToZip + File.separator, zipName, pathToUnZip + File.separator);

        Log.d("sss", "deleteZip=" + deleteZip(pathToZip, zipName));

        Intent intent = new Intent(this, PopUp2.class);
        startActivity(intent);

    }

    public void download2(final String filename, final String urlString) {
        //разрешение
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        // execute this when the downloader must be fired
        final DownloadTask downloadTask = new DownloadTask(this);
        downloadTask.execute(urlString);


        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                downloadTask.cancel(true);
            }
        });

    }

    //распаковываем zip
    private boolean unpackZip(String pathToZip, String zipName, String pathToUnZip) {
        InputStream is;
        ZipInputStream zis;
        try {
            is = new FileInputStream(pathToZip + zipName);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;

            while ((ze = zis.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count;

                String filename = ze.getName();
                FileOutputStream fout = new FileOutputStream(pathToUnZip + filename);

                // reading and writing
                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                    byte[] bytes = baos.toByteArray();
                    fout.write(bytes);
                    baos.reset();
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        } catch (IOException e) {
            Log.d("sss", "errorr" + e);
            return false;
        }

        return true;
    }

    private boolean deleteZip(String pathToZip, String zipName) {
        File file = new File(pathToZip + File.separator + zipName);
        return file.delete();
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
