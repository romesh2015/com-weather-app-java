package com.weather.weathertestapp.weather.presenter;
import android.content.Context;
import com.google.gson.Gson;
import com.weather.weathertestapp.db.ReportTableOperations;
import com.weather.weathertestapp.network.ApiErrorsResponseHandling;
import com.weather.weathertestapp.network.BaseRetrofitHandler;
import com.weather.weathertestapp.utilities.Tracer;
import com.weather.weathertestapp.weather.model.WeatherResponse;
import org.greenrobot.eventbus.EventBus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
public class WeatherPresenter {
    private final String TAG = WeatherPresenter.class.getSimpleName();
    private Context mContext;
    public WeatherPresenter(Context mContext) {
        this.mContext = mContext;
    }
    public void getWeatherInfo() {
        Observable<WeatherResponse> observable = BaseRetrofitHandler.getInstance().apiService.getWeatherReport();
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                map(UpdateAppLanguage -> UpdateAppLanguage).
                subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(WeatherResponse updateAppLanguage) {
                        ReportTableOperations reportTableOperations= new ReportTableOperations();
                        reportTableOperations.saveWeatherReport(updateAppLanguage);
                    }
                    @Override
                    public void onError(Throwable e) {
                        ApiErrorsResponseHandling errorResponse=null;
                        try {
                            String code = ((HttpException) e).response().errorBody().string();
                            Tracer.warning(TAG, code);
                            Gson gson = new Gson();
                            errorResponse = gson.fromJson(code, ApiErrorsResponseHandling.class);
                            EventBus.getDefault().unregister(this);
                            EventBus.getDefault().post(errorResponse);
                        } catch (Exception ex) {
                            Tracer.warning(TAG, ex.getMessage());
                            errorResponse=new ApiErrorsResponseHandling();
                            errorResponse.setMessage("Something went wrong, please try later!");
                            EventBus.getDefault().unregister(this);
                            EventBus.getDefault().post(errorResponse);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
