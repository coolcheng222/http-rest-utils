package com.sealll.utils.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author sealll
 * @describe
 * @time 2020/12/24 14:15
 */
public class JsonFileUtils {
    private static Gson gson = new Gson();
    private static JsonParser jp = new JsonParser();
    public static JsonElement getFromJson(String file){
        File file1 = new File(file);
        Reader rd = null;
        try {
            rd = new FileReader(file1, Charset.forName("utf-8"));
            JsonElement parse = jp.parse(rd);
            return parse;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {

            IOUtils.closeQuietly(rd);
        }
    }
}
