import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeMap;

/**
 * Created by yunhao981 on 2018/6/19.
 */

public class WindowController extends JFrame implements ActionListener {
    private JTextArea showArea;
    private JTextField textID;
    private JButton buttonSA, buttonSB, buttonSC, buttonSD;
    private Context con;
    private Student stu;
    private TreeMap<String, Double> s;
    private TreeMap<String, Double> c;

   WindowController(){
      init();
      setVisible(true);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }

   private void init(){
      showArea = new JTextArea();
      textID = new JTextField(5);
      buttonSA = new JButton("仅计算已出成绩课程总绩点");
      buttonSB = new JButton("未出成绩按零分算总绩点");
      buttonSC = new JButton("1.1 * 仅计算已出成绩课程总绩点");
      buttonSD = new JButton("1.1 * 未出成绩按零分算总绩点");
      JPanel pNorth = new JPanel();
      pNorth.add(new JLabel("学号"));
      pNorth.add(textID);

      JPanel pCenter = new JPanel();
      pCenter.add(buttonSA);
      pCenter.add(buttonSB);
      pCenter.add(buttonSC);
      pCenter.add(buttonSD);

      buttonSA.addActionListener(this);
      buttonSB.addActionListener(this);
      buttonSC.addActionListener(this);
      buttonSD.addActionListener(this);

      add(pNorth, BorderLayout.NORTH);
      add(pCenter, BorderLayout.CENTER);
      add(new JScrollPane(showArea), BorderLayout.SOUTH);


   }

   public void actionPerformed(ActionEvent e) {
      try{
          con = new Context();
          stu = new Student();
          s = new TreeMap<>();
          c = new TreeMap<>();
          int id = Integer.parseInt(textID.getText().trim());
          stu.setId(id);

          //连数据库
          String driver = "com.mysql.cj.jdbc.Driver";
          String url = "jdbc:mysql://localhost:3306/gradecalc?useSSL=false";
          String user = "root";
          String password = "FuckMySQL";
          String nameCourse;
          Double weightCourse;
          try {
              Connection connect;
              Class.forName(driver).newInstance();
              connect = DriverManager.getConnection(url, user, password);
              System.out.println("Connection Established");
              Statement statement = connect.createStatement();

              String sqlCourse = "SELECT * FROM Course";
              ResultSet rsCourse = statement.executeQuery(sqlCourse);
              while(rsCourse.next()){
                  nameCourse = rsCourse.getString("name");
                  weightCourse = rsCourse.getDouble("weight");
                  c.put(nameCourse, weightCourse);
              }

              String sqlStudent = "SELECT * FROM Student";
              ResultSet rsStudent = statement.executeQuery(sqlStudent);
              while(rsStudent.next()){
                  Integer idStu = rsStudent.getInt("idStudent");
                  String nameStu = rsStudent.getString("name");
                  if(idStu == stu.getId()) {
                      stu.setName(nameStu);
//                      System.out.println(nameStu);
                      break;
                  }
              }

              String sql = "SELECT Student.idStudent, Student.name, Course.name, Stu_Cou.gpa FROM Student, Stu_Cou, Course WHERE Student.idStudent = Stu_Cou.idStu AND Stu_Cou.idCou = Course.idCourse";
              ResultSet rs = statement.executeQuery(sql);
              while(rs.next()){
                  Integer idStu = rs.getInt("idStudent");
                  String nameCou = rs.getString("Course.name");
                  Double gpa = rs.getDouble("Stu_Cou.gpa");

                  if(idStu == stu.getId()){
                      s.put(nameCou, gpa);
                  }
              }
              rsCourse.close();
              connect.close();
          } catch (Exception dbe) {
//              System.out.print("MYSQL ERROR:" + dbe.getMessage());
              dbe.printStackTrace();
          }

          // 根据id 从 Stu_Cou 里找到对应 idStu 的学生选的课 idCou 和 gpa
          // 找到一个放一个进 s 里。

            //根据上一个结果里的 idCourse 找到每个 idCou 对应的 weight
            // 找到一个放一个进 c 里。

          //s <课程名, 绩点>
          //c <课程名, 学分>

          stu.setCourse(c);
          stu.setScore(s);

          showArea.append("学号: " + stu.getId() + "\n");
          showArea.append("姓名: " + stu.getName() + "\n");

            if(e.getSource() == buttonSA){
                con.setStrategy(new StrategyOne());
                stu.setGpa(con.getStudentScore(stu.getScore(), stu.getCourse()));
                showArea.append("总绩点: " + String.valueOf(stu.getGpa())+"\n");
            }else if(e.getSource() == buttonSB){
                con.setStrategy(new StrategyTwo());
                stu.setGpa(con.getStudentScore(stu.getScore(), stu.getCourse()));
                showArea.append("总绩点: " + String.valueOf(stu.getGpa())+"\n");
            }else if(e.getSource() == buttonSC){
                con.setStrategy(new StrategyThree());
                stu.setGpa(con.getStudentScore(stu.getScore(), stu.getCourse()));
                showArea.append("总绩点: " + String.valueOf(stu.getGpa())+"\n");
            }else if(e.getSource() == buttonSD){
                con.setStrategy(new StrategyFour());
                stu.setGpa(con.getStudentScore(stu.getScore(), stu.getCourse()));
                showArea.append("总绩点: " + String.valueOf(stu.getGpa())+"\n");
            }

      }catch (Exception ex){
         showArea.append("\n" + ex + "\n");
      }
   }
}
