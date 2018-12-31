package hongzicong.saltedfish.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import hongzicong.saltedfish.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hongzicong.saltedfish.model.PersonalInfo;
import hongzicong.saltedfish.model.signResponse;
import hongzicong.saltedfish.net.LogInInterface;
import hongzicong.saltedfish.net.SignUpInterface;
import hongzicong.saltedfish.utils.EncryptUtil;
import hongzicong.saltedfish.utils.NetUtil;
import hongzicong.saltedfish.utils.SharedPreferencesUtils;
import hongzicong.saltedfish.utils.UIUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static hongzicong.saltedfish.model.PersonalInfo.loadPersonInfo;
import static hongzicong.saltedfish.utils.Util.isValidName;

public class LoginRegisterActivity extends AppCompatActivity {

    // cookie persistent : https://github.com/franmontiel/PersistentCookieJar
    private ClearableCookieJar cookieJar =
            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.getContext()));

    private OkHttpClient build = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .build();

    private Retrofit retrofit = new  Retrofit.Builder()
            .client(build)
            .baseUrl("http://108.61.151.131:12192/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private LogInInterface logInInterface = retrofit.create(LogInInterface.class);
    private SignUpInterface signUpInterface = retrofit.create(SignUpInterface.class);

    private Unbinder mUnbinder;

    @BindView(R.id.change_button)
    Button changeButton;

    @BindView(R.id.forget_password_button)
    Button forgetPasswordButton;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.again_password)
    EditText againPassword;

    @BindView(R.id.again_password_text_input_layout)
    TextInputLayout againPasswordTextInputLayout;

    @BindView(R.id.ok_button)
    Button okButton;

    @BindView(R.id.radio_button)
    RadioButton mRadioButton;

    private boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        mUnbinder= ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setAllClickListener();
    }

    private void register(final String name, final String passEncry){
        final Map<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", passEncry);
        String json = new Gson().toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        signUpInterface.signUp(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<signResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(signResponse signResponse) {
                        if (signResponse.getStatus().equals("OK")) {
                            logIn(name, passEncry);
                        } else {
                            Toast.makeText(UIUtils.getContext(), signResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(UIUtils.getContext(), "服务器错误", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    private void logIn(String name, String passEncry){
        final Map<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", passEncry);
        String json = new Gson().toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        logInInterface.logIn(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<signResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(signResponse signResponse) {
                        if (signResponse.getStatus().equals("OK")) {
                            PersonalInfo.loadPersonInfo(map.get("username"), "", "", map.get("password"));
                            Toast.makeText(UIUtils.getContext(), "登陆成功", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(UIUtils.getContext(), signResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(UIUtils.getContext(), "服务器错误", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    private void setAllClickListener(){
        mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton.setChecked(!mRadioButton.isChecked());
                SharedPreferencesUtils.getInstance().putBoolean("auto_login", mRadioButton.isChecked());
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetUtil.isNetworkAvailable(UIUtils.getContext())){
                    if(!isLogin && !password.getText().toString().equals(againPassword.getText().toString())){
                        Toast.makeText(UIUtils.getContext(), "Password not match", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(isValidName(name.getText().toString())){
                        String passEncry = new String();
                        try {
                            passEncry = EncryptUtil.encryptPsd(password.getText().toString());
                        } catch (Exception e) {
                            Log.i("Encrypt Error", "MD5 Failed");
                            e.printStackTrace();
                        }

                        if(isLogin) {
                            logIn(name.getText().toString(), passEncry);
                        } else{
                            register(name.getText().toString(), passEncry);
                        }
                    }
                    else{
                        Toast.makeText(UIUtils.getContext(),"请输入合法账号",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(UIUtils.getContext(),"网络未连接",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLogin = !isLogin;
                if(isLogin){
                    okButton.setText("登陆");
                    changeButton.setText("注册");
                    againPasswordTextInputLayout.setVisibility(View.GONE);
                } else{
                    okButton.setText("注册");
                    changeButton.setText("登录");
                    againPasswordTextInputLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
