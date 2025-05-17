package Model;

public enum ReadingStatus {
    READ("letto"), TOREAD("da leggere"), READING("in lettura"), NONE("null");

    private final String description;
    private
    ReadingStatus(String description) {
        this.description=description;
    }

    public String toString() {
        return description;
    }
}
