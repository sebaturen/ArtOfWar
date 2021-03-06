/**
 * File : Item.java
 * Desc : Item object
 * @author Sebastián Turén Croquevielle(seba@turensoft.com)
 */
package com.blizzardPanel.gameObject;

import com.blizzardPanel.dbConnect.DBLoadObject;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class Item {

    //Item DB
    public static final String TABLE_NAME = "items";
    public static final String TABLE_KEY = "id";

    // Attribute
    private long id;
    private JsonObject name;
    private boolean is_stackable;
    private String quality_type;
    private int level;
    private int required_level;
    private long media_id;
    private String inventory_type;
    private boolean is_equippable;

    // Update control
    private long last_modified;

    // Internal DATA
    private StaticInformation quality;
    private Media media;
    private StaticInformation inventory;

    public static class Builder extends DBLoadObject {

        private static Map<Long, Item> items = new HashMap<>();

        private long id;
        public Builder(long itemId) {
            super(TABLE_NAME, Item.class);
            this.id = itemId;
        }

        public Item build() {
            if (!items.containsKey(id)) {
                Item newItem = (Item) load(TABLE_KEY, id);

                // Load internal data
                newItem.quality = new StaticInformation.Builder(newItem.quality_type).build();
                newItem.inventory = new StaticInformation.Builder(newItem.inventory_type).build();
                if (newItem.media_id > 0) {
                    newItem.loadMedia();
                }
                items.put(id, newItem);
            }

            return items.get(id);
        }
    }

    // Constructor
    private Item() {

    }

    private void loadMedia() {
        media = new Media.Builder(Media.type.ITEM, media_id).build();
    }

    //------------------------------------------------------------------------------------------------------------------
    //
    // GET / SET
    //
    //------------------------------------------------------------------------------------------------------------------

    public long getId() {
        return id;
    }

    public Media getMedia() {
        if (media == null) {
            loadMedia();
        }
        return media;
    }

    public String getName(String locale) {
        return name.get(locale).getAsString();
    }

    @Override
    public String toString() {
        return "{\"_class\":\"Item\", " +
                "\"id\":\"" + id + "\"" + ", " +
                "\"name\": \"NAME\", " + //(name == null ? "null" : "\"" + name + "\"") + ", " +
                "\"is_stackable\":\"" + is_stackable + "\"" + ", " +
                "\"quality_type\":" + (quality_type == null ? "null" : "\"" + quality_type + "\"") + ", " +
                "\"level\":\"" + level + "\"" + ", " +
                "\"required_level\":\"" + required_level + "\"" + ", " +
                "\"media_id\":\"" + media_id + "\"" + ", " +
                "\"inventory_type\":" + (inventory_type == null ? "null" : "\"" + inventory_type + "\"") + ", " +
                "\"is_equippable\":\"" + is_equippable + "\"" + ", " +
                "\"last_modified\":\"" + last_modified + "\"" + ", " +
                "\"quality\":" + (quality == null ? "null" : quality) + ", " +
                "\"media\":" + (media == null ? "null" : media) + ", " +
                "\"inventory\":" + (inventory == null ? "null" : inventory) +
                "}";
    }
}
