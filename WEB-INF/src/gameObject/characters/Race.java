/**
 * File : Race.java
 * Desc : Race object
 * @author Sebastián Turén Croquevielle(seba@turensoft.com)
 */
package com.artOfWar.gameObject.characters;

import com.artOfWar.gameObject.GameObject;
import org.json.simple.JSONObject;

public class Race extends GameObject
{	
    //Races DB
    public static final String RACES_TABLE_NAME = "races";
    public static final String RACES_TABLE_KEY = "id";
    public static final String[] RACES_TABLE_STRUCTURE = {"id", "mask", "side", "name"};
    
    //Attribute
    private int id;
    private int mask;
    private String side;
    private String name;

    public Race(int id)
    {
        super(RACES_TABLE_NAME, RACES_TABLE_KEY, RACES_TABLE_STRUCTURE);
        loadFromDB(id+"");
    }

    public Race(JSONObject exInfo)
    {
        super(RACES_TABLE_NAME, RACES_TABLE_KEY, RACES_TABLE_STRUCTURE);
        saveInternalInfoObject(exInfo);
    }

    @Override
    protected void saveInternalInfoObject(JSONObject exInfo)
    {
        if(exInfo.get("id").getClass() == java.lang.Long.class) //if info come to blizzAPI or DB
        {			
            this.id = ((Long) exInfo.get("id")).intValue();
            this.mask = ((Long) exInfo.get("mask")).intValue();
        }
        else
        {
            this.id = (Integer) exInfo.get("id");
            this.mask = (Integer) exInfo.get("mask");
        }
        this.side = exInfo.get("side").toString();
        this.name = exInfo.get("name").toString();
        this.isData = true;		
    }

    @Override
    public boolean saveInDB()
    {
        /* {"id", "mask", "side", "name"}; */
        switch (saveInDBObj(new String[] {this.id +"", this.mask +"", this.side +"", this.name}))
        {
            case SAVE_MSG_INSERT_OK: case SAVE_MSG_UPDATE_OK:
                return true;
        }
        return false;
    }

    //Getters
    public String getId() { return this.id +""; }
    public int getMask() { return this.mask; }
    public String getSide() { return this.side; }
    public String getName() { return this.name; }
    @Override
    public void setId(String id) { this.id = Integer.parseInt(id); }
	
}