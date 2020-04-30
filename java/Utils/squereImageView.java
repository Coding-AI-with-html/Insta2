package Utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class squereImageView extends AppCompatImageView {

    public squereImageView(Context context) {
        super(context);
    }

    public squereImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public squereImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
