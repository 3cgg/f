package me.libme.util.fn.file;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by J on 2017/8/8.
 */
public class SimpleThreadScheduler implements TaskSubmitter {


    private ScheduledExecutorService executorService;

    private SchedulerCfg schedulerCfg;

    public void setExecutorService(ScheduledExecutorService executorService ,SchedulerCfg schedulerCfg) {
        this.executorService = executorService;
        this.schedulerCfg=schedulerCfg;
    }

    private long getPeriod(){
        long period=this.schedulerCfg.getPeriod();
        if(period<=0){
            period=5;
        }
        return period;
    }


    @Override
    public void submit(Runnable runnable) {
        executorService.scheduleAtFixedRate(runnable,3,getPeriod(), TimeUnit.SECONDS);
    }


}
