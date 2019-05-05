package todo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TodoController {

  private final TodoRepository repository;

  TodoController(TodoRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  @GetMapping("/todos")
  List<Todo> all() {
    return repository.findAll();
  }

  @PostMapping("/todo")
  Todo newTodo(@RequestBody Todo newTodo) {
    return repository.save(newTodo);
  }

  // Single item

  @GetMapping("/todo/{id}")
  Todo one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new TodoNotFoundException(id));
  }

  @PutMapping("/todo/{id}")
  Todo replaceEmployee(@RequestBody Todo newTodo, @PathVariable Long id) {

    return repository.findById(id)
      .map(todo -> {
        todo.setName(newTodo.getName());
        todo.setDescription(newTodo.getDescription());
        return repository.save(todo);
      })
      .orElseGet(() -> {
        newTodo.setId(id);
        return repository.save(newTodo);
      });
  }

  @DeleteMapping("/todo/{id}")
  void deleteTodo(@PathVariable Long id) {
    repository.deleteById(id);
  }
}