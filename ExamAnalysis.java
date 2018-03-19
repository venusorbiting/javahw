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
    public static void main (String [] args)
    {
        Scanner keyboard = new Scanner (System.in);
        
        System.out.println("I hope you are ready to begin...\n");
        System.out.print("Please type the correct answers to the exam questions, one right after the other: ");
        String correctAnswers = keyboard.nextLine();
        int numOfQs = correctAnswers.length();
        
        System.out.print("What is the name of the file containing each student's responses to the " + numOfQs + " questions? ");
        File f = null;
        Scanner inFile = null;
        try
        {
            f = new File (keyboard.nextLine());
            inFile = new Scanner (f);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("That's not a valid file! Please try again.");
            System.exit(0);
        }
        
        boolean validInput = true;
        
        // array list to store each student's # of correct, incorrect and blank responses
        ArrayList<ArrayList> ar = new ArrayList<> ();
        // array list to store each student's actual responses
        ArrayList<ArrayList> ar2 = new ArrayList<> ();
        
        while (inFile.hasNextLine())
        {
            validInput = true;
            String thisAnswer = inFile.nextLine();
            ArrayList<Integer> student = new ArrayList<>();
            ArrayList<Character> answers = new ArrayList<>();
            
            // only bothers checking this student's answers if the string is the correct length
            if (thisAnswer.length() == numOfQs)
            {
                for (int i = 0; i < numOfQs; i++)
                {
                    // only continues to check answers as long as an invalid answer (e.g. F, Q, P, 5) has not been found
                    if (validInput)
                    {
                        switch (thisAnswer.charAt(i))
                        {
                            // each answer, whether correct or incorrect, is stored in answers array list
                            case 'A':
                                answers.add('A');
                                // student array keeps track of whether each answer was correct, incorrect or blank
                                if (correctAnswers.charAt(i) == 'A') student.add(0);
                                else student.add(1);
                                continue;
                            case 'B':
                                answers.add('B');
                                if (correctAnswers.charAt(i) == 'B') student.add(0);
                                else student.add(1);
                                continue;
                            case 'C':
                                answers.add('C');
                                if (correctAnswers.charAt(i) == 'C') student.add(0);
                                else student.add(1);
                                continue;
                            case 'D':
                                answers.add('D');
                                if (correctAnswers.charAt(i) == 'D') student.add(0);
                                else student.add(1);
                                continue;
                            case 'E':
                                answers.add('E');
                                if (correctAnswers.charAt(i) == 'E') student.add(0);
                                else student.add(1);
                                continue;
                            case ' ':
                                answers.add(' ');
                                student.add(2);
                                continue;
                            default: validInput = false;
                        }
                    }
                }
                
                // only prints the responses and adds their data to the array lists if the line of input was valid
                if (validInput)
                {
                    System.out.println("Student #" + (ar.size() + 1) + "'s responses: " + thisAnswer);
                    ar.add(student);
                    ar2.add(answers);
                }
            }
        }
        System.out.println("We have reached \"end of file!\"\n");
        System.out.println("Thank you for the data on " + ar.size() + " students. Here's the analysis:\n");
        
        System.out.println("Student #    Correct    Incorrect    Blank");
        System.out.println("---------    -------    ---------    -----");
        
        int correct = 0;
        int incorrect = 0;
        int blank = 0;
        for (int j = 0; j < ar.size(); j++)
        {
            correct = 0;
            incorrect = 0;
            blank = 0;
            // for each question, tallys up how many responses were correct/incorrect/blank
            for (int k = 0; k < ar.get(j).size(); k++)
            {
                Integer current = (Integer)ar.get(j).get(k);
                switch (current)
                {
                    case 0:
                        correct++;
                        continue;
                    case 1:
                        incorrect++;
                        continue;
                    case 2:
                        blank++;
                        continue;
                }
            }
            System.out.println((j + 1) + "            " + correct + "          " + incorrect + "            " + blank);
        }
        
        System.out.println("\nQUESTION ANALYSIS (* marks the correct response)");
        System.out.println("-----------------\n");
        
        int tallyA = 0;
        int tallyB = 0;
        int tallyC = 0;
        int tallyD = 0;
        int tallyE = 0;
        int tallyBlank = 0;
        
        for (int j = 0; j < numOfQs; j++)
        {
            System.out.println("Question #" + (j+1) + ":");
            // prints slightly different header based on which answer is correct for each given question
            switch (correctAnswers.charAt(j))
            {
                case 'A':
                    System.out.println("  A*      B       C       D       E       Blank");
                    break;
                case 'B':
                    System.out.println("  A       B*      C       D       E       Blank");
                    break;
                case 'C':
                    System.out.println("  A       B       C*      D       E       Blank");
                    break;
                case 'D':
                    System.out.println("  A       B       C       D*      E       Blank");
                    break;
                case 'E':
                    System.out.println("  A       B       C       D       E*      Blank");
                    break;
            }
            
            // resets tally counters to zero
            tallyA = 0;
            tallyB = 0;
            tallyC = 0;
            tallyD = 0;
            tallyE = 0;
            tallyBlank = 0;
            
            for (int k = 0; k < ar.size(); k++)
            {
                // for each question, tallys up how many students answered A, B, C, etc
                Character question = (Character)ar2.get(k).get(j);
                switch (question)
                {
                    case 'A':
                        tallyA++;
                        continue;
                    case 'B':
                        tallyB++;
                        continue;
                    case 'C':
                        tallyC++;
                        continue;
                    case 'D':
                        tallyD++;
                        continue;
                    case 'E':
                        tallyE++;
                        continue;
                    case ' ':
                        tallyBlank++;
                        continue;
                }
            }
            System.out.println("  " + tallyA + "       " + tallyB + "       " + tallyC +
            "       " + tallyD + "       " + tallyE + "       " + tallyBlank);
            
            int num = ar.size();
            System.out.printf("%.1f", (tallyA * 100.0) / num);
            System.out.print("%    ");
            System.out.printf("%.1f", (tallyB * 100.0) / num);
            System.out.print("%    ");
            System.out.printf("%.1f", (tallyC * 100.0) / num);
            System.out.print("%    ");
            System.out.printf("%.1f", (tallyD * 100.0) / num);
            System.out.print("%    ");
            System.out.printf("%.1f", (tallyE * 100.0) / num);
            System.out.print("%    ");
            System.out.printf("%.1f", (tallyBlank * 100.0) / num);
            System.out.println("%\n");
            
        }
        inFile.close();
    }
}