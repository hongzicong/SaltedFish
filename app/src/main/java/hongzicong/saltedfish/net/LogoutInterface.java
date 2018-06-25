package hongzicong.saltedfish.net;

import hongzicong.saltedfish.model.signResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface LogoutInterface {
    @GET("user/logout")
    Observable<signResponse> logOut();
}
