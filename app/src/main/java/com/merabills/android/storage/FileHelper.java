package com.merabills.android.storage;

import android.content.Context;
import android.util.Log;
import java.io.*;

public class FileHelper {
    private static final String FILE_NAME = "LastPayment.txt";

    public static void writeToFile(Context context, String data) {
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write(data.getBytes());
        } catch (IOException e) {
            Log.e("FileHelper", "Error writing to file", e);
        }
    }

    public static String readFromFile(Context context) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            Log.e("FileHelper", "Error reading from file", e);
        }
        return sb.toString();
    }
}
