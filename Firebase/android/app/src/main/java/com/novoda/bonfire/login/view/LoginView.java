package com.novoda.bonfire.login.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.novoda.bonfire.R;
import com.novoda.bonfire.login.displayer.LoginDisplayer;
import com.novoda.notils.caster.Views;

public class LoginView extends LinearLayout implements LoginDisplayer {

    private View googleLoginButton;
    private TwitterLoginButton twitterLoginButton;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_login_view, this);
        googleLoginButton = Views.findById(this, R.id.google_sign_in_button);
        twitterLoginButton = Views.findById(this, R.id.twitter_login_button);
    }

    @Override
    public void attach(final LoginActionListener actionListener) {
        googleLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actionListener.onGooglePlusLoginSelected();
            }
        });
        twitterLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actionListener.onTwitterLoginSelected();
            }
        });
    }

    @Override
    public void detach(LoginActionListener actionListener) {
        googleLoginButton.setOnClickListener(null);
        twitterLoginButton.setOnClickListener(null);
    }

    @Override
    public void showAuthenticationError(String message) {
        Toast.makeText(getContext(), "Login failed: " + message, Toast.LENGTH_LONG).show(); //TODO improve error display
    }

}
