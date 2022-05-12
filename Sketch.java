import processing.core.PApplet;

public class Sketch extends PApplet {
	
  // create variables 
  boolean blnUpPressed = false;
  boolean blnDownPressed = false;
  boolean blnUpPressedBlue = false;
  boolean blnDownPressedBlue = false;
  boolean blnLeftPressedBlue = false;
  boolean blnRightPressedBlue = false;

  float fltBlueCircleY = 600;
  float fltBlueCircleX = 300;

  float fltSnowballSizeX = 30;
  float fltSnowballSizeY = 30;
  float fltBBSizeX = 35;
  float fltBbSizeY = 35;
  float[] circleX = new float[25];
  float[] circleY = new float[25];

  boolean[] ballHideStatus = new boolean[25];
   
  int intPlayerLives = 3;
  boolean blnPlayerAlive = true;
  boolean blnMouseClick = false;

  public void settings() {
	// create size 
    size(600, 600);
  }

  /** generate random snowball location 
  *  loop it  */
  public void setup() {
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHideStatus[i] = false;
    }
  }

  public void draw() {
    // set player life to true, set background color 
    if (blnPlayerAlive == true) {
	    background(125, 255, 255);
      for (int i = 0; i < circleY.length; i++) {
        if (ballHideStatus[i] == false){
        fill(255);
        ellipse(circleX[i], circleY[i], fltSnowballSizeX, fltSnowballSizeY);
        circleY[i]++;
        }
        // create boundries and hide ball when player ball hits the snowball/click on it with mouse
        if (dist(fltBlueCircleX, fltBlueCircleY, circleX[i], circleY[i]) <= 35 && ballHideStatus[i] == false) {
          ballHideStatus[i] = true;
          intPlayerLives--;
        }
        if (dist(mouseX, mouseY, circleX[i], circleY[i]) <= 20 && blnMouseClick) {
          ballHideStatus[i] = true;
        }
  
        if (circleY[i] > height) {
          circleY[i] = 0;
        }
        // keyboard input to increase or decrease speed of snowball
        if (keyPressed) {
          if (keyCode == DOWN) {
            blnDownPressed = true;
            circleY[i] += 5;
          }
          if (keyCode == UP) {
            blnUpPressed = true;
            circleY[i] -= 0.5;
          }
        }
        // set player ball speed
        if (blnUpPressedBlue) {
          fltBlueCircleY-= 0.20;
        }
        if (blnDownPressedBlue) {
          fltBlueCircleY+= 0.20;
        }
        if (blnLeftPressedBlue) {
          fltBlueCircleX-= 0.20;
        }
        if (blnRightPressedBlue) {
          fltBlueCircleX+= 0.20;
        }
        // create blueball (player ball)
        fill(0, 25, 250);
        ellipse(fltBlueCircleX, fltBlueCircleY, fltBBSizeX, fltBbSizeY);   
        }

      // setting boundries so that player ball will not go out of frame
      if (fltBlueCircleX < 20) {
        fltBlueCircleX -= -7.5;
      }
      else if (fltBlueCircleX > 580) {
        fltBlueCircleX -= 7.5;
      }
      if (fltBlueCircleY < 20) {
        fltBlueCircleY -= -7.5;
      }
      else if (fltBlueCircleY > 580) {
        fltBlueCircleY -= 7.5;
      }

      // create lives for player 
      for (int i = 1; i <= intPlayerLives; i++) {
        fill(255, 0 ,0);
        rect(500 + i * 24, 15, 20, 20);      
      }

      if (intPlayerLives <= 0) {
        blnPlayerAlive = false;     
      }
    }
    // make background white when player run out of lives and display game over
    else {
      background(255);
      textSize(50);
      text("GAME OVER", 150, 300);
    }  
  }

  public void keyPressed() {
    // allow handling multiple keys when moving the player ball
    if (keyPressed) {
      if (key == 'w'){
        blnUpPressedBlue = true;
      }
      else if (key == 'a'){
        blnLeftPressedBlue = true;
      }
      else if (key == 's') {
        blnDownPressedBlue = true;
      }
      else if  (key == 'd'){
        blnRightPressedBlue = true;
      }
    }
  }

  public void keyReleased() {
    // allow handling multiple keys when moving the player ball
    if (key == 'w') {
      blnUpPressedBlue = false;
    }
    else if (key == 's') {
      blnDownPressedBlue = false;
    }
    else if (key == 'a') {
      blnLeftPressedBlue = false;
    }
    else if (key == 'd') {
      blnRightPressedBlue = false;
    }
  }

  public void mousePressed() {
    // allow mouse input to destroy snowball when clicked
    ellipse(mouseX, mouseY, 20, 20);
    blnMouseClick = true;
  }
  
  public void mouseReleased() {
    blnMouseClick = false;
  }
}