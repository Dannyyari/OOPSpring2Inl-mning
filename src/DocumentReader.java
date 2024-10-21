import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentReader {
     public List<Person> getFile(String path){
         List <Person>personList= new ArrayList<>();
         String temp="";
         String temp2="";

         //tar in fil och gör till en rad
        try (BufferedReader buf= new BufferedReader(new java.io.FileReader(path))){
            while ((temp=buf.readLine())!=null){
                temp2=buf.readLine().trim();
                String enrad=temp.trim() +", "+ temp2.trim();
                String[] strArray= enrad.split(", ");
                personList.add(new Person(strArray[0], strArray[1], strArray[2]));
            }
        }catch (FileNotFoundException e){
            System.out.println("fil inte htttad: " + path);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fel vid inläsning av fil: "+ path);
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Oväntatat fel");
            e.printStackTrace();
        }
        return personList;
    }
    public String readOutPTfile(String pathToFile){
        String printer="";
        String temp;
        try (BufferedReader reader=new BufferedReader(new FileReader(pathToFile))) {
            System.out.println("lista på medlemmar som tränat");
            while ((temp=reader.readLine()) !=null){
                if (!temp.trim().isBlank()) {
                    printer+=temp +"\n"; //lägger till ny rad för snyggare utskrift
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Kunde ej hitta filen: " + pathToFile);
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("Fel vid läsning av PT fil: " + pathToFile);
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Något gick snett när vi ska läsa ut listan med deltagande" + e.getMessage());
            e.printStackTrace();
        }
        return printer; //returnera sträng för att ta ut listan med personer som tränat
    }

}
