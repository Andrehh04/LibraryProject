package Model;

public enum ReadingStatus {
    READ("read"), TOREAD("to read"), READING("reading"), NONE("null");

    private final String description;
    private
    ReadingStatus(String description) {
        this.description=description;
    }

    public String toString() {
        return description;
    }
}
