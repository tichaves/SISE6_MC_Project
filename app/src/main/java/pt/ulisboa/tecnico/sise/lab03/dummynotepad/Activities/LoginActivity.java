package pt.ulisboa.tecnico.sise.lab03.dummynotepad.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.InternalProtocol;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - Login";
    private Button buttonLogin;
    EditText editTextUser;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin      = (Button)  findViewById(R.id.login_act_login_btn);
        editTextUser     = (EditText)findViewById(R.id.login_act_email_input);
        editTextPassword = (EditText)findViewById(R.id.login_act_pass_input);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "Login debug message!");
                Log.d(LOG_TAG, editTextUser.getText().toString());
                String user = editTextUser.getText().toString();
                String password = editTextPassword.getText().toString();

                /*// check the user
                if (user.equals("")) {
                    Toast.makeText(v.getContext(), "Write an user", Toast.LENGTH_LONG).show();
                    return;
                }*/

                // return an intent containing the title and body of the new note
                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                intent.putExtra(InternalProtocol.KEY_USER, user);
                // write a toast message
//                Toast.makeText(v.getContext(), "Successful Login", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

            }
        });
    }

}
