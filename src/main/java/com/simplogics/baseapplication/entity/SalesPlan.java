package com.simplogics.baseapplication.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales_plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesPlan {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "evsm_id")
    private long esId;
    @Column(name = "date")
    private Date date;
    @Column(name = "quantity")
    private int quantity;
}

//TODO Make unique constraint for Date and ESID
