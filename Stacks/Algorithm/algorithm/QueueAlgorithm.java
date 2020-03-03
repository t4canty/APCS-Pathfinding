package algorithm;
import java.io.IOException;
import java.util.ArrayList;
import dataStructures.FiFo;
import fileIO.IllegalCharacterException;
import fileIO.mapParser;
import fileIO.mapPoint;

public class QueueAlgorithm {
	private FiFo<mapPoint> locations;
	private mapParser mp;
	private ArrayList<mapPoint> path = new ArrayList<mapPoint>();
	private boolean debug = false;
	public QueueAlgorithm(String fileName) throws IOException, IllegalCharacterException {
		this(fileName, 0);
	}
	public QueueAlgorithm(String fileName, int index) throws IOException, IllegalCharacterException {
		mp = new mapParser(fileName, index);
		locations = new FiFo<mapPoint>(mp.toArray());
		recursiveGuess(locations.pop());
	}
	private void dequeue(FiFo<mapPoint> f) {
		FiFo<mapPoint> f2 = new FiFo<mapPoint>();
		while(f.pop().getData() != "K") {
			f2.push(f.pop());
		}
	}
	
	private void recursiveGuess(mapPoint N) {
		if(debug) { System.out.println("Guessing point:" + N.toString());}
		if(N.getData().equals("C")) {path.add(N); if(debug) {System.out.println("Found cake.");}}
		else if(N.getData().equals(".") && N.hasVisited == false) {
			if(debug) {System.out.println("Found empty unvisited square.");}
			path.add(N);
			N.hasVisited = true;
			recursiveGuess(locations.pop());
		}else{
			if(debug) {System.out.println("Found impass.");}
			if(debug && N.hasVisited == true) {System.out.println("Visited Square");}
			recursiveGuess(locations.pop());
		}
	}
	public String toString() {
		String s = "";
		for(mapPoint p : path) {
			s += p.toString();
		}
		return s;
	}
}
