package com.xzc.Compile;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class getNibolan {
    /**
     * 根据正确的表达式, 获取逆波兰式
     *
     * @param input
     * @return java.lang.String
     */

    public static StringBuilder getRpn(String input) {
        //结果
        StringBuilder sb = new StringBuilder();
        //运算符栈
        Stack<Character> opStack = new Stack();

        //运算符优先级
        Map<Character, Integer> opMap = new HashMap(5);
        opMap.put('(', 0);
        opMap.put('+', 1);
        opMap.put('-', 1);
        opMap.put('*', 2);
        opMap.put('/', 2);

        //处理字符串
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i)==' ')continue;
            //如果是'('直接压栈
            if (input.charAt(i) == '(') {
                opStack.push('(');
            } else if (getNibolan.isOperator(input.charAt(i))) {
                //如果是运算符
                char curOp = input.charAt(i);
                //如果运算符栈是空，就直接压栈
                if (opStack.isEmpty()) {
                    opStack.push(curOp);
                } else if (opMap.get(curOp) > opMap.get(opStack.peek())) {
                    //运算符栈不为空，且当当前运算符的优先级比站内第一个运算符的优先级高的时候，压栈
                    opStack.push(curOp);
                } else {
                    //栈不为空，且运算符的优先级小于等于栈顶元素
                    for (int j = 0; j <= opStack.size(); j++) {
                        //弹出栈内第一个元素
                        char ch = opStack.pop();
                        sb.append(ch);
                        if (opStack.isEmpty()) {
                            opStack.push(curOp);
                            break;
                        } else if (opMap.get(curOp) > opMap.get(opStack.peek())) {
                            opStack.push(curOp);
                            break;
                        }
                    }
                }
            } else if (input.charAt(i) == ')') {
                //如果是')'就把站内'('上的元素都弹出栈
                for (int j = 0; j < opStack.size(); j++) {
                    char c = opStack.pop();
                    if (c == '(') {
                        break;
                    } else {
                        sb.append(c);
                    }
                }
            } else if ('A'<=input.charAt(i)&&input.charAt(i)<='Z'){
                //如果是字母就直接添加
                sb.append(input.charAt(i));
            }else if ('a'<=input.charAt(i)&&input.charAt(i)<='z'){
                //如果是字母就直接添加
                sb.append(input.charAt(i));
            }else if (Character.isDigit(input.charAt(i))){
                //如果是数字
                sb.append(input.charAt(i));
            }else {
                return new StringBuilder("But the expression contains unrecognizable characters");
            }
        }

        //把栈内剩余的运算符都弹出站
        for (int i = 0; i <= opStack.size(); i++) {
            sb.append(opStack.pop());
        }

        return sb;
    }
    /**
     * 判断是否为操作符 + - * /
     *
     * @param charAt
     * @return boolean
     */
    public static boolean isOperator(char charAt) {
        return charAt == '+' || charAt == '-' || charAt == '*' || charAt == '/';
    }

}
