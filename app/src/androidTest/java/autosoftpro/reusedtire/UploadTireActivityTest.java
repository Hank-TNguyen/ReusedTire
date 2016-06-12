package autosoftpro.reusedtire;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Hung on 6/2/2016.
 */
public class UploadTireActivityTest extends ActivityInstrumentationTestCase2<UploadTireActivity> {

    public UploadTireActivityTest() {
        super(UploadTireActivity.class);
    }

    public void testActivityExists() {
        UploadTireActivity mUploadTireActivity = getActivity();
        assertNotNull(mUploadTireActivity);
    }

    public void testTire(){

        UploadTireActivity mUploadTireActivity = getActivity();
        final Spinner widthSpinner = (Spinner) mUploadTireActivity.findViewById(R.id.width_p);
        final Spinner ratioSpinner = (Spinner) mUploadTireActivity.findViewById(R.id.ratio_p);
        final Spinner diameterSpinner = (Spinner) mUploadTireActivity.findViewById(R.id.diameter_p);
        final Spinner brandSpinner = (Spinner) mUploadTireActivity.findViewById(R.id.brand_p);
        final Spinner seasonSpinner = (Spinner) mUploadTireActivity.findViewById(R.id.season_p);

        final Button uploadButton = (Button) mUploadTireActivity.findViewById(R.id.upload_tire);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                widthSpinner.requestFocus();
                widthSpinner.setSelection(2);

                ratioSpinner.requestFocus();
                ratioSpinner.setSelection(2);

                diameterSpinner.requestFocus();
                diameterSpinner.setSelection(2);

                brandSpinner.requestFocus();
                brandSpinner.setSelection(2);

                seasonSpinner.requestFocus();
                seasonSpinner.setSelection(0);

                uploadButton.performClick();
            }
        });

    }
}
