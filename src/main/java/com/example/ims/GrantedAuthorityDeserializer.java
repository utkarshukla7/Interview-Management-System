package com.example.ims;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class GrantedAuthorityDeserializer extends JsonDeserializer<Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (node.isArray() && node.size() > 0) {
            JsonNode authorityNode = node.get(0);
            if (authorityNode.has("authority")) {
                String authority = authorityNode.get("authority").asText();
                return Collections.singleton(new SimpleGrantedAuthority(authority));
            }
        }
        return Collections.emptySet();
    }
}