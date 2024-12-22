package com.java_chill_guys.MemoryTrainng.web.dto;

public class DataDto {

    private String encodedLevel;
    private String words;
    private String wordsUser;
    private Integer total;
    private String text;

    public DataDto(String encodedLevel, String words, String wordsUser, Integer total, String text) {
        this.encodedLevel = encodedLevel;
        this.words = words;
        this.wordsUser = wordsUser;
        this.total = total;
        this.text = text;
    }

    public DataDto() {
    }

    public String getEncodedLevel() {
        return this.encodedLevel;
    }

    public String getWords() {
        return this.words;
    }

    public String getWordsUser() {
        return this.wordsUser;
    }

    public void setEncodedLevel(String encodedLevel) {
        this.encodedLevel = encodedLevel;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public void setWordsUser(String wordsUser) {
        this.wordsUser = wordsUser;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DataDto)) return false;
        final DataDto other = (DataDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$encodedLevel = this.getEncodedLevel();
        final Object other$encodedLevel = other.getEncodedLevel();
        if (this$encodedLevel == null ? other$encodedLevel != null : !this$encodedLevel.equals(other$encodedLevel))
            return false;
        final Object this$words = this.getWords();
        final Object other$words = other.getWords();
        if (this$words == null ? other$words != null : !this$words.equals(other$words)) return false;
        final Object this$wordsUser = this.getWordsUser();
        final Object other$wordsUser = other.getWordsUser();
        if (this$wordsUser == null ? other$wordsUser != null : !this$wordsUser.equals(other$wordsUser)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DataDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $encodedLevel = this.getEncodedLevel();
        result = result * PRIME + ($encodedLevel == null ? 43 : $encodedLevel.hashCode());
        final Object $words = this.getWords();
        result = result * PRIME + ($words == null ? 43 : $words.hashCode());
        final Object $wordsUser = this.getWordsUser();
        result = result * PRIME + ($wordsUser == null ? 43 : $wordsUser.hashCode());
        return result;
    }

    public String toString() {
        return "DataDto(encodedLevel=" + this.getEncodedLevel() + ", words=" + this.getWords() + ", wordsUser=" + this.getWordsUser() + ")";
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
