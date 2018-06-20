import java.util.TreeMap;

public class GradeCalculator {
    public static void main(String[] args){
        WindowController wc = new WindowController();
        wc.setTitle("GradeCalculator");
        wc.setBounds(100,100,420,260);

       Context con =  new Context();
       con.setStrategy(new StrategyOne());
       Student stu0 = new Student();

       TreeMap<String, Double> s = new TreeMap<>();
       TreeMap<String, Double> c = new TreeMap<>();


       s.put("Fuckyou", -1.0 );
       c.put("Fuckyou", 3.0);

       s.put("Shit", 3.3);
       c.put("Shit", 4.0);

       stu0.setGpa(con.getStudentScore(s,c));
       System.out.println(stu0.gpa);

       con.setStrategy(new StrategyThree());
       stu0.setGpa(con.getStudentScore(s,c));
       System.out.println(stu0.gpa);
    }
}