package Utility;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFromCsv {

@DataProvider
    public Object[][] dataFromCsv() throws IOException {
        FileInputStream fis = new FileInputStream("./src/main/resources/names.csv");
        String filePath = "./src/main/resources/names.csv";  // Path to your CSV file
        String line;
        String splitBy = ",";
        int rowCount = 0;

        // First, count the rows in the file to determine the size of the 2D array
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                rowCount++;
            }
        }

        // Initialize the 2D array to hold the client data
        Object[][] data = new Object[rowCount - 1][2]; // Subtract 1 to skip header

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header
            br.readLine();

            int rowIndex = 0;
            while ((line = br.readLine()) != null) {
                String[] clientData = line.split(splitBy);
                data[rowIndex][0] = clientData[0].trim(); // client name
                data[rowIndex][1] = clientData[1].trim(); // client email
                rowIndex++;
            }
        }

        return data;

    }


    @DataProvider
    public Object[][] readCSV() throws IOException {
        String filePath = "./src/main/resources/updatebook.csv";
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        List<String> lines = new ArrayList<>();
        // Read the file line by line and store the client names
        while ((line = br.readLine()) != null) {
            lines.add(line.trim()); // Add the line to the list (skip any leading/trailing spaces)
        }

        br.close();

        // The first line is the header, so we start from index 1
        int rowCount = lines.size() - 1;  // Subtract 1 to exclude the header
        Object[][] data = new Object[rowCount][1];  // 2D array to store the client names
        // Populate the 2D array with client names
        for (int i = 1; i <= rowCount; i++) {
            data[i - 1][0] = lines.get(i);  // Skip the header row (index 0)
        }

        return data;
    }

}
