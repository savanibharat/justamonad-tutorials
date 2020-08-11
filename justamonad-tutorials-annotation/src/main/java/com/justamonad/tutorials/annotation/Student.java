package com.justamonad.tutorials.annotation;

public class Student {

	@JsonField("first_name")
	private String firstName;

	@JsonField("last_name")
	private String lastName;

	@JsonField("middle_name")
	private String middleName;

	@JsonField("student_id")
	private int studentId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Student withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Student withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Student withStudentId(int studentId) {
		this.studentId = studentId;
		return this;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Student withMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}

}