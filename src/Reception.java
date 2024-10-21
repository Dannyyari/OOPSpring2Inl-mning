import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.List;

public class Reception {

    public String checkIfMember(List<Person> list, String inputFromUser, LocalDate currentDate) {
        currentDate = LocalDate.now();
        String noAccesToGym = " är inte behörig, vem är du?!";
        String fileToWriteTo = "src/PTfile.txt";
        try {
            for (Person medlem : list) {
                if (isMatchingMember(medlem, inputFromUser)) {
                    String medlemsstatus = isPersonStillMember(medlem, currentDate, fileToWriteTo);
                    return medlemsstatus;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Fel inmatning" + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("fel");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("fel i exception e");
        }
        getBeep();
        return inputFromUser + noAccesToGym;
    }

    //kollar att medlem finns.
    public boolean isMatchingMember(Person member, String inputFromUser) {
        return member.getName().equalsIgnoreCase(inputFromUser.trim()) ||
                member.getSocialSecurityNumber().equalsIgnoreCase(inputFromUser.trim());
    }

    public String isPersonStillMember(Person medlem, LocalDate currentDate, String pathToWritePT) {
        final String notAMemberAnyMore = " är inte längre medlem! Det gick slut för ca ";
        //tar in datum i strängform och konverterar till LocalDate.
        LocalDate lastPayment = LocalDate.parse(medlem.getDate());
        Long daysSinceLastPayment = ChronoUnit.DAYS.between(lastPayment, currentDate);


        //om medlem inte har betalat på mer än 1 år
        if (daysSinceLastPayment > 365) {
            //Delar på 30 så antalet dagar sedan personen sist betalde kommer upp
            //getBeep för att få en varningssignal när obehörig person kommer
            String notMember = medlem.name + notAMemberAnyMore + (daysSinceLastPayment / 30) + " månader sedan";
            getBeep();
            return notMember;
        } else {
            //finare formatering på instämplingstid
            LocalDateTime todayAndTime = LocalDateTime.now();
            String todayTimeAndDate = todayAndTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd, HH:mm, "));
            String fileToWriteTo = pathToWritePT;
            setToPTFile(medlem.getName() + ", " + medlem.getSocialSecurityNumber() + " tränade: " + todayTimeAndDate, fileToWriteTo);
            String member = medlem.name + " är medlem, Välkommen!";
            return member;
        }
    }

    public void setToPTFile(String stringToPrint, String pathToFile) {
        String fileToWriteTo = pathToFile;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWriteTo, true))) {
            writer.write(stringToPrint);
            writer.newLine(); //Ny rad i utskrift

        } catch (InputMismatchException e) {
            System.out.println("fel i inmatning" + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Fil ej funnen" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("fel i filhantering" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Oväntat fel" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void getBeep() {
        Toolkit.getDefaultToolkit().beep();
    }


    public int getWelcomeMessage() {
        int exit = -1;
        try {
            String[] option = {"Checka in i gym", "Ta ut PTfil"};
            int rutval = JOptionPane.showOptionDialog(null, "Vad vill du göra", "Tryck på knapp",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
            if (rutval == 0 || rutval == 1) {
                return rutval;
            } else if (rutval == -1) {
                return -1;
            }

        } catch (InputMismatchException e) {
            System.out.println("Fel i menyval");
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return exit;
    }

    public int getWelcomeMessageToStartLoop() {
        int exit = -1;
        try {
            String[] option = {"Dags att träna!", "Orkar inte träna"};
            int rutval = JOptionPane.showOptionDialog(null, "Hej och välkommen tll Best Ever Gym", "Tryck på knapp",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
            if (rutval == 0) {
                return rutval;
            } else if (rutval == 1) {
                return -1;
            } else if (rutval == JOptionPane.CLOSED_OPTION) {
                return -1;
            }

        } catch (InputMismatchException e) {
            System.out.println("Fel i menyval");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return exit;
    }
}