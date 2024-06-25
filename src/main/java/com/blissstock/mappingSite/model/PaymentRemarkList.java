package com.blissstock.mappingSite.model;
import java.time.LocalDate;
import java.util.Date;

//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class PaymentRemarkList {
    String adminEmail;
    String remarkText;
    Date remarkDate;
    Long paymentRemarkId;
    
    public PaymentRemarkList(String adminEmail, String remarkText, Date remarkDate, Long paymentRemarkId) {
        this.adminEmail = adminEmail;
        this.remarkText = remarkText;
        this.remarkDate = remarkDate;
        this.paymentRemarkId = paymentRemarkId;
        
    }

}
    

