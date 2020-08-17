package org.ecocean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Project implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private ArrayList<Encounter> encounters = null;

    private String researchProjectname;
    private String researchProjectId;

    private Long dateCreatedLong;
    private Long dateLastModifiedLong;

    //empty constructor used by the JDO enhancer
    public Project() {}

    public Project(String researchProjectId) {
        this(researchProjectId, null, null);
    }

    public Project(String researchProjectId, List<Encounter> encs) {
        this(researchProjectId, null, encs);
    }

    public Project(String researchProjectId, String researchProjectName) {
        this(researchProjectId, researchProjectName, null);
    }

    public Project(String researchProjectId, String researchProjectName, List<Encounter> encs) {
        this.encounters = new ArrayList<Encounter>();
        this.id = Util.generateUUID();
        this.researchProjectId = researchProjectId;
        setTimeCreated();
        setTimeLastModified();
    }

    public String getId() {
        return id;
    }

    //stub TODO
    public  Float getPercentIdentified(){
      return 10.5;
    }

    private void setTimeCreated() {
        dateCreatedLong = System.currentTimeMillis();
    }

    public void setTimeLastModified() {
        dateLastModifiedLong = System.currentTimeMillis();
    }

    public long getDataCreatedLong() {
        return dateCreatedLong;
    }

    public long getTimeLastModifiedLong() {
        return dateLastModifiedLong;
    }

    public void setResearchProjectName(String researchProjectname) {
        setTimeLastModified();
        this.researchProjectname = researchProjectname;
    }

    public String getResearchProjectName() {
        return researchProjectname;
    }

    public void setResearchProjectId(String researchProjectId) {
        setTimeLastModified();
        this.researchProjectId = researchProjectId;
    }

    public String getResearchProjectId() {
        return researchProjectId;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void addEncounter(Encounter enc) {
        setTimeLastModified();
        if (!encounters.contains(enc)) {
            encounters.add(enc);
        } else {
            System.out.println("[INFO]: Project.addEncounter(): The selected Project id="+id+" already contains encounter id="+enc.getID()+", skipping.");
        }
    }

    public void addEncounters(List<Encounter> encs) {
        for (Encounter enc : encs) {
            addEncounter(enc);
        }
    }

    public void removeEncounter(Encounter enc) {
        setTimeLastModified();
        encounters.remove(enc);
    }

    public void clearAllEncounters() {
        setTimeLastModified();
        encounters = new ArrayList<>();
    }

    public ArrayList<MarkedIndividual> getAllIndividualsForProject() {
        ArrayList<MarkedIndividual> mis = null;
        for (Encounter enc : encounters) {
            MarkedIndividual mi = enc.getIndividual();
            if (mi!=null) {
                if (mis==null) {
                    mis = new ArrayList<MarkedIndividual>();
                }
                if (!mis.contains(mi)) {
                    mis.add(mi);
                }
            }
        }
        return mis;
    }

    public int numEncounters() {
        if (encounters!=null) {
            return encounters.size();
        }
        return 0;
    }

    public int numIndividuals() {
        if (getAllIndividualsForProject()!=null) {
            return getAllIndividualsForProject().size();
        }
        return 0;
    }

    //stub
    public JSONObject asJSONObject() {
        JSONObject j = new JSONObject();
        return j;
    }

    //stub
    public String toString() {
        return "";
    }

    //stub
    public static ArrayList<Project> getProjectsForEncounter() {
        ArrayList<Project> projects = new ArrayList<>();
        return projects;
    }

    //stub
    public static List<Project> getProjectsForUser(User user){
      ArrayList<Project> projects = new ArrayList<>();
      return projects;
    }

}