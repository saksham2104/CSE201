import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    public String courseCode;
    public String title;
    public Professor professor;
    public int credits;
    public int semester;
    public List<Course> prerequisites;
    public String syllabus;
    public String timings;
    private int capacity;
    private int enrolledStudents;
    private LocalDate dropDeadline; // Deadline for dropping the course


    public Course(String courseCode, String title, Professor professor, int credits, int semester, List<Course> prerequisites, String syllabus, String timings,int capacity, LocalDate dropDeadline)  throws CourseFullException {
        this.courseCode = courseCode;
        this.title = title;
        this.professor = professor;
        this.credits = credits;
        this.semester = semester;
        this.prerequisites = prerequisites;
        this.syllabus = syllabus;
        this.timings = timings;
        this.capacity = capacity;
        this.enrolledStudents = 0;
        this.dropDeadline = dropDeadline;


        //I will also have to add the new course to the respective prof's assigned courses list
        Professor prof = this.professor;
        prof.addCourse(this);

        //I will also have to add the new to each student who belongs to the course's semester
        for (User user : Erp.users) {
            if (user instanceof Student) {
                Student kid = (Student) user;
                if (kid.semester == (this.semester)) {
                    if (this.enrolledStudents + 1 < this.capacity) {
                        kid.addCourse(this);
                    } else {
                        throw (new CourseFullException("Courses are Full"));
                    }
                }
            }
        }
    }
    public void dropCourse(LocalDate dropDate) throws DropDeadlinePassedException {
        if (dropDate.isAfter(dropDeadline)) {
            throw new DropDeadlinePassedException("Cannot drop course: Deadline has passed for course " + this.title + ".");
        }
        enrolledStudents--;
        System.out.println("Successfully dropped course: " + this.title);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getSemester() {
        return semester;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }
}
