package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener{

    Choice meternumber, cmonth;
    JTable table;
    JButton search, print, delete;
    
    CustomerDetails(){
        super("Customer Details");
        
        setSize(1200, 650);
        setLocation(200, 150);
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);
        add(sp);
        
        print = new JButton("Print");
        print.addActionListener(this);
        
        delete = new JButton("Delete");
        delete.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(print);
        panel.add(delete);
        add(panel, "South");
        
        
        setVisible(true);
    }
    
  public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == delete) {
            // Get the selected row from the table
            int selectedRow = table.getSelectedRow();

            if (selectedRow >= 0) {
                // Get the meter number from the selected row
                String meterNumber = table.getValueAt(selectedRow, 0).toString(); 

                try {
                    Conn c = new Conn();
                    String query = "DELETE FROM customer WHERE meter_no = ?";
                    PreparedStatement pstmt = c.c.prepareStatement(query);
                    pstmt.setString(1, meter_no;
                    int rowsDeleted = pstmt.executeUpdate();

                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
                        // Refresh the table after deletion
                        ResultSet rs = c.s.executeQuery("select * from customer");
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete customer.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "An error occurred while deleting customer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a customer to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}