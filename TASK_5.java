import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean registerStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Course Code: %s, Title: %s, Description: %s, Capacity: %d, Enrolled: %d",
                courseCode, title, description, capacity, enrolledStudents);
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (course.registerStudent()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course) && course.dropStudent()) {
            return true;
        }
        return false;
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        initializeCourses();
    }

    private void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Learn the basics of computer science.", 30));
        courses.add(new Course("MATH101", "Calculus I", "An introduction to calculus.", 25));
        courses.add(new Course("PHY101", "Physics I", "Fundamentals of physics.", 20));
    }

    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course+"\n");
        }
    }

    public Student registerStudent(String studentId, String name) {
        Student student = new Student(studentId, name);
        students.add(student);
        return student;
    }

    public static void main(String[] args) {
         for(int i=0;i<23;i++)
        {
            System.out.print("*");
        }
        System.out.print("<<STUDENT COURSE REG SYSTEM>>");
        for(int i=0;i<22;i++)
        {
            System.out.print("*");
        }
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        System.out.print("Enter your Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter your Name: ");
        String studentName = scanner.nextLine();
        Student student = system.registerStudent(studentId, studentName);

        while (true) {
            system.displayCourses();
            System.out.print("\nEnter Course Code to Register (or 'exit' to quit): ");
            String courseCode = scanner.nextLine();

            if (courseCode.equalsIgnoreCase("exit")) {
                break;
            }

            Course selectedCourse = null;
            for (Course course : system.courses) {
                if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                    selectedCourse = course;
                    break;
                }
            }

            if (selectedCourse != null) {
                if (student.registerCourse(selectedCourse)) {
                    System.out.println("\nYou have successfully registered for " + selectedCourse.getTitle() + "!");
                } else {
                    System.out.println("\nOops! The course is full.");
                }
            } else {
                System.out.println("\nInvalid Course Code. Please try again.");
            }
        }

        System.out.print("\nEnter Course Code to Drop: ");
        String dropCourseCode = scanner.nextLine();
        Course dropCourse = null;
        for (Course course : student.getRegisteredCourses()) {
            if (course.getCourseCode().equalsIgnoreCase(dropCourseCode)) {
                dropCourse = course;
                break;
            }
        }

        if (dropCourse != null) {
            if (student.dropCourse(dropCourse)) {
                System.out.println("\nYou have successfully dropped the course: " + dropCourse.getTitle() + ".");
            } else {
                System.out.println("\nSomething went wrong. You couldn't drop the course.");
            }
        } else {
            System.out.println("\nYou are not registered for that course.");
        }

        scanner.close();
    }
}
