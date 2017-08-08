package me.libme.util.fn.file;

import me.libme.kernel._c.async.SimpleScheduledThreadPoolExecutorFactory;
import me.libme.kernel._c.file.FileRemover;
import me.libme.kernel._c.file.FileRemoverCfg;
import me.libme.kernel._c.file.FileTransferCfg;
import me.libme.kernel._c.json.JJSON;
import me.libme.kernel._c.util.CliParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by J on 2017/8/8.
 */
public class MainFileRemover {

    private static final Logger LOGGER= LoggerFactory.getLogger(MainFileRemover.class);


    public static void main(String[] args) {


        // decode the parameter  --fileDskPath


        //decode the parameter --fileKeepTime

        CliParams cliParams=new CliParams(args);

        String fileDskPath= cliParams.getString("--fileDskPath");

        Long fileKeepTime= cliParams.getLong("--fileKeepTime");

        Long period=cliParams.getLong("--period");



        SchedulerCfg schedulerCfg=new SchedulerCfg();
        schedulerCfg.setDaemon(false); // set thread daemon false, so the JVM can not terminate
        schedulerCfg.setFileDskPath(fileDskPath);
        schedulerCfg.setFileKeepTime(fileKeepTime);
        schedulerCfg.setAliveCount(1);
        schedulerCfg.setMaxCount(3);
        schedulerCfg.setAliveTime(60);
        schedulerCfg.setName("file-remover");
        schedulerCfg.setPeriod(period);
        String cfg="----------- scheduler cfg-------- : "+JJSON.get().format(schedulerCfg);
        LOGGER.info(cfg);
        System.out.println(cfg);

        SimpleScheduledThreadPoolExecutorFactory
                factory=new SimpleScheduledThreadPoolExecutorFactory(schedulerCfg);

        SimpleThreadScheduler simpleThreadScheduler=new SimpleThreadScheduler();
        try {
            simpleThreadScheduler.setExecutorService(factory.getObject(),schedulerCfg);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(),e);
        }

        FileTransferCfg fileTransferCfg=new FileTransferCfg();
        fileTransferCfg.setDskPath(schedulerCfg.getFileDskPath());

        FileRemoverCfg fileRemoverCfg=new FileRemoverCfg();
        fileRemoverCfg.setKeepTime(schedulerCfg.getFileKeepTime());

        simpleThreadScheduler.submit(new Runnable() {
            @Override
            public void run() {
                FileRemover fileRemover=new FileRemover(fileTransferCfg,fileRemoverCfg);
                fileRemover.remove();
            }
        });

        System.out.println("---------------- task running....");


    }


}
