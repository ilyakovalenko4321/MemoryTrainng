package com.java_chill_guys.MemoryTrainng.service.Props;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.level")
public class LevelEncryptProps {

    String key;

    public LevelEncryptProps() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LevelEncryptProps)) return false;
        final LevelEncryptProps other = (LevelEncryptProps) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LevelEncryptProps;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        return result;
    }

    public String toString() {
        return "LevelEncryptProps(key=" + this.getKey() + ")";
    }
}
