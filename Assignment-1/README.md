In this project I have tried to build a university course registration system using the 
Oops concepts taught to us in class
1. Encapsulation
   Example: The User, Student, Professor, Administrator, Course, and Complaint classes encapsulate their properties and behaviors. 
   For instance, the User class has private fields for name, email, and password, with public methods to access and manipulate these fields. 
 
2. Inheritance
   Example: The User class is a base class for Student, Professor, and Administrator. 
    This shows hierarchal Inheritence
3. Polymorphism
   Example: The display() method is overridden in subclasses (Student, Professor, and Administrator). Each subclass provides its own implementation of how to display the user interface. When you call display() on a User reference, the correct subclass method is executed based on the actual object type.
4. Abstraction
5. Example: The User class is an abstract class, defining a blueprint for user types without specifying all details. Subclasses (like Student, Professor, and Administrator) implement the abstract methods. This allows for defining general behavior while leaving specific implementation to derived classes.


Overall the flow of program:
1. Starting the Program
   When you run the program, it begins with the main menu, where you can choose to log in or sign up.

2. Logging In or Signing Up
   If you’re already a user (like a student or professor), you enter your email and password to log in.
   If you’re new, you can create an account by providing your name, email, and a password.
3. User Types
   Depending on who you are (student, professor, or administrator), you get different options:

4. Managing Courses
   Students can look for courses that fit their semester and register for them. They can also drop courses they no longer want.
   Professors can manage their courses and see the students enrolled in them.
5. Handling Complaints
   If a student has a problem, they can submit a complaint. Administrators can view these complaints and mark them as resolved once they’ve been taken care of.

6. Logging Out
   When you’re done, you can log out of the system, and it goes back to the main menu for the next user,by exiting a while loop 

7. Storing Information
   All the information, like users, courses, and complaints, is stored in lists. This makes it easy to manage and retrieve when needed.

Steps to Run:
Normal compiling of java code but the file submitted may open to a .class file on vs code hence you will have to click on 'decompose class file'  and then run the code
UML Digram:
The image of the UML digram gets diluted on uploading i have tried all enhancer ai's but none of them work this is best one that i could upload please consider .I can explain the same during demo .
Thanks
