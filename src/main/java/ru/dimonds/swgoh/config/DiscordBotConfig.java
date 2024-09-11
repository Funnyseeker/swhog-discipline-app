package ru.dimonds.swgoh.config;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

//@Configuration
@Slf4j
public class DiscordBotConfig {

    @Value("${decimator.token}")
    private String token;

    @Bean(destroyMethod = "shutdownNow")
    public JDA discordBot(@Autowired MyListener listener) {
        JDA api = JDABuilder.createDefault(token)
                            .addEventListeners(listener)
                            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                            .setEventPool(Executors.newVirtualThreadPerTaskExecutor())
                            .build();

        try {
            api.awaitReady();
        } catch (InterruptedException e) {
            log.error("wait bot ready error: ", e);
        }

        return api;
    }

}
