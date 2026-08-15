package utils;

import dto.User;
import dto.UserLombock;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

//    public static void main(String[] args) {
//        String firstName = faker.name().firstName();
//        System.out.println(firstName);
//        String lastName = faker.name().lastName();
//        System.out.println(lastName);
//        String email = faker.internet().emailAddress();
//        System.out.println(email);
//    }

    public static UserLombock positiveUser() {
        UserLombock user = UserLombock.builder()
                .username(faker.internet().emailAddress())
                .password("Qwerty!123")
                .build();
        return user;
    }
}
