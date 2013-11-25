package gtii.prog.fhbgds;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;
import gtii.prog.fhbgds.cell.Cell;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {

	public static Cell[] cells = new Cell[10];
	
	private static volatile boolean isCloseRequested = false;
	
	public Main(){
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Display test");
			Display.create();
			getReadyFor2DDrawing();
			startSim();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		while(!Display.isCloseRequested() && !isCloseRequested) {
			checkKeyboard();
			Display.update();
			try{Thread.sleep(50l);}catch(InterruptedException e){ e.printStackTrace(); isCloseRequested = true;}
			updateCells(cells);
		}
		Display.destroy();
		System.exit(0);
	}
	
	private static void updateCells(Cell[] cellArray){
		for(int i = 0; i <= cellArray.length - 1; i++){
			cellArray[i].cellUpdate();
			clearDisplay();
			drawCells(cellArray);
		}
	}
	
	private static void drawCells(Cell[] cellArray) {
		clearDisplay();
		for (int i = 0; i <= cellArray.length - 1; i++){
			drawCell(cellArray[i]);
		}
	}

	public void checkKeyboard(){
		while(Keyboard.next()){
			if(Keyboard.isKeyDown(Keyboard.KEY_P) && (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))){
				for(int i = 0; i <= 3; i++){
					int cellID = Cell.rand.nextInt(10);
//					cellID--;
					cells[cellID].setDead();
				}
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				this.shutdown();
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_D) && (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))){
				new GTheoryII(cells);
			}
		}
	}
	
	public static void clearDisplay(){
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void shutdown(){
		isCloseRequested = true;
	}
	
	public static void main(String[] args) {
		for(int i= 0; i <= 9; i++){
			cells[i] = new Cell();
		}
		new Main();
	}
	
	private static void drawShape1(float startX, float startY, float sizeX, float sizeY){
		glBegin(GL_TRIANGLES);
		glVertex2f(startX, startY);
		glVertex2f(startX, startY + sizeY);
		glVertex2f(startX + sizeX, startY + sizeY);
		glEnd();
	}
	
	public static void drawShape2(float startX, float startY, float sizeX, float sizeY){
		glBegin(GL_TRIANGLES);
		glVertex2f(startX + sizeX, startY + sizeY);
		glVertex2f(startX + sizeX, startY);
		glVertex2f(startX, startY);
		glEnd();
	}
	
	public void getReadyFor2DDrawing(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void startSim(){
		for(int i = 0; i <= 9; i++){
			Cell cell = cells[i];
			drawCell(cell);
			Display.update();
			drawCell(cell);
		}
		new GTheoryII(cells);
	}
	
	public static void drawCell(Cell cell){
		drawShape1(cell.getPosX(), cell.getPosY(), cell.getSizeX(), cell.getSizeY());
		drawShape2(cell.getPosX(), cell.getPosY(), cell.getSizeX(), cell.getSizeY());
	}
}
