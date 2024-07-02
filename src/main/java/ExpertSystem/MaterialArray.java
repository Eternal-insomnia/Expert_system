package ExpertSystem;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MaterialArray {
    private Material[] Materials;

    public MaterialArray() {
        Materials = new Material[0];
    }

    public MaterialArray(Material[] materials) {
        Materials = materials;
    }

    public Material[] getMaterials() {
        return Materials;
    }

    public void setMaterials(Material[] materials) {
        Materials = materials;
    }

    public Material[] addMaterial(String name, String description, double[] weights) {
        Material[] tmpArray;
        tmpArray = new Material[Materials.length + 1];
        if (Materials.length > 0) {
            System.arraycopy(Materials, 0, tmpArray, 0, Materials.length);
        }
        tmpArray[Materials.length] = new Material(name, description, weights);
        Materials = new Material[tmpArray.length];
        System.arraycopy(tmpArray, 0, Materials, 0, tmpArray.length);

        return Materials;
    }

    public Material[] init(int len) { // метод получения списка вопросов из БД в массив
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
                ResultSet resultSet = statement.executeQuery("select * from materials order by name");
                while (resultSet.next()) {
                    String name = resultSet.getString(1);
                    String description = resultSet.getString(2);
                    double[] weights = new double[len];
                    for (int j = 0; j < len; j++) {
                        weights[j] = resultSet.getDouble(j+3);
                    }
                    // Заполнение вопросов
                    addMaterial(name, description, weights);
                }

                connection.close();
            } catch (Exception e) {
                System.out.println(e);
                System.out.print("Соединение с базой данных не установлено");
            }
        }

        return Materials;
    }
}
