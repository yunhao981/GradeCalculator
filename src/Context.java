import java.util.TreeMap;

/**
 * Created by yunhao981 on 2018/6/12.
 */
public class Context {
    ComputeGPAStrategy strategy;
    public void setStrategy(ComputeGPAStrategy strategy){
        this.strategy = strategy;
    }
    public double getStudentScore(TreeMap<String, Double> score, TreeMap<String, Double> course){
        if(strategy != null){
            return strategy.computeGPA(score,course);
        }else{
            return 0;
        }
    }
}
