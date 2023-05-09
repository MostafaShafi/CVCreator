package com.aras.cvcreator.model.utilModels;


public enum UserType {
    Employee("Employee"),
    Employer("Employer");

    private final String name;

    UserType(String code) {
        this.name = code;
    }

    public String getName() {
        return name;
    }

    public static UserType getFromCode(String name) {
        if (name == "Employee")
            return Employee;
        else if (name == "Employer")
            return Employer;
        throw new UnsupportedOperationException(
                (new StringBuilder("This type ").append(name).append(" is not supported!")).toString());
    }
}
