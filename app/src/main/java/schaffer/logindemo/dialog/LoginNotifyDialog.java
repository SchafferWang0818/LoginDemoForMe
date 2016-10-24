package schaffer.logindemo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import schaffer.logindemo.R;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class LoginNotifyDialog extends Dialog {


    public LoginNotifyDialog(Context context) {
        super(context);
        Activity activity = (Activity) context;
        Window window = getWindow();
        View inflate = View.inflate(context, R.layout.dialog_login_notify, null);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(316, 90);
        setContentView(inflate, layoutParams);

        window.setGravity(Gravity.CENTER);
        window.getAttributes().dimAmount = 0;
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        window.setWindowAnimations(R.style.dialogAnimation);
        window.setBackgroundDrawable(null);
        window.getDecorView().setPadding(0, 0, 0, 0);
        setCanceledOnTouchOutside(true);
    }

}
