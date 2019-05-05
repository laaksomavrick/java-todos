package todo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Lombok annotation to create all getters, setters, equals, hash, toString
@Data
// JPA; magic for storage
@Entity
class Todo {

  private @Id @GeneratedValue Long id;
  private String name;
  private String description;

  Todo() {}

  Todo(String name, String description) {
    this.name = name;
    this.description = description;
  }
}