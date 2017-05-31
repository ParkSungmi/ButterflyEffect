package ajou.butterflyeffect.touchablememory;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tpdlq on 2017-05-16.
 */

public class HttpUtil2 extends AsyncTask<String, Void, String> {

    @Override
    public String doInBackground(String... params) {

        String urlStr = params[0];
        String msg = params[1];

        String boundary = "*****";

        String response = null;

        try {
            HttpURLConnection httpURLConnection = null;
            URL url = new URL(urlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true);


            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            DataOutputStream request = new DataOutputStream(httpURLConnection.getOutputStream());


            request.writeBytes(msg);

            request.flush();
            request.close();

            int code = httpURLConnection.getResponseCode();


            if(code == httpURLConnection.HTTP_OK) {
                InputStream responseStream = new BufferedInputStream(httpURLConnection.getInputStream());

                BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));

                String line = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = responseStreamReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                responseStreamReader.close();

                response = stringBuilder.toString();



                responseStream.close();

            }

            httpURLConnection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}


