
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class GeneradorPreguntas {

    public static void main(String[] args) throws IOException {
        // Tu clave de API de OpenAI
        String apiKey = "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";

        // Texto del contexto
        String contexto = "La capital de Francia es París.";

        // Dificultad de la pregunta (fácil, medio, difícil)
        String dificultad = "facil";

        // Construye el cuerpo de la solicitud
        String pregunta = generarPregunta(apiKey, contexto, dificultad);
        System.out.println("Pregunta " + dificultad + ": " + pregunta);
    }

    public static String generarPregunta(String apiKey, String contexto, String dificultad) throws IOException
    {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String prompt = contexto + " Genera una pregunta:";
        String maxTokens;

        switch (dificultad) {
            case "facil":
                maxTokens = "30";
                break;
            case "medio":
                maxTokens = "50";
                break;
            case "dificil":
                maxTokens = "70";
                break;
            default:
                throw new IllegalArgumentException("Dificultad no válida");
        }

        String json = "{\"prompt\":\"" + prompt + "\",\"max_tokens\":" + maxTokens + "}";
        RequestBody body = RequestBody.create(mediaType, json);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/engines/davinci/completions")
                .post(body)
                .addHeader("Content-Type", "application/json")
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



