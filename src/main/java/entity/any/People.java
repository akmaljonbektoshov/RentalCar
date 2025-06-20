package entity.any;

public class People {
    private String name;
    private String email;
    private String password;

    public People(String name) {
        this.name = name;
    }

    public People(String email, String password) {
        this.email = email;
        this.password = password;
    }

    ///  Bu shunchake test uchun yozilgan class hisoblanadi
}
