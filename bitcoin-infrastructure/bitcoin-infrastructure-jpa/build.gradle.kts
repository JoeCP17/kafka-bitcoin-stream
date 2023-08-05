import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = false

apply(plugin = "org.springframework.boot")

dependencies {
    //spring boot
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    //DB connect
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
}