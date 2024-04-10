package com.sdub.pbliga.pbligawebscraper.model.datalake;

public class FreeClub extends DataLakeModel {

    private String country;
    private String name;
    private String rank;
    private String stadiumCapacity;
    private Integer division;
    private boolean euroCupParticipant;
    private Integer ligaPlace;
    private String power11;
    private String finance;
    private Integer playersCount;
    private Integer playersCountUnder21;
    private String averagePlayersAge;


    @Override
    public String toString() {
        return "FreeClub{" +
                "country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", stadiumCapacity=" + stadiumCapacity +
                ", division=" + division +
                ", euroCupParticipant=" + euroCupParticipant +
                ", ligaPlace=" + ligaPlace +
                ", power11=" + power11 +
                ", finance='" + finance + '\'' +
                ", playersCount=" + playersCount +
                ", playersCountUnder21=" + playersCountUnder21 +
                ", averagePlayersAge=" + averagePlayersAge +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStadiumCapacity() {
        return stadiumCapacity;
    }

    public void setStadiumCapacity(String stadiumCapacity) {
        this.stadiumCapacity = stadiumCapacity;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public boolean isEuroCupParticipant() {
        return euroCupParticipant;
    }

    public void setEuroCupParticipant(boolean euroCupParticipant) {
        this.euroCupParticipant = euroCupParticipant;
    }

    public Integer getLigaPlace() {
        return ligaPlace;
    }

    public void setLigaPlace(Integer ligaPlace) {
        this.ligaPlace = ligaPlace;
    }

    public String getPower11() {
        return power11;
    }

    public void setPower11(String power11) {
        this.power11 = power11;
    }

    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }

    public Integer getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(Integer playersCount) {
        this.playersCount = playersCount;
    }

    public Integer getPlayersCountUnder21() {
        return playersCountUnder21;
    }

    public void setPlayersCountUnder21(Integer playersCountUnder21) {
        this.playersCountUnder21 = playersCountUnder21;
    }

    public String getAveragePlayersAge() {
        return averagePlayersAge;
    }

    public void setAveragePlayersAge(String averagePlayersAge) {
        this.averagePlayersAge = averagePlayersAge;
    }
}
