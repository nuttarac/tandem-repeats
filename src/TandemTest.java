import Tandem.TandemDP;
import Tandem.TandemSAIS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class TandemTest {


	public static void main(String args[]){
		HashMap<String, String> database = fileToHashMap("database");
		System.out.println("Your String is : "+database.get("100130899").toString());
		TandemDP dp = new TandemDP();
		TandemSAIS sa = new TandemSAIS();
		System.out.println();
		System.out.println("Logic of AP");
		long start = System.currentTimeMillis();
		System.out.println("start : " +start);
		int maxTandem =dp.start(database);
		long finish = System.currentTimeMillis();
		System.out.println("time  : " +(finish-start));

		System.out.println();
		System.out.println("Logic of SAIC");
		 start = System.currentTimeMillis();
		System.out.println("start : " +start);
		sa.start(database,maxTandem);
		 finish = System.currentTimeMillis();
		System.out.println("time  : " +(finish-start));
	}


	public static HashMap<String,String> fileToHashMap(String filename){
		if(!filename.contains(".txt")) filename = filename+".txt";
		HashMap<String, String> hash_map = new HashMap<String, String>();
		String id="";
		StringBuilder str=new StringBuilder();
		try
		{
			BufferedReader text = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = text.readLine()) != null)
			{
				line = line.trim();
				if(line.startsWith(">", 0)){
					if(id!="" && str.length()!=0){
						hash_map.put(id, str.toString());
						id="";
						str.setLength(0);
					}
					id = line.split(" ")[0].substring(5);
				}
				else{
					str.append(line);
				}

			}
			hash_map.put(id, str.toString());
			text.close();
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
		}
		return hash_map;
	}
}
