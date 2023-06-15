package com.example.organiko1.Activity;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.organiko1.R;
import com.example.organiko1.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

        private ActivityLoginBinding binding;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityLoginBinding.inflate(getLayoutInflater());
            View view = binding.getRoot();
            setContentView(view);

            EditText email = findViewById(R.id.email);
            EditText password = findViewById(R.id.psswrd);
            Button acceder = findViewById(R.id.acceder);

            TextView registro = findViewById(R.id.registro);
            //TODO(4): inicializar el atributo auth a la instancia de FirebaseAuth


            registro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
            FirebaseAuth auth;

            //TODO(4): inicializar el atributo auth a la instancia de FirebaseAuth
            auth = FirebaseAuth.getInstance();


            acceder.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ProgressDialog pd = showProgressDialog(LoginActivity.this);

                    String email1 = email.getText().toString();
                    String password1 = password.getText().toString();

                    //si el email o la contraseñ a estan vacios manda mensaje de error
                    if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)) {
                        Toast.makeText(LoginActivity.this, getText(R.string.camposObligatoriosMessg), Toast.LENGTH_SHORT).show();
                    } else {
                        auth.signInWithEmailAndPassword(email1, password1)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@androidx.annotation.NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            pd.dismiss();
                                            // El inicio de sesión fue exitoso
                                            String token =  task.getResult().getUser().getIdToken(true).toString();
                                            FirebaseUser user = auth.getCurrentUser();
                                           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                           intent.putExtra("token", token);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            // El inicio de sesión falló
                                            Toast.makeText(LoginActivity.this, "Inicio de sesión fallido.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });
        }

        ProgressDialog showProgressDialog(Context context) {
            final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
            pd.setMessage(context.getString(R.string.espere));
            pd.show();
            return pd;
        }
}