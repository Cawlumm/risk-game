//Coded by the Risk team CPT 237-W34
//3/7/2023
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/* ======================
 * Roll Dice Class
 * Created by: Carter Lumm
 * 3/15/2023
 ================*/

public class DiceRoll {
	// Public variables/objects
	public int Total;
	public int number;
	public Random rnd = new Random();
	public Timeline timeline = new Timeline();
	
	// Used by the DiceRoll Animation gets a random number
	public int rollDice() {
		while (true){
		    number = rnd.nextInt(7);
		    if(number !=0) break;
		}
		return number;
	}
	
	// Used by the DiceRoll Animation, creates a list of random Numbers for the animation
	public List<Integer> ranList() {
		List<Integer> ranNumbers = new ArrayList<Integer>();
		for(int i = 0; i < 25; i++) {
			int ranNum = rollDice();
			ranNumbers.add(ranNum);
		}
		return ranNumbers;
	}
	
	// Sets the image for the ImageView used by the DiceRoll Method
	public void setDiceImage(int number, ImageView imageview) {
		imageview.setImage(new Image(DiceConstants.DICEFACES[number - 1], 100, 100, false, false));
	}
	
	// DiceRoll Animation
	public int diceAnimation(ImageView imageview) {
		List<Integer> ranNumbers = ranList(); // Create random #list using method
		for(int i = 0; i < ranNumbers.size(); i++) {
			// index has to be final or else it throw an error, 
			// you can not use a mutable variable inside a lambda expression
			final int index = i;
			KeyFrame keyframe = new KeyFrame(Duration.millis(100 * i), event -> {
	            setDiceImage(ranNumbers.get(index), imageview);
			});
			timeline.getKeyFrames().add(keyframe);
		}
		timeline.play();
		return ranNumbers.get(ranNumbers.size() - 1);
	}
}
