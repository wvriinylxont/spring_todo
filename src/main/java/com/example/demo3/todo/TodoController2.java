package com.example.demo3.todo;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class TodoController2 {
  private List<Todo> todos = new ArrayList<>();
  private int tno=1;
  
  // 같은 주소로 만들면 주소가 죽는다
  public ModelAndView list() {
    return new ModelAndView("todo/list").addObject("todos", todos);
  }

  public ModelAndView write(@RequestParam String title){
    Todo newTodo = new Todo(tno++, title);
    todos.add(newTodo);
    return new ModelAndView("redirect:/todo/list");
  }

  public ModelAndView finish(@RequestParam int tno) {
    for(Todo todo:todos) {
      if(todo.getTno()==tno) {
        todo.setFinish(true);
        break;
      }
    }
    return new ModelAndView("redirect:/todo/list");
  }

  public ModelAndView delete(@RequestParam int tno) {
    for(int i=todos.size()-1; i>=0; i--) {
      if(todos.get(i).getTno()==tno) {
        todos.remove(i);
        break;
      }
    }
    return new ModelAndView("redirect:/todo/list");
  }
}
