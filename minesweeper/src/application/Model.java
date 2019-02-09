package application;

public class Model {
	    private int  rows;
	    private int colums;
	    private int bombs;
	    private int[][] matrix;
	    private boolean[][] matrixvisibility;
	    private int bomb_prob;
	    private int hidden;
	    Model(int rows,int colums,int bomb_prob){
		//do the stuff here
		this.rows=rows;
		this.colums=colums;
		this.bombs=0;
		this.bomb_prob=bomb_prob;
		makeMatrix();
	    }
	    public double random(int min,int max){
		//do it
		return (Math.random()*(max-min))+min;
	    }
	    public void makeMatrix(){
		//do the stuff here
		matrix=new int[rows][colums];
		matrixvisibility=new boolean[rows][colums];
		hidden=rows*colums;
		fillbombs();
	    }
	    public void  fillbombs(){
		//
		for(int i=0;i<rows;i++){
		    for(int j=0;j<colums;j++){
			if(bombornot()){
			    matrix[i][j]=9;
			    bombs++;
			}
		    }
		}
		fillboard();
		notzeros();
		
	    }
	    public void fillboard(){
		//
		for(int i=0;i<rows;i++){
		    for(int j=0;j<colums;j++){
			if(matrix[i][j]==9){
			    fillleft(i,j);
			    fillright(i,j);
			    filldown(i,j);
			    fillup(i,j);
			}
		    }
		}
		
		
	    }
	    private boolean crValidation(int i, int j){
		return (i>=0 && i<rows && j>=0 && j<colums && matrix[i][j]!=9);
	    }
	    private void fillleft(int i,int j){
		//
		if (crValidation(i, j-1))
		    matrix[i][j-1]++;
		if (crValidation(i-1, j-1))
		    matrix[i-1][j-1]++;
		if (crValidation(i+1, j-1))
		    matrix[i+1][j-1]++;
		
	    }
	    public void fillright(int i,int j){
		//
		if (crValidation(i, j+1))
		    matrix[i][j+1]++;
		if (crValidation(i-1, j+1))
		    matrix[i-1][j+1]++;
		if (crValidation(i+1, j+1))
		    matrix[i+1][j+1]++;
	    }
	    public void fillup(int i,int j){
		//
		if (crValidation(i-1, j))
		    matrix[i-1][j]++;
	    }
	    public void filldown(int i,int j){
		//
		if (crValidation(i+1, j))
		    matrix[i+1][j]++;
	    }
	    public void print(){
		//
		for(int i=0;i<rows;i++){
		    System.out.print("["+" ");
		    for(int j=0;j<colums;j++){
			if(voh()){
			    System.out.print(matrix[i][j]+" ");
			}else{
			    System.out.print("-"+" ");
			}
		    }
		    System.out.print("]");
		    System.out.println();
		    
	        }
	    }
	    public boolean voh(){
		//
		return true;
	    }
	    public boolean bombornot(){
		return bomb_prob-1>=(int)random(0,10);
	    }
//	    private void printboard(){
//		//
//			//
//		for(int i=0;i<rows;i++){
//		    System.out.print("["+" ");
//		    for(int j=0;j<colums;j++){
//			if(matrixvisibility[i][j])
//			    System.out.print(matrix[i][j]+" ");
//			else
//			    System.out.print("- ");
//		    }
//		    System.out.print("]");
//		    System.out.println();
//		    
//	        }
//
//	    }
	    private void click(int i,int j){
		if(isValid(i,j))
		    {	
		         removeH(i,j);
		       
		    }
		
	    }
	    
	    public String getLabel(int i, int j) {
	    	
	    	return Integer.toString(matrix[i][j]);
	    }
	    private boolean isValid(int i,int j){
		return (i>=0 && i<rows && j>=0 && j<colums);
	    }
	    private boolean isBomb(int  i, int j){
		return matrix[i][j]==9;
	    }
	    private  void removeH(int i,int j){
	    	if(isValid(i,j) && !matrixvisibility[i][j]){
	    		matrixvisibility[i][j]=true;
	    		hidden--;
	    	}
		
	    	else
	    		return;
	    	if(matrix[i][j]==0)
			    {
				removeH(i-1,j);
				removeH(i+1,j);
				removeH(i,j-1);
				removeH(i,j+1);
			    }
	    }


	    public String nextMove(int i,int j){
		//print();
		    if(isBomb(i,j)){
			 return "BOMB";
		    }
		    click(i,j);
		    if(hidden==bombs){
		    	return "WON";
		    }
		    return "Continue";
		
		
	    }
	    public boolean isvisible(int i,int j) {
	    	return matrixvisibility[i][j];
	    }
	    private void notzeros(){
	    	if(bombs==0) {
	    		fillbombs();
	    	}
	    	return;
	    }


}
