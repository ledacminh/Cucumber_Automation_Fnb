package actions;

import java.util.HashMap;
import java.util.Map;

public class ListGlobalScenarioVariable {
    private static final Map<String, Object> listGlobalScenarioVariable = new HashMap<>();

    public static void setValue(String key, String value) {
        listGlobalScenarioVariable.put(key, value);
    }

    public static String getValue(String key) {
        return (String) listGlobalScenarioVariable.get(key);
    }
}
