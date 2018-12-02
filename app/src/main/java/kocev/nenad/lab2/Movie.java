package kocev.nenad.lab2;

public class Movie {
    private String Title;
    private String Year;
    private String Released;
    private String Genre;
    private String Director;
    private String Plot;
    private String Poster;
    private String imdbRating;
    private String imdbID;

    public Movie(String title, String year, String released, String genre, String director, String plot, String poster, String imdbRating, String imdbID) {
        Title = title;
        Year = year;
        Released = released;
        Genre = genre;
        Director = director;
        Plot = plot;
        Poster = poster;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getReleased() {
        return Released;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getPlot() {
        return Plot;
    }

    public String getPoster() {
        return Poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }
}

