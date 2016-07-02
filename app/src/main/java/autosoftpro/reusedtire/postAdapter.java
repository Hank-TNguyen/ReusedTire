package autosoftpro.reusedtire;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Hung on 7/1/2016.
 */
public class postAdapter extends ArrayAdapter<String> {
    public postAdapter(Context context, ArrayList<String> jsonPost) {
        super(context, 0, jsonPost);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        String jsonPost =getItem(position);
        try {
            JSONObject mPost = new JSONObject(jsonPost);
            String brand = mPost.getString("brand");
            String width = "Width: " + mPost.getString("width");
            String ratio = "Ratio: " + mPost.getString("ratio");
            String diameter = "Diameter: " + mPost.getString("diameter");

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.post_adapter, parent, false);
            }

            TextView tireBrand = (TextView) convertView.findViewById(R.id.post_brand);
            TextView tireDiameter = (TextView) convertView.findViewById(R.id.post_diameter);
            TextView tireWidth = (TextView)convertView.findViewById(R.id.post_width);
            TextView tireRatio = (TextView)convertView.findViewById(R.id.post_ratio);

            tireBrand.setText(brand);
            tireDiameter.setText(diameter);
            tireRatio.setText(ratio);
            tireWidth.setText(width);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}

