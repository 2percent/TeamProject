package edu.android.teamproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 색상이나 선굵기를 선택할 수 있도록 기능 추가
 * 
 * @author Mike
 *
 */
public class GoodPaintBoardActivity2 extends AppCompatActivity {
	
	GoodPaintBoard2 board;
	ImageButton colorBtn;
    ImageButton penBtn;
    ImageButton saveBtn;

    public static LinearLayout boardLayout;
	int mColor = 0xff000000;
	int mSize = 2;
	int oldColor;
	int oldSize;
	boolean eraserSelected = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paint);
        
        boardLayout = (LinearLayout) findViewById(R.id.boardLayout);
        colorBtn = (ImageButton) findViewById(R.id.imagebtn_paint_select_color);
        penBtn = (ImageButton) findViewById(R.id.imagebtn_paint_select_brush);
        saveBtn = (ImageButton) findViewById(R.id.imagebtn_paint_select_save);

        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.MATCH_PARENT,
        		LinearLayout.LayoutParams.MATCH_PARENT);
        
        board = new GoodPaintBoard2(this);
        board.setLayoutParams(params);
        board.setPadding(2, 2, 2, 2);
        
        boardLayout.addView(board);
        
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
                boardLayout.buildDrawingCache();
                Bitmap bitmapfinal = boardLayout.getDrawingCache();

                try {
                    String fileName = "/sdcard/DCIM/Camera" + System.currentTimeMillis() + ".jpg";
                    FileOutputStream out = new FileOutputStream(fileName);
                    bitmapfinal.compress(Bitmap.CompressFormat.JPEG,100,out);
                    Toast.makeText(GoodPaintBoardActivity2.this, fileName + " 저장되었습니다.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.putExtra("image", fileName);
                    setResult(RESULT_OK,intent);
                    finish();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }




        		
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