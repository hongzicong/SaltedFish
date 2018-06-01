package hongzicong.saltedfish.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;
import hongzicong.saltedfish.R;

import android.app.Dialog;
import android.content.ContentValues;
import android.support.constraint.ConstraintLayout;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.utils.Util;

public class RegisterFragment extends Fragment {


    @BindView(R.id.register_password)
    EditText password;

    @BindView(R.id.register_mail)
    EditText mail;

    @BindView(R.id.register_register_button)
    Button register;

    private Handler handler;
    private Dialog dialog;

    private Unbinder mUnbinder;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_register,container,false);
        mUnbinder= ButterKnife.bind(this,v);

        setAllClickListener();
        ((ConstraintLayout)v.findViewById(R.id.register_layout)).setSystemUiVisibility(View.INVISIBLE);

        handler=new Handler(){
            public void handlerMessage(Message msg){
                super.handleMessage(msg);
                dialog.dismiss();
                if(msg.what==222){
                    if(msg.obj.toString().equals("SUCCESSED")){
                        Toast.makeText(UIUtils.getContext(),"注册成功！但是后面的还没做！",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UIUtils.getContext(),"注册失败，自己看看哪里没写好！！！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        return v;
    }

    private void setAllClickListener(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Util.isNetworkAvailable(UIUtils.getContext())){
                    if(Util.isValidName(mail.getText().toString())&&Util.isValidPassword(password.getText().toString())){
                        dialog=new Dialog(UIUtils.getContext());
                        dialog.setTitle("少女祈祷中...");
                        dialog.setCancelable(false);
                        dialog.show();
                        new RegisterPostThread(mail.getText().toString(),password.getText().toString()).start();
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

    public class RegisterPostThread extends Thread {

        public String id, password;

        public RegisterPostThread(String id, String password) {
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
                responseInt = RegisterService.send(params);
                Log.i("tag", "RegisterActivity: responseInt = " + responseInt);
                Message msg = handler.obtainMessage();
                msg.what = 222;
                if(responseInt == RegisterService.REGISTER_FAILED) {
                    msg.obj = "FAILED";
                }else if(responseInt == RegisterService.REGISTER_SUCCEEDED) {
                    msg.obj = "SUCCEEDED";
                }
                handler.sendMessage(msg);
            }
        }
    }

}
