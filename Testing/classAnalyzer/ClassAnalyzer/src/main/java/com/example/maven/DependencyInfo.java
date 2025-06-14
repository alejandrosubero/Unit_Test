package com.example.maven;


public class DependencyInfo {
    private String groupId;
    private String artifactId;
    private String version;




    public DependencyInfo(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }


    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
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
}

