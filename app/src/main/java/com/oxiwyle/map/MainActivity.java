package com.oxiwyle.map;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.oxiwyle.map.dialog.CountryDialog;
import com.oxiwyle.map.json.CountryJson;
import com.richpath.RichPath;
import com.richpath.RichPathView;

public class MainActivity extends AppCompatActivity {
    /** Тэг для индентификации диалога. */
    private static final String DIALOG_TAG = "COUNTRY_DIALOG";
    /** Объект для получения названия страны. */
    private CountryJson countryJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeActivity();
    }

    /**
     * Метод инициализации активности.
     */
    private void initializeActivity() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        countryJson = new CountryJson(this);

        RichPathView richPathView = findViewById(R.id.map);
        richPathView.setOnPathClickListener(new RichPath.OnPathClickListener() {
            @Override
            public void onClick(RichPath richPath) {
                String countryName = countryJson.getNameByCode(richPath.getName());
                CountryDialog dialog = new CountryDialog(countryName);
                dialog.show(getSupportFragmentManager(), DIALOG_TAG);
            }
        });
    }
}