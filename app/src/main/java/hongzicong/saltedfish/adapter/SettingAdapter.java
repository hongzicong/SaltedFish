package hongzicong.saltedfish.adapter;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import hongzicong.saltedfish.activity.BaseApplication;
import hongzicong.saltedfish.model.PersonalInfo;
import hongzicong.saltedfish.model.signResponse;
import hongzicong.saltedfish.net.LogInInterface;
import hongzicong.saltedfish.net.LogoutInterface;
import hongzicong.saltedfish.utils.SharedPreferencesUtils;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.utils.Util;
import hongzicong.saltedfish.viewholder.SettingViewHolder;
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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Dv00 on 2018/12/27.
 */

public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

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

    private LogoutInterface logoutInterface = retrofit.create(LogoutInterface.class);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        if(viewType==0){
            View itemView=layoutInflater.inflate(R.layout.item_setting,parent,false);
            return new SettingViewHolder(itemView,"更改风格");
        }
        else if(viewType==1){
            View itemView=layoutInflater.inflate(R.layout.item_setting,parent,false);
            return new SettingViewHolder(itemView,"备份");
        }
        else if(viewType==2){
            View itemView=layoutInflater.inflate(R.layout.item_setting,parent,false);
            return new SettingViewHolder(itemView,"更新");
        }
        else if(viewType==3){
            View itemView=layoutInflater.inflate(R.layout.item_setting,parent,false);
            return new SettingViewHolder(itemView,"退出登录");
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if(position == 0){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO
                }
            });
        } else if(position == 1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!PersonalInfo.getPersonalInfo().isLogin()){
                        Toast.makeText(UIUtils.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    } else{
                        // TODO
                    }
                }
            });
        } else if(position == 2){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!PersonalInfo.getPersonalInfo().isLogin()){
                        Toast.makeText(UIUtils.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    } else{
                        // TODO
                    }
                }
            });
        } else if(position == 3){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!PersonalInfo.getPersonalInfo().isLogin()){
                        Toast.makeText(UIUtils.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    } else{
                        if(Util.isNetworkAvailable(UIUtils.getContext())){
                            logoutInterface.logOut()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<signResponse>() {
                                        @Override
                                        public void onSubscribe(Disposable d) { }

                                        @Override
                                        public void onNext(signResponse signResponse) {
                                            if (signResponse.getStatus().equals("OK")) {
                                                Log.d("HAHA","logout successfully");

                                                SharedPreferencesUtils.getInstance().putString("name", "");
                                                SharedPreferencesUtils.getInstance().putString("gender", "");
                                                SharedPreferencesUtils.getInstance().putString("avatar", "");

                                                Toast.makeText(UIUtils.getContext(), "成功退出登录", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(UIUtils.getContext(), signResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
                        } else{
                            Toast.makeText(UIUtils.getContext(),"网络未连接",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
