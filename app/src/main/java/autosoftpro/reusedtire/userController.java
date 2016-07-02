package autosoftpro.reusedtire;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import com.google.gson.Gson;

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
 * Created by Hung on 6/17/2016.
 */
public class UserController {
    /**
     * Created by Hung on 6/15/2016.
     */

    public static class SignupUserOnServer extends AsyncTask<Object, Void, String> {
        public static final String uploadLink = mLink.hostingLink + "signupuser.php";
        private final Context context;

        public SignupUserOnServer(Context context){
            this.context = context;
        }

        @Override
        protected String doInBackground(Object... params) {
            String emailaddress= (String) params[0];
            String password = (String) params[1];
            String data;
            String link;
            BufferedReader bfReader;
            String result;

            try{

                data = "?emailaddress=" + URLEncoder.encode(String.valueOf(emailaddress), "UTF-8");
                data += "&password=" + URLEncoder.encode(String.valueOf(password), "UTF-8");

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

        @Override
        protected void onPostExecute(String result){
            String jsonStr = result;
            SignupActivity mAct = (SignupActivity) this.context;
            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String query_result = jsonObj.getString("query_result");
                    if (query_result.equals("SUCCESS")){
                        Toast.makeText(context, "Successfully signed up.", Toast.LENGTH_SHORT).show();
                        mAct.finish();
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
            super.onPostExecute(result);
        }
    }

    /**
     * Created by Hung on 6/17/2016.
     */
    public static class searchUserOnServer extends AsyncTask<Object, Void, String> {
        private String searchLink = mLink.hostingLink + "searchuser.php";
        private Context context;
        private String emailaddress;
        private String password;

        public searchUserOnServer(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(this.context, "Signing you in...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(Object... params) {
            emailaddress = (String) params[0];
            password = (String) params[1];
            BufferedReader bfReader;
            String data;
            String link;
            String result;

            try{
                data = "?emailaddress=" + URLEncoder.encode(String.valueOf(emailaddress), "UTF-8");

                link = searchLink + data;
                Log.i("Search up user", link);
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
            LoginActivity mAct = (LoginActivity) this.context;
            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String query_result = jsonObj.getString(mLink.TAG_QUERY_RESULT);
                    if (query_result.equals("SUCCESS")){
                        // Query runs through, process the data
                        if (jsonObj.getString(mLink.TAG_MESSAGE).equals(mLink.TAG_USER_NOT_FOUND)){
                            Toast.makeText(context, mLink.TAG_USER_NOT_FOUND, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.i("database message",jsonObj.getString(mLink.TAG_MESSAGE));
                            // User found
                            User mUser = processUser(jsonObj.getString(mLink.TAG_USER));
                            // compare password
                            Log.i("password on server", mUser.getPassword());
                            if (mUser.getPassword().equals(password)){
                                Intent signedIn = new Intent(mAct, MainScreenActivity.class);
                                signedIn.putExtra("userId", mUser.getId());
                                Log.i("userId in Login",String.valueOf(mUser.getId()));
                                mAct.startActivity(signedIn);
                            } else {
                                Toast.makeText(context, "Wrong password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    // cannot connect
                    else if (query_result.equals("FAILURE")){
                        Toast.makeText(context, "Couldn't connect to remote database", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("Error msg", query_result);
                        Toast.makeText(context, "Couldn't connect to remote database", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        private User processUser(String user){
            Gson gson = new Gson();
            User mUser = gson.fromJson(user, User.class);
            Log.i("user on server", mUser.toString());
            return mUser;
        }
    }
}
