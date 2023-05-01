package Convert;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;


import com.fasterxml.jackson.databind.ObjectMapper;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;


public class WebcaltoJson {

    public void WebcaltoJson1(String stringuri) {
      try {
        URL url = new URL(stringuri);
        CalendarBuilder builder = new CalendarBuilder();
        net.fortuna.ical4j.model.Calendar calendar = builder.build(url.openStream());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(calendar);
        FileWriter fw = new FileWriter("output1.json");
        fw.write(json);
        fw.close();
        System.out.println(json);

      } catch (IOException | ParserException e) {
        e.printStackTrace();
      }
    }
}

   

    