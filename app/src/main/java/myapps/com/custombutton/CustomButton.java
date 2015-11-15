package myapps.com.custombutton;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sajin on 11/15/2015.
 */
public class CustomButton extends LinearLayout {
    private Context mContext;

    //Background Attributes
    private int mDefaultBackgroundColor = Color.BLACK;
    private int mFocusBackgroundColor = 0;

    //Text Attributes
    private int mDefaultTextColor = Color.WHITE;
    private int mDefaultTextSize = 15;
    private int mDefaultTextGravity = 0x11; // Gravity.CENTER
    private String mText = null;

    //Icon Attributes
    private Drawable mIconResource = null;
    private int mIconPosition = 1;

    private int mIconPaddingLeft = 10;
    private int mIconPaddingRight = 10;
    private int mIconPaddingTop = 0;
    private int mIconPaddingBottom = 0;


    private int mBorderColor = Color.TRANSPARENT;
    private int mBorderWidth = 0;

    private int mRadius = 0;

    private Typeface mTextTypeFace = null;

    //icon position attribute
    public static final int POSITION_LEFT = 1;
    public static final int POSITION_RIGHT = 2;
    public static final int POSITION_TOP = 3;
    public static final int POSITION_BOTTOM = 4;

    private final String mDefaultText = "Custom Button";

    private ImageView mIconView;
    private TextView mFontIconView;
    private TextView mTextView;

    private boolean mGhost = false; // Default is a solid button !

    /**
     * Default constructor
     *
     * @param context : Context
     */
    public CustomButton(Context context) {
        super(context);
        this.mContext = context;
        mTextTypeFace = Typeface.DEFAULT;
        initializeCustomButton();
    }

    /**
     * Initialize Button dependencies
     * - Initialize Button Container : The LinearLayout
     * - Initialize Button TextView
     * - Initialize Button Icon
     */
    private void initializeCustomButton() {

        initializeButtonContainer();

        mTextView = setupTextView();
        mIconView = setupIconView();

        if (mIconView == null && mFontIconView == null && mTextView == null) {
            Button tempTextView = new Button(mContext);
            tempTextView.setText(mDefaultText);
            this.addView(tempTextView);
        } else {
            this.removeAllViews();
            setupBackground();

            ArrayList<View> views = new ArrayList<>();

            if (mIconView != null) {
                views.add(mIconView);
            }
            if (mFontIconView != null) {
                views.add(mFontIconView);
            }
            if (mTextView != null) {
                views.add(mTextView);
            }
            for (View view : views) {
                this.addView(view);
            }
        }
    }

    /**
     * Setup Text View
     *
     * @return : TextView
     */
    private TextView setupTextView() {
        if (mText != null) {
            TextView textView = new TextView(mContext);
            textView.setText(mText);
            textView.setGravity(mDefaultTextGravity);
            textView.setTextColor(mDefaultTextColor);
            textView.setTextSize(mDefaultTextSize);

            textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            // Checking edit mode
            if (!isInEditMode()) {
                textView.setTypeface(mTextTypeFace);
            }
            return textView;
        }
        return null;
    }

    /**
     * Text Icon resource view
     *
     * @return : ImageView
     */
    private ImageView setupIconView() {
        if (mIconResource != null) {
            ImageView iconView = new ImageView(mContext);
            iconView.setImageDrawable(mIconResource);
            iconView.setPadding(mIconPaddingLeft, mIconPaddingTop, mIconPaddingRight, mIconPaddingBottom);

            LayoutParams iconViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if (mTextView != null) {
                if (mIconPosition == POSITION_TOP || mIconPosition == POSITION_BOTTOM)
                    iconViewParams.gravity = Gravity.CENTER;
                else
                    iconViewParams.gravity = Gravity.START;

                iconViewParams.rightMargin = 10;
                iconViewParams.leftMargin = 10;
            } else {
                iconViewParams.gravity = Gravity.CENTER_VERTICAL;
            }
            iconView.setLayoutParams(iconViewParams);

            return iconView;
        }
        return null;
    }

    private void setupBackground() {

        // Default Drawable
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(mRadius);
        if (mGhost) {
            drawable.setColor(getResources().getColor(android.R.color.transparent)); // Hollow Background
        } else {
            drawable.setColor(mDefaultBackgroundColor);
        }
        if (mBorderColor != 0) {
            drawable.setStroke(mBorderWidth, mBorderColor);
        }

        // Focus/Pressed Drawable
        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setCornerRadius(mRadius);
        if (mGhost) {
            drawable2.setColor(getResources().getColor(android.R.color.transparent)); // No focus color
        } else {
            drawable2.setColor(mFocusBackgroundColor);
        }
        if (mBorderColor != 0) {
            if (mGhost) {
                drawable2.setStroke(mBorderWidth, mFocusBackgroundColor); // Border is the main part of button now
            } else {
                drawable2.setStroke(mBorderWidth, mBorderColor);
            }
        }

        StateListDrawable states = new StateListDrawable();

        if (mFocusBackgroundColor != 0) {
            states.addState(new int[]{android.R.attr.state_pressed}, drawable2);
            states.addState(new int[]{android.R.attr.state_focused}, drawable2);
        }
        states.addState(new int[]{}, drawable);

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackgroundDrawable(states);
        } else {
            this.setBackground(states);
        }
    }

    /**
     * Initialize button container
     */
    private void initializeButtonContainer() {

        if (mIconPosition == POSITION_TOP || mIconPosition == POSITION_BOTTOM) {
            this.setOrientation(LinearLayout.VERTICAL);
        } else {
            this.setOrientation(LinearLayout.HORIZONTAL);
        }
        LayoutParams containerParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(containerParams);
        this.setGravity(Gravity.CENTER);
        this.setClickable(true);
        this.setFocusable(true);
        if (mIconResource == null && getPaddingLeft() == 0 && getPaddingRight() == 0 && getPaddingTop() == 0 && getPaddingBottom() == 0) {
            this.setPadding(20, 20, 20, 20);
        }
    }

    /**
     * Set Text of the button
     *
     * @param text : Text
     */
    public void setText(String text) {
        this.mText = text;
        if (mTextView == null)
            initializeCustomButton();
        else
            mTextView.setText(text);
    }

    /**
     * Set the color of text
     *
     * @param color : Color
     *              use Color.parse('#code')
     */
    public void setTextColor(int color) {
        this.mDefaultTextColor = color;
        if (mTextView == null)
            initializeCustomButton();
        else
            mTextView.setTextColor(color);

    }

    /**
     * Setting the icon's color independent of the text color
     *
     * @param color : Color
     */
    public void setIconColor(int color) {
        if (mFontIconView != null) {
            mFontIconView.setTextColor(color);
        }
    }

    /**
     * Set Background color of the button
     *
     * @param color : use Color.parse('#code')
     */
    public void setBackgroundColor(int color) {
        this.mDefaultBackgroundColor = color;
        if (mIconView != null || mFontIconView != null || mTextView != null) {
            this.setupBackground();
        }
    }

    /**
     * Set Focus color of the button
     *
     * @param color : use Color.parse('#code')
     */
    public void setFocusBackgroundColor(int color) {
        this.mFocusBackgroundColor = color;
        if (mIconView != null || mFontIconView != null || mTextView != null)
            this.setupBackground();

    }

    /**
     * Set the size of Text in sp
     *
     * @param textSize : Text Size
     */
    public void setTextSize(int textSize) {
        this.mDefaultTextSize = textSize;
        if (mTextView != null)
            mTextView.setTextSize(textSize);
    }

    /**
     * Set the gravity of Text
     *
     * @param gravity : Text Gravity
     */

    public void setTextGravity(int gravity) {
        this.mDefaultTextGravity = gravity;
        if (mTextView != null) {
            mTextView.setGravity(gravity);
        }
    }

    /**
     * Set Padding for mIconView and mFontIconSize
     *
     * @param paddingLeft   : Padding Left
     * @param paddingTop    : Padding Top
     * @param paddingRight  : Padding Right
     * @param paddingBottom : Padding Bottom
     */
    public void setIconPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        this.mIconPaddingLeft = paddingLeft;
        this.mIconPaddingTop = paddingTop;
        this.mIconPaddingRight = paddingRight;
        this.mIconPaddingBottom = paddingBottom;
        if (mIconView != null) {
            mIconView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        }
        if (mFontIconView != null) {
            mFontIconView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        }
    }

    /**
     * Set an icon from resources to the button
     *
     * @param drawable : Drawable resource
     */
    public void setIconResource(int drawable) {
        this.mIconResource = mContext.getResources().getDrawable(drawable);
        if (mIconView == null || mFontIconView != null) {
            mFontIconView = null;
            initializeCustomButton();
        } else
            mIconView.setImageDrawable(mIconResource);
    }

    /**
     * Set Icon Position
     * Use the global variables (CustomButtons.POSITION_LEFT, CustomButtons.POSITION_RIGHT, CustomButtons.POSITION_TOP, CustomButtons.POSITION_BOTTOM)
     *
     * @param position : Position
     */
    public void setIconPosition(int position) {
        if (position > 0 && position < 5)
            mIconPosition = position;
        else
            mIconPosition = POSITION_LEFT;

        initializeCustomButton();
    }

    /**
     * Set color of the button border
     *
     * @param color : Color
     *              use Color.parse('#code')
     */
    public void setBorderColor(int color) {
        this.mBorderColor = color;
        if (mIconView != null || mFontIconView != null || mTextView != null) {
            this.setupBackground();
        }
    }

    /**
     * Set Width of the button
     *
     * @param width : Width
     */
    public void setBorderWidth(int width) {
        this.mBorderWidth = width;
        if (mIconView != null || mFontIconView != null || mTextView != null) {
            this.setupBackground();
        }
    }

    /**
     * Set Border Radius of the button
     *
     * @param radius : Radius
     */
    public void setRadius(int radius) {
        this.mRadius = radius;
        if (mIconView != null || mFontIconView != null || mTextView != null) {
            this.setupBackground();
        }
    }

}

