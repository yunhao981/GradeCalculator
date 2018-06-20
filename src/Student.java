import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yunhao981 on 2018/6/19.
 */
public class Student {
    private String name;
    private double id;
    double gpa;
    private TreeMap<String, Double> score;
    private TreeMap<String, Double> course;

    public void setName(String name) {
        this.name = name;
    }
    public void setId(double id){
        this.id = id;
    }
    public void setGpa(double gpa){
        this.gpa = gpa;
    }
    public void setScore(TreeMap<String, Double> score){
        this.score = score;
    }
    public void setCourse(TreeMap<String, Double> course){
        this.course = course;
    }

    public String getName(){
        return name;
    }
    public double getId(){
        return id;
    }
    public TreeMap<String, Double> getScore(){
        return score;
    }
    public TreeMap<String, Double> getCourse(){
        return course;
    }
    public double getGpa(){
        return gpa;
    }


}
