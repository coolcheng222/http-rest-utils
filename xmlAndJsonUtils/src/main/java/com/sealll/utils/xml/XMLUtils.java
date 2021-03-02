package com.sealll.utils.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUtils {
    static SAXReader reader = new SAXReader();

    public static<T> T getByXPath(String path,String xpath,T ins) {

        try {
            Document read = reader.read(new File(path));
            Element rootElement = read.getRootElement();
            List list = rootElement.selectNodes(xpath + "/*");
            for (Object o : list) {
                String name = ((Node) o).getName();
                String setName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
//                System.out.println(setName);
                Method method = ins.getClass().getMethod(setName, String.class);
                method.invoke(ins,((Node)o).getText());

            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.print("=");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("read xml error");
        }finally {
            System.out.println();
        }
        return ins;
    }
    public static Map<String,String> getMapByXPath(String path, String xpath) {

        try {
            Document read = reader.read(new File(path));
            Element rootElement = read.getRootElement();
            HashMap<String, String> map = new HashMap<>();
            List list = rootElement.selectNodes(xpath + "/*");
            for (Object o : list) {
                String name = ((Node) o).getName();
                String value = ((Node) o).getText();
                map.put(name,value);

            }
            return map;
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("read xml error");
        }finally {

        }

    }

}