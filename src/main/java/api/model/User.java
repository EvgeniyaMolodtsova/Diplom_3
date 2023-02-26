package api.model;


import io.qameta.allure.Step;
import utils.Random;

public class User {

    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Step("генератор юзера API")
    public static User generate() {
        String name = Random.getRandom(7);
        String email = Random.getRandom(7) + "@yandex.ru";
        String password = Random.getRandom(7);

        return new User(name, email, password);
    }

    @Step("данные для логина юзера API")
    public static User getLoginFrom(User user) {
        return new User(null, user.getEmail(), user.getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Step("возвращение email юзера API")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
