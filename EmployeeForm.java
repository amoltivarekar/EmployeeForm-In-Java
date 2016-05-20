import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class EmployeeForm implements ActionListener,KeyListener
{
	JFrame frm,frmChoice,frmDelete,frmUpdate;
	JPanel bigPnl,leftPnl,left1Pnl,left2Pnl,left2TopPnl,left2BottomPnl,rightPnl,right1Pnl,right2Pnl,right1TopPnl,right1BottomPnl,genderPnl,hobbiesPnl,msgPnl;
	GridLayout grdFrm,grdBigPnl,grdLeftPnl,grdRightPnl,grdLeft2Pnl,grdRight1Pnl,grdGender,grdHobbies,grdMsg,grdfrmChoice,grdfrmDelete;
	JLabel lblEmpName,lblEmpId,lblEmpGender,lblEmpAddress,lblEmpMobNo,lblEmpEmailId,lblEmpQualification,lblEmpDept,
	lblEmpDutyTm,lblEmployee,lblForm,lblLeft2Blanck1,lblLeft2Blanck2,lblRight1Blanck1,lblRight1Blanck2,lblSubEmpName,lblSubMobNo,
	lblSubAddress,lblSubEmailID,lblSubGender,lblSubQaulif,lblSubDept,lblSubDuty,lblSubMobNolength,lblKeyEmpName,lblKeyMobNo,
	lblKeyMobLn,lblHobbies,lblFirstEntry,lblLastEntry,lblMsgForEdit,lblMsgForDelete,lblSubEmpId,lblUpdate1,lblset,lblwhere,lblboth;
	JButton btnFirst,btnLast,btnNext,btnPrev,btnClear,btnAdd,btnUpdate,btnExit,btnDelete,btnEdit,btnOKdelete,btnOkUpdate;
	JTextField txtEmpName,txtEmpId,txtMobNo,txtEmailId;
	JTextArea txtAddress;
	JScrollPane sp;
	JRadioButton rbMale,rbFemale,rbUpdate,rbDelete,rbAdd,rbEmpName,rbEmpid,rbEmpNameUpdate,rbEmpidUpdate,rbSearch;
	ButtonGroup bg,bgBtn,bgDel,bgUpdate;
	JComboBox cbxQualification,cbxDept,cbxEmpNameCol,cbxEmpIdCol,cbxEmpIdCol2;
	Font f14,f16,f18;
	Color colorBlanck;
	JCheckBox chkMusic,chkSports;

	Connection  con;
	Statement stmt;
	PreparedStatement pstmtadd,prStmtDeleteid,prStmtDeleteName,prUpdate,prEByUpdate,prEIByUpdate,prENSelect,prEISelect;
	ResultSet rs,rs1,rs2,rs3,prs,rsEmpName,rsEmpId,rsENUpdate,rsEIUpdate,rsMyEmpId,r;
	String url,username,password;
	String[] strEmpName,strEmpId,strMyEmpId;
	String name;

	ImageIcon save1,clear,delete,update;

	public void create()
	{
		save1 = new ImageIcon("../icon/save-icon.png");
		clear = new ImageIcon("../icon/clear.png");
		delete = new ImageIcon("../icon/delete.png");
		update = new ImageIcon("../icon/update4.png");

		frm = new JFrame();
		frmChoice = new JFrame("Choice");
		frmDelete = new JFrame("Delete");
		frmUpdate = new JFrame("Select");
		
		f14 = new Font("Century Gothic",0,14);
		f16 = new Font("Century Gothic",0,16);
		f18 = new Font("Century Gothic",1,18);

		colorBlanck = new Color(240,240,240);
		
		grdFrm = new GridLayout(1,1);
		grdBigPnl = new GridLayout(1,2);
		grdLeftPnl = new GridLayout(1,2);
		grdLeft2Pnl = new GridLayout(11,1,0,20);
		grdRightPnl = new GridLayout(1,2);
		grdRight1Pnl = new GridLayout(11,1,0,20);
		grdGender = new GridLayout(1,2);
		grdHobbies = new GridLayout(1,2);
		grdMsg = new GridLayout(1,1);
		grdfrmChoice = new GridLayout(5,1);
		grdfrmDelete = new GridLayout(4,1);

		bigPnl = new JPanel();
		leftPnl = new JPanel();
		left1Pnl = new JPanel();
		left2Pnl = new JPanel();
		left2TopPnl = new JPanel();
		left2BottomPnl = new JPanel();
		rightPnl = new JPanel();
		right1Pnl = new JPanel();
		right2Pnl = new JPanel();
		right1TopPnl = new JPanel();
		right1BottomPnl = new JPanel();
		genderPnl = new JPanel();
		hobbiesPnl = new JPanel();
		msgPnl = new JPanel();

		lblEmployee = new JLabel(" Employee ");
		lblEmpName = new JLabel(" Employee Name ");
		lblEmpId = new JLabel(" Employee ID ");
		lblEmpGender = new JLabel(" Gender ");
		lblEmpAddress = new JLabel(" Address ");
		lblEmpMobNo = new JLabel(" Mobile No. ");
		lblEmpEmailId = new JLabel(" Email ID ");
		lblEmpQualification = new JLabel(" Qualification ");
		lblEmpDept = new JLabel(" Department ");
		lblEmpDutyTm = new JLabel(" Duty Time ");
		lblForm = new JLabel("Form");
		lblLeft2Blanck1= new JLabel("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		lblLeft2Blanck2 = new JLabel("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");	
		lblRight1Blanck1 = new JLabel("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		lblRight1Blanck2 = new JLabel("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		lblSubEmpName = new JLabel(" Enter The Employee Name ");
		lblSubMobNo = new JLabel(" Enter The Mobile Number ");
		lblSubAddress = new JLabel(" Enter The Address ");
		lblSubEmailID = new JLabel(" Enter The Email ID ");
		lblSubGender = new JLabel(" Select The Gender ");
		lblSubQaulif = new JLabel(" Select The Qualification ");
		lblSubDept = new JLabel(" Select The Department ");
		lblSubDuty = new JLabel(" Select The Duty Time ");
		lblKeyEmpName = new JLabel(" Number Not Allowed ");
		lblSubMobNolength = new JLabel(" Your Mobile Number is Less than 10 digit ");
		lblKeyMobNo = new JLabel(" Enter Only Numbers ");
		lblKeyMobLn = new JLabel(" Enter Only 10 Digit Mobile Number ");
		lblHobbies = new JLabel(" Hobbies ");
		lblFirstEntry = new JLabel(" This Is First Entry ");
		lblLastEntry = new JLabel(" This Is Last Entry ");
		lblMsgForEdit = new JLabel(" Do You Want To ? ");
		lblMsgForDelete = new JLabel("Do You Want To DELETE Record By ??");
		lblSubEmpId = new JLabel("Enter The Employee ID ");
		lblUpdate1 = new JLabel(" Select      ");
		lblset = new JLabel("Select Column Name Those After Using SET");
		lblwhere = new JLabel("Select Column Name, Those After Using WHERE");
		lblboth  = new JLabel("Both Selected Column Name Are Same");
		
		
		btnFirst = new JButton("FIRST");
		btnLast = new JButton("LAST");
		btnNext = new JButton(">>");
		btnPrev = new JButton("<<");
		btnUpdate = new JButton(update);
		btnAdd = new JButton(save1);
		btnClear = new JButton(clear);
		btnExit = new JButton("EXIT");
		btnDelete = new JButton(delete);
		btnEdit = new JButton("EDIT");
		btnOKdelete = new JButton("OK");
		btnOkUpdate = new JButton("SUBMIT");

		txtEmpName = new JTextField(10);
		txtEmpId = new JTextField(10);
		txtMobNo = new JTextField(10);
		txtEmailId = new JTextField(10);

		txtAddress = new JTextArea();
		sp = new JScrollPane(txtAddress);

		rbMale = new JRadioButton("Male");
		rbFemale = new JRadioButton("Female");
		rbUpdate = new JRadioButton("UPDATE");
		rbAdd = new JRadioButton("ADD");
		rbDelete = new JRadioButton("DELETE");
		rbEmpName = new JRadioButton(" Employee Name ");
		rbEmpid = new JRadioButton(" Employee ID ");
		rbEmpNameUpdate = new JRadioButton(" Employee Name ");
		rbEmpidUpdate = new JRadioButton(" Employee ID ");
		rbSearch = new JRadioButton("SEARCH");
		
		bg = new ButtonGroup();
		bgBtn = new ButtonGroup();
		bgDel = new ButtonGroup();
		bgUpdate = new ButtonGroup();

		chkMusic = new JCheckBox("Music");
		chkSports = new JCheckBox("Sports");
			
		String[] qualification = new String[]{"Select Qualification","S.S.C","H.S.C","Graduate"};
		cbxQualification = new JComboBox(qualification);

		String[] department = new String[]{"Select Department","Manager","Techniciane","Officer","Clerk"};
		cbxDept = new JComboBox(department);
		
		cbxEmpNameCol = new JComboBox();
		cbxEmpIdCol = new JComboBox();
		cbxEmpIdCol2 =new JComboBox();

		
	}
	
	public void addComp()
	{
		left2TopPnl.add(lblLeft2Blanck1);
		left2TopPnl.add(lblEmployee);
		left2BottomPnl.add(btnFirst);
		left2BottomPnl.add(btnNext);
		left2BottomPnl.add(btnPrev);
		left2BottomPnl.add(btnLast);
		left2BottomPnl.add(btnEdit);
			
		left2Pnl.add(left2TopPnl);
		left2Pnl.add(lblEmpName);
		left2Pnl.add(lblEmpId);
		left2Pnl.add(lblEmpGender);
		left2Pnl.add(lblEmpAddress);
		left2Pnl.add(lblEmpMobNo);
		left2Pnl.add(lblEmpEmailId);
		left2Pnl.add(lblHobbies);
		left2Pnl.add(lblEmpQualification);
		left2Pnl.add(lblEmpDept);
		left2Pnl.add(left2BottomPnl);
		
		leftPnl.add(left1Pnl);
		leftPnl.add(left2Pnl);

		bg.add(rbMale);
		bg.add(rbFemale);
		bgUpdate.add(rbEmpNameUpdate);
		bgUpdate.add(rbEmpidUpdate);
		
		genderPnl.add(rbMale);
		genderPnl.add(rbFemale);

		hobbiesPnl .add(chkMusic);
		hobbiesPnl .add(chkSports);

		right1TopPnl.add(lblForm);
		right1TopPnl.add(lblRight1Blanck1);
		right1BottomPnl.add(btnUpdate);
		right1BottomPnl.add(btnAdd);
		right1BottomPnl.add(btnDelete);
		right1BottomPnl.add(btnClear);

		right1Pnl.add(right1TopPnl);
		right1Pnl.add(txtEmpName);
		right1Pnl.add(txtEmpId);
		right1Pnl.add(genderPnl);
		right1Pnl.add(sp);
		right1Pnl.add(txtMobNo);
		right1Pnl.add(txtEmailId);
		right1Pnl.add(hobbiesPnl);
		right1Pnl.add(cbxQualification);
		right1Pnl.add(cbxDept);
		right1Pnl.add(right1BottomPnl);
				
		rightPnl.add(right1Pnl);
		rightPnl.add(right2Pnl);
		
		bigPnl.add(leftPnl);
		bigPnl.add(rightPnl);
		
		frm.add(bigPnl);		
	}
	public void display()
	{	
		btnAdd.setSize(20,20);	
		txtEmpId.setEditable(false);
		txtEmpId.setEnabled(true);
	
		lblEmployee.setFont(f18);
		lblEmpName.setFont(f16);
		lblEmpId.setFont(f16);
		lblEmpGender.setFont(f16);
		lblEmpAddress.setFont(f16);
		lblEmpMobNo.setFont(f16);
		lblEmpEmailId.setFont(f16);
		lblEmpQualification.setFont(f16);
		lblEmpDept.setFont(f16);
		lblEmpDutyTm.setFont(f16);
		btnAdd.setFont(f14);
		btnFirst.setFont(f14);
		btnLast.setFont(f14);
		btnNext.setFont(f14);
		btnPrev.setFont(f14);
		btnDelete.setFont(f14);
		btnEdit.setFont(f14);
		rbMale.setFont(f16);
		btnUpdate.setFont(f14);
		btnExit.setFont(f14);
		rbFemale.setFont(f16);
		txtEmpName.setFont(f14);
		txtEmpId.setFont(f14);
		txtMobNo.setFont(f14);
		txtEmailId.setFont(f14);
		txtAddress.setFont(f14);
		cbxQualification.setFont(f14);
		cbxDept.setFont(f14);
		btnClear.setFont(f14);
		lblForm.setFont(f18);
		lblSubEmpName.setFont(f16);
		lblSubMobNo.setFont(f16);
		lblSubAddress.setFont(f16);
		lblSubEmailID.setFont(f16);		
		lblSubGender.setFont(f16);
		lblSubQaulif.setFont(f16);
		lblSubDept.setFont(f16);
		lblSubDuty.setFont(f16);
		lblKeyEmpName.setFont(f16);	
		lblSubMobNolength.setFont(f16);
		lblKeyMobNo.setFont(f16);
		lblKeyMobLn.setFont(f16);
		lblHobbies.setFont(f16);
		chkMusic.setFont(f16);
		chkSports.setFont(f16);
		lblFirstEntry.setFont(f16);
		lblLastEntry.setFont(f16);
		rbUpdate.setFont(f16);
		rbAdd.setFont(f16);
		rbDelete.setFont(f16);
		lblMsgForEdit.setFont(f16);
		rbEmpName.setFont(f16);
		rbEmpid.setFont(f16);
		lblMsgForDelete.setFont(f16);
		btnOKdelete.setFont(f14);
		lblSubEmpId.setFont(f16);
		lblUpdate1.setFont(f16);
		cbxEmpNameCol.setFont(f16);
		cbxEmpIdCol.setFont(f16);
		btnOkUpdate.setFont(f14);
		lblset.setFont(f16);
		lblwhere.setFont(f16);
		lblboth.setFont(f16);
		rbEmpNameUpdate.setFont(f16);
		rbEmpidUpdate.setFont(f16);
		rbSearch.setFont(f16);
		cbxEmpIdCol2.setFont(f14);
		
		left1Pnl.setBackground(Color.white);
		right2Pnl.setBackground(Color.white);

		lblLeft2Blanck1.setForeground(colorBlanck);
		lblLeft2Blanck2.setForeground(colorBlanck);	
		lblRight1Blanck1.setForeground(colorBlanck);
		lblRight1Blanck2.setForeground(colorBlanck);

		leftPnl.setLayout(grdLeftPnl);
		left2Pnl.setLayout(grdLeft2Pnl);
		rightPnl.setLayout(grdRightPnl);
		right1Pnl.setLayout(grdRight1Pnl);
		bigPnl.setLayout(grdBigPnl);
		genderPnl.setLayout(grdGender);
		hobbiesPnl.setLayout(grdHobbies);
		msgPnl.setLayout(grdMsg);
		msgPnl.setBackground(Color.RED);

		txtEmpName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtEmpId.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtMobNo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtEmailId.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtAddress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		btnUpdate.setEnabled(false);
		btnAdd.setEnabled(false);
		btnDelete.setEnabled(false);

		frm.setLayout(grdFrm);
		frm.setVisible(true);
		frm.setSize(1366,730);
		frm.setTitle("Employee Form");
		frm.setResizable(true);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void frameChoice()
	{
		bgBtn.remove(rbUpdate);
		bgBtn.remove(rbAdd);
		bgBtn.remove(rbDelete);
		bgBtn.remove(rbSearch);
		
		rbUpdate.setSelected(false);
		rbAdd.setSelected(false);
		rbDelete.setSelected(false);
		rbSearch.setSelected(false);
		
		frmChoice.setLayout(grdfrmChoice);
		frmChoice.setVisible(true);
		frmChoice.setSize(300,300);
		frmChoice.setResizable(false);
		
		bgBtn.add(rbUpdate);
		bgBtn.add(rbAdd);
		bgBtn.add(rbDelete);
		bgBtn.add(rbSearch);
		
		frmChoice.add(lblMsgForEdit);
		frmChoice.add(rbUpdate);
		frmChoice.add(rbAdd);
		frmChoice.add(rbDelete);
		frmChoice.add(rbSearch);
	}
	public void frameDelete()
	{
		bgDel.remove(rbEmpName);
		bgDel.remove(rbEmpid);

		rbEmpName.setSelected(false);
		rbEmpid.setSelected(false);
		
		frmDelete.setLayout(grdfrmDelete);
		frmDelete.setVisible(true);
		frmDelete.setSize(320,250);
		frmDelete.setResizable(false);
		
		bgDel.add(rbEmpName);
		bgDel.add(rbEmpid);
		
		frmDelete.add(lblMsgForDelete);
		frmDelete.add(rbEmpName);
		frmDelete.add(rbEmpid);
		frmDelete.add(btnOKdelete);
	}
	public void frameUpdate()
	{
		frmUpdate.setLayout(new FlowLayout());
		frmUpdate.setVisible(true);
		frmUpdate.setSize(1000,270);
		frmUpdate.setResizable(false);
		frmUpdate.add(lblUpdate1);	
		frmUpdate.add(rbEmpNameUpdate);
		frmUpdate.add(cbxEmpNameCol);
		frmUpdate.add(rbEmpidUpdate);
		frmUpdate.add(cbxEmpIdCol);
		frmUpdate.add(btnOkUpdate);
		frmUpdate.setLocationRelativeTo(null);
	}
	public void dbConnection()
	{
		username="sa";			
		password="Apt@1234";
		url="jdbc:sqlserver://ACE_6;databaseName=MYDB";
	
		try
		{
			con=DriverManager.getConnection(url,username,password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from EmployeeTable");
			rs.next();                                                                   		
		}
		catch(Exception e)
		{
			System.out.println("Hello "+e);
		}
	}
	public void register()
	{
		btnAdd.addActionListener(this);
		btnClear.addActionListener(this);
		btnFirst.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrev.addActionListener(this);
		btnLast.addActionListener(this);
		btnDelete.addActionListener(this);
		btnEdit.addActionListener(this);
		btnOKdelete.addActionListener(this);
		rbUpdate.addActionListener(this);
		rbAdd.addActionListener(this);
		rbDelete.addActionListener(this);
		btnOkUpdate.addActionListener(this);
		btnUpdate.addActionListener(this);
		rbEmpNameUpdate.addActionListener(this);
		rbEmpidUpdate.addActionListener(this);
		cbxEmpNameCol.addActionListener(this);
		cbxEmpIdCol.addActionListener(this);
		rbSearch.addActionListener(this);
		
		txtEmpName.addKeyListener(this);
		txtMobNo.addKeyListener(this);
	}
	public void data()
	{
		try
		{
			chkMusic.setSelected(false);
			chkSports.setSelected(false);
			
			txtEmpName.setText(""+rs.getString(1));
			txtEmpId.setText(""+rs.getInt(2));
			txtAddress.setText(""+rs.getString(4));
			txtMobNo.setText(""+rs.getString(5));
			txtEmailId.setText(""+rs.getString(6));
			cbxQualification.setSelectedItem(""+rs.getString(9));
			cbxDept.setSelectedItem(""+rs.getString(10));
			if(rs.getString(3).equals("Male"))
			{
				rbMale.setSelected(true);
			}
			else
			{
				rbFemale.setSelected(true);
			}

			if(rs.getString(7).equals("Music"))
			{
				chkMusic.setSelected(true);
			}
			if(rs.getString(8).equals("Sports"))
			{
				chkSports.setSelected(true);
			}			
		}
		catch(Exception e)
		{
			System.out.println("Hello data : "+e);
		}		
	}	
	public void enable()
	{
		btnUpdate.setEnabled(false);
		btnAdd.setEnabled(false);
		btnDelete.setEnabled(false);
		rbMale.setEnabled(true);
		rbFemale.setEnabled(true);
		txtEmpName.setEnabled(true);
		txtEmpName.setEditable(true);
		txtEmpId.setEditable(false);
		txtAddress.setEditable(true);
		txtMobNo.setEditable(true);
		txtEmailId.setEditable(true);
		chkMusic.setEnabled(true);
		chkSports.setEnabled(true);
		cbxQualification.setEnabled(true);
		cbxDept.setEnabled(true);
		rbEmpid.setEnabled(true);
		rbEmpName.setEnabled(true);
	}
	public void enable2()
	{
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		rbMale.setEnabled(true);
		rbFemale.setEnabled(true);
		txtEmpName.setEnabled(true);
		txtEmpId.setEnabled(true);
		txtAddress.setEditable(true);
		txtMobNo.setEditable(true);
		txtEmailId.setEditable(true);
		chkMusic.setEnabled(true);
		chkSports.setEnabled(true);
		cbxQualification.setEnabled(true);
		cbxDept.setEnabled(true);
		rbEmpid.setEnabled(true);
		rbEmpName.setEnabled(true);
	}
	public void enable3()
	{
		btnUpdate.setEnabled(false);
		btnAdd.setEnabled(false);
		rbMale.setEnabled(true);
		rbFemale.setEnabled(true);
		txtEmpName.setEnabled(true);
		txtEmpId.setEnabled(true);
		txtAddress.setEditable(true);
		txtMobNo.setEditable(true);
		txtEmailId.setEditable(true);
		chkMusic.setEnabled(true);
		chkSports.setEnabled(true);
		cbxQualification.setEnabled(true);
		cbxDept.setEnabled(true);
		rbEmpid.setEnabled(true);
		rbEmpName.setEnabled(true);
	}
	public void reset()
	{
		btnUpdate.setEnabled(false);
		btnAdd.setEnabled(false);
		btnDelete.setEnabled(false);
		btnFirst.setEnabled(true);
		btnNext.setEnabled(true);
		btnPrev.setEnabled(true);
		btnLast.setEnabled(true);
		btnEdit.setEnabled(true);
		btnClear.setEnabled(true);
		
		txtEmpName.setText("");
		txtEmpId.setText("");
	
		bg.remove(rbMale);
		bg.remove(rbFemale);
		
		rbMale.setSelected(false);
		rbFemale.setSelected(false);
		bg.add(rbMale);
		bg.add(rbFemale);
		chkMusic.setSelected(false);
		chkSports.setSelected(false);
		txtAddress.setText("");
		txtMobNo.setText("");
		txtEmailId.setText("");
		
		cbxQualification.setSelectedItem("Select Qualification");
		cbxDept.setSelectedItem("Select Department");
	}	
	public void actionPerformed(ActionEvent actEvt)
	{
		String strMobNo = txtMobNo.getText();
		int mobNoLn = strMobNo.length();
		
		if(actEvt.getSource().equals(btnExit))
		{
			System.exit(0);
		}
		else if(actEvt.getSource().equals(btnAdd))
		{
			
			int count=0;
			if(txtEmpName.getText().equals(""))
			{
				JOptionPane.showMessageDialog(frm,lblSubEmpName);
				count++;
			}
			
			else if(rbMale.isSelected()==false && rbFemale.isSelected()==false)
			{
				JOptionPane.showMessageDialog(frm,lblSubGender);
				count++;
			}
			else if(txtAddress.getText().equals(""))
			{
				JOptionPane.showMessageDialog(frm,lblSubAddress);
				count++;
			}
			else if(txtMobNo.getText().equals(""))
			{
				JOptionPane.showMessageDialog(frm,lblSubMobNo);
				count++;
			}
			else if(mobNoLn<10)
			{
				JOptionPane.showMessageDialog(frm,lblSubMobNolength);
				count++;
			}
			else if(txtEmailId.getText().equals(""))
			{
				JOptionPane.showMessageDialog(frm,lblSubEmailID);
				count++;
			}
			else if(cbxQualification.getSelectedItem().equals("Select Qualification"))
			{
				JOptionPane.showMessageDialog(frm,lblSubQaulif);
				count++;
			}
			else if(cbxDept.getSelectedItem().equals("Select Department"))
			{
				JOptionPane.showMessageDialog(frm,lblSubDept);
				count++;
			}

			try
			{
				
				r=stmt.executeQuery("select max(EID) from EmployeeTable");
				r.next();
				int eid=(r.getInt(1)+1);

				pstmtadd = con.prepareStatement("insert into EmployeeTable values(?,?,?,?,?,?,?,?,?,?)");
				String empName=txtEmpName.getText();
				String empaddress=txtAddress.getText();
				String empmobno=txtMobNo.getText();
				String empemailid=txtEmailId.getText();
				String empQuali=(String)cbxQualification.getSelectedItem();
				String empdept=(String)cbxDept.getSelectedItem();
				
				pstmtadd.setString(1,empName);
				pstmtadd.setInt(2,eid);
				pstmtadd.setString(4,empaddress);
				pstmtadd.setString(5,empmobno);
				pstmtadd.setString(6,empemailid);
				pstmtadd.setString(9,empQuali);
				pstmtadd.setString(10,empdept);

				if(rbMale.isSelected()==true)
				{
					pstmtadd.setString(3,"Male");
				}
				else 
				{
					pstmtadd.setString(3,"Female");
				}
	
				if(chkMusic.isSelected()==true)
				{
					pstmtadd.setString(7,"Music");
				}
				else
				{
					pstmtadd.setString(7,"");
				}
				if(chkSports.isSelected()==true)
				{
					pstmtadd.setString(8,"Sports");
				}
				else
				{
					pstmtadd.setString(8,"");
				}
				pstmtadd.executeUpdate();
				dbConnection();
				System.out.println("record is added");
			}
			catch(Exception e)
			{
				System.out.println("insert Query Exception : "+e);
			}
			
			if(count==0)
			{
				btnFirst.setEnabled(true);
				btnNext.setEnabled(true);
				btnPrev.setEnabled(true);
				btnLast.setEnabled(true);
				btnAdd.setEnabled(false);
				reset();
			}
			enable2();
		}
		else if(actEvt.getSource().equals(btnClear))
		{
			enable();
			reset();
		}
		else if(actEvt.getSource().equals(btnFirst))
		{
			enable();
			try
			{
				rs.first();	
				data();			
			}
			catch(Exception e)
			{
				System.out.println("First Record Exception : "+e);
			}			
		}
		else if(actEvt.getSource().equals(btnNext))
		{
			enable();
			try
			{
	
				if(rs.isLast())
				{
					JOptionPane.showMessageDialog(frm,lblLastEntry);
				}
				else
				{
					rs.next();	
					data();
				}
			}
			catch(Exception e)
			{
				System.out.println("Next Record Exception : "+e);
			}		
		}
		else if(actEvt.getSource().equals(btnPrev))
		{		
			enable();
			try
			{
				
				if(rs.isFirst())
				{
					JOptionPane.showMessageDialog(frm,lblFirstEntry);
				}
				else
				{
					rs.previous();	
					data();
				}
			}
			catch(Exception e)
			{
				System.out.println("Previous Record Exception : "+e);
			}		
		}
		else if(actEvt.getSource().equals(btnLast))
		{			
			enable();
			try
			{
				rs.last();	
				data();	
			}
			catch(Exception e)
			{
				System.out.println("Last Record Exception : "+e);
			}		
		}
		else if(actEvt.getSource().equals(btnEdit))
		{
			reset();
			enable();
			frameChoice();
			frmChoice.setLocationRelativeTo(null);
		}
		else if(actEvt.getSource().equals(btnDelete))
		{
			int count=0;
			txtEmpName.setEditable(true);
			txtEmpName.setEnabled(true);
			txtEmpId.setEditable(false);	
			try
			{
				if(rbEmpidUpdate.isSelected()==true)
				{
					
					int id =Integer.parseInt(txtEmpId.getText());
					prStmtDeleteid = con.prepareStatement("delete EmployeeTable where EID=?");
					prStmtDeleteid.setInt(1,id);
					prStmtDeleteid.executeUpdate();
					System.out.println("record is deleted");
				}
				else if(rbEmpNameUpdate.isSelected()==true)
				{
					String name = txtEmpName.getText();
					prStmtDeleteName = con.prepareStatement("delete EmployeeTable where EName=?");
					prStmtDeleteName.setString(1,name);
					prStmtDeleteName.executeUpdate();
					System.out.println("record is deleted");
				}
			}
			catch(Exception e)
			{
				System.out.println("Delete Record Exception : "+e);
			}
			
			if(count==0)
			{
				enable();
				reset();
			}
			dbConnection();
		}
		else if( actEvt.getSource().equals(rbUpdate) || actEvt.getSource().equals(rbSearch) || actEvt.getSource().equals(rbDelete) )
		{
			try
			{
				cbxEmpNameCol.setEnabled(false);
				cbxEmpIdCol2.setEnabled(false);
				
				rsEmpName = stmt.executeQuery("select EName from EmployeeTable");
				cbxEmpNameCol.removeAllItems();
				while(rsEmpName.next())
				{
					int i=0;
					strEmpName = new String[1000];
					strEmpName[i]=""+rsEmpName.getString(1);
					cbxEmpNameCol.addItem(strEmpName[i]);
					i++;
				}
				rsEmpId = stmt.executeQuery("select EID from EmployeeTable");
				cbxEmpIdCol.removeAllItems();
				while(rsEmpId.next())
				{
					int i=0;
					strEmpId = new String[1000];
					strEmpId[i]=""+rsEmpId.getInt(1);
					cbxEmpIdCol.addItem(strEmpId[i]);
					i++;
				}
			}
			catch(Exception e)
			{
				System.out.println("ComboBox Record Fatch Exception : "+e);
			}
			dbConnection();
			frameUpdate();
			frmChoice.dispose();
			frmUpdate.setLocationRelativeTo(null);
		}
		else if(actEvt.getSource().equals(rbAdd))
		{
			txtEmpId.setEditable(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPrev.setEnabled(false);
			btnLast.setEnabled(false);
			btnEdit.setEnabled(false);
			enable();
			btnAdd.setEnabled(true);
			frmChoice.dispose();
			frm.setVisible(true);
			dbConnection();
		}
		else if( actEvt.getSource().equals(btnOkUpdate))
		{
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPrev.setEnabled(false);
			btnLast.setEnabled(false);
			btnAdd.setEnabled(false);
			if(rbUpdate.isSelected()==true || rbDelete.isSelected()==true)
			{
				btnEdit.setEnabled(false);
			}
			btnDelete.setEnabled(false);
			rbMale.setEnabled(true);
			rbFemale.setEnabled(true);
			txtEmpName.setEditable(true);
			txtEmpId.setEditable(false);
			txtAddress.setEditable(true);
			txtMobNo.setEditable(true);
			txtEmailId.setEditable(true);
			chkMusic.setEnabled(true);
			chkSports.setEnabled(true);
			cbxQualification.setEnabled(true);
			cbxDept.setEnabled(true);
			rbEmpidUpdate.setEnabled(true);
			cbxEmpIdCol.setEnabled(true);
			rbEmpNameUpdate.setEnabled(true);
			cbxEmpNameCol.setEnabled(true);
			bgUpdate.remove(rbEmpNameUpdate);
			bgUpdate.remove(rbEmpidUpdate);
			bgUpdate.add(rbEmpNameUpdate);
			bgUpdate.add(rbEmpidUpdate);
			cbxEmpNameCol.setEnabled(false);
			cbxEmpIdCol.setEnabled(false);
			
			if(rbDelete.isSelected()==true)
			{
				btnFirst.setEnabled(false);
				btnNext.setEnabled(false);
				btnPrev.setEnabled(false);
				btnLast.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(true);
				rbMale.setEnabled(false);
				rbFemale.setEnabled(false);
				txtAddress.setEditable(false);
				txtMobNo.setEditable(false);
				txtEmailId.setEditable(false);
				chkMusic.setEnabled(false);
				chkSports.setEnabled(false);
				cbxQualification.setEnabled(false);
				cbxDept.setEnabled(false);
				txtEmpName.setEditable(false);
				txtEmpId.setEditable(false);
				btnDelete.setEnabled(true);
				if(rbEmpNameUpdate.isSelected()==true)
				{
					try
					{
						String ceName=(String)cbxEmpNameCol.getSelectedItem();
						prENSelect  = con.prepareStatement("select EName from EmployeeTable where EName=?");
						prENSelect.setString(1,ceName);
						rsENUpdate = prENSelect.executeQuery();
						rsENUpdate.next();

						chkMusic.setSelected(false);
						chkSports.setSelected(false);
						
						txtEmpName.setText(""+rsENUpdate.getString(1));
					}
					catch(Exception e)
					{
						System.out.println("Set Emp Name Exception : "+e);
					}
				}
				if(rbEmpidUpdate.isSelected()==true)
				{
					try
					{
						String str=(String)cbxEmpIdCol.getSelectedItem();
						int ceid=Integer.parseInt(str);
						prEISelect  = con.prepareStatement("select EID from EmployeeTable where EID=?");
						prEISelect.setInt(1,ceid);
						rsEIUpdate = prEISelect.executeQuery();
						rsEIUpdate.next();
	
						chkMusic.setSelected(false);
						chkSports.setSelected(false);
						
						txtEmpId.setText(""+rsEIUpdate.getInt(1));
					}
					catch(Exception e)
					{
						System.out.println("Set Emp ID Exception : "+e);
					}
				}
				
			}
			if(rbUpdate.isSelected()==true || rbSearch.isSelected()==true)
			{
				if(rbEmpNameUpdate.isSelected()==true)
				{
					try
					{
						String ceName=(String)cbxEmpNameCol.getSelectedItem();
						prENSelect  = con.prepareStatement("select * from EmployeeTable where EName=?");
						prENSelect.setString(1,ceName);
						rsENUpdate = prENSelect.executeQuery();
						rsENUpdate.next();

						chkMusic.setSelected(false);
						chkSports.setSelected(false);
						
						txtEmpName.setText(""+rsENUpdate.getString(1));
						txtEmpId.setText(""+rsENUpdate.getInt(2));
						txtAddress.setText(""+rsENUpdate.getString(4));
						txtMobNo.setText(""+rsENUpdate.getString(5));
						txtEmailId.setText(""+rsENUpdate.getString(6));
						cbxQualification.setSelectedItem(""+rsENUpdate.getString(9));
						cbxDept.setSelectedItem(""+rsENUpdate.getString(10));
						if(rsENUpdate.getString(3).equals("Male"))
						{
							rbMale.setSelected(true);
						}
						else
						{
							rbFemale.setSelected(true);
						}

						if(rsENUpdate.getString(7).equals("Music"))
						{
							chkMusic.setSelected(true);
						}
						if(rsENUpdate.getString(8).equals("Sports"))
						{
							chkSports.setSelected(true);
						}
					}
					catch(Exception e)
					{
						System.out.println("Emp Name Fatch Record Exception : "+e);
					}
				}
				if(rbEmpidUpdate.isSelected()==true)
				{
					try
					{
						String str=(String)cbxEmpIdCol.getSelectedItem();
						int ceid=Integer.parseInt(str);
						prEISelect  = con.prepareStatement("select * from EmployeeTable where EID=?");
						prEISelect.setInt(1,ceid);
						rsEIUpdate = prEISelect.executeQuery();
						rsEIUpdate.next();

						chkMusic.setSelected(false);
						chkSports.setSelected(false);
						
						txtEmpName.setText(""+rsEIUpdate.getString(1));
						txtEmpId.setText(""+rsEIUpdate.getInt(2));
						txtAddress.setText(""+rsEIUpdate.getString(4));
						txtMobNo.setText(""+rsEIUpdate.getString(5));
						txtEmailId.setText(""+rsEIUpdate.getString(6));
						cbxQualification.setSelectedItem(""+rsEIUpdate.getString(9));
						cbxDept.setSelectedItem(""+rsEIUpdate.getString(10));
						if(rsEIUpdate.getString(3).equals("Male"))
						{
							rbMale.setSelected(true);
						}
						else
						{
							rbFemale.setSelected(true);
						}

						if(rsEIUpdate.getString(7).equals("Music"))
						{
							chkMusic.setSelected(true);
						}
						if(rsEIUpdate.getString(8).equals("Sports"))
						{
							chkSports.setSelected(true);
						}
					}
					catch(Exception e)
					{
						System.out.println("Emp ID Fatch record Exception : "+e);
					}
				}
				if(rbSearch.isSelected()==true)
				{
					btnFirst.setEnabled(true);
					btnNext.setEnabled(true);
					btnPrev.setEnabled(true);
					btnLast.setEnabled(true);
				}
				if(rbUpdate.isSelected()==true)
				{
					btnUpdate.setEnabled(true);
				}
			}
			dbConnection();
			frmUpdate.dispose();
			frm.setVisible(true);
		}
		else if(actEvt.getSource().equals(btnUpdate))
		{
			enable();
			try
			{
				prEIByUpdate = con.prepareStatement("update EmployeeTable set EName=?,EGender=?,EAddress=?,EMobNo=?,EEmailId=?,EHMusic=?,EHSports=?,EQuali=?,EDept=? where EID=?");
				
				int empid=Integer.parseInt(txtEmpId.getText());
				String empName=txtEmpName.getText();
				String empaddress=txtAddress.getText();
				String empmobno=txtMobNo.getText();
				String empemailid=txtEmailId.getText();
				String empQuali=(String)cbxQualification.getSelectedItem();
				String empdept=(String)cbxDept.getSelectedItem();
				
				prEIByUpdate.setString(1,empName);
				prEIByUpdate.setString(3,empaddress);
				prEIByUpdate.setString(4,empmobno);
				prEIByUpdate.setString(5,empemailid);
				prEIByUpdate.setString(8,empQuali);
				prEIByUpdate.setString(9,empdept);
				prEIByUpdate.setInt(10,empid);
				if(rbMale.isSelected()==true)
				{
					prEIByUpdate.setString(2,"Male");
				}
				else if(rbFemale.isSelected()==true)
				{
					pstmtadd.setString(2,"Female");
				}
	
				if(chkMusic.isSelected()==true)
				{
					prEIByUpdate.setString(6,"Music");
				}
				else
				{
					prEIByUpdate.setString(6,"");
				}
				if(chkSports.isSelected()==true)
				{
					prEIByUpdate.setString(7,"Sports");
				}
				else
				{
					prEIByUpdate.setString(7,"");
				}
				
				prEIByUpdate.executeUpdate();
				System.out.println("record is Updated");
			}
			catch(Exception e)
			{
				System.out.println("Update  Exception : : "+e);
			}	
			btnFirst.setEnabled(true);
			btnNext.setEnabled(true);
			btnPrev.setEnabled(true);
			btnLast.setEnabled(true);
			btnUpdate.setEnabled(false);
			reset();
			dbConnection();
		}
		else if(actEvt.getSource().equals(rbEmpNameUpdate))  
		{
			cbxEmpNameCol.setEnabled(true);
			cbxEmpIdCol.setEnabled(false);
		}
		else if(actEvt.getSource().equals(rbEmpidUpdate))
		{
			cbxEmpIdCol.setEnabled(true);
			cbxEmpNameCol.setEnabled(false);
		}
	}
	public void keyPressed(KeyEvent KeyEvt)
	{}
	public void keyReleased(KeyEvent keyEvt)
	{}
	public void keyTyped(KeyEvent keyEvt)
	{
		String strMobLn = txtMobNo.getText();
		int mobLenght =strMobLn.length();
		
		if(keyEvt.getSource().equals(txtEmpName))
		{
			if(keyEvt.getKeyChar()>='0' && keyEvt.getKeyChar()<='9')
			{
				JOptionPane.showMessageDialog(frm,lblKeyEmpName);
				keyEvt.consume();
			}
		}
		else if(keyEvt.getSource().equals(txtMobNo))
		{
			if((keyEvt.getKeyChar()>='0' && keyEvt.getKeyChar()<='9') || (keyEvt.getKeyChar()==8))
			{}
			else
			{
				JOptionPane.showMessageDialog(frm,lblKeyMobNo);
				keyEvt.consume();
			}
			if(mobLenght>=10)
			{
				JOptionPane.showMessageDialog(frm,lblKeyMobLn);
				keyEvt.consume();
			}
		}
	}
	
	public static void main(String[] a)
	{
		EmployeeForm ef = new EmployeeForm();
		ef.create();
		ef.addComp();
		ef.display();
		ef.register();
		ef.dbConnection();
		ef.data();
	}
}
