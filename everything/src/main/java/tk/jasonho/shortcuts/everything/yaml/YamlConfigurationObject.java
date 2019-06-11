package tk.jasonho.shortcuts.everything.yaml;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YamlConfigurationObject {
    @Getter
    private final Object yaml;
    @Getter
    private final String path;

    private YamlConfigurationObject(Object yamlStuff, String currentPath) {
        this.yaml = yamlStuff;
        this.path = currentPath;
    }

    static YamlConfigurationObject get(Object yamlStuff, String currentPath) {
        return new YamlConfigurationObject(yamlStuff, currentPath);
    }

    private Object internalGet(String key) {
        if(this.yaml instanceof Map) {
            Map<String, Object> yaml = ((Map<String, Object>) this.yaml);

            if(key.contains(".")) {
                String[] path = key.split("\\.", 2);
                return get(yaml.get(path[0]), this.path + (this.path.equals("") ? "" : ".") + path[0]).internalGet(path[1]);
            }

            if(yaml.containsKey(key)) {
                return yaml.get(key);
            }
        }
        return null;
    }

    private boolean isCompat(Class c, Object o) {
        return o.getClass().getName().equals(c.getName());
    }

    private boolean isNull(Object o) {
        return o == null;
    }

    private boolean useDefault(Class c, Object o) {
        return (isNull(o) || !isCompat(c, o));
    }

    public String getString(String key, String def) {
        return useDefault(String.class, internalGet(key)) ? def : ((String) internalGet(key));
    }
    public Integer getInt(String key, int def) {
        return useDefault(Integer.class, internalGet(key)) ? def : ((Integer) internalGet(key));
    }
    public Boolean getBoolean(String key, boolean def) {
        return useDefault(Boolean.class, internalGet(key)) ? def : ((Boolean) internalGet(key));
    }
    public ArrayList<Object> getList(String key) {
        return useDefault(ArrayList.class, internalGet(key)) ? null : ((ArrayList) internalGet(key));
    }

    public Object get(String key) {
        return internalGet(key);
    }

    public YamlConfigurationObject getSection(String k) {
        return get(internalGet(k), this.path);
    }

    public List<String> getKeys() {
        if(this.yaml instanceof Map) {
            return new ArrayList<>(((Map<String, Object>) this.yaml).keySet());
        }
        return new ArrayList<>();
    }

    public List<Object> getValues() {
        ArrayList<Object> obj = new ArrayList<>();
        if(this.yaml instanceof Map) {
            for (Object o : ((Map<String, Object>) this.yaml).values()) {
                obj.add(o);
            }
        }
        return obj;
    }

    public List<Map.Entry<String, Object>> getEntries() {
        if(this.yaml instanceof Map) {
            return new ArrayList<>(((Map<String, Object>) this.yaml).entrySet());
        }
        return new ArrayList<>();
    }
}
