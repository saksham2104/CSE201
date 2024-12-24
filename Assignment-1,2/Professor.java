import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Professor extends User {
    public List<Course> assignedCourses;
    public List<Feedback<String, Integer>> feedbacks; // List of Feedback objects

    public Professor(String name, String email, String password) {
        super(name, email, password);
        this.assignedCourses = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
    }

    @Override
    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("1. View Assigned Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Manage Course (Update Syllabus/Timings/Grades)");
            System.out.println("4.View Feedback");
            System.out.println("5. Logout");
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
                    view_feedback();
                    break;
                case 5:
                    flag = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void getTA_suggestion(){

    }

    public void addCourse(Course course) {
        this.assignedCourses.add(course);
    }
    public String getname(){
        return this.name;
    }

    public void viewAssignedCourses() {
        System.out.println("\nAssigned Courses:");
        for (Course course : assignedCourses) {
            System.out.println(course.getCourseCode() + " " + course.getTitle());
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
        {
            System.out.println("1. Update Syllabus");
            System.out.println("2. Update Timings");
            System.out.println("3. Update Grades");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter new syllabus: ");
                    String newSyllabus = scanner.nextLine();
                    String courseCode = scanner.nextLine();
                    Course course = find_course(courseCode);
                    course.setSyllabus(newSyllabus);
                    System.out.println("Syllabus updated.");
                    break;
                case 2:
                    System.out.print("Enter new timings: ");
                    String newTimings = scanner.nextLine();
                    String course_Code = scanner.nextLine();
                    Course j = find_course(course_Code);
                    j.setTimings(newTimings);
                    System.out.println("Timings updated.");
                    break;
                case 3:
                    System.out.println("3. Get TA help in grading");
                    System.out.println("Enter 1 for YES, 2 for NO:");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (choice == 1) {
                        System.out.println("Enter TA's name:");
                        String taName = scanner.nextLine();
                        TA ta = findTaByName(taName);

                        if (ta != null) {
                            System.out.println("Enter student's name:");
                            String studentName = scanner.nextLine();
                            Student student = findStudentByName(studentName);

                            System.out.println("Enter course code:");
                            String courseCodee = scanner.nextLine();
                            Course coursee=findCourseByCode(courseCodee);

                            if (student != null) {
                                int grade = ta.ta_help(student, coursee); // Get grade help from the TA
                                update_grade(courseCodee,studentName, grade); // Update the grade
                            } else {
                                System.out.println("Invalid student or course.");
                            }
                        } else {
                            System.out.println("TA not found.");
                        }
                    }

                    else {
                        System.out.println("Updating Grade:");
                        System.out.println("Enter course code:");
                        String code = scanner.nextLine();
                        System.out.println("Enter student's name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter grade (1-10):");
                        int marks = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        update_grade(code, name, marks);
                        break;
                    }
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }


    private TA findTaByName(String taName) {
        for (User user : Erp.users) {
            if (user instanceof TA) {
                if (user.getname().equalsIgnoreCase(taName)) {
                    return (TA) user;
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


    public void view_feedback() {
        System.out.println("Feedback details:");
        for (Feedback<String, Integer> feedback : feedbacks) {
            System.out.println(feedback);
        }
        System.out.println();
    }


    public void update_grade(String code, String name, Integer marks) {
        Student student = findStudentByName(name);
        Course this_course = find_course(code);
        if (student != null && this_course != null) {
            set_grades(this_course, student, marks);
            System.out.println("Grade updated successfully.");
        } else {
            System.out.println("Invalid student or course.");
        }
    }

    public void set_grades(Course course, Student student, Integer grade) {
        // Professor sets grade of 'student' for Course 'course'
        String course_code = course.getCourseCode();
        int cnt = 0;
        for (Course i : student.registeredCourses) {
            if (i.getCourseCode().equals(course_code)) {
                // Set grade for course i
                student.grades.set(cnt, grade);
                return;
            }
            cnt++;
        }
        System.out.println("Course not found in registered courses for the student.");
    }

    private Course find_course(String courseCode) {
        // Convert the input course code to lowercase (or uppercase) for comparison
        for (Course courses : assignedCourses) {
            // Compare course codes
            if (courses.getCourseCode().equals(courseCode)) {
                return courses;
            }
        }
        return null;
    }

    public static Student findStudentByName(String name) {
    for (User user : Erp.users) {
        if (user instanceof Student) {
            Student student = (Student) user;
            if (student.getName().equals(name)) {
                return student;
            }
        }
    }
        return null;
    }
}
