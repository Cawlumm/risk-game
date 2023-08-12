//Coded by the Risk team CPT 237-W34
//3/7/2023
import java.util.LinkedList;

//Checks if a chosen attack territory is actually touching
public class Continent {
   public String nameOfContinent;
   public LinkedList<String> containingTerritoryNames = new LinkedList<String>();
   public int bonusAllotment = 0;

      
  
   
	public Continent(String continentName,LinkedList<String> containingTerritories,int bonusAllotment){
      this.nameOfContinent = continentName;
      this.containingTerritoryNames = containingTerritories; 
      this.bonusAllotment = bonusAllotment;       
   }   
   
   public void isControlled(String territoryName){
      //return touchingTerritoryNames.contains(territoryName);
   
   }
   
   
   
}