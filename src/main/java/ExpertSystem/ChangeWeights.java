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

@WebServlet("/changeWeights")
public class ChangeWeights extends HttpServlet {

    public ChangeWeights() { super(); }

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

        int index = Integer.parseInt(req.getParameter("index"));
        String name = req.getParameter("name");

        printWriter.print(name);

        QuestionArray questionArray = new QuestionArray();
        Question[] Questions = questionArray.init();
        MaterialArray materialArray = new MaterialArray();
        Material[] Material = materialArray.init(Questions.length);
        double[] Weights = new double[Material[index].getWeights().length];

        printWriter.print("<form action='http://localhost:8080/ExpertSystem/updateWeights' method='post'>");
        for (int i = 0; i < Weights.length; i++) {
            printWriter.print(
                    "<input type='text' name='weight" + i + "' value='" + Weights[i] + "' required />" +
                            "<label for='weight" + i + "'>  " + Questions[i].getQuestion() + "</label><br>"
            );
        }
        printWriter.print(
                "<br>" +
                        "<input type='number' name='len' value='" + Weights.length + "' required style='display:none' />" +
                        "<input type='text' name='name' value='" + name + "' required style='display:none' />" +
                        "<button type='submit'>Изменить веса</button>" +
                        "</form>"
        );
        printWriter.print(
                "<form action='http://localhost:8080/ExpertSystem/' method='post'>" +
                        "<button type='submit'>На главную</button>" +
                        "</form>");

        printWriter.close();
    }
}
