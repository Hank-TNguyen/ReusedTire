package autosoftpro.reusedtire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Hung on 6/22/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    // references to our images
    private String[] mImgRes = {};

    public ImageAdapter(Context c, String[] ImgRes) {
        mContext = c;
        mImgRes = ImgRes;
    }


    public int getCount() {
        try {
            return mImgRes.length;
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        File imgFile = new File(mImgRes[position]);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }
        return imageView;
    }
}
