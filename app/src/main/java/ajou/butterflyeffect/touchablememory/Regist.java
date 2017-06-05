package ajou.butterflyeffect.touchablememory;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Regist extends AppCompatActivity {

    Button btn_regist;
    EditText regist_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);


        btn_regist = (Button)findViewById(R.id.btn_regist);
        regist_text = (EditText)findViewById(R.id.regist_text);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text="";
                try{
                    text = regist_text.getText().toString();
                    int number = Integer.parseInt(text);

                }
                catch (Exception e){
                    Toast.makeText(Regist.this, "wrong format", Toast.LENGTH_SHORT).show();
                }

                try {
                    String response = new HttpUtil2().execute("http://52.79.156.99:8080/register", text).get();

                    Log.d("aaaaaaaaaaa>> ",response);


                    if (response.equals("ok\n")) {
                        try {
                            File root = new File(Environment.getExternalStorageDirectory(), "TouchableMemory");
                            //File root = Environment.getExternalStorageDirectory();
                            if (!root.exists()) {
                                root.mkdirs();
                            }

                            File gpxfile = new File(root+ "/id.txt");

                            if(!gpxfile.exists()){
                                gpxfile.createNewFile();
                            }

                            FileWriter writer = new FileWriter(gpxfile);
                            writer.write(text);
                            writer.flush();
                            writer.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Regist.super.onBackPressed();
                    } else if (response.equals("false\n")) {
                        Toast.makeText(Regist.this, "fail to regist", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Regist.this, "error", Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }
}
