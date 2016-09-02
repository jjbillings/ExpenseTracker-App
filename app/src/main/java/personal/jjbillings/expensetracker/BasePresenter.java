package personal.jjbillings.expensetracker;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;


/**
 * (Base class for all our Presenters)
 * Based on the article: https://medium.com/picnic-engineering/tackling-technical-debt-with-mvp-67e805ed5103#.w8vmn4rc8
 * by: Henrique Boregio
 * @param <V>
 */
public abstract class BasePresenter<V> {

    private WeakReference<V> mView;

    public void bindView(@NonNull V view) {
        mView = new WeakReference<>(view);
    }

    public void unbindView() {
        mView = null;
    }

    public V getView() {
        if (mView == null) {
            return null;
        } else {
            return mView.get();
        }
    }

    public final boolean isViewAttached() {
        return mView != null && mView.get() != null;
    }
}
