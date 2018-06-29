package hongzicong.saltedfish.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import hongzicong.saltedfish.activity.BaseApplication;
import hongzicong.saltedfish.model.signResponse;
import hongzicong.saltedfish.net.LogInInterface;
import hongzicong.saltedfish.net.SignUpInterface;
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

public class NetUtil {

    // cookie persistent : https://github.com/franmontiel/PersistentCookieJar
    private static ClearableCookieJar cookieJar =
            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.getContext()));

    private static OkHttpClient build = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .build();

    private static Retrofit retrofit = new  Retrofit.Builder()
            .client(build)
            .baseUrl("http://108.61.151.131:12192/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private static LogInInterface logInInterface = retrofit.create(LogInInterface.class);
    private static SignUpInterface signUpInterface = retrofit.create(SignUpInterface.class);

    public static void Register(String name, String password){
        final Map<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", password);

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
                            loadPersonInfo(map.get("username"), "", "", map.get("password"));
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

    public static void LogIn(String name, String password){
        final Map<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", password);

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
                            loadPersonInfo(map.get("username"), "", "", map.get("password"));
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
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getDetailedState() == NetworkInfo.DetailedState.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
