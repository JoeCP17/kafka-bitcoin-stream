import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = false

apply(plugin = "org.springframework.boot")

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // websocket
    implementation ("org.springframework.boot:spring-boot-starter-websocket")

    // DNS Resorver
    implementation ("io.netty:netty-resolver-dns-native-macos:4.1.68.Final:osx-aarch_64")
    implementation ("org.springframework.cloud:spring-cloud-starter-gateway")
}