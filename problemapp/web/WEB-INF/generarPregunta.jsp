<!DOCTYPE html>
<html>
<head>
    <title>Generador de Preguntas</title>
</head>
<body>
<form method="post" action="/generarPreguntaServlet">
    <label for="contexto">Contexto:</label>
    <input type="text" id="contexto" name="contexto"><br>

    <label for="dificultad">Dificultad:</label>
    <select id="dificultad" name="dificultad">
        <option value="facil">Fácil</option>
        <option value="medio">Medio</option>
        <option value="dificil">Difícil</option>
    </select><br>

    <input type="submit" value="Generar Pregunta">
</form>
</body>
</html>
