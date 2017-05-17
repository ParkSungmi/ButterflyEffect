package ajou.butterflyeffect.touchablememory;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

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

        DisplayMetrics displayMetrics = Gallery.this.getResources().getDisplayMetrics();
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;

        String rootSD = Environment.getExternalStorageDirectory().toString();
        File galleryFolder = new File(rootSD + "/DCIM/TouchableMemory");
        File pictureLists[] = galleryFolder.listFiles();

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();
        gridLayout.setColumnCount(4);

        Toast.makeText(Gallery.this, pictureLists[0].getName(), Toast.LENGTH_LONG).show();

        for(int i=0; i<pictureLists.length; i++){

            ImageView imageView = new ImageView(this);
            imageView.setPadding(5,10,5,10);

            /*
            Resources r = this.getApplicationContext().getResources();
            BitmapDrawable bd = (BitmapDrawable) r.getDrawable(R.drawable.rose);
            Bitmap yourBitmap = bd.getBitmap();
            */

            Bitmap yourBitmap = BitmapFactory.decodeFile(pictureLists[i].getAbsolutePath());


            int bitmapWidth = yourBitmap.getWidth();
            int bitmapHeight = yourBitmap.getHeight();
            int newWidth = (deviceWidth-100)/4;
            int newHeight = bitmapHeight * newWidth / bitmapWidth;


            Bitmap resized = Bitmap.createScaledBitmap(yourBitmap, newWidth, newHeight, true);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(newWidth, newWidth));

            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);


            imageView.setImageBitmap(resized);
            //imageView.setBackgroundColor(Color.BLACK);
            imageView.setTag(Integer.toString(i));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Gallery.this, Picture.class);
                    intent.putExtra("index",(String)v.getTag());

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
