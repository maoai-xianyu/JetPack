package com.mao.lib_cache.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mao.lib_cache.model.UserLogin;

import java.util.List;

/**
 * @author zhangkun
 * @time 2020/9/28 1:29 PM
 * @Description 标注注解之后，会生成对应UserLoginDao的实现类
 */
@Dao
public interface UserLoginDao {


    // 如果是插入数据，只需要标记上Insert注解，并指明插入数据是如果已存在一条主键一样的数据，执行什么策略
    // REPLACE 直接替换老数据
    // ABORT 终止操作，并回滚事务，也就是老数据不影响
    // IGNORE 忽略冲突，但是会插入失败
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserLogin... userLogins);


    // 常规查询操作，需要写sql语句
    @Query("SELECT * FROM tb_user_login WHERE u_id IN (:userIds)")
    List<UserLogin> loadAllByIds(int[] userIds);


    // 3. 高级查询操作，可以通过liveData 以观察者的形式获取数据库数据，可以避免不必要的npe
    // 更重要的是  可以监听数据库表中的数据的变化。一旦发生了 insert update delete
    // room 会自动读取表中最新的数据，发送给UI层 刷新页面
    // 这一点是需要着重关注的
    @Query("SELECT * FROM tb_user_login")
    LiveData<List<UserLogin>> getAll();

    // 删除操作简单，也可以执行sql操作
    @Delete
    void delete(UserLogin userLogin);

    @Update
    void update(UserLogin userLogin);


}
