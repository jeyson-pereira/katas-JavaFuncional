package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream()
                .flatMap(movie -> movie.getBoxarts().stream())
                .reduce((boxArt1,boxArt2) -> (boxArt1.getWidth()* boxArt1.getHeight()) > (boxArt2.getWidth()* boxArt2.getHeight()) ? boxArt1:boxArt2)
                .map(BoxArt::getUrl)
                .orElse("");
    }
}
