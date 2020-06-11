package Reaktives_System;

public class FileEvent
{
    /**name of the file*/
    private String                  name;
    /**symbol which leads to the next state*/
    private DirectoryWatcher.Symbol symbol;

    public FileEvent(String name, DirectoryWatcher.Symbol symbol)
    {
        this.name   = name;
        this.symbol = symbol;
    }

    public void setSymbol(DirectoryWatcher.Symbol symbol)
    {
        this.symbol = symbol;
    }

    public String getName()
    {
        return name;
    }

    public DirectoryWatcher.Symbol getSymbol()
    {
        return symbol;
    }
}
