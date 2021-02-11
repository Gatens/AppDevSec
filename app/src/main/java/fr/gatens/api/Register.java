package fr.gatens.api;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText regEmail, regPassword;
    Button regDatabase, regLogButton;
    TextView regProvider;
    FirebaseAuth fAuth;
    ProgressBar regProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        regDatabase = findViewById(R.id.regButton);
        regLogButton=findViewById(R.id.regLogButton);

        fAuth = FirebaseAuth.getInstance();
        regProgressBar = findViewById(R.id.regProgressBar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        regDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regEmail.getText().toString().trim();
                String password = regPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    regEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    regPassword.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    regPassword.setError("Password length must be greater than 6 characters");
                }

                regProgressBar.setVisibility((View.VISIBLE));

                // authenticate

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, "Error! \n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            regProgressBar.setVisibility((View.GONE));
                        }
                    }
                });
            }
        });

        regLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });
    }
}