package Entities;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * The Entities.Client is used to communicate with the servlet
 *
 * @author  Hao Li
 * @since   2021-12-05
 */
public class Client {

    /**
     * The login method post a User object to the servlet and get result as response
     *
     * @param user info
     * @return result
     */
    public String login(User user) throws Exception{
        //set up connection
        URL myURL = new URL("https://mil-servlet.herokuapp.com/login");

        return getResult(user, myURL);
    }

    /**
     * The register method post a User object to the servlet and get result as response
     *
     * @param user info
     * @return result
     */
    public String register(User user) throws Exception{
        //set up connection
        URL myURL = new URL("https://mil-servlet.herokuapp.com/register");

        return getResult(user, myURL);
    }

    /**
     * The delete method post a User object to the servlet and get result as response
     *
     * @param user info
     * @return result
     */
    public String delete(User user) throws Exception{
        //set up connection
        URL myURL = new URL("https://mil-servlet.herokuapp.com/delete");

        return getResult(user, myURL);
    }

    /**
     * Extracted method for post user info and get result resp
     *
     * @param user info
     * @param myURL url
     * @return result
     */
    private String getResult(User user, URL myURL) throws IOException {
        //set up conn
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();

        //transform user to gson format
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);

        //set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        //write the body of the request
        try (OutputStream outputStream = conn.getOutputStream()) {
            byte[] body = jsonString.getBytes(StandardCharsets.UTF_8);
            outputStream.write(body, 0, body.length);
        }
        catch(Exception e){
            return e.getMessage();
        }

        BufferedReader bufferedReader = new BufferedReader(new
                InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;//responded text from servlet

        // Read the body of the response
        inputLine = bufferedReader.readLine();
        bufferedReader.close();

        return inputLine;
    }
}
