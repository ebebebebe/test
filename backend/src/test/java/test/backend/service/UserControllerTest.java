package test.backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import test.backend.controller.UserController;
import test.backend.exception.EmailAlreadyInUsedException;
import test.backend.model.entity.UserEntity;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUser() throws Exception {
        String body = """
                {
                    "email": "test@test.tes",
                    "passwordMd5": "1234567890"
                }
                """;

        when(userService.createUser(any())).thenReturn(getUserEntity());
        mockMvc.perform(post("/api/user").content(body).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", notNullValue()))
                .andExpect(jsonPath("$.email", notNullValue()))
                .andExpect(jsonPath("$.active", notNullValue()))
                .andExpect(jsonPath("$.length()", is(3)));

    }

    @Test
    public void createUserNullBody() throws Exception {
        mockMvc.perform(post("/api/user").contentType("application/json"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getUserList() throws Exception {
        when(userService.findAll()).thenReturn(Arrays.asList(getUserEntity(), getUserEntity()));
        mockMvc.perform(get("/api/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid", notNullValue()))
                .andExpect(jsonPath("$[0].email", notNullValue()))
                .andExpect(jsonPath("$[0].active", notNullValue()))
                .andExpect(jsonPath("$[0].*", hasSize(3)))
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    public void emailCheckOk() throws Exception {
        String body = """
                {
                    "email": "test2@test.tes"
                }
                """;
        mockMvc.perform(post("/api/user/email-check").content(body).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void emailCheckFail() throws Exception {
        String body = """
                {
                    "email": "test2@test.tes"
                }
                """;
        doThrow(new EmailAlreadyInUsedException("Email already in used")).when(userService).checkEmail(any());
        mockMvc.perform(post("/api/user/email-check").content(body).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", is("Email already in used")));
    }

    // TODO: Do unit test for remaining controller endpoints

    private UserEntity getUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setUuid(UUID.randomUUID());
        entity.setActive(true);
        entity.setEmail("test@test.tes");
        entity.setPasswordMd5("1234567890");
        return entity;
    }
}
