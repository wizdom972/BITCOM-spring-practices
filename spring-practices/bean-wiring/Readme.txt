1. soundsystem: Automatic configuration(Annotation scanning)
2. videosystem: Explicit configuration(Bean configuration)

[src/main/java]
    soundsystem
       |--- CDPlayer.java
       |--- CompactDisc.java
       |--- HighSchoolRapper2Final.java

    videosystem
       |--- DVDPlayer.java
       |--- DigitalVideoDisc.java
       |--- Avengers

    config.soundsystem
       |--- CDPlayerConfig.java

    config.videosystem.DVDPlayerConfig
       |--- DVDPlayerConfig.java

    config.videosystem.DVDPlayerConfig.mixing
       |--- DVDConfig.java
       |--- DVDPlayerConfig.java
       |--- VideoSystemConfig.java

[src/main/resources]
    config.soundsystem
       |--- applicationContext.xml

    config.videosystem.DVDPlayerConfig
       |--- applicationContext.xml

[src/test/java]
    config.soundsystem
       |--- CDPlayerXmlConfigTest.java
       |--- CDPlayerJavaConfigTest.java

    config.videosystem.DVDPlayerConfig
       |--- DVDPlayerJavaConfigTest.java
       |--- DVDPlayerXmlConfigTest.java

    config.videosystem.DVDPlayerConfig.mixing
       |--- DVDPlayerMixingConfigTest01.java
       |--- DVDPlayerMixingCon