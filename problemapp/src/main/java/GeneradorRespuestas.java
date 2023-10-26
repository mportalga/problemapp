import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

public class GeneradorRespuestas {
    public static String generarRespuesta(String apiKey, String contexto) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String endpoint = "https://api.openai.com/v1/engines/davinci/completions";

        // Configurar el contenido de la solicitud
        MediaType mediaType = MediaType.parse("application/json");
        String solicitudJSON = "{\"prompt\":\"Genera una respuesta a la pregunta: " + contexto + "\",\"max_tokens\":50}";
        RequestBody body = RequestBody.create(mediaType, solicitudJSON);

        // Crear la solicitud POST
        Request request = new Request.Builder()
                .url(endpoint)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return responseBody;
            } else {
                throw new IOException("Error en la solicitud: " + response);
            }
        }
    }
}
