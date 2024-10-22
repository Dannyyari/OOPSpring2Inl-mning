import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReceptionTest {
    Reception reception = new Reception();
    LocalDateTime specificTest = LocalDateTime.of(2024, Month.OCTOBER, 18, 10, 30);
    LocalDate fakeDate = LocalDate.of(2024, Month.OCTOBER, 18);
    String fakeTodayTimeAndDate = specificTest.format(DateTimeFormatter.ofPattern("YYYY-MM-dd, HH:mm"));
    String PTfileTest = "test/PTFileTest.txt";

    @Test
    public void setToPTfileTest() {
        //skapa Person objekt för att testa.
        Person p1 = new Person("7703021234,", "Kasto Gabriel,", "2024-07-01");
        Person p2 = new Person("8204021234,", "Hamra Ali,", "2019-12-02");
        Person p3 = new Person("8512021234,", "Khan Ahnaf,", "2018-03-12");
        List<Person> expectedList = new ArrayList<>();
        expectedList.add(p1);
        expectedList.add(p2);
        expectedList.add(p3);

        //det som skrivs till fil
        String skrivasTillFil = p1.name + fakeTodayTimeAndDate;
        String skrivasTillFil2 = p2.name + fakeTodayTimeAndDate;
        String skrivasTillFil3 = p3.name + fakeTodayTimeAndDate;


        //nollar filen först
        try (BufferedWriter w = new BufferedWriter(new FileWriter(PTfileTest))) {
            w.write("");

        } catch (IOException e) {
            e.printStackTrace();

        }

        //anropar metod som ska testas
        //matar in 3 strängar i filen
        reception.setToPTFile(skrivasTillFil, PTfileTest);
        reception.setToPTFile(skrivasTillFil2, PTfileTest);
        reception.setToPTFile(skrivasTillFil3, PTfileTest);
        try (BufferedReader reader = new BufferedReader(new FileReader(PTfileTest))) {
            String temp = reader.readLine();
            String temp2 = reader.readLine();
            String temp3 = reader.readLine();
            assertEquals(skrivasTillFil, temp);
            assertEquals(skrivasTillFil2, temp2);
            assertEquals(skrivasTillFil3, temp3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isPersonStilMemberTest() {
        // Skapar en person som nyligen har betalat
        Person member = new Person("7703021234", "Kasto Gabriel", "2024-07-01");
        String result = reception.isPersonStillMember(member, fakeDate, "mochpath.txt");

        assertEquals("Kasto Gabriel är medlem, Välkommen!", result);

        Person member2 = new Person("8204021234", "Hamra Ali", "2019-12-02");
        String resaultFalse = reception.isPersonStillMember(member2, fakeDate, "mockPath.txt");
        assertTrue(resaultFalse.contains("Hamra Ali är inte längre medlem"));
    }
}

