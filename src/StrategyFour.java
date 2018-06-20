import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by yunhao981 on 2018/6/12.
 */
public class StrategyFour implements ComputeGPAStrategy {
    public double computeGPA(TreeMap<String, Double> score, TreeMap<String, Double> course){

        //TODO: S2 * 1.1

        double gpa = 0.0;
        double credit = 0;

        Set scoreSet = score.entrySet();
        for(Iterator scoreIter = scoreSet.iterator(); scoreIter.hasNext();){
            Map.Entry entry = (Map.Entry)scoreIter.next();

            String k = (String)entry.getKey();
            Double v = (Double)entry.getValue();

            if(Double.valueOf(v)!= -1.0){
                v = 0.0;
                gpa += Double.valueOf(v) * Double.valueOf(course.get(k));
                credit += Double.valueOf(v);
            }

        }

        gpa = gpa / credit;
        return gpa * 1.1;
    }
}
