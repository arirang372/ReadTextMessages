package john.com.readtextmessages;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by johns on 11/17/2017.
 */

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
    }


    @Override
    public void onStart()
    {
        super.onStart();

        if(checkPermissions())
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);// Activity is started with requestCode 2
            finish();
        }
        else
        {
            Intent intent = new Intent(this,PagerPermissionActivity.class);
            startActivityForResult(intent, 2);// Activity is started with requestCode 2
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            Log.i("Hello", "We have all the permissions we need");
//            Intent i = new Intent(this, MainActivity.class);
//            startActivity(i);
        }
    }



    private boolean checkPermissions(){
        for(int i = 0 ; i < MULTI_PERMISSIONS.length -1 ; i++){
            int res = this.checkCallingOrSelfPermission( MULTI_PERMISSIONS[i]);
            if (res != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public final static String[] MULTI_PERMISSIONS = new String[]{
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS
    };
}
