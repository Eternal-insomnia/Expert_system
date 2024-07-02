package ExpertSystem;

/**
 * @author Suvorov Alexey
 */

public class Material {

    private String name; // название материала
    private String description; // описание материала
    private double[] weights; // массив весов материала, один вопрос - один вес

    public Material() {
        name = "Автоматически сгенерированное имя";
        description = "Автоматически сгенерированное описание";
        weights = new double[0];
    }

    public Material(String name, String description, double[] weights) {
        this.name = name;
        this.description = description;
        this.weights = weights;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double[] getWeights() {
        return weights;
    }
    public void setWeights(double[] weights) {
        this.weights = weights;
    }
}
