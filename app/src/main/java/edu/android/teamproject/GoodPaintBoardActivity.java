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
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 색상이나 선굵기를 선택할 수 있도록 기능 추가
 * 
 * @author Mike
 *
 */
public class GoodPaintBoardActivity extends AppCompatActivity {

    private static final String TAG ="edu.android" ;
    GoodPaintBoard board;
	ImageButton colorBtn;
	ImageButton penBtn;
	ImageButton saveBtn;


    int mColor = 0xff000000;
	int mSize = 2;
	int oldColor;
	int oldSize;
	boolean eraserSelected = false;
    private Bitmap image_bitmap;
    public static LinearLayout boardLayout;
    private Uri imageUri;

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

        final Intent data = getIntent();

        boardLayout = (LinearLayout) findViewById(R.id.boardLayout);

        colorBtn = (ImageButton) findViewById(R.id.colorBtn);
        penBtn = (ImageButton) findViewById(R.id.penBtn);
        saveBtn = (ImageButton) findViewById(R.id.savebtn);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
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

        board = new GoodPaintBoard(this);
        board.setLayoutParams(params);
        board.setPadding(2, 2, 2, 2);
        board.setBackground(bitmapDrawable);
        boardLayout.addView(board);

        image_bitmap =  drawableToBitmap(bitmapDrawable);
        Log.i("TAG","image_bitmap"+image_bitmap);







        colorBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		
        		ColorPaletteDialog.listener = new OnColorSelectedListener() {
        			public void onColorSelected(int color) {
        				mColor = color;
        				board.updatePaintProperty(mColor, mSize);
                        Log.i(TAG ,"image_bitmap"+image_bitmap);
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
                    Toast.makeText(GoodPaintBoardActivity.this, fileName + " 저장되었습니다.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.putExtra("image", fileName);
                    setResult(RESULT_OK,intent);
                    finish();
                } catch (Exception e) {
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