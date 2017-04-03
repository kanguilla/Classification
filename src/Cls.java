import java.util.ArrayList;

public class Cls {

	public Cls(double[][] entries){
		for (double[] e : entries){
			this.entries.add(e);
		}	
	}
	
	public Cls(Cls copy){
		this.entries = new ArrayList<double[]>(copy.entries);
	}
	
	public Cls(ArrayList<double[]> entries){
		this.entries = entries;
	}
	
	ArrayList<double[]> entries = new ArrayList<double[]>();
	
	public int size() {
		return entries.size();
	}
	
	public int occ(double val, int ft, double thresh){
		int x = 0;
		for (double[] e : entries){
			if (Math.abs(val - e[ft]) < thresh){
				x++;
			}
		}
		return x;
	}

}
