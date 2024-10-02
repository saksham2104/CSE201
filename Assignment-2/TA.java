import java.util.Scanner;

public class TA extends Student {

    public TA(String name, String email, String password) {
        super(name, email, password, 6); // Call the constructor of the Student class, assuming TA is a 6th-semester student
    }

    // The TA helps in grading a specific student for a course
    public static int ta_help(Student student, Course course) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Assisting with grading for student: " + student.getName());
        System.out.println("Course: " + course.getTitle());

        // Get the grade input from the TA
        System.out.print("Enter the grade (1-10) for " + student.getName() + " in " + course.getTitle() + ": ");
        int grade = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Return the grade to the professor for updating
        return grade;
    }

    private Student findStudentByName(String studentName) {
        for (User user : Erp.users) {
            if (user instanceof Student) {
                if (user.getname().equalsIgnoreCase(studentName)) {
                    return (Student) user; // Cast to Student and return
                }
            }
        }
        return null;
    }

    private Course findCourseByCode(String courseCode) {
        for (Course course : Erp.courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
