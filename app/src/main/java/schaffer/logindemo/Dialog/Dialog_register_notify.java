package schaffer.logindemo.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import schaffer.logindemo.R;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class Dialog_register_notify extends Dialog {


    public Dialog_register_notify(Context context) {
        super(context);
        Activity activity = (Activity) context;
        Window window = getWindow();
        View inflate = View.inflate(context, R.layout.layout_dialog_phone_register_notify, null);
        inflate.findViewById(R.id.dialog_register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(inflate);
        window.setLayout(526,308);
        window.setGravity(Gravity.CENTER);
        window.getAttributes().dimAmount = 0;
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        window.setWindowAnimations(R.style.dialogAnimation);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.getDecorView().setPadding(0, 0, 0, 0);
        setCanceledOnTouchOutside(false);
    }
}
