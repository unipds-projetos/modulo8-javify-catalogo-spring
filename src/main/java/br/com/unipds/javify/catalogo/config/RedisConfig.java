package br.com.unipds.javify.catalogo.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import tools.jackson.databind.jsontype.PolymorphicTypeValidator;
import tools.jackson.databind.cfg.DateTimeFeature;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {

        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util")
                .allowIfSubType("org.springframework.data.domain")
                .allowIfSubType("br.com.unipds.javify.catalogo.domain")
                .build();

        ObjectMapper objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
                .activateDefaultTyping(ptv, DefaultTyping.NON_FINAL_AND_RECORDS)
                .addMixIn(PageImpl.class, PageImplMixIn.class)
                .build();

        GenericJacksonJsonRedisSerializer serializer = new GenericJacksonJsonRedisSerializer(objectMapper);

        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(serializer)
                );
    }
}

@JsonIgnoreProperties(ignoreUnknown = true, value = {"pageable", "sort"})
class CustomPageImpl<T> extends PageImpl<T> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomPageImpl(
            @JsonProperty("content") List<T> content,
            @JsonProperty("number") int number,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") Long totalElements) {

        super(content == null ? new ArrayList<>() : content,
                size > 0 ? PageRequest.of(number, size) : PageRequest.of(0, 1),
                totalElements == null ? 0 : totalElements);
    }
}


@JsonDeserialize(as = CustomPageImpl.class)
interface PageImplMixIn {
}