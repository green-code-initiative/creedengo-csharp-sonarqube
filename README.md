Creedengo C#
===========

_creedengo_ is a collective project aiming to reduce environmental footprint of software at the code level. The goal of
the project is to provide a list of static code analyzers to highlight code structures that may have a negative
ecological impact: energy and resources over-consumption, "fatware", shortening terminals' lifespan, etc.

_creedengo_ is based on evolving catalogs
of [good practices](https://github.com/green-code-initiative/creedengo-rules-specifications/tree/main/docs/rules), for various technologies.
This SonarQube plugin then implements these catalogs as rules for scanning your C# projects.

This SonarQube plugin is based on a generated plugin from [SonarSource Sonarqube Roslyn SDK](https://github.com/SonarSource/sonarqube-roslyn-sdk) with a slightly different rules import process.

The _Creedengo C# Sonarqube plugin_ relies on the [Creedengo nuget package](https://www.nuget.org/packages/Creedengo) that is responsible of the codebase analysing part. You can then track detected issues within a compatible Sonarqube instance.

> ⚠️ This is still a very early stage project. Any feedback or contribution will be highly appreciated. Please
> refer to the contribution section.

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](https://github.com/green-code-initiative/creedengo-common/blob/main/doc/CODE_OF_CONDUCT.md)

🌿 SonarQube Plugins
-------------------

This plugin is part of the creedengo project.\
You can find a list of all our other plugins in
the [creedengo repository](https://github.com/green-code-initiative/creedengo-rules-specifications#-sonarqube-plugins)

🚀 Getting Started
------------------

You can give a try with a one command docker :

```sh
docker run -ti --rm \
       -p 9000:9000 \
       --name sonarqube-creedengo-csharp ghcr.io/green-code-initiative/sonarqube-creedengo-csharp:latest
```

or (with logs and data locally stored) :

```sh
docker run -ti --rm \
       -v sq_creedengo_logs:/opt/sonarqube/logs \
       -v sq_creedengo_data:/opt/sonarqube/data \
       -p 9000:9000 \
       --name sonarqube-creedengo-csharp ghcr.io/green-code-initiative/sonarqube-creedengo-csharp:latest
```

... and configure local SonarQube (security config and quality profile : see [configuration](https://github.com/green-code-initiative/creedengo-common/blob/main/doc/HOWTO.md#installing-local-environment-local-sonarqube) for more details).

To install other `creedengo` plugins, you can also :

- download each plugin separatly and copy the plugin (jar file) to `$SONAR_INSTALL_DIR/extensions/plugins` and restart SonarQube.
- install different creedengo plugins with Marketplace (inside admin panel of SonarQube)

Then you can use C# test project repository to test the environment : see README.md of [C# test project](https://github.com/green-code-initiative/creedengo-csharp-test-project)

To analyze a .net codebase without having to install required components (i.e. .net SDK, JDK/JRE, etc.), you can use our [docker dotnetscan wrapper](./docker-dotnetscan/README.md).

Finally, you can directly use a [all-in-one docker-compose](https://github.com/green-code-initiative/creedengo-common/blob/main/doc/HOWTO.md#installing-local-environment-local-sonarqube)

By default, `Dockerfile` use the official [Sonarqube LTS Community](https://hub.docker.com/_/sonarqube) image version.
You can override this behaviour and use a custom version of your choice by specifying a `build-arg` to the build command, for example :

```sh
docker build --build-arg SONARQUBE_VERSION=10.5.1-community .
```

If you want to use the `docker-compose` file, you can use the below commands to use an overriding `dockerfile` build :

```sh
docker compose build --build-arg SONARQUBE_VERSION=10.5.1-community
docker compose up -d --no-build
```

🛒 Distribution
------------------

Ready to use binaries are available [from GitHub](https://github.com/green-code-initiative/creedengo-csharp-sonarqube/releases).

🧩 Compatibility
-----------------

| Plugin version | SonarQube version   | creedengo nuget package version |
| -------------- | ------------------- |---------------------------------|
| 1.1.0+         | 9.9.+ LTS to 10.5.1 | 1.1.0                           |

🤝 Contribution
---------------

check [creedengo repository](https://github.com/green-code-initiative/creedengo-rules-specifications#-contribution)

🤓 Main contributors
--------------------

check [creedengo repository](https://github.com/green-code-initiative/creedengo-rules-specifications#-main-contributors)

Links
-----

- <https://docs.sonarqube.org/latest/analysis/overview/>
