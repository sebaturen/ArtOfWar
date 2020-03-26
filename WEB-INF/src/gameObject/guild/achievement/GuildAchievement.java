/**
 * File : GuildAchivement.java
 * Desc : Guild Achivement Object
 * @author Sebastián Turén Croquevielle(seba@turensoft.com)
 */
package com.blizzardPanel.gameObject.guild.achievement;

import com.blizzardPanel.Logs;
import com.blizzardPanel.gameObject.GameObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonObject;

public class GuildAchievement extends GameObject
{
    //TABLE STRUCTURE
    public static final String TABLE_NAME = "guild_achievements";
    public static final String TABLE_KEY = "achievement_id";
    public static final String[] TABLE_STRUCTURE = {"achievement_id", "time_completed"};
    
    //Atribute
    private int achivementId;
    private GuildAchievementsList achievement;
    private Date timeCompleted;
    
    public GuildAchievement(int ahId)
    {
        super(TABLE_NAME, TABLE_KEY, TABLE_STRUCTURE);
        loadFromDB(ahId);
    }
    
    public GuildAchievement(JsonObject info)
    {
        super(TABLE_NAME, TABLE_KEY, TABLE_STRUCTURE);
        saveInternalInfoObject(info);
    }

    @Override
    protected void saveInternalInfoObject(JsonObject objInfo)
    {
        this.achivementId = objInfo.get("achievement_id").getAsInt();
        this.achievement = new GuildAchievementsList(this.achivementId);
        try { //2018-10-17 02:39:00
            this.timeCompleted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objInfo.get("time_completed").getAsString());
        } catch (ParseException ex) {
            Logs.errorLog(GuildAchievement.class, "(DB) Fail to convert date from challenge group! "+ this.achivementId +" - "+ ex);
        }
        this.isData = true;
    }

    @Override
    public boolean saveInDB() 
    {        
        /* {"achievement_id", "time_completed"} */
        switch (saveInDBObj(new String[] {this.achivementId +"", getDBDate(this.timeCompleted)}))
        {
            case SAVE_MSG_INSERT_OK: case SAVE_MSG_UPDATE_OK:
            return true;
        }
        return false;
    }

    @Override
    public void setId(int id) 
    { 
        this.achivementId = id; 
        this.achievement = new GuildAchievementsList(this.achivementId); 
    }

    @Override
    public int getId() { return this.achivementId; }
    public GuildAchievementsList getAchievement() { return this.achievement; }
    public Date getTimeCompleted() { return this.timeCompleted; }
    
    
}
