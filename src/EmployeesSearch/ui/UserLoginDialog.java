package EmployeesSearch.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import EmployeeSearch.core.User;
import EmployeeSearch.dao.EmployeeDAO;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UserLoginDialog extends JDialog {

	 private final JPanel contentPanel = new JPanel();
	 private JPasswordField passwordField;
	 private JComboBox userComboBox;

	 private EmployeeDAO employeeDAO;

	 public void setEmployeeDAO(EmployeeDAO theEmployeeDAO) {
	  employeeDAO = theEmployeeDAO;
	 }

	 public void populateUsers(List < User > users) {
	  userComboBox.setModel(new DefaultComboBoxModel(users.toArray(new User[0])));
	 }

	 /**
	  * Create the dialog.
	  */
	 public UserLoginDialog() {
	  addWindowListener(new WindowAdapter() {
	   @Override
	   public void windowClosing(WindowEvent e) {
	    System.exit(0);
	   }
	  });
	  setTitle("User Login");
	  setBounds(100, 100, 450, 168);
	  getContentPane().setLayout(new BorderLayout());
	  contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	  getContentPane().add(contentPanel, BorderLayout.CENTER);
	  contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
	    FormSpecs.RELATED_GAP_COLSPEC,
	     FormSpecs.DEFAULT_COLSPEC,
	     FormSpecs.RELATED_GAP_COLSPEC,
	     ColumnSpec.decode("default:grow"),
	   },
	   new RowSpec[] {
	    FormSpecs.RELATED_GAP_ROWSPEC,
	    FormSpecs.DEFAULT_ROWSPEC,
	    FormSpecs.RELATED_GAP_ROWSPEC,
	    FormSpecs.DEFAULT_ROWSPEC,
	   })); {
	   JLabel lblUser = new JLabel("User");
	   contentPanel.add(lblUser, "2, 2, right, default");
	  } {
	   userComboBox = new JComboBox();
	   contentPanel.add(userComboBox, "4, 2, fill, default");
	  } {
	   JLabel lblPassword = new JLabel("Password");
	   contentPanel.add(lblPassword, "2, 4, right, default");
	  } {
	   passwordField = new JPasswordField();
	   contentPanel.add(passwordField, "4, 4, fill, default");
	  } {
	   JPanel buttonPane = new JPanel();
	   buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	   getContentPane().add(buttonPane, BorderLayout.SOUTH); {
	    JButton okButton = new JButton("OK");
	    okButton.addActionListener(new ActionListener() {
	     public void actionPerformed(ActionEvent e) {

	      // get the user id
	      if (userComboBox.getSelectedIndex() == -1) {
	       JOptionPane.showMessageDialog(UserLoginDialog.this, "You must select a user.", "Error", JOptionPane.ERROR_MESSAGE);
	       return;
	      }

	      User theUser = (User) userComboBox.getSelectedItem();
	      int userId = theUser.getId();

	      // get the password
	      char[] password = passwordField.getPassword();

	      // TO DO: you should add code here to check user id and password again db
	      //		  Since this is a demo, we will not perform this check

	      // hide the login window
	      setVisible(false);
	      dispose();

	      // now show the main app window
	      EmployeeSearchApp frame = new EmployeeSearchApp(userId, employeeDAO);
	      frame.setLoggedInUserName(theUser.getFirstName(), theUser.getLastName());

	      frame.setVisible(true);
	     }
	    });
	    okButton.setActionCommand("OK");
	    buttonPane.add(okButton);
	    getRootPane().setDefaultButton(okButton);
	   } {
	    JButton cancelButton = new JButton("Cancel");
	    cancelButton.setActionCommand("Cancel");
	    buttonPane.add(cancelButton);
	   }
	  }
	 }

	 public JComboBox getUserComboBox() {
	  return userComboBox;
	 }
}