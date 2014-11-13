package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class KOTeamSelectedController implements Initializable {
	
	private String playersInTeamArray[];
	private String Name;
	private ObservableList<String> playersInTeam;
	private ObservableList<String> playersSelectedInTeam;
	
	
	
	
	@FXML
	private Button playerSelect;
	
	@FXML
	private Button playerDeselect;
	
	@FXML
	private ListView<String> playerInKOTeam;
	
	@FXML
	private ListView<String> playerSelectedInKOTeam;
	
	@FXML
	private Button simulateKOMatch;
	
	private String PlayerQueryKO;

	
	// Player Selection For KickOff
	public void PlayerSelect(javafx.event.ActionEvent event){
		
		String playerInsert=playerInKOTeam.getSelectionModel().selectedItemProperty().getValue();
		System.out.println("player selected : " + playerInsert);

		// player remove from current list through dynamic binding
		if(playerInsert!=null){
			
			if(playersSelectedInTeam.size()<15){
				playersInTeam.remove(playerInsert);
				playersSelectedInTeam.add(playerInsert);
			}else{
				System.out.println("15 players selected already");
			}
			
		}
	}
	
	public void PlayerDeselect(javafx.event.ActionEvent event){
		
		String playerInsert=playerSelectedInKOTeam.getSelectionModel().selectedItemProperty().getValue();
		System.out.println("player selected : " + playerInsert);

		// player remove from current list through dynamic binding
		if(playerInsert!=null){			

			if(playersSelectedInTeam.size()>0){
				playersSelectedInTeam.remove(playerInsert);
				playersInTeam.add(playerInsert);
			}else{
				System.out.println("no more player");
			}
	
		}
	}
	
	
	// After team Selection of player One
	public void SimulateKOMatch(javafx.event.ActionEvent event){
		//simulate here
		String[] playerTeamOne=new String[15];
		for(int i=0;i<15;i++){
			playerTeamOne[i]=playersSelectedInTeam.get(i).trim();
			System.out.print(playerTeamOne[i]);	
		}
		
		if(playersSelectedInTeam.size()==15){
			System.out.println("\n15 players selected");
			// Do The Simulation Here
			System.out.println("obtaining data from database");
			System.out.println("Winner is : " + SimulateBetweenTeam.SimulateTeam(global.getTeam2Selected(), global.getTeam2Selected(), global.getUserID()));
		}else{
			System.out.println("15 players not selected");	
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("Initialize method called");

		System.out.println(" making connection....." );
		Connection conn=global.getConnection();
		
		System.out.println("get team players into a table for team : "+global.KOTeamOneSelected);
		String query="select TEAM_ID from FBM_TABLE_TEAM_ID where TEAM_NAME='" + global.KOTeamOneSelected + "'"; 
		System.out.println("query is : " + query);
		
		try {
		
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
			global.KOTeamOneSelected=rs.getString("TEAM_ID");
			System.out.println("obtained team ID is : "+global.KOTeamOneSelected);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();		
		}
		
		query="select FIRST_NAME,LAST_NAME from FBM_PLAYER_TABLE_STATS where TEAM_ID='" +
		global.KOTeamOneSelected + "' AND USER_ID='"+global.userID+"'"; 
		System.out.println("query is :" + query);
		
		playersInTeamArray=new String[30];
		for(int count=0;count<30;count++){
			playersInTeamArray[count]="no player";
		}
		
		int count=0;
		
		try {
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
			this.Name=rs.getString("FIRST_NAME");
			this.Name=this.Name + " " +rs.getString("LAST_NAME");
			playersInTeamArray[count]=this.Name;
			count++;
			
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();		
		}
		
		playersInTeam=FXCollections.observableArrayList(playersInTeamArray);
		playerInKOTeam.setItems(playersInTeam);
		
		playersSelectedInTeam=FXCollections.observableArrayList();
		playerSelectedInKOTeam.setItems(playersSelectedInTeam);
		
		
		
	}
	
	
}






