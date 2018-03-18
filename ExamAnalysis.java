/*  Alicia Curtis
    CS E-10b Spring 2018
    PS 5C
    Problem #6
    DESCRIPTION OF THE PROGRAM ***
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
        //String correctAnswers = keyboard.nextLine();
        String correctAnswers = "ABCEDBACED";            // REMOVE THIS, JUST FOR TESTING
        
        System.out.print("What is the name of the file containing each student's responses to the 10 questions? \n");
        // File f = new File (keyboard.nextLine());
        File f = new File ("exams.dat");                 // REMOVE THIS, JUST FOR TESTING
        Scanner inFile = new Scanner (f);
        // ADD A TRY/CATCH HERE        
        //if (!(f.exists() && f.canRead())) System.out.println("Sorry, that's not a valid file.");
        
        int lineCount = 0;
        int correct = 0;
        int incorrect = 0;
        int blank = 0;
        ArrayList<Integer> studentTally = new ArrayList<> ();
        while (inFile.hasNextLine())
        {
            correct = 0;
            incorrect = 0;
            blank = 0;
            String thisAnswer = inFile.nextLine();
            if (thisAnswer.length() == 10)
            {
                for (int i = 0; i < 10; i++)
                {
                    switch (thisAnswer.charAt(i))
                    {
                        case 'A':
                            if (correctAnswers.charAt(i) == 'A') correct++;
                            else incorrect++;
                            continue;
                        case 'B':
                            if (correctAnswers.charAt(i) == 'B') correct++;
                            else incorrect++;
                            continue;
                        case 'C':
                            if (correctAnswers.charAt(i) == 'C') correct++;
                            else incorrect++;
                            continue;
                        case 'D':
                            if (correctAnswers.charAt(i) == 'D') correct++;
                            else incorrect++;
                            continue;
                        case 'E':
                            if (correctAnswers.charAt(i) == 'E') correct++;
                            else incorrect++;
                            continue;
                        case ' ':
                            blank++;
                            continue;
                        default:
                            System.out.println("Not a valid grade");
                            break;          // ADD A CASE HERE TO IGNORE THE LINE
                    }
                }
                lineCount++;
                
                System.out.println("Student #" + lineCount + "'s responses: " + thisAnswer);
                System.out.println(correct + " " + incorrect + " " + blank);
                studentTally.addAll(Arrays.asList(correct, incorrect, blank));
                System.out.println("The student tally currently contains: " + studentTally);
            }
        }
        System.out.println("We have reached \"end of file!\"\n");
        System.out.println("Thank you for the data on " + lineCount + " students. Here's the analysis:\n");
        
        /* THINGS THAT NEED TO GO HERE:
        // Student   # Correct      Incorrect   Blank
         ~~~~~~~~~     ~~~~~~~      ~~~~~~~~~   ~~~~~
            1          7                2          1
            2          8                1          1
            etc
            
        QUESTION ANALYSIS (* marks the correct response)
        Question 1:
        
        A*      B       C       D       E       Blank
        4       2       1       1       0       1
        44.4%   22.2%   11.1%   11.1%   0.0%    11.1%
        
        Question 2:
        A       B*      C       D       E       Blank
        etc


*/
    }
}