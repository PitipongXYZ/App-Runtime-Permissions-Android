package com.mukeshsolanki.example.requestpermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.mukeshsolanki.requestpermissions.RuntimePermission;

public class MainActivity extends AppCompatActivity {
  public static final String[] PERMISSIONS = {
      Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECEIVE_SMS,
      Manifest.permission.READ_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE
  };
  private static final String TAG = MainActivity.class.getName();
  private int mRequestCode = 100;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    RuntimePermission runtimePermission = new RuntimePermission();
    if (runtimePermission.hasPermission(this, PERMISSIONS)) {
      Log.d(TAG, "All Permissions Granted");
    } else {
      runtimePermission.requestPermission(this, PERMISSIONS, mRequestCode);
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == mRequestCode) {
      for (int grantResult : grantResults) {
        if (grantResult == PackageManager.PERMISSION_DENIED) {
          Log.d(TAG, "Denied");
          return;
        }
      }
    }
  }
}
