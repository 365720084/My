package com.fancy.showmedata.utils.log;

import org.mym.plog.printer.FilePrinter;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * User: Smile(lijianhy1990@gmail.com)
 * Date: 2017-03-10
 * Time: 17:46
 */
public class LogFileNameGenerator implements FilePrinter.FileNameGenerator {

    private static final String formatStr = "yyyyMMdd-HHmm";
    private static final String FORMAT_REGEX = "\\d{8}\\-\\d{4}";
    private static final String CONCAT = "-";
    private SimpleDateFormat timingFormat = new SimpleDateFormat(formatStr, Locale.getDefault());

    @Override
    public String nextFile() {
        return timingFormat.format(System.currentTimeMillis()) + ".log";
    }

    @Override
    public String nextFileIfDuplicate(File dir, String timestamp) {
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(FORMAT_REGEX + CONCAT + "\\d+" + "\\.log");
            }
        });
        if (files == null || files.length == 0) {
//                throw new RuntimeException("File name not valid but no such file exists.");
            return formatNameWithSerialNum(timestamp, 1);
        }

        //Find last file and
        long lastModifiedTime = 0;
        File lastModifiedFile = null;
        for (File file : files) {
            if (lastModifiedFile == null || file.lastModified() > lastModifiedTime) {
                lastModifiedTime = file.lastModified();
                lastModifiedFile = file;
            }
        }
        //Must be not null
        //noinspection ConstantConditions
        String last = lastModifiedFile.getName();
        //Because we used FilenameFilter, so can be sure that suffix must be a number.
        int serialNum = Integer.parseInt(last.substring((formatStr + CONCAT).length()));
        return formatNameWithSerialNum(timestamp, serialNum + 1);
    }

    private String formatNameWithSerialNum(String timestamp, int serialNum) {
        return timestamp + CONCAT + serialNum + ".log";
    }
}
