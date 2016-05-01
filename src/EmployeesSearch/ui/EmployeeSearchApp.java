package EmployeesSearch.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import EmployeeSearch.dao.EmployeeDAO;
import EmployeeSearch.core.AuditHistory;
import EmployeeSearch.core.Employee;
import EmployeeSearch.core.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class EmployeeSearchApp extends JFrame {

	 private JPanel contentPane;
	 private JTextField lastNameTextField;
	 private JButton btnSearch;
	 private JScrollPane scrollPane;
	 private JTable table;

	 private EmployeeDAO employeeDAO;
	 private JPanel panel_1;
	 private JButton btnAddEmployee;
	 private JButton btnUpdateEmployee;
	 private JButton viewHistoryButton;

	 private int userId;
	 private JPanel topPanel;
	 private JPanel searchPanel;
	 private JLabel lblLoggedIn;
	 private JLabel loggedInUserLabel;


	 /**
	  * Launch the application.
	  */
	 public static void main(String[] args) {
	  EventQueue.invokeLater(new Runnable() {
	   public void run() {
	    try {

	     EmployeeDAO employeeDAO = new EmployeeDAO();

	     // Get users
	     List < User > users = employeeDAO.getUsers();

	     // Show login dialog
	     UserLoginDialog dialog = new UserLoginDialog();
	     dialog.populateUsers(users);
	     dialog.setEmployeeDAO(employeeDAO);

	     dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	     dialog.setVisible(true);

	    } catch (Exception e) {
	     e.printStackTrace();
	    }
	   }
	  });
	 }

	 /**
	  * Create the frame.
	  */
	 public EmployeeSearchApp(int theUserId, EmployeeDAO theEmployeeDAO) {

	  userId = theUserId;

	  employeeDAO = theEmployeeDAO;

	  setTitle("Employee Search App");
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setBounds(100, 100, 584, 300);
	  contentPane = new JPanel();
	  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	  contentPane.setLayout(new BorderLayout(0, 0));
	  setContentPane(contentPane);

	  JPanel panel = new JPanel();
	  contentPane.add(panel, BorderLayout.NORTH);
	  panel.setLayout(new BorderLayout(0, 0));

	  topPanel = new JPanel();
	  FlowLayout flowLayout_2 = (FlowLayout) topPanel.getLayout();
	  flowLayout_2.setAlignment(FlowLayout.LEFT);
	  panel.add(topPanel, BorderLayout.NORTH);

	  lblLoggedIn = new JLabel("Logged In:");
	  topPanel.add(lblLoggedIn);

	  loggedInUserLabel = new JLabel("New label");
	  topPanel.add(loggedInUserLabel);

	  searchPanel = new JPanel();
	  searchPanel.setBorder(null);
	  FlowLayout flowLayout_1 = (FlowLayout) searchPanel.getLayout();
	  flowLayout_1.setAlignment(FlowLayout.LEFT);
	  panel.add(searchPanel);

	  JLabel lblEnterLastName = new JLabel("Enter last name");
	  searchPanel.add(lblEnterLastName);

	  lastNameTextField = new JTextField();
	  searchPanel.add(lastNameTextField);
	  lastNameTextField.setColumns(10);

	  btnSearch = new JButton("Search");
	  searchPanel.add(btnSearch);
	  btnSearch.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	    // Get last name from the text field

	    // Call DAO and get employees for the last name

	    // If last name is empty, then get all employees

	    // Print out employees				

	    try {
	     String lastName = lastNameTextField.getText();

	     List < Employee > employees = null;

	     if (lastName != null && lastName.trim().length() > 0) {
	      employees = employeeDAO.searchEmployees(lastName);
	     } else {
	      employees = employeeDAO.getAllEmployees();
	     }

	     // create the model and update the "table"
	     EmployeeTableModel model = new EmployeeTableModel(employees);

	     table.setModel(model);

	     /*
	     for (Employee temp : employees) {
	     	System.out.println(temp);
	     }
	     */
	    } catch (Exception exc) {
	     JOptionPane.showMessageDialog(EmployeeSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
	    }

	   }
	  });

	  scrollPane = new JScrollPane();
	  contentPane.add(scrollPane, BorderLayout.CENTER);

	  table = new JTable();
	  scrollPane.setViewportView(table);

	  panel_1 = new JPanel();
	  contentPane.add(panel_1, BorderLayout.SOUTH);

	  btnAddEmployee = new JButton("Add Employee");
	  btnAddEmployee.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	    // create dialog
	    EmployeeDialog dialog = new EmployeeDialog(EmployeeSearchApp.this, employeeDAO, userId);

	    // show dialog
	    dialog.setVisible(true);
	   }
	  });
	  panel_1.add(btnAddEmployee);

	  btnUpdateEmployee = new JButton("Update Employee");
	  btnUpdateEmployee.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {

	    // get the selected item
	    int row = table.getSelectedRow();

	    // make sure a row is selected
	    if (row < 0) {
	     JOptionPane.showMessageDialog(EmployeeSearchApp.this, "You must select an employee", "Error",
	      JOptionPane.ERROR_MESSAGE);
	     return;
	    }

	    // get the current employee
	    Employee tempEmployee = (Employee) table.getValueAt(row, EmployeeTableModel.OBJECT_COL);

	    // create dialog
	    EmployeeDialog dialog = new EmployeeDialog(EmployeeSearchApp.this, employeeDAO,
	     tempEmployee, true, userId);

	    // show dialog
	    dialog.setVisible(true);

	   }
	  });
	  panel_1.add(btnUpdateEmployee);

	  viewHistoryButton = new JButton("View History");
	  viewHistoryButton.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {

	    // get the selected item
	    int row = table.getSelectedRow();

	    // make sure a row is selected
	    if (row < 0) {
	     JOptionPane.showMessageDialog(EmployeeSearchApp.this, "You must select an employee", "Error",
	      JOptionPane.ERROR_MESSAGE);
	     return;
	    }

	    // get the current employee
	    Employee tempEmployee = (Employee) table.getValueAt(row, EmployeeTableModel.OBJECT_COL);

	    try {
	     // get audit history for this employee
	     int employeeId = tempEmployee.getId();
	     List < AuditHistory > auditHistoryList = employeeDAO.getAuditHistory(employeeId);

	     // show audit history dialog
	     AuditHistoryDialog dialog = new AuditHistoryDialog();
	     dialog.populate(tempEmployee, auditHistoryList);

	     dialog.setVisible(true);
	    } catch (Exception exc) {
	     exc.printStackTrace();
	     JOptionPane.showMessageDialog(EmployeeSearchApp.this, "Error retrieving audit history", "Error",
	      JOptionPane.ERROR_MESSAGE);
	     return;
	    }

	   }
	  });
	  panel_1.add(viewHistoryButton);
	 }

	 public void refreshEmployeesView() {

	  try {
	   List < Employee > employees = employeeDAO.getAllEmployees();

	   // create the model and update the "table"
	   EmployeeTableModel model = new EmployeeTableModel(employees);

	   table.setModel(model);
	  } catch (Exception exc) {
	   JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
	    JOptionPane.ERROR_MESSAGE);
	  }

	 }

	 public void setLoggedInUserName(String firstName, String lastName) {
	  loggedInUserLabel.setText(firstName + " " + lastName);
	 }
	}