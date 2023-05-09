package com.aras.cvcreator.model.utilModels;

public enum YesNo {
    Yes("Yes"),
    No("No");

    private String name;

    YesNo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static YesNo getFromName(String name) {
        if (name.equals("Yes"))
            return Yes;
        else if (name.equals("No"))
            return No;
        throw new UnsupportedOperationException(
                (new StringBuilder("This code ").append(name).append(" is not supported!")).toString());
    }
}
