# Unit_Test


# Project structure bash unit-test-dist.zip
=============================================
hello-cli/
├── cli/
│   ├── unit
│   └── unit.bat
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── App.java
├── pom.xml
└── Makefile

=============================================

cli-dist.zip
|── cli/
│   ├── unit
│   ├── unit.bat
│   └── unit-1.0.0-jar-with-dependencies.jar
|____Makefile        
=============================================

# Build STEPS:
1. mvn clean package
2. make dist
3. unzip 


# macOS:
=============================================
unzip:
unzip unit-test-dist.zip -d unit-test-dist
cd cli
=============================================
Install:
sudo ./cli/unit install

=============================================
Use post-install in macOs:
Usage:
  unit [command] [options]

Commands:
  install       Install the application
  uninstall     Remove the application
  --version, -v Show version
  --help, -h    Show this help

=============================================


# Windows:
=============================================
Install:
CMD/Powershell open like Administrator
cli\unit.bat install
=============================================
post-instalación:
Use in Windows:
unit [comando]
Comandos:
install    Install the application
uninstall  Remove the application
--version  Show version
--help     Show this help

=============================================