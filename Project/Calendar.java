import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Calendar {
    public static void main(String[] args){
        Calendar newCalendar = new Calendar();
        newCalendar.askEventDetails();

    }

    public void askEventDetails(){
        String eventName ="";
        String date="";
        String time="";
        Scanner keyboard = new Scanner(System.in);

        System.out.println("What is the name of the event you would like to add?");
        eventName=keyboard.nextLine();

        System.out.println("What is date of the event? Put in the form MM/DD/YYYY");
        date=keyboard.nextLine();

        System.out.println("What is the time of the event?");
        time=keyboard.nextLine();

        addEvent(eventName, date, time);
    }

    public void addEvent(String event, String dates, String times){

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
    public void checkEvent(){

    }
    public void readCalendarFile(){

    }
    public void getTodaysDate(){

    }



}
