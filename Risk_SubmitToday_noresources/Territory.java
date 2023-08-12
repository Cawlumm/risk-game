//Coded by the Risk team CPT 237-W34
//3/7/2023


import java.util.LinkedList;
import javafx.scene.paint.Color;

//Checks if a chosen attack territory is actually touching
public class Territory {
   public String nameOfTerritory;
   public LinkedList<String> touchingTerritoryNames;
   public int armyUnitCount = 0;
   //public String currentPlayerColor = "";
   
   public Color currentPlayerColor = Color.ALICEBLUE;
  
   
	public Territory(String territoryName,LinkedList<String> touchingTerritories){
      nameOfTerritory = territoryName;
      touchingTerritoryNames = touchingTerritories;         
   }   
   public Territory(String territoryName){
      //the null representation
      nameOfTerritory = territoryName;
      touchingTerritoryNames = new LinkedList<String>();         
   } 
   
   public boolean isTouching(String territoryName){
      return touchingTerritoryNames.contains(territoryName);
   
   }
   
   
   
}