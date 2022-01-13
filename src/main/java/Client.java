import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * The UI.Entities.Client is used to communicate with the servlet
 *
 * @author  Hao Li
 * @since   2021-12-05
 */
public class Client {

    public Client(){
    }

    public String login(User user) throws Exception{
        URL myURL = new URL("https://mil-servlet.herokuapp.com/login");
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();

        //transform user to gson format
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        byte[] body = jsonString.getBytes(StandardCharsets.UTF_8);

        //set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        //write the body of the login request
        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body, 0, body.length);
        }
        catch(Exception e){
            System.out.println(e);
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

            String inputLine;//responded text from servlet

            // Read the body of the login response
            inputLine = bufferedReader.readLine();
            bufferedReader.close();

            return inputLine;
        }
        catch (Exception e){
            System.out.println(e);
            return "response problem";
        }
    }

    public String register(User user) throws Exception{
        URL myURL = new URL("https://mil-servlet.herokuapp.com/register");
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();

        //transform user to gson format
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);

        //set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        //write the body of the register request
        try (OutputStream outputStream = conn.getOutputStream()) {
            byte[] body = jsonString.getBytes(StandardCharsets.UTF_8);
            outputStream.write(body, 0, body.length);
        }
        catch(Exception e){
            System.out.println("false in body");
        }

        BufferedReader bufferedReader = new BufferedReader(new
                InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;//responded text from servlet

        // Read the body of the login response
        inputLine = bufferedReader.readLine();
        bufferedReader.close();

        return inputLine;
    }

}