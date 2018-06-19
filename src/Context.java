/**
 * Created by yunhao981 on 2018/6/12.
 */
public class Context {
    ComputeGPAStrategy strategy;
    public void setStrategy(ComputeGPAStrategy strategy){
        this.strategy = strategy;
    }
    public double getScore(double [] a){
        if(strategy != null){
            return strategy.computeGPA(a);
        }else{
            return 0;
        }
    }
}
