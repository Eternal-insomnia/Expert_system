package ExpertSystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Suvorov Alexey
 */

@WebServlet("/nextQuestion")
public class ShowTestNextQuestion extends HttpServlet {

    public ShowTestNextQuestion() { super(); }

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

        int num = Integer.parseInt(req.getParameter("num"));
        String res = req.getParameter("res");
        res = res.concat(req.getParameter("ans"));
        num++;

        if (num < Questions.length) {
            req.setAttribute("num", num);
            printWriter.print(Questions[num].getQuestion() + "<br>");
            printWriter.print("<p style='color:#808080'>" + Questions[num].getDescription() +"</p>");
            printWriter.print("<form action='http://localhost:8080/ExpertSystem/nextQuestion' method='post'>" +
                    "    <input type='radio' id='yes' name='ans' value='1' checked />" +
                    "    <label for='yes'>Да</label><br>" +
                    "    <input type='radio' id='no' name='ans' value='0' />" +
                    "    <label for='no'>Нет</label><br>" +
                    "    <input type='number' id='number' name='num' value='" + num + "' required style='display:none'/>" +
                    "    <label for='number' style='display:none'>Номер вопроса</label><br>" +
                    "    <input type='text' id='result' name='res' value='" + res + "' required style='display:none'/>" +
                    "    <label for='result' style='display:none'>Ответы</label><br>" +
                    "<button type='submit'>Далее</button>" +
                    "</form>");
        } else {
            double[] results = new double[Materials.length];
            Arrays.fill(results, 0.00); // заполнение единицами
            int i = 0; // счётчик вопросов
            for (char ch : res.toCharArray()){
                int tmp = Character.getNumericValue(ch);
                int j = 0; // счётчик материалов
                for (Material material : Materials) {
                    double[] tmpArr = material.getWeights();
                    results[j] += tmpArr[i] * tmp;
                    j++;
                }
                i++;
            }
            i = 0;
            int maxIndex = 0;
            double maxValue = 0;
            for (double result : results) {
                result /= results.length;
                if (result > maxValue) {
                    maxIndex = i;
                    maxValue = result;
                }
                i++;
            }

            printWriter.print(
                    "Мы рекомендуем вам использовать материал \"" + Materials[maxIndex].getName() + "\" в качестве напольного покрытия." +
                            "<br><p style='color:#808080'>" + Materials[maxIndex].getDescription() + "</p><br>"
            );

            printWriter.print(
                    "<script src='https://www.kryogenix.org/code/browser/sorttable/sorttable.js'></script>" +
                            "<table class='sortable'>" +
                            "<tr><th>Материал</th>" +
                            "<th>Сходство, %</th></tr>"
            );
            for (i = 0; i < results.length; i++) {
                printWriter.print(
                        "<tr class='item'><td>" + Materials[i].getName() + "</td>" +
                                "<td>" + results[i]/results.length * 100 + "%</td></tr>"
                );
            }
            printWriter.print("</table><br>");
        }

        printWriter.print(
                "<br><form action='http://localhost:8080/ExpertSystem/' method='post'>" +
                        "<button type='submit'>На главную</button>" +
                        "</form>");

        printWriter.print("</div>");
        printWriter.close();
    }
}
