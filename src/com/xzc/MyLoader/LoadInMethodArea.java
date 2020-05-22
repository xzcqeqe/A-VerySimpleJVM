package com.xzc.MyLoader;

import com.xzc.Instrution.InstructSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 返回 执行引擎要执行的 指令信息.
 * 指令信息用map 封装 键存放 pc； 值存放指令和操作数
 *  Map<Integer, String[]> pc_instruct_num
 */
public class LoadInMethodArea {
    private static String pakageInfo;//包名信息
    private static String classInfo;//类名信息
    private static String mainInfo;//主函数信息
    private static Integer store_pc;//存放pc

    //用map集合 存储方法区的指令信息
    private    static  Map<Integer, String[]> pc_instruct_num = new HashMap<>();//键用来存储pc指令，用字符串数组值来存放指令

    //加载字节码文件到jvm 返回Map集合 包含 pc 和 指令信息 以及 操作数
    public static Map loader(ArrayList<String> conversed){
        Map<String, String> instructSet = InstructSet.getInstructSet();//取得字节码对应的指令集合

        String num = "";//记录操作数
        String finalInstruct;//记录最终转换的指令
        for (String s : conversed) {//遍历每一行字节码
            if(s.contains("package")){//获得包名信息
                LoadInMethodArea.pakageInfo = "The package is :  " + s;
                continue;
            }if(s.contains("class")){ //获得类信息
                LoadInMethodArea.classInfo = "The class   is :  " + s;
                continue;
            }if(s.contains("main")){ //获得主函数信息
                LoadInMethodArea.mainInfo = "public static void main  (java.long.String[]);";
                continue;
            }
            else {
                //剩下所有即 要为执行引擎准备的指令了
                //1.获取并设置pc
                store_pc = Integer.valueOf(s.substring(0, s.indexOf(":")));// 0: iconst_4 取得是0   取得指令序号放到 pc中
                //2.获取并保存操作数
                String instruct_num = s.substring(s.indexOf(":") + 1).trim();  //iconst_4    iadd
                char[] chars = instruct_num.toCharArray();  //0:iconst_4
                for(int index=0 ; index<chars.length;index++){
                    if(LoadInMethodArea.isDigit(chars[index])){
                        num = instruct_num.substring(instruct_num.indexOf(chars[index]));
                        break;
                    }
                    }
                //3.获取字节码中 指令集合 设置指令信息
                Set<String> ByteInstruct = instructSet.keySet();
                for (String byteCodeInstruct : ByteInstruct) {
                    if (s.contains(byteCodeInstruct)){
                         finalInstruct = instructSet.get(byteCodeInstruct);
                        String[] InstructInfo= {finalInstruct,num};
                        pc_instruct_num.put(store_pc,InstructInfo);
                    }
                }
            }
            num="";
        }
            return  pc_instruct_num;
    }

    //判断是否为数字
    public static boolean isDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        } else
            return false;
    }

}
