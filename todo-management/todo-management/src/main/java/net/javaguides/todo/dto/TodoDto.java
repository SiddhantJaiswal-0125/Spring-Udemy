package net.javaguides.todo.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TodoDto {

    private Long id;
    private String title;
    private  String description;
    private Boolean completed;

}
