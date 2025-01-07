package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class GestorCredenciales {

    private static final String JSON_PATH = "src/test/resources/data/sesion/sesion.json";
    private static JsonObject credenciales;

    static {
        cargarCredenciales();
    }

    private static void cargarCredenciales() {
        try (FileReader reader = new FileReader(JSON_PATH)) {
            credenciales = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo JSON de credenciales: " + e.getMessage(), e);
        }
    }

    public static String getCampo(String campo) {
        if (credenciales != null && credenciales.has(campo)) {
            return credenciales.get(campo).getAsString();
        } else {
            throw new RuntimeException("El campo '" + campo + "' no se encuentra en el archivo JSON.");
        }
    }
}
