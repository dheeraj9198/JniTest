package com.example.dheeraj.jnitest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    static {
        try {
            System.loadLibrary("HelloLibrary");
        } catch (Exception e) {
            Log.e("", "", e);
        }
    }

    public native String stringFromJNI(String in, String out);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = "/sdcard/dheeraj.txt";
                String s2 = "/storage/sdcard1/dheeraj_copied.txt";
                String s3 = "/storage/sdcard1";

                File file1 = new File(s1);
                File file3 = new File(s3);

                if (file1.exists() && file3.exists() && file3.isDirectory()) {

                    String x = "error";
                    try {
                        x = stringFromJNI(s1, s2);
                    } catch (Exception e) {
                        Log.e("", "", e);
                        x = "exception";
                    }

                    Toast.makeText(MainActivity.this, x, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "fuck you", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
