package com.xzc;

import com.xzc.Compile.Compile;
import com.xzc.Compile.wordAnalyse;
import com.xzc.Engine.engine;
import com.xzc.MyLoader.Myloader;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * 主程序入口
 */

public class MySimpleJvm {
    public static void main(String[] args) {
        //1.获取源文件 (源程序)
        File file = new File("source.txt");
        //2.交给编译器 编译 生成字节码"byteCodeClass"文件
        try {
            Compile.startCompile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.用自定义的装载器 解析 存放到方法区中
        Myloader.loadInArea();
        //4.手动启动执行引擎 返回计算结果
        Integer integer = engine.runEngine();
        System.out.println("处理结果是 : "+integer);
    }
}
