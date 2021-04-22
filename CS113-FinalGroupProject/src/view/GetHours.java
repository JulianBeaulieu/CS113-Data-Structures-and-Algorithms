/**
 * @author Julian Beaulieu/Nery Chapeton Lamas/Zach Chao
 * @git julianbeaulieu
 * @version 2.0
 * @description Class which uses the library api to get the opening hours
 */

package view;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetHours
{
  private static final String USER_AGENT = "Mozilla/5.0"; //needed for HTTP request
  private static final String MIRACOSTAURL = "https://api3.libcal.com/api_hours_today.php?iid=3045&lid=6354&format=xml&systemTime=0";
  private static final String SANELIJIOURL = "https://api3.libcal.com/api_hours_today.php?iid=3045&lid=6355&format=xml&systemTime=0";
  private static final String CLCURL = "https://api3.libcal.com/api_hours_today.php?iid=3045&lid=6366&format=xml&systemTime=0";


  public static String getMiraCostaOpeningHours()
  {
    String htmFile = makeCleanFile(MIRACOSTAURL);

    return htmFile;
  }

  public static String getSanElijoOpeningHours()
  {
    String htmFile = makeCleanFile(SANELIJIOURL);

    return htmFile;
  }

  public static String getCLCOpeningHours()
  {
    String htmFile = makeCleanFile(CLCURL);

    return htmFile;
  }

  /**
   * A getter of raw html from a url, outputting to a file
   * @param url The url to fetch
   */
  private static String makeCleanFile(String url)
  {
    StringBuilder sB = new StringBuilder();
    try
    {
      URL http = new URL(url);
      HttpURLConnection conn = (HttpURLConnection) http.openConnection();


      // optional default is GET
      conn.setRequestMethod("GET");
      //add request header
      conn.setRequestProperty("User-Agent", USER_AGENT);

      int responseCode = conn.getResponseCode();
      System.out.println("\nSending 'GET' request to URL : " + url);
      System.out.println("Response Code : " + responseCode);

      //Only execute if we had a 200 response code
      if (responseCode == 200)
      {
        Scanner dirtyFile = new Scanner(conn.getInputStream());

        while(dirtyFile.hasNextLine())
        {
          sB.append(preRinse(dirtyFile.nextLine()));
        }

        dirtyFile.close();
      }

      conn.disconnect();

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return sB.toString();
  }

  /** This method takes the String and cuts away all the tags
   * @param input
   * @return
   */
  private static String preRinse(String input)
  {
    StringBuilder sB = new StringBuilder();
    char[] c = input.toCharArray();
    boolean stopLoop = true;

    for(int i = 0; i < input.length() - 19 && stopLoop; i++)
    {
      String tempString = "" + c[i] + c[i + 1] + c[i + 2] + c[i + 3] + c[i + 4] + c[i + 5] + c[i + 6] + c[i + 7] + c[i + 8] + c[i + 9];

      if(tempString.equals("<rendered>"))
      {
        for(int j = i + 10; j < input.length() && stopLoop; j++)
        {
          if(c[j] == '<')
          {
            stopLoop = false;
          }
          else if(c[j] == '.')
          {
            sB.append("\n   ");
            j++;
          }
          else
          {
            sB.append(c[j]);
          }
        }
      }
    }

    return sB.toString();
  }
}
