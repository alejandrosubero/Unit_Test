package com.unitTestGenerator.test.interfaces;

public interface IInternalTest {
    public void executeTest(String pathProject, boolean isAnalisis, String nombreClase, String method, Boolean useMock);
    public void analizedTest(String pathProject, boolean isAnalisis, String nombreClase, String method, Boolean useMock);
}
