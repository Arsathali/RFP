package census;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.Reader;
import java.util.*;

public class StateCensusAnalyser {

    private Map<String, CensusDAO> censusMap = new HashMap<>();

    public int loadStateCensusData(String csvPath) throws CensusException {
        try (Reader reader = new FileReader(csvPath)) {

            CsvToBean<CSVStateCensus> csvToBean =
                    new CsvToBeanBuilder<CSVStateCensus>(reader)
                            .withType(CSVStateCensus.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            Iterator<CSVStateCensus> iterator = csvToBean.iterator();
            while (iterator.hasNext()) {
                CSVStateCensus data = iterator.next();
                censusMap.put(data.state, new CensusDAO(data));
            }

            return censusMap.size();

        } catch (RuntimeException e) {
            throw new CensusException("Incorrect CSV Header",
                    CensusException.ExceptionType.INCORRECT_HEADER);
        } catch (Exception e) {
            if (!csvPath.endsWith(".csv"))
                throw new CensusException("Incorrect File Type",
                        CensusException.ExceptionType.INCORRECT_FILE_TYPE);

            throw new CensusException("File Problem",
                    CensusException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public int loadStateCodeData(String csvPath) throws CensusException {
        try (Reader reader = new FileReader(csvPath)) {

            CsvToBean<CSVStates> csvToBean =
                    new CsvToBeanBuilder<CSVStates>(reader)
                            .withType(CSVStates.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            Iterator<CSVStates> iterator = csvToBean.iterator();
            while (iterator.hasNext()) {
                CSVStates data = iterator.next();
                censusMap.put(data.stateName, new CensusDAO(data));
            }

            return censusMap.size();

        } catch (Exception e) {
            throw new CensusException("State Code Load Failed",
                    CensusException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    // Sorting using Generics
    public <E> String sortData(Comparator<CensusDAO> comparator) {
        List<CensusDAO> list = new ArrayList<>(censusMap.values());
        list.sort(comparator);
        return new Gson().toJson(list);
    }
}