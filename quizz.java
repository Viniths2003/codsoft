import java.util.Scanner;

class Question {
    String questionText;
    String[] options;
    int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect(int answer) {
        return answer == correctAnswer;
    }

    public void display() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ": " + options[i]);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Question[] questions = {
                new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 3),
                new Question("Who wrote 'Hamlet'?", new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"}, 2),
                new Question("What is the smallest prime number?", new String[]{"0", "1", "2", "3"}, 3),
        };

        int score = 0;

        for (Question question : questions) {
            question.display();
            System.out.print("Your answer: ");
            int answer = scanner.nextInt();

            if (question.isCorrect(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        }

        System.out.println("You scored " + score + " out of " + questions.length);

        scanner.close();
    }
}
