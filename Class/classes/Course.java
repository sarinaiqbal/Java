// Edited by Sarina Iqbal

package classes;
import java.util.ArrayList;
import java.util.Comparator;

public class Course implements Comparable<Course> {
	private static int compareMethod;
	private String courseNum;
	private ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	private Semester sem;
	private int year;
	private int creditHours;
	private boolean isCompleted = false;
	private double percentage = 0.0;
	private char letterGrade='F';
	
	//constructor
	
	/* This is the contructor
	 * @param a which is the Assignment to be added
	 * 
	*/
	public Course (String courseNum, Semester sem, int year, int credits) {
		this.courseNum = courseNum;
		this.sem = sem;
		this.year = year;
		this.creditHours = credits;
	}

	//getters and setters
	public String getCourseNum() {
		return this.courseNum;
	}
	

	public ArrayList<Assignment> getAssignments() {
		return this.assignments;
	}
	
	
	public Semester getSemester() {
		return sem;
	}
	
	
	public int getYear() {
		return this.year;
	}
	
	
	public int getCreditHours() {
		return this.creditHours;
	}
	
	public void setCreditHours(int credits) throws NotAValidCompareOptionException {
		if (credits > 0) {
			this.creditHours = credits;
		}else {
			throw new NotAValidCompareOptionException();
		}
		
	}
	
	public boolean getIsCompleted() {
		return this.isCompleted;
	}
	
	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public double getPercentage() {
		return this.percentage;
	}
	
	
	public char getLetterGrade() {
		return this.letterGrade;
	}

	
	//other methods
	
	public void calculatePercentage() {
		double pointsPossible = 0.0;
		double pointsEarned = 0.0;
		for(Assignment assignment : assignments) {
			if(assignment.getHasBeenGraded()) {
				pointsPossible += assignment.getPointsPossible();
				pointsEarned += assignment.getPointsEarned();
			}
		}
		this.percentage = pointsEarned/pointsPossible * 100.0;
	}
	
	public void calculateLetterGrade() {
		this.calculatePercentage();
		if(percentage <= 60.0)
			this.letterGrade = 'E';
		else if(percentage <= 70.0)
			this.letterGrade = 'D';
		else if(percentage <= 80.0)
			this.letterGrade = 'C';
		else if(percentage <= 90.0)
			this.letterGrade = 'B';
		else
			this.letterGrade = 'A';
	}
	
	
	public static void setCompareMethod(int compMethod) throws NotAValidCompareOptionException {
		if(compMethod != 0 && compMethod != 1) {
			throw new NotAValidCompareOptionException();
		}
		Course.compareMethod = compMethod;
	}
	
	public int compareTo(Course other) {
		// Alphabetical sorting
		if(compareMethod == 0) {
			return this.courseNum.compareTo(other.courseNum);
			
		// Chronological sorting
		} else if(compareMethod == 1) {

			int ret = Integer.compare(this.year, other.year);
			
			if(ret == 0){
				
				return this.sem.compareTo(other.sem);
			}else{
				return ret;
			}
		}
		return 0;
	}
	
	
	/* 
	 * @param a which is the Assignment to be added
	 * 
	*/

	public void addAssignment(Assignment a) {
		this.assignments.add(a);
		calculatePercentage();
		calculateLetterGrade();
	}
}
