package co.com.porvenir.projects.models.rest;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

import static co.com.porvenir.projects.utils.Constants.LOCALE_FAKER;

@Setter
@Getter
public class Users {

    private String name;
    private String job;

    public Users(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public static Users generateRandomUser() {
        Locale locale = new Locale(LOCALE_FAKER);
        Faker faker = new Faker(locale);
        String randomName = faker.name().fullName();
        String randomJob = faker.job().position();
        return new Users(randomName, randomJob);
    }

    @Override
    public String toString() {
        return "{"
        +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

}