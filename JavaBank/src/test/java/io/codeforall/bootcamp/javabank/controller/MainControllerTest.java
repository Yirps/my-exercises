package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MainControllerTest {

    private MainController mainController;
    private Controller nextController;
    private AuthService authService;
    private View mainView;

    @Before
    public void setup(){

        mainController = new MainController();

        authService = mock(AuthService.class);
        mainView = mock(View.class);
        nextController = mock(Controller.class);

        mainController.setAuthService(authService);
        mainController.setView(mainView);

    }

    @Test
    public void initTest(){
        mainController.init();
        verify(mainView).show();
    }

    @Test
    public void onMenuSelectionTest(){

        when(mainController.onMenuSelection()).thenReturn(true);
    }
}
