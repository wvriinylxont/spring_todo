package com.example.demo3.todo;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
public class Todo {
  private int tno;
  private String title;
  private final LocalDate regDate = LocalDate.now();
  @Setter
  private boolean finish = false;
  public Todo(int tno, String title) {
    this.tno = tno;
    this.title = title;
  }
}
