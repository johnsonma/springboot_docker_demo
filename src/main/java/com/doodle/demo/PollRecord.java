package com.doodle.demo;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "doodle")
public class PollRecord {
	@Id
	private ObjectId _id;
	private String id;
	private String adminKey;
	private long latestChange;
	private long initiated;
	private int participantsCount;
	private int inviteesCount;
	private String type;
	private String preferencesType;
	private String state;
	private String locale;
	private List<Participant> participants;
	private Initiator initiator;
	private List<Map<String, Object>> options;
	private String optionsHash;
	private String title;
	private String description;
	private String device;
	private String levels;
	private List<Invitee> invitees;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdminKey() {
		return adminKey;
	}
	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}
	public long getLatestChange() {
		return latestChange;
	}
	public void setLatestChange(long latestChange) {
		this.latestChange = latestChange;
	}
	public long getInitiated() {
		return initiated;
	}
	public void setInitiated(long initiated) {
		this.initiated = initiated;
	}
	public Initiator getInitiator() {
		return initiator;
	}
	public void setInitiator(Initiator initiator) {
		this.initiator = initiator;
	}
	public List<Map<String, Object>> getOptions() {
		return options;
	}
	public void setOptions(List<Map<String, Object>> options) {
		this.options = options;
	}
	public Object getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getParticipantsCount() {
		return participantsCount;
	}
	public void setParticipantsCount(int participantsCount) {
		this.participantsCount = participantsCount;
	}
	public int getInviteesCount() {
		return inviteesCount;
	}
	public void setInviteesCount(int inviteesCount) {
		this.inviteesCount = inviteesCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPreferencesType() {
		return preferencesType;
	}
	public void setPreferencesType(String preferencesType) {
		this.preferencesType = preferencesType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public List<Participant> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	public String getOptionsHash() {
		return optionsHash;
	}
	public void setOptionsHash(String optionsHash) {
		this.optionsHash = optionsHash;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public List<Invitee> getInvitees() {
		return invitees;
	}
	public void setInvitees(List<Invitee> invitees) {
		this.invitees = invitees;
	}
	
}
