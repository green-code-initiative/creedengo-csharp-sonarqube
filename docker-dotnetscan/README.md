# .net analysis docker utility container

Docker utility container for dotnet codebase analysis that can be use to scan .net code and upload result to a Sonarqube instance.
This container is intended to be used as a standalone tool with the docker-compose configuration provided in this repo that starts a Sonarqube instance or an existing Sonarqube instance.
With this tool, you don't need to install required components on your local environment. You just need to be able to launch docker container.

## How to use

### Build the image

Build the docker image and tag it with a convenient name (for instance we named it `scanner`) :

```sh
docker build -t scanner .
```

### Use a specific .Net SDK version

By default building docker image will use `.Net SDK 9.0`. You can use a version that will suit you usecase by specifying the version you want to use with a `build-arg` :

```sh
docker build -t scanner --build-arg DOTNET_SDK=8.0 .
```

Obviously, you can tag your image using `-t scanner:8.0` for example and adapt below analysing commands accordingly.

### Analyze your code

#### Prerequisites

You need to have an up and running Sonarqube instance with :

- the creedengo CSharp plugin installed
- Quality profile with creedengo rules activated
- An user token, global or project analysis token, cf. Sonarqube documentation : [Generating and using tokens](https://docs.sonarsource.com/sonarqube/latest/user-guide/user-account/generating-and-using-tokens/)

The following samples commands are based on these assumptions :

| Property                  | Parameter | Value                   | Comments                                                                                                                   |
| ------------------------- | --------- | ----------------------- | -------------------------------------------------------------------------------------------------------------------------- |
| Project key               | -p        | `myDotNetProject`       | Project will be create automatically if you use a user token (i.e. `-l`) that is allowed to create one (i.e. admin rights) |
| User auth token           | -l        | `squ_123456789`         | User token are `squ_XXXX`. [_Deprecated by Sonarqube and will be remove in the future, use -t instead_]                    |
| Global/Project auth token | -t        | `sqp_123456789`         | Global or projet analysis token are `sqp_XXXX`                                                                             |
| Sonarqube url             | -u        | `http://localhost:9000` | Note : this is the default url if you omit to specify one with `-u` parameter                                              |

Running a SonarQube analysis is straighforward. You just need to execute the following commands at **the root of your solution**.

#### With a Sonarqube instance from our docker-compose

```sh
docker run --rm -it --mount type=bind,src="$(pwd)",target=/src --network sonarnet scanner -p myDotNetProject -u http://sonar:9000 -t sqp_123456789
```

#### With an existing Sonarqube instance

```sh
docker run --rm -it --mount type=bind,src="$(pwd)",target=/src --network host scanner -p myDotNetProject -u http://localhost:9000 -t sqp_123456789
```

For further available parameters, refer to Help

```sh
docker run --rm -it scanner -h
```
