import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Professor extends User {
    public List<Course> assignedCourses;

    public Professor(String name, String email, String password) {
        super(name, email, password);
        this.assignedCourses = new ArrayList<>();
    }

    @Override
    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("1. View Assigned Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Manage Course (Update Syllabus/Timings)");
            System.out.println("4. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAssignedCourses();
                    break;
                case 2:
                    viewEnrolledStudents(scanner);
                    break;
                case 3:
                    manageCourse(scanner);
                    break;
                case 4:
                    flag = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void addCourse(Course course){
        this.assignedCourses.add(course);
    }

    public void viewAssignedCourses() {
        System.out.println("\nAssigned Courses:");
        for (Course course : assignedCourses) {
            System.out.println(course.getCourseCode()+ " " + course.getTitle());
        }
    }

    public void viewEnrolledStudents(Scanner scanner) {
        System.out.print("Enter course code to view enrolled students: ");
        String courseCode = scanner.nextLine();
        Course selectedCourse = find_course(courseCode);

        if (selectedCourse != null) {
            System.out.println("Students enrolled in " + selectedCourse.getTitle() + ":");
            for (Student student : Erp.getUsers(Student.class)) {
                if (student.is_enrolled(selectedCourse)) {
                    System.out.println("Name: " + student.getName() + ", Email: " + student.getEmail());
                }
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    public void manageCourse(Scanner scanner) {
        System.out.print("Enter course code to manage: ");
        String courseCode = scanner.nextLine();
        Course course = find_course(courseCode);

        if (course != null) {
            System.out.println("1. Update Syllabus");
            System.out.println("2. Update Timings");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter new syllabus: ");
                    String newSyllabus = scanner.nextLine();
                    course.setSyllabus(newSyllabus);
                    System.out.println("Syllabus updated.");
                    break;
                case 2:
                    System.out.print("Enter new timings: ");
                    String newTimings = scanner.nextLine();
                    course.setTimings(newTimings);
                    System.out.println("Timings updated.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        else {
            System.out.println("Course not found.");
        }
    }

    private Course find_course(String courseCode) {
        // Convert the input course code to lowercase (or uppercase) for comparison
         String course = courseCode;

        // Iterate through assigned courses
        for (Course courses : assignedCourses) {
            // Compare course codes
            if (courses.getCourseCode().equals(course)) {
               return courses;
            }
        }
        return null;
    }

}

