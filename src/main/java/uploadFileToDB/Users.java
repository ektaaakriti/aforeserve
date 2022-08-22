package uploadFileToDB;

public class Users {
	private String First_Name;
	private String Last_Name;
	private String AD_User_login_ID;
	private String Password_enc;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String ip;
	private String start_date;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	private String Mobile;
	private String Email_ID;
	private String Password;
	private String Department;
	private String Location;
	private String Manager_Name;
	private String Manager_User_ID;
	private String Emp_Code;
	private String username;
	private String admin_panel_enable;
	private String user_group_id;
	private String user_id;
	
	public Users(String first_Name, String last_Name, String aD_User_login_ID, String password_enc, String mobile,
			String email_ID, String password, String department, String location, String manager_Name,
			String manager_User_ID, String emp_Code, String username, String admin_panel_enable, String user_group_id,
			String user_id) {
		super();
		First_Name = first_Name;
		Last_Name = last_Name;
		AD_User_login_ID = aD_User_login_ID;
		Password_enc = password_enc;
		Mobile = mobile;
		Email_ID = email_ID;
		Password = password;
		Department = department;
		Location = location;
		Manager_Name = manager_Name;
		Manager_User_ID = manager_User_ID;
		Emp_Code = emp_Code;
		this.username = username;
		this.admin_panel_enable = admin_panel_enable;
		this.user_group_id = user_group_id;
		this.user_id = user_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getAD_User_login_ID() {
		return AD_User_login_ID;
	}
	public void setAD_User_login_ID(String aD_User_login_ID) {
		AD_User_login_ID = aD_User_login_ID;
	}
	public String getPassword_enc() {
		return Password_enc;
	}
	public void setPassword_enc(String password_enc) {
		Password_enc = password_enc;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getEmail_ID() {
		return Email_ID;
	}
	public void setEmail_ID(String email_ID) {
		Email_ID = email_ID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getManager_Name() {
		return Manager_Name;
	}
	public void setManager_Name(String manager_Name) {
		Manager_Name = manager_Name;
	}
	public String getManager_User_ID() {
		return Manager_User_ID;
	}
	public void setManager_User_ID(String manager_User_ID) {
		Manager_User_ID = manager_User_ID;
	}
	public String getEmp_Code() {
		return Emp_Code;
	}
	public void setEmp_Code(String emp_Code) {
		Emp_Code = emp_Code;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAdmin_panel_enable() {
		return admin_panel_enable;
	}
	public void setAdmin_panel_enable(String admin_panel_enable) {
		this.admin_panel_enable = admin_panel_enable;
	}
	public String getUser_group_id() {
		return user_group_id;
	}
	public void setUser_group_id(String user_group_id) {
		this.user_group_id = user_group_id;
	}
	

}
