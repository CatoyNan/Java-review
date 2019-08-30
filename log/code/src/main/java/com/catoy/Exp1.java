package com.catoy;

import org.apache.log4j.Logger;

/**
 * @ClassName com.catoy.Exp1
 * @Description TODO
 * @Author admin
 * @Date 2019-08-22 21:55
 * @Version 1.0
 **/
public class Exp1 {
    private static final Logger logger= Logger.getLogger(Exp1.class);

    public static void main(String[] args){
        logger.debug("debug");

        logger.info("info");

        logger.warn("warn");

        logger.error("error");

        logger.fatal("fatal");
    }
}

