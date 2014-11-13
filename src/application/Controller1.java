package application;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller1 implements Initializable {
	@FXML
	private Label mainLabel;
	
	@FXML 
	private Pane pane;
	
	@FXML
	private ListView<String> teamList;
	
	@FXML
	private Button checkValidate;
	
	@FXML 
	private ListView<String> teamList1;
	
	
	private Hyperlink hLinks[];
	private String[] teamNames;
	
	public void teamSelectedBTN(javafx.event.ActionEvent event){
		String teamOneSelected = teamList.getSelectionModel().selectedItemProperty().getValue();

		String teamTwoSelected=teamList1.getSelectionModel().selectedItemProperty().getValue();
		
		if(teamOneSelected=="no team"||teamTwoSelected=="no team"||teamOneSelected==null||teamTwoSelected==null){
			System.out.println("Selection Not Complete");
		}else{

		global.KOTeamOneSelected=teamOneSelected;
		global.KOTeamTwoSelected=teamTwoSelected;
			
		System.out.println("Teams selected are :" + teamOneSelected + " and team two selected " +
		 "are : " + teamTwoSelected);
		// goto another page KOTeamSelectedPage
		//teamOneSelected and teamTwoSelected are variables Strings
		
		try {
			Stage primaryStage=new Stage();
			AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("KOTeamSelected.fxml"));
			Scene scene = new Scene(page,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			
		// navigated to another page
			
		}
		
		
		
		}
		
		
		
	}


	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println(" making connection....." );
		Connection conn=global.getConnection();
		
		System.out.println("get team names into a table");
		String query="select * from FBM_TABLE_TEAM_ID"; 
		
		//hLinks=new Hyperlink[32];
		teamNames=new String[32];
		int count=0;
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			
			while(rs.next()){
			String teamID=rs.getString("TEAM_ID");
			String teamName=rs.getString("TEAM_Name");
			String SimpleID=rs.getString("SIMPLE_ID");
			
			teamNames[count]=teamName;
			count++;

			
			}	// While statement ends
			
				for(;count<32;count++){
					teamNames[count]="no team";
				}


			//Use teamName Array to generate a listView
			ObservableList<String> myTeamListArray = FXCollections.observableArrayList(teamNames);
			teamList.setItems(myTeamListArray);
			teamList1.setItems(myTeamListArray);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
