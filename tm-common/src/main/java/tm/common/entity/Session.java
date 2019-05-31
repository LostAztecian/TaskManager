package tm.common.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Session {

    private final String id = UUID.randomUUID().toString();

    private final String userId;

    private String hash;

}
