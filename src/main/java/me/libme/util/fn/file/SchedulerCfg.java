package me.libme.util.fn.file;

import me.libme.kernel._c.async.ThreadConfig;

/**
 * Created by J on 2017/8/8.
 */
public class SchedulerCfg extends ThreadConfig {

    private String fileDskPath;

    /**
     * seconds
     */
    private long fileKeepTime;

    /**
     * seconds
     */
    private long period;

    public String getFileDskPath() {
        return fileDskPath;
    }

    public void setFileDskPath(String fileDskPath) {
        this.fileDskPath = fileDskPath;
    }

    /**
     * seconds
     * @return seconds
     */
    public long getFileKeepTime() {
        return fileKeepTime;
    }

    /**
     * seconds
     * @param fileKeepTime seconds
     */
    public void setFileKeepTime(long fileKeepTime) {
        this.fileKeepTime = fileKeepTime;
    }


    /**
     *
     * @return seconds
     */
    public long getPeriod() {
        return period;
    }

    /**
     *
     * @param period seconds
     */
    public void setPeriod(long period) {
        this.period = period;
    }
}
