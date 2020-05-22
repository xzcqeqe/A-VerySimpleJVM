package com.xzc.Instrution;

import java.util.HashMap;
import java.util.Map;

/**
 * 指令集
 */
public class InstructSet {
     private static Map<String,String> InstructSet = new HashMap<>();

     public static  Map<String, String> getInstructSet(){
      //Push the int constant <i> (-1, 0, 1, 2, 3, 4 or 5)onto the operand stack
      InstructSet.put("iconst","PUSH");//推送到操作数栈

      //The <n> must be an index into the local variable array of the current frame (§2.6).
      // The value on the top of the operand stack must be of type int.
      // It is popped from the operand stack, and the value of the local variable at<n> is set to value
      InstructSet.put("istore","STORE");//操作数栈到局部变量

      //The <n> must be an index into the local variable array of the current frame (§2.6).
      // The local variable at <n> must contain an int.
      // The value of the local variable at <n> is pushed onto the operand stack.
      InstructSet.put("iload","LOAD");//局部变量到操作数栈

      //Both value1 and value2 must be of type int. The values are popped from the operand stack.
      // The int result is value1 * value2. The result is pushed onto the operand stack.
      InstructSet.put("imul","MUL");//弹出栈顶两个 做乘法 压到栈顶

      //Both value1 and value2 must be of type int. The values are popped from the operand stack.
      // The int result is value1 - value2. The result is pushed onto the operand stack.
      InstructSet.put("isub","SUB");//弹出两个  做减法  压到栈顶

      //Both value1 and value2 must be of type int. The values are popped from the operand stack.
      // The int result is value1 + value2. The result is pushed onto the operand stack.
      InstructSet.put("iadd","ADD");//弹出两个  做加法 压到栈顶

      //Both value1 and value2 must be of type int. The values are popped from the operand stack.
      // The int result is the value of the Java programming language expression value1 / value2.
      // The result is pushed onto the operand stack
      InstructSet.put("idiv","DIV");//弹出两个 做除法 压到栈顶

      //The current method must have return type void
      //exited as if by execution of a monitor exit instruction(§monitorexit) in the current thread
      InstructSet.put("return","HALT");//返回 停机指令 本实验没有考虑到线程安全等问题 所以直接停机即可

      return InstructSet;
     }

}
