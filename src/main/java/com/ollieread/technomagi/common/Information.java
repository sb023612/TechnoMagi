package com.ollieread.technomagi.common;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ollieread.technomagi.TechnoMagi;

public class Information
{

    private static Map<String, Map<String, List<String>>> data = new HashMap<String, Map<String, List<String>>>();
    private static Gson gson = new Gson();

    public static void load(String name)
    {
        try {
            String location = "/assets/technomagi/information/" + name + ".json";
            Type jsonType = new TypeToken<Map<String, List<String>>>()
            {
            }.getType();
            InputStream resource = TechnoMagi.class.getResourceAsStream(location);
            Map<String, List<String>> json = gson.fromJson(IOUtils.toString(resource), jsonType);
            data.put(name, json);
        } catch (IOException e) {
            TechnoMagi.logger.warn("Unable to load information for: " + name);
            e.printStackTrace();
        } catch (Exception e) {
            TechnoMagi.logger.warn("Unable to load information for: " + name);
            e.printStackTrace();
        }
    }

    public static String getInformation(String type, String name)
    {
        if (data.containsKey(type)) {
            Map<String, List<String>> information = data.get(type);

            if (information.containsKey(name)) {
                Object info = information.get(name);

                if (info instanceof String) {
                    return (String) info;
                } else if (info instanceof List<?>) {
                    List<String> list = (List<String>) info;

                    String paragraph = null;

                    for (Iterator<String> p = list.iterator(); p.hasNext();) {
                        if (paragraph == null) {
                            paragraph = p.next();
                        } else {
                            paragraph += "\n\n" + p.next();
                        }
                    }

                    return paragraph;
                }
            }
        }

        return "";
    }
}
