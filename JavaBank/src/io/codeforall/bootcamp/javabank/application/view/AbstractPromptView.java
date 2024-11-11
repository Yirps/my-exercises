package io.codeforall.bootcamp.javabank.application.view;

import io.codeforall.bootcamp.javabank.domain.Bank;
import org.academiadecodigo.bootcamp.Prompt;

public abstract class AbstractPromptView extends AbstractView {
    private final Prompt prompt;

    public AbstractPromptView(Bank bank) {
        super(bank);
        this.prompt = new Prompt(System.in, System.out);
    }

    /**
     * Gets the prompt used for the UI
     *
     * @return the prompt
     */
    public Prompt getPrompt() {
        return prompt;
    }

    @Override
    public abstract void show();
}
