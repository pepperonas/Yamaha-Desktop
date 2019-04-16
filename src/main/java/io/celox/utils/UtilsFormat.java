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

import java.text.DecimalFormat;

public class UtilsFormat {

    private static final String TAG = "UtilsFormat";

    public static String formatVersionCode(int versionCode) {
        String res = String.valueOf(versionCode);
        switch (res.length()) {
            case 1:
                return "0.0." + res;
            case 2:
                return "0." + res.charAt(0) + "." + res.charAt(1);
            case 3:
                return "" + res.charAt(0) + "." + res.charAt(1) + "." + res.charAt(2);
        }
        return "-1";
    }

    public static String formatVelocity(float speedKmh) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(speedKmh) + " km/h";
    }

    public static String formatKm(float speedKmh) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        return df.format(speedKmh) + " km";
    }

}
