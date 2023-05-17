package io.github.springbootio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BytesConvert {

    static {
        String tmpdir = System.getProperty("java.io.tmpdir");
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        String name = "libbytesconvert";
        String os_name = System.getProperty("os.name").toLowerCase();
        String suffix = ".so";
        if (os_name.indexOf("mac") > -1) {
            suffix = ".dylib";
        } else if (os_name.indexOf("windows") > -1) {
            suffix = ".dll";
        }
        try(InputStream in = cl.getResourceAsStream(name + suffix);
            FileOutputStream out = new FileOutputStream(tmpdir + "/" + name + suffix)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.load(tmpdir + "/" + name + suffix);
    }

    public native byte[] convert(byte[] bytes, int mode);

    public native byte[] convert2(byte[] bytes);

}
