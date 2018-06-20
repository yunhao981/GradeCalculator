import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yunhao981 on 2018/6/11.
 */
public interface ComputeGPAStrategy{
    public abstract double computeGPA(TreeMap<String, Double> score, TreeMap<String, Double> course);
}