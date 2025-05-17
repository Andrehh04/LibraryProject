package Model;

public enum Rating {
    UNO(1), DUE(2), TRE(3), QUATTRO(4), CINQUE(5), NONE(-1);

    private final int value;
    private Rating(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
