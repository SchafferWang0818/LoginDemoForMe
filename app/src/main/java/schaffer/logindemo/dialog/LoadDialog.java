package schaffer.logindemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import schaffer.logindemo.R;

/**
 * Created by SchafferW on 2016/10/21.
 */

public class LoadDialog extends Dialog {


    private final ImageView imageView;
    private AnimationDrawable animationDrawable;

    public LoadDialog(Context context) {
        super(context);
        View inflate = View.inflate(context, R.layout.dialog_load, null);
        imageView = (ImageView) inflate.findViewById(R.id.dialog_load_iv);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(inflate, layoutParams);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setDimAmount(0.8f);
        window.setWindowAnimations(R.style.dialogAnimation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
        super.show();
    }

    @Override
    public void dismiss() {
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        super.dismiss();
    }
}
