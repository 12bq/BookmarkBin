/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.linksaver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author chesp
 */

public class HTTPClient {
    
    private boolean verifyUrl(String url) {
        String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher m = pattern.matcher(url);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public String failedURLS ="";
    public String succeededURLS ="";
    public String incorrectURLS = "";
    
    public void validateUrl(String link) throws Exception {
            if (verifyUrl(link)) {
                try {
                    URL myURL = new URL(link);
                    HttpURLConnection myConnection = (HttpURLConnection) myURL.openConnection();
                    if (myConnection.getResponseCode() == URLStatus.HTTP_OK.getStatusCode()) {
                        succeededURLS =  link + "\nStatus message is : "
                                + URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode());
                    } else {
                        failedURLS = link + "\nStatus message is : "
                                + URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode());
                    }
                } catch (Exception e) {
                    System.out.print("For url- " + link + "" + e.getMessage());
                }
            } else {
                incorrectURLS = link;
            }
    }
}
