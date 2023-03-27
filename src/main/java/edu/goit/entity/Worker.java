package edu.goit.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@ToString
@Data
public class Worker {
    private int ID;
    private String name;
    private LocalDate birthday;
    private String level;
    private int salary;

}
