package com.blissstock.mappingSite.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_mst")
public class BankInfo {

  @Column(name = "bank_id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long bankId;

  @Column(name = "bank_name", length = 255)
  //@NotBlank(message="Please fill payment service")
  private String bankName;

}
