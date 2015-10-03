package com.example.blancomm.dailyselfie.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.blancomm.dailyselfie.R;
import com.example.blancomm.dailyselfie.model.SelfieInfo;
import com.example.blancomm.dailyselfie.notification.SelfieNotification;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DialogName.EditNameDialogListener{

    private MainActivityFragment fragment;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final long INTERVAL_MINUTES = 2 * 60 * 1000L;

    private String mSelfieName;
    private String mPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateObjects();

        fragment = (MainActivityFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);

        createSelfieAlarm();
    }

    private void instantiateObjects() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showEditDialog();
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

    private File createImageFile() throws IOException {

        File imageFile = File.createTempFile(
                mSelfieName,
                ".jpg",
                getExternalFilesDir(null));

        mPhotoPath = imageFile.getAbsolutePath();
        return imageFile;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE &&  resultCode != RESULT_CANCELED) {

            File photoFile = new File(mPhotoPath);
            File selfieFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mSelfieName + ".jpg");
            photoFile.renameTo(selfieFile);

            SelfieInfo selfie = new SelfieInfo(Uri.fromFile(selfieFile).getPath(), mSelfieName);
            fragment.updateSelfiesList(selfie);

        } else {

            File photoFile = new File(mPhotoPath);
            photoFile.delete();
        }
    }

    private void createSelfieAlarm() {
        Intent intent = new Intent(this, SelfieNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + INTERVAL_MINUTES,
                INTERVAL_MINUTES,
                pendingIntent);
    }

    @Override
    public void onFinishEditDialog(String inputText) {

        mSelfieName = inputText + "_" + getCurrentDate();
        dispatchTakePictureIntent();
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogName editNameDialog = new DialogName();
        editNameDialog.show(fm, "fragment_edit_name");
        editNameDialog.setCancelable(true);
    }

    private Date getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

}
