package model;
import control.PDC;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Hieu Nguyen
 */
public class CSVHandler {
    private final String fileName;

    public CSVHandler(String fileName) {
        this.fileName = PDC.myDir + fileName;
    }

    public List<String[]> readAll() throws IOException {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public User loadUser(String email) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(Arrays.toString(values)); // add this line to print the contents of the array

                if (values.length >= 4 && values[2].equals(email)) {
                    String password = values[3];
                    String firstName = values[0];
                    String lastName = values[1];

                    return new User(firstName, lastName, email, password);
                }
            }
        }

        // User not found
        return null;
    }






    public void writeAll(List<String[]> data) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String[] values : data) {
                for (int i = 0; i < values.length; i++) {
                    writer.append(values[i]);

                    if (i != values.length - 1) {
                        writer.append(",");
                    }
                }

                writer.append("\n");
            }
        }
    }
}