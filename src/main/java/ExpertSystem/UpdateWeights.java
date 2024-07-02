package ExpertSystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Suvorov Alexey
 */

@WebServlet("/updateWeights")
public class UpdateWeights extends HttpServlet {

    public UpdateWeights() { super(); }

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
        Question[] Questions = questionArray.init();

        String name = req.getParameter("name");
        int len = Integer.parseInt(req.getParameter("len"));
        String[] Weights = new String[len];
        for (int i = 0; i < len; i++) {
            String str = req.getParameter("weight" + i);
            Weights[i] = str.replace(',', '.');
        }
        // SQL-запрос для получения вопросов
        String sqlreq = "update materials set ";
        for (int i = 0; i < Weights.length; i++) {
            sqlreq += "`" + Questions[i].getId() + "` = " + Weights[i];
            if (i < Weights.length - 1) { sqlreq += ", "; }
            else { sqlreq += " where name = '" + name + "';"; }
        }

        String user = "", pass = ""; // доступ в БД
        FileInputStream fileIn = null; // поток вывода из файла
        boolean checkExc = false; // проверка на ошибки

        // чтение данных для доступа в БД
        try {
            // попытка открыть файл
            fileIn = new FileInputStream("C:/Java progs/ExpertSystem/src/main/resources/login");
        } catch (Exception e) {
            // если файл не открывается, вывод ошибки пользователю
            printWriter.println("Ошибка получения данных доступа. Не удалось открыть файл<br>");
            checkExc=true;
        }

        // если ошибок нет, работа продолжается
        if (!checkExc) {
            // данные доступа считываются из файла
            Scanner in = new Scanner(fileIn);
            user = in.nextLine();
            pass = in.nextLine();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/expertsystem", user, pass);
                Statement statement = connection.createStatement();
                int res = statement.executeUpdate(sqlreq);

                if (res <= 0) {
                    // запрос либо не выполнен, либо в таблице НЕТ СТРОК, с которым повзаимодействовал запрос
                    resp.setStatus(302); // moved temporarily, простой GET-запрос
                    resp.setHeader("Location", "http://localhost:8080/kurs/addError?error=sql_req");
                }

                connection.close();
            } catch (Exception e) {
                printWriter.print("Соединение с базой данных не установлено");
                printWriter.print("<br>Ваш запрос: " + sqlreq);
            }
        }

        resp.setStatus(307); // temporary redirect, сохрание POST-запроса
        resp.setHeader("Location","http://localhost:8080/ExpertSystem");

        printWriter.close();
    }
}
