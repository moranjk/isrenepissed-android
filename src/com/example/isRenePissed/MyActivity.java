package com.example.isRenePissed;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MyActivity extends Activity
{
    protected TextView isHeTxt;
    protected Button isHeBtn;
    protected LinearLayout layout;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        isHeBtn = (Button)findViewById(R.id.isHeButton);
        isHeTxt = (TextView)findViewById(R.id.isHeText);
        layout = (LinearLayout)findViewById(R.id.layout);

        isHeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Pissed>() {
                    @Override
                    protected Pissed doInBackground(Void... voids) {

                        return new RestClient().getPissed();
                    }

                    @Override
                    protected void onPostExecute(Pissed pissed) {
                        super.onPostExecute(pissed);
                        Bitmap img;
                        if ((img = pissed.getImg()) != null) {
                            MyActivity.this.layout.setBackground(new BitmapDrawable(img));
                        }
                        MyActivity.this.isHeTxt.setText(pissed.getMessage());
                    }
                }.execute();
            }
        });
    }
}
