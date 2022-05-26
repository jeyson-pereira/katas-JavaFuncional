package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream().flatMap(list -> list.getVideos().stream())
                .map(movie -> ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(), "boxart", getUrlBoxArt(movie.getBoxarts())))
                .collect(Collectors.toUnmodifiableList());
    }

    public static String getUrlBoxArt(List<BoxArt> boxarts) {
        return boxarts.stream().reduce((boxArt1,boxArt2) -> (boxArt1.getWidth()* boxArt1.getHeight()) < (boxArt2.getWidth()* boxArt2.getHeight()) ? boxArt1:boxArt2)
                .map(BoxArt::getUrl)
                .orElse("");
    }

}
