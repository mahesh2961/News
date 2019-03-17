package in.news.data.db.utils;

import java.io.Serializable;
import java.sql.Timestamp;

import androidx.room.TypeConverter;

/**
 * Converter is used by Rooms Database for datatype conversion
 */
public class Converter implements Serializable {


    /**
     * Performs Long to TimeStamp conversion
     * @param timestamp long
     * @return Timestamp
     */
    @TypeConverter
    public static Timestamp longToTimestamp(Long timestamp) {
        return timestamp == null ? null : new Timestamp(timestamp);
    }

    /**
     * Performs TimeStamp to Long conversion
     * @param timestamp
     * @return
     */
    @TypeConverter
    public static Long timestampToLong(Timestamp timestamp) {
        return timestamp == null ? new Timestamp(System.currentTimeMillis()).getTime() : timestamp.getTime();
    }
}

