/*
 * Copyright (c) 2019 Martin Pfeffer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.celox.utils;

/**
 * @author Martin Pfeffer
 * <a href="mailto:martin.pfeffer@celox.io">martin.pfeffer@celox.io</a>
 * @see <a href="https://celox.io">https://celox.io</a>
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.Map;

public class Log {

    public static void v(String tag, String msg) {
        System.out.println("V/" + tag + " - " + msg);
    }

    public static void i(String tag, String msg) {
        System.out.println("I/" + tag + " - " + msg);

    }

    public static void d(String tag, String msg) {
        System.out.println("D/" + tag + " - " + msg);

    }

    public static void w(String tag, String msg) {
        System.out.println("W/" + tag + " - " + msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        System.out.println("W/" + tag + " - " + msg + '\n' + getStackTraceString(tr));
    }

    public static void e(String tag, String msg) {
        System.out.println("E/" + tag + " - " + msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        System.out.println("E/" + tag + " - " + msg + '\n' + getStackTraceString(tr));
    }

    public static void wtf(String tag, String msg) {
        System.out.println("WTF/" + tag + " - " + msg);
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        System.out.println("WTF/" + tag + " - " + msg + '\n' + getStackTraceString(tr));
    }

    public static void logHashMap(String tag, int i, Map<String, Object> params) {
        for (String name : params.keySet()) {
            String v = params.get(name).toString();
            d(tag, "Map[" + i++ + "] " + name + " = " + v);
        }
    }

    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        } else {
            for (Throwable t = tr; t != null; t = t.getCause()) {
                if (t instanceof UnknownHostException) {
                    return "";
                }
            }
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            tr.printStackTrace(pw);
            pw.flush();
            return sw.toString();
        }
    }

}

