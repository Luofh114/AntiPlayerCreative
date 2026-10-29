# Anti Player Creative

A server-side Fabric mod for Minecraft **1.21.11** that prevents players from
using creative mode. It forces a player to survival when they join and checks
all connected players at the end of every server tick, so command- or
plugin-triggered game-mode changes are reverted immediately.

## Build

Requirements: Java 21 and a network connection for Gradle dependencies.

```text
gradle build
```

(`gradle` must be installed and available on `PATH`; the source project does
not vendor a Gradle wrapper.)

Copy `build/libs/anti-player-creative-1.0.0.jar` to the server's `mods`
directory alongside Fabric API. This is a server-only mod; it does not need to
be installed on clients.

## Configuration

There is no configuration: every player is forced to survival.
