package autosoftpro.reusedtire;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hung on 5/29/2016.
 */
public class UploadTireActivity extends Activity {

    private Spinner widthSpinner, ratioSpinner, diameterSpinner, brandSpinner, ratingSpinner;
    private EditText modelText;
    private String link = "";
    //TODO: link is the database interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_new_tire);
        setUpSizeSpinners();
        setUpBrandSpinner();
        modelText = (EditText) findViewById(R.id.model_edittext);
    }

    private void setUpSizeSpinners(){
        widthSpinner = (Spinner) findViewById(R.id.width_p);
        ratioSpinner = (Spinner) findViewById(R.id.ratio_p);
        diameterSpinner = (Spinner) findViewById(R.id.diameter_p);

        ArrayAdapter<CharSequence> widthSpinnerAdapter
                = ArrayAdapter.createFromResource(this, R.array.width_option,
                android.R.layout.simple_spinner_dropdown_item);
        widthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        widthSpinner.setAdapter(widthSpinnerAdapter);

        ArrayAdapter<CharSequence> ratioSpinnerAdapter
                = ArrayAdapter.createFromResource(this, R.array.ratio_option,
                android.R.layout.simple_spinner_dropdown_item);
        ratioSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratioSpinner.setAdapter(ratioSpinnerAdapter);

        ArrayAdapter<CharSequence> diameterSpinnerAdapter
                = ArrayAdapter.createFromResource(this, R.array.diameter_option,
                android.R.layout.simple_spinner_dropdown_item);
        diameterSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diameterSpinner.setAdapter(widthSpinnerAdapter);
    }

    private void setUpBrandSpinner(){
        brandSpinner = (Spinner) findViewById(R.id.brand_p);

        ArrayAdapter<CharSequence> brandSpinnerAdapter
                = ArrayAdapter.createFromResource(this, R.array.brand_option,
                android.R.layout.simple_spinner_dropdown_item);
        brandSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(brandSpinnerAdapter);
    }

    private void setUpRatingSpinner(){
        ratingSpinner = (Spinner) findViewById(R.id.rating_p);

        ArrayAdapter<CharSequence> ratingSpinnerAdapter
                = ArrayAdapter.createFromResource(this, R.array.rating_option,
                android.R.layout.simple_spinner_dropdown_item);
        ratingSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(ratingSpinnerAdapter);
    }

    public void uploadTire(){
        int w = (int) widthSpinner.getSelectedItem();
        int r = (int) ratioSpinner.getSelectedItem();
        int d = (int) diameterSpinner.getSelectedItem();
        TireBrand tb = TireBrand.valueOf(brandSpinner.getSelectedItem().toString());

        try {
            URL url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


}
