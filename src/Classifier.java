

public class Classifier {
	

	Dataset data;
	double[] weights;
	
	public Classifier(Dataset data) {
		this.data = data;
		weights = new double[data.features()];
		for (int i = 0; i < weights.length; i++){
			weights[i] = 1;
		}
	}



	private double prob(Dataset data, double thresh, int cs, int ft, double[] value){
		double prior = (double)data.classes.get(cs).size()/(double)data.totalSize();
		double like = (double)data.classes.get(cs).occ(value[ft], ft, thresh)/(double)data.classes.get(cs).size();
		double post = prior * like;
		return post;
	}
	
	
	
	public static void main(String[] args) {
		Classifier c = new Classifier(new Wine());
		System.out.println(c.data.totalSize());
		double thresh = 1;
		c.train(5, 0.5, thresh);
	}
	
	public void train(int times, double fold, double thresh){
		
		for (int n = 0; n < times; n++){
			
			System.out.println("\nFold " + n);
			
			Dataset newData = new Dataset(data);
			SplitSet s = newData.splitTestTrain(fold);
			
			for (int i = 0; i < s.test.classes.size(); i++){
				System.out.println("Class " + i);
				double correct = 0;
				for (double[] a : s.test.classes.get(i).entries){
					double max = 0;
					int index = 0;
					for (int k = 0; k < s.test.classes.size(); k++){
						double p = 0;
						
						
						for (int f = 0; f < weights.length; f++){
							p += (prob(s.train, thresh, k, f, a) * weights[f]);
						}
						
						p = p/weights.length;
						//System.out.println("Entry " + a.toString() + " against " + k + "  = " + p);
						if (p > max){
							max = p;
							index = k;
						}
					}
					//System.out.println("Guessed: " + index);
					if (index == i){
						correct++;
					}
					
				}
				System.out.println(correct + " / " + s.test.classes.get(i).size() + " - " + correct/s.test.classes.get(i).size());
			}
		}
	}
	
	public void decide(){
		
	}
	
	public double entropy(Dataset data, int ft){
		double h = 0;
		for (int i = 0; i < data.classes.size(); i++){
			double p = prob(data, 1, i, ft, weights);
			h += prob
		}
	}
}
