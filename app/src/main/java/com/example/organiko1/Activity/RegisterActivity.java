package com.example.organiko1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.organiko1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {


        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            // Inicializa la instancia de FirebaseAuth
            mAuth = FirebaseAuth.getInstance();

            // Configura el evento del botón de registro
            Button registerButton = findViewById(R.id.registrar);
            EditText correo = findViewById(R.id.correo);
            EditText pss = findViewById(R.id.clave);


            String email1 = correo.getText().toString();
            String password1 = pss.getText().toString();


            TextView login = findViewById(R.id.);


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


                    //si el email o la contraseñ a estan vacios manda mensaje de error
                    if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)) {
                        Toast.makeText(RegisterActivity.this, getText(R.string.camposObligatoriosMessg), Toast.LENGTH_SHORT).show();
                    } else {
                        mAuth.createUserWithEmailAndPassword(email1, password1)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // El registro fue exitoso
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            // Realiza las acciones necesarias después del registro exitoso
                                        } else {
                                            // El registro falló
                                            Toast.makeText(RegisterActivity.this, "Registro fallido.",
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
    }
