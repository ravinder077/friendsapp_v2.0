package com.tuespotsolutions.ravinder077.friendsapp.thread;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Created by ravinder077 on 11-10-2017.
 */

public  class ProgressDialogAsyncTask extends AsyncTask<Void, Void, Void> {
    private ProgressDialog dialog;

    public ProgressDialogAsyncTask() {
       // dialog = new ProgressDialog();
    }

    @Override
    protected void onPreExecute() {



        dialog.setMessage("Doing something, please wait.");
        dialog.show();
    }

    protected Void doInBackground(Void... args) {
        // do background work here




        return null;
    }

    protected void onPostExecute(Void result) {
        // do UI work here
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}