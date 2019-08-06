package com.boorsaty.sehtk;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.proflie:
                        Toast.makeText(MainActivity.this, "proflie", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.map:
                        Toast.makeText(MainActivity.this, "map", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.emergency:
                        Toast.makeText(MainActivity.this, "emergency", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.protocols:
                        Toast.makeText(MainActivity.this, "protocols", Toast.LENGTH_SHORT).show();
                        break;                }
                return true;
            }
        });
    }
}
