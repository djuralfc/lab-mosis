package com.djuralfc.myplaces;

/**
 * Created by bolee on 19.4.18..
 */

public class MyPlace {
    String name;
    String description;

    public MyPlace(String name, String desc)
    {
        this.name = name;
        this.description=desc;

    }

    public MyPlace(String name)
    {
        this(name,"");
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String desc)
    {
        this.description = desc;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

}
