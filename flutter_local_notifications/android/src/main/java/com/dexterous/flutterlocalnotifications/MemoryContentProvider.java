package com.dexterous.flutterlocalnotifications;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MemoryContentProvider extends ContentProvider {

    // 전역적으로 관리될 Bitmap 저장소 (싱글톤 역할)
    private static final Map<String, byte[]> memoryDataStore = new HashMap<>();

    // Bitmap을 메모리에 저장하는 메서드
    public static void storeBitmapInMemory(String key, Bitmap bitmap) {
        byte[] byteArray = bitmapToByteArray(bitmap);
        memoryDataStore.put(key, byteArray);
    }

    // 메모리에서 Bitmap 데이터를 가져오는 메서드
    public static byte[] getBitmapFromMemory(String key) {
        return memoryDataStore.get(key);
    }

    // Bitmap을 ByteArray로 변환하는 메서드
    private static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    public boolean onCreate() {
        // ContentProvider가 처음 생성될 때의 초기화 작업
        return true;
    }

    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
        Log.d("caz tst","open Asset! palz1111");
        String key = uri.getLastPathSegment();
        byte[] data = memoryDataStore.get(key);

        if (data == null) {
            return null;
        }

        // ByteArray를 임시 파일로 저장하고 AssetFileDescriptor로 반환
        try {
            File tempFile = new File(getContext().getCacheDir(), "temp_image.png");
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(data);
            fos.close();

            return new AssetFileDescriptor(
                    ParcelFileDescriptor.open(tempFile, ParcelFileDescriptor.MODE_READ_ONLY),
                    0, tempFile.length()
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 기타 필요한 메서드들: insert, update, delete 등
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }
}