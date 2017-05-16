package ajou.butterflyeffect.touchablememory;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Button btn_cancel = (Button) findViewById(R.id.gallery_btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gallery.super.onBackPressed();
            }
        });


        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();
        gridLayout.setColumnCount(4);


        for(int i=0; i<30; i++){

            ImageView imageView = new ImageView(this);
            imageView.setPadding(5,10,5,10);

            Resources r = this.getApplicationContext().getResources();
            BitmapDrawable bd = (BitmapDrawable) r.getDrawable(R.drawable.rose);
            Bitmap yourBitmap = bd.getBitmap();


            Bitmap resized = Bitmap.createScaledBitmap(yourBitmap, 250, 180, true);

            imageView.setImageBitmap(resized);
            //imageView.setBackgroundColor(Color.BLACK);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
            imageView.setTag(i);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Gallery.this, Picture.class);

                    intent.putExtra("index",Integer.toString((int)v.getTag()));
                    startActivity(intent);
                }
            });


            GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.Spec colSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);


            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(rowSpan, colSpan);
            gridLayout.addView(imageView, gridParam);



        }



    }
}
