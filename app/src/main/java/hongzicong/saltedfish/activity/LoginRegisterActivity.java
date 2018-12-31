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
import hongzicong.saltedfish.utils.EncryptUtil;
import hongzicong.saltedfish.utils.NetUtil;
import hongzicong.saltedfish.utils.UIUtils;
import static hongzicong.saltedfish.utils.Util.isValidName;

public class LoginRegisterActivity extends AppCompatActivity {

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

    private boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        mUnbinder= ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setAllClickListener();
    }

    private void setAllClickListener(){
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
                            NetUtil.LogIn(name.getText().toString(), passEncry);
                        } else{
                            NetUtil.Register(name.getText().toString(), passEncry);
                        }
                        finish();
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
