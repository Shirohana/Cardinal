package me.shirohana.cardinal.modules;

public abstract class CardinalModule implements Module {

    protected String name = "";
    protected String version = "";
    protected boolean debug = false;

    protected CardinalModule() { }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public boolean debug() {
        return debug;
    }

}
