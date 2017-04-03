import java.util.ArrayList;
import java.util.Random;


public class Dataset {
	ArrayList<Cls> classes = new ArrayList<Cls>();

	
	public int totalSize(){
		int i = 0;
		for (Cls c : classes){
			i += c.size();
		}
		return i;
	}
	
	public Dataset(Dataset other){
		for (Cls cls : other.classes){
			this.classes.add(new Cls(cls));
		}
	}
	public Dataset(){
		
	}
	
	public SplitSet splitTestTrain(double size){
		
		Random r = new Random();
		
		Dataset training = new Dataset();
		Dataset test = new Dataset(this);
		
		
		
		for (Cls cls : test.classes){
			int i = 0;
			int s = (int) (cls.size() * size);
			ArrayList<double[]> subEntries = new ArrayList<double[]>();

			while (i < s-1){
				int r1 = r.nextInt(cls.entries.size());
				subEntries.add(cls.entries.get(r1));
				i++;
				cls.entries.remove(r1);
			}
			training.classes.add(new Cls(subEntries));
		}
		return new SplitSet(training, test);
	}

	public int features() {
		return classes.get(0).entries.get(0).length;
	}
	
}
