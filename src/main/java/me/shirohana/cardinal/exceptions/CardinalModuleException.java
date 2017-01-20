package me.shirohana.cardinal.exceptions;

import me.shirohana.cardinal.modules.Module;

public class CardinalModuleException extends CardinalException {

    private static final long serialVersionUID = -857658665966624544L;
    private final Module module;

    public CardinalModuleException(Module module) {
        super();

        this.module = module;
    }

    public CardinalModuleException(Module module, String message) {
        super(message);

        this.module = module;
    }

    public CardinalModuleException(Module module, String message, Throwable cause) {
        super(message, cause);

        this.module = module;
    }

    public CardinalModuleException(Module module, Throwable cause) {
        super(cause);

        this.module = module;
    }

    public Module getModule() {
        return module;
    }

}
