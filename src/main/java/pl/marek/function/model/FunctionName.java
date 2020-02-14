package pl.marek.function.model;

public enum FunctionName {

    SPHERE("SPHERE"),
    ACKLEY("ACKLEY"),
    MICHALEWICZ("MICHALEWICZ"),
    RASTRIGIN("RASTRIGIN"),
    SCHWEFEL("SCHWEFEL");

    private final String name;

    FunctionName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
