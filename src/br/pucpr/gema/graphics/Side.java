package br.pucpr.gema.graphics;

public enum Side {
    RIGHT(0),
    LEFT(1),
    TOP(2),
    BOTTOM(3),
    BACK(4),
    FRONT(5);

    private final int value;

    Side(int value) {
        this.value = value;
    }

    public static Side valueOf(int i) {
        return Side.values()[i];
    }
}
