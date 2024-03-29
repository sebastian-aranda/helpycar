package cl.osare.helpycar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    Context context;
    ProgressBar progressBar;
    ImageView imageView;

    int height, width;

    public DownloadImageTask(Context context, ProgressBar progressBar, ImageView imageView, int height, int width) {
        this.context = context;
        this.progressBar = progressBar;
        this.imageView = imageView;

        this.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, context.getResources().getDisplayMetrics());
        this.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, context.getResources().getDisplayMetrics());
    }

    protected Bitmap doInBackground(String... urls) {

        String urldisplay = urls[0];
        Bitmap icon = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            icon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return icon;
    }

    protected void onPostExecute(Bitmap result) {
        progressBar.setVisibility(View.GONE);

        if (result != null){
            imageView.setImageBitmap(result);
            imageView.getLayoutParams().height = this.height;
            imageView.getLayoutParams().width = this.width;
            imageView.requestLayout();
        }
        else{
            imageView.setVisibility(View.GONE);
        }

    }
}
