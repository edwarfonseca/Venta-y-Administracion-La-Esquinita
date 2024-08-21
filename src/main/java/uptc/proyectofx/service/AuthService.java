package uptc.proyectofx.service;

public class AuthService {

    public boolean isValidCredentials(String email, String password) {
        // Implementa la lógica de autenticación aquí
        return (email.endsWith("@admin") && password.equals("Admin")) ||
                (email.endsWith("@emp") && password.equals("Empleado"));
    }
}
