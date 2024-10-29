import java.io.Console;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Mastermind {
    private List<String> answer;
    private List<String> givenLetter;
    private List<String> feedback;
    private List<String> listofpermutation;
    private String answerString;

    public void start(List<String> words) {
        this.givenLetter = words;
        this.listofpermutation = new ArrayList<>();
        this.answer = new ArrayList<>();
        String[] fourword = new String[4];
        for (int i = 0; i < fourword.length; i++) {
            fourword[i] = "_";
        }
        permutate(givenLetter, fourword);
        SecureRandom random = new SecureRandom();
        int randInd = random.nextInt(listofpermutation.size());
        System.out.println(listofpermutation.size());
        answerString = listofpermutation.get(randInd);
        for (String test : answerString.split("")) {
            answer.add(test);
        }
        printAns();
        Console cons = System.console();
        while (true) {
            String guess = cons.readLine(">");
            if (checkDuplicate(guess)) {
                System.out.println("You can only key one alphabet once");
                continue;
            }
            boolean verify = checkAns(guess);
            if (verify) {
                break;
            }
        }
    }

    public void permutate(List<String> list, String[] fourword) {

        if (checkComplete(fourword)) {
            String line = convertString(fourword);
            if (!checkDuplicate(line)) {
                if (!listofpermutation.contains(line)) {
                    listofpermutation.add(convertString(fourword));
                    System.out.println(convertString(fourword));
                }
            }
        }
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < list.size(); i++) {
                if (fourword[k].equals("_")) {
                    fourword[k] = list.get(i);
                    permutate(list, fourword);
                    fourword[k] = "_";
                }
            }
        }
    }

    public String convertString(String[] fourword) {
        String line = "";
        for (String four : fourword) {
            line += four;
        }
        return line;
    }

    public boolean checkComplete(String[] fourword) {
        for (String four : fourword) {
            if (four.equals("_")) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAns(String guess) {
        String[] guessArray = guess.split("");
        this.feedback = new ArrayList<>();
        for (int i = 0; i < guessArray.length; i++) {
            if (answer.get(i).equals(guessArray[i])) {
                feedback.add("Y");
                continue;
            }
            if (answer.contains(guessArray[i])) {
                feedback.add("X");
                continue;
            }
        }
        int count = 0;
        for (String s : feedback) {
            if (s.equals("Y")) {
                count++;
            }
        }
        if (count == 4) {
            System.out.println("Congratulations! on getting it right!");
            return true;
        }
        System.out.println(feedback.toString());

        return false;
    }

    public boolean checkDuplicate(String guess) {
        String[] guessArray = guess.split("");
        List<String> seen = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < guessArray.length; i++) {
            if (!seen.contains(guessArray[i])) {
                seen.add(guessArray[i]);
                count++;
            }
        }
        if (count == 4) {
            return false;
        }
        return true;
    }

    public void printAns() {
        System.out.println(answer.toString());
    }
}
