package xyz.viseator.v2ex.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by viseator on 2016/11/19.
 */

public class GetAvatarTask extends AsyncTask<String, Void, Bitmap> {

    public interface AsyncResponse {
        void processFinish(Bitmap bitmap);
    }

    private AsyncResponse delegate = null;
    private ImageView imageView = null;
    private final static String TAG = "wudi GetAvatarTask";

    public GetAvatarTask(ImageView imageView) {
        this.imageView = imageView;
    }

    public GetAvatarTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url;
        if (imageView != null) {
            url = "http:" + strings[0];
        } else {
            url = strings[0];
        }
        Bitmap bitmap;
        try {
            URL url1 = new URL(url);
            InputStream inputStream;
            if (url.charAt(4) != 's') {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                inputStream = httpURLConnection.getInputStream();
            } else {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url1.openConnection();
                inputStream = httpsURLConnection.getInputStream();
            }
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        } catch (IOException e) {

            Log.d(TAG, "URL error:" + url);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageView != null) imageView.setImageBitmap(bitmap);
        if (delegate != null) {
            Log.d(TAG, "processFinish");
            delegate.processFinish(bitmap);
        }
    }


}
