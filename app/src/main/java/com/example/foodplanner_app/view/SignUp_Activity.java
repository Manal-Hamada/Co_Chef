package com.example.foodplanner_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp_Activity extends AppCompatActivity {

    TextView loginTv;
    Button signUp;
    TextInputEditText usernameEdt, mailEdt, passwordEdt, confirmPasswordEdt;
    String username, mail, password, confirmPassword;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginTv = findViewById(R.id.login_tv);
        signUp = findViewById(R.id.signupBtn);
        usernameEdt = findViewById(R.id.edt_username);
        mailEdt = findViewById(R.id.edt_mail);
        passwordEdt = findViewById(R.id.edt_password);
        confirmPasswordEdt = findViewById(R.id.edt_confirm_password);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        username= usernameEdt.getText().toString();
        mail= mailEdt.getText().toString();
        password= passwordEdt.getText().toString();
        confirmPassword= confirmPasswordEdt.getText().toString();

        validateUsername();
        validateMail();
        validatePassword();
        validateConfirmPassword();

        setSignUpAction();
        setLoginTvAction();

    }

    private void createUser(String mail, String password) {
        firebaseAuth.createUserWithEmailAndPassword(mail, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignUp_Activity.this, "success signup", Toast.LENGTH_SHORT).show();
                        firebaseFirestore.collection("User")
                                .document(firebaseAuth.getUid())
                                .set(new UserModel(usernameEdt.getText().toString(), mailEdt.getText().toString()));
                        navToLogin();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp_Activity.this, "failed to signup", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
    }

    public void setLoginTvAction() {

        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToLogin();

            }
        });
    }

    public void navToLogin() {
        Intent intent = new Intent(SignUp_Activity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void setSignUpAction() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidDataInput())
                    createUser(mailEdt.getText().toString(), passwordEdt.getText().toString());
                else {
                     Snackbar.make(findViewById(android.R.id.content), "Please enter valid data", Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.RED).show();
                }
            }
        });
    }
    private boolean isValidDataInput(){
        username= usernameEdt.getText().toString();
        mail= mailEdt.getText().toString();
        password= passwordEdt.getText().toString();
        confirmPassword= confirmPasswordEdt.getText().toString();
        return LoginActivity.isValidMail(mail) && LoginActivity.isValidUsername(username) && LoginActivity.isValidPassword(password) && password.equals(confirmPassword);
    }

    private void validateMail() {
        TextInputLayout textInputLayout = findViewById(R.id.outlinedTextField_mail);
        TextInputEditText edtPassword = findViewById(R.id.edt_mail);

        mailEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!LoginActivity.isValidMail(mailEdt.getText().toString().trim())) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("Not a valid mail");
                    } else
                        textInputLayout.setErrorEnabled(false);
                }
            }
        });
    }

    private void validatePassword() {
        TextInputLayout textInputLayout = findViewById(R.id.outlinedTextField_password);
        TextInputEditText edtPassword = findViewById(R.id.edt_password);

        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!LoginActivity.isValidPassword(passwordEdt.getText().toString())) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("Weak password");
                    } else
                        textInputLayout.setErrorEnabled(false);
                }
            }
        });
    }

    private void validateUsername() {
        TextInputLayout textInputLayout = findViewById(R.id.outlinedTextField_username);
        TextInputEditText edtPassword = findViewById(R.id.edt_username);

        usernameEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!LoginActivity.isValidUsername(usernameEdt.getText().toString().trim())) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("Not a valid username");
                    } else
                        textInputLayout.setErrorEnabled(false);
                }
            }
        });
    }

    private void validateConfirmPassword() {
        TextInputLayout textInputLayout = findViewById(R.id.outlinedTextField_confirm_password);
        TextInputEditText edtPassword = findViewById(R.id.edt_confirm_password);

        confirmPasswordEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!passwordEdt.getText().toString().equals(confirmPasswordEdt.getText().toString())) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("Not matching the password");
                    } else
                        textInputLayout.setErrorEnabled(false);
                }
            }
        });
    }
}