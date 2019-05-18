/**
 * File : EventAsist.java
 * Desc : Detail User asist to specific event
 * @author Sebastián Turén Croquevielle(seba@turensoft.com)
 */
package com.blizzardPanel.events;

import com.blizzardPanel.User;
import com.blizzardPanel.dbConnect.DBStructure;
import com.blizzardPanel.gameObject.GameObject;
import com.blizzardPanel.gameObject.characters.CharacterMember;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

public class EventAsist extends GameObject
{
    //DB Structure
    public static final String EVENTS_ASIST_TABLE_NAME = "events_asist";
    public static final String EVENTS_ASIST_TABLE_KEY = "id_asis";
    public static final String[] EVENTS_ASIST_TABLE_STRUCTURE = {"id_asis", "id_event", "user_id" };
    
    //Atribute
    private int idAsis = -1;
    private int idEvent;
    private User user;
    private List<EventAsistCharacter> eventCharacter = new ArrayList<>();
    
    public EventAsist()
    {
        super(EVENTS_ASIST_TABLE_NAME, EVENTS_ASIST_TABLE_KEY, EVENTS_ASIST_TABLE_STRUCTURE);
    }
    
    public EventAsist(int id)
    {
        super(EVENTS_ASIST_TABLE_NAME, EVENTS_ASIST_TABLE_KEY, EVENTS_ASIST_TABLE_STRUCTURE);
        loadFromDB(id);
    }

    @Override
    protected void saveInternalInfoObject(JSONObject objInfo) 
    {
        this.idAsis = (Integer) objInfo.get("id_asis");
        this.idEvent = (Integer) objInfo.get("id_event");
        this.user = new User((Integer) objInfo.get("user_id"));
        this.isData = true;
    }

    @Override
    public boolean saveInDB() 
    {
        String[] valArray = new String[] {this.idAsis+"", this.idEvent+"", this.user.getId()+""};
        if(this.idAsis != -1)
        {           
            /* {"id_event", "user_id" }; */
            setTableStructur(DBStructure.outKey(EVENTS_ASIST_TABLE_STRUCTURE));
        }
        else
        {
            valArray = DBStructure.outKey(valArray);
        }        
        switch (saveInDBObj(valArray))
        {
            case SAVE_MSG_INSERT_OK: case SAVE_MSG_UPDATE_OK:
                this.eventCharacter.forEach((evChar) -> {
                    evChar.saveInDB();
                });
                return true;
        } 
        return false;
    }
    
    public boolean setCharacters(CharacterMember mainChar, List<CharacterMember> altersChar)
    {
        
        
    }

    @Override
    public void setId(int id) { this.idAsis = id; }
    public void setEventId(int eId) { this.idEvent = eId; }
    public void setUser(User u) { this.user = u; }
    public void addAsisCharacter(EventAsistCharacter eaChar) { this.eventCharacter.add(eaChar); }
    
    @Override
    public int getId() { return this.idAsis; }
    public int getEventId() { return this.idEvent; }
    public User getUser() { return this.user; }
    public List getEventAsistCharacter() { return this.eventCharacter; }
    
}
