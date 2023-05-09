package com.aras.cvcreator.model.utilModels;

public enum LocationType {
    Country(0, "Country"),
    City(1, "City"),
    State(2, "State");

    private final int id;
    private final String name;

    LocationType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static LocationType getFromId(int id) {
        if (id == 0)
            return Country;
        else if (id == 1)
            return City;
        else if (id == 2)
            return State;
        throw new UnsupportedOperationException(
                (new StringBuilder("This code ").append(id).append(" is not supported!")).toString());
    }
}
