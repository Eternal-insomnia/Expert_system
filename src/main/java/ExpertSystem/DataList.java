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

@WebServlet("/dataList")
public class DataList extends HttpServlet {

    public DataList() { super(); }

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

        QuestionArray questionArray = new QuestionArray();
        Question[] Questions = questionArray.init(); // массив вопросов
        MaterialArray materialArray = new MaterialArray();
        Material[] Materials = materialArray.init(Questions.length); // массив материалов

        printWriter.print("<b>Вопросы:</b><br>");
        for (Question question : Questions) {
            printWriter.print(question.getId() + ". ");
            printWriter.print(question.getQuestion() + "<br>");
        }
        printWriter.print("<br><b>Материалы:</b><br>");
        for (Material material : Materials) {
            printWriter.print("<i>" + material.getName() + "</i><br>");
            printWriter.print(material.getDescription() + "<br><br>");
        }
        printWriter.print("<form action=\"http://localhost:8080/ExpertSystem/\" method=\"post\">" +
                "<button type=\"submit\">НА ГЛАВНУЮ</button>" +
                "</form>");

        printWriter.close();
    }
}
