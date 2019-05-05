package todo;

import org.springframework.data.jpa.repository.JpaRepository;

// Extending the JpaRepository gives us CRUD methods for free
interface TodoRepository extends JpaRepository<Todo, Long> {

}