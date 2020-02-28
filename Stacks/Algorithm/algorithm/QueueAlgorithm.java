package algorithm;
import java.io.IOException;
import dataStructures.FiFo;
import fileIO.IllegalCharacterException;
import fileIO.mapParser;
import fileIO.mapPoint;

public class QueueAlgorithm {
	private FiFo<mapPoint> locations;
	private mapParser mp;
	public QueueAlgorithm(String fileName) throws IOException, IllegalCharacterException {
		this(fileName, 0);
	}
	public QueueAlgorithm(String fileName, int index) throws IOException, IllegalCharacterException {
		mp = new mapParser(fileName, index);
		locations = new FiFo<mapPoint>(mp.toArray());
	}
	
}
