package io.codeforall.bootcamp.javabank.application.view;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.UserOptions;
import io.codeforall.bootcamp.javabank.domain.Bank;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class MainMenuPromptView extends AbstractPromptView {
    private final MenuInputScanner scanner;
    private static final String message = Messages.MENU_WELCOME;
    private static final String error = Messages.ERROR_INVALID_OPTION;

    public MainMenuPromptView(Bank bank) {
        super(bank);
        this.scanner = new MenuInputScanner(UserOptions.getMessages());
        scanner.setMessage(message);
        scanner.setError(error);
    }

    @Override
    public void show() {
        Integer userChoice = getMenuChoice();
        while(userChoice != UserOptions.QUIT.getOption()) {
            // TODO: Select an operation
            userChoice = getMenuChoice();
        }
    }

    private Integer getMenuChoice() {
        return getPrompt().getUserInput(scanner);
    }
}
