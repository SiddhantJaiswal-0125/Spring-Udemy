package net.javaguides.springboot.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponse {
    private  String orderTrackingNumber;
   private  String status;
   private String message;
}
