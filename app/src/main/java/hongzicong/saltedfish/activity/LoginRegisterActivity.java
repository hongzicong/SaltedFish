package hongzicong.saltedfish.activity;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hongzicong.saltedfish.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.utils.Util;

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
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Util.isNetworkAvailable(UIUtils.getContext())){
                    if(isValidName(name.getText().toString())){
                        if(isLogin){
                            //Todo 登陆
                            finish();
                        } else{
                            //Todo 注册
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
