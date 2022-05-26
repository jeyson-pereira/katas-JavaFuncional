package katas;

import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        //return ImmutableList.of(ImmutableMap.of("id", 5, "title", "Bad Boys", "boxart", new BoxArt(150, 200, "url")));
        return movieLists.stream().flatMap(list -> list.getVideos().stream())
                .map(movie -> ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(), "boxart", getUrlBoxArt(movie.getBoxarts())))
                .collect(Collectors.toUnmodifiableList());
    }

    public static String getUrlBoxArt(List<BoxArt> boxarts) {
        return boxarts.stream().filter(boxart -> boxart.getWidth().equals(150)  && boxart.getHeight().equals(200))
                .map(BoxArt::getUrl)
                .findFirst()
                .orElse("");
    }
}
