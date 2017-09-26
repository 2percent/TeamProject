package edu.android.teamproject;

import android.annotation.TargetApi;
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
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
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
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paint);

        Intent data = getIntent();
        Uri imageUri = data.getParcelableExtra("image_uri");
//        Bitmap bitmap = data.getParcelableExtra("image");
//        Log.i("TAG","image_bitmap"+bitmap);

//        Log.i("tag", "uri: " + imageUri);
//        Log.i("tag", "path: " + imageUri.getPath());
        String realPath = getRealPathFromURI(this, imageUri);
//        Log.i("tag", "real path: " + realPath);

        BitmapDrawable bitmapDrawable = null;
        try {
            bitmapDrawable = new BitmapDrawable(getResources(), new FileInputStream(new File(realPath)));
            Log.i("tag", "drawable: " + bitmapDrawable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boardLayout = (LinearLayout) findViewById(R.id.boardLayout);

        colorBtn = (ImageButton) findViewById(R.id.colorBtn);
        penBtn = (ImageButton) findViewById(R.id.penBtn);
        saveBtn = (ImageButton) findViewById(R.id.savebtn);
        undoBtn = (ImageButton) findViewById(R.id.undoBtn);


        Bitmap bitmap = drawableToBitmap(bitmapDrawable);
        resizeBitmapImage(bitmap,1920);




        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.MATCH_PARENT,
        		LinearLayout.LayoutParams.MATCH_PARENT);
        
        board = new GoodPaintBoard(this);
        board.setLayoutParams(params);
        board.setPadding(2, 2, 2, 2);
//        Drawable drawable = (Drawable)new BitmapDrawable(image_bitmap);
//        board.picture(bitmapDrawable);
//        Log.i("TAG" , "image_bitmap" + image_bitmap);







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
    public Bitmap resizeBitmapImage(Bitmap source, int maxResolution)
    {
        int width = source.getWidth();
        int height = source.getHeight();
        int newWidth = width;
        int newHeight = height;
        float rate = 0.0f;

        if(width > height)
        {
            if(maxResolution < width)
            {
                rate = maxResolution / (float) width;
                newHeight = (int) (height * rate);
                newWidth = maxResolution;
            }
        }
        else
        {
            if(maxResolution < height)
            {
                rate = maxResolution / (float) height;
                newWidth = (int) (width * rate);
                newHeight = maxResolution;
            }
        }

        return Bitmap.createScaledBitmap(source, newWidth, newHeight, true);
    }


    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}