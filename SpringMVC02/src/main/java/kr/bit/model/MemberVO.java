package kr.bit.model;

//회원관리페이지에서 회원객체(object) 
public class MemberVO {
	private int num;
	private String id;
	private String pass;
	private String name;
	private int age;
	private String email;
	private String phone;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	
	//num은 db에서 outoIncrement로써 회원가입 시 직접입력하는 변수가 아님. -> 생성자에서 제외함. 
	public MemberVO(String id, String pass, String name, int age, String email, String phone) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = phone;
	}
	
	//db에 존재하는 데이터와 맵핑하기 위함 -> num까지 포함.
	public MemberVO(int num, String id, String pass, String name, int age, String email, String phone) {
		super();
		this.num = num;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = phone;
	}

	//private인 변수의 데이터를 넣고 빼기 위함. 
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", id=" + id + ", pass=" + pass + ", name=" + name + ", age=" + age + ", email="
				+ email + ", phone=" + phone + "]";
	}
	
	
}
