package swing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Breed;
import busines.Facade;

public class Breeds {
    private JFrame frame;
    private JTable table;
    private JTextField breedName;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Breeds window = new Breeds();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the application.
     */
    public Breeds() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
		Facade.init(); // TODO: Move this to main window init
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        JLabel lblBreeds = new JLabel("Breeds");
        lblBreeds.setHorizontalAlignment(SwingConstants.CENTER);
        lblBreeds.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblBreeds.setBounds(10, 11, 414, 25);
        frame.getContentPane().add(lblBreeds);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 47, 414, 150);
        frame.getContentPane().add(scrollPane);
        
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Name"
            }
        ));
        scrollPane.setViewportView(table);
        
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(10, 208, 46, 14);
        frame.getContentPane().add(lblName);
        
        breedName = new JTextField();
        breedName.setBounds(66, 205, 86, 20);
        frame.getContentPane().add(breedName);
        breedName.setColumns(10);
        
        JButton btnAdd = new JButton("Add");
        
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = breedName.getText();
                
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields");
                } else {
                    try {
                        breedName.setText("");
                        Facade.createBreed(name);
                        updateTable();
                    } catch (Exception e1) {
                    	JOptionPane.showMessageDialog(frame, e1);
					}
                }
            }
        }
        );
        btnAdd.setBounds(162, 204, 89, 23);
        frame.getContentPane().add(btnAdd);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String name = breedName.getText();
                
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields");
                } else {
                    try {
                        breedName.setText("");
                        Facade.deleteBreed(name);
                        updateTable();
                    } catch (Exception e1) {
                    	JOptionPane.showMessageDialog(frame, e1);
					}
                }
            }
        }
        );
        btnDelete.setBounds(162, 229, 89, 23);
        frame.getContentPane().add(btnDelete);

        updateTable();
    }

    public void updateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Breed b : Facade.readAllBreeds()) {
            model.addRow(new Object[] { b.getName()});
        }
    }
}
