package aloogle.pokedex.other;

import android.util.Log;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipHelper {
    private String zipFileLocation;
    private String unzipTarget;

    public ZipHelper(String zipFileLocation, String unzipTarget) {
        this.zipFileLocation = zipFileLocation;
        this.unzipTarget = unzipTarget;
        dirChecker("");
    }

    public String ZipFileLocation() {
        return zipFileLocation;
    }

    public void unzip(int bufferSize) {
        try {
            FileInputStream fis = new FileInputStream(zipFileLocation);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry = null;
            int size;
            byte[] buffer = new byte[bufferSize];

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    File f = new File(unzipTarget + entry.getName());
                    if(!f.isDirectory()) f.mkdirs();

                    continue;
                }
                FileOutputStream fos = new FileOutputStream(unzipTarget + entry.getName());
                BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

                while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
                    fos.write(buffer, 0, size);
                }

                bos.flush();
                bos.close();
            }
            zis.close();
            fis.close();
        }
        catch (Exception e) {Log.e("DECOMPRESS", "UNZIP", e);}

    }

    private void dirChecker(String dir) {
        File f = new File(unzipTarget + dir);

        if(!f.isDirectory()) {
            f.mkdirs();
        }
    }
}