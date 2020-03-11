package pojo;

import util.EmpCount;

public class Emp {
    private long id;

    private String no;

    private String pass;

    private String name;

    private long did;

    private long flag;

    private String sex;

    private String email;

    private String qq;

    private String phone;

    private String createdate;

    private String photo;

    private long del;
    
    private Depart depart;
    private EmpCount empCount;

	public EmpCount getEmpCount() {
		return empCount;
	}

	public void setEmpCount(EmpCount empCount) {
		this.empCount = empCount;
	}

	public long getId() {
		return id;
	}

	public String getNo() {
		return no;
	}

	public String getPass() {
		return pass;
	}

	public String getName() {
		return name;
	}

	public long getDid() {
		return did;
	}

	public long getFlag() {
		return flag;
	}

	public String getSex() {
		return sex;
	}

	public String getEmail() {
		return email;
	}

	public String getQq() {
		return qq;
	}

	public String getPhone() {
		return phone;
	}

	public String getCreatedate() {
		return createdate;
	}

	public String getPhoto() {
		return photo;
	}

	public long getDel() {
		return del;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDid(long did) {
		this.did = did;
	}

	public void setFlag(long flag) {
		this.flag = flag;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setDel(long del) {
		this.del = del;
	}

	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}

    
}