package edu.android.teamproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 색상이나 선굵기를 선택할 수 있도록 기능 추가
 * 
 * @author Mike
 *
 */
public class GoodPaintBoardActivity extends AppCompatActivity {

	GoodPaintBoard board;
	ImageButton colorBtn;
	ImageButton penBtn;
	ImageButton saveBtn;
	ImageButton undoBtn;

    int mColor = 0xff000000;
	int mSize = 2;
	int oldColor;
	int oldSize;
	boolean eraserSelected = false;
    private Bitmap image_bitmap;
    public static LinearLayout boardLayout;


    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paint);

        Intent data = getIntent();
        Uri imageUri = data.getParcelableExtra("image_uri");

        String realPath = getRealPathFromURI(this, imageUri);


        BitmapDrawable bitmapDrawable = null;
        try {
            bitmapDrawable = new BitmapDrawable(getResources(), new FileInputStream(new File(realPath)));
            Log.i("tag", "drawable: " + bitmapDrawable);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boardLayout = (LinearLayout) findViewById(R.id.boardLayout);

        colorBtn = (ImageButton) findViewById(R.id.colorBtn);
        penBtn = (ImageButton) findViewById(R.id.penBtn);
        saveBtn = (ImageButton) findViewById(R.id.savebtn);
        undoBtn = (ImageButton) findViewById(R.id.undoBtn);



        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.MATCH_PARENT,
        		LinearLayout.LayoutParams.MATCH_PARENT);
        
        board = new GoodPaintBoard(this);
        board.setLayoutParams(params);
        board.setPadding(2, 2, 2, 2);








        boardLayout.addView(board);
        board.setBackground(bitmapDrawable);


        colorBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		
        		ColorPaletteDialog.listener = new OnColorSelectedListener() {
        			public void onColorSelected(int color) {
        				mColor = color;
        				board.updatePaintProperty(mColor, mSize);
        			}
        		};
        		
        		
        		// show color palette dialog
        		Intent intent = new Intent(getApplicationContext(), ColorPaletteDialog.class);
        		startActivity(intent);
        		
        	}
        });
        
        penBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		
        		PenPaletteDialog.listener = new OnPenSelectedListener() {
        			public void onPenSelected(int size) {
        				mSize = size;
        				board.updatePaintProperty(mColor, mSize);
        			}
        		};
        		
        		
        		// show pen palette dialog
        		Intent intent = new Intent(getApplicationContext(), PenPaletteDialog.class);
        		startActivity(intent);
        		
        	}
        });
        
        saveBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {

                board.saveUndo();

        		
        	}
        });
        
        undoBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		
        		board.undo();
        		
        	}
        });
        
    }
    
        
    public int getChosenColor() {
    	return mColor;
    }
    
    public int getPenThickness() {
    	return mSize;
    }

}