plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // used by application plugin
    implementation("com.google.guava:guava:32.1.1-jre")

    // - - tabularium Dependencies

    // ui
    implementation("com.miglayout:miglayout:3.7.4");
    implementation("com.formdev:flatlaf:1.6.5")

    // logging
    implementation("org.slf4j:slf4j-api:2.0.9");
    implementation("ch.qos.logback:logback-classic:1.4.13");

    // - - 
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.9.3")
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("org.yates.App")
}
