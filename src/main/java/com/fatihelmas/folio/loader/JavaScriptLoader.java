package com.fatihelmas.folio.loader;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;
import com.fatihelmas.folio.Main;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaScriptLoader {
    private final V8 runtime;
    private List<File> files = new ArrayList<>();

    public JavaScriptLoader(V8 runtime) {
        this.runtime = runtime;
    }
    public void load(File directory) {
        Arrays.stream(directory.listFiles()).filter(f -> f.getName().endsWith(".js")).forEach(f -> {
            files.add(f);
            implement(f);
        });
    }

    // Implementation of methods, variables etc...
    public void implement(File f) {
        
        try {
            runtime.executeScript(String.join("\n", Files.readAllLines(f.toPath()))); // load javascript codes
        } catch (IOException e) {
            e.printStackTrace();
        }

        V8Object obj = new V8Object(runtime);
        // Implementations
        addImplementation(Main.getInstance(), "folio", "get");

    }

    private void addImplementation(Object clazz, String clazzName, String methodName, Class<Object>... params) {
        V8Object v8Obj = new V8Object(runtime);
        runtime.add(clazzName, v8Obj);
        v8Obj.registerJavaMethod(clazz, methodName, methodName, params);
    }

}
