package persistence;

import org.json.JSONObject;

/**
 * Interface for all classes saved as JSONObjects specifying necessary method
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

