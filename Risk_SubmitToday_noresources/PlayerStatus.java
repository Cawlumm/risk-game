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
import javafx.scene.paint.Color;


public class PlayerStatus {	
	
   public String teamName;
	//public String teamColor;
   public Color teamColor;
   public int currentAllottedUnits = 0;
   public Territory currentAttackFromTerritory;
   public Territory currentAttackThisTerritory;
   public int diceToRoll = 0;
   public boolean conquereedLastTurn = false;
   public LinkedList<Card> myCards = new LinkedList<Card>();
   
   //private LinkedList<Card> myCards = new LinkedList<Card>();
   
   //Pass in a valid javafx font color option
   //Possibly filter for any bad contrasting colors that dont display well on the map
	public PlayerStatus(String teamName,Color teamColor){
      this.teamName = teamName;
      this.teamColor = teamColor;
            
   }
   
}
