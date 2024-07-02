package ExpertSystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Suvorov Alexey
 */

@WebServlet("/test")
public class ShowTest extends HttpServlet {

    public ShowTest() {super();}

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        printWriter.println("??");

        // при использовании GET запроса на этой странице, пользователь преренаправляется на главную
        resp.setStatus(302); // moved temporarily
        resp.setHeader("Location","http://localhost:8080/ExpertSystem/");

        printWriter.close();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        printWriter.print("<link rel='stylesheet' type='text/css' href='styles/style.css'>");
        printWriter.print("<div class='all_center'>");

        QuestionArray questionArray = new QuestionArray();
        Question[] Questions = questionArray.init(); // массив вопросов
        MaterialArray materialArray = new MaterialArray();
        Material[] Materials = materialArray.init(Questions.length); // массив материалов

        // ЦИКЛ ВОПРОСОВ, СМЕНЯЮЩИХ ДРУГ ДРУГА
        int num = 0;
        String res = "";
        printWriter.print(Questions[0].getQuestion() + "<br>");
        printWriter.print("<p style='color:#808080'>" + Questions[0].getDescription() +"</p>");
        printWriter.print("<form action='http://localhost:8080/ExpertSystem/nextQuestion' method='post'>" +
                "    <input type='radio' id='yes' name='ans' value='1' checked />" +
                "    <label for='yes'>Да</label><br>" +
                "    <input type='radio' id='no' name='ans' value='0' />" +
                "    <label for='no'>Нет</label><br>" +
                "    <input type='number' id='number' name='num' value='" + num + "' required style='display:none'/>" +
                "    <label for='number' style='display:none'>Номер вопроса</label><br>" +
                "    <input type='text' id='result' name='res' value='" + res + "' style='display:none'/>" +
                "    <label for='result' style='display:none'>Ответы</label><br>" +
                "<button type='submit'>Далее</button>" +
                "</form>");

        printWriter.print(
                "<form action='http://localhost:8080/ExpertSystem/' method='post'>" +
                "<br><button type='submit'>На главную</button>" +
                "</form>");

        printWriter.print("</div>");
        printWriter.close();
    }
}
