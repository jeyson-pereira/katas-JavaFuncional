package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream().flatMap(list -> list.getVideos().stream())
                .map(movie -> ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(), "time", getMiddleTime(movie.getInterestingMoments()),"boxart", getUrlBoxArt(movie.getBoxarts())))
                .collect(Collectors.toUnmodifiableList());
    }

    public static Date getMiddleTime(List<InterestingMoment> interestingMoments) {
        return interestingMoments.stream().filter(times -> times.getType().equals("Middle")).map(time -> time.getTime()).findFirst().orElseThrow();
    }

    public static String getUrlBoxArt(List<BoxArt> boxarts) {
        return boxarts.stream().reduce((boxArt1,boxArt2) -> (boxArt1.getWidth()* boxArt1.getHeight()) < (boxArt2.getWidth()* boxArt2.getHeight()) ? boxArt1:boxArt2)
                .map(BoxArt::getUrl)
                .orElse("");
    }
}
