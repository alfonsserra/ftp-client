
# `ftp-client` — FTP Client for Systelab projects

## Getting Started

To start, simply clone the `ftp-client` repository and install the dependencies:

### Prerequisites

You need [git][git] to clone the `ftp-client` repository.

You will need [Java™ SE Development Kit 8][jdk-download] and [Maven][maven].

#### Maven in Windows

>If you are working in a Windows environment, you could have some issues if the maven local repository is in a folder with a name containing white spaces (quite common as the default value is ${user.home}/.m2/repository). In order to avoid this, it is fully recommended that you specify another folder in your maven settings.xml file.     

For example:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <localRepository>/dev/repo</localRepository>
  
  ...
```

### Clone `ftp-client`

Clone the `ftp-client` repository using git:

```bash
git clone https://github.com/systelab/ftp-client.git
cd ftp-client
```

### Install Dependencies

In order to install the dependencies you must run:

```bash
mvn install
```

### Generate jar

In order to generate a jar file you must run:

```bash
mvn package
```

### Run

Run it from your favourite IDE.

## Next Steps

This is only a proof of concept.

- Get the user and secret as parameter.
- Send the image file.
- ...

[git]: https://git-scm.com/
[maven]: https://maven.apache.org/download.cgi
[jdk-download]: http://www.oracle.com/technetwork/java/javase/downloads

