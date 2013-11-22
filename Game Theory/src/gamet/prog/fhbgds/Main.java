package gamet.prog.fhbgds;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {
	
	private static CellBase cell = new CellBase();
	
	private Random rand = new Random();
	public boolean shouldRun = true;
	public static Cell[] cells = new Cell[10];
	
	public Main() throws Exception{
		for(int i = 0; i <= 9; i++){
			cells[i] = new Cell();
			cells[i].setPosition(rand.nextInt(10), rand.nextInt(10));
		}
		
		
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setResizable(true);
			Display.setTitle("Game Theory Demonstration");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main();
	}

	
	public void gameLoop(){
		while(shouldRun){
//			cell.livingUpdate();
		}
	}
}
