package ru.mirea.shutovks.activitylifecycle;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "logger";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.editText);
        
        LogMessage("onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogMessage("onStart()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogMessage("onRestoreInstanceState()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogMessage("onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogMessage("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogMessage("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogMessage("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogMessage("onDestroy()");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogMessage("onUserLeaveHint()");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LogMessage("onWindowFocusChanged(): " + hasFocus);
    }

    private void LogMessage(String message) {
        Log.i(TAG, message);
        editText.setText(message);
    }
}
