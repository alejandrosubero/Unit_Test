package com.unitTestGenerator.analyzers.dependency.pojos;


public class DependencyInfo {
    private String groupId;
    private String artifactId;
    private String version;
    private String rawDependency; // cadena original

    public DependencyInfo(String rawDependency) {
        this.rawDependency = rawDependency;
        parseRawDependency(rawDependency);
    }

    public DependencyInfo(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    private void parseRawDependency(String dep) {
        String[] parts = dep.split(":");
        if (parts.length >= 2) {
            this.groupId = parts[0];
            this.artifactId = parts[1];
            if (parts.length == 3) {
                this.version = parts[2];
            }
        }
    }

    public String getGroupId() { return groupId; }
    public String getArtifactId() { return artifactId; }
    public String getVersion() { return version; }
    public String getRawDependency() { return rawDependency; }


    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setRawDependency(String rawDependency) {
        this.rawDependency = rawDependency;
    }



    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{").append("\n");
        buffer.append(" groupId=").append(groupId).append("\n");
        buffer.append(" artifactId=").append(artifactId).append("\n");
        buffer.append(" version=").append(version).append("\n");
        buffer.append("}").append("\n");
        return buffer.toString();
    }


    public String toStringGradle() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Dependencys: =").append("\n").append(rawDependency).append("\n");
        return buffer.toString();
    }


    public String toStringMaven() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("GroupId: ").append(groupId).append("\n");
        buffer.append("ArtifactId: ").append(artifactId).append("\n");
        buffer.append("Version: ").append(version).append("\n");
        return buffer.toString();
    }

    public String toStringMavenLine() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Dependencys:  ");
        buffer.append("GroupId: ").append(groupId).append(", ");
        buffer.append("ArtifactId: ").append(artifactId).append(", ");
        buffer.append("Version: ").append(version).append("\n");
        return buffer.toString();
    }
}

