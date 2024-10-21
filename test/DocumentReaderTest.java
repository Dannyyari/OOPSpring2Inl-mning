import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentReaderTest {
    String realPath ="test/memebersTest.txt";

    DocumentReader reader= new DocumentReader();
    @Test
    public void getFileTester(){
        Person p1 = new Person("7703021234", "Kasto Gabriel","2024-07-01"
        );
        Person p2 = new Person("8204021234", "Hamra Ali",
                "2019-12-02");
        Person p3 = new Person("8512021234", "Khan Ahnaf",
                "2018-03-12");
        List<Person> expectedList =new ArrayList<>();
        expectedList.add(p1);
        expectedList.add(p2);
        expectedList.add(p3);

        List<Person> actualList= reader.getFile(realPath);

        assertEquals(expectedList.size(), actualList.size());
        assertFalse(actualList.isEmpty());
        assertFalse(expectedList.isEmpty());
        assertTrue(expectedList.size()==3);
    }

    @Test
    public void testgetFileWithInvalidPath(){
        String errorFilePath = "test/tomFil.txt";
        List<Person> invalidList=reader.getFile(errorFilePath);
        assertTrue(invalidList.isEmpty(), "Filen borde vara tom");

    }
 @Test
    public void testgetFileWithemotyFile(){
     String emptyFilePath = "test/tomFil.txt";
     List<Person> invalidList=reader.getFile(emptyFilePath);
     assertTrue(invalidList.isEmpty(), "Listan borde vara tom när filen är tom");

    }

}
