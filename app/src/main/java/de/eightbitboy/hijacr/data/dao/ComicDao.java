package de.eightbitboy.hijacr.data.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import de.eightbitboy.hijacr.data.dao.Comic;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COMIC".
*/
public class ComicDao extends AbstractDao<Comic, Long> {

    public static final String TABLENAME = "COMIC";

    /**
     * Properties of entity Comic.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Key = new Property(1, String.class, "key", false, "KEY");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Url = new Property(3, String.class, "url", false, "URL");
        public final static Property RandomUrl = new Property(4, String.class, "randomUrl", false, "RANDOM_URL");
        public final static Property FirstUrl = new Property(5, String.class, "firstUrl", false, "FIRST_URL");
        public final static Property LastUrl = new Property(6, String.class, "lastUrl", false, "LAST_URL");
        public final static Property RecentUrl = new Property(7, String.class, "recentUrl", false, "RECENT_URL");
        public final static Property RecentImageUrl = new Property(8, String.class, "recentImageUrl", false, "RECENT_IMAGE_URL");
        public final static Property ImageQuery = new Property(9, String.class, "imageQuery", false, "IMAGE_QUERY");
        public final static Property PreviousQuery = new Property(10, String.class, "previousQuery", false, "PREVIOUS_QUERY");
        public final static Property NextQuery = new Property(11, String.class, "nextQuery", false, "NEXT_QUERY");
        public final static Property RandomQuery = new Property(12, String.class, "randomQuery", false, "RANDOM_QUERY");
        public final static Property Hidden = new Property(13, boolean.class, "hidden", false, "HIDDEN");
        public final static Property Favourite = new Property(14, boolean.class, "favourite", false, "FAVOURITE");
    };


    public ComicDao(DaoConfig config) {
        super(config);
    }
    
    public ComicDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COMIC\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"KEY\" TEXT NOT NULL ," + // 1: key
                "\"TITLE\" TEXT NOT NULL ," + // 2: title
                "\"URL\" TEXT NOT NULL ," + // 3: url
                "\"RANDOM_URL\" TEXT," + // 4: randomUrl
                "\"FIRST_URL\" TEXT NOT NULL ," + // 5: firstUrl
                "\"LAST_URL\" TEXT," + // 6: lastUrl
                "\"RECENT_URL\" TEXT," + // 7: recentUrl
                "\"RECENT_IMAGE_URL\" TEXT," + // 8: recentImageUrl
                "\"IMAGE_QUERY\" TEXT NOT NULL ," + // 9: imageQuery
                "\"PREVIOUS_QUERY\" TEXT NOT NULL ," + // 10: previousQuery
                "\"NEXT_QUERY\" TEXT NOT NULL ," + // 11: nextQuery
                "\"RANDOM_QUERY\" TEXT," + // 12: randomQuery
                "\"HIDDEN\" INTEGER NOT NULL ," + // 13: hidden
                "\"FAVOURITE\" INTEGER NOT NULL );"); // 14: favourite
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COMIC\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Comic entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getKey());
        stmt.bindString(3, entity.getTitle());
        stmt.bindString(4, entity.getUrl());
 
        String randomUrl = entity.getRandomUrl();
        if (randomUrl != null) {
            stmt.bindString(5, randomUrl);
        }
        stmt.bindString(6, entity.getFirstUrl());
 
        String lastUrl = entity.getLastUrl();
        if (lastUrl != null) {
            stmt.bindString(7, lastUrl);
        }
 
        String recentUrl = entity.getRecentUrl();
        if (recentUrl != null) {
            stmt.bindString(8, recentUrl);
        }
 
        String recentImageUrl = entity.getRecentImageUrl();
        if (recentImageUrl != null) {
            stmt.bindString(9, recentImageUrl);
        }
        stmt.bindString(10, entity.getImageQuery());
        stmt.bindString(11, entity.getPreviousQuery());
        stmt.bindString(12, entity.getNextQuery());
 
        String randomQuery = entity.getRandomQuery();
        if (randomQuery != null) {
            stmt.bindString(13, randomQuery);
        }
        stmt.bindLong(14, entity.getHidden() ? 1L: 0L);
        stmt.bindLong(15, entity.getFavourite() ? 1L: 0L);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Comic readEntity(Cursor cursor, int offset) {
        Comic entity = new Comic( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // key
            cursor.getString(offset + 2), // title
            cursor.getString(offset + 3), // url
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // randomUrl
            cursor.getString(offset + 5), // firstUrl
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // lastUrl
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // recentUrl
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // recentImageUrl
            cursor.getString(offset + 9), // imageQuery
            cursor.getString(offset + 10), // previousQuery
            cursor.getString(offset + 11), // nextQuery
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // randomQuery
            cursor.getShort(offset + 13) != 0, // hidden
            cursor.getShort(offset + 14) != 0 // favourite
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Comic entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setKey(cursor.getString(offset + 1));
        entity.setTitle(cursor.getString(offset + 2));
        entity.setUrl(cursor.getString(offset + 3));
        entity.setRandomUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFirstUrl(cursor.getString(offset + 5));
        entity.setLastUrl(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setRecentUrl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRecentImageUrl(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setImageQuery(cursor.getString(offset + 9));
        entity.setPreviousQuery(cursor.getString(offset + 10));
        entity.setNextQuery(cursor.getString(offset + 11));
        entity.setRandomQuery(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setHidden(cursor.getShort(offset + 13) != 0);
        entity.setFavourite(cursor.getShort(offset + 14) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Comic entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Comic entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
