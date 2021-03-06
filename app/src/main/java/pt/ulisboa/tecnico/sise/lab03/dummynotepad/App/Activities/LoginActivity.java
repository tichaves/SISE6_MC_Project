package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSLogin;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - Login";
    private Button _buttonLogin;
    private EditText _editTextUser;
    private EditText _editTextPassword;
    private int _sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final GlobalState globalState = (GlobalState) getApplicationContext();

        _buttonLogin      = (Button)  findViewById(R.id.login_act_login_btn);
        _editTextUser     = (EditText)findViewById(R.id.login_act_email_input);
        _editTextPassword = (EditText)findViewById(R.id.login_act_pass_input);

        _buttonLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.d(LOG_TAG, "Login debug message!");
                Log.d(LOG_TAG, _editTextUser.getText().toString());
                String user = _editTextUser.getText().toString();
                String password = _editTextPassword.getText().toString();

                /*// check the user
                if (user.equals("")) {
                    Toast.makeText(v.getContext(), "Write an user", Toast.LENGTH_LONG).show();
                    return;
                }*/

                try {
                    int id = new WSLogin(user, password).execute().get();
                    Log.d(LOG_TAG,"ID VALUE:"+id);
                    if (id==0) {
                        Toast.makeText(_buttonLogin.getContext(), "Login failed: Try again!", Toast.LENGTH_LONG).show();
                    }else if (id == -1){
                        Toast.makeText(_buttonLogin.getContext(), "Server connection failed!", Toast.LENGTH_LONG).show();
                    }else {
                        _sessionId = id;
                        globalState.set_sessionId(id);
                        // return an intent containing the title and body of the new note
                        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                        // write a toast message
                        Toast.makeText(v.getContext(), "Successful Login!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                _editTextUser.setText("");
                _editTextPassword.setText("");

            }
        });
    }

}
