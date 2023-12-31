import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = false

apply(plugin = "org.springframework.boot")

dependencies {
    implementation(project(":bitcoin-domain"))
    api("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-validation") // kafka 에서 필요

    api("org.springframework.kafka:spring-kafka-test")
}