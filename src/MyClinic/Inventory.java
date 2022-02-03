/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyClinic;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Zombie
 */
public class Inventory extends javax.swing.JFrame {

    /**
     * Creates new form Inventory
     */
    public Inventory() {
        initComponents();
    }
    
    String presid;
    String newPresID;
    
    public Inventory(String presid){
        initComponents();
        Connect();
        this.presid = presid;
        this.newPresID = this.presid;
        
        lblpresid.setText(newPresID);
    }
    String Iname;
    String Quant;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/clinic_management","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void sales() 
    {
	DateTimeFormatter dat = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
	LocalDateTime now = LocalDateTime.now();
	String date = dat.format(now);
	

	String subtotal = txttotal.getText();
	String pay = txtpay.getText();
	String balance = txtbal.getText();

	int lastInsertedId = 0;
        int lastInsertedPay = 0;//
        int lastInsertedBal = 0;//

        
        try {
            String query = "insert into sales(date,subtotal,pay,balance) values(?,?,?,?)";
            pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1,date);
            pst.setString(2,subtotal);
            pst.setString(3, pay);
            pst.setString(4, balance);
            
            pst.executeUpdate();
            
            rs = pst.getGeneratedKeys();
            
            if(rs.next()) 
            {
                  lastInsertedId = rs.getInt(1);
            }

        int rows = jTable1.getColumnCount();

        String query1 = "insert into sales_product(sales_id, product_id, sellprice, qty, total, paid, r_balance, date) values(?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(query1);
        String pres_id;
        String item_id;
        String item_name;
        int price;
        String qty;
        int total;

        for(int i=0; i<jTable1.getRowCount(); i++)
        {
                pres_id = (String)jTable1.getValueAt(i, 0);
                item_id = (String)jTable1.getValueAt(i, 1);
                qty = jTable1.getValueAt(i, 3).toString();
                int qty1 = Integer.parseInt(qty);
                price = (int)jTable1.getValueAt(i, 4);
                total = (int)jTable1.getValueAt(i, 5);


                pst.setInt(1, lastInsertedId);
                pst.setString(2,item_id);
                pst.setInt(3,price);
                pst.setInt(4,qty1);
                pst.setInt(5,total);
                pst.setString(6,pay); //
                pst.setString(7,balance); //
                pst.setString(8,date);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Record Saved");
        }
        
            //for receipt table
          String Tot = txttotal.getText();
          String Pd = txtpay.getText();
        
          String Rquery = "insert into receipt(ItemName,Quantity,Total,Paid)values(?,?,?,?)";
            pst = con.prepareStatement(Rquery);
            pst.setString(1, Iname);
            pst.setString(2, Quant);
            pst.setString(3, Tot);
            pst.setString(4, Pd);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Please Enter proper values");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtdrugcode = new javax.swing.JTextField();
        lblpresid = new javax.swing.JLabel();
        txtdrugname = new javax.swing.JTextField();
        txtqty = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtpay = new javax.swing.JTextField();
        txtbal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(117, 117, 167));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Prescription ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Drug code");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Drug Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, -1));

        txtdrugcode.setBackground(new java.awt.Color(18, 53, 87));
        txtdrugcode.setForeground(new java.awt.Color(255, 255, 255));
        txtdrugcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdrugcodeKeyPressed(evt);
            }
        });
        jPanel1.add(txtdrugcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 110, -1));

        lblpresid.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lblpresid.setForeground(new java.awt.Color(255, 255, 255));
        lblpresid.setText("jLabel6");
        jPanel1.add(lblpresid, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        txtdrugname.setBackground(new java.awt.Color(18, 53, 87));
        txtdrugname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtdrugname, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 128, -1));
        jPanel1.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 48, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Cost");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pay");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Balance");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, -1, -1));

        txttotal.setBackground(new java.awt.Color(18, 53, 87));
        txttotal.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 76, -1));

        txtpay.setBackground(new java.awt.Color(18, 53, 87));
        txtpay.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 76, -1));

        txtbal.setBackground(new java.awt.Color(18, 53, 87));
        txtbal.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtbal, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 76, -1));

        jButton1.setBackground(new java.awt.Color(117, 117, 167));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/Sign_Add_Icon_48.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 50, 50));

        jButton2.setBackground(new java.awt.Color(117, 117, 167));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/pay.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 60, 60));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("press enter key");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        jLabel1.setBackground(new java.awt.Color(117, 117, 167));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 255));
        jLabel1.setText("Checkout...");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel10.setBackground(new java.awt.Color(117, 117, 167));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/checkout.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 150, 150));

        jButton4.setBackground(new java.awt.Color(204, 204, 255));
        jButton4.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jButton4.setText("Generate Receipt");
        jButton4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 120, 40));

        jButton5.setBackground(new java.awt.Color(204, 204, 255));
        jButton5.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jButton5.setText("Clear Receipt");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 260));

        jPanel2.setBackground(new java.awt.Color(18, 53, 87));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBackground(new java.awt.Color(204, 204, 255));
        jTable1.setFont(new java.awt.Font("Sylfaen", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prescription ID", "Drug code", "Drug Name", "Quantity", "Price ", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(0, 102, 102));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 610, 260));

        jButton3.setBackground(new java.awt.Color(153, 0, 0));
        jButton3.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 790, 300));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtdrugcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdrugcodeKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String dcode = txtdrugcode.getText(); //input drug code
            
            try {
                pst = con.prepareStatement("select * from item where itemid = ?");
                pst.setString(1,dcode); //setting input drug code
                rs = pst.executeQuery(); //retrieve drug code

                if(rs.next() == false) //checking if our input drug code is same as databse code
                {

                        JOptionPane.showMessageDialog(this, "Drug not found");

                }
                else
                {
                        String dname = rs.getString("itemname");
                        txtdrugname.setText(dname.trim());
                        txtqty.requestFocus();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,"Please Enter proper values");
            }
        }
    }//GEN-LAST:event_txtdrugcodeKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String dcode = txtdrugcode.getText();
        try {
            pst = con.prepareStatement("select * from item where itemid = ?");
            pst.setString(1,dcode);
            rs = pst.executeQuery();
            
            
        while(rs.next())
        {
            int currentqty;
            int sellprice;
            int qty;

            currentqty = rs.getInt("qty");
            sellprice = rs.getInt("sellprice");
            
            qty = Integer.parseInt(txtqty.getValue().toString()); //need to convert object type to int
            int total = sellprice * qty;

            if(qty > currentqty)
            {
                    JOptionPane.showMessageDialog(this, "Available Item: "+currentqty);
                    JOptionPane.showMessageDialog(this, "Qty not Enough");
            }
            else 
            {
                    DefaultTableModel DF = (DefaultTableModel) jTable1.getModel();                  
                    DF.addRow(new Object[] 
                    {
                        lblpresid.getText(),
                        txtdrugcode.getText(),
                        txtdrugname.getText(),
                        txtqty.getValue().toString(),
                        sellprice,
                        total,
                        
                    });
                    
                    int sum = 0;
                    for(int i=0; i<jTable1.getRowCount(); i++)
                    {
                        sum = sum + Integer.parseInt(jTable1.getValueAt(i,5).toString()); //to display value in total cost text field
                    }
                    
                    int newQty = currentqty - qty;
                    String id = txtdrugcode.getText();
                    pst = con.prepareStatement("update item set qty=? where itemid=?");
                    pst.setInt(1, newQty);
                    pst.setString(2,id);
                    pst.executeUpdate();
                    
                    //for receipt table
                    Iname = txtdrugname.getText(); 
                    Quant = txtqty.getValue().toString();
                    
                    
                    txttotal.setText(Integer.toString(sum));
                    txtdrugcode.setText("");
                    txtdrugname.setText("");
                    txtqty.setValue(0);
                    
                  
            }
        }


        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Please Enter proper values");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int pay = Integer.parseInt(txtpay.getText());
        int totalcost = Integer.parseInt(txttotal.getText());
        int bal = pay - totalcost;
       
        txtbal.setText(String.valueOf(bal));
        
        sales(); 
        txttotal.setText("");
        txtpay.setText("");
        txtbal.setText("");
         txtdrugcode.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            Connection con;
            com.mysql.jdbc.PreparedStatement pst;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/clinic_management","root","");
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Zombie\\Documents\\NetBeansProjects\\Clinic\\src\\MyClinic\\Receipt.jrxml");
               //C:\\Users\\Zombie\\Documents\\NetBeansProjects\\Clinic\\src\\pictures\\flower.jpg
            String query = "select * from receipt";
            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);
            jdesign.setQuery(updateQuery);
            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null,con);
            
            JasperViewer.viewReport(jprint,false);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
          try {
            pst = con.prepareStatement("truncate table receipt");
      
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Cleared");
         
        } catch (SQLException ex) {
            Logger.getLogger(MyClinic.Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblpresid;
    private javax.swing.JTextField txtbal;
    private javax.swing.JTextField txtdrugcode;
    private javax.swing.JTextField txtdrugname;
    private javax.swing.JTextField txtpay;
    private javax.swing.JSpinner txtqty;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
