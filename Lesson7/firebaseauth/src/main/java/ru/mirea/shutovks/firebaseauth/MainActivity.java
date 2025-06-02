package ru.mirea.shutovks.firebaseauth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText etEmail;
    private EditText etPassword;
    private TextView tvMessage;
    private TextView tvEmailInfo;
    private TextView tvUidInfo;
    private Button btnRegister;
    private Button btnLogin;
    private Button btnVerify;
    private Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvMessage = findViewById(R.id.tvMessage);
        tvEmailInfo = findViewById(R.id.tvEmailInfo);
        tvUidInfo = findViewById(R.id.tvUidInfo);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnVerify = findViewById(R.id.btnVerify);
        btnSignOut = findViewById(R.id.btnSignOut);

        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            if (!email.isEmpty() && !pass.isEmpty()) {
                auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                if (user != null) {
                                    user.sendEmailVerification();
                                }
                                showProfile(user);
                            } else {
                                tvMessage.setText("Ошибка регистрации");
                                tvMessage.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            if (!email.isEmpty() && !pass.isEmpty()) {
                auth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                showProfile(auth.getCurrentUser());
                            } else {
                                tvMessage.setText("Ошибка входа");
                                tvMessage.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });

        btnVerify.setOnClickListener(v -> {
            FirebaseUser user = auth.getCurrentUser();
            if (user != null && !user.isEmailVerified()) {
                user.sendEmailVerification()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Письмо отправлено", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Ошибка отправки письма", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btnSignOut.setOnClickListener(v -> {
            auth.signOut();
            showLoginScreen();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            showProfile(user);
        } else {
            showLoginScreen();
        }
    }

    private void showLoginScreen() {
        etEmail.setVisibility(View.VISIBLE);
        etPassword.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.VISIBLE);
        tvMessage.setVisibility(View.GONE);

        tvEmailInfo.setVisibility(View.GONE);
        tvUidInfo.setVisibility(View.GONE);
        btnVerify.setVisibility(View.GONE);
        btnSignOut.setVisibility(View.GONE);
    }

    private void showProfile(FirebaseUser user) {
        if (user == null) return;

        etEmail.setVisibility(View.GONE);
        etPassword.setVisibility(View.GONE);
        btnRegister.setVisibility(View.GONE);
        btnLogin.setVisibility(View.GONE);
        tvMessage.setVisibility(View.GONE);

        tvEmailInfo.setVisibility(View.VISIBLE);
        tvUidInfo.setVisibility(View.VISIBLE);
        btnVerify.setVisibility(View.VISIBLE);
        btnSignOut.setVisibility(View.VISIBLE);

        String email = user.getEmail() != null ? user.getEmail() : "--";
        String verified = user.isEmailVerified() ? "true" : "false";
        tvEmailInfo.setText("Email User: " + email + " (verified: " + verified + ")");
        tvUidInfo.setText("Firebase UID: " + user.getUid());
    }
}
