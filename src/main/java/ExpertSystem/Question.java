package ExpertSystem;

/**
 * @author Suvorov Alexey
 */

public class Question { // Класс вопроса, обозначает структуру вопроса экспертной системы

    private int id; // id вопроса (уникален)
    private String question; // текст вопроса
    private String description; // описание вопроса
    private int parent; // родительский вопрос

    public Question () { // конструктор по умолчанию
        id = -1;
        question = "Автоматически созданный вопрос";
        description = "Автоматически созданное описание";
        parent = -2;
    }

    public Question(int id, String question, String description, int parent) { // конструктор с параметрами
        this.id = id;
        this.question = question;
        this.description = description;
        this.parent = parent;
    }

    // Методы доступа к полям

    public String getQuestion() { return question; }
    public String getDescription() { return description; }
    public int getParent() { return parent; }
    public int getId() { return id; }

    public void setQuestion(String question) { this.question = question; }
    public void setDescription(String description) { this.description = description; }
    public void setParent(int parent) { this.parent = parent; }
    public void setId(int id) { this.id = id; }
}
