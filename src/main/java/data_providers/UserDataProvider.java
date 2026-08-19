package data_providers;

import dto.UserLombock;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDataProvider {
    @DataProvider
    public Iterator<UserLombock> dataProviderWrongPasswordOrEmail() {
        List<UserLombock> list = new ArrayList<>();
        try(BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("src/test/resources/wrong_email_password.csv"))){
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                list.add(UserLombock.builder()
                        .username(splitLine[0])
                        .password(splitLine[1])
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
