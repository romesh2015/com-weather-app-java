package com.weather.weathertestapp.db;
import android.content.Context;
import android.os.AsyncTask;
import com.weather.weathertestapp.WeatherApp;
import com.weather.weathertestapp.utilities.SharedPrefsHelper;
import com.weather.weathertestapp.utilities.Tracer;
import com.weather.weathertestapp.utilities.Utility;
import com.weather.weathertestapp.weather.model.WeatherResponse;
import org.greenrobot.eventbus.EventBus;
import static com.weather.weathertestapp.utilities.AppConstants.DEFAULT_FUNCTION_CALL_REQUEST;
import static com.weather.weathertestapp.utilities.AppConstants.UPDATE_REQUEST_VAL;
public class ReportTableOperations {
    private static final String TAG = ReportTableOperations.class.getSimpleName();
    private Context context;

    public ReportTableOperations() {

    }
    public boolean saveWeatherReport(WeatherResponse weatherResponse) {
        class SaveTask extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... voids) {
                WeatherDetail weatherDetail = new WeatherDetail();
                weatherDetail.setId(weatherResponse.getId());
                weatherDetail.setLocation(weatherResponse.getName());
                weatherDetail.setCountry(weatherResponse.getSys().getCountry());
                weatherDetail.setWeatherType(weatherResponse.getWeatherList().get(0).getMain());
                weatherDetail.setTemp(weatherResponse.getMain().getTemp());
                weatherDetail.setMaxTemp(weatherResponse.getMain().getTempMax());
                weatherDetail.setMinTemp(weatherResponse.getMain().getTempMin());
                weatherDetail.setFeelLike(weatherResponse.getMain().getFeelsLike());
                weatherDetail.setPressure(weatherResponse.getMain().getPressure());
                weatherDetail.setHumidity(weatherResponse.getMain().getHumidity());
                weatherDetail.setVisibility(weatherResponse.getVisibility());
                weatherDetail.setTimeStamp(Utility.getCurrentTime());
                DatabaseClient.getInstance(WeatherApp.getsAppContext()).getAppDatabase()
                        .reportDetailDAO()
                        .insertWeatherReport(weatherDetail);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                Tracer.info(TAG,"Record save status->"+aBoolean);
                SharedPrefsHelper.getInstance().save(DEFAULT_FUNCTION_CALL_REQUEST,UPDATE_REQUEST_VAL);
                getWeatherData();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();

        return false;
    }
    public void  getWeatherData() {
        class GetVideoDetail extends AsyncTask<Void, Void, WeatherDetail> {
            WeatherDetail weatherDetail;
            @Override
            protected WeatherDetail doInBackground(Void... voids) {
                weatherDetail = DatabaseClient.getInstance(WeatherApp.getsAppContext()).getAppDatabase()
                        .reportDetailDAO()
                        .getWeatherReport();
                return weatherDetail;
            }
            @Override
            protected void onPostExecute(WeatherDetail weatherDetail) {
                super.onPostExecute(weatherDetail);
                EventBus.getDefault().post(weatherDetail);
            }
        }
        GetVideoDetail getVideoDetail= new GetVideoDetail();
        getVideoDetail.execute();

    }

}
