import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public Main() {
        DocumentReader reader = new DocumentReader();
        Reception reception = new Reception();
        String pathToFile = "src/members.txt";
        String pathToPtFile = "src/PTfile.txt";
        LocalDate today= LocalDate.now();

        int welcome = reception.getWelcomeMessageToStartLoop();
        while (welcome == 0) {
            int val = reception.getWelcomeMessage();

            while (val != -1) {
                if ((val == 0) || (val == 1)) {
                    if (val == 0) {
                        String input = JOptionPane.showInputDialog(null, "Vänligen checka in");
                        System.out.println(reception.checkIfMember(reader.getFile(pathToFile), input, today));
                        break;

                    } else if (val == 1) {
                        System.out.println(reader.readOutPTfile(pathToPtFile));
                        JOptionPane.showMessageDialog(null, "titta i konsolen");
                        System.exit(0);
                        break;
                    }
                } else if (welcome != 0) {
                    JOptionPane.showMessageDialog(null, "hejdå");
                    System.exit(0);
                }
            }
            welcome = JOptionPane.showConfirmDialog(null, "Igen?");
            if (welcome != 0) {
                JOptionPane.showMessageDialog(null, "hejdå");
                System.exit(0);
            }
        }
        JOptionPane.showMessageDialog(null, "hejdå");
        System.exit(0);
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}