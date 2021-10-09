package utility;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Arrays;

public class CsvParser {
    static Logger logger = Logger.getLogger(CsvParser.class);
    /**
     * Read CSV file and get in Map form<br/>
     * @param filePath CSV data file path
     * @return HashMap contains data
     */
    public static HashMap<String, Map<String, String>> getInMap(String filePath) {
        HashMap<String, Map<String, String>> mapList = new HashMap<>();
        try {
            String[] headRowArray;
            List<String> headRowList = null;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currentRecord;
            int rowIndex = 0;
            while ((currentRecord = reader.readLine()) != null) {
                if (rowIndex == 0) {
                    headRowArray = currentRecord.split(",");
                    headRowList = Arrays.asList(headRowArray);
                } else {
                    HashMap<String, String> singleRowMap = new LinkedHashMap<>();
                    String[] oneRowArray = currentRecord.split(",");
                    List<String> oneRowList = Arrays.asList(oneRowArray);
                    for (int index = 1; index < oneRowList.size(); index++) {
                        String key = headRowList.get(index);
                        String value = oneRowList.get(index);
                        singleRowMap.put(key.trim(), value.trim());
                    }

                    mapList.put(oneRowList.get(0).trim(), singleRowMap);
                }
                rowIndex++;
            }
        } catch (Exception e) {
            logger.error("Exception occurred while parsing CSV file"+e.getMessage());
        }
        return mapList;
    }
}
