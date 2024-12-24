1. Invalid Course Registration
   Description: This exception occurs when a student attempts to register for a course that is either full or does not exist.
   The registerForCourse method checks if the course exists and if it is full.
   If the course is full, a CourseFullException is thrown with an appropriate message
2. Invalid Login
   Description: This exception occurs when a user attempts to log in with invalid credentials (email or password).

    In the login method, validate the entered email and password against the stored user credentials.
    If the credentials do not match, throw an InvalidLoginException.
3.  Course Drop Failures:
   Description:This exception occurs when a student tries to drop a course after the drop deadline has passed.

    The dropCourse method checks if the current date is past the course's drop deadline.
    If the deadline has passed, a DropDeadlinePassedException is thrown

Generic Feedback Class
Generic Type Parameters:
    T: Represents the type of the feedback description (e.g., String).
    U: Represents the type of the feedback rating (e.g., Integer).
    Storing Feedback: Feedback objects can be added to a professor’s feedback list, 
    allowing for easy accumulation and retrieval of feedback over time.
Flexibility: By using generics, the Feedback class can handle different types for the description and rating, making it versatile for future expansions (e.g., supporting different types of feedback or ratings).

Class TA:
My TA class helps a TA to assist prof in grading