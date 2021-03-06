
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Allen Hwang
 * ah45755
 * 16445
 * Sagar Krishnaraj
 * sk37433
 * 16455
 * Slip days used: <0>
 * Fall 2016
 * GIT URL: https://github.com/HorizonStrider/Critter.git
 */
package assignment4; // cannot be in default package
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        String input = "";
        boolean loop = true;
        do
        {
        	try{
	            input = kb.nextLine();
	            if(input.contains("show"))
	            {	if(input.equals("show"))
	            	Critter.displayWorld();
	            else
	            	System.out.println("error processing: " + input);
	            }
	            else if(input.contains("quit"))
	            {
	            	if(input.equals("quit"))
	            	loop = false;
	            	else
	            		System.out.println("invalid command: " + input);
	            }
	            else if(input.contains("step"))
	            {
	            	
	            	String[] parts = input.split(" ");
	            	if(parts.length ==1&&input.equals("step"))
	            	{
	            		Critter.worldTimeStep();
	            	}
	            	else if(parts.length == 2&&parts[0].equals("step"))
	            	{
	            		int stepLoop = Integer.parseInt(parts[1]);
	            		for(int x = 0; x < stepLoop; x++)
	            		{
	            			Critter.worldTimeStep();
	            		}
	            	}
	            	else
	            	{
	            		System.out.println("error processing: " + input);
	            	}
	            }
	            else if(input.contains("seed"))
	            {
	            	String[] parts = input.split(" ");
	            	if(parts.length!= 2||!parts[0].equals("seed"))
	            		System.out.println("error processing: " + input);
	            	else
	            	{
	            		Critter.setSeed(Long.parseLong(parts[1]));
	            	}
	            }
	            
	            else if(input.contains("make"))
	            {
	            	String[] parts = input.split(" ");
	            	if(parts.length < 2||parts.length >3||!parts[0].equals("make"))
	            		System.out.println("error processing: " + input);
	            	else if(parts.length == 2)
	            	{
	            		Critter.makeCritter(parts[1]);
	            	}
	            	else if(parts.length == 3)
	            	{
	            		int parse = Integer.parseInt(parts[2]);
	            		for(int x = 0; x < parse; x++)
	            		{
	            			Critter.makeCritter(parts[1]);
	            		}
	            	}
	            }
	            else if(input.contains("stats"))
	            {
	            	String[] parts = input.split(" ");
	            	if(parts.length != 2||!parts[0].equals("stats"))
	            		System.out.println("error processing: " + input);
	            	else
	            	{
	            		try{
	            			String packageName = Critter.class.getPackage().toString().split(" ")[1];
	            		
	            		List<Critter> list = Critter.getInstances(parts[1]);
	            		Class <?> overload = Class.forName(packageName + "." + parts[1]);
	            		Method stats = overload.getMethod("runStats", List.class);
	            		stats.invoke(overload, list);
	            		}
	            		catch(Exception e)
	            		{
	            			System.out.println("error processing: " + input);
	            		}
	            	}
	            }
	            else
	            {
	            	System.out.println("invalid command: " + input );
	            }
        	}
        	catch(Exception e)
        	{
        		System.out.println("error processing: " + input);
        	}
        }while(loop);
        /* Write your code above */
        System.out.flush();

    }
}
