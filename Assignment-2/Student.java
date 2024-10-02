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
            System.out.println("7. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableCourses();  // Assume courses list is accessible
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    viewSchedule();
                    break;
                case 4:
                    //give option to view SGPA and CGPA
                    System.out.println("Choose option\n");
                    System.out.print("1:SGPA");
                    System.out.print("2:CGPA");
                    int cg_choice = scanner.nextInt();
                    if(cg_choice==1) {
                        viewGrades();
                    }
                    else if(cg_choice==2) {
                        if (this.semester == 1) {
                            System.out.println("You are in 1st sem so your CGPA is equal to SGPA");
                            viewGrades();
                        }
                        else if (this.semester == 2)
                            System.out.println("Give CGPA upto previous sem ");
                            double prev= scanner.nextDouble();
                            cgpa(prev);
                    }
                    break;
                case 5:
                    dropCourse(scanner);
                    break;
                case 6:
                    submitComplaint(scanner);
                    break;
                case 7:
                    studentMenu = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
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

    public void viewAvailableCourses() {
        // Placeholder for viewing courses
        System.out.println("Available Courses:");
        for (Course course : Erp.courses) {
            if (course.semester == semester) {
                System.out.println(course.getCourseCode() + " - " + course.getTitle());
            }
        }
    }

    public void registerForCourse(Scanner scanner) {
        System.out.print("Enter course code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course != null && !is_enrolled(course)) {
            registeredCourses.add(course);
            System.out.println("Registered for " + course.getTitle() + " successfully.");
        } else {
            System.out.println("Course not found or already registered.");
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

    public void dropCourse(Scanner scanner) {
        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course != null && is_enrolled(course)) {
            registeredCourses.remove(course);
            System.out.println("Dropped " + course.getTitle() + " successfully.");
        } else {
            System.out.println("Course not found or not registered.");
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

