package com.example.maven;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\REPOSITORYES\\BPM_work_flow_engine\\EngineBpm";
        ProjectInfo info = PomParser.parsePom(PomLocator.findPomFile(path));
        System.out.println(info.toString());
    }
}
