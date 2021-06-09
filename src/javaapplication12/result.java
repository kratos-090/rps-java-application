/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author rohit
 */
public class result extends javax.swing.JFrame {

    /**
     * Creates new form result
     */
    public result() {
        initComponents();
    }
    
    public int session(int sem)
    {
        switch(sem)
        {
            case 1,2 -> {
                return 0;
            }
            case 3,4 -> {
                return 1;
            }
            case 5,6 -> {
                return 2;
            }
            case 7,8 -> {
                return 3;
            }
        }
    return 0;
    
    }
    public result(String reg_no)
    {
        initComponents();
        JLabel[] subjectCode={jLabel5,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10,jLabel11,jLabel12};
        JLabel[] subjectName={jLabel13,jLabel15,jLabel16,jLabel17,jLabel18,jLabel19,jLabel20,jLabel21};
        JLabel[] creditTheory={jLabel14,jLabel28,jLabel22,jLabel23,jLabel24,jLabel25,jLabel27,jLabel26};
        JLabel[] creditPractical={jLabel29,jLabel36,jLabel30,jLabel31,jLabel32,jLabel33,jLabel34,jLabel35};
        JLabel[] theoryMarks={jLabel37,jLabel38,jLabel39,jLabel40,jLabel41,jLabel42,jLabel43,jLabel44};
        JLabel[] practicalMarks={jLabel45,jLabel46,jLabel47,jLabel48,jLabel49,jLabel50,jLabel51,jLabel52};
        JLabel[] midtermMarks={jLabel84,jLabel85,jLabel86,jLabel87,jLabel88,jLabel89,jLabel90,jLabel91};
        JLabel[] totalMarks={jLabel53,jLabel54,jLabel55,jLabel56,jLabel57,jLabel58,jLabel59,jLabel60};
        JLabel[] gradePoints={jLabel62,jLabel63,jLabel64,jLabel65,jLabel66,jLabel67,jLabel68,jLabel69};
        JLabel[] creditPoints={jLabel71,jLabel72,jLabel73,jLabel74,jLabel75,jLabel76,jLabel77,jLabel78};
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rps","root","ro07hi11t99");
            Statement stmt=conn.createStatement();
            ResultSet rst,rst2;
            String sql;
            sql="SELECT * FROM rps.student__records WHERE reg_no='"+reg_no+"';";
            rst=stmt.executeQuery(sql);
            rst.next();
            int sem=rst.getInt("current_sem");
            
            if(sem<8)
                sem=sem-1;
            
           jLabel107.setText(Integer.toString(rst.getInt(sem+5)));
           jLabel111.setText(reg_no);
           
            
            sql="SELECT * FROM rps.records WHERE reg_no='"+reg_no+"' AND sem='"+sem+"';";
            rst=stmt.executeQuery(sql);
         
            
            int[] theory_marks=new int[10];
            int[] practical_marks=new int[10];
            int[] midterm_marks=new int[10];
            int[] total_marks=new int[10];
            double[] grade_points=new double[10];
            double[] credit_points=new double[10];
            String[] sub_code=new String[10];
            
            int i=0;
            int count=0;
            while(rst.next())
            {
                count=count+1;
                sub_code[i]=rst.getString("sub_code");
                theory_marks[i]=rst.getInt("th_mrks");
                practical_marks[i]=rst.getInt("pr_mrks");
                midterm_marks[i]=rst.getInt("mt_mrks");
                total_marks[i]=rst.getInt("total_marks");
                grade_points[i]=rst.getDouble("gradepoint");
                credit_points[i]=rst.getDouble("credit_point");
                i=i+1;
            }
            sql="SELECT acadmic_year FROM rps.student_info WHERE reg_no='"+reg_no+"';";
            rst=stmt.executeQuery(sql);
            rst.next();
            String acadmic_year=rst.getString("acadmic_year");
            for(i=0;i<count;i++)
            {
                sql="SELECT * FROM subject WHERE sub_code='"+sub_code[i]+"' AND acadmic_year='"+acadmic_year+"';";
                rst2=stmt.executeQuery(sql);
                rst2.next();
                
                String sub_name=rst2.getString("sub_name");
                int th_cr=rst2.getInt("credit_th");
                int pr_cr=rst2.getInt("credit_pr");
                
                
                
               
                subjectCode[i].setText(sub_code[i]);
                subjectName[i].setText(sub_name);
                creditTheory[i].setText(Integer.toString(th_cr));
                creditPractical[i].setText(Integer.toString(pr_cr));
                theoryMarks[i].setText(Integer.toString(theory_marks[i]));
                practicalMarks[i].setText(Integer.toString(practical_marks[i]));
                midtermMarks[i].setText(Integer.toString(midterm_marks[i]));
                totalMarks[i].setText(Integer.toString(total_marks[i]));
                gradePoints[i].setText(Double.toString(grade_points[i]));
                creditPoints[i].setText(Double.toString(credit_points[i]));
               
            }
            sql="SELECT * FROM rps.gpa WHERE reg_no='"+reg_no+"';";
            rst=stmt.executeQuery(sql);
            rst.next();
            double sgpa=rst.getDouble(sem+2);
            double ogpa=rst.getDouble("ogpa");
            jLabel96.setText(Double.toString(sgpa));
            jLabel97.setText(Double.toString(ogpa));
            
            sql="SELECT * FROM rps.student_info WHERE reg_no='"+reg_no+"';";
            rst=stmt.executeQuery(sql);
            rst.next();
            jLabel108.setText(rst.getString("name"));
            jLabel109.setText(rst.getString("fname"));
            jLabel110.setText(rst.getString("mname"));
            jLabel113.setText(Integer.toString(sem));
            jLabel114.setText((Integer.parseInt(acadmic_year)+session(sem))+"-"+(Integer.parseInt(acadmic_year)+session(sem)+1));
            
            String current_class;
            current_class = switch (sem) {
                case 1, 2 -> "FIRST";
                case 3, 4 -> "SECOND";
                case 5, 6 -> "THIRD";
                case 7, 8 -> "FOURTH";
                default -> "--";
            };
            jLabel112.setText(current_class);
            
            
            for(i=i-1;i<8;i++)
            {
                subjectCode[i].setVisible(false);
                subjectName[i].setVisible(false);
                creditTheory[i].setVisible(false);
                creditPractical[i].setVisible(false);
                theoryMarks[i].setVisible(false);
                practicalMarks[i].setVisible(false);
                midtermMarks[i].setVisible(false);
                totalMarks[i].setVisible(false);
                gradePoints[i].setVisible(false);
                creditPoints[i].setVisible(false);
                
            }
            
            conn.close();
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel95.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel95.setText("SGPA");

        jLabel96.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel96.setText("--");

        jLabel97.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel97.setText("--");

        jLabel98.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel98.setText("OGPA");

        jButton1.setText("Check previous result");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel40.setText("--");

        jLabel41.setText("--");

        jLabel42.setText("--");

        jLabel43.setText("--");

        jLabel44.setText("--");

        jLabel45.setText("--");

        jLabel46.setText("--");

        jLabel47.setText("--");

        jLabel100.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel100.setText("Mother's Name");

        jLabel48.setText("--");

        jLabel101.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel101.setText("Name");

        jLabel49.setText("--");

        jLabel102.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel102.setText("Father's Name");

        jLabel103.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel103.setText("Enrollment No.");

        jLabel104.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel104.setText("Session");

        jLabel105.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel105.setText("Class");

        jLabel79.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel79.setText("Th");

        jLabel106.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel106.setText("Semester");

        jLabel80.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel80.setText("Pr");

        jLabel107.setText("jLabel107");

        jLabel81.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel81.setText("Th");

        jLabel108.setText("jLabel108");

        jLabel82.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel82.setText("Pr");

        jLabel83.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel83.setText("Total");

        jLabel84.setText("--");

        jLabel85.setText("--");

        jLabel86.setText("--");

        jLabel87.setText("--");

        jLabel88.setText("--");

        jLabel109.setText("jLabel109");

        jLabel110.setText("jLabel110");

        jLabel111.setText("jLabel111");

        jLabel112.setText("jLabel112");

        jLabel113.setText("jLabel113");

        jLabel114.setText("jLabel114");

        jLabel89.setText("--");

        jLabel90.setText("--");

        jLabel91.setText("--");

        jLabel92.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel92.setText("MT");

        jLabel93.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel93.setText("Points");

        jLabel94.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel94.setText("Points");

        jLabel9.setText("--");

        jLabel10.setText("--");

        jLabel11.setText("--");

        jLabel12.setText("--");

        jLabel13.setText("--");

        jLabel15.setText("--");

        jLabel16.setText("--");

        jLabel17.setText("--");

        jLabel18.setText("--");

        jLabel19.setText("--");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Course No.");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Titile of the course");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Credit ");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("     Marks Obtained");

        jLabel5.setText("--");

        jLabel6.setText("--");

        jLabel7.setText("--");

        jLabel50.setText("--");

        jLabel8.setText("--");

        jLabel51.setText("--");

        jLabel20.setText("--");

        jLabel52.setText("--");

        jLabel53.setText("--");

        jLabel54.setText("--");

        jLabel55.setText("--");

        jLabel56.setText("--");

        jLabel57.setText("--");

        jLabel58.setText("--");

        jLabel59.setText("--");

        jLabel21.setText("--");

        jLabel14.setText("--");

        jLabel22.setText("--");

        jLabel23.setText("--");

        jLabel24.setText("--");

        jLabel25.setText("--");

        jLabel26.setText("--");

        jLabel27.setText("--");

        jLabel60.setText("--");

        jLabel28.setText("--");

        jLabel61.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel61.setText("Grade");

        jLabel29.setText("--");

        jLabel62.setText("--");

        jLabel63.setText("--");

        jLabel64.setText("--");

        jLabel65.setText("--");

        jLabel66.setText("--");

        jLabel67.setText("--");

        jLabel68.setText("--");

        jLabel69.setText("--");

        jLabel30.setText("--");

        jLabel31.setText("--");

        jLabel32.setText("--");

        jLabel33.setText("--");

        jLabel34.setText("--");

        jLabel35.setText("--");

        jLabel36.setText("--");

        jLabel37.setText("--");

        jLabel70.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel70.setText("Credit");

        jLabel38.setText("--");

        jLabel71.setText("--");

        jLabel39.setText("--");

        jLabel72.setText("--");

        jLabel73.setText("--");

        jLabel74.setText("--");

        jLabel75.setText("--");

        jLabel76.setText("--");

        jLabel77.setText("--");

        jLabel78.setText("--");

        jLabel99.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel99.setText("Roll NO.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(72, 72, 72))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168)
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 487, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel100)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel110))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel5)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(jLabel99)
                                .addComponent(jLabel101)
                                .addComponent(jLabel102))
                            .addGap(24, 24, 24)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel15)
                                                .addComponent(jLabel16)
                                                .addComponent(jLabel17)
                                                .addComponent(jLabel18)
                                                .addComponent(jLabel13)))
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel21))
                                    .addGap(80, 80, 80)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14)
                                                .addComponent(jLabel23)
                                                .addComponent(jLabel22)
                                                .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel26)
                                                .addComponent(jLabel27)
                                                .addComponent(jLabel25)
                                                .addComponent(jLabel24))
                                            .addGap(51, 51, 51)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel29)
                                                .addComponent(jLabel31)
                                                .addComponent(jLabel30)
                                                .addComponent(jLabel36)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel35)
                                                        .addComponent(jLabel34)
                                                        .addComponent(jLabel33))
                                                    .addComponent(jLabel32))))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(158, 158, 158)
                                                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(162, 162, 162)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel38)
                                                        .addComponent(jLabel39)
                                                        .addComponent(jLabel40)
                                                        .addComponent(jLabel41)
                                                        .addComponent(jLabel42)
                                                        .addComponent(jLabel43)
                                                        .addComponent(jLabel44)
                                                        .addComponent(jLabel37))))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel46)
                                                .addComponent(jLabel47)
                                                .addComponent(jLabel48)
                                                .addComponent(jLabel49)
                                                .addComponent(jLabel50)
                                                .addComponent(jLabel51)
                                                .addComponent(jLabel52)
                                                .addComponent(jLabel45)
                                                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel85)
                                                .addComponent(jLabel86)
                                                .addComponent(jLabel87)
                                                .addComponent(jLabel88)
                                                .addComponent(jLabel89)
                                                .addComponent(jLabel90)
                                                .addComponent(jLabel91)
                                                .addComponent(jLabel84))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(10, 10, 10)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel54)
                                                        .addComponent(jLabel55)
                                                        .addComponent(jLabel56)
                                                        .addComponent(jLabel57)
                                                        .addComponent(jLabel58)
                                                        .addComponent(jLabel59)
                                                        .addComponent(jLabel60)
                                                        .addComponent(jLabel53)))
                                                .addComponent(jLabel83))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(33, 33, 33)
                                                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(56, 56, 56)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel69)
                                                        .addComponent(jLabel68)
                                                        .addComponent(jLabel67)
                                                        .addComponent(jLabel66)
                                                        .addComponent(jLabel65)
                                                        .addComponent(jLabel64)
                                                        .addComponent(jLabel63)
                                                        .addComponent(jLabel62)))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(158, 158, 158)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(36, 36, 36)
                                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(27, 27, 27)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel72)
                                                .addComponent(jLabel71)
                                                .addComponent(jLabel73)
                                                .addComponent(jLabel74)
                                                .addComponent(jLabel75)
                                                .addComponent(jLabel76)
                                                .addComponent(jLabel77)
                                                .addComponent(jLabel78))
                                            .addGap(64, 64, 64))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel109)
                                        .addComponent(jLabel108)
                                        .addComponent(jLabel107))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel103)
                                        .addComponent(jLabel105)
                                        .addComponent(jLabel106)
                                        .addComponent(jLabel104))
                                    .addGap(87, 87, 87)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel114)
                                        .addComponent(jLabel113)
                                        .addComponent(jLabel112)
                                        .addComponent(jLabel111))
                                    .addGap(143, 143, 143)))))
                    .addContainerGap(165, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(600, 600, 600)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel95)
                    .addComponent(jLabel96)
                    .addComponent(jLabel98)
                    .addComponent(jLabel97))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel99)
                                .addComponent(jLabel107))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel101)
                                .addComponent(jLabel108))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel102)
                                .addComponent(jLabel109))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel100)
                                .addComponent(jLabel110)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel103)
                                .addComponent(jLabel111))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel105)
                                .addComponent(jLabel112))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel106)
                                .addComponent(jLabel113))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel104)
                                .addComponent(jLabel114))))
                    .addGap(73, 73, 73)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel81)
                                        .addComponent(jLabel82))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel37)
                                        .addComponent(jLabel45))
                                    .addGap(29, 29, 29)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel38)
                                        .addComponent(jLabel46))
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel39)
                                        .addComponent(jLabel47))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel40)
                                        .addComponent(jLabel48))
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel41)
                                        .addComponent(jLabel49))
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel42)
                                        .addComponent(jLabel50))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel43)
                                        .addComponent(jLabel51))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel44)
                                        .addComponent(jLabel52)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel92)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel84)
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel85)
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel86)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel87)
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel88)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel89)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel90)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel91))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel61))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(29, 29, 29)
                                            .addComponent(jLabel6)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel7)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel8)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel9)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel10)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel11)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel12))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addGap(29, 29, 29)
                                            .addComponent(jLabel15)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel16)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel17)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel18)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel19)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel20)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel21))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel79)
                                        .addComponent(jLabel80))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addGap(29, 29, 29)
                                            .addComponent(jLabel28)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel22)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel23)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel24)
                                            .addGap(26, 26, 26)
                                            .addComponent(jLabel25)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel27)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel26))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel29)
                                            .addGap(29, 29, 29)
                                            .addComponent(jLabel36)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel30)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel31)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel32)
                                            .addGap(26, 26, 26)
                                            .addComponent(jLabel33)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel34)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel35))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel94)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel62)
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel63)
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel64)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel65)
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel66)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel67)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel68)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel69))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel83)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel53)
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel54)
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel55)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel56)
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel57)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel58)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel59)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel60)
                                    .addGap(7, 7, 7))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel70)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel93)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71)
                            .addGap(29, 29, 29)
                            .addComponent(jLabel72)
                            .addGap(28, 28, 28)
                            .addComponent(jLabel73)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel74)
                            .addGap(28, 28, 28)
                            .addComponent(jLabel75)
                            .addGap(26, 26, 26)
                            .addComponent(jLabel76)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel77)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel78)))
                    .addContainerGap(122, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String sem=JOptionPane.showInputDialog("Enter Semester");
        previous_result m=new previous_result(jLabel111.getText(),Integer.parseInt(sem));
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new result().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    // End of variables declaration//GEN-END:variables
}
