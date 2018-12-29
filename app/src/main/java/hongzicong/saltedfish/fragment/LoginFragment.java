package hongzicong.saltedfish.fragment;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.utils.Util;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.forget_password)
    Button forget;

    @BindView(R.id.register)
    Button register;

    private Handler handler;

    private Dialog dialog;

    private Unbinder mUnbinder;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login,container,false);
        mUnbinder= ButterKnife.bind(this,v);
        setAllClickListener();
        ((ConstraintLayout)v.findViewById(R.id.login_layout)).setSystemUiVisibility(View.INVISIBLE);
        name.setText(getUserName());
        handler=new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                dialog.dismiss();
                if(msg.what==111){
                    if(msg.obj.toString().equals("SUCCEEDED")){
                        goToHomeActivity();
                    }else{
                        Toast.makeText(UIUtils.getContext(),"账户不存在或者密码错误！！！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        return v;
    }

    public class LoginPostThread extends Thread {

        public String id, password;

        public LoginPostThread(String id, String password) {
            this.id = id;
            this.password = password;
        }

        @Override
        public void run() {
            int responseInt = 0;
            if(!id.equals("")) {
                ContentValues contentValues=new ContentValues();
                List<ContentValues> params = new ArrayList<>();
                contentValues.put("id",id);
                contentValues.put("password",password);
                params.add(contentValues);
                responseInt = LoginPostService.send(params);
                Log.i("tag", "FirstActivity: responseInt = " + responseInt);
                Message msg = handler.obtainMessage();
                msg.what = 111;
                if(responseInt == LoginPostService.LOGIN_FAILED) {
                    msg.obj = "FAILED";
                }else if(responseInt == LoginPostService.LOGIN_SUCCEEDED) {
                    msg.obj = "SUCCEEDED";
                }
                handler.sendMessage(msg);
            }
        }
    }


    private void setAllClickListener(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Util.isNetworkAvailable(UIUtils.getContext())){
                    if(isValidName(name.getText().toString())){
                        saveUserName();
                        goToHomeActivity();
                        //Todo 完成服务器端才开启以下登陆功能
                        /*
                        dialog=new Dialog(FirstActivity.this);
                        dialog.setTitle("少女祈祷中...");
                        dialog.setCancelable(false);
                        dialog.show();
                        new LoginPostThread(name.getText().toString(),password.getText().toString()).start();*/
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
    }


    //登陆成功，去往主页面
    private void goToHomeActivity(){
        Intent intent=new Intent(UIUtils.getContext(),HomeActivity.class);
        startActivity(intent);
    }

    private void saveUserName(){
        //todo 要不要存进数据库
        SharedPreferences.Editor editor=UIUtils.getContext().getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("name",name.getText().toString());
        editor.apply();
    }

    private String getUserName(){
        //todo 要不要从数据库中取出来
        SharedPreferences pref=UIUtils.getContext().getSharedPreferences("data",MODE_PRIVATE);
        String name=pref.getString("name","");
        return name;
    }

}