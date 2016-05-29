package autosoftpro.reusedtire;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hung on 5/29/2016.
 */
public class UploadTireActivity extends Activity {

    private Spinner widthSpinner, ratioSpinner, diameterSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_new_tire);
        setUpSizeSpinners();
    }

    private void setUpSizeSpinners(){
        widthSpinner = (Spinner) findViewById(R.id.width_p);
        ratioSpinner = (Spinner) findViewById(R.id.ratio_p);
        diameterSpinner = (Spinner) findViewById(R.id.diameter_p);

        ArrayAdapter<CharSequence> widthSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.width_option,
                android.R.layout.simple_spinner_dropdown_item);
        widthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        widthSpinner.setAdapter(widthSpinnerAdapter);

        ArrayAdapter<CharSequence> ratioSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.ratio_option,
                android.R.layout.simple_spinner_dropdown_item);
        ratioSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratioSpinner.setAdapter(ratioSpinnerAdapter);

        ArrayAdapter<CharSequence> diameterSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.diameter_option,
                android.R.layout.simple_spinner_dropdown_item);
        diameterSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diameterSpinner.setAdapter(widthSpinnerAdapter);
    }
}
