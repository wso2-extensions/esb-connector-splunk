# Accessing and updating Splunk Enterprise configurations

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 

The following operations allow you to  access and update information contained in *.conf file(configuration file).. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with configuration files, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [addStanza](#add-a-stanza)    | Add stanzas to the configuration file |
| [updateStanza](#update-a-stanza)      | Update or add property to stanza in configuration file |
| [getStanza](#get-all-stanzas)      | Returns all the stanza names in the named configuration file |
| [deleteStanza](#delete-a-stanza)      | Delete stanza in configuration file. |
| [createConfigFile](#create-a-configuration-file)      | Create a configuration file. |
| [getAppConfigFiles](#get-all-configuration-files-for-an-app)      | List all the configuration files available for the app. |

### Operation details

This section provides more details on each of the operations.

#### Adding a stanza
The addStanza operation adds a stanza to a specific configuration file

**addStanza**
```xml
<splunk.addStanza>
    <appName>{$ctx:appName}</appName>
    <configFileName>{$ctx:configFileName}</configFileName>
    <stanzaName>{$ctx:stanzaName}</stanzaName>
    <dataFields>{$ctx:dataFields}</dataFields>
</splunk.addStanza>
```

**Properties**
* appName           : Required - Name of the default app
* configFileName    : Required - Name of the configuration file
* stanzaName        : Required - Name of the stanza
* dataFields        : Arbitrary number of key/value pairs


**Sample request**

Following is a sample request that can be handled by the addStanza operation.

```xml
<addStanza>
    <username>shevindi</username>
    <password>n@v@nj@n@95</password>
    <hostname>localhost</hostname>
    <port>8089</port>
    <appName>splunkApp</appName>
    <configFileName>MyConfigFile</configFileName>
    <stanzaName>proxylog</stanzaName>
    <dataFields>x=555&amp;y=true&amp;repo=false</dataFields>
</addStanza>
```

**Related Splunk documentation**
https://docs.splAunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTconfigurations#Updating_Configuration_Files

####  Updating a stanza

The updateStanza operation allows you to update or add property to a stanza in a specific configuration file.

**updateStanza**
```xml
<splunk.updateStanza>
    <appName>{$ctx:appName}</appName>
    <configFileName>{$ctx:configFileName}</configFileName>
    <stanzaName>{$ctx:stanzaName}</stanzaName>
    <dataFields>{$ctx:dataFields}</dataFields>
</splunk.updateStanza>
```

**Properties**
* appName           : Required - Name of the default app
* configFileName    : Required - Name of the configuration file
* stanzaName        : Required - Name of the stanza
* dataFields        : Required - Arbitrary number of key/value pairs

**Sample request**

Following is a sample request that can be handled by the accessSavedSearch operation.

```xml
<updateStanza>
     <username>shevindi</username>
     <password>n@v@nj@n@95</password>
     <hostname>localhost</hostname>
     <port>8089</port>
     <appName>splunkApp</appName>
     <configFileName>MyConfigFile</configFileName>
     <stanzaName>proxylog</stanzaName>
     <dataFields>p=5&amp;z=556</dataFields>
 </updateStanza>
```

**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTconfigurations#configs.2Fconf-.7Bfile.7D.2F.7Bname.7D

####  Get all stanzas

The getStanza operation returns all the stanza names in the named configuration file

**getStanza**
```xml
<splunk.getStanza>
    <appName>{$ctx:appName}</appName>
    <configFileName>{$ctx:configFileName}</configFileName>
</splunk.getStanza>
```
**Properties**
* appName           : Required - Name of the default app
* configFileName    : Required - Name of the configuration file

**Sample request**

Following is a sample request that can be handled by the getStanza operation.

```xml
<getStanza>
     <username>shevindi</username>
     <password>n@v@nj@n@95</password>
     <hostname>localhost</hostname>
     <port>8089</port>
     <appName>splunkApp</appName>
     <configFileName>MyConfigFile</configFileName>
</getStanza>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTconfigurations#Reading_configuration_files

####  Delete a stanza

The deleteStanza operation deletes a stanza in a specific configuration file.

**deleteStanza**
```xml
<splunk.deleteStanza>
    <appName>{$ctx:appName}</appName>
    <configFileName>{$ctx:configFileName}</configFileName>
    <stanzaName>{$ctx:stanzaName}</stanzaName>
</splunk.deleteStanza>
```
**Properties**
* appName           : Required - Name of the default app
* configFileName    : Required - Name of the configuration file
* stanzaName        : Required - Name of the stanza

**Sample request**

Following is a sample request that can be handled by the deleteStanza operation.

```xml
<deleteStanza>
     <username>shevindi</username>
     <password>n@v@nj@n@95</password>
     <hostname>localhost</hostname>
     <port>8089</port>
     <appName>splunkApp</appName>
     <configFileName>MyConfigFile</configFileName>
     <stanzaName>proxylog</stanzaName>
</deleteStanza>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTREF/RESTconf#configs.2Fconf-.7Bfile.7D.2F.7Bstanza.7D

####  Create a configuration file

The createConfigFile operation creates a configuration file.

**createConfigFile**
```xml
<splunk.createConfigFile>
    <appName>{$ctx:appName}</appName>
    <configFileName>{$ctx:configFileName}</configFileName>
</splunk.createConfigFile>
```
**Properties**
* appName           : Required - Name of the default app
* configFileName    : Required - Name of the configuration file

**Sample request**

Following is a sample request that can be handled by the createConfigFile operation.

```xml
<createConfigFile>
     <username>shevindi</username>
     <password>n@v@nj@n@95</password>
     <hostname>localhost</hostname>
     <port>8089</port>
     <appName>splunkApp</appName>
     <configFileName>MyConfigFile</configFileName>
</createConfigFile>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTREF/RESTconf#properties

####  Get all configuration files for an app

The getAppConfigFiles operation list all the configuration files available for the app.

**getAppConfigFiles**
```xml
<splunk.getAppConfigFiles>
    <appName>{$ctx:appName}</appName>
</splunk.getAppConfigFiles>
```
**Properties**
* appName       : Required - Name of the default app.

**Sample request**

Following is a sample request that can be handled by the getAppConfigFiles operation.

```xml
<getAppConfigFiles>
     <username>shevindi</username>
     <password>n@v@nj@n@95</password>
     <hostname>localhost</hostname>
     <port>8089</port>
     <appName>splunkApp</appName>
</getAppConfigFiles>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTconfigurations#Reading_configuration_files

#### Sample configuration

Following example illustrates how to connect to Splunk with the init operation and createConfigFile operation.

1. Create a sample proxy as below :

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse" name="createConfigFile"
       transports="https,http" statistics="disable" trace="disable"
       startOnLoad="true">
    <target>
        <inSequence>
            <property name="username" expression="//username/text()"/>
            <property name="password" expression="//password/text()"/>
            <property name="hostname" expression="//hostname/text()"/>
            <property name="port" expression="//port/text()"/>
            <property name="appUserName" expression="//appUserName/text()"/>
            <property name="appName" expression="//appName/text()"/>
            <property name="configFileName" expression="//configFileName/text()" />
            <splunk.init>
                <username>{$ctx:username}</username>
                <password>{$ctx:password}</password>
                <hostname>{$ctx:hostname}</hostname>
                <port>{$ctx:port}</port>
            </splunk.init>
            <splunk.createConfigFile>
                <appName>{$ctx:appName}</appName>
                <configFileName>{$ctx:configFileName}</configFileName>
            </splunk.createConfigFile>
            <log level="full"/>
            <respond/>
        </inSequence>
        <outSequence>
            <send/>
        </outSequence>
    </target>
    <description/>
</proxy>

```
2. Create a xml file named createConfigFile.xml and copy the configurations given below to it:

```xml
<createConfigFile>
     <username>shevindi</username>
     <password>n@v@nj@n@95</password>
     <hostname>localhost</hostname>
     <port>8089</port>
     <appName>splunkApp</appName>
     <configFileName>MyConfigFile</configFileName>
</createConfigFile>
```
3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8089/services/createConfigFile -H "Content-Type: application/xml" -d @createConfigFile.xml
```

5. Splunk returns HTTP status = 201 (created) with no response body.

