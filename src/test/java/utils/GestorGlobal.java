package utils;

public class GestorGlobal {

    private static String usuario;
    private static String contrasena;

    public static void inicializarVariables() {
        usuario = GestorCredenciales.getCampo("User");
        contrasena = GestorCredenciales.getCampo("Pass");
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getContrasena() {
        return contrasena;
    }
}
