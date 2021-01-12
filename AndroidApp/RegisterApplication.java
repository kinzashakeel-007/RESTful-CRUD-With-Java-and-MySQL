import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {
private static final String TAG = RegisterActivity.class.getSimpleName();

private MaterialButton btnRegister, btnLinkToLogin;
private TextInputLayout inputName, inputEmail, inputPassword;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_register);

inputName = findViewById(R.id.edit_name);
inputEmail = findViewById(R.id.edit_email);
inputPassword = findViewById(R.id.edit_password);
btnRegister = findViewById(R.id.button_register);
btnLinkToLogin = findViewById(R.id.button_login);

getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

init();
}

private void init() {
btnRegister.setOnClickListener(view -> {
Functions.hideSoftKeyboard(RegisterActivity.this);

String name = Objects.requireNonNull(inputName.getEditText()).getText().toString().trim();
String email = Objects.requireNonNull(inputEmail.getEditText()).getText().toString().trim();
String password = Objects.requireNonNull(inputPassword.getEditText()).getText().toString().trim();

if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
if (Functions.isValidEmailAddress(email)) {
registerUser(name, email, password);
} else {
Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();
}
} else {
Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
}
});
btnLinkToLogin.setOnClickListener(view -> {
Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
startActivity(i);
finish();
});
}

private void registerUser(final String name, final String email, final String password) {
String tag_string_req = "req_register";

showDialog();

StringRequest strReq = new StringRequest(Request.Method.POST,
Functions.REGISTER_URL, response -> {
Log.d(TAG, "Register Response: " + response);
hideDialog();

try {
JSONObject jObj = new JSONObject(response);
boolean error = jObj.getBoolean("error");
if (!error) {
Functions logout = new Functions();
logout.logoutUser(getApplicationContext());

Bundle b = new Bundle();
b.putString("email", email);
Intent i = new Intent(RegisterActivity.this, EmailVerify.class);
i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
i.putExtras(b);
startActivity(i);
finish();

} else {
String errorMsg = jObj.getString("message");
Toast.makeText(getApplicationContext(),errorMsg, Toast.LENGTH_LONG).show();
}
} catch (JSONException e) {
e.printStackTrace();
}

}, error -> {
Log.e(TAG, "Registration Error: " + error.getMessage(), error);
Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
hideDialog();
}) {

@Override
protected Map<String, String> getParams() {
Map<String, String> params = new HashMap<>();
params.put("name", name);
params.put("email", email);
params.put("password", password);

return params;
}

};
MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
}

private void showDialog() {
Functions.showProgressDialog(RegisterActivity.this, "Registering ...");
}
private void hideDialog() {
Functions.hideProgressDialog(RegisterActivity.this);
}
}