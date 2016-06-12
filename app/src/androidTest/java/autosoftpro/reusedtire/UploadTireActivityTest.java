package autosoftpro.reusedtire;

import android.test.ActivityInstrumentationTestCase2;
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

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                widthSpinner.requestFocus();
            }
        });


    }
}
