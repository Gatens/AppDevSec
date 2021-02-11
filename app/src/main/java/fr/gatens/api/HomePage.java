package fr.gatens.api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    EditText logEmail, logPassword;
    Button logButton, register;

    FirebaseAuth fAuth;
    ProgressBar logProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        logEmail = findViewById(R.id.logEmail);
        logPassword = findViewById(R.id.logPassword);
        fAuth= FirebaseAuth.getInstance();
        logButton=findViewById(R.id.logButton);
        register=findViewById(R.id.register);
        logProgressBar = findViewById(R.id.logProgressBar);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = logEmail.getText().toString().trim();
                String password = logPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    logEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    logPassword.setError("Password is required");
                    return;
                }

                logProgressBar.setVisibility((View.VISIBLE));

                // authenticate

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(HomePage.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(HomePage.this, "Error! \n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            logProgressBar.setVisibility((View.GONE));
                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }
}

