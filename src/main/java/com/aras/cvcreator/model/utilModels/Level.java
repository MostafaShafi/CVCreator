package com.aras.cvcreator.model.utilModels;

public enum Level {
    Junior('J'),
    MidLevel('M'),
    Senior('S');

    private final Character code;

    Level(Character code) {
        this.code = code;
    }

    public Character getCode() {
        return code;
    }

    public static Level getFromCode(Character code) {
        if (code == 'J' || code == 'j')
            return Junior;
        else if (code == 'M' || code == 'm')
            return MidLevel;
        else if (code == 'S' || code == 's')
            return Senior;
        throw new UnsupportedOperationException(
                (new StringBuilder("This code ").append(code).append(" is not supported!")).toString());
    }
}
