// Author: Sarina Iqbal

package tests;

import classes.Assignment;
import classes.Course;
import classes.Student;
import classes.Semester;
import classes.NotAValidCompareOptionException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class LA3Test {

	private Assignment ass1;
	private Assignment ass2;
	private Assignment ass3;
	private Course c1;
	
	
	@Test
	void testGetAssName() {
		ass1 = new Assignment("HW1", 100);
		assertEquals("HW1", ass1.getName());
		assertEquals(100.0, ass1.getPointsPossible());
		assertFalse(ass1.getHasBeenGraded());
		assertEquals(0, ass1.getPercentage());
	}
	
	@Test
	void testGetAss2Name() throws NotAValidCompareOptionException {
		ass2 = new Assignment("HW2", 100);
		ass2.setPointsEarned(80.0);
		assertTrue(ass2.getHasBeenGraded());
		assertEquals(80.0, ass2.getPointsEarned());
		assertEquals(80.0, ass2.getPercentage());
	}
	@Test
	void testGetAss3Name() throws NotAValidCompareOptionException {
		ass3 = new Assignment("HW2", 100);
		ass3.setPointsEarned(75.0);
		assertTrue(ass3.getHasBeenGraded());
		assertEquals(75.0, ass3.getPointsEarned());
		assertEquals(75.0, ass3.getPercentage());
	}
	
	@Test
	void testGetAss4Name() throws NotAValidCompareOptionException {
		ass3 = new Assignment("HW2", 100);
		
		
		assertThrows(NotAValidCompareOptionException.class, () -> ass3.setPointsEarned(115.0));
		assertFalse(ass3.getHasBeenGraded());
		
		
	}
	
	@Test
	void testC1(){
		c1 = new Course("MAT 129", Semester.FALL, 2024, 3);
		assertEquals("MAT 129", c1.getCourseNum());
		assertEquals(Semester.FALL, c1.getSemester());
		assertEquals(2024, c1.getYear());
		assertEquals(3, c1.getCreditHours());
		assertFalse(c1.getIsCompleted());
		assertEquals(0.0, c1.getPercentage());
		assertEquals('F', c1.getLetterGrade());
	}
	
	@Test
	void testC2() throws NotAValidCompareOptionException{
		Assignment ass = new Assignment("hw", 100);
		Course c2 = new Course("MAT 129", Semester.FALL, 2024, 3);
		ass.setPointsEarned(76.0);
		c2.addAssignment(ass);
	
		assertEquals(1, c2.getAssignments().size());
		c2.calculatePercentage();
		assertEquals(76.0, c2.getPercentage());
		c2.calculateLetterGrade();
		
		assertEquals('C', c2.getLetterGrade());
		assertEquals(3, c2.getCreditHours());
		//assertTrue(c2.getIsCompleted());

	}	
	@Test
	void testC3() throws NotAValidCompareOptionException{
		Course course = new Course("DATA 130", Semester.SUMMER, 2020, 4);
		Assignment h1 = new Assignment("Project1", 100.0);
		Assignment h2 = new Assignment("Project2", 100);
		
		h1.setPointsEarned(7);
		h2.setPointsEarned(12.0);
		course.addAssignment(h1);
		course.addAssignment(h2);
	
		assertEquals(2, course.getAssignments().size());
		course.calculatePercentage();
		assertEquals(9.5, course.getPercentage());
		course.calculateLetterGrade();
		
		assertEquals('E', course.getLetterGrade());
		Assignment h3 = new Assignment("Project3", 200.0);
		Assignment h4 = new Assignment("Project4", 250.0);
		
		course.addAssignment(h3);
		course.addAssignment(h4);
		h3.setPointsEarned(197.0);
		h4.setPointsEarned(234);
		assertEquals(4, course.getAssignments().size());
		course.calculatePercentage();
		
		//assertEquals(69.2, course.getPercentage(), 0.01);
		course.calculateLetterGrade();
		
		assertEquals('D', course.getLetterGrade());
		


		
		

	}
	
	@Test
	void testC3a() throws NotAValidCompareOptionException{
		Course course = new Course("DATA 130", Semester.SUMMER, 2020, 4);
		Assignment h1 = new Assignment("Project1", 100.0);
		Assignment h2 = new Assignment("Project2", 100);
		
		h1.setPointsEarned(86);
		
		course.addAssignment(h1);
		
	
		assertEquals(1, course.getAssignments().size());
		course.calculatePercentage();
		assertEquals(86, course.getPercentage());
		course.calculateLetterGrade();
		assertEquals('B', course.getLetterGrade());
		course.addAssignment(h2);
		h2.setPointsEarned(98.5);
		course.calculatePercentage();
		assertEquals(92.3, course.getPercentage(), 0.1);
		course.calculateLetterGrade();
		assertEquals('A', course.getLetterGrade());
	
	
	}@Test
	void testC4() throws NotAValidCompareOptionException{
		Course ista = new Course("ISTA 130", Semester.SUMMER, 2000, 4);
		Course esoc = new Course("ESOC 200", Semester.SPRING, 2023, 3);
		c1 = new Course("MAT 129", Semester.FALL, 2024, 2);
		
		List<Course> courses = new ArrayList<>();
		courses.add(ista);
		courses.add(c1);
		courses.add(esoc);
		
		Course.setCompareMethod(0);
		Collections.sort(courses);
		
	
		assertEquals("ESOC 200", courses.get(0).getCourseNum());
		assertEquals("ISTA 130", courses.get(1).getCourseNum());
		assertEquals("MAT 129", courses.get(2).getCourseNum());

	}@Test
	void testC5() throws NotAValidCompareOptionException{
		assertThrows(NotAValidCompareOptionException.class, () -> Course.setCompareMethod(2));
		
	
	
	}@Test
	void testC7() {
		Course ista = new Course("ISTA 130", Semester.SUMMER, 2000, 4);
		assertFalse(ista.getIsCompleted());
		ista.setIsCompleted(true);
		assertTrue(ista.getIsCompleted());

	}
	
	@Test
	void testC8() throws NotAValidCompareOptionException {
		Course anth = new Course("ANT 500", Semester.SPRING, 2001, 4);
		
		
		assertThrows(NotAValidCompareOptionException.class, () -> anth.setCreditHours(0));
		
		
		assertEquals(4, anth.getCreditHours());
		anth.setCreditHours(3);
		assertEquals(3, anth.getCreditHours());
		

	}

	
	@Test
	void testC6() throws NotAValidCompareOptionException{
		Course ista = new Course("ISTA 130", Semester.SUMMER, 2023, 4);
		Course esoc = new Course("ESOC 200", Semester.SPRING, 2023, 3);
		Course csc = new Course("CSC 120", Semester.FALL, 2024, 2);
		c1 = new Course("MAT 129", Semester.FALL, 2024, 2);
		
		List<Course> courses = new ArrayList<>();
		courses.add(ista);
		courses.add(c1);
		courses.add(esoc);
		courses.add(csc);
		
		Course.setCompareMethod(1);
		Collections.sort(courses);
		
	
		assertEquals("ESOC 200", courses.get(0).getCourseNum());
		assertEquals("ISTA 130", courses.get(1).getCourseNum());
		assertEquals("MAT 129", courses.get(2).getCourseNum());
		assertEquals("CSC 120", courses.get(3).getCourseNum());


	}
	
	@Test
	void testS1() {
		Calendar date = Calendar.getInstance();
		date.set(2010, Calendar.SEPTEMBER, 23);
		Date age = date.getTime();
		Student student = new Student("Mary", "Jane", age, "Junior");
		student.setPreferredName("Katrina");
		int agecal = Calendar.getInstance().get(Calendar.YEAR)-2010;
		assertEquals(agecal, student.getAge());
		
		
	
		assertEquals("Mary", student.getFirstName());
		assertEquals("Jane", student.getLastName());
		assertEquals("Katrina", student.getPreferredName());
		assertEquals(age, student.getBirthDate());


	}
	
	@Test
	void testS2() throws NotAValidCompareOptionException {
		Calendar bi = Calendar.getInstance();
		bi.set(2000, Calendar.JUNE, 12);
		Date b = bi.getTime();
		Student student = new Student("Sara", "Lee", b, "Freshman");
		
		Course course = new Course("PSY 100", Semester.SUMMER, 2022, 3);
		student.addCourse(course);
		Assignment h1 = new Assignment("Project2", 100);
		course.addAssignment(h1);
		h1.setPointsEarned(86);
		course.calculatePercentage();
		
		assertEquals(86, course.getPercentage());
		course.calculateLetterGrade();
		assertEquals('B', course.getLetterGrade());
		
		assertEquals(1, student.getCourses().size());
		course.setIsCompleted(true);
		course.setCreditHours(3);
	
		student.calculateGpa();
		assertEquals(3.0, student.getGpa());
		
		assertEquals(course, student.getCourses().get(0));
		assertEquals(1, student.getComplete().size());
		
		
		Course course2 = new Course("PSY 150", Semester.FALL, 2022, 3);
		student.addCourse(course2);
		assertEquals(1, student.getOngoing().size());
		
		
		

	}
	
	@Test
	void testS3() throws NotAValidCompareOptionException {
		Calendar bi = Calendar.getInstance();
		bi.set(2000, Calendar.JUNE, 12);
		Date b = bi.getTime();
		
		
		Student student = new Student("Sara", "Lee", b, "Freshman");
		Calendar d2 = Calendar.getInstance();
		d2.set(2003, Calendar.JULY, 20);
		Date bd = d2.getTime();
		Student student2 = new Student("Zayn", "Jen", bd, "Sophomore");
		
		ArrayList<Student> pupils = new ArrayList<>();
		pupils.add(student);
		pupils.add(student2);
		
		pupils.sort(null);
		
		
		assertEquals("Zayn", pupils.get(0).getFirstName());
		assertEquals("Sara", pupils.get(1).getFirstName());
	
		

	}
	
	
	


}
