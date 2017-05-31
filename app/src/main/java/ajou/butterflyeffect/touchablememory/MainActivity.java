package ajou.butterflyeffect.touchablememory;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn_select;
    Button btn_regist;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        btn_select = (Button) findViewById(R.id.btn_select);
        btn_regist = (Button) findViewById(R.id.btn_regist);

        btn_select.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                //tts.speak("갤러리 화면 로딩중입니다.", TextToSpeech.QUEUE_FLUSH, null);

                Intent intent = new Intent(MainActivity.this, Gallery.class);
                startActivity(intent);


            }
        });


        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("기기를 등록하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Regist.class);
                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



    }




}
