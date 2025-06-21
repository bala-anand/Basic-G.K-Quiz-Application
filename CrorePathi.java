import java.util.Scanner;
import java.util.Random;

public class CrorePathi {
    // Color codes
    final static String RESET = "\u001B[0m";
    final static String RED = "\u001B[31m";
    final static String GREEN = "\u001B[32m";
    final static String YELLOW = "\u001B[33m";
    final static String BLUE = "\u001B[34m";
    final static String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get user info
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your city: ");
        String city = sc.nextLine();

        System.out.print("Enter your Date of Birth (DD-MM-YYYY): ");
        String dob = sc.nextLine();

        // Questions
        String[] questions = {
            "Who is the most successful formula one driver of all time?",
            "When did India get its freedom from colonial Britain?",
            "Which is the largest river in the world?",
            "When did World War II start?",
            "Which season is considered best for holidays?",
            "In 2006, which team won the UEFA Champions League?",
            "Who won more World Cup finals in football?",
            "Which is the best colour suited for suits especially in parties?",
            "What is the name of the atom bomb which was dropped in Hiroshima?",
            "How fast can a Formula One car go?"
        };

        String[][] options = {
            {"John Alessi", "Alain Prost", "Max Verstappen", "Lewis Hamilton"},
            {"1974", "1947", "1988", "1888"},
            {"Nile", "Amazon", "Godhavari", "Sadhuperi"},
            {"1969", "1948", "1939", "2000"},
            {"Summer", "Spring", "Winter", "Rainy"},
            {"Liverpool", "Barcelona", "Bayern", "Arsenal"},
            {"France", "Argentina", "Brazil", "Germany"},
            {"Blue", "Skyblue", "Black", "Beige"},
            {"Fatboy", "YellowBrain", "TheAbyss", "TheDawn"},
            {"350km/hr", "210km/hr", "260km/hr", "370km/hr"}
        };

        int[] correctAnswers = {4, 2, 1, 3, 2, 2, 3, 3, 1, 4};
        int[] rewards = {1000, 2000, 5000, 10000, 20000, 40000, 80000, 160000, 320000, 1000000};

        boolean audienceUsed = false;
        boolean fiftyFiftyUsed = false;
        int earnedReward = 0;

        System.out.println("\n" + YELLOW + "Reward Tree:" + RESET);
        for (int i = 0; i < rewards.length; i++) {
            System.out.println((i + 1) + " Question - Reward: ₹" + rewards[i]);
        }
        System.out.println("\n" + YELLOW + name + ", Your current reward is ₹" + earnedReward + RESET);

        for (int q = 0; q < questions.length; q++) {
            System.out.println(BLUE + "\n" + name + ", this is your Question " + (q + 1) + ":" + RESET);
            System.out.println(CYAN + questions[q] + RESET);

            for (int i = 0; i < 4; i++) {
                System.out.println(YELLOW + (i + 1) + ". " + options[q][i] + RESET);
            }

            if (!audienceUsed || !fiftyFiftyUsed) {
                System.out.print("\nDo you want to use a lifeline? (yes/no): ");
                String useLifeline = sc.next();

                if (useLifeline.equalsIgnoreCase("yes")) {
                    System.out.println("Available lifelines:");
                    if (!audienceUsed) System.out.println("1. Audience Poll");
                    if (!fiftyFiftyUsed) System.out.println("2. Fifty Fifty");

                    System.out.print("Enter your choice (1/2): ");
                    int lifelineChoice = sc.nextInt();

                    if (lifelineChoice == 1 && !audienceUsed) {
                        audienceUsed = true;
                        Random rand = new Random();
                        int correctPercent = 50 + rand.nextInt(31);
                        int[] otherPercents = new int[3];
                        int remaining = 100 - correctPercent;
                        for (int i = 0; i < 2; i++) {
                            otherPercents[i] = rand.nextInt(remaining + 1);
                            remaining -= otherPercents[i];
                        }
                        otherPercents[2] = remaining;

                        System.out.println("\nAudience Poll Results:");
                        int correctIndex = correctAnswers[q] - 1;
                        int count = 0;
                        for (int i = 0; i < 4; i++) {
                            int percent = (i == correctIndex) ? correctPercent : otherPercents[count++];
                            System.out.println(YELLOW + options[q][i] + " : " + percent + "%" + RESET);
                        }
                    } else if (lifelineChoice == 2 && !fiftyFiftyUsed) {
                        fiftyFiftyUsed = true;
                        int correctIndex = correctAnswers[q] - 1;
                        int randomIncorrect;
                        do {
                            randomIncorrect = new Random().nextInt(4);
                        } while (randomIncorrect == correctIndex);

                        System.out.println("\nFifty Fifty Options:");
                        System.out.println(YELLOW + (correctIndex + 1) + ". " + options[q][correctIndex] + RESET);
                        System.out.println(YELLOW + (randomIncorrect + 1) + ". " + options[q][randomIncorrect] + RESET);
                    } else {
                        System.out.println(RED + "No such lifeline available or already used!" + RESET);
                    }
                }
            } else {
                System.out.println(RED + "\nNo lifelines available. Please select your answer." + RESET);
            }

            System.out.print("\n" + name + ", enter your answer (1-4): ");
            int answer = sc.nextInt();

            if (answer == correctAnswers[q]) {
                earnedReward = rewards[q];
                System.out.println(GREEN + "\n" + name + ", Congrats! Your answer is correct!" + RESET);
                System.out.println(YELLOW + name + ", Your current reward is ₹" + earnedReward + RESET);
            } else {
                System.out.println(RED + "\n" + name + ", Sorry! Your answer is wrong." + RESET);
                System.out.println(YELLOW + name + ", you have earned ₹" + earnedReward + RESET);
                System.out.println(GREEN + "\nThank you for playing, " + name + "! Game Over." + RESET);
                System.exit(0);
            }
        }

        System.out.println(GREEN + "\n\nCongrats " + name + "! You have completed the game." + RESET);
        System.out.println(YELLOW + "Candidate Details:" + RESET);
        System.out.println("Name: " + name);
        System.out.println("City: " + city);
        System.out.println("DOB: " + dob);
        System.out.println(GREEN + "Total Reward Earned: ₹" + earnedReward + RESET);
    }
}
