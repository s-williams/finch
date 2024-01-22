package io.swilliams.finch.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Ignore

@SpringBootTest
@AutoConfigureMockMvc
class MockMvcTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Call to get entries fails`() {
        val result = mockMvc
            .perform(get("/entries"))
            .andExpect(status().isBadRequest)
            .andReturn()
    }

    @Test
    @Ignore
    fun `Call to post entries succeeds`() {
        val result = mockMvc
            .perform(post("/entries").contentType("text/plain").content("12300000-0000-0000-0000-000000000000|1X1A11|John Doe|Likes Avocado|Cycles|8.2|16.6"))
            .andExpect(status().isOk)
            .andReturn()
    }
}
