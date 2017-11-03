package com.velocity.entity;

import org.nutz.dao.entity.annotation.*;

@Table("local_file")
public class FilePath {
	@Name
	@Column("LOCAL_FILE_CODE")
	private String local_file_code;

	@Column("leval1")
	private String leval1;

	@Column("leval2")
	private String leval2;

	@Column("leval3")
	private String leval3;

	@Column("leval4")
	private String leval4;

	@Column("leval5")
	private String leval5;

	@Column("leval6")
	private String leval6;

	@Column("filepath")
	private String filepath;

	@Column("creattime")
	private String creattime;

	public String getLocal_file_code() {
		return local_file_code;
	}

	public void setLocal_file_code(String local_file_code) {
		this.local_file_code = local_file_code;
	}

	public String getLeval1() {
		return leval1;
	}

	public void setLeval1(String leval1) {
		this.leval1 = leval1;
	}

	public String getLeval2() {
		return leval2;
	}

	public void setLeval2(String leval2) {
		this.leval2 = leval2;
	}

	public String getLeval3() {
		return leval3;
	}

	public void setLeval3(String leval3) {
		this.leval3 = leval3;
	}

	public String getLeval4() {
		return leval4;
	}

	public void setLeval4(String leval4) {
		this.leval4 = leval4;
	}

	public String getLeval5() {
		return leval5;
	}

	public void setLeval5(String leval5) {
		this.leval5 = leval5;
	}

	public String getLeval6() {
		return leval6;
	}

	public void setLeval6(String leval6) {
		this.leval6 = leval6;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

}
