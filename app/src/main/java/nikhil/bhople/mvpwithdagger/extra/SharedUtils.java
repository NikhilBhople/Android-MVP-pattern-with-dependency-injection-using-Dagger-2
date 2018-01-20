package nikhil.bhople.mvpwithdagger.extra;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by NIkhil on 20-01-2018.
 */

public class SharedUtils {

    SharedPreferences sharePref;
    SharedPreferences.Editor editor;

    public SharedUtils(Context context) {
        sharePref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharePref.edit();
    }


    public void setIsLogin(boolean isLogin){
        editor.putBoolean("Is_logIn", isLogin);
        editor.commit();
    }

    public boolean isLogedIN(){
        return sharePref.getBoolean("Is_logIn",true);
    }

    public void  setUserId(Integer userId){
        editor.putInt("UserId",userId);
        editor.commit();
    }
    public int getUserId(){
        return sharePref.getInt("UserId",0);
    }

    public void  setAdminFlag(Integer adminFlag){
        editor.putInt("Is_admin",adminFlag);
        editor.commit();
    }
    public int getAdminFlag(){
        return sharePref.getInt("Is_admin",0);
    }


    public void setInternalTransactionId(String transactionId){
        editor.putString("transaction_id",transactionId);
        editor.commit();
    }
    public String getInternalTransactionId(){
        return sharePref.getString("transaction_id","");
    }

    public void setPackageName(String packageName) {
        editor.putString("PACKAGE_LIST", packageName);
        editor.commit();
    }

    public String getPackageName() {
        return  sharePref.getString("PACKAGE_LIST","");
    }

    public void setFirstTimeOverlay(boolean isFirstTimeoverlayval) {
        editor.putBoolean("IsFirstTimeOvelay", isFirstTimeoverlayval);
        editor.commit();
    }

    public boolean isFirstTimeoverlay() {
        return sharePref.getBoolean("IsFirstTimeOvelay", true);
    }


    public void setFirstTimeOverlayProfile(boolean isFirstTimeoverlayvalprofile) {
        editor.putBoolean("IsFirstTimeOvelayPROFILE", isFirstTimeoverlayvalprofile);
        editor.commit();
    }

    public boolean isFirstTimeoverlayprofile() {
        return sharePref.getBoolean("IsFirstTimeOvelayPROFILE", true);
    }

    public void setUserEmail(String email){
        editor.putString("user_email", email);
        editor.commit();
    }
    public String getUserEmail(){
        return sharePref.getString("user_email","");
    }



    public void setFirstTimePopUp(boolean isFirstTimepopupval) {
        editor.putBoolean("IsFirstTimePopUp", isFirstTimepopupval);
        editor.commit();
    }

    public boolean isFirstTimePopUp() {
        return sharePref.getBoolean("IsFirstTimePopUp", true);
    }

    public void setIsUserGroupExist(Integer value){
        editor.putInt("IsUserGroupExest",value);
        editor.commit();
    }
    public Integer getIsUserGroupExist(){
        return sharePref.getInt("IsUserGroupExest",0);
    }


    public void setFirebaseToken(String token){
        editor.putString("FirebaseToken",token);
        editor.commit();
    }

    public String getFirebaseToken(){
        return sharePref.getString("FirebaseToken","");
    }


}