package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import fileIO.IllegalCharacterException;

public class Main {
	//==========Variables==========//
	private final static int STACK = 1;
	private final static int QUEUE = 2;
	private final static int OPTIMAL = 3;
	private static int index = 0;
	private static boolean isCoordinate = false;
	private static boolean outIsCoordinate = false;
	private static boolean time = false;
	private static boolean debug = false;
	private static int algorithm = -1;
	//==========Main==========//
	public static void main(String[] args) throws FileNotFoundException {
		if(args.length == 0) {help();}												//If there are not arguments, print help.
		File tmp = new File(args[0]);												//Check if the file provided exists. 
		if(!tmp.exists()) { throw new FileNotFoundException();}
		for(int i = 1; i < args.length; i++) {										//Handle arguments in args. 
			switch (args[i].toLowerCase()) {
			case "--stack":
				if(algorithm != -1) {throw new IllegalArgumentException("More than one algorithm specified.");}
				else { algorithm = STACK;}
				break;
			case "--queue":
				if(algorithm != -1) {throw new IllegalArgumentException("More than one algorithm specified.");}
				else { algorithm = QUEUE;}
				break;
			case "--opt":
				if(algorithm != -1) {throw new IllegalArgumentException("More than one algorithm specified.");}
				else { algorithm = OPTIMAL;}
				break;
			case "--time":
				time = true;
				break;
			case "--incoordinate":
				isCoordinate = true;
				break;
			case "--outcoordinate":
				outIsCoordinate = true;
				break;
			case "--debug":
				debug = true;
				break;
			case "--help":
				help();
				break;
			case "--bunny":
				System.out.println("\n/)___(\\ \n(='.'=)\n(\")_(\")\n"); //Credit to Hak5 for this
				break;
			case "--index":
				try {
					index = Integer.parseInt(args[i+1]);
					i++;
				}
				catch (Exception e) {
					System.err.println("Error when parsing index, see below");
					e.printStackTrace();
				}
				break;
			default:
				help();
				break;
			}
		}
		long startTime = 0;
		if(time) {startTime = System.currentTimeMillis();}								//Gets time for time testing.
		try {
			switch (algorithm) {														//Attempts the algorithm specified by the flag.
			case STACK:		
				StackAlgorithm s = new StackAlgorithm(args[0], debug, index, isCoordinate);
				System.out.println(s.getMap());
				break;
			case QUEUE:
				//QueueAlgorithm q = new QueueAlgorithm(args[0], isCoordinate);
				break;
			case OPTIMAL:
				OptimalPath o = new OptimalPath(args[0],debug, index, isCoordinate);
				System.out.println(o.getMap());
				break;
			default:
				throw new IllegalArgumentException("Error: Algorithm not specified");	//Throws error if algorithm not specified.
			}
		} catch (IOException | IllegalCharacterException e) {							//Error handling.
			e.printStackTrace();
		}
		if(time) {System.out.println("Time to completion:" + (System.currentTimeMillis() - startTime));}
	}
	//==========Help Method==========//
	private static void help() {
		System.out.println("A maze solving algorithm created by Screencap.\nhttps://github.com/t4canty");
		System.out.println("Usage: .\\Pather.jar <mapFile> [--Stack|--Queue|--Opt] [--Time] [--Incoordinate] [--Outcoordinate] [--Help] [--Debug]");
		System.out.println("--Stack : Uses the Stack Algorithm to solve the map.\n--Queue : Uses the Queue Algorithm to solve the map\n--Opt Uses the optimal Algorithm to solve the map.\n--Index: Index at which to start parsing");
		System.out.println("--Time : Times execution time");
		System.out.println("--Incoordinate : Sets wether or not the map is a coordinate-based map or an ASCII map\n--Outcoordinate : Sets wether or not the output map is a coordinate-based map or an ASCII map");
		System.out.println("--Help : Prints this help message.");
		System.out.println("--Debug : Sets the debug flag for the program. (Warning: VERY verbose.)");
		System.exit(0);
	}

}
