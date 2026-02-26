package census;

public class CensusDAO {

    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;

    public CensusDAO(CSVStateCensus census) {
        this.state = census.state;
        this.population = census.population;
        this.area = census.areaInSqKm;
        this.density = census.densityPerSqKm;
    }

    public CensusDAO(CSVStates state) {
        this.state = state.stateName;
        this.stateCode = state.stateCode;
    }
}