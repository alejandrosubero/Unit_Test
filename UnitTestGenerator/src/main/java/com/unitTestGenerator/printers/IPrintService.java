package com.unitTestGenerator.printers;

import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.menus.AnalizerMenu;

public interface IPrintService {

    default PrintService service(){
       return ContextIOC.getInstance().getClassInstance(PrintService.class);
    }

}
