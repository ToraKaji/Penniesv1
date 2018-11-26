package deepdive.cnm.edu.penniesv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


    public class SignInActivity extends AppCompatActivity {

      private static final int REQUEST_CODE = 1010;

      SignInButton signIn;

      public static GoogleSignInAccount account;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signIn = findViewById(R.id.sign_in_button);
        signIn.setOnClickListener((view) -> signin());

      }

      @Override
      protected void onStart() {
        super.onStart();
        account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
          Penniesv1Application.getInstance().setAccount(account);
          switchToMain();
        }
      }

      @Override
      protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode== REQUEST_CODE){
          try {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Penniesv1Application.getInstance().setAccount(account);
          } catch (ApiException e) {
            //e.printStackTrace();
            Toast.makeText(this, "Error signing in.", Toast.LENGTH_LONG).show();
          }
          switchToMain();
        }
      }


      private void signin(){
        Intent intent = Penniesv1Application.getInstance().getClient().getSignInIntent();
        startActivityForResult(intent, REQUEST_CODE);
      }

      private void switchToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

      }
    }
