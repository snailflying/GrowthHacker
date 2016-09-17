//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.mw.growthhacker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BasePrefs {
    private SharedPreferences prefs;
    private Editor editor;

    protected BasePrefs(Context context, String prefsName) {
        this.prefs = context.getSharedPreferences(prefsName, 0);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return this.prefs.getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return this.prefs.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return this.prefs.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return this.prefs.getLong(key, defValue);
    }

    public String getString(String key, String defValue) {
        return this.prefs.getString(key, defValue);
    }

//    public Object getObject(String key) {
//        try {
//            String e = this.prefs.getString(key, "");
//            if(TextUtils.isEmpty(e)) {
//                return null;
//            } else {
//                byte[] base64Bytes = Base64.decode(e.getBytes());
//                ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
//                ObjectInputStream ois = new ObjectInputStream(bais);
//                return ois.readObject();
//            }
//        } catch (Exception var6) {
//            var6.printStackTrace();
//            return null;
//        }
//    }

    public void putBoolean(String key, boolean v) {
        this.ensureEditorAvailability();
        this.editor.putBoolean(key, v);
        this.save();
    }

    public void putFloat(String key, float v) {
        this.ensureEditorAvailability();
        this.editor.putFloat(key, v);
        this.save();
    }

    public void putInt(String key, int v) {
        this.ensureEditorAvailability();
        this.editor.putInt(key, v);
        this.save();
    }

    public void putLong(String key, long v) {
        this.ensureEditorAvailability();
        this.editor.putLong(key, v);
        this.save();
    }

    public void putString(String key, String v) {
        this.ensureEditorAvailability();
        this.editor.putString(key, v);
        this.save();
    }

//    public void putObject(String key, Object obj) {
//        this.ensureEditorAvailability();
//
//        try {
//            ByteArrayOutputStream e = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(e);
//            oos.writeObject(obj);
//            String stringBase64 = new String(Base64.encodeBase64(e.toByteArray()));
//            this.editor.putString(key, stringBase64);
//            this.save();
//        } catch (IOException var6) {
//            var6.printStackTrace();
//        }
//
//    }

    public void save() {
        if(this.editor != null) {
            this.editor.commit();
        }

    }

    private void ensureEditorAvailability() {
        if(this.editor == null) {
            this.editor = this.prefs.edit();
        }

    }

    public void remove(String key) {
        this.ensureEditorAvailability();
        this.editor.remove(key);
        this.save();
    }

    public void clear() {
        this.ensureEditorAvailability();
        this.editor.clear();
        this.save();
    }
}
