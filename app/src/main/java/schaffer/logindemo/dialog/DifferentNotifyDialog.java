package schaffer.logindemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import schaffer.logindemo.R;

/**
 * Created by SchafferW on 2016/10/20.
 */

public class DifferentNotifyDialog extends Dialog {


    public DifferentNotifyDialog(Context context) {
        super(context);

        Window window = getWindow();
        View inflate = View.inflate(context, R.layout.dialog_show_different, null);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        setContentView(inflate, layoutParams);
        inflate.findViewById(R.id.dialog_dif_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
//        window.setLayout(526, 308);
        window.setGravity(Gravity.CENTER);
        window.getAttributes().dimAmount = 0;
        window.setWindowAnimations(R.style.dialogAnimation);
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.getDecorView().setPadding(0, 0, 0, 0);
        setCanceledOnTouchOutside(false);
    }
}
