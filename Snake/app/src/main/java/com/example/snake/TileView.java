package com.example.snake;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class TileView extends View {

    protected static int tileSize;

    protected static int xTileCount;
    protected static int yTileCount;

    private static int xOffset;
    private static int yOffset;


    // tile 을 그릴 array
    private Bitmap[] tileArray;


    // 각 위치의 인덱스를 저장할 이차원 배열
    private int[][] tileGrid;

    private final Paint tilePaint = new Paint();

    // getAttrs : attrs.xml 에 선언한 attribute 를 View 에 설정
    public TileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    // getAttrs
    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    // initView : xml 파일 할당, 각각의 view 설정
    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TileView);
        tileSize = a.getInt(R.styleable.TileView_tileSize, 24);
        a.recycle();
    }


    // Bitmap 으로 초기 리스트 리셋
    public void resetTiles(int tilecount) {
        tileArray = new Bitmap[tilecount];
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        xTileCount = (int) Math.floor(w / tileSize);
        yTileCount = (int) Math.floor(h / tileSize);

        xOffset = ((w - (tileSize * xTileCount)) / 2);
        yOffset = ((h - (tileSize * yTileCount)) / 2);

        tileGrid = new int[xTileCount][yTileCount];
        clearTiles();
    }


    // Tile 그리기
    public void loadTile(int key, Drawable tile) {
        Bitmap bitmap = Bitmap.createBitmap(tileSize, tileSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, tileSize, tileSize);
        tile.draw(canvas);

        tileArray[key] = bitmap;
    }

    // Tile 초기화
    public void clearTiles() {
        for (int x = 0; x < xTileCount; x++) {
            for (int y = 0; y < yTileCount; y++) {
                setTile(0, x, y);
            }
        }
    }


    // 특정 타일 표시
    public void setTile(int tileindex, int x, int y) {
        tileGrid[x][y] = tileindex;
    }


    // canvas : 도화지, paint : 색연필
    // 위에서 타일을 표시했기 때문에 이를 칠할 수 있음
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = 0; x < xTileCount; x += 1) {
            for (int y = 0; y < yTileCount; y += 1) {
                if (tileGrid[x][y] > 0) {
                    canvas.drawBitmap(tileArray[tileGrid[x][y]],
                            xOffset + x * tileSize,
                            yOffset + y * tileSize,
                            tilePaint);
                }
            }
        }

    }

}
