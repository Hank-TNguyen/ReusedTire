package autosoftpro.reusedtire;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Hung on 6/11/2016.
 */
public class UploadTireToServer extends AsyncTask<Object, Void, String> {

    public static final String uploadLink = "http://reusedtire.com/androidApp/uploadtire.php";
    private Context context;

    public UploadTireToServer(Context context){
        this.context = context;
    }
    @Override
    protected String doInBackground(Object... params) {
        String width = (String) params[0];
        String ratio = (String) params[1];
        String diameter = (String) params[2];
        String tb = (String) params[3];

        String data;
        String link;
        BufferedReader bfReader;
        String result;

        try{
            data = "?width=" + URLEncoder.encode(String.valueOf(width), "UTF-8");
            data += "&ratio=" + URLEncoder.encode(String.valueOf(ratio), "UTF-8");
            data += "&diameter=" + URLEncoder.encode(String.valueOf(diameter), "UTF-8");
            data += "&brand=" + URLEncoder.encode(tb.toString(), "UTF-8");

            link = uploadLink + data;
            Log.i("upload data", link);
            //TODO: add the database link
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bfReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bfReader.readLine();
            return result;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String result){
        String jsonStr = result;
        if (jsonStr != null){
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("SUCCESS")){
                    Toast.makeText(context, "Upload succeeded.",Toast.LENGTH_SHORT).show();
                } else if (query_result.equals("FAILURE")){
                    Toast.makeText(context, "Upload failed.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Error msg", query_result);
                    Toast.makeText(context, "Couldn't connect to remote database", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
