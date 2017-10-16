import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Calendar {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void start() throws IOException {

        Calendar newCalendar = new Calendar();
        System.out.println( newCalendar.checkEventToday());
        int choice=0;
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.println("Would you like to 1: Add an Event? 2: Find an Event by Name 3: Find an event by date? 4: Read your calendar? Enter 0 to Exit");
            choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    newCalendar.askEventDetails();
                    break;
                case 2:
                    newCalendar.getTodaysDate();
                    break;
                case 3:
                    newCalendar.checkEvent();
                    break;
                case 4:
                    try {
                        newCalendar.readCalendarFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;


            }

        }while(choice!=0);
    }

    public void askEventDetails(){
        String eventName ="";
        String date="";
        String time="";

        Scanner keyboard = new Scanner(System.in);

        System.out.println("What is the name of the event you would like to add?");

        eventName=keyboard.nextLine();
        System.out.println("What is date of the event? Put in the form MMDDYYYY");
        date=keyboard.nextLine();

        System.out.println("What is the time of the event?");
        time=keyboard.nextLine();
        time=keyboard.nextLine();

        addEvent(eventName, date, time);
    }

    public void addEvent(String event, String dates, String times){

        try
        {
            String filename= "calendarEvents.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write("\n"+event+" , "+dates+" , "+ times);//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }


    }
    public void checkEvent() throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        String nameOfEvent="";
        String dateOfEvent="";
        String choice="";
        do {
            System.out.println("Would you like to check a date or check a name?");
            choice = keyboard.nextLine();

            if(choice.equals("date")){
                System.out.println("What date would you like to check in the form MMDDYYYY?");
                dateOfEvent=keyboard.nextLine();
                checkDateEvent(dateOfEvent);


            }
            else if(choice.equals("name")){

                System.out.println("What is the name of the event?");
                nameOfEvent=keyboard.nextLine();
                checkNameEvent(nameOfEvent);
            }
            else{
                choice="";
                System.out.println("Please type 'name' or 'date'");

            }


        }while(choice.equals(""));






    }

    public void checkNameEvent(String searchName) throws FileNotFoundException {
        File file =new File("calendarEvents.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
            while(in.hasNext())
            {
                String line=in.nextLine();
                if(line.contains(searchName))
                    System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void checkDateEvent(String searchDate){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(searchDate);
        String searchStringDate     = sb.toString();
        File file =new File("calendarEvents.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
            while(in.hasNext())
            {
                String line=in.nextLine();
                if(line.contains(searchStringDate))
                    System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void readCalendarFile() throws IOException {
        // Open the file
        System.out.println("Printing out your calendar.....");

        FileInputStream fstream = new FileInputStream("calendarEvents.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

//Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
            // Print the content on the console
            System.out.println (strLine);
        }

//Close the input stream
        br.close();
    }
    public LocalDate getTodaysDate() throws IOException{
        LocalDate localDate = LocalDate.now();
        //  System.out.println(dtf.format());
        return localDate;
    }
    public String checkEventToday() throws IOException {
        String todayEvent = "No Events Today";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddYYYY");
        String formattedString= getTodaysDate().format(formatter);
        String strLine="";
        System.out.println(formattedString);
        FileInputStream fstream = new FileInputStream("calendarEvents.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));


        while ((strLine = br.readLine()) != null)   {
            // Print the content on the console
            if((strLine.contains(formattedString))){
                todayEvent=strLine;
                todayEvent = todayEvent.substring(0,todayEvent.indexOf(","));
                todayEvent = "You have an event today: "+todayEvent;
            }
        }

//Close the input stream
        br.close();
        return todayEvent;




    }



}