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

import java.util.Arrays;

/**
 * This utility class implements unit tests to check the correctness of methods defined in the
 * CourseEnrollment class of the Course Enrollment System program.
 *
 */
public class CourseEnrollmentTester {


  /**
   * Ensures the correctness of the createEmptyList() method
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean createEmptyListTester() {
    String errMsg = "Bug detected: createEmptyList did not return the expected array.";
    // Create an empty list String[][] whose capacity is 5
    String[][] actual = CourseEnrollment.createEmptyList(5);
    String[][] expected = new String[5][];
    // same as expected = new String[][]{null, null, null, null, null}

    if (!Arrays.deepEquals(actual, expected)) {
      // Recommended: descriptive error messages to help locating the bug
      System.out.println(errMsg);
      System.out.println("Expected: " + Arrays.deepToString(expected));
      System.out.println("Actual: " + Arrays.deepToString(actual));
      return false;
    }


    // Try a different capacity: create an empty list String[][] whose capacity is 8
    actual = CourseEnrollment.createEmptyList(8);
    expected = new String[8][];
    // same as expected = new String[][]{null, null, null, null, null}

    if (!Arrays.deepEquals(actual, expected)) {
      // descriptive error messages to help locating the bug
      System.out.println(errMsg);
      System.out.println("Expected: " + Arrays.deepToString(expected));
      System.out.println("Actual: " + Arrays.deepToString(actual));
      return false;
    }

    return true; // expected behavior verified, no bugs detected!
  }

  /**
   * Ensures the correctness of the indexOf(String, String[][]) method
   * 
   * Expected behavior to be verified:<BR>
   * (+) Correct index returned for multiple cases (normal and edge cases)<BR>
   * (+) No changes made to the contents of the input list<BR>
   * (+) Handles null input list gracefully<BR>
   * (+) Handles no match in the array gracefully<BR>
   * 
   * @return true if the tester verifies correct functionality, false if at least one bug is
   *         detected
   */
  public static boolean indexOfPerfectSizeArrayTester() {
    // Define four test cases

    // (1) edge case: match found at index 0
    String[][] testArray1 = {{"Joshua", "joshua@wisc.com", "1234567890"}, null,
        {"Spencer", "spencer@wisc.com", "2345678901"}};
    int result1 = CourseEnrollment.indexOf("1234567890", testArray1);
    if (result1 != 0) {
      System.out.println("Test case 1 failed. Expected: 0, Actual: " + result1);
      return false;
    }

    // (2) edge case: match found at index length-1 considering a full input array
    String[][] testArray2 =
        {{"Noah", "noah@wisc.com", "1234567890"}, {"Ben", "ben@wisc.com", "2345678901"}};
    int result2 = CourseEnrollment.indexOf("2345678901", testArray2);
    if (result2 != 1) {
      System.out.println("Test case 2 failed. Expected: 1, Actual: " + result2);
      return false;
    }

    // (3) normal case: match found at the middle of the input array
    String[][] testArray3 = {{"Jalen", "jalen@wisc.com", "1234567890"},
        {"Chuck", "chuck@wisc.com", "2345678901"}, {"Zach", "zach@wisc.com", "3456789012"}};
    int result3 = CourseEnrollment.indexOf("2345678901", testArray3);
    if (result3 != 1) {
      System.out.println("Test case 3 failed. Expected: 1, Actual: " + result3);
      return false;
    }

    // (4) normal case: no match found, -1 should be returned
    String[][] testArray4 = {{"Abby", "abby@wisc.com", "1234567890"},
        {"Kaylee", "kaylee@wisc.com", "2345678901"}, {"Jack", "jack@wisc.com", "3456789012"}};
    int result4 = CourseEnrollment.indexOf("9999999999", testArray4);
    if (result4 != -1) {
      System.out.println("Test case 4 failed. Expected: -1, Actual: " + result4);
      return false;
    }

    // (5) Handles null input list
    String[][] testArray5 = null;
    try {
      int result5 = CourseEnrollment.indexOf("1234567890", testArray5);
      System.out.println("Test case 5 failed. No exception thrown.");
      return false;
    } catch (NullPointerException e) {
      // Expected exception
    }

    // (6) Handles no match in the array
    String[][] testArray6 =
        {{"Emma", "emma@wisc.com", "1234567890"}, {"Conner", "conner@wisc.com", "2345678901"}};
    int result6 = CourseEnrollment.indexOf("9999999999", testArray6);
    if (result6 != -1) {
      System.out.println("Test case 6 failed. Expected: -1, Actual: " + result6);
      return false;
    }

    // All test cases passed
    return true;
  }

  /**
   * Ensures the correctness of the indexOf(String, String[][], int) method with an oversize size
   * array.
   * 
   * Expected behavior to be verified: (+) Correct index returned for multiple cases (normal and
   * edge cases) (+) No changes made to the contents of the input list
   * 
   * @return true if the tester verifies correct functionality, and false if at least one bug is
   *         detected
   */
  public static boolean indexOfOversizeSizeArrayTester() {
    // Create a test array with sample data
    String[][] testArray = new String[5][];
    testArray[0] = new String[] {"Nolan", "nolan@wisc.com", "12345"};
    testArray[1] = new String[] {"Joe", "joe@wisc.com", "23456"};
    testArray[2] = new String[] {"Anthony", "anthony@wisc.com", "34567"};
    testArray[3] = new String[] {"Aaron", "aaron@wisc.com", "45678"};
    testArray[4] = new String[] {"Emily", "emily@wisc.com", "56789"};

    // Test Case 1: Match found at index 0
    int result1 = CourseEnrollment.indexOf("12345", testArray, testArray.length);
    if (result1 != 0) {
      System.out.println("Test Case 1 failed: Expected result is 0, but got " + result1);
      return false;
    }

    // Test Case 2: Match found at index length-1 considering a full input array
    int result2 = CourseEnrollment.indexOf("56789", testArray, testArray.length);
    if (result2 != 4) {
      System.out.println("Test Case 2 failed: Expected result is 4, but got " + result2);
      return false;
    }

    // Test Case 3: Normal case - Match found at the middle of the input array
    int result3 = CourseEnrollment.indexOf("34567", testArray, testArray.length);
    if (result3 != 2) {
      System.out.println("Test Case 3 failed: Expected result is 2, but got " + result3);
      return false;
    }

    // Test Case 4: Normal case - No match found, should return -1
    int result4 = CourseEnrollment.indexOf("99999", testArray, testArray.length);
    if (result4 != -1) {
      System.out.println("Test Case 4 failed: Expected result is -1, but got " + result4);
      return false;
    }

    // Test Case 5: Edge case - Empty array, should return -1
    String[][] emptyArray = new String[0][];
    int result5 = CourseEnrollment.indexOf("54321", emptyArray, emptyArray.length);
    if (result5 != -1) {
      System.out.println("Test Case 5 failed: Expected result is -1, but got " + result5);
      return false;
    }

    return true; // All test cases passed
  }

  // Helper method to compare actual and expected oversize roster arrays
  /**
   * Helper method defined to help verifying the actual roster and waitlist arrays with respect to
   * the expected ones
   * 
   * @param methodName     name of the method being tested
   * @param actualRoster   actual roster
   * @param expectedRoster expected roster
   * @param actualSize     actual roster size
   * @param expectedSize   expected roster size
   * 
   * @return true if expected behavior satisfied, false if any bug is detected
   */
  private static boolean assessDeepEqualOversizeArraysHelper(String methodName,
      String[][] actualRoster, String[][] expectedRoster, int actualSize, int expectedSize) {
    // error messages
    String errMsgBadSize =
        "Bug detected: Your " + methodName + "() method did not return the expected size.";
    String errMsgBadRoster = "Bug detected: The contents of the roster array was not as expected "
        + "after " + "your " + methodName + "() method returned.";

    // check roster size
    if (actualSize != expectedSize) {
      System.out.println(errMsgBadSize);
      System.out.println("Expected size: " + expectedSize + ". Actual size: " + actualSize);
      return false;
    }

    // compare roster contents
    if (!Arrays.deepEquals(actualRoster, expectedRoster)) {
      System.out.println(errMsgBadRoster);
      System.out.println("Expected Roster: " + Arrays.deepToString(expectedRoster));
      System.out.println("Actual Roster: " + Arrays.deepToString(actualRoster));
      return false;
    }

    return true; // expected behavior satisfied, no bugs detected
  }

  // Helper method to compare actual and expected oversize roster arrays
  /**
   * Helper method defined to help verifying the actual roster and waitlist arrays with respect to
   * the expected ones
   * 
   * @param methodName       name of the method being tested
   * @param actualWaitlist   actual waitlist
   * @param expectedWaitlist expected waitlist
   * 
   * @return true if expected behavior satisfied, false if any bug is detected
   */
  private static boolean assessDeepEqualPerfectSizeArraysHelper(String methodName,
      String[][] actualWaitlist, String[][] expectedWaitlist) {
    // error message
    String errMsgBadWaitlist =
        "Bug detected: The contents of the waitlist array was not as expected after " + "your "
            + methodName + "() method returned";

    // compare waitlist contents
    if (!Arrays.deepEquals(actualWaitlist, expectedWaitlist)) {
      System.out.println(errMsgBadWaitlist);
      System.out.println("Expected Waitlist: " + Arrays.deepToString(expectedWaitlist));
      System.out.println("Actual Waitlist: " + Arrays.deepToString(actualWaitlist));
      return false;
    }
    return true; // expected behavior satisfied, no bugs detected
  }

  /**
   * Ensures the correctness of the enrollOneStudent() method when called to enroll one student
   * record in the course. The course did not reach its capacity and the course pre-requisites are
   * satisfied.
   * 
   * Expected behavior to be verified:<BR>
   * (+) Student record correctly added to the end of the roster array<BR>
   * (+) No changes made to the waitlist array<BR>
   * (+) Correct size returned
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean enrollOneStudentTester() {
    // You do not need to make changes to this method
    // create a waitlist array. We can consider a normal case: not-empty and not-full waitlist)
    String[][] actualWaitlist = new String[][] {{"Andy", "andy@wisc.edu", "9087654321"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};

    // No changes to the waitlist are expected
    String[][] expectedWaitlist = new String[][] {{"Andy", "andy@wisc.edu", "9087654321"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};

    // This method considers three test cases:
    // (1) edge case: adding to an empty roster
    // (2) normal case: adding to the end of a non-empty roster
    // (3) edge case: after adding the student record, the roster is full

    // To avoid code redundancy, we defined a helper method named verifyCorrectBehaviorHelper() to
    // help verifying the expected behavior for each of the above test cases.

    // --------------------------------------------------------------------------
    // (1) edge case Enroll a student considering an empty roster oversize array
    // enroll one student satisfying prerequisites
    // Create an empty roster
    String[][] actualRoster = new String[3][];
    int actualSize = 0;

    // Try to enroll George
    actualSize = CourseEnrollment.enrollOneStudent("George", "george@wisc.edu", "9780563421", true,
        actualRoster, actualSize, actualWaitlist);
    // expected roster and its size
    String[][] expectedRoster =
        new String[][] {{"George", "george@wisc.edu", "9780563421"}, null, null};
    int expectedSize = 1;
    boolean resultCase1 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);

    // --------------------------------------------------------------------------
    // (2) normal case: adding to the end of a non-empty roster
    actualRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};
    actualSize = 2;

    // Try to enroll Matt
    actualSize = CourseEnrollment.enrollOneStudent("Matt", "matt@wisc.edu", "9745632180", true,
        actualRoster, actualSize, actualWaitlist);
    // expected roster and its size
    expectedRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"}, null};
    expectedSize = 3;
    boolean resultCase2 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);

    // --------------------------------------------------------------------------
    // (3) edge case: after adding the student record, the roster is full
    actualRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"}, null};
    actualSize = 3;

    // Try to enroll Lisa
    actualSize = CourseEnrollment.enrollOneStudent("Lisa", "lisa@wisc.edu", "9784563211", true,
        actualRoster, actualSize, actualWaitlist);
    // expected roster and its size
    expectedRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"},
        {"Lisa", "lisa@wisc.edu", "9784563211"}};
    expectedSize = 4;
    boolean resultCase3 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);

    // Verify that all the above enrollOneStudent() method calls did not make any changes to the
    // contents of the input waitlist
    boolean assessWaitlistContents = assessDeepEqualPerfectSizeArraysHelper("enrollOneStudent",
        actualWaitlist, expectedWaitlist);

    // test passes only if each of the defined test scenarios passes
    return resultCase1 && resultCase2 && resultCase3 && assessWaitlistContents;
  }


  /**
   * Ensures the correctness of the enrollOneStudent() method when called to enroll one student
   * record in the course. The student record is in the waitlist, course pre-requisites are
   * satisfied, and there is room in the roster to enroll the student in the course.
   * 
   * Expected behavior to be verified:<BR>
   * (+) Student record correctly added to the end of the roster array<BR>
   * (+) Matching student correctly record removed off the waitlist<BR>
   * (+) Correct size returned
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean enrollOneStudentMoveFromWaitlistTester() {
    // Create a sample waitlist with some student records
    String[][] waitlist = {{"Carri", "carri@wisc.com", "8146279801"},
        {"Grace", "grace@wisc.com", "2176250987"}, {"Michael", "michael@wisc.com", "3172897635"}};

    // Create a sample roster with limited space (2 empty spaces) and some student records
    String[][] roster = {{"Luke", "luke@wisc.com", "1234567890"},
        {"Amber", "amber@wisc.com", "9167234567"}, {"", "", ""}, {"", "", ""}};

    // Get the initial sizes of the waitlist and roster
    int initialWaitlistSize = waitlist.length;
    int initialRosterSize = 2; // Limited space for roster

    // Choose a student record from the waitlist to enroll
    String nameToEnroll = "Carri";
    String emailToEnroll = "carri@wisc.com";
    String campusIdToEnroll = "8146279801";

    // Attempt to enroll the student, considering the waitlist if the roster is full
    int newSize = CourseEnrollment.enrollOneStudent(nameToEnroll, emailToEnroll, campusIdToEnroll,
        true, roster, initialRosterSize, waitlist);

    if (newSize == initialRosterSize) {
      // Roster size did not increase, so the student was added to the waitlist
      // Verify that the student record is now in the waitlist
      int index = CourseEnrollment.indexOf(campusIdToEnroll, waitlist, initialWaitlistSize);
      if (index == -1) {
        return false; // The student record should exist in the waitlist
      }
    } else if (newSize == initialRosterSize + 1) {
      // Roster size increased, so the student was enrolled
      // Verify that the student record is now in the roster
      int index = CourseEnrollment.indexOf(campusIdToEnroll, roster, newSize);
      if (index == -1) {
        return false; // The enrolled student record should exist in the roster
      }

      // Verify that the student record is no longer in the waitlist
      index = CourseEnrollment.indexOf(campusIdToEnroll, waitlist, initialWaitlistSize);
      if (index != -1) {
        return false; // The student record should not exist in the waitlist after enrollment
      }
    } else {
      return false; // Unexpected newSize value
    }

    // The test passes if the student was either added to the roster or waitlist as appropriate
    return true;
  }

  /**
   * Ensures the correctness of the dropCourse() method when called to remove an existing student
   * record from a course enrollment roster of the class.
   * 
   * Expected behavior to be verified:<BR>
   * (+) Matching student record correctly removed off the input roster array. Order of precedence
   * of the student records must be preserved.<BR>
   * (+) Correct size returned
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean successfulDropCourseTester() {
    // Create a sample roster with some student records
    String[][] roster =
        {{"Dan", "dan@wisc.com", "9183471234"}, {"Lucas", "lucas@wisc.com", "5817424987"},
            {"Sebastian", "sebastian@wisc.com", "8712369276"}};

    // Get the initial size of the roster
    int initialSize = roster.length;

    // Choose a campusId of an existing student to remove
    String campusIdToRemove = "5817424987";

    // Attempt to drop the student record
    int newSize = CourseEnrollment.dropCourse(campusIdToRemove, roster, initialSize);

    // Verify that the size has decreased by 1
    if (newSize != initialSize - 1) {
      return false; // Size should be reduced by 1 after removal
    }

    // Check if the student record with the specified campusId is no longer in the roster
    int index = CourseEnrollment.indexOf(campusIdToRemove, roster, newSize);
    if (index != -1) {
      return false; // The student record should not exist in the roster after removal
    }

    // The test passes if the size is reduced, and the student record is no longer in the roster
    return true;
  }

  /**
   * Ensures the correctness of the dropCourse() method when called to remove a non-existing student
   * record from a course enrollment roster of the class.
   * 
   * Expected behavior to be verified:<BR>
   * (+) No changes made to the input roster array: Fail to find a matching student record .<BR>
   * (+) Correct size returned (same size passed as input to the method)
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean unsuccessfulDropCourseTester() {
    // Create a sample roster with some student records
    String[][] roster = {{"Dylan", "dylan@wisc.com", "1325476980"},
        {"Connor", "connor@wisc.com", "4185274086"}, {"Jake", "jake@wisc.com", "7777777777"}};

    // Get the initial size of the roster
    int initialSize = roster.length;

    // Attempt to drop a non-existing student record
    String campusIdToRemove = "9999999999";
    int newSize = CourseEnrollment.dropCourse(campusIdToRemove, roster, initialSize);

    // Verify that the size remains the same
    if (newSize != initialSize) {
      return false; // Size should not have changed
    }

    // Check if the student record with the specified campusId is still not in the roster
    int index = CourseEnrollment.indexOf(campusIdToRemove, roster, newSize);
    if (index != -1) {
      return false; // The student record should not exist in the roster
    }

    // The test passes if the size remains the same and the student record is still not in the
    // roster
    return true;
  }


  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    boolean createEmptyListTesterResult = createEmptyListTester();
    System.out
        .println("createEmptyListTester: " + (createEmptyListTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean indexOfOversizeSizeArrayTesterResult = indexOfOversizeSizeArrayTester();
    System.out.println("indexOfOversizeSizeArrayTester: "
        + (indexOfOversizeSizeArrayTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean indexOfPerfectSizeArrayTesterResult = indexOfPerfectSizeArrayTester();
    System.out.println("indexOfPerfectSizeArrayTester: "
        + (indexOfPerfectSizeArrayTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean enrollOneStudentTesterResult = enrollOneStudentTester();
    System.out
        .println("enrollOneStudentTester: " + (enrollOneStudentTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean enrollOneStudentMoveFromWaitlistTesterResult = enrollOneStudentMoveFromWaitlistTester();
    System.out.println("enrollOneStudentMoveFromWaitlistTester: "
        + (enrollOneStudentMoveFromWaitlistTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean successfulDropCourseTesterResult = successfulDropCourseTester();
    System.out.println(
        "successfulDropCourseTester: " + (successfulDropCourseTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean unsuccessfulDropCourseTesterResult = unsuccessfulDropCourseTester();
    System.out.println("unsuccessfulDropCourseTester: "
        + (unsuccessfulDropCourseTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");

    return createEmptyListTesterResult && indexOfOversizeSizeArrayTesterResult
        && indexOfPerfectSizeArrayTesterResult && enrollOneStudentTesterResult
        && enrollOneStudentMoveFromWaitlistTesterResult && successfulDropCourseTesterResult
        && unsuccessfulDropCourseTesterResult;
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------");
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}
