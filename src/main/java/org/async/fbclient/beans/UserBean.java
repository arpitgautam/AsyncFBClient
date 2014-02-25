package org.async.fbclient.beans;

import com.google.gson.annotations.SerializedName;


public class UserBean {

	private String id;
	private String name;
	private String firstName;
	private String lastName;
	@SerializedName("link") private String profileLink;
	private String birthday;//TODO- make this one date
	private String bio;
	private String quotes;
	private String gender;//TODO-make this enum
	private String relationshipStatus;//TODO- make this enum
	private String religion;
	@SerializedName("political")private String politicalInclination;
	private String website;//TODO- make this url
	private String timezone;//TODO- make this timeZone
	private String locale;//TODO- make this locale
	private String verified;//TODO- make this boolean
	private String username;
	private String updatedTime;
	
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelationshiSstatus() {
		return relationshipStatus;
	}
	public void setRelationshipStatus(String relationship_status) {
		this.relationshipStatus = relationship_status;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getPoliticalInclination() {
		return politicalInclination;
	}
	public void setPoliticalInclination(String politicalInclination) {
		this.politicalInclination = politicalInclination;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTimeZone() {
		return timezone;
	}
	public void setTimeZone(String timeZone) {
		this.timezone = timeZone;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getProfileLink() {
		return profileLink;
	}
	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public UserBean(){}
	
	
}
