package com.simplogics.baseapplication.view;

import java.util.Date;

public interface SalesReportView {
    String getEventName();
    String getStoreName();
    Date getDate();
    int getQuantity();
}
