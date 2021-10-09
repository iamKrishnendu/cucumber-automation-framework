package constants;



import utility.CsvParser;
import utility.SeleniumUtils;

import java.io.File;

public class Constants {
    public final static String PAGE_TIMEOUT = "30";
    public final static String TARGET_FOLDER_PATH = System.getProperty("user.dir")+File.separator+"target";
    public final static String CSV_DATA_FILE = SeleniumUtils.getResourceFilePath("test","csv")+File.separator+"dataSet.csv";

}
