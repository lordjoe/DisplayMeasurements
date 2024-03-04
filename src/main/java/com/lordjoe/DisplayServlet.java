package com.lordjoe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class DisplayServlet  extends HttpServlet
{
    public static String jsonStr = "{\"Temperature\":\"25\",\"Voltage\":\"17\",\"X\":\"127\"}";
    public static Map<String,String>   displayValues = processJSONString(jsonStr);

    public static String speakStr = "Temperature equals 25  Voltage equals 17 Speed equals 120";




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Use a BufferedReader to read the request payload (JSON data)
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            // Convert the StringBuilder to a String
            jsonStr = jsonBuilder.toString();

            // Use Jackson ObjectMapper to convert JSON string to a Java object
            displayValues = processJSONString(jsonStr);

            speakStr = sayValues(); // Assume sayValues() is a method returning a String
            request.setAttribute("json", jsonStr);
            request.setAttribute("speech", speakStr);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);



            // Set internal variables using the Java object
            // For example, set them as attributes in the servlet context or session
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static Map<String, String> processJSONString(String jsonData)    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(jsonData, Map.class);
            return map;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }

    }

    public static String sayValues() {
        if(displayValues.size() == 0)
            return "There is no data to display";
        StringBuilder sb = new StringBuilder();
        for (String s : displayValues.keySet()) {
            sb.append(s);
            sb.append(" equals ");
            sb.append(displayValues.get(s));
            sb.append(" ");
        }

        return sb.toString();
    }

}
