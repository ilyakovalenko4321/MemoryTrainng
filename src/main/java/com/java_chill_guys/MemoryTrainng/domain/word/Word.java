package com.java_chill_guys.MemoryTrainng.domain.word;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "words")
public class Word {

    @Id
    private Long id;
    private String word;

    public Word() {
    }

    public Long getId() {
        return this.id;
    }

    public String getWord() {
        return this.word;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Word)) return false;
        final Word other = (Word) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$word = this.getWord();
        final Object other$word = other.getWord();
        if (this$word == null ? other$word != null : !this$word.equals(other$word)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Word;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $word = this.getWord();
        result = result * PRIME + ($word == null ? 43 : $word.hashCode());
        return result;
    }

    public String toString() {
        return "Word(id=" + this.getId() + ", word=" + this.getWord() + ")";
    }
}
