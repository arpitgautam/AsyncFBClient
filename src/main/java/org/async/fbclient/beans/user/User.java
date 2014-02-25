
package org.async.fbclient.beans.user;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("com.googlecode.jsonschema2pojo")
public class User {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String first_name;
    @Expose
    private String last_name;
    @Expose
    private String link;
    @Expose
    private String birthday;
    @Expose
    private Hometown hometown;
    @Expose
    private Location location;
    @Expose
    private String bio;
    @Expose
    private String quotes;
    @Expose
    private List<Work> work = new ArrayList<Work>();
    @Expose
    private List<Sport> sports = new ArrayList<Sport>();
    @Expose
    private List<Favorite_team> favorite_teams = new ArrayList<Favorite_team>();
    @Expose
    private List<Favorite_athlete> favorite_athletes = new ArrayList<Favorite_athlete>();
    @Expose
    private List<Inspirational_person> inspirational_people = new ArrayList<Inspirational_person>();
    @Expose
    private List<Education> education = new ArrayList<Education>();
    @Expose
    private String gender;
    @Expose
    private List<String> interested_in = new ArrayList<String>();
    @Expose
    private String relationship_status;
    @Expose
    private Significant_other significant_other;
    @Expose
    private String religion;
    @Expose
    private String political;
    @Expose
    private String website;
    @Expose
    private Double timezone;
    @Expose
    private String locale;
    @Expose
    private Boolean verified;
    @Expose
    private String updated_time;
    @Expose
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Hometown getHometown() {
        return hometown;
    }

    public void setHometown(Hometown hometown) {
        this.hometown = hometown;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public List<Work> getWork() {
        return work;
    }

    public void setWork(List<Work> work) {
        this.work = work;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    public List<Favorite_team> getFavorite_teams() {
        return favorite_teams;
    }

    public void setFavorite_teams(List<Favorite_team> favorite_teams) {
        this.favorite_teams = favorite_teams;
    }

    public List<Favorite_athlete> getFavorite_athletes() {
        return favorite_athletes;
    }

    public void setFavorite_athletes(List<Favorite_athlete> favorite_athletes) {
        this.favorite_athletes = favorite_athletes;
    }

    public List<Inspirational_person> getInspirational_people() {
        return inspirational_people;
    }

    public void setInspirational_people(List<Inspirational_person> inspirational_people) {
        this.inspirational_people = inspirational_people;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getInterested_in() {
        return interested_in;
    }

    public void setInterested_in(List<String> interested_in) {
        this.interested_in = interested_in;
    }

    public String getRelationship_status() {
        return relationship_status;
    }

    public void setRelationship_status(String relationship_status) {
        this.relationship_status = relationship_status;
    }

    public Significant_other getSignificant_other() {
        return significant_other;
    }

    public void setSignificant_other(Significant_other significant_other) {
        this.significant_other = significant_other;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Double getTimezone() {
        return timezone;
    }

    public void setTimezone(Double timezone) {
        this.timezone = timezone;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

}
