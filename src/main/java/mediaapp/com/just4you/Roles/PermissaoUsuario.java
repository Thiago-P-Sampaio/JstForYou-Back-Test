package mediaapp.com.just4you.Roles;

public enum PermissaoUsuario {

    USER("user");

    private String role;

    PermissaoUsuario(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
