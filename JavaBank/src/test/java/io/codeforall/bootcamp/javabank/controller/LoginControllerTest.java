package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private Controller nextController;
    private AuthService authService;
    private View loginView;

    //Setup

    @Before
    public void setup(){

        loginController = new LoginController();

        //mock auth service, next controller and view
        authService = mock(AuthService.class);
        loginView = mock(View.class);
        nextController = mock(Controller.class);

        loginController.setAuthService(authService);
        loginController.setView(loginView);
        loginController.setNextController(nextController);
    }

    @Test
    public void initTest(){

        loginController.init();
        verify(loginView).show();
    }

    @Test
    public void onLoginTest(){

        //fake user ID
        int fakeId = 123213232;

        when(authService.authenticate(fakeId)).thenReturn(true);

        loginController.onLogin(fakeId);

        //verify authenticate been called with proper id
        verify(authService).authenticate(fakeId);

        //verify nextControllers init method has been called
        verify(nextController).init();

        //verify that loginView was never presented
        verify(loginView, never()).show();
    }
}
