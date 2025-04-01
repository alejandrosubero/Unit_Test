package com.unitTestGenerator.printers;

import com.unitTestGenerator.ioc.ContextIOC;

public interface IPrintService {

    default PrintService service(){
       return ContextIOC.getInstance().getClassInstance(PrintService.class);
    }

}
