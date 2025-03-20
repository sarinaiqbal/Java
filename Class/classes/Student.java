// Edited by Sarina Iqbal

package classes;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class Student implements Comparable<Student> {
	private String firstName;
	private String lastName;
	private String preferredName;
	private int age;
	private Date birthDate;
	private String classStanding;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private double gpa;
	
	//constructor
	public Student(String firstName, String lastName, Date birthDate, String standing) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.classStanding = standing;
	}
	
	//getters and setters
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getPreferredName() {
		return this.preferredName;
	}
	
	public int getAge() {
		//return this.age;
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthDate);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		return age;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public double getGpa() {
		return this.gpa;
	}
	


	public ArrayList<Course> getCourses() {
		return new ArrayList<> (courses);
		
		
	}


	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}

	
	//other methods
	public void calculateGpa() {
		int totalCredits = 0;
		double totalPoints = 0.0;
		for(Course course : courses) {
			
			if(course.getIsCompleted()) {
				totalCredits += course.getCreditHours();
				
				char grade = course.getLetterGrade();
				if(grade == 'A') {
					totalPoints += course.getCreditHours()*4.0;
					
				} else if(grade == 'B') {
					totalPoints += course.getCreditHours()*3.0;
					
				} else if(grade == 'C') {
					totalPoints += course.getCreditHours()*2.0;
					
				} else if(grade == 'D') {
					totalPoints += course.getCreditHours()*1.0;
					
				} else {
					totalPoints += course.getCreditHours()*0.0;
				}
					
				
			}
			
		}if(totalCredits>0) {
			this.gpa = totalPoints/totalCredits;
		}else {
			this.gpa = 0.0;
		}
	}
	
	public int compareTo(Student other) {
		int comp = this.age - other.getAge();
		if(comp == 0)
			comp = this.firstName.compareTo(other.firstName);
		if(comp == 0)
			comp = this.lastName.compareTo(other.lastName);
		return comp;
	}
	
	
	public  ArrayList<Course> getComplete() {
		ArrayList<Course> complete = new ArrayList<>(); 
		for(Course c : courses) {
			if(c.getIsCompleted()) {
				complete.add(c);
				
			}
		}
		
		return complete;
	}
	
	public  ArrayList<Course> getOngoing() {
		ArrayList<Course> ongoing = new ArrayList<>(); 
		for(Course c : courses) {
			if(!c.getIsCompleted()) {
				ongoing.add(c);
			}
		}
		
		return ongoing;
	}
	
	public void addCourse(Course c) {
		this.courses.add(c);
	}
}
