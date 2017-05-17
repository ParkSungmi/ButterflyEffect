package ajou.butterflyeffect.touchablememory;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
 * Created by tpdlq on 2017-05-15.
 */

public class CustomAdapter extends PagerAdapter {

    LayoutInflater inflater;
    File pictureLists[];

    public CustomAdapter(LayoutInflater inflater) {
        this.inflater = inflater;

        String rootSD = Environment.getExternalStorageDirectory().toString();
        File galleryFolder = new File(rootSD + "/DCIM/TouchableMemory");
        pictureLists = galleryFolder.listFiles();

    }


    @Override
    public int getCount() {
        return pictureLists.length;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = null;
        view = inflater.inflate(R.layout.viewpager_childview, null);
        ImageView img = (ImageView) view.findViewById(R.id.img_viewpager_childimage);


        Bitmap yourBitmap = BitmapFactory.decodeFile(pictureLists[position].getAbsolutePath());
        img.setImageBitmap(yourBitmap);

        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Toast.makeText(container.getContext(), cameraTempFilePath, Toast.LENGTH_LONG).show();
                Toast.makeText(container.getContext(), "Send Picture", Toast.LENGTH_LONG).show();
                new HttpUtil().execute(pictureLists[position].getName(), pictureLists[position].getAbsolutePath());
                return false;
            }
        }) ;

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}

/*
    public class HttpUtil2 extends AsyncTask<Resources, Void, Void> {

        @Override
        public Void doInBackground(Resources... params) {

            //InputStream is = params[0].openRawResource(R.raw.rose);


            String attachmentName = "rose";
            String attachmentFileName = "rose.jpg";
            String crlf = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";

            try {
                HttpURLConnection httpURLConnection = null;
                URL url = new URL("http://52.79.156.99:8080/upload");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoOutput(true);


                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
                httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                //httpURLConnection.setRequestProperty("id", "image");

                DataOutputStream request = new DataOutputStream(httpURLConnection.getOutputStream());


               // String param = URLEncoder.encode("aaaaaaa","UTF-8");
                //request.writeBytes("aaaaa");




                //request.writeBytes(twoHyphens + boundary + crlf);
                //request.writeBytes("Content-Disposition: form-data; test=\"" + attachmentName + "\";filename=\"" + attachmentFileName + "\"" + crlf);
                //request.writeBytes(crlf);





                String cameraTempFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/5.jpg";

                File file = new File(cameraTempFilePath);



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
                //request.writeBytes("ok");


                /*

                 ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] data = new byte[16384];

                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                buffer.flush();
*/
                //data = buffer.toByteArray();
/*
                    byte[] pixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
                    for (int i = 0; i < bitmap.getWidth(); ++i) {
                        for (int j = 0; j < bitmap.getHeight(); ++j) {
                            //we're interested only in the MSB of the first byte,
                            //since the other 3 bytes are identical for B&W images
                            pixels[i + j] = (byte) ((bitmap.getPixel(i, j) & 0x80) >> 7);
                        }
                    }
*/
                //request.write(pixels);

                //request.write(buffer.toByteArray());
                //request.write(bytes);
                //request.writeBytes(crlf);
                //request.writeBytes(twoHyphens + boundary + twoHyphens + crlf);

/*
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
                }

                httpURLConnection.disconnect();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }
*/
/*

    }


}

*/



