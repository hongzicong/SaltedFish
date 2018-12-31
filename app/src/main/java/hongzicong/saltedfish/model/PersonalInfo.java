package hongzicong.saltedfish.model;

import hongzicong.saltedfish.utils.SharedPreferencesUtils;

public class PersonalInfo {

    public static String getName(){
        return SharedPreferencesUtils.getInstance().getString("name");
    }

    public static String getAvatar(){
        return SharedPreferencesUtils.getInstance().getString("avatar");
    }

    public static String getGender(){
        return SharedPreferencesUtils.getInstance().getString("gender");
    }

    public static String getPassword(){
        return SharedPreferencesUtils.getInstance().getString("password");
    }

    public static boolean isLogin(){
        return !SharedPreferencesUtils.getInstance().getString("name", "").equals("");
    }

    public static void loadPersonInfo(String name, String avatar, String gender,String password){
        SharedPreferencesUtils.getInstance().putString("name", name);
        SharedPreferencesUtils.getInstance().putString("gender", gender);
        SharedPreferencesUtils.getInstance().putString("avatar", avatar);
        SharedPreferencesUtils.getInstance().getString("password",password);
    }

}
