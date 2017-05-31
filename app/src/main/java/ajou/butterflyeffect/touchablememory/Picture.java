package ajou.butterflyeffect.touchablememory;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class Picture extends AppCompatActivity {

    ViewPager pager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_picture);

        Intent intent = getIntent();
        String index = (String)intent.getExtras().get("index");



        pager = (ViewPager)findViewById(R.id.pager);
        CustomAdapter adapter = new CustomAdapter(getLayoutInflater());
        pager.setAdapter(adapter);

        pager.setCurrentItem(Integer.parseInt(index));


        Button btn_cancel = (Button)findViewById(R.id.picture_btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picture.super.onBackPressed();
            }
        });



    }




}
