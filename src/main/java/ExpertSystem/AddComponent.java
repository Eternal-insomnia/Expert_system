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
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Suvorov Alexey
 */

@WebServlet("/addComponent")
public class AddComponent extends HttpServlet {

    public AddComponent() {super();}

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        printWriter.println("??");

        // при использовании GET запроса на этой странице, пользователь преренаправляется на главную
        resp.setStatus(302); // moved temporarily
        resp.setHeader("Location","http://localhost:8080/ExpertSystem/");

        printWriter.close();
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        String action = req.getParameter("action");
        if (action.equals("ADD_Q")) {
            printWriter.println("ADD QUESTIONS<br>");
            printWriter.println(req.getParameter("question"));

            // получение параметров из запроса
            int id = Integer.parseInt(req.getParameter("id")); // id вопроса
            String question = req.getParameter("question"); // текст вопроса
            String description = req.getParameter("description"); // описание вопроса
            int parentId = Integer.parseInt(req.getParameter("parentId")); // id родительского вопроса

            // создание SQL-запроса
            String sqlreq = "insert into questions (question_id, question, q_description, parent_id) values "; // запрос на добавление нового вопроса
            sqlreq += ("(" + id + ", '" + question + "', '" + description + "', " + parentId + ")");
            createReq(sqlreq, req, resp, printWriter, action);
            sqlreq = "alter table materials add column "; // запрос на добавления нового столбца с весами
            sqlreq += "`" + id + "` double default (0) not null";
            createReq(sqlreq, req, resp, printWriter, action);
        } else if (action.equals("ADD_M")) {
            printWriter.println("ADD MATERIALS<br>");
            printWriter.println(req.getParameter("material"));

            // получение параметров из запроса
            String material = req.getParameter("material"); // название материала
            String description = req.getParameter("description"); // описание материала

            // создание SQL-запроса
            String sqlreq = "insert into materials (name, m_description) values ";
            sqlreq += ("('" + material + "', '" + description + "')");
            createReq(sqlreq, req, resp, printWriter, action);
        }
        // если запрос выполнен успешно, пользователь отправляется на главную
        resp.setStatus(307); // temporary redirect, сохрание POST-запроса
        resp.setHeader("Location","http://localhost:8080/ExpertSystem/");

        printWriter.close();
    }

    // Метод отправляет заготовленный запрос sqlreq в указанную таблицу tableName
    protected void createReq(String sqlreq, HttpServletRequest req, HttpServletResponse resp, PrintWriter printWriter, String action)
            throws IOException, ServletException {

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

    }
}
