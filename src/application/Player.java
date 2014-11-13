package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Player {
	private String firstName;
	private String lastName;
	private String playerID;
	private String teamID;
	private String nationality;
	private float attacking;
	private float defending;
	private float goalKeeping;
	private float shortPassing;
	private float longPassing;
	private float crossing;
	private float speed;
	private float stamina;
	private float tackling;
	private float power;
	private float accuracy;
	private float luck;
	private String userID;
	private String position;
	private int jerseyNo;
	private Date DOB;
	

	//--------------->>> Getters and Setters Starts Here
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPlayerID() {
		return playerID;
	}
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	public String getTeamID() {
		return teamID;
	}
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public float getAttacking() {
		return attacking;
	}
	public void setAttacking(float attacking) {
		this.attacking = attacking;
	}
	public float getDefending() {
		return defending;
	}
	public void setDefending(float defending) {
		this.defending = defending;
	}
	public float getGoalKeeping() {
		return goalKeeping;
	}
	public void setGoalKeeping(float goalKeeping) {
		this.goalKeeping = goalKeeping;
	}
	public float getShortPassing() {
		return shortPassing;
	}
	public void setShortPassing(float shortPassing) {
		this.shortPassing = shortPassing;
	}
	public float getLongPassing() {
		return longPassing;
	}
	public void setLongPassing(float longPassing) {
		this.longPassing = longPassing;
	}
	public float getCrossing() {
		return crossing;
	}
	public void setCrossing(float crossing) {
		this.crossing = crossing;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getStamina() {
		return stamina;
	}
	public void setStamina(float stamina) {
		this.stamina = stamina;
	}
	public float getTackling() {
		return tackling;
	}
	public void setTackling(float tackling) {
		this.tackling = tackling;
	}
	public float getPower() {
		return power;
	}
	public void setPower(float power) {
		this.power = power;
	}
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	public float getLuck() {
		return luck;
	}
	public void setLuck(float luck) {
		this.luck = luck;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getJerseyNo() {
		return jerseyNo;
	}
	public void setJerseyNo(int jerseyNo) {
		this.jerseyNo = jerseyNo;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	
	
	//--------------->>> Getters and Setters Ends Here


	
	//--------------->>> Constructors
	public Player(String firstName, String lastName, String playerID,
			String teamID, String nationality, float attacking,
			float defending, float goalKeeping, float shortPassing,
			float longPassing, float crossing, float speed, float stamina,
			float tackling, float power, float accuracy, float luck,
			String userID, String position, int jerseyNo, Date dOB) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.playerID = playerID;
		this.teamID = teamID;
		this.nationality = nationality;
		this.attacking = attacking;
		this.defending = defending;
		this.goalKeeping = goalKeeping;
		this.shortPassing = shortPassing;
		this.longPassing = longPassing;
		this.crossing = crossing;
		this.speed = speed;
		this.stamina = stamina;
		this.tackling = tackling;
		this.power = power;
		this.accuracy = accuracy;
		this.luck = luck;
		this.userID = userID;
		this.position = position;
		this.jerseyNo = jerseyNo;
		DOB = dOB;
	}
	
	
	
	public Player(String firstName, String lastName, String playerID,
			String userID, int jerseyNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.playerID = playerID;
		this.userID = userID;
		this.jerseyNo = jerseyNo;
		// set rest of the attributes
	}
	
	
	
	
	@Override
	public String toString() {
		return "Player [firstName=" + firstName + ", lastName=" + lastName
				+ ", playerID=" + playerID + ", teamID=" + teamID
				+ ", nationality=" + nationality + ", attacking=" + attacking
				+ ", defending=" + defending + ", goalKeeping=" + goalKeeping
				+ ", shortPassing=" + shortPassing + ", longPassing="
				+ longPassing + ", crossing=" + crossing + ", speed=" + speed
				+ ", stamina=" + stamina + ", tackling=" + tackling
				+ ", power=" + power + ", accuracy=" + accuracy + ", luck="
				+ luck + ", userID=" + userID + ", position=" + position
				+ ", jerseyNo=" + jerseyNo + ", DOB=" + DOB + "]";
	}
	
	public Player(String playerID, String teamID, String userID) {
		super();
		this.playerID = playerID;
		this.teamID = teamID;
		this.userID = userID;
		
		// get player stats from database
		String fetchPlayerQuery="select * from FBM_PLAYER_TABLE_STATS where PLAYER_ID='" + playerID + "' AND TEAM_ID='" + teamID + "'" +
		" AND USER_ID='"+userID+"'";
		
		Connection conn=global.getConnection();
		try {
			
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(fetchPlayerQuery);
			
			System.out.println("inside player constructor");
			
			while(rs.next()){
			
			this.accuracy=rs.getFloat("ACCURACY");
			this.attacking=rs.getFloat("ATTACKING");
			this.crossing=rs.getFloat("CROSSING");
			this.defending=rs.getFloat("DEFENDING");
			this.DOB=rs.getDate("DATE_OF_BIRTH");
			this.firstName=rs.getString("FIRST_NAME");
			this.lastName=rs.getString("LAST_NAME");
			this.goalKeeping=rs.getFloat("GOAL_KEEPING");
			this.jerseyNo=rs.getInt("JERSEY_NO");
			this.longPassing=rs.getFloat("LONG_PASSING");
			this.shortPassing=rs.getFloat("SHORT_PASSING");
			this.luck=rs.getFloat("LUCK");
			this.nationality=rs.getString("NATIONALITY");
			this.position=rs.getString("POSITION");
			this.power=rs.getFloat("POWER");
			this.speed=rs.getFloat("SPEED");
			this.stamina=rs.getFloat("STAMINA");
			this.tackling=rs.getFloat("TACKLING");
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// set rest of the attributes first fetch data from DB using playerID and userID
	}	
	


	
	
}
