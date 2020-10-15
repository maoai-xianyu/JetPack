package com.mao.lib_cache.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mao.lib_cache.dao.UserLiveDataDao;
import com.mao.lib_cache.dao.UserLoginDao;
import com.mao.lib_cache.model.UserLiveData;
import com.mao.lib_cache.model.UserLogin;
import com.mao.lib_common.utils.AppGlobals;

/**
 * @author zhangkun
 * @time 2020/9/27 8:15 PM
 * @Description
 */
@Database(entities = {UserLiveData.class, UserLogin.class}, version = 1,exportSchema = false)
public abstract class CacheDataBase extends RoomDatabase {

    private static final CacheDataBase databaseName;

    static {
        // 数据库的初始化
        // 创建临时数据库
        //Room.inMemoryDatabaseBuilder()
        // 创建持久化数据库
        databaseName = Room.databaseBuilder(AppGlobals.getApplication(), CacheDataBase.class, "database_name")
                // 是否允许在主线程执行查询操作
                .allowMainThreadQueries()
                .build();

    }

    public static CacheDataBase getDatabase() {
        return databaseName;
    }

    public abstract UserLiveDataDao userLiveDataDao();

    public abstract UserLoginDao userLoginDao();
}
