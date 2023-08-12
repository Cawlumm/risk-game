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
import java.util.ArrayList;
//Coded by the Risk team CPT 237-W34
//3/7/2023

public class Card {

   private ArrayList<String> choices = new ArrayList<String>();
   
      
   public String cardTypeString;
   public int cardTypeInt;
   
	public Card(String type){
      choices.add("infantry"); //0
      choices.add("cavalry");  //1
      choices.add("cannon");   //2
      choices.add("wild card");//3
      cardTypeString = type;
      cardTypeInt = choices.indexOf(type);           
   }   
}