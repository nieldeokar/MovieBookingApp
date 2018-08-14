package com.tentwenty.movieticket;

public class TheaterLayoutNew
{
    private String[] values;

    private String row;

    public String[] getValues ()
    {
        return values;
    }

    public void setValues (String[] values)
    {
        this.values = values;
    }

    public String getRow ()
    {
        return row;
    }

    public void setRow (String row)
    {
        this.row = row;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [values = "+values+", rowName = "+row+"]";
    }
}
	