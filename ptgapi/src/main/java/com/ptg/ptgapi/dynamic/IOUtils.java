package com.ptg.ptgapi.dynamic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {

    public static long copy(InputStream from, OutputStream to, long length, int buffSize) throws IOException {
        if (buffSize <= 0) {
            buffSize = 8192;
        }
        if (length <= 0) {
            length = Long.MAX_VALUE;
        }
        byte[] buf = new byte[buffSize];
        long total = 0;
        int r;
        for (long left = length; left > 0; left -= (long) r) {
            if (((long) buffSize) <= left) {
                r = from.read(buf);
            } else {
                r = from.read(buf, 0, (int) left);
            }
            if (r == -1) {
                break;
            }
            to.write(buf, 0, r);
            total += (long) r;
        }
        return total;
    }

    public static long copy(InputStream from, OutputStream to) throws IOException {
        return copy(from, to, -1, 8192);
    }
}
