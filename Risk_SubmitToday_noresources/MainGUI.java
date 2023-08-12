//Coded by the Risk team CPT 237-W34
//3/7/2023
//Author of Map GUI feature/Logic: Calvin Bultz


//This is the main entry of the program. 
//This is also the main gui for the game.
//This class holds a single instance of the GameStatus class.
//The GameStatus class keeps track of who's turn it is, a list of the active players, and a list of all the territories
//The player class holds the name of the player, their color, how many alloted armies they have to place, and a list of card objects
//The territory class keeps track of how many units are in the territory and what team color they are. It also holds a list of what territories are touching it.

//The game status class also holds a continent class that list what territories are in each continent. This is used for bonus unit allocation at the beginning of a turn.

//The mapscrollpane class is a extension of a javafx scroll pane class. It holds the map image pane like a regular scroll pane but...
//..also features methods to manipulate territory text content and color.

//The card class just stores a card type. Infantry,Cavalry,Cannon,or wild. These can be cached in at the beginning of a turn for extra... 
//..troop alottment 


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.control.ScrollPane;
import javafx.stage.WindowEvent;
import javafx.stage.Modality;
import java.util.LinkedList;


public class MainGUI extends Application {	
		
	//--Set Panes
   
   //Fits map on left and controls on right
	BorderPane mainPane = new BorderPane();
	//holds controls on the right(buttons, console, status)
   VBox controlPane = new VBox();
   //top of controls, Holds the status labels(current player, alloted units, color)
	HBox statusPane = new HBox();
   //middle of controls is just a console textarea
   TextArea txtAreaConsole = new TextArea();
   //after console, holds the 3 buttons, Go, Attack/End Attack/End turn (multiuse but single button), and use cards
	HBox buttonPane = new HBox();
   //after buttons, a pane to hold input for unit and dice quantity input 
   HBox inputPane = new HBox();
   //after input, a pane to hold dice roll results(3 rows of dice with a label header row)
   HBox diceHeader = new HBox();
   HBox diceImgRow1 = new HBox();
   HBox diceImgRow2 = new HBox();
   HBox diceImgRow3 = new HBox();
   //Two labels
   Label lblDH1 = new Label("Attacker");
   Label lblDH2 = new Label("Defender");
	// Array of dice images for attacker
	public ImageView imgViewAttacker1 = new ImageView();
	public ImageView imgViewAttacker2 = new ImageView();
	public ImageView imgViewAttacker3 = new ImageView();
   public ImageView imgViewDefender1 = new ImageView();
	public ImageView imgViewDefender2 = new ImageView();
   //spacer label
   Label lblDiceSpacer1 = new Label("                          ");
   Label lblDiceSpacer2 = new Label("    ");
   Label lblDiceSpacer3 = new Label("    ");
   
   
   //--Set MenuBar and Items
	MenuBar menuBar = new MenuBar();
	Menu shortCuts = new Menu("Dev Shortcuts");
	MenuItem skipSetup = new MenuItem("Skip Setup");
	MenuItem skipToEnd = new MenuItem("Skip To The Final Battle");
	
	   
   //--Set Map
   //Set MapScrollPane custom class
   MapScrollPane _mapScrollPane;
	
   //--Set control pane items
   
	//Set status labels and text
	Label lblTroops = new Label("Troops:");
	Text txtCurrentTroops = new Text("");
	Label lblPlayer = new Label("Player:");
   Text txtCurrentPlayer = new Text("");  
	Label lblColor = new Label("Color:");
	Text txtCurrentColor = new Text("");
      
   //Set button pane controls  
	final String[] cboBoxChoices = {"Attack", "Move Troops", "Next Turn"};
	@SuppressWarnings("unchecked")
	ComboBox cboBox = new ComboBox(FXCollections.observableArrayList(cboBoxChoices));
   //note that buttons are multiuse
   Button bnGo = new Button("Go");
	Button bnEnd = new Button("End Turn");
	Button bnUpdate = new Button("----");
         
   //Set the input labels and text
   Label lblUnitsOrDice = new Label("Units/Dice");
	TextField txtFieldUnitsOrDice = new TextField("");	
   
  


	            
   
   //--Game tracking settings
   GameStatus _GameStatus;
   int currentStep = 0;
   boolean updateFunction = false;
   boolean endFunction = false;
	
	
	public void start(Stage primaryStage) {
   
		//--Set left pane :map
      //Instantiate the custom scroll pane
      _mapScrollPane = new MapScrollPane();  
      //Render the map within which causes the javafx pane content calculations of nodes within    
      _mapScrollPane.renderMap();
      
      
     
      //--Set Right Pane :controls
      //Set Menu bar control
		menuBar.getMenus().add(shortCuts);
		shortCuts.getItems().addAll(skipSetup, skipToEnd);
      //Set other controls
		statusPane.setSpacing(30);     
		txtAreaConsole.setPrefSize(160, 200);
      cboBox.setPromptText("");      
		cboBox.setStyle("-fx-font-size: 10px");
		cboBox.setPrefSize(200, 40);
		buttonPane.setAlignment(Pos.BASELINE_CENTER);
      inputPane.setSpacing(30);   
      imgViewAttacker1.resize(50,50);
	   imgViewAttacker2.resize(50,50);
	   imgViewAttacker3.resize(50,50);
      imgViewDefender1.resize(50,50);
	   imgViewDefender2.resize(50,50);
		// Set  overall Styles to right 
		controlPane.setStyle("-fx-border-style: dashed; -fx-border-color: red;"
						+ " -fx-border-width: 4px; -fx-padding: 10px 10px 10px 10px;"
						+ " -fx-background-color: lightblue; -fx-align-items: center");
		//Add Children to control's sub panes
		statusPane.getChildren().addAll(lblTroops,txtCurrentTroops, lblPlayer, txtCurrentPlayer, lblColor, txtCurrentColor);
		buttonPane.getChildren().addAll(cboBox,bnGo,bnEnd,bnUpdate);
      inputPane.getChildren().addAll(lblUnitsOrDice,txtFieldUnitsOrDice);
      diceHeader.getChildren().addAll(lblDH1,lblDiceSpacer1,lblDH2);
      diceImgRow1.getChildren().addAll(imgViewAttacker1,lblDiceSpacer2,imgViewDefender1);
      diceImgRow2.getChildren().addAll(imgViewAttacker2,lblDiceSpacer3,imgViewDefender2);
      diceImgRow3.getChildren().addAll(imgViewAttacker3);
      //Add sub panes to the control pane
		controlPane.getChildren().addAll(menuBar,statusPane,txtAreaConsole,buttonPane,inputPane,diceHeader,diceImgRow1,diceImgRow2,diceImgRow3);
		
      //---Set main pane
      mainPane.setRight(controlPane);	
      mainPane.setCenter(_mapScrollPane);
      
      //Set all dice to the empty dice image
      clearDice();
            
      
      //---Set event handlers	
      bnGo.setOnAction(e ->{
         //The runlogicstep method will set the currentStep
         RunLogicStep(currentStep);
      });
      bnEnd.setOnAction(e ->{
         endFunction = true;
         RunLogicStep(currentStep);
      });
      bnUpdate.setOnAction(e ->{         
         updateFunction = true;
         RunLogicStep(currentStep);
      }); 
      skipSetup.setOnAction(e ->{
         //Developer Shortcut
         //places single unit in all territories alternating 2 players
         _GameStatus.developerSetMapInitial();
         //drop allotment to zero
         PlayerStatus player1 =_GameStatus.playerList.get(0);
         player1.currentAllottedUnits = 0;
         PlayerStatus player2 = _GameStatus.playerList.get(1);
         player2.currentAllottedUnits = 0;
         txtAreaConsole.setText("");
         txtAreaConsole.appendText("Skipped Setup."+ "\n");
         txtAreaConsole.appendText("All units have been placed. To waaarr!"+ "\n");
         currentStep = 2;
         RunLogicStep(currentStep);
      });
      skipToEnd.setOnAction(e ->{ 
         //Developer Shortcut
         //places single unit in all territories alternating 2 players
         _GameStatus.developerSkipToEnd();
         //drop allotment to zero
         PlayerStatus player1 =_GameStatus.playerList.get(0);
         player1.currentAllottedUnits = 0;
         PlayerStatus player2 = _GameStatus.playerList.get(1);
         player2.currentAllottedUnits = 0;
         txtAreaConsole.setText("");
         txtAreaConsole.appendText("Skipped To End"+ "\n");
         //Go the the start of a turn
         currentStep = 3;
         RunLogicStep(currentStep);        
      });   
           
        
				
      //---Set Scene/Stage/Window
		Scene scene = new Scene(mainPane, 1250, 750);
		primaryStage.setScene(scene);		      
      //before showing the main window, assign a handle for the window close event
      primaryStage.setOnCloseRequest(ev -> {
         System.out.println("Window Close");
      });
      primaryStage.show();
		
      //--Run the game
      //Instantiate the game status object
		_GameStatus = new GameStatus();
      //Call the main logic control switching method. The first step opens the popup for adding players
      RunLogicStep(currentStep);
	}
	public static void main(String[] args) {
		launch(args);
	}
   
   
   
   
   //Controls the Game. Relies on the currentStep variable, logic within, and the gamestatus object   
   public void RunLogicStep(int step) {
      if(step == 0){
         //--Game Start
         
         
         //Display popup until all players are added. Also provides a way to start the game         
         AddPlayerPopUp();
                  
         //set what the buttons say, enable or disable.
         //This also resets the button variables enfunction and updatefunction
         //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
         setButtons("Go",false,"----",true,"----",true);
         
         
                
      }
      else if(step == 1){
         //---Setup
         //All players have been added, Proceed with the setup portion of the game
         
         
         
         txtAreaConsole.appendText("All players added. Setup begins."+ "\n");
         txtAreaConsole.appendText("Armies have been allocated." + "\n");
         //Allot armies to all players for the setup portion of the game.
         //This will go through each player in the game and allot the appropriate amount of units per instructions 2=40 3=35.....
         _GameStatus.InitialAllotment();
         //Determine who goes first with a single dice roll by each player
         //---Todo: Insert Dice functionallity-----
         //---Player 1 wins //index 0-------
         //After setting this initial index, the _GameStatus.nextPlayer() method will move in the order players were added and wrap around.
         _GameStatus.currentActivePlayer = 0;
         //Get the current player object
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         //Add instruction to the console
         txtAreaConsole.appendText("Your turn: " + currentPlayer.teamName + "\n");
         txtAreaConsole.appendText("Choose a territory to place a single infantry unit."+ "\n");
         txtAreaConsole.appendText("Click go to place one."+ "\n");
         //Show territories in the combo box
         String[] territoryChoiceList = new String[_GameStatus.territoryList.size()];
         int iterator = 0;
         //Populate territory list: the base of observable collection
         for (Territory territory : _GameStatus.territoryList){
            territoryChoiceList[iterator] = territory.nameOfTerritory;
            iterator++;
         }
         cboBox.setItems(FXCollections.observableArrayList(territoryChoiceList));
         cboBox.setValue(territoryChoiceList[0]);         
         //Everything is all set to loop each player placing a single army.
         //Increment the step, go button even handler will call this method: RunLogicStep
         currentStep = 2;
         //Updates the map, updates the control panel with the current player's stats                 
         UpdateGui();
         
         
         
           
      }
      else if(step == 2){
         //--Setup
         //Loop the setup routine of placing single units until the map is filled followed by single unit reinforments.
         
      
         //Check if the player still has alloted armies to place. All the other players should also be empty.
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         if(currentPlayer.currentAllottedUnits == 0){
            //Setup complete
            //Start the game: player turn
            currentStep = 3;
            //Inform the user
            txtAreaConsole.setText("");
            txtAreaConsole.appendText("All units have been placed. To waaarr!"+ "\n");
            RunLogicStep(currentStep);
            return;
         }
         else{
            //There are still available units to place
            //Continue placing units         
            //Check if the map is full to determine if reinforcements can be added yet
            if(_GameStatus.fullyOccupied() == false){
               //There are still empty territories
               //Continue placing armies in unoccupied territories
               boolean success = _GameStatus.SetupPlaceSingleUnitEmptyRule(cboBox.getValue().toString(),currentPlayer.teamColor);
               //return error or switch player and repeat
               if(success == true){
                  //Subtract one unit
                  currentPlayer.currentAllottedUnits--;
                  //next player 
                  _GameStatus.nextPlayer();
                  //Check to see if this player is on zero. This prevents bug where all units have been placed but the game has not started.
                  currentPlayer = _GameStatus.getCurrentPlayer();
                  if(currentPlayer.currentAllottedUnits == 0){                   
                     //Setup complete
                     //Start the game
                     currentStep = 3;
                     //Inform the user
                     txtAreaConsole.setText("");
                     txtAreaConsole.appendText("All units have been placed. To waaarr!"+ "\n");
                     RunLogicStep(currentStep);
                     return;
                  }
                  //Setup is not done.
                  //Indicate successfull placement and next player
                  txtAreaConsole.setText("");
                  txtAreaConsole.appendText("Placement success" + "\n");
                  txtAreaConsole.appendText("Your turn: " + currentPlayer.teamName +"....place a unit."+ "\n");
                  //update the map with new unit placements, update control panel to the player
                  UpdateGui();
                  return;
               }
               else{
                  //Unit placement failed
                  //alert user to the error message
                  txtAreaConsole.appendText("There is already a unit there. The empty territories must be filled first. Try again."+ "\n");
                  //dont change player
                  //dont remove unit                  
                  return;
               }
            }
            else{
               //The map is fully occupied
               //Continue placing armies,but as reinforcements, only check for color match
               boolean placeSuccess = _GameStatus.PlaceUnit(cboBox.getValue().toString(),currentPlayer.teamColor);
               if(placeSuccess == true){
                  //Successfull placement of a unit
                  //Subtract one unit
                  currentPlayer.currentAllottedUnits--;                 
                  //next player 
                  _GameStatus.nextPlayer();
                  //Check to see if this player is on zero. This prevents bug where all units have been placed but the game has not started.
                  currentPlayer = _GameStatus.getCurrentPlayer();
                  if(currentPlayer.currentAllottedUnits == 0){                   
                     //Setup complete
                     //Start the game
                     currentStep = 3;
                     //Inform the user
                     txtAreaConsole.setText("");
                     txtAreaConsole.appendText("All units have been placed. To waaarr!"+ "\n");
                     RunLogicStep(currentStep);
                     return;
                  }
                  //Setup is not done.
                  //Indicate successfull placement and next player
                  txtAreaConsole.appendText("Placement success" + "\n");
                  txtAreaConsole.appendText("Your turn: " + currentPlayer.teamName +"..place a unit."+ "\n");
                  //update the map with new unit placements, update control panel to the player
                  UpdateGui();
                  return;
               }
               else{
                  //failure to place a unit
                  //display error
                  txtAreaConsole.appendText("There is already a unit there from another player. Try again."+ "\n");
                  //dont change player
                  //dont remove unit 
                  return;            
               }
            }         
         }   
         
         
         
         
      }
      else if(step == 3){
         //---Player turn
         //The start of a player turn 
         
         
         
         
         //Ensure the game is not over
         if(_GameStatus.isGameOver() == true){
            //Declare victory screen
            currentStep = 14; 
            RunLogicStep(currentStep);
            return;
         }
         //Game is not over, Allot armies to the player to place as reinforcements at the beginning of a turn.
         
         //Get the current player
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer(); 
         //ensure the allotment is reset
         currentPlayer.currentAllottedUnits = 0;       
         //Allot armies based on the everyone gets at least 3 rule.
         int reinforcementCount = 3;         
         //Allot armies based on territory ownership quantity         
         reinforcementCount = reinforcementCount + _GameStatus.calculateTerritoryAllotment(currentPlayer);
         //Allot armies based on owning a continent
         reinforcementCount = reinforcementCount + _GameStatus.calculateContinentAllotment(currentPlayer);
         //assign the reinforcements to the player object
         currentPlayer.currentAllottedUnits = reinforcementCount;
         //Update the gui so the player can see how many reinforments they have so far at the start of their turn
         UpdateGui();
         //increment the step and wait for the go or the use cards buttons
         currentStep = 4;         
         //Instruct the user 
         txtAreaConsole.setText("");        
         txtAreaConsole.appendText("Your turn: " + currentPlayer.teamName + "\n");
         txtAreaConsole.appendText("Click cash in cards to turn in a matching set of cards for additional units. You can only do this at the beggining of your turn." + "\n");
         txtAreaConsole.appendText("Otherwise, select a territory, type the number of units, and click Go to add reinforcements to a territory." + "\n");
         txtAreaConsole.appendText("You may also choose to end your turn." + "\n");        
         //set what the buttons say, enable or disable, and reset the button variables
         //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
         setButtons("Go",false,"End Turn",false,"Use Cards",false);
         return;
         
         


      }
      else if(step == 4){     
         //--Player turn 
         //User chose to place reinforcements, to cash in card set, or end turn      
         
         
         
                  
        
         //Determine if the user pressed the cash in the cards btn
         if(updateFunction == true){
            //Disable card btn until next turn           
            //set what the buttons say, enable or disable, and reset the button variables
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"End Turn",false,"----",true);

            
                        
            //Add armies based on the current matching set allotment
            //Get the current player
            PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();             
            int reinforcmentCount = 0;
            reinforcmentCount = currentPlayer.currentAllottedUnits;
            reinforcmentCount = reinforcmentCount + _GameStatus.cashInCardSet();
            //update player
            currentPlayer.currentAllottedUnits = reinforcmentCount;
            //Reflect this on the gui
            UpdateGui();
            txtAreaConsole.setText("");
            //add a success message
            txtAreaConsole.appendText("Cash in success. Additional units granted." + "\n");
            //Instruct the user to place reinforcements
            txtAreaConsole.appendText("Select a territory, type the number of units, and click Go to add reinforcements to a territory." + "\n");
            txtAreaConsole.appendText("You may also choose to end your turn." + "\n");
            //Increment to the reinforcments step
            currentStep = 5;
            return;
         }
         //Check for the end function btn
         if(endFunction == true){
            //User hit the end turn button
            txtAreaConsole.setText("");
            txtAreaConsole.appendText("Ending Turn" + "\n");
            //set what the buttons say, enable or disable, and reset the button variables
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"----",true,"----",true);            
            //User jumps to the end of turn step
            currentStep = 10;
            RunLogicStep(currentStep);
            return;
         }         
         //If the user did not press the cash cards button or end turn, this step acts as a bypass to the reinforcent placement step
         //The reinforcement propts occur at the end so that a double prompt does not occur between the beggining of a turn and a nth reinforcement placement turn.
         currentStep = 5;
         RunLogicStep(currentStep);
         return;
         
                  
                 
      }
      else if(step == 5){
         //Player turn
         //nth reinforcement placement. 
         
         
         
         //Get the current player
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         //Get the chosen territory and the amount to place
         String territoryName = cboBox.getValue().toString();
         int unitsToMove = Integer.valueOf(txtFieldUnitsOrDice.getText());
         //this method will check if the player has enough units, it also checks for same team ownership
         boolean moveSuccess = _GameStatus.PlaceUnits(territoryName,currentPlayer,unitsToMove);
         if(moveSuccess == true){
            //Remove the units from the reinforcement count
            currentPlayer.currentAllottedUnits = currentPlayer.currentAllottedUnits - unitsToMove;
            //success message
            txtAreaConsole.appendText("Reinforcements placed." + "\n");
            //update the gui
            UpdateGui();            
            //Determine if all the reinforcements have been placed
            if(currentPlayer.currentAllottedUnits <= 0){
               //Proceed to the attack portion of the players turn
               currentStep = 6;                             
               //attack prompt, clear out previous so that the destinction is notable
               txtAreaConsole.setText("");
               txtAreaConsole.appendText("All reinforcemnts have been placed." + "\n");
               txtAreaConsole.appendText("Select a territory to attack and click Go. To end turn hit the end turn." + "\n");
               //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
               setButtons("Go",false,"End Turn",false,"----",true); 
               //user button press will be handled in step 6.               
               return;
            }
            else{
               //There are still units to place.
               //Give instructions
               txtAreaConsole.appendText("Select a territory, type the number of units, and click Go to add reinforcements to a territory." + "\n");
               return;
            }            
         }
         else{
            //error placing reinfocements
            txtAreaConsole.appendText("There was an error placing units please try again." + "\n"); 
            //dont update the player reinforcement count
            //Give instructions
            txtAreaConsole.appendText("Select a territory, type the number of units, and click Go to add reinforcements to a territory." + "\n");
            return;
         }

         
         
         
         
      }
      else if(step == 6){
         //Player Turn
         //The player chose a place to attack or hit the end button
        
         
         
         
         
         //Check for the end function btn
         if(endFunction == true){
            //User hit the end turn button
            txtAreaConsole.setText("");
            txtAreaConsole.appendText("Ending Turn" + "\n");
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"----",true,"----",true);
            //User jumps to the end of turn step
            currentStep = 10;
            RunLogicStep(currentStep);
            return;
         }
         //Go button was pressed
         //User selected a territory to invade
         
         //Get the current player
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         
         //Get the selected territory and set the current player's attack this property         
         Territory attackThis = _GameStatus.getTerritoryByName(cboBox.getValue().toString());
         //Validate
         if(_GameStatus.attackingSelf(currentPlayer,attackThis ) == true){
            //fail
            txtAreaConsole.appendText("Friendly Fire! Check your fire! Check your fire!" + "\n");
            txtAreaConsole.appendText("Select a territory to attack and click Go. To end turn hit the end turn button." + "\n");
            return;
         }
         else{
            //valid enemy, assign property
            currentPlayer.currentAttackThisTerritory = attackThis;
            //increment the step
            currentStep = 7;
            //Relay that to the user
            txtAreaConsole.appendText("Attacking: " + currentPlayer.currentAttackThisTerritory.nameOfTerritory + "\n");
            txtAreaConsole.appendText("Select the from territory, type how many dice to use, and hit go." + "\n"); 
            txtAreaConsole.appendText("You may also choose to end the attack and go back to a new attack." + "\n");
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"End Attack",false,"----",true); 
            return;          
         }
                  
         
         
         
      }
      else if(step == 7){
         //Player Turn
         //Player chose the attack from and how many dice to use, also handle a end attack button decision.
         
         
         
         //Check for the end function btn
         if(endFunction == true){
            //User hit the end attack button            
            //User jumps to the start of turn for a new attack, from there they can click end again to end turn
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"End Turn",false,"----",true);
            currentStep = 6;
            txtAreaConsole.setText("");
            txtAreaConsole.appendText("Select a territory to attack and click Go. To end turn hit the end button." + "\n");
            return;
         }
         //Go button was pressed
         //User selected a territory to attack from
         
         //Get the current player
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         //Get the selected territory and set the current player's attack from property
         //Validate. ensures player owns territory, ensures at least a single player is left behind
         boolean valid = _GameStatus.validateAttackFrom(currentPlayer, _GameStatus.getTerritoryByName(cboBox.getValue().toString()), Integer.valueOf(txtFieldUnitsOrDice.getText()));
         if(valid){
            //assign properties
            currentPlayer.currentAttackFromTerritory = _GameStatus.getTerritoryByName(cboBox.getValue().toString());          
            currentPlayer.diceToRoll = Integer.valueOf(txtFieldUnitsOrDice.getText());
            //increment the step
            currentStep = 8;
            //Relay that to the user
            txtAreaConsole.appendText("Attacking from: " + currentPlayer.currentAttackFromTerritory.nameOfTerritory + "\n");
            txtAreaConsole.appendText("Hit go to roll dice and view results. Hit end attack to cancel the attack. You may also update the dice quantity before hitting Go." + "\n");
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"End Attack",false,"----",true);
            return;
         }
         else{
            //invalid
            //return error
            txtAreaConsole.appendText("This is not valid. Ensure ownership and that a unit will be left behind per game rules on dice quantity." + "\n");
            txtAreaConsole.appendText("Select the from territory, type how many dice to use, and hit go." + "\n");
            return;
         }
         
         
         
      
      }
      else if(step == 8){
         //Player turn
         //Roll dice for both players to simulate the battle, This was separated from step 7 attack from so that the territory is locked in
       
         
         
        
         
         //Check for the end function btn
         if(endFunction == true){
            //User hit the end attack button
            //User jumps to the start of turn to end attack, from there they can click it again to end turn
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"End Turn",false,"----",true);
            currentStep = 6;
            txtAreaConsole.setText("");
            txtAreaConsole.appendText("Select a territory to attack and click Go. To end turn hit the end turn button." + "\n");
            return;
         }
         //The go button was pressed.
         //The dice must be rolled
         
         //Validate players dice,
         //Get the current player
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         //Validate
         boolean valid = _GameStatus.validateAttackFrom(currentPlayer, currentPlayer.currentAttackFromTerritory, Integer.valueOf(txtFieldUnitsOrDice.getText()));
         if(!valid){
            //invalid, error message and return                       
            txtAreaConsole.appendText("Dice count error." + "\n");
            txtAreaConsole.appendText("Hit go to roll dice and view results.Type in dice quantity as needed and hit Update, Hit end attack to cancel the attack." + "\n");
            return;
         }  
         //Valid
         //assign properties                        
         currentPlayer.diceToRoll = Integer.valueOf(txtFieldUnitsOrDice.getText());
          
         //Calculate defender's dice count, should not have to handle zero because concquer is determined after roll
         int defenderDiceCount = _GameStatus.getDefenderDiceCount(currentPlayer.currentAttackThisTerritory);
         //Assemble imageView arrays sized proportional to the dice counts used
         //Also updates properties in the game status for win loss stats and the territories
         rollDice(currentPlayer,currentPlayer.diceToRoll, defenderDiceCount);        
                
         //Update Gui
         UpdateGui();
         
         //Ensure the game is not over
         if(_GameStatus.isGameOver() == true){
            //Declare victory screen
            currentStep = 14; 
            RunLogicStep(currentStep);
            return;
         }

         
         //Show stats of attack
         txtAreaConsole.appendText("Defender loss: " + _GameStatus.diceResultDefenderLoss + " Attacker loss: " + _GameStatus.diceResultAttackerLoss + "\n");
         
         //Determine if territory conquered, move to step 9, if the player runs out of units then they will just get an error if they try to attack
         if(_GameStatus.getDefenderDiceCount(currentPlayer.currentAttackThisTerritory) > 0){
            //Territory not conquerred yet
            txtAreaConsole.appendText("Hit go to roll dice and view results. Hit update to change the quantity of dice, Hit end attack to cancel the attack." + "\n");
            return;
         }
         else{
            //Conquerred
            
            //Ensure the game is not over
            currentPlayer.conquereedLastTurn = true;            
            currentStep = 9;
            txtAreaConsole.appendText("You won the battle!" + "\n");
            txtAreaConsole.appendText("Enter how many troops to move into your new territory and hit go." + "\n");
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"----",true,"----",true);
            return;
         }
         
         
         
         
         
      }
      else if(step == 9){
         //Player turn
         //Move into territory from attacking position (this is not the free end of turn) 
         
         
         //clear dice here rather than step 8 so the player can still see what happend on that last finishing dice roll that wiped out the other player on the territory
         clearDice();
         //validate quantity
         //Get the current player
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         //get the requested amount
         int requestedMoveCount = Integer.valueOf(txtFieldUnitsOrDice.getText());
         if(requestedMoveCount < currentPlayer.diceToRoll){
            //error, you must at least move how many dice you used
            txtAreaConsole.appendText("You must move at least as many as the dice you used." + "\n");
            txtAreaConsole.appendText("Enter how many troops to move into your new territory and hit go." + "\n");
            return;
         } 
         else{
            //Valid move            
            boolean addSuccess = _GameStatus.PlaceUnits(currentPlayer.currentAttackThisTerritory.nameOfTerritory,currentPlayer,requestedMoveCount);
            boolean removeSuccess = _GameStatus.RemoveUnits(currentPlayer.currentAttackFromTerritory.nameOfTerritory,currentPlayer,requestedMoveCount);
            if(addSuccess == true && removeSuccess == true){
               //Set conquerred a territory property
               currentPlayer.conquereedLastTurn = true;
               //Go back to the start of a new attack
               currentStep = 6;
               //give prompt           
               txtAreaConsole.setText("");
               txtAreaConsole.appendText("Select a territory to attack and click Go. To end turn hit the end turn." + "\n");
               //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
               setButtons("Go",false,"End Turn",false,"----",true);
               UpdateGui();
               return;
            }
            else{
               //error
               //loop
               txtAreaConsole.appendText("Error moving into conquerred region." + "\n");
               txtAreaConsole.appendText("Enter how many troops to move into your new territory and hit go." + "\n");
               return;            
            }
         }               
        
         
         
         
         
         
      }
      else if(step == 10){
         //Player turn
         //End turn, free move
         
         
         
                  
         //Get the current player
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         //Reset
         currentPlayer.conquereedLastTurn = false;

         //prompt select country to move to and how many
         txtAreaConsole.appendText("You may do a single reinforcement move between two of your territories" + "\n");
         txtAreaConsole.appendText("Select the territory to move into and hit go. To skip this step hit end turn." + "\n");
         currentStep = 11;
         //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
         setButtons("Go",false,"End Turn",false,"----",true);
         UpdateGui();

         
         
      
      }
      else if(step == 11){
         //Player turn
         //end turn selected move to 
         
         
         
         //Check for end turn skip
         //Check for the end function btn
         if(endFunction == true){
            //User hit the end turn button           
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"----",true,"----",true);
            currentStep = 13;
            RunLogicStep(currentStep);
            return;
         }

         
         //Validate
         //Get the current player
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         boolean ownedTerritory = _GameStatus.attackingSelf(currentPlayer,_GameStatus.getTerritoryByName(cboBox.getValue().toString()));
         if(ownedTerritory == true){
            //Valid
            //just reuse existing variables
            currentPlayer.currentAttackThisTerritory = _GameStatus.getTerritoryByName(cboBox.getValue().toString());
            txtAreaConsole.appendText("Moving to: " + cboBox.getValue().toString() + "\n");
            txtAreaConsole.appendText("Select the territory to move from, the number of units, and hit go. To skip this step hit end turn." + "\n");
            //increment
            currentStep = 12;
            return;
         }
         else{
            //error
            txtAreaConsole.appendText("Error selecting the move to territory, ensure you have ownership.: " + "\n");
            txtAreaConsole.appendText("Select the territory to move into and hit go. To skip this step hit end turn." + "\n");
            return;
         }
         
         
         
         
      }
      else if(step == 12){
         //Player turn
         //end turn select move from and how many
         
         
         
         
         //Handle end turn skip
         //Check for end turn skip
         //Check for the end function btn
         if(endFunction == true){
            //User hit the end turn button           
            //setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,bnUpdate_Off)
            setButtons("Go",false,"----",true,"----",true);
            //Next player
            currentStep = 13;
            RunLogicStep(currentStep);
            return;
         }

         
         //Get the current player
         PlayerStatus currentPlayer = _GameStatus.getCurrentPlayer();
         //Validate touching
         boolean touching = currentPlayer.currentAttackThisTerritory.isTouching(cboBox.getValue().toString());
         if(touching == false){
            //return error
            txtAreaConsole.appendText("Error. Ensure the two are touching." + "\n");
            txtAreaConsole.appendText("Select the territory to move from, the number of units, and hit go. To skip this step hit end turn." + "\n");
            return;     
         }
         //Remove units
         boolean removeSuccess = _GameStatus.RemoveUnits(cboBox.getValue().toString(),currentPlayer,Integer.valueOf(txtFieldUnitsOrDice.getText()));
         if(removeSuccess == false){
            txtAreaConsole.appendText("Error. Ensure you have ownership. Ensure at least one unit is left behind." + "\n");
            txtAreaConsole.appendText("Select the territory to move from, the number of units, and hit go. To skip this step hit end turn." + "\n");
            return;
         }
         //Add units
         boolean addSuccess = _GameStatus.PlaceUnits(currentPlayer.currentAttackThisTerritory.nameOfTerritory,currentPlayer,Integer.valueOf(txtFieldUnitsOrDice.getText()));
         if(addSuccess == false){
            txtAreaConsole.appendText("Error. Ensure you have ownership. Ensure at least one unit is left behind." + "\n");
            txtAreaConsole.appendText("Select the territory to move from, the number of units, and hit go. To skip this step hit end turn." + "\n");
            return;
         }
         txtAreaConsole.appendText("Reinforcement move success." + "\n");
         //next player
         currentStep = 13;
         RunLogicStep(currentStep);
         return;   
         
         
         
      }
      else if(step == 13){
         //End of Turn
         //Go to next player
         
         
         //Either the player hit end turn or they successfully did a reinforcment move
         //increment player
         _GameStatus.nextPlayer();
         //Go to the beginning of a turn where the units are allocated.
         currentStep = 3;
         RunLogicStep(currentStep);

         
         
         
         
         
         
      }
      else if(step == 14){
         //End of game
         
         displayVictory();
         txtAreaConsole.appendText("End of game." + "\n");
         
         
      }     
   } 
   
   
   public void UpdateGui(){
      //Update the mainGui controls with the current players data
      PlayerStatus currentPlayer = _GameStatus.playerList.get(_GameStatus.currentActivePlayer);
      
      txtCurrentPlayer.setText(currentPlayer.teamName);
      txtCurrentColor.setText(currentPlayer.teamColor.toString());
      txtCurrentTroops.setText(String.valueOf(currentPlayer.currentAllottedUnits));
      
            
           
           
      //Update the map with the game status territory data on troop placement
      
      for(Territory t : _GameStatus.territoryList){
         if(t.armyUnitCount > 0){        
             _mapScrollPane.setMapTextObjectColor(t.nameOfTerritory,t.currentPlayerColor);
             _mapScrollPane.setMapTextObjectText(t.nameOfTerritory,String.valueOf(t.armyUnitCount));          
         }
         else{
            _mapScrollPane.setMapTextObjectColor(t.nameOfTerritory,Color.ALICEBLUE);
            _mapScrollPane.setMapTextObjectText(t.nameOfTerritory,"0");
            
         }                    
      }
    
   
   }
   
   
   public void setButtons(String bnGo_T, boolean bnGo_Off,String bnEnd_T,boolean bnEnd_Off,String bnUpdate_T,boolean bnUpdate_Off){
      bnGo.setText(bnGo_T);
      bnGo.setDisable(bnGo_Off);
      bnEnd.setText(bnEnd_T);
      bnEnd.setDisable(bnEnd_Off);
      bnUpdate.setText(bnUpdate_T);
      bnUpdate.setDisable(bnUpdate_Off);
      endFunction = false;
      updateFunction = false;      
   }
   
   
   
public void AddPlayerPopUp(){
      Stage addPlayer = new Stage();
      addPlayer.setTitle("Add Player");
      String playerStr = "";
      
      /* Set Fields */
      Label title = new Label("RISK");
      Label instructionLabel1 = new Label("Please enter the player name and color.");
      Label instructionLabel2 = new Label("Continue adding players as needed. When done click Start Game.");
      Label playerCountLabel = new Label("Player Count: ");
      
      Text playerCountField = new Text();
     
      Label nameLabel = new Label("Name:");
      TextField nameField = new TextField();
      
      
      Label colorLabel = new Label("Color:");
      TextField colorField = new TextField();
      
      Button btnAddPlayer = new Button("Add Player");
      Button btnStartGame = new Button("Start Game");
      
      Text playerLayout = new Text();
      
      VBox root = new VBox();
      HBox countRow = new HBox();
      HBox nameRow = new HBox();
      HBox colorRow = new HBox();
      HBox btnRow = new HBox();
      HBox playerList = new HBox();
         
      /* Set Text and Add Event Listeners with logic */
      playerCountField.setText(String.valueOf(_GameStatus.playerList.size()));
      for(PlayerStatus p: _GameStatus.playerList) {
      	 playerStr += (p.teamName.toString() + " " + p.teamColor.toString() + "\r\n");
       }
      playerLayout.setText(playerStr);
      btnAddPlayer.setOnAction(e ->{
         //Add in: error check for color match and avoid the default empty color 
         _GameStatus.AddPlayer(nameField.getText(),Color.valueOf(colorField.getText()));
         addPlayer.close();
         RunLogicStep(currentStep);
      }); 
 
      btnStartGame.setOnAction(e ->{            
         _GameStatus.currentActivePlayer = 0;
         UpdateGui();
         currentStep = 1;
         addPlayer.close();
         RunLogicStep(currentStep);  
      }); 
      
      /* Add Items to the root and Set Styles*/
      title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
      playerLayout.setStyle("-fx-font-size: 20px");
      root.setAlignment(Pos.BASELINE_CENTER);
      root.setSpacing(10);
      root.setStyle("-fx-background-color: lightblue;");
      nameRow.setAlignment(Pos.BASELINE_CENTER);
      colorRow.setAlignment(Pos.BASELINE_CENTER);
      btnRow.setAlignment(Pos.BASELINE_CENTER);
      playerList.setAlignment(Pos.BASELINE_CENTER);
      nameRow.setSpacing(10);
      colorRow.setSpacing(10);
      btnRow.setSpacing(10);
      playerList.setSpacing(10);
      countRow.getChildren().addAll(
    		  playerCountLabel, playerCountField);
      nameRow.getChildren().addAll(
    		  nameLabel, nameField);
      colorRow.getChildren().addAll(
    		  colorLabel, colorField);
      btnRow.getChildren().addAll(
    		  btnAddPlayer, btnStartGame);
      playerList.getChildren().add(playerLayout);
      root.getChildren().addAll( title,
          instructionLabel1,
          instructionLabel2,
          nameRow,
          colorRow,
          btnRow, countRow, playerList
      );
      // Create a scene and set it on the new stage
      Scene newScene = new Scene(root, 500, 400);
      addPlayer.setScene(newScene);
      addPlayer.setAlwaysOnTop(true);
      // Show the new stage
      addPlayer.show();

   }

   public void displayVictory() {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Game Over");
      
        Label label1 = new Label("Bragging rights allocated.");
        Label label2 = new Label("Close the application and restart to play again.");

      
        Button button1 = new Button("Close");
        button1.setOnAction(e -> popupwindow.close());
     
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,label2, button1);
        layout.setAlignment(Pos.CENTER);
      
        Scene scene1 = new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
   
        popupwindow.showAndWait();
    }
   
   
   
   public void rollDice(PlayerStatus currentPlayer,int aQty, int dQty){
      if(aQty == 3 && dQty == 2){
         ImageView[] aImgs = {imgViewAttacker1,imgViewAttacker2,imgViewAttacker3};
         ImageView[] dImgs = {imgViewDefender1,imgViewDefender2};
         _GameStatus.rollDiceImg(currentPlayer,aImgs,dImgs);

      }
      else if(aQty == 3 && dQty == 1){
         ImageView[] aImgs = {imgViewAttacker1,imgViewAttacker2,imgViewAttacker3};
         ImageView[] dImgs = {imgViewDefender1};
         _GameStatus.rollDiceImg(currentPlayer,aImgs,dImgs);

      }
      else if(aQty == 2 && dQty == 2){
         ImageView[] aImgs = {imgViewAttacker1,imgViewAttacker2};
         ImageView[] dImgs = {imgViewDefender1,imgViewDefender2};
         _GameStatus.rollDiceImg(currentPlayer,aImgs,dImgs);

      }
      else if(aQty == 2 && dQty == 1){
         ImageView[] aImgs = {imgViewAttacker1,imgViewAttacker2};
         ImageView[] dImgs = {imgViewDefender1};
         _GameStatus.rollDiceImg(currentPlayer,aImgs,dImgs);

      }
      else if(aQty == 1 && dQty == 2){
         ImageView[] aImgs = {imgViewAttacker1};
         ImageView[] dImgs = {imgViewDefender1,imgViewDefender2};
         _GameStatus.rollDiceImg(currentPlayer,aImgs,dImgs);

      }
      else if(aQty == 1 && dQty == 1){
         ImageView[] aImgs = {imgViewAttacker1};
         ImageView[] dImgs = {imgViewDefender1};
         _GameStatus.rollDiceImg(currentPlayer,aImgs,dImgs);

      }

   }
   
   
   private void clearDice(){
      //Set all dice to the empty dice image
      imgViewAttacker1.setImage(new Image("images/emptyDice.png", 100, 100, false, false));
      imgViewAttacker2.setImage(new Image("images/emptyDice.png", 100, 100, false, false));
      imgViewAttacker3.setImage(new Image("images/emptyDice.png", 100, 100, false, false));
      imgViewDefender1.setImage(new Image("images/emptyDice.png", 100, 100, false, false));
      imgViewDefender2.setImage(new Image("images/emptyDice.png", 100, 100, false, false));

   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
