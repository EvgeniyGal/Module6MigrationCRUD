package edu.goit.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Client {
    private long ID;
    private String name;
}
