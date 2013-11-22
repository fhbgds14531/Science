package gtii.prog.fhbgds.cell;

import gtii.prog.fhbgds.Main;

import java.util.Random;

public class Cell {
	
	Random rand = new Random();
	
	int xPos = rand.nextInt(630);
	int yPos = rand.nextInt(470);
	
	int sizeX = 10;
	int sizeY = 10;
	
	int reproductionChance = 25;
	
	public void cellUpdate(){
		
	}
	
	/**
	 * @param x - The x coord of the current {@link Cell}
	 * @param y - The y coord of the current {@link Cell}
	 * @param range - Maximum distance to check within
	 * @return the first cell it finds within the range
	 */
	public Cell getCellWithinRange(int x, int y, double range){
		for(int i = 0; i <= Main.cells.length; i++){
			double distance = Math.sqrt((double)((x + Main.cells[i].getPosX())^2 + (y + Main.cells[i].getPosY())^2));
			if(distance < range && Main.cells[i] != this){
				return Main.cells[i];
			}
		}
		return null;
	}
	
	public int getPosX(){
		return this.xPos;
	}
	
	public int getPosY(){
		return this.yPos;
	}
	
	public int getSizeX(){
		return this.sizeX;
	}
	
	public int getSizeY(){
		return this.sizeY;
	}
}	
