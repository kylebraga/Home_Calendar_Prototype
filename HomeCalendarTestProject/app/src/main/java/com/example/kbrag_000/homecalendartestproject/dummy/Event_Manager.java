package com.example.kbrag_000.homecalendartestproject.dummy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;




/**
 * Created by basedkyle on 2/28/16.
 */
public class Event_Manager {

    public String mTableName = "Events";
    public String mDatabaseName = "EventLog_DB";
    public String mEventIdLabel = "event_id";
    public String mEventNameLabel = "event_name";
    public String mEventOwnerLabel = "owner_name";
    public String mEventStartLabel = "start_time";
    public String mEventEndlabel = "end_time";
    public String mEventColorLabel = "event_color";
    public String mEntryType = " VARCHAR";
    public String mCommaSep = ",";
    public SQLiteDatabase eventDb;

    Event_Manager (String tableInput)
    {

    }

    public void getAllEventsForDay()
    {



    }

    public void CreateEventLog()
    {
        eventDb = eventDb.openOrCreateDatabase(mDatabaseName, null, null);
        eventDb.execSQL("CREATE TABLE IF NOT EXISTS" +
                mTableName + "(" +
                                    mEventIdLabel + mEntryType + mCommaSep +
                                    mEventNameLabel + mEntryType + mCommaSep +
                                    mEventIdLabel + mEntryType + mCommaSep +
                                    mEventIdLabel + mEntryType + mCommaSep +

                "id VARCHAR,name VARCHAR,marks VARCHAR);");
    }

}
