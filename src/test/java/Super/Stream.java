package Super;

public class Stream extends Student {
    public String getStudentStream() {
        return studentStream;
    }

    private final String studentStream;

    public Stream(final String firstName, final String lastName, final String studentStream) {
        super(firstName, lastName); // this is to invoke the parent class
        this.studentStream = studentStream;
    }

    public static void main(String[] args) {
        Stream student = new Stream("Ayesha", "Ali", "Engineering");
        student.printStudentDetails();

    }

    public void printStudentDetails() {
        System.out.println(super.getFirstName());
        System.out.println(super.getLastName());
        System.out.println(this.studentStream);
    }

}
