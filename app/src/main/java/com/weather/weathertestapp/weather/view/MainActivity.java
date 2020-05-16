package com.weather.weathertestapp.weather.view;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.weather.weathertestapp.R;
import com.weather.weathertestapp.db.ReportTableOperations;
import com.weather.weathertestapp.db.WeatherDetail;
import com.weather.weathertestapp.network.ApiErrorsResponseHandling;
import com.weather.weathertestapp.utilities.AppConstants;
import com.weather.weathertestapp.utilities.SharedPrefsHelper;
import com.weather.weathertestapp.utilities.Tracer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
public class MainActivity extends AppCompatActivity{
    public static final String TAG = MainActivity.class.getSimpleName();
    private TextView tvLocation, tvTemperature, tvTempMaxMIn, tvFeelLIke, tvPressure, tvHumidity, tvVisibility,tvUpdateTime;
    private ImageView imageViewIcon;
    private  ReportTableOperations reportTableOperations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reportTableOperations= new ReportTableOperations();

        tvLocation = findViewById(R.id.textView);
        tvTemperature = findViewById(R.id.textView1);
        tvTempMaxMIn = findViewById(R.id.textView2);
        tvFeelLIke = findViewById(R.id.textView3);
        tvPressure = findViewById(R.id.textView4);
        tvHumidity = findViewById(R.id.textView5);
        tvVisibility = findViewById(R.id.textView6);
        tvUpdateTime=findViewById(R.id.textView7);
        imageViewIcon=findViewById(R.id.imageView);
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        if(AppConstants.UPDATE_REQUEST_VAL.equalsIgnoreCase(SharedPrefsHelper.getInstance().get(AppConstants.DEFAULT_FUNCTION_CALL_REQUEST))){
            reportTableOperations.getWeatherData();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onWeatherReportSuccess(WeatherDetail weatherResponse) {
        imageViewIcon.setVisibility(View.VISIBLE);
        tvLocation.setText(getString(R.string.address) + ": " + weatherResponse.getLocation() + ", " + weatherResponse.getCountry() + "\n\n" + getString(R.string.weather) + ": " + weatherResponse.getWeatherType());
        tvTemperature.setText(weatherResponse.getTemp() + getString(R.string.temp));
        tvTempMaxMIn.setText(weatherResponse.getMaxTemp() + " / " + weatherResponse.getMinTemp() + getString(R.string.temp));
        tvFeelLIke.setText(getString(R.string.feel_like) + ": " + weatherResponse.getFeelLike());
        tvPressure.setText(getString(R.string.pressure) + ": " + weatherResponse.getPressure());
        tvHumidity.setText(getString(R.string.humidity) + ": " + weatherResponse.getHumidity());
        tvVisibility.setText(getString(R.string.visibility) + ": " + weatherResponse.getVisibility());
        tvUpdateTime.setText(getString(R.string.timestamp)+": "+weatherResponse.getTimeStamp());
        SharedPrefsHelper.getInstance().save(AppConstants.IS_JOB_EXECUTED,"1");
    }
    @Subscribe
    public void onWeatherReportFailed(ApiErrorsResponseHandling apiErrorsResponseHandling) {
        imageViewIcon.setVisibility(View.GONE);
        Tracer.warning(TAG, apiErrorsResponseHandling.getMessage());
        Toast.makeText(this,apiErrorsResponseHandling.getMessage(),Toast.LENGTH_SHORT).show();
    }

}
