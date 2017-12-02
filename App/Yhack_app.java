import java.util.ArrayList;
import java.util;

public class User{
	
	//constructor
	public User(){
		name_ = "";
		dob_ = "";
		addr_ = "";
		city_ = "";
		sex_ = "";
		state_ = "";
		employ_ = "";
		income_ = "";
		preconditions = new ArrayList<String>();
		height_ = "";
		weight_ = "";
		tobacco_ = "No";
	}
	
	
	//setters
	public void setName(String name){
		name_ = name;
	}
	public void setDob(String dob){
		dob_ = dob;
	}
	public void setAddr(String addr){
		addr_ = addr;
	}
	public void setCity(String city){
		city_ = city;
	}
	public void setSex(String sex){
		sex_ = sex;
	}
	public void setState(String state){
		state_ = state;
	}
	public void setEmploy(String employ){
		employ_ = employ;
	}
	public void setIncome(String income){
		income_ = income;
	}
	public void setPreconditions(String preconditions){
		preconditions.add(1,preconditions);
	}
	public void setHeight(String height){
		height_ = height;
	}
	public void setWeight(String weight){
		weight_ = weight;
	}
	public void setTobacco(String tobacco){
		tobacco_ = tobacco;
	}
	
	//attribut
	private String name_;
	private String dob_;
	private String addr_;
	private String city_;
	private String sex_;
	private String state_;
	private String employ_;
	private String income_;
	private ArrayList<String> preconditions;
	private String height_;
	private String weight_;
	private String tobacco_;
	
}