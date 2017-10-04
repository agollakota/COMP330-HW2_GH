import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Calendar {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) throws FileNotFoundException {
        Calendar newCalendar = new Calendar();
        newCalendar.askEventDetails();
        newCalendar.getTodaysDate();
        newCalendar.checkEvent();

    }

    public void askEventDetails(){
        String eventName ="";
        int date=0;
        String time="";
        Scanner keyboard = new Scanner(System.in);

        System.out.println("What is the name of the event you would like to add?");
        eventName=keyboard.nextLine();

        System.out.println("What is date of the event? Put in the form MMDDYYYY");
        date=keyboard.nextInt();

        System.out.println("What is the time of the event?");
        time=keyboard.nextLine();
        time=keyboard.nextLine();

        addEvent(eventName, date, time);
    }

    public void addEvent(String event, int dates, String times){

        try
        {
            String filename= "calendarEvents.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write("\n"+event+", "+dates+", "+ times);//appends the string to the file
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
        int dateOfEvent=0;
        String choice="";
        do {
            System.out.println("Would you like to check a date or check a name?");
            choice = keyboard.nextLine();

            if(choice.equals("date")){
                System.out.println("What date would you like to check in the form MMDDYYYY?");
                dateOfEvent=keyboard.nextInt();
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

    public void checkDateEvent(int searchDate){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(searchDate);
        String searchStringDate = sb.toString();
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


    public void readCalendarFile(){


    }
    public void getTodaysDate(){
        LocalDate localDate = LocalDate.now();
        //  System.out.println(dtf.format());

    }



}