package autosoftpro.reusedtire;

import android.content.Context;
import android.os.AsyncTask;
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
import java.util.PriorityQueue;

/**
 * Created by Hung on 6/11/2016.
 */
public class UploadTireToServer extends AsyncTask<Object, Void, String> {

    private Context context;

    public UploadTireToServer(Context context){
        this.context = context;
    }
    @Override
    protected String doInBackground(Object... params) {
        int width = (int) params[0];
        int ratio = (int) params[1];
        int diameter = (int) params[2];
        TireBrand tb = (TireBrand) params[3];

        String data;
        String link;
        BufferedReader bfReader;
        String result;

        try{
            data = "?width=" + URLEncoder.encode(String.valueOf(width), "UTF-8");
            data += "&ratio" + URLEncoder.encode(String.valueOf(ratio), "UTF-8");
            data += "&diameter" + URLEncoder.encode(String.valueOf(diameter), "UTF-8");
            data += "&brand" + URLEncoder.encode(tb.toString(), "UTF-8");

            link = "" + data;
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
                } else if (query_result.equals("Failure")){
                    Toast.makeText(context, "Upload failed.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Couldn't connect to remote database", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
