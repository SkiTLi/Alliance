package com.sktl.alliance;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... sUrl) {
        InputStream input = null;
        OutputStream output = null;

        HttpURLConnection connection = null;
        try {
            URL url = new URL(sUrl[0]);
            Log.d("sss", "URL url = new URL(sUrl[0]); url =" + url);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();
//            output = new FileOutputStream("/sdcard/file_name.extension");

            /* Checks if external storage is available for read and write */
            Log.d("sss", "isExternalStorageWritable()=" + isExternalStorageWritable() +
                    ", isExternalStorageReadable()=" +
                    isExternalStorageReadable());
            // проверяем доступность SD
            if (!Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                Log.d("sss", "SD-карта не доступна: " + Environment.getExternalStorageState());
            } else {
                Log.d("sss", "SD-карта доступна: " + Environment.getExternalStorageState());
            }

            File sdPath;
            sdPath = new File(Environment.getExternalStorageDirectory().getPath()
                    + File.separator
                    + "Android/data/"
                    + this.context.getPackageName()
                    + File.separator
                    + "temp");
            // создаем каталог
            Log.d("sss", "sdPath.mkdirs()=" + sdPath.mkdirs());
            // формируем объект File, который содержит путь к файлу
            File sdFile = new File(sdPath, "bonusSktl5.zip");
            // открываем поток для записи
            output = new FileOutputStream(sdFile);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                    publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }
        } catch (Exception e) {
            Log.d("sss", "errorro " + e);
            return e.toString();

        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
                Log.d("sss", "errorro " + ignored);
            }

            if (connection != null)
                connection.disconnect();
        }

        return null;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}