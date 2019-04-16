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

package io.celox;

import java.awt.SystemTray;

import io.celox.utils.Utils;
import io.celox.utils.UtilsFormat;
import io.celox.utils.UtilsGui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String TAG = "Main";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = fxmlLoader.load();
        MainController controller = fxmlLoader.getController();
        controller.setApp(this);
        controller.setStage(primaryStage);

        initScene(primaryStage, root);
        UtilsGui.closeOnEscAndExit(root);
    }

    private void initScene(Stage primaryStage, Parent root) {
        primaryStage.setTitle(Utils.getAppNameFromProperties(Main.class) + "-" +
                UtilsFormat.formatVersionCode(Utils.getVersionFromProperties(Main.class)));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        Utils.setAppIcon(Main.class, primaryStage);
        if (Utils.getOperatingSystem() == Utils.OS.WIN && SystemTray.isSupported()) {
            Platform.setImplicitExit(false);
        }
        primaryStage.show();
    }
}