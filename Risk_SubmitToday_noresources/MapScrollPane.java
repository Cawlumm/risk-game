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
//Coded by the Risk team CPT 237-W34
//3/7/2023
import javafx.scene.control.ScrollPane;
import javafx.stage.WindowEvent;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class MapScrollPane extends ScrollPane {	

   private double startX, startY;
   private TranslateTransition transition;
	
	//Territory Text objects
   Text textAlaska;
   Text textNorthwestTerritory;
   Text textAlberta;
   Text textOntario;
   Text textGreenland;
   Text textQuebec;
   Text textWesternUnitedStates;
   Text textEasternUnitedStates;
   Text textCentralAmerica;
   Text textVenezuela;
   Text textBrazil;
   Text textPeru;
   Text textArgentina;
   Text textIceland;
   Text textGreatBritain;
   Text textWesternEurope;
   Text textNorthernEurope;
   Text textSouthernEurope;
   Text textUkraine;
   Text textScandinavia;
   Text textNorthAfrica;
   Text textEgypt;
   Text textEastAfrica;
   Text textCongo;
   Text textSouthAfrica;
   Text textMadagascar;
   Text textMiddleEast;
   Text textAfghanistan;
   Text textUral;
   Text textSiberia;
   Text textYakutsk;
   Text textKamchatka;
   Text textIrkutsk;
   Text textMongolia;
   Text textChina;
   Text textIndia;
   Text textSiam;
   Text textJapan;
   Text textIndonesia;
   Text textNewGuinea;
   Text textEasternAustralia;
   Text textWesternAustralia;
   
   Image mapImage;
   ImageView imageViewMap;
   Pane mapPane;
   

	public MapScrollPane(){
      super();
      //Invoke the super classes constructor
      
   
      
      
   }
   
   public void renderMap(){
      //Create the map image object
      mapImage = new Image("images/Map_W3921_H2645.jpg");
      //Create an imageView object of that map
      imageViewMap = new ImageView(mapImage);
      //Create a pane to hold the map image with text objects
      mapPane = new Pane();      
      
      //Create text objects on territories
      textAlaska = new Text(360,570,"default");
      textAlaska.setFill(Color.RED);
      textAlaska.setFont(Font.font("Times",20));
      textAlaska.setText("0");
      
      textNorthwestTerritory = new Text(710,610,"default");
      textNorthwestTerritory.setFill(Color.RED);
      textNorthwestTerritory.setFont(Font.font("Times",20));
      textNorthwestTerritory.setText("0");
      
      textAlberta = new Text(710,780,"default");
      textAlberta.setFill(Color.RED);
      textAlberta.setFont(Font.font("Times",20));
      textAlberta.setText("0");
     
      textOntario = new Text(960,830,"default");
      textOntario.setFill(Color.RED);
      textOntario.setFont(Font.font("Times",20));
      textOntario.setText("0");
      
      textGreenland = new Text(1530,420,"default");
      textGreenland.setFill(Color.RED);
      textGreenland.setFont(Font.font("Times",20));
      textGreenland.setText("0");
      
      textQuebec = new Text(1260,830,"default");
      textQuebec.setFill(Color.RED);
      textQuebec.setFont(Font.font("Times",20));
      textQuebec.setText("0");
      
      textWesternUnitedStates = new Text(720,1010,"default");
      textWesternUnitedStates.setFill(Color.RED);
      textWesternUnitedStates.setFont(Font.font("Times",20));
      textWesternUnitedStates.setText("0");
     
      textEasternUnitedStates = new Text(1000,1090,"default");
      textEasternUnitedStates.setFill(Color.RED);
      textEasternUnitedStates.setFont(Font.font("Times",20));
      textEasternUnitedStates.setText("0");
      
      textCentralAmerica = new Text(750,1230,"default");
      textCentralAmerica.setFill(Color.RED);
      textCentralAmerica.setFont(Font.font("Times",20));
      textCentralAmerica.setText("0");
      
      textVenezuela = new Text(1060,1490,"default");
      textVenezuela.setFill(Color.RED);
      textVenezuela.setFont(Font.font("Times",20));
      textVenezuela.setText("0");
     
      textBrazil = new Text(1290,1710,"default");
      textBrazil.setFill(Color.RED);
      textBrazil.setFont(Font.font("Times",20));
      textBrazil.setText("0");
     
      textPeru = new Text(1090,1810,"default");
      textPeru.setFill(Color.RED);
      textPeru.setFont(Font.font("Times",20));
      textPeru.setText("0");
      
      textArgentina = new Text(1140,2030,"default");
      textArgentina.setFill(Color.RED);
      textArgentina.setFont(Font.font("Times",20));
      textArgentina.setText("0");
      
      textIceland = new Text(1640,700,"default");
      textIceland.setFill(Color.RED);
      textIceland.setFont(Font.font("Times",20));
      textIceland.setText("0");
     
      textGreatBritain = new Text(1610,890,"default");
      textGreatBritain.setFill(Color.RED);
      textGreatBritain.setFont(Font.font("Times",20));
      textGreatBritain.setText("0");
     
      textWesternEurope = new Text(1750,1180,"default");
      textWesternEurope.setFill(Color.RED);
      textWesternEurope.setFont(Font.font("Times",20));
      textWesternEurope.setText("0");
     
      textNorthernEurope = new Text(1950,970,"default");
      textNorthernEurope.setFill(Color.RED);
      textNorthernEurope.setFont(Font.font("Times",20));
      textNorthernEurope.setText("0");
      
      textSouthernEurope = new Text(2060,1100,"default");
      textSouthernEurope.setFill(Color.RED);
      textSouthernEurope.setFont(Font.font("Times",20));
      textSouthernEurope.setText("0");
      
      textUkraine = new Text(2300,840,"default");
      textUkraine.setFill(Color.RED);
      textUkraine.setFont(Font.font("Times",20));
      textUkraine.setText("0");
      
      textScandinavia = new Text(2000,630,"default");
      textScandinavia.setFill(Color.RED);
      textScandinavia.setFont(Font.font("Times",20));
      textScandinavia.setText("0");
      
      textNorthAfrica = new Text(1820,1550,"default");
      textNorthAfrica.setFill(Color.RED);
      textNorthAfrica.setFont(Font.font("Times",20));
      textNorthAfrica.setText("0");
      
      textEgypt = new Text(2070,1410,"default");
      textEgypt.setFill(Color.RED);
      textEgypt.setFont(Font.font("Times",20));
      textEgypt.setText("0");
     
      textEastAfrica = new Text(2250,1710,"default");
      textEastAfrica.setFill(Color.RED);
      textEastAfrica.setFont(Font.font("Times",20));
      textEastAfrica.setText("0");
      
      textCongo = new Text(2080,1850,"default");
      textCongo.setFill(Color.RED);
      textCongo.setFont(Font.font("Times",20));
      textCongo.setText("0");
      
      textSouthAfrica = new Text(2100,2160,"default");
      textSouthAfrica.setFill(Color.RED);
      textSouthAfrica.setFont(Font.font("Times",20));
      textSouthAfrica.setText("0");
      
      textMadagascar = new Text(2340,2200,"default");
      textMadagascar.setFill(Color.RED);
      textMadagascar.setFont(Font.font("Times",20));
      textMadagascar.setText("0");
     
      textMiddleEast = new Text(2350,1300,"default");
      textMiddleEast.setFill(Color.RED);
      textMiddleEast.setFont(Font.font("Times",20));
      textMiddleEast.setText("0");
      
      textAfghanistan = new Text(2570,1050,"default");
      textAfghanistan.setFill(Color.RED);
      textAfghanistan.setFont(Font.font("Times",20));
      textAfghanistan.setText("0");
     
      textUral = new Text(2650,770,"default");
      textUral.setFill(Color.RED);
      textUral.setFont(Font.font("Times",20));
      textUral.setText("0");
      
      textSiberia = new Text(2820,600,"default");
      textSiberia.setFill(Color.RED);
      textSiberia.setFont(Font.font("Times",20));
      textSiberia.setText("0");
     
      textYakutsk = new Text(3170,570,"default");
      textYakutsk.setFill(Color.RED);
      textYakutsk.setFont(Font.font("Times",20));
      textYakutsk.setText("0");
      
      textKamchatka = new Text(3520,600,"default");
      textKamchatka.setFill(Color.RED);
      textKamchatka.setFont(Font.font("Times",20));
      textKamchatka.setText("0");
      
      textIrkutsk = new Text(3110,820,"default");
      textIrkutsk.setFill(Color.RED);
      textIrkutsk.setFont(Font.font("Times",20));
      textIrkutsk.setText("0");
      
      textMongolia = new Text(3130,1030,"default");
      textMongolia.setFill(Color.RED);
      textMongolia.setFont(Font.font("Times",20));
      textMongolia.setText("0");
      
      textChina = new Text(3100,1300,"default");
      textChina.setFill(Color.RED);
      textChina.setFont(Font.font("Times",20));
      textChina.setText("0");
      
      textIndia = new Text(2770,1460,"default");
      textIndia.setFill(Color.RED);
      textIndia.setFont(Font.font("Times",20));
      textIndia.setText("0");
      
      textSiam = new Text(3080,1540,"default");
      textSiam.setFill(Color.RED);
      textSiam.setFont(Font.font("Times",20));
      textSiam.setText("0");
      
      textJapan = new Text(3480,1120,"default");
      textJapan.setFill(Color.RED);
      textJapan.setFont(Font.font("Times",20));
      textJapan.setText("0");
      
      textIndonesia = new Text(3245,1830,"default");
      textIndonesia.setFill(Color.RED);
      textIndonesia.setFont(Font.font("Times",20));
      textIndonesia.setText("0");
      
      textNewGuinea = new Text(3560,1900,"default");
      textNewGuinea.setFill(Color.RED);
      textNewGuinea.setFont(Font.font("Times",20));
      textNewGuinea.setText("0");
     
      textEasternAustralia = new Text(3510,2160,"default");
      textEasternAustralia.setFill(Color.RED);
      textEasternAustralia.setFont(Font.font("Times",20));
      textEasternAustralia.setText("0");
      
      textWesternAustralia = new Text(3330,2270,"default");
      textWesternAustralia.setFill(Color.RED);
      textWesternAustralia.setFont(Font.font("Times",20));
      textWesternAustralia.setText("0");
     
      //Add image and Text objects to the mappane    
      mapPane.getChildren().addAll(imageViewMap,textAlaska,textNorthwestTerritory,textAlberta,textOntario,textGreenland);
      mapPane.getChildren().addAll(textQuebec,textWesternUnitedStates,textEasternUnitedStates,textCentralAmerica,textVenezuela);
      mapPane.getChildren().addAll(textBrazil,textPeru,textArgentina,textIceland,textGreatBritain,textWesternEurope,textNorthernEurope);
      mapPane.getChildren().addAll(textSouthernEurope,textUkraine,textScandinavia,textNorthAfrica, textEgypt,textEastAfrica);
      mapPane.getChildren().addAll(textCongo,textSouthAfrica,textMadagascar,textMiddleEast,textAfghanistan,textUral,textSiberia);
      mapPane.getChildren().addAll(textYakutsk,textKamchatka,textIrkutsk,textMongolia,textChina,textIndia,textSiam,textJapan);
      mapPane.getChildren().addAll(textIndonesia,textNewGuinea,textEasternAustralia,textWesternAustralia);
      
      //invoke super methods to add the mapPane and set syles
      super.setContent(mapPane);    
      //Set the size of the scroll pane so that out of bounds content is scrollable
      super.setPrefWidth(1000);
      super.setPrefHeight(800);
      super.setStyle("-fx-padding: 10;" 
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 10;" 
                + "-fx-border-insets: 5;" 
                + "-fx-border-radius: 5;" 
                + "-fx-border-color: black;");
      super.setPannable(true);
   }
   
   
 	
		
	public void testMethods() {
		      
      setMapTextObjectColor("Alaska",Color.ALICEBLUE);    
      setMapTextObjectColor("Northwest Territory",Color.ALICEBLUE);
      setMapTextObjectColor("Alberta",Color.ALICEBLUE);
      setMapTextObjectColor("Ontario",Color.ALICEBLUE);
      setMapTextObjectColor("Greenland",Color.ALICEBLUE);   
      setMapTextObjectColor("Quebec",Color.ALICEBLUE);
      setMapTextObjectColor("Western United States",Color.ALICEBLUE);
      setMapTextObjectColor("Eastern United States",Color.ALICEBLUE);
      setMapTextObjectColor("Central America",Color.ALICEBLUE);
      setMapTextObjectColor("Venezuela",Color.ALICEBLUE);
      setMapTextObjectColor("Brazil",Color.ALICEBLUE);
      setMapTextObjectColor("Peru",Color.ALICEBLUE);
      setMapTextObjectColor("Argentina",Color.ALICEBLUE);
      setMapTextObjectColor("Iceland",Color.ALICEBLUE);
      setMapTextObjectColor("Great Britain",Color.ALICEBLUE);
      setMapTextObjectColor("Western Europe",Color.ALICEBLUE);
      setMapTextObjectColor("Northern Europe",Color.ALICEBLUE);
      setMapTextObjectColor("Southern Europe",Color.ALICEBLUE);
      setMapTextObjectColor("Ukraine",Color.ALICEBLUE);
      setMapTextObjectColor("Scandinavia",Color.ALICEBLUE);
      setMapTextObjectColor("North Africa",Color.ALICEBLUE);
      setMapTextObjectColor("Egypt",Color.ALICEBLUE);
      setMapTextObjectColor("East Africa",Color.ALICEBLUE);
      setMapTextObjectColor("Congo",Color.ALICEBLUE);
      setMapTextObjectColor("South Africa",Color.ALICEBLUE);
      setMapTextObjectColor("Madagascar",Color.ALICEBLUE);
      setMapTextObjectColor("Middle East",Color.ALICEBLUE);
      setMapTextObjectColor("Afghanistan",Color.ALICEBLUE);
      setMapTextObjectColor("Ural",Color.ALICEBLUE);
      setMapTextObjectColor("Siberia",Color.ALICEBLUE);
      setMapTextObjectColor("Yakutsk",Color.ALICEBLUE);
      setMapTextObjectColor("Kamchatka",Color.ALICEBLUE);
      setMapTextObjectColor("Irkutsk",Color.ALICEBLUE);
      setMapTextObjectColor("Mongolia",Color.ALICEBLUE);
      setMapTextObjectColor("China",Color.ALICEBLUE);
      setMapTextObjectColor("India",Color.ALICEBLUE);
      setMapTextObjectColor("Siam",Color.ALICEBLUE);
      setMapTextObjectColor("Japan",Color.ALICEBLUE);
      setMapTextObjectColor("Indonesia",Color.ALICEBLUE);
      setMapTextObjectColor("New Guinea",Color.ALICEBLUE);
      setMapTextObjectColor("Eastern Australia",Color.ALICEBLUE);
      setMapTextObjectColor("Western Australia",Color.ALICEBLUE);
      
      
      
      setMapTextObjectText("Alaska","0");    
      setMapTextObjectText("Northwest Territory","0");
      setMapTextObjectText("Alberta","0");
      setMapTextObjectText("Ontario","0");
      setMapTextObjectText("Greenland","0");   
      setMapTextObjectText("Quebec","0");
      setMapTextObjectText("Western United States","0");
      setMapTextObjectText("Eastern United States","0");
      setMapTextObjectText("Central America","0");
      setMapTextObjectText("Venezuela","0");
      setMapTextObjectText("Brazil","0");
      setMapTextObjectText("Peru","0");
      setMapTextObjectText("Argentina","0");
      setMapTextObjectText("Iceland","0");
      setMapTextObjectText("Great Britain","0");
      setMapTextObjectText("Western Europe","0");
      setMapTextObjectText("Northern Europe","0");
      setMapTextObjectText("Southern Europe","0");
      setMapTextObjectText("Ukraine","0");
      setMapTextObjectText("Scandinavia","0");
      setMapTextObjectText("North Africa","0");
      setMapTextObjectText("Egypt","0");
      setMapTextObjectText("East Africa","0");
      setMapTextObjectText("Congo","0");
      setMapTextObjectText("South Africa","0");
      setMapTextObjectText("Madagascar","0");
      setMapTextObjectText("Middle East","0");
      setMapTextObjectText("Afghanistan","0");
      setMapTextObjectText("Ural","0");
      setMapTextObjectText("Siberia","0");
      setMapTextObjectText("Yakutsk","0");
      setMapTextObjectText("Kamchatka","0");
      setMapTextObjectText("Irkutsk","0");
      setMapTextObjectText("Mongolia","0");
      setMapTextObjectText("China","0");
      setMapTextObjectText("India","0");
      setMapTextObjectText("Siam","0");
      setMapTextObjectText("Japan","0");
      setMapTextObjectText("Indonesia","0");
      setMapTextObjectText("New Guinea","0");
      setMapTextObjectText("Eastern Australia","0");
      setMapTextObjectText("Western Australia","0");
	}

   
   public void setMapTextObjectColor(String territory,Color targetColor)
   {
      if(territory == "Alaska")
      {
         textAlaska.setFill(targetColor);
      }
      else if(territory == "Northwest Territory")
      {
         textNorthwestTerritory.setFill(targetColor);
      }
      else if(territory == "Alberta")
      {
         textAlberta.setFill(targetColor);
      }
      else if(territory == "Ontario")
      {
         textOntario.setFill(targetColor);
      }
      else if(territory == "Greenland")
      {
         textGreenland.setFill(targetColor);
      }
      else if(territory == "Quebec")
      {
         textQuebec.setFill(targetColor);
      }
      else if(territory == "Western United States")
      {
         textWesternUnitedStates.setFill(targetColor);
      }
      else if(territory == "Eastern United States")
      {
         textEasternUnitedStates.setFill(targetColor);
      }
      else if(territory == "Central America")
      {
         textCentralAmerica.setFill(targetColor);
      }
      else if(territory == "Venezuela")
      {
         textVenezuela.setFill(targetColor);
      }
      else if(territory == "Brazil")
      {
         textBrazil.setFill(targetColor);
      }
      else if(territory == "Peru")
      {
         textPeru.setFill(targetColor);
      }
      else if(territory == "Argentina")
      {
         textArgentina.setFill(targetColor);
      }
      else if(territory == "Iceland")
      {
         textIceland.setFill(targetColor);
      }
      else if(territory == "Great Britain")
      {
         textGreatBritain.setFill(targetColor);
      }
      else if(territory == "Western Europe")
      {
         textWesternEurope.setFill(targetColor);
      }
      else if(territory == "Northern Europe")
      {
         textNorthernEurope.setFill(targetColor);
      }
      else if(territory == "Southern Europe")
      {
         textSouthernEurope.setFill(targetColor);
      }
      else if(territory == "Ukraine")
      {
         textUkraine.setFill(targetColor);
      }
      else if(territory == "Scandinavia")
      {
         textScandinavia.setFill(targetColor);
      }
      else if(territory == "North Africa")
      {
         textNorthAfrica.setFill(targetColor);
      }
      else if(territory == "Egypt")
      {
         textEgypt.setFill(targetColor);
      }
      else if(territory == "East Africa")
      {
         textEastAfrica.setFill(targetColor);
      }
      else if(territory == "Congo")
      {
         textCongo.setFill(targetColor);
      }
      else if(territory == "South Africa")
      {
         textSouthAfrica.setFill(targetColor);
      }
      else if(territory == "Madagascar")
      {
         textMadagascar.setFill(targetColor);
      }
      else if(territory == "Middle East")
      {
         textMiddleEast.setFill(targetColor);
      }
      else if(territory == "Afghanistan")
      {
         textAfghanistan.setFill(targetColor);
      }
      else if(territory == "Ural")
      {
         textUral.setFill(targetColor);
      }
      else if(territory == "Siberia")
      {
         textSiberia.setFill(targetColor);
      }
      else if(territory == "Yakutsk")
      {
         textYakutsk.setFill(targetColor);
      }
      else if(territory == "Kamchatka")
      {
         textKamchatka.setFill(targetColor);
      }
      else if(territory == "Irkutsk")
      {
         textIrkutsk.setFill(targetColor);
      }
      else if(territory == "Mongolia")
      {
         textMongolia.setFill(targetColor);
      }
      else if(territory == "China")
      {
         textChina.setFill(targetColor);
      }
      else if(territory == "India")
      {
         textIndia.setFill(targetColor);
      }
      else if(territory == "Siam")
      {
         textSiam.setFill(targetColor);
      }
      else if(territory == "Japan")
      {
         textJapan.setFill(targetColor);
      }
      else if(territory == "Indonesia")
      {
         textIndonesia.setFill(targetColor);
      }
      else if(territory == "New Guinea")
      {
         textNewGuinea.setFill(targetColor);
      }
      else if(territory == "Eastern Australia")
      {
         textEasternAustralia.setFill(targetColor);
      }
      else if(territory == "Western Australia")
      {
         textWesternAustralia.setFill(targetColor);
      }
   }
   
   public void setMapTextObjectText(String territory,String targetText)
   {
      if(territory == "Alaska")
      {
         textAlaska.setText(targetText);
      }
      else if(territory == "Northwest Territory")
      {
         textNorthwestTerritory.setText(targetText);
      }
      else if(territory == "Alberta")
      {
         textAlberta.setText(targetText);
      }
      else if(territory == "Ontario")
      {
         textOntario.setText(targetText);
      }
      else if(territory == "Greenland")
      {
         textGreenland.setText(targetText);
      }
      else if(territory == "Quebec")
      {
         textQuebec.setText(targetText);
      }
      else if(territory == "Western United States")
      {
         textWesternUnitedStates.setText(targetText);
      }
      else if(territory == "Eastern United States")
      {
         textEasternUnitedStates.setText(targetText);
      }
      else if(territory == "Central America")
      {
         textCentralAmerica.setText(targetText);
      }
      else if(territory == "Venezuela")
      {
         textVenezuela.setText(targetText);
      }
      else if(territory == "Brazil")
      {
         textBrazil.setText(targetText);
      }
      else if(territory == "Peru")
      {
         textPeru.setText(targetText);
      }
      else if(territory == "Argentina")
      {
         textArgentina.setText(targetText);
      }
      else if(territory == "Iceland")
      {
         textIceland.setText(targetText);
      }
      else if(territory == "Great Britain")
      {
         textGreatBritain.setText(targetText);
      }
      else if(territory == "Western Europe")
      {
         textWesternEurope.setText(targetText);
      }
      else if(territory == "Northern Europe")
      {
         textNorthernEurope.setText(targetText);
      }
      else if(territory == "Southern Europe")
      {
         textSouthernEurope.setText(targetText);
      }
      else if(territory == "Ukraine")
      {
         textUkraine.setText(targetText);
      }
      else if(territory == "Scandinavia")
      {
         textScandinavia.setText(targetText);
      }
      else if(territory == "North Africa")
      {
         textNorthAfrica.setText(targetText);
      }
      else if(territory == "Egypt")
      {
         textEgypt.setText(targetText);
      }
      else if(territory == "East Africa")
      {
         textEastAfrica.setText(targetText);
      }
      else if(territory == "Congo")
      {
         textCongo.setText(targetText);
      }
      else if(territory == "South Africa")
      {
         textSouthAfrica.setText(targetText);
      }
      else if(territory == "Madagascar")
      {
         textMadagascar.setText(targetText);
      }
      else if(territory == "Middle East")
      {
         textMiddleEast.setText(targetText);
      }
      else if(territory == "Afghanistan")
      {
         textAfghanistan.setText(targetText);
      }
      else if(territory == "Ural")
      {
         textUral.setText(targetText);
      }
      else if(territory == "Siberia")
      {
         textSiberia.setText(targetText);
      }
      else if(territory == "Yakutsk")
      {
         textYakutsk.setText(targetText);
      }
      else if(territory == "Kamchatka")
      {
         textKamchatka.setText(targetText);
      }
      else if(territory == "Irkutsk")
      {
         textIrkutsk.setText(targetText);
      }
      else if(territory == "Mongolia")
      {
         textMongolia.setText(targetText);
      }
      else if(territory == "China")
      {
         textChina.setText(targetText);
      }
      else if(territory == "India")
      {
         textIndia.setText(targetText);
      }
      else if(territory == "Siam")
      {
         textSiam.setText(targetText);
      }
      else if(territory == "Japan")
      {
         textJapan.setText(targetText);
      }
      else if(territory == "Indonesia")
      {
         textIndonesia.setText(targetText);
      }
      else if(territory == "New Guinea")
      {
         textNewGuinea.setText(targetText);
      }
      else if(territory == "Eastern Australia")
      {
         textEasternAustralia.setText(targetText);
      }
      else if(territory == "Western Australia")
      {
         textWesternAustralia.setText(targetText);
      }


  

   }
   
   
   
   
   
   
   
   
   
}
