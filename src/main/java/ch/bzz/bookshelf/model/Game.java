package ch.bzz.bookshelf.model;

import java.math.BigDecimal;


public class Game {
    private String gameUUID;
    private String title;
    private String author;
    private Publisher publisher;
    private BigDecimal price;
    private String isbn;

    public Game() {
        setGameUUID(null);
        setTitle(null);
        setAuthor(null);
        setPublisher(null);
        setPrice(null);
        setIsbn(null);
    }

    public String getGameUUID() {
        return gameUUID;
    }



    public void setGameUUID(String gameUUID) {
        this.gameUUID = gameUUID;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }



    public void setAuthor(String author) {
        this.author = author;
    }


    public Publisher getPublisher() {
        return publisher;
    }



    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }


    public BigDecimal getPrice() {
        return price;
    }



    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getIsbn() {
        return isbn;
    }



    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}