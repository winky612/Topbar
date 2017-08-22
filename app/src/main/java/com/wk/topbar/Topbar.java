package com.wk.topbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by wk on 2017/1/13.
 */

public class Topbar extends RelativeLayout {

    //声明自定义控件
    private Button leftButton, rightButton;
    private TextView tvTitle;

    //声明这些控件所用到的属性
    private int topbar_leftTextColor;
    private String topbar_leftText;
    private Drawable topbar_leftBackground;

    private int topbar_rightTextColor;
    private String topbar_rightText;
    private Drawable topbar_rightBackground;

    private int topbar_titleTextColor;
    private float topbar_titleTextSize;///???为啥不是 dimension?
    private String topbar_title;

    private LayoutParams leftParams,rightParams;
    private LayoutParams titleParams;

    private topbarClickListener listener;

    public interface topbarClickListener{
        void leftClick();
        void rightClick();
    }

    //暴露一个方法给调用者 这样 调用者就可以把它的实现以匿名内部类的形式传递进来
    public void setOnTopbarClickLisener(topbarClickListener listener){
        this.listener=listener;
    }

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //关联控件 和 属性

        //在构造方法中 获得在xml中定义的属性 并把这些属性值赋给控件（放在TypeArray中，再挨个取出来）
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        topbar_leftTextColor = typedArray.getColor(R.styleable.Topbar_topbar_leftTextColor, 0);
        topbar_leftText = typedArray.getString(R.styleable.Topbar_topbar_leftText);
        topbar_leftBackground = typedArray.getDrawable(R.styleable.Topbar_topbar_leftBackground);

        topbar_rightTextColor = typedArray.getColor(R.styleable.Topbar_topbar_rightTextColor, 0);
        topbar_rightText = typedArray.getString(R.styleable.Topbar_topbar_rightText);
        topbar_rightBackground = typedArray.getDrawable(R.styleable.Topbar_topbar_rightBackground);

        topbar_titleTextColor = typedArray.getColor(R.styleable.Topbar_topbar_titleTextColor, 0);
        topbar_titleTextSize = typedArray.getDimension(R.styleable.Topbar_topbar_titleTextSize, 0);
        topbar_title = typedArray.getString(R.styleable.Topbar_topbar_title);

        typedArray.recycle();

        //自定义控件（组合模式）-->形成一个新的控件
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        leftButton.setTextColor(topbar_leftTextColor);
        leftButton.setBackground(topbar_leftBackground);
        leftButton.setText(topbar_leftText);

        rightButton.setTextColor(topbar_rightTextColor);
        rightButton.setBackground(topbar_rightBackground);
        rightButton.setText(topbar_rightText);

        tvTitle.setText(topbar_title);
        tvTitle.setTextSize(topbar_titleTextSize);
        tvTitle.setTextColor(topbar_titleTextColor);
        tvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0xFFF59563);

        //将控件放到ViewGroup中
        //LayoutParams控制 控件以何种方式加入到ViewGroup中
        leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftButton,leftParams);

        rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton,rightParams);

        titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(tvTitle,titleParams);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }
    public void setLeftisVisible(boolean flag){
        if (flag){
            leftButton.setVisibility(VISIBLE);
        }else {
            leftButton.setVisibility(GONE);
        }

    }
}
