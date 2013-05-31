package com.example.isRenePissed;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button isHeBtn = (Button)findViewById(R.id.isHeButton);

        isHeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UpdatePissedTask((TextView)findViewById(R.id.isHeText)).execute();
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
