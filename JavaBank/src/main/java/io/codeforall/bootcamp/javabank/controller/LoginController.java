package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.view.LoginView;
import org.springframework.beans.factory.InitializingBean;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController implements InitializingBean {

    private Controller nextController;
    private boolean authFailed = false;

    /**
     * Sets the next controller
     *
     * @param nextController the next controller to set
     */
    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    /**
     * Initializes the next controller, if authentication is successful
     *
     * @param id the customer id
     */
    public void onLogin(int id) {

        if (authService.authenticate(id)) {
            nextController.init();
            return;
        }

        authFailed = true;
        init();
    }

    /**
     * Checks if authentication failed
     *
     * @return {@code true} if authentication fails
     */
    public boolean isAuthFailed() {
        return authFailed;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
