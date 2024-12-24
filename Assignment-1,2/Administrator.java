import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Administrator extends User {

    public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public void display() throws CourseFullException {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Add Course");
            System.out.println("2. Delete Course");
            System.out.println("3. View Student Records");
            System.out.println("4. View Complaints");
            System.out.println("5. Update Complaint Status");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    deleteCourse(scanner);
                    break;
                case 3:
                    viewStudentRecords();
                    break;
                case 4:
                    viewComplaints();
                    break;
                case 5:
                    updateComplaintStatus(scanner);
                    break;
                case 6:
                    flag = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void addCourse(Scanner scanner) throws CourseFullException {
        System.out.println("\nAdding a new course...");

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Enter course title: ");
        String title = scanner.nextLine();

        System.out.print("Enter number of credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter course semester: ");
        int semester = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter course syllabus: ");
        String syllabus = scanner.nextLine();

        System.out.print("Enter course timings: ");
        String timings = scanner.nextLine();

        System.out.print("Enter Professor Name: ");
        String professorName = scanner.nextLine();
        Professor professor = find_prof(professorName);

        if (professor != null) {
            Course newCourse = new Course(courseCode, title, professor, credits, semester, new ArrayList<>(), syllabus, timings,5, LocalDate.of(2024, 10, 25));
            Erp.courses.add(newCourse);
            System.out.println("Course " + title + " added successfully.");
        } else {
            System.out.println("Professor not found.");
        }
    }

    public void deleteCourse(Scanner scanner) {
        System.out.print("Enter course code to delete: ");
        String courseCode = scanner.nextLine();
        Course courseToRemove = find_course(courseCode);

        if (courseToRemove != null) {
            Erp.courses.remove(courseToRemove);
            System.out.println("Course " + courseCode + " deleted.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void viewStudentRecords() {
        System.out.println("\nStudent Records:");
        for (Student student : Erp.getUsers(Student.class)) {
            System.out.println("Name: " + student.getName() + ", Email: " + student.getEmail() + ", Semester: " + student.semester);
        }
    }

    public void viewComplaints() {
        System.out.println("\nComplaints:");
        if (Erp.complaints.isEmpty()) {
            System.out.println("No complaints submitted.");
        } else {
            for (Complaint complaint : Erp.complaints) {
                System.out.println("ID: " + complaint.name_complaint_no + ", Description: " + complaint.getDescription() + ", Status: " + complaint.getStatus());
            }
        }
    }

    public void updateComplaintStatus(Scanner scanner) {
        System.out.print("Enter complaint ID to update: ");
        String complaintId = scanner.nextLine();
        Complaint complaint = findComplaintById(complaintId);

        if (complaint != null) {
            complaint.resolve();
            System.out.println("Complaint status updated to 'Resolved'.");
        } else {
            System.out.println("Complaint not found.");
        }
    }

    private Professor find_prof(String name) {
        for (Professor professor : Erp.getUsers(Professor.class)) {
            if (professor.name.equals(name)) {
                return professor;
            }
        }
        return null;
    }

    private Course find_course(String courseCode) {
        for (Course course : Erp.courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private Complaint findComplaintById(String complaintId) {
        for (Complaint complaint : Erp.complaints) {
            if (complaint.name_complaint_no.equalsIgnoreCase(complaintId)) {
                return complaint;
            }
        }
        return null;
    }
}
