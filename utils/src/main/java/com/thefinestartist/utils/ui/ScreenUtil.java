package com.thefinestartist.utils.ui;

import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;

import com.thefinestartist.utils.content.ResourcesUtil;
import com.thefinestartist.utils.content.ThemeUtil;
import com.thefinestartist.utils.content.TypedValueUtil;
import com.thefinestartist.utils.service.WindowManagerUtil;

/**
 * ScreenUtil helps to calculate screen size conveniently.
 *
 * @author Leonardo Taehwan Kim
 */
public class ScreenUtil {

    public enum Rotation {
        ROTATION_0(0),
        ROTATION_90(1),
        ROTATION_180(2),
        ROTATION_270(3);

        int value;

        Rotation(int value) {
            this.value = value;
        }

        public static Rotation fromValue(int value) {
            for (Rotation rotation : values())
                if (rotation.value == value)
                    return rotation;

            return ROTATION_0;
        }
    }

    public static int getDeviceWidth() {
        Display display = WindowManagerUtil.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.x;
        } else {
            return display.getWidth();
        }
    }

    public static int getDeviceHeight() {
        Display display = WindowManagerUtil.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.y;
        } else {
            return display.getHeight();
        }
    }

    public static Rotation getRotation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO)
            return Rotation.fromValue(WindowManagerUtil.getDefaultDisplay().getRotation());
        else
            return Rotation.fromValue(WindowManagerUtil.getDefaultDisplay().getOrientation());
    }

    public static int getStatusBarHeight() {
        int resourceId = ResourcesUtil.getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ?
                ResourcesUtil.getDimensionPixelSize(resourceId) :
                0;
    }

    public static int getToolbarHeight() {
        return getActionBarHeight();
    }

    public static int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        return ThemeUtil.resolveAttribute(android.R.attr.actionBarSize, tv, true) ?
                TypedValueUtil.complexToDimensionPixelSize(tv.data) :
                0;
    }

    public static int getNavigationBarHeight() {
        int resourceId = ResourcesUtil.getIdentifier("navigation_bar_height", "dimen", "android");
        return resourceId > 0 ?
                ResourcesUtil.getDimensionPixelSize(resourceId) :
                0;
    }
}