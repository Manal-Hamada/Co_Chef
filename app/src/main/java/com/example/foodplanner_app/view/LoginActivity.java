package com.example.foodplanner_app.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.UserModel;
import com.example.foodplanner_app.view.HomeActivity;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    TextInputEditText mailEdt, passwordEdt;
    TextView forgetPasswordTv;
    TextView signUpTv;
    ImageView googleImg, facebookImg;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String mail, password, id;
    UserModel user;
    BeginSignInRequest signInRequest;
    private GoogleSignInClient googleClient;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAGGGG", "onActivityResult");
            //Toast.makeText(this, "onActivityResult", Toast.LENGTH_SHORT).show();
        if (requestCode==123){
            Log.d("TAGGGG", "req code 123");
            //Toast.makeText(this, "req code 123", Toast.LENGTH_SHORT).show();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleAccount = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(googleAccount.getIdToken(),null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    id = googleAccount.getIdToken();
                                    Log.d("TAGGGG", "id: "+id);
                                    Toast.makeText(LoginActivity.this, id, Toast.LENGTH_SHORT).show();
                                    storeId(id);
                                    navigateToHome();
                                }else {
                                    Log.d("TAGGGG", "not successful ");
                                    Toast.makeText(LoginActivity.this, "zzzzzzzzz", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                String id = firebaseUser.getUid();
                                String email = firebaseUser.getEmail();

                                Log.d("TAGGGG", "id = "+id);
                                Log.d("TAGGGG", "email = "+email);

                                if (authResult.getAdditionalUserInfo().isNewUser()){
                                    Log.d("TAGGGG", "new user");
                                }else {
                                    Log.d("TAGGGG", "existing user");
                                }
                                storeId(id);
                                navigateToHome();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAGGGG", "failed to signin");
                            }
                        });
            } catch (ApiException e) {
                Log.d("TAGGGG", "ApiException");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        mailEdt = findViewById(R.id.edt_mail);
        passwordEdt = findViewById(R.id.edt_password);
        forgetPasswordTv = findViewById(R.id.forget_password_tv);
        signUpTv = findViewById(R.id.signup_tv);
        googleImg = findViewById(R.id.google_imgView);
        facebookImg = findViewById(R.id.facebook_imgView);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        user = new UserModel();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleClient = GoogleSignIn.getClient(this, googleSignInOptions);
        googleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleIntent = googleClient.getSignInIntent();
                startActivityForResult(googleIntent,123);
            }
        });


//        createGoogleRequest();
//        signInWithGoogle();

        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });
        forgetPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = mailEdt.getText().toString().trim();
                firebaseAuth.sendPasswordResetEmail(mail)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginActivity.this, "please check your mail inbox", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = mailEdt.getText().toString().trim();
                password = passwordEdt.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(mail, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(LoginActivity.this, "success login", Toast.LENGTH_SHORT).show();
                                id = firebaseFirestore.collection("User")
                                        .document(firebaseAuth.getUid())
                                        .getId();
                                storeId(id);
                                navigateToHome();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                Log.i("TAG", "onFailure: " + e.toString());
                            }
                        });
            }
        });
        validatePassword();
        validateMail();
    }

    private void signInWithGoogle() {
        //Intent signInIntent = mGoo
    }

    private void createGoogleRequest() {
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            navigateToHome();
        }
    }

    private void validateMail() {
        TextInputLayout textInputLayout = findViewById(R.id.outlinedTextField_mail);
        TextInputEditText edtPassword = findViewById(R.id.edt_mail);

        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                        textInputLayout.setError("Not a valid password");
                    } else
                        textInputLayout.setErrorEnabled(false);
                }
            }
        });
    }

    private void navigateToSignUp() {
        Intent i = new Intent(LoginActivity.this, SignUp_Activity.class);
        startActivity(i);
        finish();
    }

    private void storeId(String id) {
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("loggedId", id);
        editor.apply();
    }

    private void navigateToHome() {
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    public static boolean isValidMail(String emailAddress) {
        String regexPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public static boolean isValidUsername(String username) {
        String regexPattern = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        return Pattern.compile(regexPattern)
                .matcher(username)
                .matches();
    }

    public static boolean isValidPassword(String password) {
        String regexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        return Pattern.compile(regexPattern)
                .matcher(password)
                .matches();
    }
}