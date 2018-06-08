package io.zup.springframework.kafka.ui.integrated

import io.zup.springframework.kafka.ui.KafkaDLQUITestApplication
import io.zup.springframework.kafka.ui.helper.TestConstants
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.config.KafkaListenerEndpointRegistry
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.rule.KafkaEmbedded
import org.springframework.kafka.test.utils.ContainerTestUtils
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest(
    classes = [KafkaDLQUITestApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@DirtiesContext
class KafkaDLQIntegratedTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Value("\${spring.kafka.dlq-ui.path}")
    lateinit var path: String

    @Test
    fun `should redirect to index page`() {
        mockMvc.perform(MockMvcRequestBuilders.get(path))
            .andExpect(status().isFound)
    }

    @Test
    fun `should get index page`() {
        mockMvc.perform(MockMvcRequestBuilders.get("$path/index.html"))
            .andExpect(status().isOk)
    }
}