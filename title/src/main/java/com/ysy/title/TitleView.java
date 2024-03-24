package com.ysy.title;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.title.R;

public class TitleView extends ConstraintLayout {
    private OnClickListener clickIvRight,clickTvRightTitle;
    private AppCompatTextView tvLeftTitle,tvTitle,tvRightTitle;
    private AppCompatImageView ivBack,ivRight;
    public TitleView(@NonNull Context context) {
        super(context);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取布局
        inflate(context, R.layout.my_title_view, this);
        tvLeftTitle = findViewById(R.id.tvLeftTitle);
        tvTitle = findViewById(R.id.tvTitle);
        tvRightTitle = findViewById(R.id.tvRightTitle);
        ivRight = findViewById(R.id.ivRight);
        ivBack=findViewById(R.id.ivBack);

        ivRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickIvRight != null) {
                    clickIvRight.onClick(view);
                }
            }
        });
        tvRightTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickTvRightTitle!=null){
                    clickTvRightTitle.onClick(view);
                }
            }
        });

        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleView);

        //获取到属性值
        String LeftTitleText = typedArray.getString(R.styleable.MyTitleView_tvLeftTitle);
        tvLeftTitle.setText(LeftTitleText);
        String tvTitleText = typedArray.getString(R.styleable.MyTitleView_tvTitle);
        tvTitle.setText(tvTitleText);
        String tvRightTitleText = typedArray.getString(R.styleable.MyTitleView_tvRightTitle);
        tvRightTitle.setText(tvRightTitleText);

        int color = typedArray.getColor(R.styleable.MyTitleView_tvLeftTitleColor, Color.BLACK);
        tvLeftTitle.setTextColor(color);
        int color1 = typedArray.getColor(R.styleable.MyTitleView_tvTitleColor, Color.BLACK);
        tvTitle.setTextColor(color1);
        int color2 = typedArray.getColor(R.styleable.MyTitleView_tvRightTitleColor, Color.BLACK);
        tvRightTitle.setTextColor(color2);

        float defaultSizeInDp = 18f; // 默认大小为 16dp
        float defaultSizeInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, defaultSizeInDp, getResources().getDisplayMetrics());

        // 仅计算一次密度
        float density = getResources().getDisplayMetrics().density;
        // 计算标题大小
        float titleSizePx1 = typedArray.getDimension(R.styleable.MyTitleView_tvLeftTitleSize, defaultSizeInPx);
        float titleSizePx2 = typedArray.getDimension(R.styleable.MyTitleView_tvTitleSize, defaultSizeInPx);
        float titleSizePx3 = typedArray.getDimension(R.styleable.MyTitleView_tvRightTitleSize, defaultSizeInPx);
        // 设置文本大小
        tvLeftTitle.setTextSize(titleSizePx1 / density);
        tvTitle.setTextSize(titleSizePx2 / density);
        tvRightTitle.setTextSize(titleSizePx3/density);


        // 获取自定义属性中的资源 ID
        int ivBackDrawableResId = typedArray.getResourceId(R.styleable.MyTitleView_ivBack, 0);
        // 检查资源 ID 是否有效，如果为 0 则表示未设置自定义属性或资源未找到
        if (ivBackDrawableResId == 0) {
            // 如果未设置自定义属性或资源未找到，则设置默认的 Drawable
            ivBack.setImageResource(R.drawable.icon_fh);
        } else {
            // 使用从自定义属性中获取的资源 ID 设置图片资源
            ivBack.setImageResource(ivBackDrawableResId);
        }


        // 获取自定义属性中的资源 ID
        int ivRightDrawableResId = typedArray.getResourceId(R.styleable.MyTitleView_ivRight, 0);
        // 检查资源 ID 是否有效，如果为 0 则表示未设置自定义属性或资源未找到
        if (ivRightDrawableResId == 0) {
            // 如果未设置自定义属性或资源未找到，则设置默认的 Drawable

        } else {
            // 使用从自定义属性中获取的资源 ID 设置图片资源
            ivRight.setImageResource(ivRightDrawableResId);
        }


        float defaultMargInDp = 20f; // 默认大小为 20dp
        float defaultMargInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, defaultMargInDp, getResources().getDisplayMetrics());
        // 获取现有的布局参数
        LayoutParams layoutParams = (LayoutParams) ivBack.getLayoutParams();
        // 设置底部和左外边距
        layoutParams.bottomMargin = (int) typedArray.getDimension(R.styleable.MyTitleView_ivBackMarginBottom,defaultMargInPx);
        layoutParams.leftMargin = (int) typedArray.getDimension(R.styleable.MyTitleView_ivBackMarginLeft,defaultMargInPx);
        // 应用更新后的布局参数
        ivBack.setLayoutParams(layoutParams);

        LayoutParams layoutParams2 = (LayoutParams) tvLeftTitle.getLayoutParams();
        layoutParams2.leftMargin = (int) typedArray.getDimension(R.styleable.MyTitleView_TvLeftTitleMarginLeft,defaultSizeInPx);
        tvLeftTitle.setLayoutParams(layoutParams2);

        LayoutParams layoutParams3 = (LayoutParams) ivRight.getLayoutParams();
        layoutParams3.rightMargin = (int) typedArray.getDimension(R.styleable.MyTitleView_ivRightMarginRight,defaultMargInPx);
        ivRight.setLayoutParams(layoutParams3);

        //记得回收
        typedArray.recycle();
    }

    public AppCompatImageView getIvBackView(){
        return ivBack;
    }
    // 公共方法
    public void setClickIvRightListener(OnClickListener clickIvRight) {
        this.clickIvRight = clickIvRight;
    }
    public void setClickTvRightTitleListener(OnClickListener clickTvRightTitle) {
        this.clickTvRightTitle = clickTvRightTitle;
    }
    public void setTvLeftTitle(String title){
        tvLeftTitle.setText(title);
    }
    public void setTvLeftTitle(int resId){
        tvLeftTitle.setText(resId);
    }
    public void setTvTitle(String title){
        tvTitle.setText(title);
    }
    public void setTvTitle(int resId){
        tvTitle.setText(resId);
    }
    public void setTvRightTitle(String title){
        tvRightTitle.setText(title);
    }
    public void setTvRightTitle(int resId){
        tvRightTitle.setText(resId);
    }
    public void setIvBackDrawableResId(int resId){
        ivBack.setImageResource(resId);
    }
    public void setIvRightDrawableResId(int resId){
        ivRight.setImageResource(resId);
    }
    public void setTvLeftTitleColor(int colorId){
        tvLeftTitle.setTextColor(colorId);
    }
    public void setTvLeftTitleColor(String color){
        tvLeftTitle.setTextColor(Color.parseColor(color));
    }
    public void setTvTitleColor(int colorId){
        tvTitle.setTextColor(colorId);
    }
    public void setTvTitleColor(String color){
        tvTitle.setTextColor(Color.parseColor(color));
    }
    public void setTvRightTitleColor(int colorId){
        tvRightTitle.setTextColor(colorId);
    }
    public void setTvRightTitleColor(String color){
        tvRightTitle.setTextColor(Color.parseColor(color));
    }


}
