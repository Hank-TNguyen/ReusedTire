package autosoftpro.reusedtire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Hung on 5/23/2016.
 */
public class MainScreenActivity extends Activity {

    private String userId;
    private Button uploadB;
    private ListView postList;
    private postAdapter mPostAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        Intent mIntent = getIntent();
        userId = mIntent.getStringExtra("userId");

        uploadB = (Button) findViewById(R.id.mainscreen_upload_button);
        postList = (ListView) findViewById(R.id.mainscreen_post_list);
    }

    public void uploadTireClickedOnMain(View v){
        Intent mIntent = new Intent(this, UploadTireActivity.class);
        mIntent.putExtra("userId", userId);
        startActivity(mIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        
        mPostAdapter = new postAdapter(this, "");
        postList.setAdapter(mPostAdapter);
        mPostAdapter.notifyDataSetChanged();

    }
}
