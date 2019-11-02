package com.alsjava.courses.servers.ui;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * Created by aluis on 11/2/19.
 */
public class Login extends Dialog {

    private LoginListener loginListener;

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    private void fireLogin() {
        if (loginListener != null) {
            loginListener.login();
        }
    }

    public interface LoginListener {
        void login();
    }

    public interface LoginModel extends TemplateModel {

        void setAppLogo(String appLogo);
    }
}
