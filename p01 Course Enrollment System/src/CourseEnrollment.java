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


// Below is a javadoc class header to complete
/**
 * The CourseEnrollment class provides methods for managing course enrollment, including creating
 * enrollment lists, printing student rosters and waitlists, enrolling students, and dropping
 * courses based on information from the persons name, Wisconsin school email, and the persons
 * Wisconsin student ID.
 * 
 * @author Dylan Zulkosky
 *
 */

public class CourseEnrollment {

  /**
   * Create an empty new list String[][] with a given capacity. The list should be empty, meaning
   * that it must contain null references only. For instance if the capacity of the list to create
   * is 4, the returned array must contain 4 null references.
   * 
   * @param capacity maximum number of students to be stored in the list. We assume that capacity is
   *                 greater than zero.
   * @return the created list which contains null references only
   */
  public static String[][] createEmptyList(int capacity) {
    String[][] emptyList = new String[capacity][];

    for (int i = 0; i < capacity; i++) {
      emptyList[i] = null;
    }

    return emptyList;
  }

  /**
   * Prints the list of all the students enrolled in the course, with respect to the following
   * format.
   * 
   * Maximum capacity: enrollment_capacity<BR>
   * Number of enrolled students: size<BR>
   * 1. name (email, campusId)<BR>
   * 2. name (email, campusId)<BR>
   * ...
   * 
   * Every entry must be in a newline. Each of the students records is printed in the format:
   * "order. name (email, campusId)"
   * 
   * where order represents index+1 of the student records in roster (orders are in the range
   * 1..size and NOT in the range 0..size-1), name, email, and campusId represent the name, email
   * address, and campusId of the enrolled student.
   * 
   * We assume all inputs are valid.
   * 
   * @param roster an oversize two-dimensional of strings storing student records. When roster[i] is
   *               not null, it references a one-dimensional array of strings whose length is
   *               exactly three, and where roster[i][0], roster[i][1], and roster[i][2]
   *               respectively represent the name, email address, and campus Id of the student
   *               record stored at index i.
   * @param size   the number of student records stored in roster.
   */
  public static void printRoster(String[][] roster, int size) {
    // Print maximum capacity and number of enrolled students
    int enrollmentCapacity = roster.length;
    System.out.println("Maximum capacity: " + enrollmentCapacity);
    System.out.println("Number of enrolled students: " + size);

    // Iterate through the roster and print student records
    for (int i = 0; i < size; i++) {
      String[] student = roster[i];
      String name = student[0];
      String email = student[1];
      String campusId = student[2];

      // Print student record in the specified format
      System.out.println((i + 1) + ". " + name + " (" + email + ", " + campusId + ")");
    }
  }

  /**
   * Prints the list of all the students in the waitlist the course, with respect to the following
   * format.
   * 
   * Waitlist capacity: waitlist_capacity<BR>
   * 1. name (email, campusId)<BR>
   * 2. name (email, campusId)<BR>
   * ...
   * 
   * Every entry must be in a newline. Each of the students records is printed in the format:
   * "order. name (email, campusId)"
   * 
   * where order starts at 1 for the student stored at index 0, name, email, and campusId represent
   * the name, email address, and campusId of the waitlisted student.
   * 
   * We assume all inputs are valid.
   * 
   * @param waitlist a perfect size non-compact two-dimensional of strings storing student records.
   *                 When waitlist[i] is not null, it references a one-dimensional array of strings
   *                 whose length is exactly three, and where waitlist[i][0], waitlist[i][1], and
   *                 waitlist[i][2] respectively represent the name, email address, and campus Id of
   *                 the student record stored at index i.
   */
  public static void printWaitlist(String[][] waitlist) {
    // Get the waitlist capacity
    int waitlistCapacity = waitlist.length;

    // Print the waitlist capacity
    System.out.println("Waitlist capacity: " + waitlistCapacity);

    // Initialize a counter for the order
    int order = 1;

    // Iterate through the waitlist
    for (int i = 0; i < waitlistCapacity; i++) {
      // Check if the current entry is not null and has a length of 3
      if (waitlist[i] != null && waitlist[i].length == 3) {
        // Extract student information
        String name = waitlist[i][0];
        String email = waitlist[i][1];
        String campusId = waitlist[i][2];

        // Print the student record in the specified format
        System.out.println(order + ". " + name + " (" + email + ", " + campusId + ")");

        // Increment the order
        order++;
      }
    }
  }


  /**
   * Returns the index of the student record having an exact match with campusId. The search is made
   * in a two-dimensional oversize array. We assume that campusId values are unique.
   * 
   * @param campusId a 10-digits string. We assume that the format of campusID is valid.
   * @param list     an oversize two-dimensional of strings storing student records. When list[i] is
   *                 not null, it references a one-dimensional array of strings whose length is
   *                 exactly three, and where list[i][0], list[i][1], and list[i][2] respectively
   *                 represent the name, email address, and campus Id of the student record stored
   *                 at index i. We assume that (1) list is not null, (2) its length is greater than
   *                 zero, and (3) it stores valid/correct student records. We also assume that when
   *                 i is greater or equal to size, list[i] is null.
   * @param size     the number of student records stored in list.
   * @return the index of the record containing an exact match with campusId, and -1 if no match
   *         found.
   */
  public static int indexOf(String campusId, String[][] list, int size) {
    for (int i = 0; i < size; i++) {
      // Check if the current entry is not null and has a length of 3
      if (list[i] != null && list[i].length == 3) {
        // Extract campusId from the current student record
        String recordCampusId = list[i][2];

        // Check if the extracted campusId matches the target campusId
        if (campusId.equals(recordCampusId)) {
          return i; // Return the index of the matching record
        }
      }
    }

    // Return -1 if no match is found
    return -1;
  }

  /**
   * Returns the index of the student record having an exact match with campusId. The search is made
   * in a two-dimensional non-compact perfect size array. We assume that campusId values are unique.
   * 
   * @param campusId a 10-digits string. We assume that the format of campusID is valid.
   * @param list     a non-compact perfect-size two-dimensional of strings storing student records.
   *                 When list[i] is not null, it references a one-dimensional array of strings
   *                 whose length is exactly three, and where list[i][0], list[i][1], and list[i][2]
   *                 respectively represent the name, email address, and campus Id of the student
   *                 record stored at index i. We assume that (1) list is not null, (2) its length
   *                 is greater than zero, and (3) it stores valid/correct student records. We also
   *                 assume that null references can be at any position of the array list and NOT
   *                 necessary pushed to the back of the array.
   * @return the index of the record containing an exact match with campusId, and -1 if no match
   *         found.
   */
  public static int indexOf(String campusId, String[][] list) {
    for (int i = 0; i < list.length; i++) {
      // Check if the current entry is not null and has a length of 3
      if (list[i] != null && list[i].length == 3) {
        // Extract campusId from the current student record
        String recordCampusId = list[i][2];

        // Check if the extracted campusId matches the target campusId
        if (campusId.equals(recordCampusId)) {
          return i; // Return the index of the matching record
        }
      }
    }

    // Return -1 if no match is found
    return -1;
  }


  // We assume the addWaitlist method is only called when the course reaches its enrollment capacity
  /**
   * Adds the student record {name, email, campusId} to the first available position in the
   * waitlist, meaning to the first null reference, if the course pre-requisites are satisfied, the
   * student record is not already in the waitlist, and there is a room to add the new student
   * record to the waitlist.
   * 
   * This method prints the following message is the student record is successfully added to the
   * waitlist:
   * 
   * "You are successfully added to the waitlist of this course."
   * 
   * This method prints the following error message if the pre-requisites of the course are not
   * satisfied:
   * 
   * "Error: You do not meet the prerequisite(s) for this course."
   * 
   * This method prints the following error message if a matching student record is already in the
   * list:
   * 
   * "Error: You are already in the waitlist of this course."
   * 
   * This method prints the following error message if the waitlist is full (meaning it does not
   * contain any null reference).
   * 
   * "Error: Course closed! Waitlist full."
   * 
   * @param name                  name of a student. We assume that name is not null and is not
   *                              blank.
   * @param email                 email address of the student to add. We assume that email ends
   *                              with "@wisc.edu"
   * @param campusId              10-digits campusId. We assume that campusId is valid
   * @param prerequisiteSatisfied boolean, evaluated to true if the prerequisite(s) for this course
   *                              are satisfied, false otherwise.
   * 
   * @param waitlist              a perfect size two-dimensional non-compact array storing an
   *                              ordered list of student records waiting to secure a spot in the
   *                              course. Every of the student records is a one-dimensional array of
   *                              strings whose length is exactly 3. A student record is represented
   *                              by the triplet {name, email, campusId}. This means that
   *                              roster[i][0], roster[i][1], and roster[i][2] represent the name,
   *                              email address, and campusId of the record stored at index i within
   *                              the roster array. Null references can be at any position in the
   *                              list array.
   * @return true if the student record was successfully added to the waitlist, and false otherwise.
   */
  public static boolean addWaitlist(String name, String email, String campusId,
      boolean prerequisiteSatisfied, String[][] waitlist) {
    // Check if prerequisites are satisfied
    if (!prerequisiteSatisfied) {
      System.out.println("Error: You do not meet the prerequisite(s) for this course.");
      return false;
    }

    // Check if the student is already in the waitlist
    for (int i = 0; i < waitlist.length; i++) {
      if (waitlist[i] != null && waitlist[i].length == 3) {
        // Extract campusId from the current student record
        String recordCampusId = waitlist[i][2];

        // Check if campusId matches
        if (campusId.equals(recordCampusId)) {
          System.out.println("Error: You are already in the waitlist of this course.");
          return false;
        }
      }
    }

    // Check if there is an available position in the waitlist
    int availablePosition = -1;
    for (int i = 0; i < waitlist.length; i++) {
      if (waitlist[i] == null) {
        availablePosition = i;
        break;
      }
    }

    // If there's an available position, add the student to the waitlist
    if (availablePosition != -1) {
      // Create the student record
      String[] studentRecord = {name, email, campusId};

      // Add the student record to the waitlist
      waitlist[availablePosition] = studentRecord;

      // Print success message
      System.out.println("You are successfully added to the waitlist of this course.");
      return true;
    } else {
      // Print error message if the waitlist is full
      System.out.println("Error: Course closed! Waitlist full.");
      return false;
    }
  }


  /**
   * Enrolls one student given their name, email address and campusId in a specific course. The
   * course enrollment is defined by the course roster (a 2D oversize array) and a waitlist (a 2D
   * compact perfect size array).
   * 
   * 
   * If the student (1) has NOT been already enrolled in the course, (2) satisfies the course
   * pre-requisites and (3) the course roster did not reach each capacity, the student record {name,
   * email, campusId} is added to the end of the array roster, and the size of the roster is
   * incremented by one. In this case, the student is successfully enrolled in the course. The
   * method prints the exact following message and it returns the new size of the roster.
   * 
   * "You are successfully enrolled in this class."
   * 
   * In case there is a match with the student record in the waitlist, the student record must be
   * removed off the waitlist after it is successfully added to the roster. Note that the waitlist
   * is a non-compact perfect size 2D array, meaning that setting the reference of an element stored
   * at a given index to null, removes it off the array.
   * 
   * If the student is already enrolled in the course, no changes are made to the contents of the
   * course enrollment lists (roster and waitlist). The method returns the current size of the
   * roster, and prints the exact following message.
   * 
   * "Error: You are already enrolled in this class."
   * 
   * If the roster is full (reached its capacity), this method does not make any changes to the
   * contents of roster or the waitlist. The method prints the exact following message and it
   * returns the current size of the roster.
   * 
   * "The course is full. Please make another selection or try adding yourself to the waitlist."
   * 
   * If the pre-requisites of the course are NOT satisfied, no changes will be made to the course
   * enrollment lists (roster and waitlist). The method prints the exact following message and it
   * returns the current size of the roster.
   * 
   * "Error: You do not meet the prerequisite(s) for this course."
   * 
   * @param name                  name of a student. We assume that name is not null and is not
   *                              blank.
   * @param email                 email address of the student to add. We assume that email ends
   *                              with "@wisc.edu"
   * @param campusId              10-digits campusId. We assume that campusId is valid
   * @param prerequisiteSatisfied boolean, evaluated to true if the prerequisite(s) for this course
   *                              are satisfied, false otherwise.
   * @param roster                2D oversize array which stores the records of students enrolled in
   *                              the course. Every of the student records is a one-dimensional
   *                              array of strings whose length is exactly 3. A student record is
   *                              represented by the triplet {name, email, campusId}. This means
   *                              that roster[i][0], roster[i][1], and roster[i][2] represent the
   *                              name, email address, and campusId of the record stored at index i
   *                              within the roster array.
   * @param size                  the number of student records stored in roster
   * @param waitlist              2D non-compact perfect size arrays storing the records {name,
   *                              email, campusId} of students in the waitlist of this course.
   * @return the size of roster after successfully enrolling the student in the course, or the input
   *         size if the enrollment fails for any reason.
   */
  public static int enrollOneStudent(String name, String email, String campusId,
      boolean prerequisiteSatisfied, String[][] roster, int size, String[][] waitlist) {

    // Check if the student is already enrolled in the course
    if (isEnrolled(name, roster, size)) {
      System.out.println("Error: You are already enrolled in this class.");
      return size; // No changes in roster size
    }

    // Check if the course prerequisites are satisfied
    if (!prerequisiteSatisfied) {
      System.out.println("Error: You do not meet the prerequisite(s) for this course.");
      return size; // No changes in roster size
    }

    // Check if the roster is full
    if (size >= roster.length) {
      System.out.println(
          "The course is full. Please make another selection or try adding yourself to the waitlist.");
      return size; // No changes in roster size
    }

    // If all conditions are met, enroll the student
    // Create a new student record
    String[] studentRecord = {name, email, campusId};

    // Add the student to the roster and increment the size
    roster[size] = studentRecord;
    size++;

    // Check if the student is in the waitlist and remove them if necessary
    removeFromWaitlist(name, waitlist);

    // Print enrollment message
    System.out.println("You are successfully enrolled in this class.");

    return size; // Return the new size of the roster
  }

  // Helper method to check if a student is already enrolled
  private static boolean isEnrolled(String name, String[][] roster, int size) {
    for (int i = 0; i < size; i++) {
      if (name.equals(roster[i][0])) {
        return true;
      }
    }
    return false;
  }

  // Helper method to remove a student from the waitlist
  private static void removeFromWaitlist(String name, String[][] waitlist) {
    for (int i = 0; i < waitlist.length; i++) {
      if (waitlist[i] != null && name.equals(waitlist[i][0])) {
        waitlist[i] = null; // Remove the student from the waitlist
      }
    }
  }


  /**
   * Removes the student record whose campusId matches the one provided as input off the roster
   * list. We assume that all the inputs are valid and correctly formatted. We also assume that
   * campusId are unique.
   * 
   * The roster array is an ordered oversize array. This means that a successful removal operation
   * must involve a shift operation if the element to be removed is in the range 0..size-2. This
   * means that this method must preserve the order of precedence of the student records stored in
   * the roster array. The size of roster must be decremented by one after successfully removing the
   * student record off the roster.
   * 
   * This method prints the following message if the course was successfully dropped.
   * 
   * "You just dropped the course. This action cannot be revoked."
   * 
   * This method does not make any changes to the contents of the roster array and prints the
   * following message if there is no student record matching the input campusId:
   * 
   * "Error: You are not enrolled in the course!"
   * 
   * @param campusId 10-digits campusId. We assume that campusId is valid
   * @param roster   2D oversize array storing students records. Every of the student records is a
   *                 one-dimensional array of strings whose length is exactly 3. A student record is
   *                 represented by the triplet {name, email, campusId}. This means that
   *                 roster[i][0], roster[i][1], and roster[i][2] represent the name, email address,
   *                 and campusId of the record stored at index i within the roster array.
   * @param size     number of student records stored in roster
   * @return the size of the roster after the student record was successfully removed, or the same
   *         input size if the drop operation fails for any reason.
   */
  public static int dropCourse(String campusId, String[][] roster, int size) {
    // Loop through the roster to find the matching student record
    int indexToRemove = -1; // Initialize with an invalid index

    for (int i = 0; i < size; i++) {
      if (roster[i][2].equals(campusId)) {
        indexToRemove = i; // Found a match, store the index
        break; // Exit the loop once a match is found
      }
    }

    // Check if a matching student record was found
    if (indexToRemove != -1) {
      // Shift elements to remove the record while maintaining order
      for (int i = indexToRemove; i < size - 1; i++) {
        roster[i] = roster[i + 1];
      }

      // Decrement the size of the roster
      size--;

      // Print a success message
      System.out.println("You just dropped the course. This action cannot be revoked.");
    } else {
      // Print an error message
      System.out.println("Error: You are not enrolled in the course!");
    }

    // Return the updated size of the roster
    return size;
  }
}
