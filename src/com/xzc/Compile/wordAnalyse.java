package com.xzc.Compile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//自定义编译器 只支持固定格式 以及int float double 等类型的基础运算
public class wordAnalyse {

    static String text = "";    //记录所有输出信息
    static Map<String, String> cal_map = new HashMap<>();//字母与赋予的值  映射关系
    static Map<String, Integer> local_map = new HashMap<>();//字母与要存放的局部变量表的索引值 映射关系
    static int location = 1;  //记录局部变量表中 应存储的位置索引
    static int pc = 0;              //记录PC计数器的位置


    public static String test1(ArrayList<String> s) {

        //String类型集合 s 存放每一行的信息
        for (String s1 : s) {      //遍历每一行s1进行编译解析
            if (s1.contains("package")) {//打印包信息
                text = s1 + '\n';
                continue;
            }
            if (s1.contains("class")) {//打印类名信息
                text += s1 + '\n';
                continue;
            }
            if (s1.contains("main")) {//打印主函数信息
                text += s1 + '\n';
                continue;
            }//遍历每一行 每一个索引位置 分析
            if (s1.contains("}")) continue; //把末尾有右括号的忽略 只考虑规范写法（所以做的比较鸡肋...）

            //说明这是计算行
            if (s1.contains("=")&&(s1.contains("+") || s1.contains("-") || s1.contains("*") || s1.contains("/") || s1.contains("(") || s1.contains(")"))) {
                //截取 等号后 分号前 的计算字符串
                String computeStr = s1.substring(s1.indexOf("=") + 1, s1.indexOf(";"));
                //引入逆波兰表达式求值工具
                String expression = getNibolan.getRpn(computeStr).toString();//得到逆波兰式 acb*-ac/+

                char[] NibolanChars = expression.toCharArray();//转换为字符数组
                String temp1 = "";
                for (int index = 0; index < NibolanChars.length; index++) {
                    if (wordAnalyse.isLetter(NibolanChars[index])) {//获取到逆波兰式中的字母
                        //获取字母应该 对应的局部变量表的索引
                        temp1 += NibolanChars[index];
                        Integer local = local_map.get(temp1);
                        text += pc + ": " + "iload " + local + '\n';
                        pc++;
                        temp1 = "";
                    }//剩下就是操作符
                    if (NibolanChars[index] == '+') {
                        text += pc + ": " + "iadd" + '\n';
                        pc++;
                    }
                    if (NibolanChars[index] == '-') {
                        text += pc + ": " + "isub" + '\n';
                        pc++;
                    }
                    if (NibolanChars[index] == '*') {
                        text += pc + ": " + "imul" + '\n';
                        pc++;
                    }
                    if (NibolanChars[index] == '/') {
                        text += pc + ": " + "idiv" + '\n';
                        pc++;
                    }

                }
                text+=pc+": "+"istore->"+location+'\n';
                location++;
                pc++;
            }
            //说明这是输出行
            if (s1.contains("out")){
                text+=pc+": "+"return";

            }
            else if(!s1.contains("+")&&!s1.contains("-")&&!s1.contains("*")&&!s1.contains("/")) {//不是运算行 只为了保证知识赋值语句而已
                //普通的赋值语句行
                    int index = 0;  //分析每一行每一个下标的索引
                    String temp = ""; //记录数字类型
                    String num = "";//记录数字
                    String identity = "";
                    //每一行字符串转换为 字符数组
                    char[] chars = s1.toCharArray();
                    //遍历到每一行最后一个  分号
                    while (chars[index] != ';') {
                        //111111111111当开头是字母时，有可能为保留字
                        if (wordAnalyse.isLetter(chars[index])) {
                            temp += chars[index];
//                        //下一个也也是字母，继续追加到temp中
                            while (wordAnalyse.isLetter(chars[index + 1])) {
                                index++;
                                temp += chars[index];
                            }
//                        //与保留数字匹配，成功为保留字
                            if (temp.equals("int")) {
                                text += pc + ": " + "iconst_";
                                pc++;
                            } else {  //表示什么数字赋值给哪个变量了 int a = 4 ; 就是 赋值给 a 了
                                identity = temp;
                            }
                        }
                        if (wordAnalyse.isDigit(chars[index])) {
                            num += chars[index];
                            //下一个也是数字 继续追加
                            while (wordAnalyse.isDigit(chars[index + 1])) {
                                index++;
                                num += chars[index];
                            }
                            text +=  num + '\n';
                            cal_map.put(identity, num);
                            local_map.put(identity, location);
                        }
                        index++;
                        temp = "";
                        num = "";
                    }
                    text += pc + ": " + "istore->" + location + '\n';
                    location++;
                    pc++;
                }
            }
        return text;
    }


    //判断是否为字母
    public static boolean isLetter(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        } else
            return false;
    }

    //判断是否为数字
    public static boolean isDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        } else
            return false;
    }

}

