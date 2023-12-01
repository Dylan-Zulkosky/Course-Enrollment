//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Creating A Course Enrollment System For Students
// Course: CS 300 Fall 2023
//
// Author: Dylan Zulkosky
// Email: dzulkosky@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: None
// Partner Email: None
// Partner Lecturer's Name: None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Youtube video titled "Returning Arrays from a Method in Java - Array Return Type - Java
//////////////// Programming Tutorial - Appficial" helped me understand arrays more and how to
//////////////// properly get the data out of the array - Channel: Appficial
// Online Sources: https://greenteapress.com/thinkjava7/html/chapter-07.html#sec86 - also helped
//////////////// more with understand arrays, how to run through them, and how to implement better
//
///////////////////////////////////////////////////////////////////////////////
// Be sure to credit the outside help section in the file header
import java.util.Scanner;

/**
 * This class implements the Driver Application for cs300 Fall 2023 p01 Course Enrollment System
 *
 */
public class CourseEnrollmentDriver {

  // welcome, good bye, and syntax error messages
  private static final String WELCOME_MSG = "--- Welcome to the Course Enrollment System! ----";
  private static final String GOOD_BYE_MSG = "---------- BYE! Thanks for using our App! ----------";
  private static final String SYNTAX_ERROR_MSG = "Syntax Error: Please enter a valid command!";
  private static final String NO_COURSE_ENROLLMENT_MSG =
      "Error: Create a new course enrollment first!";


  /**
   * Main method that launches this driver application
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    // run application
    System.out.println(WELCOME_MSG); // display welcome message
    // Create a scanner to read the user inputs
    Scanner scanner = new Scanner(System.in);
    // read and process user command lines
    processUserCommands(scanner);
    scanner.close();// close the scanner
    System.out.println(GOOD_BYE_MSG);// display good bye message
  }


  /**
   * Prints out the menu of this application
   */
  private static void displayMenu() {
    System.out.println("\n================================ MENU ===============================");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <roster_capacity> <waitlist_capacity>] Create a new course enrollment");
    System.out.println("[2 <name>:<wisc_email>:<campus_ID>:boolean(true/false)] Enroll student");
    System.out.println("[3 <name>:<wisc_email>:<campus_ID>:boolean] Add student to waitlist");
    System.out.println("[4 <campus_ID>] Drop the course");
    System.out.println("[5] Print roster");
    System.out.println("[6] Print waitlist");
    System.out.println("[7] Logout and EXIT");
    System.out.println("-----------------------------------------------------------------------");
  }


  /**
   * Reads and processes user command lines
   */
  private static void processUserCommands(Scanner scanner) {

    String promptCommandLine = "ENTER COMMAND: ";
    String command = null; // variable to save the user command line

    // define a roster and a waitlist variables for the course enrollment, not yet initialized
    String[][] roster = null;
    int size = 0; // size of roster

    String[][] waitlist = null; // waitlist of the course

    // read and process user command lines until the user quits the application
    do {

      displayMenu(); // display the main menu
      // read user command line
      System.out.print(promptCommandLine);
      command = scanner.nextLine();
      // blank command
      if (command == null || command.isBlank()) {
        System.out.println(SYNTAX_ERROR_MSG); // syntax error message
        continue; // go to the next iteration
      }

      // Exit the loop if the user command is 7 for Quit
      if (command.charAt(0) == '7') {
        break; // exit the loop
      }

      // Create new course enrollment lists
      if (command.charAt(0) == '1') {
        // [1 <roster_capacity> <waitlist_capacity>] Create a new course enrollment
        String[] parts = command.split(" ");
        if (parts.length != 3) {// syntax error
          System.out.println(SYNTAX_ERROR_MSG);
          continue;
        }
        // valid syntax: create lists
        roster = CourseEnrollment.createEmptyList(Integer.parseInt(parts[1]));
        waitlist = CourseEnrollment.createEmptyList(Integer.parseInt(parts[2]));
        continue;
      }

      else { // any other type of command.
        // Check whether the course enrollment lists were created
        if (roster == null || waitlist == null) {
          System.out.println(NO_COURSE_ENROLLMENT_MSG);
          continue;
        }
        // process the user command line for the other options
        switch (command.charAt(0)) {
          // Enroll student
          case '2': // [2 <name>:<wisc_email>:<campus_ID>:boolean(true/false)] Enroll student
            String[] parts = command.split(" ");
            if (parts.length != 2) {// syntax error
              System.out.println(SYNTAX_ERROR_MSG);
              continue;
            }
            // Parse the command and extract parameters
            String[] studentInfo = parts[1].split(":");
            if (studentInfo.length != 4) {
              System.out.println(SYNTAX_ERROR_MSG);
              continue;
            }
            String name = studentInfo[0];
            String email = studentInfo[1];
            String campusId = studentInfo[2];
            boolean prerequisiteSatisfied = Boolean.parseBoolean(studentInfo[3]);

            // Enroll the student using the CourseEnrollment class method
            size = CourseEnrollment.enrollOneStudent(name, email, campusId, prerequisiteSatisfied,
                roster, size, waitlist);
            break;

          case '3': // [3 <name>:<wisc_email>:<campus_ID>:boolean] Add student to waitlist
            String[] parts3 = command.split(" ");
            if (parts3.length != 2) {// syntax error
              System.out.println(SYNTAX_ERROR_MSG);
              continue;
            }
            // Parse the command and extract parameters
            String[] studentInfo3 = parts3[1].split(":");
            if (studentInfo3.length != 4) {
              System.out.println(SYNTAX_ERROR_MSG);
              continue;
            }
            String name3 = studentInfo3[0];
            String email3 = studentInfo3[1];
            String campusId3 = studentInfo3[2];
            boolean prerequisiteSatisfied3 = Boolean.parseBoolean(studentInfo3[3]);

            // Add the student to the waitlist using the CourseEnrollment class method
            CourseEnrollment.addWaitlist(name3, email3, campusId3, prerequisiteSatisfied3,
                waitlist);
            break;

          case '4': // [4 <campus_ID>] Drop the course
            String[] parts4 = command.split(" ");
            if (parts4.length != 2) {// syntax error
              System.out.println(SYNTAX_ERROR_MSG);
              continue;
            }
            String campusId4 = parts4[1];

            // Drop the student from the course using the CourseEnrollment class method
            size = CourseEnrollment.dropCourse(campusId4, roster, size);
            break;

          case '5': // [5] Print roster
            CourseEnrollment.printRoster(roster, size);
            break;

          case '6': // [6] Print waitlist
            CourseEnrollment.printWaitlist(waitlist);
            break;

          default:
            System.out.println(SYNTAX_ERROR_MSG); // Syntax Error

        }
      }

    } while (command.charAt(0) != '7');

  }

}

