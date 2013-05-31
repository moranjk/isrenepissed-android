package com.example.isRenePissed;

import android.os.AsyncTask;
import android.widget.TextView;


/**
 * Created with IntelliJ IDEA.
 * User: jmoran
 * Date: 5/31/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class UpdatePissedTask extends AsyncTask<Void, Void, String> {

    private TextView textElement;

    public UpdatePissedTask(TextView textElement)
    {
        super();
        this.textElement = textElement;
    }

    @Override
    protected String doInBackground(Void... voids) {

        return new RestClient().getIsPissed();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);    //To change body of overridden methods use File | Settings | File Templates.

        this.textElement.setText(s);
    }
}
