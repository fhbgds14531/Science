package gtii.prog.fhbgds.cell;

import gtii.prog.fhbgds.Main;

import java.util.Random;

public class Cell {
	
	public static Random rand = new Random();
	
	int xPos = rand.nextInt(630);
	int yPos = rand.nextInt(470);
	
	int sizeX = 10;
	int sizeY = 10;
	
	int reproductionChance = 25;

	private boolean isDead;

	private boolean isFinishedPathing;
	
	public void cellUpdate(){
		if(rand.nextFloat() < 0.01){
			this.kill();
		}
		this.moveRandom();
		Cell cell = getCellWithinRange(xPos, yPos, 25);
		if(cell != this && cell != null && !cell.isDead && this.getDiatanceToCell(this, cell) <= 50){
			cell.reproductionChance++;
			this.reproductionChance++;
		}else if (cell == null || cell.isDead){
			this.reproductionChance--;
		}
		if(this.reproductionChance <= 0 && !this.isDead){
			this.kill();
			if(!this.isDead) this.reproductionChance = 25;
		}
		if(this.reproductionChance >= 100 && !this.isDead){
			this.reproduce();
			this.reproductionChance = 25;
		}
	}
	
	private void reproduce() {
		if(this.sizeX <= 90 && this.sizeY <= 90){
			this.sizeX += 5;
			this.sizeY += 5;
		}
	}

	private void kill() {
		if(this.sizeX >= 10 && this.sizeY >= 10){
			this.sizeX -= 5;
			this.sizeY -= 5;
		}else{
			this.sizeX = 0;
			this.sizeY = 0;
			this.isDead = true;
		}
	}
	
	public void setDead(){
		this.isDead = true;
		this.sizeX = 0;
		this.sizeY = 0;
	}

	private void pickRandomPath(){
		this.isFinishedPathing = false;
		int xDest, yDest;
		
	}
	
	private void moveRandom(){
		int num = rand.nextInt(5);
		switch(num){
		case 1:
			xPos++;
		break;
		case 2:
			xPos--;
		break;
		case 3:
			yPos++;
		break;
		case 4:
			yPos--;
		break;
		default:
			yPos--;
		break;
		}
		if(xPos > 640 - this.sizeX) xPos = 640 - this.sizeX;
		if(xPos < this.sizeX) xPos = this.sizeX;
		if(yPos > 480 - this.sizeY) yPos = 480 - this.sizeY;
		if(yPos < this.sizeY) yPos = this.sizeY;
	}
	
	/**
	 * @param x - The x coord of the current {@link Cell}
	 * @param y - The y coord of the current {@link Cell}
	 * @param range - Maximum distance to check within
	 * @return the first cell it finds within the range
	 */
	public Cell getCellWithinRange(int x, int y, double range){
		for(int i = 0; i <= Main.cells.length - 1; i++){
			double distance = Math.sqrt((double)((x + Main.cells[i].getPosX())^2 + (y + Main.cells[i].getPosY())^2));
			if(distance < range && Main.cells[i] != this){
				return Main.cells[i];
			}
		}
		return null;
	}
	
	public double getDiatanceToCell(Cell cell1, Cell cell2){
		return Math.sqrt((double)((cell1.getPosX() + cell2.getPosX())^2 + (cell1.getPosY() + cell2.getPosY())^2));
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
