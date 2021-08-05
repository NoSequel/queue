# Queue
A simplistic queue system, written in Java, primarily for Bukkit.

## Features
* Multiple queues per server (set the target of a server using /queuemeta setserver <queue\> <server\>)
* Very easy to make new implementations, most code is shared between all implementations.
* Able to join multiple queues at the same time, position synchronized across network instances.
* Very extensible structure easily allowing for new features.

## Configuration
The plugin is completely configurable, including queues, servers and most (if not all) messages.
The configurations provided underneath are all the default configurations, and although the configurtions might
look intimidating to configure for a beginner, they're really extensible, with a mix between JSON and YAML.

### lang.yml
```yaml
messages:
  queue:
    help:
      header: '&6=== &eViewing usages of &6/queuemeta ==='
      format: '&equeuemeta %sub_label% &6%sub_arguments% - %description%'
      sub_commands: |-
        [
          {
            "subcommandName": "help",
            "arguments": "",
            "description": "Show the message you\u0027re viewing now."
          },
          {
            "subcommandName": "list",
            "arguments": "",
            "description": "Display all the registered queues."
          },
          {
            "subcommandName": "create",
            "arguments": "\u003cname\u003e",
            "description": "Create a new queue."
          },
          {
            "subcommandName": "setserver",
            "arguments": "\u003cqueue\u003e \u003cserver\u003e",
            "description": "Set the target server of a queue."
          }
        ]
    created: '&eSuccessfully created a queue with the name &d%queue_name%'
    info:
      header: '&6=== &eViewing all registered queues &6==='
      entry: '&e%queue_name% &7| &f%target_server%'
    server:
      update: '&eYou have set &d%queue_name%&e''s target server to &d%target_server%'
    join: '&6You have joined the &f%queue_name% &6queue.'
    send: '&6Trying to send you to the &f%server_name% &6server.'
```

### queues.yml
```yaml
queues: |-
  [
    "{\"identifier\":\"potato\",\"server_target\":\"N/A\"}",
    "{\"identifier\":\"hors\",\"server_target\":\"N/A\"}"
  ]
```

### servers.yml
```yaml
local-server: '{"identifier":"dev-1"}'
servers: |-
  [
    "{\"identifier\":\"hcteams-eu\"}"
  ]
```

### database.yml
```yaml
redis:
  authorization: |-
    {
      "hostname": "127.0.0.1",
      "port": 6379,
      "password": ""
    }
```

## Current State
The project is not completely finished, and I don't know if I will finish it any time soon.