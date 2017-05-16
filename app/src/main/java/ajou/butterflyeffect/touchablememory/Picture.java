package ajou.butterflyeffect.touchablememory;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Picture extends AppCompatActivity {

    ViewPager pager;

    String attachmentName = "bitmap";
    String attachmentFileName = "bitmap.bmp";
    String crlf = "\r\n";
    String twoHyphens = "--";
    String boundary =  "*****";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Intent intent = getIntent();
        String a = (String)intent.getExtras().get("index");

        pager = (ViewPager)findViewById(R.id.pager);
        CustomAdapter adapter = new CustomAdapter(getLayoutInflater());
        pager.setAdapter(adapter);

        ImageView img = (ImageView)findViewById(R.id.img_viewpager_childimage) ;
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Resources r = Picture.this.getResources();
                    BitmapDrawable bd = (BitmapDrawable) r.getDrawable(R.drawable.rose);
                    Bitmap bitmap = bd.getBitmap();

                    HttpURLConnection httpURLConnection = null;
                    URL url = new URL("");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setDoOutput(true);

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                    httpURLConnection.setRequestProperty("Cache-Control", "no-cahce");
                    httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+Picture.this.boundary);

                    DataOutputStream request = new DataOutputStream(httpURLConnection.getOutputStream());
                    request.writeBytes(Picture.this.twoHyphens + Picture.this.boundary + Picture.this.crlf);
                    request.writeBytes("Content-Disposition: form-data; name=\"" + Picture.this.attachmentName + "\";filename=\"" + Picture.this.attachmentFileName + "\"" + Picture.this.crlf);
                    request.writeBytes(Picture.this.crlf);


                    byte[] pixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
                    for (int i = 0; i < bitmap.getWidth(); ++i) {
                        for (int j = 0; j < bitmap.getHeight(); ++j) {
                            //we're interested only in the MSB of the first byte,
                            //since the other 3 bytes are identical for B&W images
                            pixels[i + j] = (byte) ((bitmap.getPixel(i, j) & 0x80) >> 7);
                        }
                    }

                    request.write(pixels);



                    request.writeBytes(Picture.this.crlf);
                    request.writeBytes(Picture.this.twoHyphens + Picture.this.boundary + Picture.this.twoHyphens + Picture.this.crlf);

                    request.flush();
                    request.close();


                    InputStream responseStream = new BufferedInputStream(httpURLConnection.getInputStream());

                    BufferedReader responseStreamReader =
                            new BufferedReader(new InputStreamReader(responseStream));

                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((line = responseStreamReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    responseStreamReader.close();

                    String response = stringBuilder.toString();

                    Log.d("dbg>>",response);

                    responseStream.close();

                    httpURLConnection.disconnect();



                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


        Button btn_cancel = (Button)findViewById(R.id.picture_btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picture.super.onBackPressed();
            }
        });


    }




}
