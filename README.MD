# Shortcuts
Nice little things that I find myself doing a lot of put all in one place.

### What is this for?
It's just to put a bunch of code that I found myself repeating a lot, and slapping it all down here so that I can just import one dependency over rewriting it for x many more future projects.

### Where can I use it?
Everything - Code that applies to everything, may be used by other modules
...
More to come as I add it.

### Why?
Why the heck not? It's kinda a helper for me.

## How to use?
Probably the most important is the `EverythingAPI`s: `BukkitEverythingAPI` and `BungeeEverythingAPI`.

Wherever you need it just create a new instance of it and pass your plugin to the constructor:

```java
// Bukkit
BukkitEverythinAPI bukkit = new BukkitEverythingAPI(plugin);

// Bungee
BungeeEverythinAPI bungee = new BungeeEverythingAPI(plugin);
```