package com.gabeochoa.wib;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class WIB_app extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wib_app);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wib_app, menu);
        return true;
    }
    
}
