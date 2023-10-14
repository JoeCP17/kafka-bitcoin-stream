import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = false

apply(plugin = "org.springframework.boot")

dependencies {
    implementation(project(":bitcoin-infrastructure:bitcoin-infrastructure-kafka"))
    implementation(project(":bitcoin-infrastructure:bitcoin-infrastructure-jpa"))
    implementation(project(":bitcoin-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    // websocket
    implementation ("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.webjars:sockjs-client:1.1.2")
    implementation("org.webjars:stomp-websocket:2.3.3-1")
}