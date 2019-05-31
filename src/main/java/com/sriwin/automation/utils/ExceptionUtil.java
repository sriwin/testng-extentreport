package com.sriwin.automation.utils;

import com.sriwin.automation.exceptions.CoreException;
import org.apache.log4j.Logger;

public class ExceptionUtil {
    private static final Logger LOGGER = Logger.getLogger(ExceptionUtil.class);

    public static CoreException handleCoreException(Throwable ex) {
        if (!(ex instanceof CoreException)) {
            return new CoreException(ex);
        }
        return (CoreException) ex;
    }

    public static CoreException handleCoreException(String errorMsg, Throwable ex) {
        if (!(ex instanceof CoreException)) {
            return new CoreException(errorMsg, ex);
        }
        return (CoreException) ex;
    }

    public static CoreException handleCoreException(Throwable ex, String errorMsg) {
        if (!(ex instanceof CoreException)) {
            return new CoreException(errorMsg, ex);
        }
        return (CoreException) ex;
    }
}