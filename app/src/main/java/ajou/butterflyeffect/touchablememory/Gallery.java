package ajou.butterflyeffect.touchablememory;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

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



            GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.Spec colSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);


            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(rowSpan, colSpan);
            gridLayout.addView(imageView, gridParam);



        }



    }
}
