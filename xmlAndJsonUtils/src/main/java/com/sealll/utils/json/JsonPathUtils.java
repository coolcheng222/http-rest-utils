package com.sealll.utils.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class JsonPathUtils
{
    public static <T>T getAThing(
    JsonElement json, String jsonPath,Class<T> clazz)
    {
        try
        {
            var objects = parse(jsonPath);

            JsonElement jo = json;
            for (Object o: objects)
            {
                var b = o instanceof String;
                if (b)
                {
                    if(!jo.isJsonObject()){
                        System.out.println(jo);
                        return null;
                    }

                    JsonObject object = jo.getAsJsonObject();
                    jo = object.get(o.toString());
                }
                else
                {
                    if(!jo.isJsonArray()){
                        return null;
                    }

                    JsonArray asJsonArray = jo.getAsJsonArray();
                    jo = asJsonArray.get((Integer) o);
                }
            }

            return clazz == String.class? (T) jo.toString() :new Gson().fromJson(jo,new TypeToken<T>(){}.getType());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private static List<Object> parse(String jsonPath)
    {
        Boolean mFlag = false;
        Boolean bFlag = false;
        var list = new ArrayList<Object>();

        var jsonP = jsonPath.getBytes();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < jsonP.length; i++)
        {
            if (bFlag && mFlag)
            {
                return null;
            }
            char curr = (char) jsonP[i];
            if ((Character.isDigit(curr) || Character.isLetter(curr)) && (mFlag ^ bFlag))
            {
                builder.append(curr);
            }else if ((curr == '[' && !mFlag && ((mFlag = !mFlag) || true))
                      || (curr == '{' && !bFlag && ((bFlag = !bFlag) || true)))
            {}else if (curr == ']')
            {
                if (mFlag)
                {
                    mFlag = false;
                    try
                    {
                        var res = Integer.parseInt(builder.toString());
                        list.add(res);
                    }
                    catch (Exception e)
                    {
                        return null;
                    }

                    builder.delete(0,builder.length());

                }
                else
                {
                    return null;
                }
            }else if (curr == '}')
            {
                if (bFlag)
                {
                    bFlag = false;
                    list.add(builder.toString());
                    builder.delete(0,builder.length());
                }
            }
            else
            {
                return null;
            }

        }

        if (mFlag || bFlag)
        {
            return null;
        }

        return list;
    }
}