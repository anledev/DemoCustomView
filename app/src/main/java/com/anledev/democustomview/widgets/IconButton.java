package com.anledev.democustomview.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.anledev.democustomview.R;

public class IconButton extends CardView {

    private int mResId = 0;
    private String mTitle = "";
    private Boolean mShowIconRight = false;
    private Boolean mTitleTextCapAll = false;
    private int mIconSize = 0;
    private int mPaddingItem = 0;

    private RelativeLayout itemCard;
    private ImageView ivIcon;
    private ImageView ivRight;
    private TextView txtTitle;

    public IconButton(@NonNull Context context) {
        super(context);
        init(null);
    }

    public IconButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public IconButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        initView();
        loadAttrs(attributeSet);
        setupViews();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.button_icon, this, true);
        itemCard = view.findViewById(R.id.itemCard);
        ivIcon = view.findViewById(R.id.ivIcon);
        ivRight = view.findViewById(R.id.ivRight);
        txtTitle = view.findViewById(R.id.txtTitle);
    }

    private void setupViews() {
        setSizeImage();
        setPaddingItem();
        txtTitle.setAllCaps(mTitleTextCapAll);
        showIcon();
        setTitle(mTitle);
        setShowIconRight(mShowIconRight);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCard.performClick();
            }
        });
    }

    public void setTitle(String title) {
        txtTitle.setText(title);
    }

    private void showIcon() {
        ivIcon.setImageResource(mResId);
        if (mResId != 0) {
            ivIcon.setVisibility(VISIBLE);
        } else {
            ivIcon.setVisibility(GONE);
        }
    }

    private void setShowIconRight(boolean showIcon) {
        if (showIcon) {
            ivRight.setVisibility(VISIBLE);
        } else {
            ivRight.setVisibility(GONE);
        }
    }

    private void loadAttrs(AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.IconButton);
        mResId = typedArray.getResourceId(R.styleable.IconButton_iconSrc, 0);
        mTitle = typedArray.getString(R.styleable.IconButton_buttonTitle);
        mShowIconRight = typedArray.getBoolean(R.styleable.IconButton_showIconRight, true);
        mTitleTextCapAll = typedArray.getBoolean(R.styleable.IconButton_titleTextCapAll, false);
        mIconSize = typedArray.getDimensionPixelOffset(R.styleable.IconButton_iconSize, 0);
        mPaddingItem = typedArray.getDimensionPixelSize(R.styleable.IconButton_buttonPadding, 0);
        typedArray.recycle();
    }

    private void setPaddingItem() {
        if (mPaddingItem == 0) return;
        itemCard.setPadding(mPaddingItem, mPaddingItem, mPaddingItem, mPaddingItem);
    }

    private void setSizeImage() {
        if (mIconSize == 0) return;
        ivIcon.getLayoutParams().width = mIconSize;
        ivIcon.getLayoutParams().height = mIconSize;
        ivIcon.requestLayout();
        ivRight.getLayoutParams().width = mIconSize;
        ivRight.getLayoutParams().height = mIconSize;
        ivRight.requestLayout();
    }

}
