package myfirstpackage;
public class MySecondClass{
	private int i;
	private int j;

	public MySecondClass(int i, int j){
		this.i=i;
		this.j=j;
	}

	public int getI(){
		return i;
	}
	public int getJ(){
		return j;
	}

	public void setI(int new_i){
		i = new_i;
	}
	public void setJ(int new_j){
		j = new_j;
	}

	public int sum(){
		return i + j;
	}
}