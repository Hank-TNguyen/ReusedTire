package autosoftpro.reusedtire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Hung on 5/23/2016.
 */
public class MainScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        Intent upload = new Intent(this, UploadTireActivity.class);
        startActivity(upload);
    }
}
