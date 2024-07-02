package ExpertSystem;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class QuestionArray {
    private Question[] Questions;

    public QuestionArray() {
        Questions = new Question[0];
    }

    public QuestionArray(Question[] questions) {
        Questions = questions;
    }

    public Question[] getQuestions() {
        return Questions;
    }

    public void setQuestions(Question[] questions) {
        Questions = questions;
    }

    public Question[] addQuestion(int id, String q, String q_description, int p_id) {
        Question[] tmpArray;
        tmpArray = new Question[Questions.length + 1];
        if (Questions.length > 0) {
            System.arraycopy(Questions, 0, tmpArray, 0, Questions.length);
        }
        tmpArray[Questions.length] = new Question(id, q, q_description, p_id);
        Questions = new Question[tmpArray.length];
        System.arraycopy(tmpArray, 0, Questions, 0, tmpArray.length);

        return Questions;
    }

    public Question[] init() { // метод получения списка вопросов из БД в массив
        // SQL-запрос для получения вопросов
        String user = "", pass = ""; // данные доступа для базы данных
        FileInputStream fileIn = null;
        boolean checkExc = false;

        try {
            // попытка открыть файл
            fileIn = new FileInputStream("C:/Java progs/ExpertSystem/src/main/resources/login");
        } catch (Exception e) {
            // если файл не открывается, вывод ошибки пользователю
            System.out.println("Ошибка получения данных доступа. Не удалось открыть файл<br>");
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
                ResultSet resultSet = statement.executeQuery("select * from questions order by question_id");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String question = resultSet.getString(2);
                    String q_description = resultSet.getString(3);
                    int parent_id = resultSet.getInt(4);
                    // Заполнение вопросов
                    addQuestion(id, question, q_description, parent_id);
                }

                connection.close();
            } catch (Exception e) {
                System.out.println(e);
                System.out.print("Соединение с базой данных не установлено");
            }
        }

        return Questions;
    }
}
