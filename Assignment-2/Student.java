import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Student extends User {
    public int semester;
    public List<Course> registeredCourses;
    public List<Integer> grades = Arrays.asList(-1,-1,-1,-1,-1,-1,-1);//any student can have maximum 7 courses generally 5,-1 means not graded yet


    public Student(String name, String email, String password,int semester) {
        super(name, email, password);
        this.semester = semester;
        this.registeredCourses = new ArrayList<>();
    }

    @Override
    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean studentMenu = true;

        while (studentMenu) {
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for Courses");
            System.out.println("3. View Schedule");
            System.out.println("4. View Grades");
            System.out.println("5. Drop Courses");
            System.out.println("6. Submit Complaint");
            System.out.println("7. Give feedback");
            System.out.println("8. Assist as TA");
            System.out.println("9. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableCourses();  // Assume courses list is accessible
                    break;
                case 2:
                    try {
                        registerForCourse(scanner);
                    } catch (CourseFullException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    viewSchedule();
                    break;
                case 4:
                    // give option to view SGPA and CGPA
                    System.out.println("Choose option\n");
                    System.out.print("1: SGPA\n");
                    System.out.print("2: CGPA\n");
                    int cgChoice = scanner.nextInt();
                    if (cgChoice == 1) {
                        viewGrades();
                    } else if (cgChoice == 2) {
                        System.out.println("Give CGPA upto previous sem ");
                        double prev= scanner.nextDouble();
                        cgpa(prev);
                    }
                    break;
                case 5:
                    System.out.print("Enter course code to drop: ");
                    String code = scanner.nextLine();
                    Course core = findCourseByCode(code);
                    if (core == null) {
                        System.out.print("Course not Found");
                        break;
                    }
                    dropCourse(core);
                    break;
                case 6:
                    submitComplaint(scanner);
                    break;
                case 7:
                    System.out.print("Enter professor's name: ");
                    String profName = scanner.nextLine();
                    Professor prof = findProfessorByName(profName);
                    give_feedback(prof);
                    break;

                case 8:
                    String codee = scanner.nextLine();
                    Course coree = findCourseByCode(codee);
                    String s_name= scanner.nextLine();
                    Student studentt=findStudentByName(s_name);
                    int ans=TA.ta_help(studentt,coree);
                case 9:
                    studentMenu = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
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

    private static Professor findProfessorByName(String professorName) {
        for (User user : Erp.users) {
            if (user instanceof Professor) {
                if (user.getname().equals(professorName)) {
                    return (Professor) user;  // Cast to Professor before returning
                }
            }
        }
        return null;
    }

    public void addCourse(Course course){
        this.registeredCourses.add(course);
    }

    public boolean is_enrolled(Course course) {
        return registeredCourses.contains(course);
    }

    public String getName() {
        return name;
    }

    public void give_feedback(Professor prof) {
        // Give feedback to a particular professor
        System.out.println("Give Feedback Description:");
        Scanner scanner = new Scanner(System.in); // Initialize Scanner
        String inp = scanner.nextLine();
        System.out.println("Give Feedback Rating (1-5):");
        int inpt = scanner.nextInt();

        // Create a Feedback object and add it to the professor's feedback list
        Feedback<String, Integer> feedback = new Feedback<>(inp, inpt);
        prof.feedbacks.add(feedback);
    }

    public void viewAvailableCourses() {
        // Placeholder for viewing courses
        System.out.println("Available Courses:");
        for (Course course : Erp.courses) {
            if (course.semester == semester) {
                System.out.println(course.getCourseCode() + " - " + course.getTitle());
            }
        }
    }

    public void registerForCourse(Scanner scanner) throws CourseFullException{
        System.out.print("Enter course code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if(course==null){
            System.out.println("Course not found or already registered.");
            return;
        }

        if(course.getEnrolledStudents()==course.getCapacity()){
            throw(new CourseFullException("Course is Full"));
        }
        else{
            registeredCourses.add(course);
            System.out.println("Registered for " + course.getTitle() + " successfully.");
        }

    }

    public void viewSchedule() {
        System.out.println("\nYour Schedule:");
        for (Course course : registeredCourses) {
            System.out.println(course.getTitle() + " (" + course.getTimings() + ")");
        }
    }

    public void viewGrades() {
        {
            System.out.println("Grades and GPA to be displayed.");
            int sz=registeredCourses.size();
            int sgpa=0;double subjects=0;
            for(int i=0;i<sz;i++){
                System.out.print(grades.get(i) +" ");
                if(grades.get(i)!=-1) {
                    sgpa = sgpa + grades.get(i);
                    subjects++;
                }
            }
            System.out.println("SGPA based on the grades set for this Semester:");
            if(subjects==0){
                System.out.println("No grades have been set yet");
                return;
            }
            double g=(sgpa/subjects);
            System.out.println(g);
        }
    }

    public void cgpa(double prev){
        //for viewing someone's cgpa we have to ask them for semester and gpa upto previous sem
        //hardcode handle for sem 1,2,3
        int sz=registeredCourses.size();
        boolean f=true;int sgpa=0;double subjects=0;
        for(int i=0;i<sz;i++){
            System.out.print(grades.get(i) +" ");
            if(grades.get(i)==-1){
                f=false;
                break;
            }
            if(grades.get(i)!=-1) {
                sgpa = sgpa + grades.get(i);
                subjects++;
            }
        }
        if(!f){
            System.out.println("All Grades have not been set yet hence cannot view CGPA");
            return;
        }
        double g=(sgpa/subjects);
        if(this.semester==2){
            double total=prev+g;
            total=total/2.0;
            System.out.println("CGPA is:"+total);
        }
        else if(this.semester==3){
            double total=prev*2;
            total=total+g;
            total=total/3.0;
            System.out.println("CGPA is:"+total);
        }
    }

    public void dropCourse(Course course) {
        LocalDate today = LocalDate.now(); // Get the current date for drop deadline check
        try {
            course.dropCourse(today);
            registeredCourses.remove(course);
        } catch (DropDeadlinePassedException e) {
            System.out.println(e.getMessage()); // Handle exception by printing the message
        }
    }

    public void submitComplaint(Scanner scanner) {
        System.out.print("Enter complaint description: ");
        String complaintDesc = scanner.nextLine();
        String complaintId = email + "_" + System.currentTimeMillis(); // Unique ID based on email and timestamp
        Erp.complaints.add(new Complaint(complaintDesc, complaintId));
        System.out.println("Complaint submitted.");
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

