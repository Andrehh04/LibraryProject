package Model;

public class Book {
    final String title;
    final String author;
    final String isbn;
    String genre;
    Rating rating;
    ReadingStatus readingStatus;

    //utilizzo il builder per gestire la creazione del libro
    public static class Builder{
        final String title;
        final String author;
        final String isbn;
        String genre = "null";
        Rating rating = Rating.NONE;
        ReadingStatus readingStatus = ReadingStatus.NONE;

        public Builder(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
        }

        public Builder setGenre(String g){
            genre=g;
            return this;
        }
        public Builder setRating(Rating r){
            rating=r;
            return this;
        }
        public Builder setReadingStatus(ReadingStatus r){
            readingStatus=r;
            return this;
        }

        public Book build(){
            return new Book(this);
        }
    }

    //il costruttore della classe Book è privato perchè lo si può chiamare solamente col metodo build() di Builder
    private Book(Builder builder){
        if(builder.title == null)
            this.title="Unknown title";
        else
            this.title=builder.title;
        if(builder.author == null)
            this.author="Unknown author";
        else
            this.author=builder.author;
        if(builder.isbn == null)
            this.isbn="Unknown isbn";
        else
            this.isbn=builder.isbn;
        this.genre=builder.genre;
        this.rating=builder.rating;
        this.readingStatus=builder.readingStatus;
    }

    //aggiungo i metodi setter per permettere la modifica successiva alla creazione dei campi mutabili
    public void setGenre(String genre){
        this.genre=genre;
    }
    public void setRating(Rating rating){
        this.rating=rating;
    }
    public void setreadingStatus(ReadingStatus readingStatus){
        this.readingStatus=readingStatus;
    }

    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getIsbn(){
        return isbn;
    }
    public Rating getRating(){
        return rating;
    }
    public ReadingStatus getReadingStatus() {
        return readingStatus;
    }
    public String getGenre(){
        return genre;
    }

    public String toString(){
        return "Titolo: "+title+"\nAutore: "+author+"\nISBN: "+isbn+"\nGenere: "+genre+"\nStato: "+readingStatus.toString()+"\nValutazione: "+rating.toString();
    }
}
