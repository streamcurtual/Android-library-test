package com.test.testmvvm.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.test.testmvvm.App;
import com.test.testmvvm.beans.Activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by issuser on 2017/6/7 0007.
 */

public class FileUtil {

    private static final String TAG = File.class.getSimpleName();

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(final String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(final File file) {
        // 如果存在，是目录则返回 true，是文件则返回 false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(final String filePath) {
        return createOrExistsFile(getFileByPath(filePath));
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(final File file) {
        if (file == null) return false;
        // 如果存在，是文件则返回 true，是目录则返回 false
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 读取sd卡中文件内容返回字符串
     *
     * @param filePath
     * @return
     */
    public static String getFileToString(String filePath) {
        FileInputStream inputStream = null;
        String data = "";
        try {
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            data = inputStream2String(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public static String inputStream2String(InputStream in) throws IOException {

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        int n;
        while ((n = in.read(b)) != -1) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    /**
     * 写文件
     *
     * @param file
     * @param data
     */
    public static void saveStringToFile(File file, String data) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getFaceDatas(String dir) {
        File dirFile = new File(dir);
        File[] files = dirFile.listFiles();
        JSONArray array = new JSONArray();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (!file.getAbsolutePath().equals(Environment.getExternalStorageDirectory().getPath() + "pingan/record/2019-03-12/.DS_Store")) {
                    String faceData = getFileToString(file.getAbsolutePath());
                    array.add(JSON.parse(faceData));
                }
            }
        }
        Log.d("", "getFaceDatas: " + array.toJSONString());
        return array.toJSONString();
    }

    /**
     * 播放音频
     *
     * @param path 音符文件
     */
    public static void playMedia(final String path) {
        try {
            Log.d("", "playMedia=" + path);
            final MediaPlayer player = new MediaPlayer();
            player.setDataSource(path);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Log.i("", "complete media" + path);
                    player.release();
                }
            });
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放音频
     *
     * @param assetMusic 音符文件
     */
    public static void playAssetMusic(final String assetMusic) {
        try {
            Log.d("", "playMedia=" + assetMusic);
            final MediaPlayer player = new MediaPlayer();
            AssetFileDescriptor fd = App.getContext().getAssets().openFd(assetMusic);
            player.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Log.i("", "complete media" + assetMusic);
                    player.release();
                }
            });
            player.prepareAsync();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public static void deleteFile(File file) {

        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }

    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static int[] randomCommon(int min, int max, int n, List<Integer> current) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j] || current.contains(num)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    public static int[] randomCommon(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    /**
     * 获取指定路径下的所有文件
     *
     * @param path 路径
     */
    public static File[] getFiles(String path) {
        File videoPath = new File(path);
        return videoPath.listFiles();
    }

    /**
     * 获取指定路径下的所有文件符合规则的文件名
     *
     * @param regex 路径
     */
    public static ArrayList<String> getFile(String path, String regex) {
        File[] files = getFiles(path);
        ArrayList<String> list = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.getName().endsWith(regex)) {
                    list.add(file.getAbsolutePath());
                    Log.d(file.getName(), "====");
                }
            }
        }
        images = list;
        images.add(0, images.get(images.size() - 1));
        images.add(images.size(), images.get(1));
        getActivities();
        getImgs();
        return list;
    }

    public static ArrayList<String> images = new ArrayList<>();

    public static String getFilePath(String name, String path) {
        File videoPath = new File(path);
        File[] files = videoPath.listFiles();
        String names = "";
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.getName().startsWith(name)) {
                    names = file.getAbsolutePath();
                    break;
                }
            }
        }
        Log.i(TAG, "getFilePath: " + names);
        return names;
    }

    public static HashMap<String, Bitmap> imgs = new HashMap<>();

    public static boolean LOAD_FINISH = false;

    public static void getImgs() {
        if (images.size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Activities activity : activities) {
                        imgs.put(activity.getImg(), ImageUtils.getLoacalBitmap(activity.getImg()));
                    }
                    LOAD_FINISH = true;
                }
            }).start();
        }
    }

    public static ArrayList<Activities> activities;

    public static final String TEXT_PATH = "/sdcard/mibox/excel";

    public static void getActivities() {
        activities = ReadExcel.readExcel(FileUtil.getFilePath("test", TEXT_PATH));
        if (activities != null) {
            getImages();
        }
    }

    public static ArrayList<String> getImages() {
        ArrayList<String> list = new ArrayList<>();
        if (activities != null) {
            for (Activities activities : activities) {
                imgs.put(activities.getImg(), ImageUtils.getLoacalBitmap(activities.getImg()));
                images.add(activities.getImg());
            }
        }
        return list;
    }

    /**
     * 将asset文件写入缓存
     */

    public static String copyAssetAndWrite(Context context,String fileName) {

        try {

            File cacheDir = context.getCacheDir();

            if (!cacheDir.exists()) {

                cacheDir.mkdirs();

            }

            File outFile = new File(cacheDir, fileName);

            if (!outFile.exists()) {

                boolean res = outFile.createNewFile();

                if (!res) {

                    return "createNewFile failed";

                }

            }

            InputStream is = context.getAssets().open(fileName);

            FileOutputStream fos = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];

            int byteCount;

            while ((byteCount = is.read(buffer)) != -1) {

                fos.write(buffer, 0, byteCount);

            }

            fos.flush();

            is.close();

            fos.close();

            return outFile.getPath();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return "IOException";

    }


}
