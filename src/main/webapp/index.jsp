<%@ page import="com.lordjoe.DisplayServlet" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
        }

        #fullSizeButton {
            width: 100%;
            height: 100%;
            background-color: red;
            color: white;
            border: none;
            font-size: 120px;
        }
    </style>

 
    <style>
        .SpeakButton {
            font-family: Arial, sans-serif; /* Set the font family */
            font-size: 120px; /* Set the font size */
            width: 100%; /* Set the width */
            height: 20%; /* Set the height */
            background-color: red;
        }
        table {
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<%
    // Set the page to refresh every 3 seconds
    response.setIntHeader("Refresh",3);
%>

<table>
    <style>
        td {
            font-size: 120px; /* Large text */
            font-weight: bold; /* Bold text */
        }
    </style>
<%
    Map<String, String> map =  DisplayServlet.displayValues;
    // Iterate over the map and display the key-value pairs
    for (Map.Entry<String, String> entry : map.entrySet()) {
%>
<tr>
    <td><%= entry.getKey() %></td>
    <td><%= entry.getValue() %></td>
</tr>
<%
    }
%>
</table>


<button class="SpeakButton" onclick="speakText(  )">Click to Read Values</button>

<script>
    var toSay = " <%= DisplayServlet.speakStr %>";
    function speakText( ) {

         // Create a new instance of SpeechSynthesisUtterance.
        var msg = new SpeechSynthesisUtterance();
        msg.text = toSay;
        // Set the text you want to speak.

        // Choose the voice. Uncomment the following lines to see the list of voices and select one.
        // voices = window.speechSynthesis.getVoices();
        // msg.voice = voices.filter(voice => voice.lang === 'en')[0]; // Select English voice, for example.

        // Speak the text.
        window.speechSynthesis.speak(msg);
    }
</script>


</body>
</html>
