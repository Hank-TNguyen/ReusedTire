package autosoftpro.reusedtire;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Hung on 5/29/2016.
 */
public class UploadTireActivity extends Activity {

    private Spinner widthSpinner, ratioSpinner, diameterSpinner, brandSpinner,
            ratingSpinner, seasonSpinner, quantitySpinner;
    private ImageButton imageButton;
    private String mCurrentPhotoPath;
    private String mImageName;
    private GridView mGridView;
    private ArrayList<String> mImgRes = new ArrayList<>();
    private ArrayList<String> mImgNames = new ArrayList<>();
    private String userId;
    public final static int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_new_tire);
        setUpWings();
        Intent mIntent = getIntent();
        userId = mIntent.getStringExtra("userId");
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        // get Images
        Log.i("request, result Code", String.valueOf(requestCode) + " and " + String.valueOf(resultCode));
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Log.i("Image path", mCurrentPhotoPath);
            mImgRes.add(mCurrentPhotoPath);
            mImgNames.add(mImageName);
            String[] imgRes = getImageRes();
            mGridView.setAdapter(new ImageAdapter(this, imgRes));
        }
    }

    @NonNull
    private String[] getImageRes() {
        String[] imgRes = new String[mImgRes.size()];
        imgRes = mImgRes.toArray(imgRes);
        return imgRes;
    }

    @NonNull
    private String[] getImageNames(){
        String[] imgNames = new String[mImgNames.size()];
        imgNames = mImgNames.toArray(imgNames);
        return imgNames;
    }

    private void setUpWings(){
        setUpSeasonSpinner();
        setUpSizeSpinners();
        setUpRatingSpinner();
        setUpBrandSpinner();
        setUpQuantitySpinner();
        imageButton = (ImageButton) findViewById(R.id.tire_image_button);

        mGridView = (GridView) findViewById(R.id.image_views);
        mGridView.setAdapter(new ImageAdapter(this, null));
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

    private void setUpSeasonSpinner(){
        seasonSpinner = (Spinner) findViewById(R.id.season_p);
        ArrayAdapter<CharSequence> seasonSpinnerAdapter
                = ArrayAdapter.createFromResource(this, R.array.season_option,
                android.R.layout.simple_spinner_dropdown_item);
        seasonSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seasonSpinner.setAdapter(seasonSpinnerAdapter);
    }

    private void setUpQuantitySpinner(){
        quantitySpinner = (Spinner) findViewById(R.id.quantity_p);
        ArrayAdapter<CharSequence> quantitySpinnerAdapter
                = ArrayAdapter.createFromResource(this, R.array.quantity_option,
                android.R.layout.simple_spinner_dropdown_item);
        quantitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantitySpinnerAdapter);
    }

    public void uploadTire(View view) throws ExecutionException, InterruptedException, JSONException {
        Button uploadB = (Button) findViewById(R.id.upload_tire);
        uploadB.setClickable(false);

        // get selected data from the wings
        String w = (String) widthSpinner.getSelectedItem();
        String r = (String) ratioSpinner.getSelectedItem();
        String d = (String) diameterSpinner.getSelectedItem();
        String rating = (String) ratingSpinner.getSelectedItem();
        TireBrand tb = TireBrand.valueOf(brandSpinner.getSelectedItem().toString());
        Season s = Season.fromString(seasonSpinner.getSelectedItem().toString());
        String quantity = (String) quantitySpinner.getSelectedItem();

        Toast.makeText(this, "Uploading...", Toast.LENGTH_SHORT).show();

        // upload photos using ftp connection
        new UploadPhotoToServer(this).execute(getImageRes());

        // upload tire information to database, then get tireid
        String uploadTireResult = new UploadTireToServer(this)
                .execute(w, r, d, tb.toString(), s.toString(), rating).get();
        JSONObject mJsonResult = new JSONObject(uploadTireResult);
        String tireId = (String) mJsonResult.get("tireid");

        // upload post to database, then get postid
        UploadPostToServer upts = new UploadPostToServer(this);
        String uploadPostResult = upts.execute(tireId, userId, quantity, "location", "notes").get();
                    //TODO: add location and notes to layout
        mJsonResult = new JSONObject(uploadPostResult);
        String postId = (String) mJsonResult.get("postid");

        // insert image path to database
        for (String subpath : getImageNames()) {
            String path = UploadPhotoToServer.FTP_HOST + UploadPhotoToServer.IMAGE_FOLDER + '/' + subpath;
            new InsertPhotoPathToDatabase(this).execute(postId, path);
        }

        Toast.makeText(this, "Uploaded your post.", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        mImageName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                mImageName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        mImageName = image.getName();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i("IOException occurred", ex.toString());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Log.i("photoFile", photoFile.toString());
                Uri photoURI = FileProvider.getUriForFile(this,
                        "autosoftpro.reusedtire.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                this.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
}
