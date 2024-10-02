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

    public Course(String courseCode, String title, Professor professor, int credits, int semester, List<Course> prerequisites, String syllabus, String timings) {
        this.courseCode = courseCode;
        this.title = title;
        this.professor = professor;
        this.credits = credits;
        this.semester = semester;
        this.prerequisites = prerequisites;
        this.syllabus = syllabus;
        this.timings = timings;

        //I will also have to add the new course to the respective prof's assigned courses list
        Professor prof=this.professor;
        prof.addCourse(this);

        //I will also have to add the new to each student who belongs to the course's semester
        for(User user:Erp.users){
            if(user instanceof Student){
                Student kid=(Student)user;
                if(kid.semester==(this.semester)){
                    kid.addCourse(this);
                }
            }
        }
//        List<Student> students = Erp.getUsers(Student.class);
//        for (Student kid : students) {
//            if (kid.semester == this.semester) {
//                kid.addCourse(this); // Add course to the student's registered courses
//            }
//        }


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
