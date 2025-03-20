// Edited by Sarina Iqbal

package classes;
public class Assignment {
	private String name;
	private double pointsPossible;
	private double pointsEarned = 0.0;
	private boolean graded = false;
	
	
	
	/* This is the constructor
	 * @param name which is the name of the Assignment
	 * 		  pointsPossible which is the points possible on an Assignment
	 * 
	*/
	public Assignment(String name, double pointsPossible) {
		this.name = name;
		this.pointsPossible = pointsPossible;
	}
	
	//getters and setters
	
	/* 
	 * @return name of Assignment
	*/
	
	public String getName() {
		return this.name;
	}
	
	
	public double getPointsPossible() {
		return this.pointsPossible;
	}
	
	
	public double getPointsEarned() {
		return this.pointsEarned;
	}
	
	public boolean getHasBeenGraded() {
		return this.graded;
	}
	
	
	/* 
	 * @return percentage grade based on points earned 
	 * 		   on an Assignment and total points available
	*/
	public double getPercentage() {
		if(this.getHasBeenGraded()) {
			return (this.getPointsEarned()/this.getPointsPossible())*100.0;
		}else {
			return 0;
		}
		
	}
	
	
	/* 
	 * @param pointsEarned which is the points earned on an Assignment
	 * 
	*/
	public void setPointsEarned(double pointsEarned) throws NotAValidCompareOptionException {
		if(pointsEarned > pointsPossible)
			
			throw new NotAValidCompareOptionException();
		this.pointsEarned = pointsEarned;
	
		this.graded = true;
	}

}
