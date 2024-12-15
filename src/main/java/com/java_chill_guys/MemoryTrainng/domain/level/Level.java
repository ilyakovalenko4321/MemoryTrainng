package com.java_chill_guys.MemoryTrainng.domain.level;

public class Level {

    private Long stage;
    private Long repeated;

    public Level() {
    }

    public Level(Long stage, Long repeated) {
        this.stage = stage;
        this.repeated = repeated;
    }

    public Long getStage() {
        return this.stage;
    }

    public Long getRepeated() {
        return this.repeated;
    }

    public void setStage(Long stage) {
        this.stage = stage;
    }

    public void setRepeated(Long repeated) {
        this.repeated = repeated;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Level)) return false;
        final Level other = (Level) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$stage = this.getStage();
        final Object other$stage = other.getStage();
        if (this$stage == null ? other$stage != null : !this$stage.equals(other$stage)) return false;
        final Object this$repeated = this.getRepeated();
        final Object other$repeated = other.getRepeated();
        if (this$repeated == null ? other$repeated != null : !this$repeated.equals(other$repeated)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Level;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $stage = this.getStage();
        result = result * PRIME + ($stage == null ? 43 : $stage.hashCode());
        final Object $repeated = this.getRepeated();
        result = result * PRIME + ($repeated == null ? 43 : $repeated.hashCode());
        return result;
    }

    public String toString() {
        return "Level(stage=" + this.getStage() + ", repeated=" + this.getRepeated() + ")";
    }
}
