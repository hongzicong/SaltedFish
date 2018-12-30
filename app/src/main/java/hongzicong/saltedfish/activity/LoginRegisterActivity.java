package hongzicong.saltedfish.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import hongzicong.saltedfish.model.signResponse;
import hongzicong.saltedfish.netInterface.logInInterface;
import hongzicong.saltedfish.netInterface.signUpInterface;
import hongzicong.saltedfish.utils.EncryptUtil;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.utils.Util;
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

import static hongzicong.saltedfish.utils.Util.isValidName;

public class LoginRegisterActivity extends AppCompatActivity {
    // cookie persistent : https://github.com/franmontiel/PersistentCookieJar
    private ClearableCookieJar cookieJar =
            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));
    private OkHttpClient build = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .build();
    private Retrofit retrofit = new  Retrofit.Builder()
            .baseUrl("http://108.61.151.131:12192/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    private logInInterface logInInterface = retrofit.create(logInInterface.class);
    private signUpInterface signUpInterface = retrofit.create(signUpInterface.class);

    private Unbinder mUnbinder;

    @BindView(R.id.change_button)
    Button changeButton;

    @BindView(R.id.forget_password_button)
    Button forgetPasswordButton;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.again_password_text_input_layout)
    TextInputLayout againPasswordTextInputLayout;

    @BindView(R.id.ok_button)
    Button okButton;

    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        mUnbinder= ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setAllClickListener();
    }

    private void loadPersonInfo(String name, String avatar, String gender){
        SharedPreferences.Editor editor= UIUtils.getContext().getSharedPreferences("PERSONAL_INFO",MODE_PRIVATE).edit();
        editor.putString("name",name);
        editor.putString("gender",name);
        editor.putString("avatar",name);
        editor.apply();
    }

    private void setAllClickListener(){
        final Context context = this;
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Util.isNetworkAvailable(UIUtils.getContext())){
                    if(isValidName(name.getText().toString())){
                        Map<String, String> map = new HashMap<>();
                        map.put("username", name.getText().toString());
                        try {
                            map.put("password", EncryptUtil.encryptPsd(password.getText().toString()));
                        } catch (Exception e) {
                            Log.i("Encrypt Error", "MD5 Failed");
                            e.printStackTrace();
                        }
                        String json = new Gson().toJson(map);
                        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
                        if(isLogin){
                            logInInterface.logIn(body)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<signResponse>() {
                                        @Override
                                        public void onSubscribe(Disposable d) { }

                                        @Override
                                        public void onNext(signResponse signResponse) {
                                            if (signResponse.getStatus().equals("OK")) {
                                                // TODO: 登陆成功，跳转
                                            } else {
                                                Toast.makeText(context, signResponse.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Toast.makeText(context, "服务器错误", Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onComplete() { }
                                    });
                            finish();
                        } else{
                            signUpInterface.signUp(body)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<signResponse>() {
                                        @Override
                                        public void onSubscribe(Disposable d) { }

                                        @Override
                                        public void onNext(signResponse signResponse) {
                                            if (signResponse.getStatus().equals("OK")) {
                                                // TODO: 注册成功，跳转
                                            } else {
                                                Toast.makeText(context, signResponse.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Toast.makeText(context, "服务器错误", Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onComplete() { }
                                    });
                            finish();
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
