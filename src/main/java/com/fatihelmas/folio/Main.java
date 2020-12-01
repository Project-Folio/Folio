package com.fatihelmas.folio;

import com.eclipsesource.v8.V8;
import com.fatihelmas.folio.loader.JavaScriptLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    private static JavaPlugin instance;
    private static JavaScriptLoader loader;

    private static String JS_PLUGINS_BASE_PATH;
    @Override
    public void onEnable() {
        instance = this;
        loader = new JavaScriptLoader(V8.createV8Runtime());
        JS_PLUGINS_BASE_PATH = this.getDataFolder().getPath() + File.separator + "plugins";

        loader.implement(new File(JS_PLUGINS_BASE_PATH));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
