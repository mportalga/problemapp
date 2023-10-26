import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenerarPreguntaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contexto = request.getParameter("contexto");
        String dificultad = request.getParameter("dificultad");
        String apiKey  = "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";

        String pregunta = GeneradorPreguntas.generarPregunta(apiKey, contexto, dificultad);
        String respuesta = GeneradorRespuestas.generarRespuesta(apiKey, contexto);


        request.setAttribute("pregunta", pregunta);
        request.setAttribute("respuesta", respuesta);

        request.getRequestDispatcher("/WEB-INF/resultado.jsp").forward(request, response);
    }
}

