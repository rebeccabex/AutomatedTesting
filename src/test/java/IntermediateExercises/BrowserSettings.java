package IntermediateExercises;

public class BrowserSettings {

    private String browser;
    private String username;
    private String password;

    public BrowserSettings(String browser, String username, String password) {
        this.browser = browser;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
