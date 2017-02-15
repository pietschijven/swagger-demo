package de.pschijven.entwicklertag;

public class Greeting {

    private final Long id;
    private final String content;

    public Greeting(final Long id, final String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
