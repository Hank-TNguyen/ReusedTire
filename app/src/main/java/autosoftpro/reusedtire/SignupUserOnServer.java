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
import java.util.UUID;

/**
 * Created by Hung on 6/15/2016.
 */
public class SignupUserOnServer extends AsyncTask<Object, Void, String> {
    public static final String uploadLink = "http://reusedtire.com/androidApp/signupuser.php";
    private Context context;

    public SignupUserOnServer(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        String emailaddress= (String) params[0];
        String password = (String) params[1];
        String uuid = UUID.randomUUID().toString();
        String data;
        String link;
        BufferedReader bfReader;
        String result;

        try{

            data = "?emailaddress=" + URLEncoder.encode(String.valueOf(emailaddress), "UTF-8");
            data += "&password=" + URLEncoder.encode(String.valueOf(password), "UTF-8");
            data += "&id=" + URLEncoder.encode(String.valueOf(uuid), "UTF-8");

            link = uploadLink + data;
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
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("SUCCESS")){
                    Toast.makeText(context, "Successfully signed up.",Toast.LENGTH_SHORT).show();
                } else if (query_result.equals("FAILURE")){
                    Toast.makeText(context, "Sign up have failed.", Toast.LENGTH_SHORT).show();
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
