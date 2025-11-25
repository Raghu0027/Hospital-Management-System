public class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate() {
        // Basic authentication logic for demonstration
        return username.equals("admin") && password.equals("password");
    }

    public String getUsername() {
        return username;
    }
}
