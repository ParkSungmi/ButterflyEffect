package ajou.butterflyeffect.touchablememory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                try{
                    String text = regist_text.getText().toString();
                    int number = Integer.parseInt(text);

                    String response  = new HttpUtil2().execute("http://52.79.156.99:8080/regist", text).get();

                    if(response.equals("ok")){



                        Regist.super.onBackPressed();
                    }
                    else if(response.equals("fail")){
                        Toast.makeText(Regist.this, "fail to regist", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(Regist.this, "error", Toast.LENGTH_LONG).show();
                    }




                }
                catch (Exception e){
                    Toast.makeText(Regist.this, "wrong format", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
