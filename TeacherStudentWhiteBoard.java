class WhiteBoard {
    String text; // This is the note that the teacher writes down on the board
    int noOfStudents = 0; // total amount of student in the class
    int count = 0; // keeps a track of how many student are finish writing down notes from the board

    synchronized public void write(String text) {
        System.out.println("Teacher writes " + text);

        // Teacher waits for all the student to finish writing from the board
        while(count != 0) {
            try {
                wait();
            }
			catch(Exception e){}
        }
        this.text = text; // Teacher writes to the board
        count = noOfStudents; // Teacher has to now wait until all the students are finish writing from the board
        notifyAll(); // Signal all the students that the Teacher has just wrote to the board
    }

    synchronized public String read() {
        // Teacher gets a chance to write to the board again, since all the students are finish taking notes
        while( count == 0) {
            try {
                wait();
            }
            catch(Exception e){}
        }

        String txt = text;
        count--; // marks that a student is finish taking down the notes from the board

        // Signal the Teacher that all the students are finish taken down the notes from the board
        if(count == 0)
            notify();

        return txt;
    }

    // Checks how many student are in the class
    public void attendance() {
        noOfStudents++;
    }
}

class Teacher extends Thread {
    WhiteBoard whiteBoard;
    String[] notes = {"the cat says meow","the dog says woof", "the horse says neigh", "the sheep says baa", "the pig says oink"};

    public Teacher(WhiteBoard whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    public void run() {
        for(int i = 0; i < notes.length; i++)
            whiteBoard.write(notes[i]);
    }
}

class Student extends Thread {
    WhiteBoard whiteBoard;
    String name;

    public Student(String name, WhiteBoard whiteBoard) {
        this.whiteBoard = whiteBoard;
        this.name = name;
    }

    public void run() {
        String text;
        whiteBoard.attendance();

        do {
            text = whiteBoard.read();
            System.out.println(name + " is reading " + text);
            System.out.flush();
        } while(!text.equals("The pig says oink"));
    }
}

class TeacherStudentWhiteBoard {
    public static void main(String[] args) {
        WhiteBoard whiteBoard = new WhiteBoard();
        Teacher teacher = new Teacher(whiteBoard);

        Student student1 = new Student("1. Sam", whiteBoard);
        Student student2 = new Student("2. Mary", whiteBoard);
        Student student3 = new Student("3. David", whiteBoard);
        Student student4 = new Student("4. Jane", whiteBoard);

        teacher.start();

        student1.start();
        student2.start();
        student3.start();
        student4.start();
    }
}