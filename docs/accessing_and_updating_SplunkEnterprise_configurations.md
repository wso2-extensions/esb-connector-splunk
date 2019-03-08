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

#### Add a stanza
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


**Sample Response**

```xml

<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>conf-myConfigFile</title>
  <id>https://localhost:8089/servicesNS/nobody/splunkApp/configs/conf-myConfigFile</id>
  <updated>2018-12-13T15:48:50+05:30</updated>
  <generator build="be11b2c46e23" version="7.2.1"/>
  <author>
    <name>Splunk</name>
  </author>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/_new" rel="create"/>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/_reload" rel="_reload"/>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/_acl" rel="_acl"/>
  <opensearch:totalResults>1</opensearch:totalResults>
  <opensearch:itemsPerPage>30</opensearch:itemsPerPage>
  <opensearch:startIndex>0</opensearch:startIndex>
  <s:messages/>
  <entry>
    <title>proxylog</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/proxylogs</id>
    <updated>2018-12-13T15:48:50+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/proxylog" rel="alternate"/>
    <author>
      <name>shevindi</name>
    </author>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/proxylog" rel="list"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/proxylog/_reload" rel="_reload"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/proxylog" rel="edit"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/proxylog" rel="remove"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/proxylog/move" rel="move"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-MyConfigFile/proxylog/disable" rel="disable"/>
    <content type="text/xml">
      <s:dict>
        <s:key name="disabled">0</s:key>
        <s:key name="eai:acl">
          <s:dict>
            <s:key name="app">splunkApp</s:key>
            <s:key name="can_change_perms">1</s:key>
            <s:key name="can_list">1</s:key>
            <s:key name="can_share_app">1</s:key>
            <s:key name="can_share_global">1</s:key>
            <s:key name="can_share_user">1</s:key>
            <s:key name="can_write">1</s:key>
            <s:key name="modifiable">1</s:key>
            <s:key name="owner">shevindi</s:key>
            <s:key name="perms">
              <s:dict>
                <s:key name="read">
                  <s:list>
                    <s:item>*</s:item>
                  </s:list>
                </s:key>
                <s:key name="write">
                  <s:list>
                    <s:item>admin</s:item>
                    <s:item>power</s:item>
                  </s:list>
                </s:key>
              </s:dict>
            </s:key>
            <s:key name="removable">1</s:key>
            <s:key name="sharing">app</s:key>
          </s:dict>
        </s:key>
        <s:key name="eai:appName">splunkApp</s:key>
        <s:key name="eai:userName">nobody</s:key>
        <s:key name="x">555</s:key>
        <s:key name="y">1</s:key>
        <s:key name="repo">0</s:key>
      </s:dict>
    </content>
  </entry>
</feed>
```

**Related Splunk documentation**
https://docs.splAunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTconfigurations#Updating_Configuration_Files

####  Update a stanza

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
     <configFileName>myConfigFile</configFileName>
     <stanzaName>proxylogs</stanzaName>
     <dataFields>p=5&amp;z=556</dataFields>
 </updateStanza>
```


**Sample Response**

```xml
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>conf-myConfigFile</title>
  <id>https://localhost:8089/servicesNS/nobody/splunkApp/configs/conf-myConfigFile</id>
  <updated>2018-12-14T10:06:43+05:30</updated>
  <generator build="be11b2c46e23" version="7.2.1"/>
  <author>
    <name>Splunk</name>
  </author>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/_new" rel="create"/>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/_reload" rel="_reload"/>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/_acl" rel="_acl"/>
  <opensearch:totalResults>1</opensearch:totalResults>
  <opensearch:itemsPerPage>30</opensearch:itemsPerPage>
  <opensearch:startIndex>0</opensearch:startIndex>
  <s:messages/>
  <entry>
    <title>proxylogs</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/proxylogs</id>
    <updated>2018-12-14T10:06:43+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/proxylogs" rel="alternate"/>
    <author>
      <name>shevindi</name>
    </author>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/proxylogs" rel="list"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/proxylogs/_reload" rel="_reload"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/proxylogs" rel="edit"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/proxylogs" rel="remove"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/proxylogs/move" rel="move"/>
    <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/proxylogs/disable" rel="disable"/>
    <content type="text/xml">
      <s:dict>
        <s:key name="CHARSET">UTF-8</s:key>
        <s:key name="SHOULD_LINEMERGE">1</s:key>
        <s:key name="disabled">0</s:key>
        <s:key name="eai:acl">
          <s:dict>
            <s:key name="app">splunkApp</s:key>
            <s:key name="can_change_perms">1</s:key>
            <s:key name="can_list">1</s:key>
            <s:key name="can_share_app">1</s:key>
            <s:key name="can_share_global">1</s:key>
            <s:key name="can_share_user">1</s:key>
            <s:key name="can_write">1</s:key>
            <s:key name="modifiable">1</s:key>
            <s:key name="owner">shevindi</s:key>
            <s:key name="perms">
              <s:dict>
                <s:key name="read">
                  <s:list>
                    <s:item>*</s:item>
                  </s:list>
                </s:key>
                <s:key name="write">
                  <s:list>
                    <s:item>admin</s:item>
                    <s:item>power</s:item>
                  </s:list>
                </s:key>
              </s:dict>
            </s:key>
            <s:key name="removable">1</s:key>
            <s:key name="sharing">app</s:key>
          </s:dict>
        </s:key>
        <s:key name="eai:appName">splunkApp</s:key>
        <s:key name="eai:userName">nobody</s:key>
        <s:key name="p">5</s:key>
        <s:key name="z">556</s:key>
      </s:dict>
    </content>
  </entry>
</feed>
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


**Sample Response**

```xml
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>conf-authentication</title>
  <id>https://localhost:8089/servicesNS/nobody/splunkApp/configs/conf-authentication</id>
  <updated>2018-12-13T14:43:01+05:30</updated>
  <generator build="be11b2c46e23" version="7.2.1"/>
  <author>
    <name>Splunk</name>
  </author>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-authentication/_new" rel="create"/>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-authentication/_reload" rel="_reload"/>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-authentication/_acl" rel="_acl"/>
  <opensearch:totalResults>5</opensearch:totalResults>
  <opensearch:itemsPerPage>30</opensearch:itemsPerPage>
  <opensearch:startIndex>0</opensearch:startIndex>
  <s:messages/>
  <entry>
    <title>authentication</title>
    <id>https://localhost:8089/servicesNS/nobody/system/configs/conf-authentication/authentication</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/authentication" rel="alternate"/>
    <author>
      <name>nobody</name>
    </author>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/authentication" rel="list"/>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/authentication/_reload" rel="_reload"/>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/authentication" rel="edit"/>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/authentication/disable" rel="disable"/>
    <content type="text/xml">
      <s:dict>
        <s:key name="authType">Splunk</s:key>
        <s:key name="disabled">0</s:key>
<!-- eai:acl elided -->
        <s:key name="eai:appName">splunkApp</s:key>
        <s:key name="eai:userName">nobody</s:key>
        <s:key name="passwordHashAlgorithm">SHA512-crypt</s:key>
      </s:dict>
    </content>
  </entry>
  <entry>
    <title>cacheTiming</title>
    <id>https://localhost:8089/servicesNS/nobody/system/configs/conf-authentication/cacheTiming</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/cacheTiming" rel="alternate"/>
    <author>
      <name>nobody</name>
    </author>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/cacheTiming" rel="list"/>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/cacheTiming/_reload" rel="_reload"/>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/cacheTiming" rel="edit"/>
    <link href="/servicesNS/nobody/system/configs/conf-authentication/cacheTiming/disable" rel="disable"/>
    ..........
  </entry>
</feed>
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


**Sample Response**

```xml
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>conf-myConfigFile</title>
  <id>https://localhost:8089/servicesNS/nobody/splunkApp/configs/conf-myConfigFile</id>
  <updated>2018-12-13T17:03:36+05:30</updated>
  <generator build="be11b2c46e23" version="7.2.1"/>
  <author>
    <name>Splunk</name>
  </author>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/_new" rel="create"/>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/_reload" rel="_reload"/>
  <link href="/servicesNS/nobody/splunkApp/configs/conf-myConfigFile/_acl" rel="_acl"/>
  <opensearch:totalResults>0</opensearch:totalResults>
  <opensearch:itemsPerPage>30</opensearch:itemsPerPage>
  <opensearch:startIndex>0</opensearch:startIndex>
  <s:messages/>
</feed>
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
Returns HTTP status = 201 (created) with no response body.


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


**Sample Response**

```xml
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest">
  <title>properties</title>
  <id>https://localhost:8089/servicesNS/nobody/splunkApp/properties</id>
  <updated>2018-12-13T13:00:53+05:30</updated>
  <generator build="be11b2c46e23" version="7.2.1"/>
  <author>
    <name>Splunk</name>
  </author>
  <entry>
    <title>alert_actions</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/properties/alert_actions</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/properties/alert_actions" rel="alternate"/>
  </entry>
  <entry>
    <title>alerts</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/properties/alerts</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/properties/alerts" rel="alternate"/>
  </entry>
  <entry>
    <title>app</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/properties/app</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/properties/app" rel="alternate"/>
  </entry>
  <entry>
    <title>authentication</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/properties/authentication</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/properties/authentication" rel="alternate"/>
  </entry>
  <entry>
    <title>authorize</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/properties/authorize</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/properties/authorize" rel="alternate"/>
  </entry>
  <entry>
    <title>collections</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/properties/collections</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/properties/collections" rel="alternate"/>
  </entry>
  <entry>
    <title>commands</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/properties/commands</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/properties/commands" rel="alternate"/>
  </entry>
  <entry>
    ............
  </entry>
 </feed>
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
