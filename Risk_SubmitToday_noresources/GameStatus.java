//Coded by the Risk team CPT 237-W34
//3/7/2023
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
import java.util.LinkedList;
import java.util.HashMap;
import java.lang.Math;

public class GameStatus {	

   //Matches the playerList object indexes, easier to move to the next than storing a player object
   public int currentActivePlayer = 0;
   
   //At the beginning of a turn, a matching set of cards can be turned in, cetain allotments of units are granted, this number increments as sets are turned in.
   public int cardSubmissionCount = 0;
   
   //Hold list of certain game objects
   public LinkedList<PlayerStatus> playerList = new LinkedList<PlayerStatus>();
   public LinkedList<Territory> territoryList = new LinkedList<Territory>();
   private LinkedList<Continent> continentList = new LinkedList<Continent>();
   
   public int diceResultDefenderLoss = 0;
   public int diceResultAttackerLoss = 0;
   
   
   //Maps aka Dictionaries
   //<player count,units> this is the units alloted at the beginning of the game
   private HashMap<Integer,Integer> setupAllotmentMap = new HashMap<Integer,Integer>();
   //<card submission count,units> this is the units alloted when a player cashes in a matching set of cards
   private HashMap<Integer,Integer> cashInAllotmentMap = new HashMap<Integer,Integer>();
   
  
   
	public GameStatus(){
      
      //Set up all the territories on the map
      
      
      //Define and instantiate reused variables
      LinkedList<String> touchingTerritories = new LinkedList<String>();
      Territory mapTerritory = new Territory("Null");

      
      //Alaska
      //each territory keeps track of what territories it is touching
      touchingTerritories.clear();
      touchingTerritories.add("Northwest Territory");
      touchingTerritories.add("Alberta");
      touchingTerritories.add("Kamchatka");
      mapTerritory = new Territory("Alaska",touchingTerritories);
      territoryList.add(mapTerritory);     
      
      //Nothwest Territory
      //reuse variable
      touchingTerritories.clear();
      touchingTerritories.add("Alaska");
      touchingTerritories.add("Alberta");
      touchingTerritories.add("Ontario");
      touchingTerritories.add("Greenland");
      mapTerritory = new Territory("Northwest Territory",touchingTerritories);
      territoryList.add(mapTerritory);
      
      //...
      touchingTerritories.clear();
      touchingTerritories.add("Alaska");
      touchingTerritories.add("Northwest Territory");
      touchingTerritories.add("Ontario");
      touchingTerritories.add("Western United States");
      mapTerritory = new Territory("Alberta",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Alberta");
      touchingTerritories.add("Quebec");
      touchingTerritories.add("Greenland");
      touchingTerritories.add("Northwest Territory");
      touchingTerritories.add("Eastern United States");
      touchingTerritories.add("Western United States");
      mapTerritory = new Territory("Ontario",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Alberta");     
      touchingTerritories.add("Ontario");
      touchingTerritories.add("Eastern United States");
      touchingTerritories.add("Central America");
      mapTerritory = new Territory("Western United States",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();          
      touchingTerritories.add("Venezuela");
      touchingTerritories.add("Eastern United States");
      touchingTerritories.add("Western United States");
      mapTerritory = new Territory("Central America",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();          
      touchingTerritories.add("Ontario");
      touchingTerritories.add("Quebec");
      touchingTerritories.add("Western United States");
      touchingTerritories.add("Central America");
      mapTerritory = new Territory("Eastern United States",touchingTerritories);
      territoryList.add(mapTerritory);

      touchingTerritories.clear();          
      touchingTerritories.add("Ontario");
      touchingTerritories.add("Greenland");      
      touchingTerritories.add("Eastern United States");
      mapTerritory = new Territory("Quebec",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();          
      touchingTerritories.add("Northwest Territory");
      touchingTerritories.add("Ontario");      
      touchingTerritories.add("Quebec");
      touchingTerritories.add("Iceland");
      mapTerritory = new Territory("Greenland",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();          
      touchingTerritories.add("Central America");
      touchingTerritories.add("Peru");      
      touchingTerritories.add("Brazil");      
      mapTerritory = new Territory("Venezuela",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();          
      touchingTerritories.add("Venezuela");
      touchingTerritories.add("North Africa");
      touchingTerritories.add("Peru");      
      touchingTerritories.add("Argentina");      
      mapTerritory = new Territory("Brazil",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();          
      touchingTerritories.add("Venezuela");
      touchingTerritories.add("Brazil");      
      touchingTerritories.add("Argentina");      
      mapTerritory = new Territory("Peru",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();          
      touchingTerritories.add("Brazil");      
      touchingTerritories.add("Peru");      
      mapTerritory = new Territory("Argentina",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();          
      touchingTerritories.add("Greenland");      
      touchingTerritories.add("Scandinavia"); 
      touchingTerritories.add("Great Britain");     
      mapTerritory = new Territory("Iceland",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();  
      touchingTerritories.add("Iceland");        
      touchingTerritories.add("Scandinavia");      
      touchingTerritories.add("Northern Europe"); 
      touchingTerritories.add("Western Europe");     
      mapTerritory = new Territory("Great Britain",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();  
      touchingTerritories.add("North Africa");        
      touchingTerritories.add("Great Britain");      
      touchingTerritories.add("Northern Europe"); 
      touchingTerritories.add("Southern Europe");     
      mapTerritory = new Territory("Western Europe",touchingTerritories);
      territoryList.add(mapTerritory);

      touchingTerritories.clear(); 
      touchingTerritories.add("Ukraine"); 
      touchingTerritories.add("Middle East");
      touchingTerritories.add("North Africa");        
      touchingTerritories.add("Egypt");      
      touchingTerritories.add("Northern Europe"); 
      touchingTerritories.add("Western Europe");     
      mapTerritory = new Territory("Southern Europe",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear(); 
      touchingTerritories.add("Ukraine"); 
      touchingTerritories.add("Scandinavia");
      touchingTerritories.add("Great Britain");       
      touchingTerritories.add("Southern Europe"); 
      touchingTerritories.add("Western Europe");     
      mapTerritory = new Territory("Northern Europe",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear(); 
      touchingTerritories.add("Ukraine"); 
      touchingTerritories.add("Iceland");
      touchingTerritories.add("Great Britain");       
      touchingTerritories.add("Northern Europe");        
      mapTerritory = new Territory("Scandinavia",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Scandinavia");       
      touchingTerritories.add("Northern Europe"); 
      touchingTerritories.add("Southern Europe");   
      touchingTerritories.add("Afghanistan"); 
      touchingTerritories.add("Middle East"); 
      touchingTerritories.add("Ural");     
      mapTerritory = new Territory("Ukraine",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Southern Europe");
      touchingTerritories.add("Western Europe");       
      touchingTerritories.add("Egypt");   
      touchingTerritories.add("East Africa"); 
      touchingTerritories.add("Congo"); 
      touchingTerritories.add("Brazil");     
      mapTerritory = new Territory("North Africa",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Southern Europe");           
      touchingTerritories.add("Middle East");   
      touchingTerritories.add("East Africa");
      touchingTerritories.add("North Africa");     
      mapTerritory = new Territory("Egypt",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Madagascar");           
      touchingTerritories.add("South Africa");   
      touchingTerritories.add("Congo");
      touchingTerritories.add("North Africa"); 
      touchingTerritories.add("Egypt");    
      mapTerritory = new Territory("East Africa",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();              
      touchingTerritories.add("South Africa"); 
      touchingTerritories.add("North Africa"); 
      touchingTerritories.add("East Africa");    
      mapTerritory = new Territory("Congo",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();              
      touchingTerritories.add("Congo"); 
      touchingTerritories.add("Madagascar"); 
      touchingTerritories.add("East Africa");    
      mapTerritory = new Territory("South Africa",touchingTerritories);
      territoryList.add(mapTerritory);
      
      
      touchingTerritories.clear();             
      touchingTerritories.add("South Africa"); 
      touchingTerritories.add("East Africa");    
      mapTerritory = new Territory("Madagascar",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Ukraine");
      touchingTerritories.add("Afghanistan");
      touchingTerritories.add("India");             
      touchingTerritories.add("Southern Europe"); 
      touchingTerritories.add("Egypt");    
      mapTerritory = new Territory("Middle East",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Ukraine");
      touchingTerritories.add("Ural");
      touchingTerritories.add("India");             
      touchingTerritories.add("China"); 
      touchingTerritories.add("Middle East");    
      mapTerritory = new Territory("Afghanistan",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Ukraine");    
      touchingTerritories.add("Siberia");             
      touchingTerritories.add("China"); 
      touchingTerritories.add("Afghanistan");    
      mapTerritory = new Territory("Ural",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Ural");    
      touchingTerritories.add("Irkutsk");             
      touchingTerritories.add("China"); 
      touchingTerritories.add("Mongolia"); 
      touchingTerritories.add("Yakutsk");   
      mapTerritory = new Territory("Siberia",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();
      touchingTerritories.add("Ural");    
      touchingTerritories.add("Afghanistan");             
      touchingTerritories.add("India"); 
      touchingTerritories.add("Mongolia"); 
      touchingTerritories.add("Siam"); 
      touchingTerritories.add("Siberia");  
      mapTerritory = new Territory("China",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();    
      touchingTerritories.add("Afghanistan");            
      touchingTerritories.add("Middle East"); 
      touchingTerritories.add("Siam"); 
      touchingTerritories.add("China");  
      mapTerritory = new Territory("India",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();                
      touchingTerritories.add("Indonesia"); 
      touchingTerritories.add("India"); 
      touchingTerritories.add("China");  
      mapTerritory = new Territory("Siam",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();                
      touchingTerritories.add("Kamcharka"); 
      touchingTerritories.add("Irkutsk");
      touchingTerritories.add("Siberia");
      touchingTerritories.add("Japan"); 
      touchingTerritories.add("China");  
      mapTerritory = new Territory("Mongolia",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();                
      touchingTerritories.add("Kamcharka"); 
      touchingTerritories.add("Irkutsk");
      touchingTerritories.add("Mongolia");   
      mapTerritory = new Territory("Japan",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();                
      touchingTerritories.add("Kamcharka"); 
      touchingTerritories.add("Irkutsk");
      touchingTerritories.add("Siberia");
      touchingTerritories.add("Mongolia");   
      mapTerritory = new Territory("Irkutsk",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();                
      touchingTerritories.add("Kamcharka"); 
      touchingTerritories.add("Irkutsk");
      touchingTerritories.add("Siberia"); 
      mapTerritory = new Territory("Yakutsk",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();                
      touchingTerritories.add("Yakutsk"); 
      touchingTerritories.add("Irkutsk");
      touchingTerritories.add("Japan"); 
      touchingTerritories.add("Alaska");
      mapTerritory = new Territory("Kamchatka",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();                 
      touchingTerritories.add("New Guinea");
      touchingTerritories.add("Siam"); 
      touchingTerritories.add("Western Australia");
      mapTerritory = new Territory("Indonesia",touchingTerritories);
      territoryList.add(mapTerritory);
      
            
      touchingTerritories.clear();                 
      touchingTerritories.add("Indonesia"); 
      touchingTerritories.add("Western Australia");
      touchingTerritories.add("Eastern Australia");
      mapTerritory = new Territory("New Guinea",touchingTerritories);
      territoryList.add(mapTerritory);
      
      touchingTerritories.clear();                 
      touchingTerritories.add("New Guinea"); 
      touchingTerritories.add("Western Australia");
      mapTerritory = new Territory("Eastern Australia",touchingTerritories);
      territoryList.add(mapTerritory);


      touchingTerritories.clear();                 
      touchingTerritories.add("Indonesia"); 
      touchingTerritories.add("New Guinea");
      touchingTerritories.add("Eastern Australia");
      mapTerritory = new Territory("Western Australia",touchingTerritories);
      territoryList.add(mapTerritory);
      
      //Set the HashMap
      //player,units alloted at the initial setup of the game
      setupAllotmentMap.put(2,40);
      setupAllotmentMap.put(3,35);
      setupAllotmentMap.put(4,30);
      setupAllotmentMap.put(5,25);
      setupAllotmentMap.put(6,20);
    
      cashInAllotmentMap.put(0,4);
      cashInAllotmentMap.put(1,6);
      cashInAllotmentMap.put(2,8);
      cashInAllotmentMap.put(3,10);
      cashInAllotmentMap.put(4,12);
      cashInAllotmentMap.put(5,15);
      cashInAllotmentMap.put(6,20);
      cashInAllotmentMap.put(7,25);
      cashInAllotmentMap.put(8,30);
      cashInAllotmentMap.put(9,35);
      cashInAllotmentMap.put(10,40);
      cashInAllotmentMap.put(11,45);
      cashInAllotmentMap.put(12,50);
      cashInAllotmentMap.put(13,55);
      
      //Set up the continents
      //North America
      LinkedList<String> containingTerritories = new LinkedList<String>();
      containingTerritories.add("Alaska");
      containingTerritories.add("Northwest Territory");
      containingTerritories.add("Alberta");
      containingTerritories.add("Western United States");
      containingTerritories.add("Central America");
      containingTerritories.add("Eastern United States");
      containingTerritories.add("Ontario");
      containingTerritories.add("Greenland");
      containingTerritories.add("Quebec");     
      Continent mapContinent = new Continent("North America",containingTerritories,5);
      continentList.add(mapContinent); 
      
      //South America
      containingTerritories.clear();
      containingTerritories.add("Venezuela");
      containingTerritories.add("Brazil");
      containingTerritories.add("Peru");
      containingTerritories.add("Argentina");     
      mapContinent = new Continent("South America",containingTerritories,2);
      continentList.add(mapContinent);
      
      //...
      containingTerritories.clear();
      containingTerritories.add("North Africa");
      containingTerritories.add("Egypt");
      containingTerritories.add("Congo");
      containingTerritories.add("East Africa");
      containingTerritories.add("South Africa");
      containingTerritories.add("Madagascar");     
      mapContinent = new Continent("Africa",containingTerritories,3);
      continentList.add(mapContinent);  
      
      //...
      containingTerritories.clear();
      containingTerritories.add("Middle East");
      containingTerritories.add("Afghanistan");
      containingTerritories.add("India");
      containingTerritories.add("Ural");
      containingTerritories.add("Siberia");
      containingTerritories.add("China"); 
      containingTerritories.add("Siam");
      containingTerritories.add("Mongolia");
      containingTerritories.add("Irkutsk");
      containingTerritories.add("Yakutsk");
      containingTerritories.add("Japan");
      containingTerritories.add("Kamchatka");          
      mapContinent = new Continent("Asia",containingTerritories,7);
      continentList.add(mapContinent); 
      
      containingTerritories.clear();      
      containingTerritories.add("Scandinavia"); 
      containingTerritories.add("Ukraine");
      containingTerritories.add("Iceland");
      containingTerritories.add("Great Britain");
      containingTerritories.add("Northern Europe");
      containingTerritories.add("Southern Europe");
      containingTerritories.add("Western Europe");          
      mapContinent = new Continent("Europe",containingTerritories,5);
      continentList.add(mapContinent); 
      
      containingTerritories.clear();      
      containingTerritories.add("New Guinea");
      containingTerritories.add("Indonesia");
      containingTerritories.add("Eastern Australia");
      containingTerritories.add("Western Australia");          
      mapContinent = new Continent("Australia",containingTerritories,2);
      continentList.add(mapContinent);
      
   }
   
   public void AddPlayer(String teamName,Color teamColor){
      PlayerStatus newPlayer = new PlayerStatus(teamName,teamColor);
      playerList.add(newPlayer);
   }
   
   public PlayerStatus getCurrentPlayer(){
       return playerList.get(currentActivePlayer);
   }
   
   public void InitialAllotment(){
      //Determine how many troops based on the number of players
      int calculatedAllotment = setupAllotmentMap.get(playerList.size());
      for(PlayerStatus player : playerList){
         player.currentAllottedUnits = calculatedAllotment;
      }
   }
   
   public void developerSetMapInitial(){
      int playerNum = 0;
      for(Territory t: territoryList){
         PlayerStatus player = playerList.get(playerNum);
         t.armyUnitCount = 1;
         t.currentPlayerColor = player.teamColor;
         
         if(playerNum == 0){
            playerNum = 1;
         }
         else if(playerNum == 1){
            playerNum = 0;
         }
      }
   }
   
   public void developerSkipToEnd(){
      
      PlayerStatus player = playerList.get(0);
      for(Territory t: territoryList){         
         t.armyUnitCount = 1;
         t.currentPlayerColor = player.teamColor;         
      }
      //Establish final battle ground
      //Set player A      
      boolean successA = PlaceUnits("Eastern Australia",player,10);
      //Set player B
      player = playerList.get(1);
      for(Territory t: territoryList){ 
         if(t.nameOfTerritory == "Western Australia"){
            t.armyUnitCount = 10;
            t.currentPlayerColor = player.teamColor;
         }               
      }
   }

   
   
   
   
   public boolean SetupPlaceSingleUnitEmptyRule(String chosenTerritory,Color playerColor){
      //Ensure the unit being placed is for a empty territory
      boolean addSuccess = true;
      for(Territory t : territoryList){
         if(t.nameOfTerritory == chosenTerritory){
            //Determine if there is already a unit
            if(t.armyUnitCount > 0){
               //return error
               addSuccess = false;
            }
            else{
               //add the unit
               t.armyUnitCount = 1;
               t.currentPlayerColor = playerColor;
               //go ahead and return
               return addSuccess;
            }
         }
         
      }
      //should return false if this part is reached
      return addSuccess;
   }
   
   
   public boolean PlaceUnit(String chosenTerritory,Color playerColor){
      //Ensure the unit being placed is for matching color or empty(conquer)
      boolean addSuccess = true;
      for(Territory t : territoryList){
         if(t.nameOfTerritory == chosenTerritory){
            //Determine if there is already a unit
            if(t.armyUnitCount > 0){
               //Determine if the team color is the same
               if(t.currentPlayerColor == playerColor){
                  //add the unit, main gui handles player unit count allotment
                  t.armyUnitCount++;
                  return addSuccess;
               }
               else{
                  //return error
                  addSuccess = false;
               }
               
            }
            else{
               //add the unit
               t.armyUnitCount = 1;
               t.currentPlayerColor = playerColor;
               //go ahead and return
               return addSuccess;
            }
         }
         
      }
      //should return false if this part is reached
      return addSuccess;
   }
   
   public boolean PlaceUnits(String chosenTerritory,PlayerStatus player,int count){
      //Ensure the unit being placed is for matching color or empty(conquer)
      boolean addSuccess = true;
      for(Territory t : territoryList){
         if(t.nameOfTerritory == chosenTerritory){
            //Determine if there is already a unit
            if(t.armyUnitCount > 0){
               //Determine if the team color is the same
               if(t.currentPlayerColor == player.teamColor){
                  //determine if the player actually has enough
                  if(player.currentAllottedUnits >= count)
                  {
                     //add the unit, main gui handles player unit count allotment
                     t.armyUnitCount = t.armyUnitCount + count;
                     return addSuccess;
                  }
                  else{
                     addSuccess = false;
                  }
                  
               }
               else{
                  //return error
                  addSuccess = false;
               }
               
            }
            else{
               //no units there
               //add the unit
               t.armyUnitCount = t.armyUnitCount + count;
               t.currentPlayerColor = player.teamColor;
               //go ahead and return
               return addSuccess;
            }
         }
         
      }
      //should return false if this part is reached
      return addSuccess;
   }
   


   public boolean RemoveUnits(String chosenTerritory,PlayerStatus player,int count){
      //Ensure the units being removed are for matching color and at least one remains.
      for(Territory t : territoryList){
         if(t.nameOfTerritory == chosenTerritory){
            //Determine if the player has ownership
            if(t.currentPlayerColor == player.teamColor){
               //Determine if there are suffiecint units in the territory
               if((t.armyUnitCount - count) >= 1){
                  //Valid
                  //Preform action
                  t.armyUnitCount = t.armyUnitCount - count;
                  return true;
               }
               else{
                  //fail
                  return false;
               }
            }
            else{
               //fail
               return false;
            }
         }
         
      }
      //fail
      return false;
   }


   


   //Determine if the map has been fully set at the start of the game
   public boolean fullyOccupied(){      
      for(Territory t : territoryList){
         if(t.armyUnitCount == 0){         
               return false;
            }         
      }      
      return true;
   }
   
   public void nextPlayer(){
      int listSize = playerList.size();
      currentActivePlayer++;
      if(currentActivePlayer >= listSize){
         //wrap
         currentActivePlayer = 0;
      }
   }
   
   

   
   
   public boolean isGameOver(){
      //Go through all the territories and see if there is more than one teamColor
  
      LinkedList<Color> teamColorList = new LinkedList<Color>(); 
      for(Territory t : territoryList){
         //At the end of an attack ignore the zero with a team color so the very last dice roll will win the game
         if(t.armyUnitCount > 0){
            //Dont count default empty color
            if(t.currentPlayerColor != Color.ALICEBLUE){
               //Dont add a color that is already in there
               boolean alreadyInList = false;
               for(Color c : teamColorList){
                  if(t.currentPlayerColor == c){
                     alreadyInList = true;
                  }
               }
               //Add the unique color
               if(alreadyInList == false){
                  teamColorList.add(t.currentPlayerColor);
               }                  
            }
         }         
      }
      //Determine if there are still two or more teams battling
      if(teamColorList.size() > 1){
         return false;
      } 
      else{
         return true;
      }     
   }

   

   
   public int calculateTerritoryAllotment(PlayerStatus player){
      //Take the players owned territories count, divide by 3, truncate to whole number
      int territories = 0;
      for(Territory t : territoryList){
         if(t.currentPlayerColor == player.teamColor){
            territories++;
         }
      }
      //returns truncated quotient
      int allotedArmies = Math.floorDiv(territories,3);
      return allotedArmies;
   }
   
   
   public int calculateContinentAllotment(PlayerStatus player){
      //Get a list of territores that the player controls, go through each continent's territyor list and do a linkedlist base class's comparAll method
      int allotedArmies = 0;
      //create a list of controlled territories
      LinkedList<Territory> playerTerritoryList = new LinkedList<Territory>();
      for(Territory t : territoryList){
         if(t.currentPlayerColor == player.teamColor){
            playerTerritoryList.add(t);
         }
      }
      //convert the list of territories to a list of territory string names
      LinkedList<String> playerTerritoryNames = new LinkedList<String>();
      for(Territory t : playerTerritoryList){
         playerTerritoryNames.add(t.nameOfTerritory);
      }

      
      //Go through each continent and determine if the player has control, allot armies if they do
      for(Continent c : continentList){
         boolean hasControl = playerTerritoryNames.containsAll(c.containingTerritoryNames);
         if(hasControl == true){
            allotedArmies = allotedArmies + c.bonusAllotment;
            
         }
      }
      return allotedArmies;
   }
   
   
   public int cashInCardSet(){
      //Increments every time a set is cached in from a player
      int units = cashInAllotmentMap.get(cardSubmissionCount);      
      cardSubmissionCount++;
      return units;
   }
   
   public Territory getTerritoryByName(String name){
      Territory nullT = new Territory("null");
      for(Territory t : territoryList){
         if(t.nameOfTerritory == name){
            return t;
         }
      }
      return nullT;
   }
   
   public boolean attackingSelf(PlayerStatus player, Territory attackThis){
      //Loop through the owned territories and ensure the attackThis territory is not in that list
      for(Territory t : territoryList){
         if(t.nameOfTerritory == attackThis.nameOfTerritory){
            //match, determine friendly fire
            if(t.currentPlayerColor == player.teamColor){
               return true;
            }
         }
      }
      return false;
   }
   
   public boolean validateAttackFrom(PlayerStatus player, Territory attackFrom, int dice){
      //Ensure the territory is owned by the player, ensure there is at least one unit left behind
      
      //ownership
      boolean owned = false;
      for(Territory t : territoryList){
         if(t.nameOfTerritory == attackFrom.nameOfTerritory){
            //match, determine ownership
            if(t.currentPlayerColor == player.teamColor){
               owned = true;               
            }
         }
      }
      if(owned == false){
         //return error
         return false;
      }
      
      //dice valid? Must leave at least one unit behind
      if(((attackFrom.armyUnitCount - 1) >= dice) && (dice <= 3)){
         //Valid dice
         
         //Check for touching **Does not work for some reason. I think it has something to do with the way the territories are created in the GameStatus object.
         // boolean touching = false;
//          for(String tName : attackFrom.touchingTerritoryNames){
//             if(tName == player.currentAttackThisTerritory.nameOfTerritory){
//                touching = true;
//             }
//          }
//          
//          return touching;
            return true;
      }
      else{
         return false;
      }
   }
   
   
   public int getDefenderDiceCount(Territory attackThis){
      for(Territory t : territoryList){
         if(t.nameOfTerritory == attackThis.nameOfTerritory){
            if(t.armyUnitCount == 0){
               return 0;
            }
            else if(t.armyUnitCount == 1)
            {
               return 1;
            }
            else if(t.armyUnitCount == 2)
            {
               return 2;
            }
            else if(t.armyUnitCount > 2)
            {
               return 2;
            }
         }
      }
      return 0;
   }
   
   
      
   
   public void rollDiceImg(PlayerStatus player,ImageView[] attackerDiceImgViews,ImageView[] defenderDiceImgViews){
      //simulates battle dice roll, updates game status win loss variables, updates territories
      LinkedList<String> attackerDiceVal = new LinkedList<String>();
      LinkedList<String> defenderDiceVal = new LinkedList<String>();
      for(ImageView view: attackerDiceImgViews) {
         int number = new DiceRoll().diceAnimation(view);
         attackerDiceVal.add(String.valueOf(number));
      }
       for(ImageView view: defenderDiceImgViews) {
         int number = new DiceRoll().diceAnimation(view);
         defenderDiceVal.add(String.valueOf(number));
      }
      CompareDice(attackerDiceVal,defenderDiceVal);
      
      //RollDice(int attackerDiceQuantity, int defenderDiceQuantity)
      // myDiceStatus.RollDice(player.diceToRoll, defenderDiceCount);
//       diceResultDefenderLoss = myDiceStatus.getAttackerLossCount();
//       diceResultAttackerLoss = myDiceStatus.getDefenderLossCount();
      
      //update territories
      for(Territory t : territoryList){
         //update the attack from with any losses
         if(t.nameOfTerritory == player.currentAttackFromTerritory.nameOfTerritory){
            t.armyUnitCount = t.armyUnitCount - diceResultAttackerLoss;            
         }
         //update the attack this with any losses
         if(t.nameOfTerritory == player.currentAttackThisTerritory.nameOfTerritory){
            t.armyUnitCount = t.armyUnitCount - diceResultDefenderLoss; 
            //update the color if conquerred to attacking team color
            if(t.armyUnitCount <= 0){
               t.currentPlayerColor = player.teamColor;
            }           
         }

      }
   }
   
   
   private void CompareDice(LinkedList<String> attackerDice,LinkedList<String> defenderDice){
      //Instantiate reused objects
      int attackerHighestDiceVal = 0;
      int defenderHighestDiceVal = 0;
      int highestDiceIndex = 0;
      int diceVal1 = 0;
      int diceVal2 = 0;
      int diceVal3 = 0;
      int indexer = 0;
      
      diceResultAttackerLoss = 0;
      diceResultDefenderLoss = 0;
      
      //Infinite loop will exit when all dice have been compared
      while(true){     
      
      
          //determine if there are remaining dice to compare
           if(attackerDice.size() <= 0 || defenderDice.size() <= 0){
            return;
         }
         
         //Reset
         indexer = 0;
         
         //---Find the highest attacker die
         //Get all the dice
         while(indexer < attackerDice.size()){
         if(indexer == 0){
            diceVal1 = Integer.parseInt(attackerDice.get(indexer));
         }
         else if(indexer == 1){
            diceVal2 = Integer.parseInt(attackerDice.get(indexer));
         }
         else if(indexer == 2){
            diceVal3 = Integer.parseInt(attackerDice.get(indexer));
         }
         indexer++;         
      }
      //Use conditional sorting
      if(attackerDice.size() == 3){
         if(diceVal1 > diceVal2 && diceVal1 > diceVal3){
            highestDiceIndex = 0;
         }
         else if(diceVal2 > diceVal1 && diceVal2 > diceVal3){
            highestDiceIndex = 1;
         }
         else if(diceVal3 > diceVal1 && diceVal3 > diceVal2){
            highestDiceIndex = 2;
         }
         else if(diceVal1 == diceVal2){
            highestDiceIndex = 0;
         }
         else if(diceVal1 == diceVal3){
            highestDiceIndex = 0;
         }
         else if(diceVal2 == diceVal3){
            highestDiceIndex = 1;
         }

      }
      else if(attackerDice.size() == 2){
         if(diceVal1 > diceVal2){
            highestDiceIndex = 0;
         }
         else if(diceVal2 > diceVal1){
            highestDiceIndex = 1;
         }
         else if(diceVal1 == diceVal2){
            highestDiceIndex = 0;
         }
      }
      else if(attackerDice.size() == 1){
         highestDiceIndex = 0;
      }
      attackerHighestDiceVal = Integer.parseInt(attackerDice.get(highestDiceIndex));
      //remove the dice
      attackerDice.remove(highestDiceIndex);

      //---Find the highest defender die
      indexer = 0;
      //Get all the dice
      while(indexer < defenderDice.size()){
         if(indexer == 0){
            diceVal1 = Integer.parseInt(defenderDice.get(indexer));
         }
         else if(indexer == 1){
            diceVal2 = Integer.parseInt(defenderDice.get(indexer));
         }
         else if(indexer == 2){
            diceVal3 = Integer.parseInt(defenderDice.get(indexer));
         }
         indexer++;         
      }
      //Use conditional sorting
      if(defenderDice.size() == 3){
         if(diceVal1 > diceVal2 && diceVal1 > diceVal3){
            highestDiceIndex = 0;
         }
         else if(diceVal2 > diceVal1 && diceVal2 > diceVal3){
            highestDiceIndex = 1;
         }
         else if(diceVal3 > diceVal1 && diceVal3 > diceVal2){
            highestDiceIndex = 2;
         }
         else if(diceVal1 == diceVal2){
            highestDiceIndex = 0;
         }
         else if(diceVal1 == diceVal3){
            highestDiceIndex = 0;
         }
         else if(diceVal2 == diceVal3){
            highestDiceIndex = 1;
         }
      }
      else if(defenderDice.size() == 2){
         if(diceVal1 > diceVal2){
            highestDiceIndex = 0;
         }
         else if(diceVal2 > diceVal1){
            highestDiceIndex = 1;
         }
         else if(diceVal1 == diceVal2){
            highestDiceIndex = 0;
         }
      }
      else if(defenderDice.size() == 1){
         highestDiceIndex = 0;
      }
      defenderHighestDiceVal = Integer.parseInt(defenderDice.get(highestDiceIndex));
      //remove the dice
      defenderDice.remove(highestDiceIndex);

      //Determine who looses a unit
      if(defenderHighestDiceVal > attackerHighestDiceVal){
         diceResultAttackerLoss++;
      }
      else if(defenderHighestDiceVal == attackerHighestDiceVal){
         diceResultAttackerLoss++;
      }
      else{
         diceResultDefenderLoss++;
      }

         
         
         
         
      }    
      
    
      
        
      
      
      
      
   }

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
