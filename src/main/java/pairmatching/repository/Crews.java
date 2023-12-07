package pairmatching.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.domain.Course;
import pairmatching.exception.PairExceptionMaker;

public class Crews {
    public static final List<Crew> backendCrews = new ArrayList<>();
    public static final List<Crew> frontendCrews = new ArrayList<>();
    public static final String BACKEND_CREW_MD = "src/main/resources/backend-crew.md";
    public static final String FRONTEND_CREW_MD = "src/main/resources/frontend-crew.md";

    static {
        try (BufferedReader backendFile = new BufferedReader(new FileReader(BACKEND_CREW_MD));
             BufferedReader frontendFile = new BufferedReader(new FileReader(FRONTEND_CREW_MD))) {
            backendFile.lines().forEach(line -> backendCrews.add(new Crew(Course.BACKEND, line)));
            frontendFile.lines().forEach(line -> frontendCrews.add(new Crew(Course.FRONTEND, line)));
        } catch (FileNotFoundException e) {
            throw PairExceptionMaker.NO_SUCH_FILE.makeException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getCrewNamesOf(Course course) {
        if (course == Course.BACKEND) {
            return backendCrews.stream()
                    .map(Crew::getName)
                    .collect(Collectors.toList());
        }
        return frontendCrews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }
}
