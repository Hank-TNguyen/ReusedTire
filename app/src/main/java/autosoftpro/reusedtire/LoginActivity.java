package autosoftpro.reusedtire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hung on 5/29/2016.
 */
public class LoginActivity extends Activity {

    private EditText usernameText, passwordText;
    private Button loginB, signupB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        setContentView(R.layout.login_screen);

        // set up wings
        usernameText = (EditText) findViewById(R.id.login_username);
        passwordText = (EditText) findViewById(R.id.login_password);
        loginB = (Button) findViewById(R.id.login_button);
        signupB = (Button) findViewById(R.id.login_signup);
    }

    public void login(View v) {
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        UserController.searchUserOnServer suos = (UserController.searchUserOnServer)
                new UserController.searchUserOnServer(this).execute(username, password);

    }

    public void signup(View v) {
        Intent signupActivity = new Intent(this, SignupActivity.class);
        startActivity(signupActivity);
    }
}

