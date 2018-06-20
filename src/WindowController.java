import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yunhao981 on 2018/6/19.
 */

public class WindowController extends JFrame implements ActionListener {
    private JTextArea showArea;
    private JTextField textID;
    private JButton buttonSA, buttonSB, buttonSC, buttonSD;
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
//         double a = Double.parseDouble(textA.getText().trim());
          int id = Integer.parseInt(textID.getText().trim());
            showArea.append(String.valueOf(id)+"\n");
            if(e.getSource() == buttonSA){
                showArea.append(String.valueOf(id)+"1\n");

            }else if(e.getSource() == buttonSB){
                showArea.append(String.valueOf(id)+"2\n");
            }else if(e.getSource() == buttonSC){
                showArea.append(String.valueOf(id)+"3\n");
            }else if(e.getSource() == buttonSD){
                showArea.append(String.valueOf(id)+"4\n");
            }

      }catch (Exception ex){
         showArea.append("\n" + ex + "\n");
      }
   }
}
