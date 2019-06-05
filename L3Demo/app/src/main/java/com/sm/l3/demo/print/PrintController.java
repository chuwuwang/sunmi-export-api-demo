package com.sm.l3.demo.print;

import com.sm.l3.demo.socket.TransferExtra;

public class PrintController {

    public PrintController() {

    }

    public void print(TransferExtra.Bean payDetail, BasePrintCallback callback, int pageNum, boolean isAgain) throws Exception {
        int transPlatform = payDetail.transactionPlatform;
        switch (transPlatform) {
            case 0:
                PrintCardTicket printCardTicket = new PrintCardTicket();
                printCardTicket.printCardTicket(payDetail, callback, pageNum, isAgain);
                break;
            default:
                PrintCodeTicket printCodeTicket = new PrintCodeTicket();
                printCodeTicket.printCodeTicket(payDetail, isAgain, pageNum, callback);
                break;
        }
    }


}
