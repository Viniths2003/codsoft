
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
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

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(student);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStudent(Student student) {
        return registeredStudents.remove(student);
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nAvailable Slots: " + getAvailableSlots();
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

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.registerStudent(this);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent(this);
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\nName: " + name;
    }
}

class Main {
    private List<Course> courses;
    private List<Student> students;
    private Scanner scanner;

    public Main() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourses() {
        for (Course course : courses) {
            System.out.println(course);
            System.out.println();
        }
    }

    public Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void registerStudentForCourse() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.registerStudent(student)) {
            student.registerCourse(course);
            System.out.println("Student registered successfully.");
        } else {
            System.out.println("Course is full.");
        }
    }

    public void dropStudentFromCourse() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.removeStudent(student)) {
            student.dropCourse(course);
            System.out.println("Student dropped from course successfully.");
        } else {
            System.out.println("Student is not registered in this course.");
        }
    }

    public static void main(String[] args) {
        Main cms = new Main();

        cms.addCourse(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science", 30, "MWF 9-10AM"));
        cms.addCourse(new Course("CS102", "Data Structures", "Introduction to data structures", 25, "TTh 10-11:30AM"));
        cms.addStudent(new Student("S1001", "Alice"));
        cms.addStudent(new Student("S1002", "Bob"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Display Courses");
            System.out.println("2. Register Student for Course");
            System.out.println("3. Drop Student from Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    cms.displayCourses();
                    break;
                case 2:
                    cms.registerStudentForCourse();
                    break;
                case 3:
                    cms.dropStudentFromCourse();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
