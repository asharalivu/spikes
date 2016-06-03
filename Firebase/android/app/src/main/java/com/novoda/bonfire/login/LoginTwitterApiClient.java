package com.novoda.bonfire.login;

import android.content.Intent;

import com.novoda.bonfire.BaseActivity;
import com.novoda.bonfire.R;
import com.novoda.bonfire.navigation.LoginNavigator;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import io.fabric.sdk.android.Fabric;

public class LoginTwitterApiClient {
    private BaseActivity activity;
    private TwitterAuthClient twitterAuthClient;

    public LoginTwitterApiClient(BaseActivity activity) {
        this.activity = activity;
    }

    public void setupApiClient() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(activity.getString(R.string.twitter_api_key),
                                                             activity.getString(R.string.twitter_api_secret));
        Fabric.with(activity, new TwitterCore(authConfig));
        twitterAuthClient = new TwitterAuthClient();
    }

    public void logInWithCallback(final LoginNavigator.LoginResultListener loginResultListener) {
        twitterAuthClient.authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                loginResultListener.onTwitterLoginSuccess(result.data.getAuthToken().token, result.data.getAuthToken().secret);
            }

            @Override
            public void failure(TwitterException exception) {
                loginResultListener.onLoginFailed(exception.getMessage());
            }
        });
    }

    public int getRequestCode() {
        return twitterAuthClient.getRequestCode();
    }

    public void resolveSignInResult(int requestCode, int resultCode, Intent data) {
        twitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }
}
