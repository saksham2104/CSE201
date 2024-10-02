import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Complaint {
    public String description;
    public String status; // Pending or Resolved
    public String name_complaint_no; // name of student + no of his complaint

    public Complaint(String description, String name_complaint_no) {
        this.description = description;
        this.status = "Pending";
        this.name_complaint_no = name_complaint_no;
    }

    public void resolve() {
        this.status = "Resolved";
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return this.description;
    }
}

