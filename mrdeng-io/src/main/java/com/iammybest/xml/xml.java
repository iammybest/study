package com.iammybest.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by MrDeng on 2017/6/12.
 */
public class xml {
    public static void main(String[] args) {
//            Document domDocument =DocumentHelper.parseText("");

        Element root = DocumentHelper.createElement("body");
        Document document = DocumentHelper.createDocument(root);
        Element et = root.addElement("test1");

        et.addElement("tt1");
        et.addElement("tt2");
        et.addElement("tt3");
        root.addElement("test2");
        root.addElement("test3");
        System.out.println(document.asXML());
        getElement(root);
        System.out.println(document.asXML());
    }

    private static void getElement(Element element) {
        System.out.println("--------------------");

        //当前节点的名称、文本内容和属性
        System.out.println("当前节点名称：" + element.getName());//当前节点名称
        System.out.println("当前节点的内容：" + element.getTextTrim());//当前节点名称
        List<Attribute> listAttr = element.attributes();//当前节点的所有属性的list
        for (Attribute attr : listAttr) {//遍历当前节点的所有属性
            String name = attr.getName();//属性名称
            String value = attr.getValue();//属性的值
            System.out.println("属性名称：" + name + "属性值：" + value);
            if (value == null || value.isEmpty()) {
            }
        }

        //递归遍历当前节点所有的子节点
        List<Element> listElement = element.elements();//所有一级子节点的list
        for (Element e : listElement) {//遍历所有一级子节点
            if (e.getText() == null || e.getText().isEmpty()) {
                e.setText("");
            }
            getElement(e);//递归
        }
    }
}
