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
 * Created by Hung on 6/30/2016.
 */
public class UploadPostToServer extends AsyncTask<Object, Void, String> {

    public static final String uploadPostLink = mLink.hostingLink + "uploadpost.php";
    private Context context;

    public UploadPostToServer(Context context){
        this.context = context;
    }
    @Override
    protected String doInBackground(Object... params) {
        String tireId = (String) params[0];
        String userId = (String) params[1];
        String quantity = (String) params[2];
        String location = (String) params[3];
        String notes = (String) params[4];

        String data;
        String link;
        BufferedReader bfReader;
        String result;

        try{
            data = "?tireId=" + URLEncoder.encode(String.valueOf(tireId), "UTF-8");
            data += "&userId=" + URLEncoder.encode(String.valueOf(userId), "UTF-8");
            data += "&quantity=" + URLEncoder.encode(String.valueOf(quantity), "UTF-8");
            data += "&location=" + URLEncoder.encode(String.valueOf(location), "UTF-8");
            data += "&notes=" + URLEncoder.encode(String.valueOf(notes), "UTF-8");

            link = uploadPostLink + data;
            Log.i("upload data", link);

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
                String query_result = jsonObj.getString(mLink.TAG_QUERY_RESULT);
                if (query_result.equals("SUCCESS")){
                    Toast.makeText(context, "Upload succeeded.", Toast.LENGTH_SHORT).show();
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
