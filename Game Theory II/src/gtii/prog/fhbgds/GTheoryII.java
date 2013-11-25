package gtii.prog.fhbgds;

import gtii.prog.fhbgds.cell.Cell;

public class GTheoryII {

	private static Cell[] cells = new Cell[10];
	
	public GTheoryII(Cell[] cellArray){
		if(cellArray != null){
			cells = cellArray;
		}
		for(int i = 0; i <= cells.length - 1; i++){
			System.out.format("Cell %s:\n", i);
			System.out.format("x: %s, y: %s\n", cells[i].getPosX(), cells[i].getPosY());
			System.out.println("-----------");
		}
	}
	
	
	
	
}
