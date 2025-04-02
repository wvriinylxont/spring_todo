package com.example.demo3.supply;

import com.example.demo3.todo.*;
import jakarta.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.time.*;
import java.util.*;

// 비품 목록을 출력하는 메소드와 html을 작성
// html에는 번호, 제품명, 입고일, 개수, 증가버튼, 감소버튼

@Controller
public class SupplyController {
  private List<Supply> supplies = new ArrayList<>();
  private int sno = 4;

  @PostConstruct
  public void init() {
    supplies.add(new Supply(1, "펩시콜라", LocalDate.of(2025,3,25),4));
    supplies.add(new Supply(2, "막걸리", LocalDate.of(2025,3,26),3));
    supplies.add(new Supply(3, "사이다", LocalDate.of(2025,3,26),2));
  }

  @GetMapping("/supply/list")
  public ModelAndView list() {
    return new ModelAndView("supply/list").addObject("supplies", supplies);
  }

  // @ModelAttribute : 사용자 입력을 담은 객체를 생성
  // Supply 객체로 사용자 입력을 받아오는데.. 모든 값을 다 입력하는건 아니다 -> 입력하지 않은 필드는 null이 들어간다(int->Integer..)
  // @ModelAttribute는 기본 생성자로 객체를 생성한 다음, setter로 값을 집어넣는다
  @PostMapping("/supply/add")
  public ModelAndView add(@ModelAttribute Supply supply) {
    supply.setSno(sno++);
    supplies.add(supply);
    return new ModelAndView("redirect:/supply/list");
  }

  @PostMapping("/supply/plus")
  public ModelAndView plus(@RequestParam int sno) {
    for(int i=0; i < supplies.size(); i++) {
      if(supplies.get(i).getSno()==sno) {
        supplies.get(i).setQuantity(supplies.get(i).getQuantity() + 1);
        break;
      }
    }
    return new ModelAndView("redirect:/supply/list");
  }

  @PostMapping("/supply/down")
  public ModelAndView down(@RequestParam int sno) {
    for(int i= supplies.size()-1; i>=0; i--) {
      if(supplies.get(i).getSno()==sno) {
        if (supplies.get(i).getQuantity() > 0) {
          supplies.get(i).setQuantity(supplies.get(i).getQuantity() - 1);
        }
        break;
      }
    }
    return new ModelAndView("redirect:/supply/list");
  }

  @PostMapping("/supply/delete")
  public ModelAndView delete(@RequestParam int sno) {
    for(int i=supplies.size()-1; i>=0; i--) {
      if(supplies.get(i).getSno()==sno) {
        System.out.println(sno + " " + i);
        supplies.remove(i);
        break;
      }
    }
    return new ModelAndView("redirect:/supply/list");
  }
}
