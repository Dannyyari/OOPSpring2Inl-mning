import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReceptionTest {
    //Här ska metoden för getCustomerInfo vara antingen JoptionPane eller terminal
    //besökaren ska klsssas in i kund, f.d kund eller obehörig.
        Reception reception=new Reception();
        LocalDateTime specificTest=LocalDateTime.of(2024,  Month.OCTOBER, 18, 10,30);
        LocalDate todaysDateTest= LocalDate.of(2024, Month.OCTOBER, 18);
        String fakeTodayTimeAndDate = specificTest.format(DateTimeFormatter.ofPattern("YYYY-MM-dd, HH:mm"));

        String PTfileTest= "test/PTFileTest.txt";


//BufferedWriter w = new BufferedWriter(new FileWriter(PTfileTest, true))
        @Test
    public void setToPTfileTest() {
            //skapa Person objekt för att testa.
            Person p1 = new Person("7703021234,", "Kasto Gabriel,","2024-07-01");
            Person p2 = new Person("8204021234,", "Hamra Ali,","2019-12-02");
            Person p3 = new Person("8512021234,", "Khan Ahnaf,", "2018-03-12");
            List<Person> expectedList =new ArrayList<>();
            expectedList.add(p1);
            expectedList.add(p2);
            expectedList.add(p3);

            //det som skrivs till fil
            String skrivasTillFil= p1.name+ fakeTodayTimeAndDate;
            String skrivasTillFil2= p2.name+ fakeTodayTimeAndDate;

            //anropar metod som ska testas
            //nollar filen först
            reception.setToPTFile(skrivasTillFil2,fakeTodayTimeAndDate, PTfileTest );
            try (BufferedWriter w = new BufferedWriter(new FileWriter(PTfileTest))) {
                w.write("");

            }catch (IOException e){
                e.printStackTrace();

            }

            reception.setToPTFile(skrivasTillFil,fakeTodayTimeAndDate, PTfileTest );
            try(BufferedReader reader= new BufferedReader(new FileReader(PTfileTest))) {
                String temp= reader.readLine();
                assertEquals(skrivasTillFil, temp);

            }catch (IOException e){
                e.printStackTrace();
            }
        //    reception.setToPTFile(skrivasTillFil2, PTfileTest);
          //  reception.setToPTFile(skrivasTillFil, PTfileTest);
         //   String testperson=  reception.isPersonStillMember(expectedList.get(0), todaysDateTest,PTfileTest);
            // String testperson2=  reception.isPersonStillMember(expectedList.get(1), todaysDateTest,PTfileTest);
            //String testperson3=  reception.isPersonStillMember(expectedList.get(2), todaysDateTest,PTfileTest);
            // System.out.println(testperson);
            // assertTrue(testperson.contains("Kasto Gabriel"));
            // assertTrue(testperson2.contains("Hamra Ali"));
            // assertTrue(testperson3.contains("Khan Ahnaf"));
        }
}
