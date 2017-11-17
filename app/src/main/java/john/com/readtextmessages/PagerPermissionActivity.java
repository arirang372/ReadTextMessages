package john.com.readtextmessages;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.fastaccess.permission.base.activity.BasePermissionActivity;
import com.fastaccess.permission.base.model.PermissionModel;
import com.fastaccess.permission.base.model.PermissionModelBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 8/29/2016.
 */
public class PagerPermissionActivity extends BasePermissionActivity
{

    @NonNull
    @Override
    protected List<PermissionModel> permissions()
    {
        List<PermissionModel> permissions = new ArrayList<>();

        permissions.add(PermissionModelBuilder.withContext(this)
                    .withCanSkip(false)
                    .withTitle(R.string.title_get_send_sms)
                    .withPermissionName(Manifest.permission.READ_SMS)
                    .withMessage(R.string.message_get_send_sms)
                    .withExplanationMessage(R.string.explanation_get_send_sms)
                    .withLayoutColorRes(R.color.colorPrimary)
                    .withImageResourceId(R.drawable.prefs_sms)
                    .build());
        
        return permissions;
    }

    @Override
    protected int theme() {
        return R.style.noActionBar;
    }

    @Override
    protected void onIntroFinished()
    {
        Intent intent=new Intent();
        intent.putExtra("GOOD",1);
        setResult(2,intent);
        finish();
    }

    @Nullable
    @Override
    protected ViewPager.PageTransformer pagerTransformer() {
        return null;//use default
    }

    @Override
    protected boolean backPressIsEnabled() {
        return false;
    }

    @Override
    protected void permissionIsPermanentlyDenied(String permissionName)
    {
        Log.e("DANGER", "Permission ( " + permissionName + " ) is permanentlyDenied and can only be granted via settings screen");
        /** {@link com.fastaccess.permission.base.PermissionHelper#openSettingsScreen(Context)} can help you to open it if you like */
    }

    @Override
    protected void onUserDeclinePermission(String permissionName)
    {
        Log.w("Warning", "Permission ( " + permissionName + " ) is skipped you can request it again by calling doing such\n " +
                "if (permissionHelper.isExplanationNeeded(permissionName)) {\n" +
                "        permissionHelper.requestAfterExplanation(permissionName);\n" +
                "    }\n" +
                "    if (permissionHelper.isPermissionPermanentlyDenied(permissionName)) {\n" +
                "        /** read {@link #permissionIsPermanentlyDenied(String)} **/\n" +
                "    }");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


}
