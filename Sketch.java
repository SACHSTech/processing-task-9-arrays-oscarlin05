import processing.core.PApplet;

public class Sketch extends PApplet {
	
  boolean upPressed = false;
  boolean downPressed = false;
  boolean upPressedBlue = false;
  boolean downPressedBlue = false;
  boolean leftPressedBlue = false;
  boolean rightPressedBlue = false;

  float blueCircleY = 400;
  float blueCircleX = 200;

  float snowballSizeX = 25;
  float snowballSizeY = 25;
  float bbSizeX = 35;
  float bbSizeY = 35;
  float[] circleX = new float[25];
  float[] circleY = new float[25];

  boolean[] ballHideStatus = new boolean[25];
   
  int intPlayerLives = 3;
  boolean blnPlayerAlive = true;

  public void settings() {
	// put your size call here
    size(400, 400);
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHideStatus[i] = false;
    }
  }

  public void setup() {
  }

  public void draw() {
    if (blnPlayerAlive = true){
	    background(125, 255, 255);
      for (int i = 0; i < circleY.length; i++) {
        if (ballHideStatus[i] == false){
        fill(255);
        ellipse(circleX[i], circleY[i], snowballSizeX, snowballSizeY);
        circleY[i]++;
        }
        if (dist(blueCircleX, blueCircleY, circleX[i], circleY[i]) <= 35 && ballHideStatus[i] == false) {
          ballHideStatus[i] = true;
          intPlayerLives--;
        }
  
        if (circleY[i] > height) {
          circleY[i] = 0;
      }
        if (keyPressed) {
          if (keyCode == DOWN) {
            downPressed = true;
            circleY[i] += 5;
        }
          if (keyCode == UP) {
            upPressed = true;
            circleY[i] -= 0.5;
        }
      }

        if (upPressedBlue) {
          blueCircleY-= 0.20;
        }
        if (downPressedBlue) {
          blueCircleY+= 0.20;
        }
        if (leftPressedBlue) {
          blueCircleX-= 0.20;
        }
        if (rightPressedBlue) {
          blueCircleX+= 0.20;
        }
        fill(0, 25, 250);
        ellipse(blueCircleX, blueCircleY, bbSizeX, bbSizeY);   
      }


    if (blueCircleX < 25) {
      blueCircleX -= -7.5;
    }
    else if (blueCircleX > 375) {
      blueCircleX -= 7.5;
    }
    if (blueCircleY < 25) {
      blueCircleY -= -7.5;
    }
    else if (blueCircleY > 375) {
      blueCircleY -= 7.5;
    }

    // create lives
    for (int i = 1; i <= intPlayerLives; i++) {
      fill(255, 0 ,0);
      rect(350 + i * 12, 15, 10, 10);
      //rect(365, 15, 10, 10);
      //rect(380, 15, 10, 10);      
    }

    if (intPlayerLives == 0) {
      blnPlayerAlive = false;
    }
    }
  else {
      background(255);
    }  
  }
  
    // create blue ball 
  public void keyPressed() {

    if (keyPressed) {
      if (key == 'w'){
        upPressedBlue = true;
      }
      else if (key == 'a'){
        leftPressedBlue = true;
      }
      else if (key == 's') {
        downPressedBlue = true;
      }
      else if  (key == 'd'){
        rightPressedBlue = true;
      }
    }
  }

  public void keyReleased() {

    if (key == 'w') {
      upPressedBlue = false;
    }
    else if (key == 's') {
      downPressedBlue = false;
    }
    else if (key == 'a') {
      leftPressedBlue = false;
    }
    else if (key == 'd') {
      rightPressedBlue = false;
    }
}

}