package androidx.lifecycle;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import com.mao.jp_livedata_java.core.BaseBusObserverWrapper;
import com.mao.jp_livedata_java.core.BusAlwaysActiveObserver;
import com.mao.jp_livedata_java.core.BusLifecycleObserver;
import com.mao.jp_livedata_java.core.LiveDataBusCore;

import java.util.HashMap;

/**
 * @author zhangkun
 * @time 2022/2/16 17:27
 * @Description
 */
public class BusLiveData<T> extends MutableLiveData<T> {

    private final HashMap<Observer<T>, BaseBusObserverWrapper<T>> mObserverMap = new HashMap<>();
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private final String mKey;

    public BusLiveData(String key) {
        this.mKey = key;
    }

    /**
     * 注册一个Observer，生命周期感知，自动取消订阅
     *
     * @param owner
     * @param observer
     */
    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        BaseBusObserverWrapper<T> exist = mObserverMap.get(observer);
        if (exist == null) {
            BusLifecycleObserver<T> lifecycleObserver =
                    new BusLifecycleObserver<>((Observer<T>) observer, owner, this);
            mObserverMap.put((Observer<T>) observer, lifecycleObserver);
            owner.getLifecycle().addObserver(lifecycleObserver);
            exist = lifecycleObserver;
        }
        super.observe(owner, exist);
    }

    /**
     * 注册一个Observer，需手动解除绑定
     *
     * @param observer
     */
    @MainThread
    @Override
    public void observeForever(@NonNull Observer<? super T> observer) {
        BaseBusObserverWrapper<T> exist = mObserverMap.get(observer);
        if (exist == null) {
            BusAlwaysActiveObserver<T> lifecycleObserver =
                    new BusAlwaysActiveObserver<>((Observer<T>) observer, this);
            mObserverMap.put((Observer<T>) observer, lifecycleObserver);
        }
        super.observeForever(observer);
    }


    /**
     * 注册一个Observer，生命周期感知，自动取消订阅
     * 如果之前有消息发送，可以在注册时收到消息（消息同步）
     *
     * @param owner
     * @param observer
     */
    @MainThread
    public void observeSticky(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
    }

    /**
     * 注册一个Observer，需手动解除绑定
     * 如果之前有消息发送，可以在注册时收到消息（消息同步）
     *
     * @param observer
     */
    @MainThread
    public void observeStickyForever(@NonNull Observer<? super T> observer) {
        super.observeForever(observer);
    }

    /**
     * observeForever observeStickyForever 的解除方法
     * @param observer
     */
    @Override
    public void removeObserver(@NonNull Observer<? super T> observer) {
        Observer<T> exist = mObserverMap.get(observer);
        if (exist != null) {
            mObserverMap.remove(observer);
        } else {
            exist = (Observer<T>) observer;
        }
        super.removeObserver(exist);
    }

    @Override
    public void postValue(T value) {
        mMainHandler.post(() -> setValue(value));
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (!hasObservers()) {
            // 当 LiveData 没有活跃的观察者时，可以移除相关的实例
            LiveDataBusCore.getInstance().getBusMap().remove(mKey);
        }
    }

    @Override
    public int getVersion() {
        return super.getVersion();
    }
}
