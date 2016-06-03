package com.novoda.bonfire.navigation;

public interface LoginNavigator extends Navigator {

    void toGooglePlusLogin();

    void toTwitterLogin();

    void attach(LoginResultListener loginResultListener);

    void detach(LoginResultListener loginResultListener);

    interface LoginResultListener {

        void onGooglePlusLoginSuccess(String tokenId);

        void onLoginFailed(String statusMessage);

        void onTwitterLoginSuccess(String token, String secret);
    }

}
