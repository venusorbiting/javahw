/*  Alicia Curtis
    CS E-10b Spring 2018
    PS 5C
    Problem #6
    Program that grades student responses to an exam, taking input from the user to get the correct answers.
*/

import java.util.*;
import java.io.*;

class ExamAnalysis
{
    public static void main (String [] args) throws IOException
    {
        Scanner keyboard = new Scanner (System.in);
        
        System.out.println("I hope you are ready to begin...\n");
        System.out.print("Please type the correct answers to the exam questions, one right after the other: ");
        String correctAnswers = keyboard.nextLine();
        int numOfQs = correctAnswers.length();
        
        System.out.print("What is the name of the file containing each student's responses to the " + numOfQs + " questions? ");
        File f = new File (keyboard.nextLine());
        Scanner inFile = new Scanner (f);
        // ADD A TRY/CATCH HERE        
        //if (!(f.exists() && f.canRead())) System.out.println("Sorry, that's not a valid file.");
        
        // initialize variables
        int lineCount = 0;
        int correct = 0;
        int incorrect = 0;
        int blank = 0;
        boolean validInput = true;
        ArrayList<Integer> studentTally = new ArrayList<> ();
        int [] fullAnswers = new int [numOfQs * 6];
        while (inFile.hasNextLine())
        {
            correct = 0;
            incorrect = 0;
            blank = 0;
            validInput = true;
            String thisAnswer = inFile.nextLine();
            if (thisAnswer.length() == numOfQs)
            {
                for (int i = 0; i < numOfQs; i++)
                {
                    if (validInput)
                    {
                        switch (thisAnswer.charAt(i))
                        {
                            case 'A':
                                fullAnswers[i*6]++;
                                if (correctAnswers.charAt(i) == 'A') correct++;
                                else incorrect++;
                                continue;
                            case 'B':
                                fullAnswers[(i * 6)+1]++;
                                if (correctAnswers.charAt(i) == 'B') correct++;
                                else incorrect++;
                                continue;
                            case 'C':
                                fullAnswers[(i * 6)+2]++;
                                if (correctAnswers.charAt(i) == 'C') correct++;
                                else incorrect++;
                                continue;
                            case 'D':
                                fullAnswers[(i * 6)+3]++;
                                if (correctAnswers.charAt(i) == 'D') correct++;
                                else incorrect++;
                                continue;
                            case 'E':
                                fullAnswers[(i * 6)+4]++;
                                if (correctAnswers.charAt(i) == 'E') correct++;
                                else incorrect++;
                                continue;
                            case ' ':
                                fullAnswers[(i* 6)+5]++;
                                blank++;
                                continue;
                            default: validInput = false;
                        }
                    }
                }
                
                if (validInput)
                {
                    lineCount++;
                    System.out.println("Student #" + lineCount + "'s responses: " + thisAnswer);
                    studentTally.addAll(Arrays.asList(correct, incorrect, blank));
                }
            }
        }
        System.out.println("We have reached \"end of file!\"\n");
        System.out.println("Thank you for the data on " + lineCount + " students. Here's the analysis:\n");
        
        System.out.println("Student #    Correct    Incorrect    Blank");
        System.out.println("---------    -------    ---------    -----");
        for (int k = 0; k < studentTally.size() / 3; k++)
        {
            System.out.println("    " + (k + 1) + "           " + studentTally.get(k*3) + "          " + studentTally.get(k*3 + 1)
            + "           " + studentTally.get(k*3 + 2));
        }
        
        System.out.println("\n");
        System.out.println("QUESTION ANALYSIS (* marks the correct response)");
        System.out.println("-----------------\n");
        
        for (int j = 0; j < numOfQs; j++)
        {
            System.out.println("Question #" + (j+1) + ":");
            switch (correctAnswers.charAt(j))
            {
                case 'A':
                    System.out.println("A*      B       C       D       E       Blank");
                    break;
                case 'B':
                    System.out.println("A       B*      C       D       E       Blank");
                    break;
                case 'C':
                    System.out.println("A       B       C*      D       E       Blank");
                    break;
                case 'D':
                    System.out.println("A       B       C       D*      E       Blank");
                    break;
                case 'E':
                    System.out.println("A       B       C       D       E*      Blank");
                    break;
                default: System.out.println("Something went wrong.");
            }
            int ans1 = fullAnswers[(j*6)];
            int ans2 = fullAnswers[(j*6)+1];
            int ans3 = fullAnswers[(j*6)+2];
            int ans4 = fullAnswers[(j*6)+3];
            int ans5 = fullAnswers[(j*6)+4];
            int ans6 = fullAnswers[(j*6)+5];
            
            System.out.println(fullAnswers[j*6] + "       " + fullAnswers[(j*6)+1] + "       " + fullAnswers[(j*6)+2] + "       " + 
            fullAnswers[(j*6)+3] + "       " + fullAnswers[(j*6)+4] + "       " + fullAnswers[(j*6)+5]);
            
            inFile.close();
            
            // PERCENTAGE BULLSHIT TESTING AAAAAAGGGGGHH
            System.out.println((fullAnswers[j*6] * (float)100) / numOfQs);
            System.out.println(((float)fullAnswers[j*6] * (float)100) / (float)numOfQs);
            System.out.println((fullAnswers[j*6] * 100.0f) / numOfQs);
        }
    }
}