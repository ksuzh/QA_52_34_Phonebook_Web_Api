package data_providers;

import dto.ContactDto;
import dto.UserLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactDataProvider {
    @DataProvider
    public Iterator<ContactDto> dataProviderWrongContact() {
        List<ContactDto> list = new ArrayList<>();
        try(BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("src/test/resources/wrong_contacts.csv"))){
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println("Reading line: " + line);
                String[] splitLine = line.split(",");
                System.out.println("length: " + splitLine.length);
                list.add(ContactDto.builder()
                        .id(splitLine[0])
                        .name(splitLine[1])
                        .lastName(splitLine[2])
                        .email(splitLine[3])
                        .phone(splitLine[4])
                        .address(splitLine[5])
                        .description(splitLine[6])
                        .build());
                line = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("created exception");
        }
        return list.iterator();
    }
}
