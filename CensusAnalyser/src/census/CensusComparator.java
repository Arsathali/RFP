package census;

import java.util.Comparator;

public class CensusComparator {

    public static Comparator<CensusDAO> sortByState =
            Comparator.comparing(c -> c.state);

    public static Comparator<CensusDAO> sortByPopulation =
            Comparator.comparingInt(c -> c.population);

    public static Comparator<CensusDAO> sortByDensity =
            Comparator.comparingInt(c -> c.density);
}

