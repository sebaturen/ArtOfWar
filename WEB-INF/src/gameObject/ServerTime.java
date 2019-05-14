/**
 * File : ServerTime.java
 * Desc : ServerTime controller
 * @author Sebastián Turén Croquevielle(seba@turensoft.com)
 */
package com.blizzardPanel.gameObject;

import com.blizzardPanel.Logs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class ServerTime 
{    
    //Constante
    public static final String PST_TIME_ZONE = "America/Chicago";
    public static final String MST_TIME_ZONE = "";
    public static final String CST_TIME_ZONE = "";
    public static final String EST_TIME_ZONE = "";
    public static final String TIME_ZONE = "PST";
    private static ServerTime svObject;
    
    //Atribute
    private final String timeZone;
    
    public ServerTime()
    {
        switch(ServerTime.TIME_ZONE)
        {
            case "MST": this.timeZone = MST_TIME_ZONE; break;
            case "CST": this.timeZone = CST_TIME_ZONE; break;
            case "EST": this.timeZone = EST_TIME_ZONE; break;
            default: this.timeZone = PST_TIME_ZONE; break;                
        }
    }
        
    /**
     *  Get current server time.
     * @return Date time
     */
    public static Date getServerTime()
    {
        Date currentTime = null;
        if(svObject == null) svObject = new ServerTime();
        try {
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTimeZone(TimeZone.getTimeZone(svObject.getTimeZone()));
            currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                    currentCalendar.get(Calendar.YEAR) +"-"+
                    (currentCalendar.get(Calendar.MONTH)+1) +"-"+ //calendar object start month in 0
                    currentCalendar.get(Calendar.DAY_OF_MONTH) +" "+
                    currentCalendar.get(Calendar.HOUR_OF_DAY) +":"+
                    currentCalendar.get(Calendar.MINUTE) +":"+
                    currentCalendar.get(Calendar.SECOND));
        } catch (ParseException ex) {
            Logs.errorLog(FactionAssaultControl.class, "Fail to get current time");
        }
        return currentTime;
    }    
    
    /**
     * Get last mantence time in long
     * 
     * @return 
     */
    public static long getLastResetTime()
    {
        return 1557237925000L;
    }
    
    /**
     * Get last season start time in long
     * @return 
     */
    public static long getSeasonTime()
    {
        return 1548633770000L;
    }
    
    public String getTimeZone()
    {
        return this.timeZone;
    }
    
}
