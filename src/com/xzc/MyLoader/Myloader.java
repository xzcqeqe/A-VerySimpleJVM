package com.xzc.MyLoader;

import com.xzc.MethodArea.methodArea;
import com.xzc.utils.Myconverse;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * 自定义装载模块
 * 返回的结果是包含pc和指令的map集合
 */
public class Myloader {

    public static void loadInArea() {
        File compiled = new File("byteCodeClass.txt");
        Myconverse myconverse = new Myconverse();
        ArrayList<String> conversed = myconverse.testConverse(compiled);//得到每一行字节码信息
        //得到指令信息
        Map<Integer,String[]> instruct_num = LoadInMethodArea.loader(conversed);
        //放到方法区中
        methodArea.setInstructMap(instruct_num);
    }
}
