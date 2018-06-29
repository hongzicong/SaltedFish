package hongzicong.saltedfish.model;

import hongzicong.saltedfish.utils.SharedPreferencesUtils;

public class PersonalInfo {

    private String name;

    private String gender;

    private String avatar;

    private String password;

    private boolean login;

    public PersonalInfo(String name, String gender, String avatar, String password){
        this.name = name;
        this.gender = gender;
        this.avatar = avatar;
        this.password = password;
        this.login = false;
    }

    public boolean hasRecord() {
        return !getPersonalInfo().getName().equals("");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                    SharedPreferencesUtils.getInstance().getString("avatar",""),
                    SharedPreferencesUtils.getInstance().getString("password",""));
            personalInfo.login = !personalInfo.getName().equals("");
        }
        return personalInfo;
    }

    public static void loadPersonInfo(String name, String avatar, String gender,String password){
        SharedPreferencesUtils.getInstance().putString("name", name);
        SharedPreferencesUtils.getInstance().putString("gender", gender);
        SharedPreferencesUtils.getInstance().putString("avatar", avatar);
        SharedPreferencesUtils.getInstance().getString("password",password);
        getPersonalInfo().setName(name);
        getPersonalInfo().setGender(gender);
        getPersonalInfo().setAvatar(avatar);
        getPersonalInfo().setPassword(password);
    }

}
