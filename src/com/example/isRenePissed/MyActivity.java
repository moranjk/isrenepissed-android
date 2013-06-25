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
                new AsyncTask<Void, Void, JSONObject>() {
                    @Override
                    protected JSONObject doInBackground(Void... voids) {

                        return new RestClient().getIsPissed();
                    }

                    @Override
                    protected void onPostExecute(JSONObject status) {
                        super.onPostExecute(status);

                        String statusMessage = null;
                        try {
                            statusMessage = status.getString("message");
                            new AsyncTask<String, Void, Bitmap>() {
                                @Override
                                protected Bitmap doInBackground(String... strings) {
                                    try {
                                        return new RestClient().getPissedImage(strings[0]);
                                    } catch (IOException e) {
                                        return null;
                                    }
                                }

                                @Override
                                protected void onPostExecute(Bitmap bitmap) {
                                    super.onPostExecute(bitmap);
                                    if (bitmap != null) {
                                        MyActivity.this.layout.setBackground(new BitmapDrawable(bitmap));
                                    }
                                }
                            }.execute(status.getString("img"));
                        } catch (Exception e) {
                            statusMessage = "Oops!! He is really pissed off now!";
                        }
//                        try {
//                            JSONObject status = new JSONObject(s).getJSONObject("status");
//                            statusMessage = status.getString("message");
//                        } catch (JSONException e) {
//                            statusMessage = "Oops!! He is really pissed off right now!";
//                        }
                        MyActivity.this.isHeTxt.setText(statusMessage);
                    }
                }.execute();
            }
        });
    }
}
