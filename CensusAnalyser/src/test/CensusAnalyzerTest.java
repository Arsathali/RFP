package test;

import census.CensusException;
import census.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyzerTest {

    private static final String CORRECT_CSV_PATH = 
            "D:\\BridgeLabz\\RFP\\RFP\\CensusAnalyser\\resources\\StateCensusData.csv";
            
    private static final String WRONG_PATH =
            "src/main/resources/WrongFile.csv";

    private static final String WRONG_TYPE =
            "src/main/resources/StateCensusData.txt";

    private static final String WRONG_DELIMITER =
            "src/main/resources/WrongDelimiter.csv";

    private static final String WRONG_HEADER =
            "src/main/resources/WrongHeader.csv";

    // TC 1.1 – Happy Case
    @Test
    public void givenStateCensusCSV_WhenCorrect_ShouldReturnCorrectRecordCount()
            throws CensusException {

        StateCensusAnalyser analyser = new StateCensusAnalyser();

        int recordCount = analyser.loadStateCensusData(CORRECT_CSV_PATH);

        Assert.assertEquals(10, recordCount); // change to your actual count
    }

    // TC 1.2 – Wrong File Path
    @Test
    public void givenWrongFilePath_ShouldThrowNoSuchFileException() {

        StateCensusAnalyser analyser = new StateCensusAnalyser();

        try {
            analyser.loadStateCensusData(WRONG_PATH);
            Assert.fail("Expected CensusException");
        } catch (CensusException e) {
            Assert.assertEquals(
                    CensusException.ExceptionType.NO_SUCH_FILE,
                    e.type);
        }
    }

    // TC 1.3 – Wrong File Type
    @Test
    public void givenWrongFileType_ShouldThrowIncorrectFileTypeException() {

        StateCensusAnalyser analyser = new StateCensusAnalyser();

        try {
            analyser.loadStateCensusData(WRONG_TYPE);
            Assert.fail("Expected CensusException");
        } catch (CensusException e) {
            Assert.assertEquals(
                    CensusException.ExceptionType.INCORRECT_FILE_TYPE,
                    e.type);
        }
    }

    // TC 1.4 – Wrong Delimiter
    @Test
    public void givenWrongDelimiter_ShouldThrowDelimiterIssueException() {

        StateCensusAnalyser analyser = new StateCensusAnalyser();

        try {
            analyser.loadStateCensusData(WRONG_DELIMITER);
            Assert.fail("Expected CensusException");
        } catch (CensusException e) {
            Assert.assertEquals(
                    CensusException.ExceptionType.DELIMITER_ISSUE,
                    e.type);
        }
    }

    // TC 1.5 – Wrong Header
    @Test
    public void givenWrongHeader_ShouldThrowIncorrectHeaderException() {

        StateCensusAnalyser analyser = new StateCensusAnalyser();

        try {
            analyser.loadStateCensusData(WRONG_HEADER);
            Assert.fail("Expected CensusException");
        } catch (CensusException e) {
            Assert.assertEquals(
                    CensusException.ExceptionType.INCORRECT_HEADER,
                    e.type);
        }
    }
}