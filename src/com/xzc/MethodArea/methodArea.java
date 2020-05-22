package com.xzc.MethodArea;

import java.util.Map;

/**
 * 方法区  存放执行引擎要执行的指令信息
 */
public class methodArea {
    private static Map<Integer,String[]> instructMap;

    public static Map<Integer, String[]> getInstructMap() {
        return instructMap;
    }

    public static void setInstructMap(Map<Integer, String[]> instructMap) {
        methodArea.instructMap = instructMap;
    }
}
