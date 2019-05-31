package tm.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
public class Session {

    @NotNull private final String id = UUID.randomUUID().toString();
    @NotNull private final String userId;
    private String hash;

    public Session(@NotNull final String userId) {
        this.userId = userId;
    }
}
