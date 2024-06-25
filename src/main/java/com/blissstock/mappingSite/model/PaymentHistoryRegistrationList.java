package com.blissstock.mappingSite.model;
import java.time.LocalDate;
import java.util.Date;

//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistoryRegistrationList {
    String teacherName;
    String courseName;
    Date paymentFrom;
    Date paymentTo;
    int payAmount;
    LocalDate paymentDate;
    
    public PaymentHistoryRegistrationList(String teacherName, String courseName, Date paymentFrom, Date paymentTo, int payAmount, LocalDate paymentDate) {
        this.teacherName = teacherName;
        this.courseName = courseName;
        this.paymentFrom = paymentFrom;
        this.paymentTo = paymentTo;
        this.payAmount = payAmount;
        this.paymentDate = paymentDate;
    }

}
    

