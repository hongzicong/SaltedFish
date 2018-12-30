package hongzicong.saltedfish.model;

import android.content.Context;
import android.content.SharedPreferences;

import hongzicong.saltedfish.utils.SharedPreferencesUtils;
import hongzicong.saltedfish.utils.UIUtils;

public class PersonalInfo {

    private String name;

    private String gender;

    private String avatar;

    private boolean login;

    public PersonalInfo(String name, String gender, String avatar){
        this.name = name;
        this.gender = gender;
        this.avatar = avatar;
        this.login = false;
    }

    public boolean isLogin(){
        return login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private static PersonalInfo personalInfo;

    public static PersonalInfo getPersonalInfo(){
        if(personalInfo == null){
            personalInfo = new PersonalInfo(SharedPreferencesUtils.getInstance().getString("name",""),
                    SharedPreferencesUtils.getInstance().getString("gender",""),
                    SharedPreferencesUtils.getInstance().getString("avatar",""));
            personalInfo.login = !personalInfo.getName().equals("");
        }
        return personalInfo;
    }

    public static void loadPersonInfo(String name, String avatar, String gender){
        SharedPreferencesUtils.getInstance().putString("name", name);
        SharedPreferencesUtils.getInstance().putString("gender", gender);
        SharedPreferencesUtils.getInstance().putString("avatar", avatar);
        getPersonalInfo().setName(name);
        getPersonalInfo().setName(gender);
        getPersonalInfo().setName(avatar);
    }

}
