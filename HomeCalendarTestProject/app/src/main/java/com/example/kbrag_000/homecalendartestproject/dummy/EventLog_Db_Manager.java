package com.example.kbrag_000.homecalendartestproject.dummy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by basedkyle on 2/28/16.
 */
public class EventLog_Db_Manager {

    public String mTableName = "Events";
    public String mDatabaseName = "EventLog_DB";
    public String mEventIdLabel = "event_id";
    public String mEventNameLabel = "event_name";
    public String mEventOwnerLabel = "owner_name";
    public String mEventStartLabel = "start_time";
    public String mEventEndLabel  = "end_time";
    public String mEventDateLabel = "event_date";
    public String mEventColorLabel = "event_color";
    public String mEntryType = " VARCHAR";
    public String mCommaSep = ",";
    public SQLiteDatabase eventDb;

    public String mCurrentEventId;
    public String mCurrentEventName;
    public String mCurrentEventOwner;
    public String mCurrentventStart;
    public String mCurrentEventEnd;
    public String mCurrentDate;
    public String mCurrentEventColor;

    public EventLog_Db_Manager()
    {
        CreateEventLog();
    }

    public void CreateEventLog()
    {
        eventDb = SQLiteDatabase.openOrCreateDatabase(mDatabaseName, null, null);

        eventDb.execSQL("CREATE TABLE IF NOT EXISTS" +
                mTableName + "(" +
                                mEventIdLabel + mEntryType + mCommaSep +
                                mEventNameLabel + mEntryType + mCommaSep +
                                mEventIdLabel + mEntryType + mCommaSep +
                                mEventOwnerLabel + mEntryType + mCommaSep +
                                mEventStartLabel + mEntryType + mCommaSep +
                                mEventEndLabel  + mEntryType + mCommaSep +
                                mEventDateLabel  + mEntryType + mCommaSep +
                                mEventColorLabel + mEntryType + mCommaSep +
                            ");");
    }

    public void CallInsertToEventLog(String idInput, String nameInput, String ownerInput,
                                     String startInput, String endInput, String colorInput, String dateInput)
    {
        mCurrentEventId = idInput;
        mCurrentEventName = nameInput;
        mCurrentEventOwner = ownerInput ;
        mCurrentventStart = startInput;
        mCurrentEventEnd = endInput;
        mCurrentDate = dateInput;
        mCurrentEventColor = colorInput;

        InsertToEventLog();
    }
    private void InsertToEventLog()
    {
        eventDb.execSQL("INSERT INTO" + mTableName + " VALUES(" +
                mCurrentEventId + mCommaSep +
                mCurrentEventName + mCommaSep +
                mCurrentEventOwner + mCommaSep +
                mCurrentventStart + mCommaSep +
                mCurrentEventEnd + mCommaSep +
                mCurrentEventColor + mCommaSep +
                ");");
    }
    public void CallGetFromEventLog(String dateInput)
    {
        mCurrentDate = dateInput;
        GetFromEventLog();
    }
    private void GetFromEventLog()
    {
        String queryString = "SELECT *" +
                            " FROM " + mTableName +
                            " WHERE " + mEventDateLabel + "= \"" + mCurrentDate + "\"";

        Cursor c = eventDb.rawQuery(queryString, null);

        if(c.getCount() == 0)
        {
            return;
        }

        while(c.moveToNext()) {
            mCurrentEventId = c.getString(0);
            mCurrentEventName = c.getString(1);
            mCurrentEventOwner = c.getString(2);
            mCurrentventStart = c.getString(3);
            mCurrentEventEnd = c.getString(4);
            mCurrentDate = c.getString(5);
            mCurrentEventColor = c.getString(6);
        }
    }
}
