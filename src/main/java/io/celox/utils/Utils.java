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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.swing.ImageIcon;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Utils {

    @SuppressWarnings("unused")
    private static final String TAG = "Utils";

    public enum OS {
        MAC, WIN, LINUX
    }

    public static OS getOperatingSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            return OS.MAC;
        } else if (os.contains("win")) {
            return OS.WIN;
        } else if (os.contains("linux")) {
            return OS.LINUX;
        }
        return OS.WIN;
    }

    // method catches errors globally and prints them into the log-file you'll find in user.home/jlog
    private static void logError(@SuppressWarnings("unused") Thread thread, Throwable throwable) {
        StringWriter errorMsg = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errorMsg));
        Log.e(TAG, errorMsg.toString());
    }

    public static void disableNodes(boolean disable, Node... nodes) {
        for (Node node : nodes) {
            node.setDisable(disable);
        }
    }

    public static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return sdf.format(date);
    }

    public static String getReadableTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        return sdf.format(date);
    }

    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }

    public static int getRandomInt(int min, int max, int multiply) {
        Random rand = new Random();
        return (rand.nextInt(max) + min) * multiply;
    }

    public static int getVersionFromProperties(Class clazz) {
        InputStream in = clazz.getResourceAsStream("app.properties");
        Properties props = new Properties();
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = (String) props.get("version");
        return Integer.parseInt(s);
    }

    public static String getAppNameFromProperties(Class clazz) {
        InputStream in = clazz.getResourceAsStream("app.properties");
        Properties props = new Properties();
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String) props.get("app_name");
    }

    public static void setAppIcon(Class clazz, Stage primaryStage) {
        String appName = Utils.getAppNameFromProperties(clazz);
        primaryStage.getIcons().clear();
        Image image = new Image(clazz.getResourceAsStream("/icons/icon.png"));
        primaryStage.getIcons().add(image);
        URL iconUrl = clazz.getResource("/icons/icon.png");
        java.awt.Image awtImage = new ImageIcon(iconUrl).getImage();
        if (Utils.getOperatingSystem() == OS.MAC) {
            com.apple.eawt.Application.getApplication().setDockIconImage(awtImage);
        }
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    private static boolean isInteger(String s, int radix) {
        if (s == null || s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

    public static String getHtml(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

}
