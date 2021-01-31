package jp.skypencil.presentation;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import jp.skypencil.infrastructure.onmemory.task.OnMemoryTaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
  @Autowired private MockMvc mockMvc;

  @Autowired private OnMemoryTaskRepository repository;

  @AfterEach
  void clear() {
    repository.clear();
  }

  @Test
  void testCreateTask() throws Exception {
    mockMvc
        .perform(post("/task/").content("subject"))
        .andExpect(matchAll(status().isOk(), jsonPath("$.subject").value("subject")));
  }

  @Test
  void testCreateDuplicatedTask() throws Exception {
    mockMvc.perform(post("/task/").content("subject"));
    mockMvc
        .perform(post("/task/").content("subject"))
        .andExpect(
            matchAll(
                status().isBadRequest(),
                status().reason("Provided subject is already used. Try other subject instead.")));
  }

  @Test
  void testListTask() throws Exception {
    mockMvc.perform(post("/task/").content("subject"));
    mockMvc
        .perform(get("/task"))
        .andExpect(matchAll(status().isOk(), content().json("[{\"subject\":\"subject\"}]")));
  }

  @Test
  void testTaskNotFound() throws Exception {
    UUID id = UUID.randomUUID();
    String message = String.format("No task found with id: %s", id);
    mockMvc
        .perform(get("/task/{taskId}", id))
        .andExpect(matchAll(status().isNotFound(), status().reason(message)));
  }
}
