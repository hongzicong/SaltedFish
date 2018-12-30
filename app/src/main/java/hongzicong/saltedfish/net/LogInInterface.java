package hongzicong.saltedfish.net;

import hongzicong.saltedfish.model.signResponse;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LogInInterface {
	@POST("user/login")
	Observable<signResponse> logIn(@Body RequestBody body);
}
