package velzenvan.thomas.pro10.models;

public class TokenPayload {

    private String role;
    private String userId;
    private String token;

    public TokenPayload() {
    }
    public TokenPayload(String role, String userId, String token) {
        this.role = role;
        this.userId = userId;
        this.token = token;
    }

    public TokenPayload(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
