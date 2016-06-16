package autosoftpro.reusedtire;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class SignupActivity extends Activity {

    private EditText emailText, passwordText, confirmPasswordText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        setContentView(R.layout.signup_screen);

        // set up wings
        sendButton = (Button) findViewById(R.id.signup_sendquery);
        emailText = (EditText) findViewById(R.id.signup_username);
        passwordText = (EditText) findViewById(R.id.signup_password);
        confirmPasswordText = (EditText) findViewById(R.id.signup_password_confirm);
    }

    public void sendQuery(View v){
        String pass = passwordText.getText().toString();
        String confirmPass = confirmPasswordText.getText().toString();
        String emailaddress = emailText.getText().toString();
        final String[] queryResult = {null};

        final String[] result = {null};
        if (passwordIsMatched(pass, confirmPass) && !passwordContainQuotationMark(pass)){
            Toast.makeText(this, "Signing up...", Toast.LENGTH_SHORT).show();

            // call asynctask class
            SignupUserOnServer suos = (SignupUserOnServer) new
                    SignupUserOnServer(this).execute(emailaddress, pass);
        }
    }

    private Boolean passwordIsMatched(String pass, String confirmPass){
        if (pass.equals(confirmPass))
            return true;
        else {
            Toast.makeText(this, "Your password is not matched", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private Boolean passwordContainQuotationMark(String pass){
        if (pass.indexOf("\'") >= 0 || pass.indexOf("\"") >= 0){
            Toast.makeText(this, "Password cannot contain \" or \'", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return false;
    }
}
