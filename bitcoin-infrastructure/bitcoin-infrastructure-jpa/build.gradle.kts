import org.springframework.boot.gradle.tasks.bundling.BootJar
import java.util.regex.Pattern.compile

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = false

apply(plugin = "org.springframework.boot")

dependencies {
    //spring boot
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    //DB connect
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")

    // entity
    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }
    compile("javax.xml.bind:jaxb-api:2.3.0")


}