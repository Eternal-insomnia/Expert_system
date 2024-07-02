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

@WebServlet("/showWeights")
public class ShowWeights extends HttpServlet {

    public ShowWeights() { super(); }

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

        int counter = 0;
        for (Material material : Materials) {
            printWriter.print((counter+1) + ". " + material.getName() + "<br>");
            double[] tmpArr = material.getWeights();
            for (double d : tmpArr) {
                printWriter.print(d + " ");
            }
            printWriter.print(
                    "<form action='http://localhost:8080/ExpertSystem/changeWeights' method='post'>" +
                            "<input type='number' id='index' name='index' required value='" + counter + "' style='display:none' />" +
                            "<label for='index' style='display:none'>Номер материала</label>" +
                            "<input type='text' id='name' name='name' required value='" + material.getName() + "' style='display:none' />" +
                            "<label for='name' style='display:none'>Название материала</label>" +
                            "<button type='submit'>Ввести новые веса</button>" +
                            "</form><br>");
            counter++;
        }

        printWriter.print(
                "<form action='http://localhost:8080/ExpertSystem/' method='post'>" +
                        "<button type='submit'>На главную</button>" +
                        "</form>");

        printWriter.close();
    }
}
