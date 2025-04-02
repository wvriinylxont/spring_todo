package com.example.demo3.supply;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

// 사용자로 부터 값을 입력받는 객체 : Command 객체
// 스프링에서 커맨드 객체는 반드시 기본생성자와 세터가 있어야한다
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// 비품번호, 입고일, 이름, 개수
public class Supply {
  private Integer sno;
  private String name;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate regDate;
  private  Integer quantity;
}
