import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Erp {
    public static List<User> users = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();
    public static List<Complaint> complaints = new ArrayList<>();


    public static void main(String[] args) throws InvalidLoginException, CourseFullException {

        Erp erpSystem = new Erp();

        // Prepopulate students
        erpSystem.users.add(new Student("Student-1", "student1@iiitd.ac.in", "password1",1));
        erpSystem.users.add(new Student("Student-2", "student2@iiitd.ac.in", "password2",1));
        erpSystem.users.add(new Student("Student-3", "student3@iiitd.ac.in", "password3",1));

        List<Professor> professors = new ArrayList<>();
        professors.add(new Professor("Prof. A", "profa@iiitd.ac.in", "password1"));
        professors.add(new Professor("Prof. B", "profb@iiitd.ac.in", "password2"));
        professors.add(new Professor("Prof. C", "profc@iiitd.ac.in", "password3"));
        professors.add(new Professor("Prof. D", "profd@iiitd.ac.in", "password4"));
        professors.add(new Professor("Prof. E", "profe@iiitd.ac.in", "password5"));

        erpSystem.users.addAll(professors);

        String[] timings = {"8:30-9:30", "9:30-10:30", "10:30-11:30", "11:30-12:30", "12:30-1:30"};
        String[] syllabusSem1 = {"Dictionaries", "Fields and Sets", "Flip Flops", "Design Principles", "Communication"};
        String[] syllabus_sem_2 = {"Linked Lists", "Cache Properties", "Diodes", "Random Variables", "Bonds"};
        String[] syllabus_sem_3 = {"Generic Programming", "Directional Derivatives", "Semaphores", "Fourier Transforms", "Business"};

        // Courses for Semester 1
        Course course1 = new Course("CS101", "Intro to Programming", professors.get(0), 4, 1, new ArrayList<>(), syllabusSem1[0], timings[0],5, LocalDate.of(2024, 10, 25));
        Course course2 = new Course("MTH101", "Linear Algebra-I", professors.get(1), 4, 1, new ArrayList<>(), syllabusSem1[1], timings[1],5,LocalDate.of(2024, 10, 25));
        Course course3 = new Course("ECE111", "Digital Circuits", professors.get(2), 4, 1, new ArrayList<>(), syllabusSem1[2], timings[2],5,LocalDate.of(2024, 10, 25));
        Course course4 = new Course("DES102", "HCI", professors.get(3), 4, 1, new ArrayList<>(), syllabusSem1[3], timings[3],5,LocalDate.of(2024, 10, 25));
        Course course5 = new Course("SSH101", "Communication Skills", professors.get(4), 4, 1, new ArrayList<>(), syllabusSem1[4], timings[4],5,LocalDate.of(2024, 10, 25));

        // Courses for Semester 2 with prerequisites
        Course course6 = new Course("CS102", "Data Structures", professors.get(0), 4, 2, List.of(course1), syllabus_sem_2[0], timings[0],5,LocalDate.of(2024, 10, 25)); // Prerequisite: CS101
        Course course7 = new Course("CS120", "Computer Organisation", professors.get(1), 4, 2, List.of(course2), syllabus_sem_2[1], timings[1],5,LocalDate.of(2024, 10, 25)); // Prerequisite: MTH101
        Course course8 = new Course("ECE113", "Basic Electronics", professors.get(2), 4, 2, List.of(course3), syllabus_sem_2[2], timings[2],5,LocalDate.of(2024, 10, 25)); // Prerequisite: ECE111
        Course course9 = new Course("MTH102", "Probability and Statistics", professors.get(3), 4, 2, List.of(course2), syllabus_sem_2[3], timings[3],5,LocalDate.of(2024, 10, 25)); // Prerequisite: MTH101
        Course course10 = new Course("ECO101", "Money and Banking", professors.get(4), 4, 2, List.of(course2), syllabus_sem_2[4], timings[4],5,LocalDate.of(2024, 10, 25)); // Prerequisite: MTH101

        // Courses for Semester 3 with prerequisites
        Course course11 = new Course("CS201", "Advanced Programming", professors.get(0), 4, 3, List.of(course6), syllabus_sem_3[0], timings[0],5,LocalDate.of(2024, 10, 25)); // Prerequisite: CS102
        Course course12 = new Course("MTH201", "Multivariate Calculus", professors.get(1), 4, 3, List.of(course7), syllabus_sem_3[1], timings[1],5,LocalDate.of(2024, 10, 25)); // Prerequisite: MTH102
        Course course13 = new Course("CS203", "Operating Systems", professors.get(0), 4, 3, List.of(course6), syllabus_sem_3[2], timings[2],5,LocalDate.of(2024, 10, 25)); // Prerequisite: CS102
        Course course14 = new Course("ECE203", "Signals and Systems", professors.get(3), 4, 3, List.of(course8), syllabus_sem_3[3], timings[3],5,LocalDate.of(2024, 10, 25)); // Prerequisite: ECE113
        Course course15 = new Course("SSH201", "Business Communication", professors.get(4), 4, 3, List.of(course9), syllabus_sem_3[4], timings[4],5,LocalDate.of(2024, 10, 25)); // Prerequisite: SSH101

        // Adding all the courses to the courses list
        erpSystem.courses.add(course1);
        erpSystem.courses.add(course2);
        erpSystem.courses.add(course3);
        erpSystem.courses.add(course4);
        erpSystem.courses.add(course5);
        erpSystem.courses.add(course6);
        erpSystem.courses.add(course7);
        erpSystem.courses.add(course8);
        erpSystem.courses.add(course9);
        erpSystem.courses.add(course10);
        erpSystem.courses.add(course11);
        erpSystem.courses.add(course12);
        erpSystem.courses.add(course13);
        erpSystem.courses.add(course14);
        erpSystem.courses.add(course15);


        // Prepopulate administrators
        erpSystem.users.add(new Administrator("Admin-1", "admin1@iiitd.ac.in", "adminpassword1"));
        erpSystem.users.add(new Administrator("Admin-2", "admin2@iiitd.ac.in", "adminpassword2"));


        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nERP System");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    signUp(scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

     private static void login(Scanner scanner) throws InvalidLoginException, CourseFullException {
        System.out.print("Enter name: ");
        String username = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = findUserByEmail(email);
        if (user == null) {
            throw new InvalidLoginException("Invalid Email"); // Throw exception if email is not found

        }

        // Verify the password
        if (!user.verifyPassword(password)) {
            throw new InvalidLoginException("Invalid Password"); // Throw exception if password is incorrect
        }

        // If all checks pass, login is successful
        System.out.println("Login successful!");
        user.display(); // Show respective menu based on user type
    }

    private static void signUp(Scanner scanner) {
        System.out.println("Select user type:");
        System.out.println("1. Student");
        System.out.println("2. Professor");
        System.out.println("3. Administrator");
        System.out.println("4. TA");
        System.out.print("Select an option: ");

        int userType = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        switch (userType) {
            case 1:
                System.out.println("Enter your semester:");
                int sem=scanner.nextInt();
                Student student = new Student(name, email, password,sem);
                users.add(student);
                for(Course courses:courses){
                    if(courses.getSemester()==student.semester){
                        student.registeredCourses.add(courses);
                    }
                }
                System.out.println("Student registered successfully.");
                break;
            case 2:
                Professor professor = new Professor(name, email, password);
                users.add(professor);
                System.out.println("Professor registered successfully.");
                break;
            case 3:
                System.out.println("Admin?,No need to Sign up.");
                break;
            case 4:
                TA ta=new TA(name,email,password);
                users.add(ta);
                break;
            default:
                System.out.println("Invalid user type.");
        }
    }

    private static User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public static <T extends User> List<T> getUsers(Class<T> userClass) {
        List<T> result = new ArrayList<>();
        for (User user : users) {
            if (user.getClass() == userClass) {
                result.add(userClass.cast(user));
            }
        }
        return result;
    }

}


