package andrew.cmu.edu.abhineec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/******************************************************************************
 * ListOfCrimes class represents a list of crimes held as SinglyLinkedList.
 * attribute listOfCrimes points to the list
 * attribute examinedNodes is used to track explored nodes while searching for
 * points within a range in a 2-d tree
 ******************************************************************************/
public class ListOfCrimes {
    private static final String KML_PREFIX = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<kml\n" +
            "\txmlns=\"http://earth.google.com/kml/2.2\">\n" +
            "\t<Document>\n" +
            "\t\t<Style id=\"style1\">\n" +
            "\t\t\t<IconStyle>\n" +
            "\t\t\t\t<Icon>\n" +
            "\t\t\t\t\t<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue\n" +
            "-dot.png</href>\n" +
            "\t\t\t\t</Icon>\n" +
            "\t\t\t</IconStyle>\n" +
            "\t\t</Style>" ;
    private static final String KML_PLACEMARK_PLACEHOLDER = "<Placemark>\n" +
            "\t\t\t<name>{{CRIME_OFFENSE}}</name>\n" +
            "\t\t\t<description>{{CRIME_STREET}}</description>\n" +
            "\t\t\t<styleUrl>#style1</styleUrl>\n" +
            "\t\t\t<Point>\n" +
            "\t\t\t\t<coordinates>\n" +
            "{{CRIME_LONGITUDE}},{{CRIME_LATITUDE}},0.000000</coordinates>\n" +
            "\t\t\t</Point>\n" +
            "\t\t</Placemark>";
    private static final String KML_POSTFIX = "</Document>\n" +
            "</kml>";

    SinglyLinkedList listOfCrimes;

    int examinedNodes;

    /**
     * Initialize a ListOfCrimes object
     * @postcondition
     *   An empty SinglyLinkedList object is initialized
     *   and examinedNodes is set to zero
     **/
    public ListOfCrimes(){
        this.listOfCrimes = new SinglyLinkedList();
        this.examinedNodes = 0;
    }
    /**
     * Helper function to add a crime to list
     */
    public void addCrime(Crime c){
        listOfCrimes.addAtEnd(c);
    }

    /**
     * Helper function to add all crimes in a list to current list
     */
    public void addFromCrimeList(ListOfCrimes crimeList){
        crimeList.listOfCrimes.reset();
        while(crimeList.listOfCrimes.hasNext()) {
            this.listOfCrimes.addAtEnd(crimeList.listOfCrimes.next());
        }
    }

    /**
     * Helper function to print list on console
     */
    public void print(){
        this.listOfCrimes.reset();
        while(listOfCrimes.hasNext()){
            Crime current = (Crime) listOfCrimes.next();
            System.out.println(current.toString());
        }
    }

    /**
     * Helper function to get string representation of crimes in list
     */
    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        listOfCrimes.reset();
        while(listOfCrimes.hasNext()){
            stringBuffer.append(listOfCrimes.next().toString());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    /**
     * Helper function to create KML representation and write to file named PGHCrimes.KML
     */
    public String toKML() throws IOException {
        StringBuffer kmlString = new StringBuffer();
        kmlString.append(KML_PREFIX);
        listOfCrimes.reset();
        while(listOfCrimes.hasNext()){
            Crime crime = (Crime) listOfCrimes.next();
            String kml_placemark_sub = KML_PLACEMARK_PLACEHOLDER;
            kml_placemark_sub = kml_placemark_sub.replace("{{CRIME_OFFENSE}}", crime.offense);
            kml_placemark_sub = kml_placemark_sub.replace("{{CRIME_STREET}}", crime.street);
            kml_placemark_sub = kml_placemark_sub.replace("{{CRIME_LONGITUDE}}", String.valueOf(crime.longitude));
            kml_placemark_sub = kml_placemark_sub.replace("{{CRIME_LATITUDE}}", String.valueOf(crime.latitude));
            kmlString.append(kml_placemark_sub);
        }
        kmlString.append(KML_POSTFIX);

        String FILE_PATH = "PGHCrimes.KML";
        Path path = Paths.get(FILE_PATH);
        Files.writeString(path, kmlString,  StandardCharsets.UTF_8);

        return System.getProperty("user.dir") + "\\" + FILE_PATH;
    }

}
