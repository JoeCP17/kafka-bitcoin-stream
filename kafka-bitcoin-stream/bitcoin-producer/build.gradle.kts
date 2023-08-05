import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = false

apply(plugin = "org.springframework.boot")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.slf4j:slf4j-api:2.0.5")
}