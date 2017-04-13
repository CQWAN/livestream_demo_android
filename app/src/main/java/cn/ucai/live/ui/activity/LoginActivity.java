package cn.ucai.live.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import cn.ucai.live.I;
import cn.ucai.live.R;
import cn.ucai.live.data.model.IUserModel;
import cn.ucai.live.data.model.OnCompleteListener;
import cn.ucai.live.data.model.UserModel;
import cn.ucai.live.utils.CommonUtils;
import cn.ucai.live.utils.MD5;
import cn.ucai.live.utils.PreferenceManager;
import cn.ucai.live.utils.Result;
import cn.ucai.live.utils.ResultUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {


  // UI references.
  private AutoCompleteTextView mEmailView;
  private EditText mPasswordView;
  private View mProgressView;
  private View mLoginFormView;
  IUserModel model;

  String username,password;
  ProgressDialog pd;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    model = new UserModel();
    if(EMClient.getInstance().isLoggedInBefore()){
      startActivity(new Intent(this, MainActivity.class));
      finish();
      return;
    }
    setContentView(R.layout.activity_login);
    // Set up the login form.
    mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
    mPasswordView = (EditText) findViewById(R.id.password);
    mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        if (id == R.id.login || id == EditorInfo.IME_NULL) {
          attemptLogin();
          return true;
        }
        return false;
      }
    });
    initData();


    Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    mEmailSignInButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        loginAppServer();
      }
    });

    mLoginFormView = findViewById(R.id.login_form);
    mProgressView = findViewById(R.id.login_progress);

    findViewById(R.id.register).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
      }
    });
  }

  private void initData() {
    username = getIntent().getStringExtra(I.User.USER_NAME);
    mEmailView.setText(username);
  }


  /**
   * Attempts to sign in or register the account specified by the login form.
   * If there are form errors (invalid email, missing fields, etc.), the
   * errors are presented and no actual login attempt is made.
   */
  private void attemptLogin() {
//     Reset errors.
    mEmailView.setError(null);
    mPasswordView.setError(null);

//     Store values at the time of the login attempt.
    Editable email = mEmailView.getText();
    Editable password = mPasswordView.getText();

    boolean cancel = false;
    View focusView = null;

//     Check for a valid password, if the user entered one.
    if (TextUtils.isEmpty(password)) {
      mPasswordView.setError(getString(R.string.error_invalid_password));
      focusView = mPasswordView;
      cancel = true;
    }

//     Check for a valid email address.
    if (TextUtils.isEmpty(email)) {
      mEmailView.setError(getString(R.string.error_invalid_email));
      focusView = mEmailView;
      cancel = true;
    }

    if (cancel) {
//       There was an error; don't attempt login and focus the first
//       form field with an error.
      focusView.requestFocus();
    } else {
//       Show a progress spinner, and kick off a background task to
//       perform the user login attempt.
      showProgress(true);
      EMClient.getInstance().login(/*email.toString()*/username, password.toString(), new EMCallBack() {
        @Override public void onSuccess() {
          PreferenceManager.getInstance().setCurrentUserName(username); // 将用户名保存到首选项中
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        @Override public void onError(int i, final String s) {
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              showProgress(false);
              mPasswordView.setError(s);
              mPasswordView.requestFocus();
            }
          });
        }

        @Override public void onProgress(int i, String s) {
        }
      });

    }
  }
  public boolean checkInput() {
    username = mEmailView.getText().toString().trim();
    password = mPasswordView.getText().toString().trim();
    if (TextUtils.isEmpty(username)) {
      Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
      mEmailView.requestFocus();
      return false;
    } else if (TextUtils.isEmpty(password)) {
      Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
      mPasswordView.requestFocus();
      return false;
    }
    return true;
  }


  public void loginAppServer() {
    if (checkInput()) {
      showDialog();
      model.login(LoginActivity.this, username, MD5.getMessageDigest(password),
              new OnCompleteListener<String>() {
                @Override
                public void onSuccess(String s) {
                  boolean success = false;
                  if (s != null) {
                    Result result = ResultUtils.getResultFromJson(s, String.class);
                    if (result != null) {
                      if (result.isRetMsg()) {
                        success = true;
                        attemptLogin();
                      } else if (result.getRetCode() == I.MSG_REGISTER_USERNAME_EXISTS) {
                        CommonUtils.showShortToast(R.string.User_already_exists);
                      } else {
                        CommonUtils.showShortToast(R.string.Login_failed);
                      }
                    }
                  }
                  if (!success) {
                    pd.dismiss();
                  }
                }

                @Override
                public void onError(String error) {
                  pd.dismiss();
                  CommonUtils.showShortToast(R.string.Login_failed);
                }
              });
    }
  }

  private void showDialog(){
    pd = new ProgressDialog(LoginActivity.this);
    pd.setMessage("正在登录...");
    pd.setCanceledOnTouchOutside(false);
    pd.show();
  }


  /**
   * Shows the progress UI and hides the login form.
   */
  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2) private void showProgress(final boolean show) {
    // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
    // for very easy animations. If available, use these APIs to fade-in
    // the progress spinner.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

      mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
      mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
        @Override public void onAnimationEnd(Animator animation) {
          mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
      });

      mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
      mProgressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
        @Override public void onAnimationEnd(Animator animation) {
          mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
      });
    } else {
      // The ViewPropertyAnimator APIs are not available, so simply show
      // and hide the relevant UI components.
      mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
      mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
  }
}

