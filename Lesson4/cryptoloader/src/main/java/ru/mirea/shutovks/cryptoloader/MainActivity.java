package ru.mirea.shutovks.cryptoloader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import javax.crypto.SecretKey;

import ru.mirea.shutovks.cryptoloader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private ActivityMainBinding binding;

    private static final String TAG = "MainActivity";
    private static final int LOADER_ID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(this::onClickButton);
    }

    public void onClickButton(View view) {
        EditText et = findViewById(R.id.editTextInput);
        String plain = et.getText().toString();
        if (plain.isEmpty()) {
            Toast.makeText(this, "Введите фразу", Toast.LENGTH_SHORT).show();
            return;
        }

        SecretKey secret = CryptoUtils.generateKey();
        byte[] cipherText = CryptoUtils.encryptMsg(plain, secret);

        Bundle bundle = new Bundle();
        bundle.putByteArray(MyLoader.ARG_CIPHER, cipherText);
        bundle.putByteArray(MyLoader.ARG_KEY, secret.getEncoded());

        LoaderManager.getInstance(this)
                .restartLoader(LOADER_ID, bundle, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if (id != LOADER_ID) {
            throw new IllegalArgumentException("Unknown loader");
        }

        Toast.makeText(this, "onCreateLoader", Toast.LENGTH_SHORT).show();

        return new MyLoader(this, args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String decrypted) {
        Toast.makeText(this, "Расшифровка: " + decrypted, Toast.LENGTH_LONG).show();
        Log.d(TAG, "Decrypted text: " + decrypted);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }
}
