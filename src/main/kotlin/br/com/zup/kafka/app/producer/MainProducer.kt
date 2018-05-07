package br.com.zup.kafka.app.producer

import br.com.zup.kafka.framework.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class MainProducer(val template: KafkaTemplate<String, Message<String>>) {

    companion object {
        const val TOPIC = "main_topic"
    }

    fun send(message: Message<String>) {
        template.send(TOPIC, message)
    }

}