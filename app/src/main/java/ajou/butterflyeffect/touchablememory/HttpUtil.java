package ajou.butterflyeffect.touchablememory;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tpdlq on 2017-05-16.
 */

public class HttpUtil extends AsyncTask<String, Void, Void> {

    @Override
    public Void doInBackground(String... params) {

        String pictureName = params[0];
        String picturePath = params[1];
        String urlStr = params[2];

        String boundary = "*****";

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


            // String param = URLEncoder.encode("aaaaaaa","UTF-8");
            //request.writeBytes("aaaaa");

            //request.writeBytes(twoHyphens + boundary + crlf);
            //request.writeBytes("Content-Disposition: form-data; test=\"" + attachmentName + "\";filename=\"" + attachmentFileName + "\"" + crlf);
            //request.writeBytes(crlf);


            //String cameraTempFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/5.jpg";

            File file = new File(picturePath);



            int size = (int) file.length();


            byte[] bytes = new byte[size];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                buf.read(bytes, 0, bytes.length);
                buf.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Log.d("dbgaa>>",bytes.length+"");


            request.write(bytes, 0, bytes.length);
            request.writeBytes("ok");


            request.flush();
            request.close();

            int code = httpURLConnection.getResponseCode();

            Log.d("code : "+code,"");

            if(code == httpURLConnection.HTTP_OK) {
                InputStream responseStream = new BufferedInputStream(httpURLConnection.getInputStream());

                BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));

                String line = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = responseStreamReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                responseStreamReader.close();

                String response = stringBuilder.toString();

                Log.d("dbg>>", response);

                responseStream.close();


                SystemClock.sleep(4000);

                Log.d("dbgaaaaaa>>","start");

                HttpURLConnection httpURLConnection2 = null;
                URL url2 = new URL("http://52.79.156.99:8080/doit");
                httpURLConnection2 = (HttpURLConnection) url2.openConnection();
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setDoOutput(true);


                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection2.setRequestProperty("Cache-Control", "no-cache");
                httpURLConnection2.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "********");

                DataOutputStream request2 = new DataOutputStream(httpURLConnection2.getOutputStream());
                request2.writeBytes("finish");
                request2.flush();
                request2.close();

                int code2 = httpURLConnection2.getResponseCode();
                if (code2 == httpURLConnection2.HTTP_OK) {
                    InputStream responseStream2 = new BufferedInputStream(httpURLConnection2.getInputStream());

                    BufferedReader responseStreamReader2 = new BufferedReader(new InputStreamReader(responseStream2));

                    String line2 = "";
                    StringBuilder stringBuilder2 = new StringBuilder();

                    while ((line2 = responseStreamReader2.readLine()) != null) {
                        stringBuilder2.append(line2).append("\n");
                    }
                    responseStreamReader2.close();

                    String response2 = stringBuilder2.toString();

                    Log.d("dbg222>>", response2);

                    responseStream2.close();
                }


                httpURLConnection2.disconnect();






            }

            httpURLConnection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }



}


