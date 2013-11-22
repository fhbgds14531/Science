package gamet.prog.fhbgds;

public class CellBase {
	public int reproductionChance = 25;
	public int cellID = 0;
	public int posX, posY;
	
	public CellBase(){
		this.cellID += 1;
	}
	
	public void setPosition(int x, int y){
		this.posX = x;
		this.posY = y;
	}
	
	public void livingUpdate(){
		final CellBase thisCell = this;
		final int x = this.posX;
		final int y = this.posY;
		Thread thread = new Thread(new Runnable(){
			public void run(){
				Cell nearestCell = getNearestCellWithin3(x, y);
				try{
					Thread.sleep(500l);
				}catch (Exception e){}
				if(nearestCell == getNearestCellWithin3(x, y)){
					nearestCell.reproductionChance++;
					thisCell.reproductionChance++;
				}else{
					thisCell.reproductionChance--;
				}
			}
		});
		thread.start();
	}
	
	public Cell getNearestCellWithin3(int x, int y){
		for(int i = 0; i <= Main.cells.length; i++){
			double distance = Math.sqrt((double)((x + Main.cells[i].posX)^2 + (y + Main.cells[i].posY)^2));
			if(distance < 3.0d && Main.cells[i].cellID != this.cellID){
				return Main.cells[i];
			}
		}
		return null;
	}
}
