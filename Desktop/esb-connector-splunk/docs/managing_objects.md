# Working with Saved Searches

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 

The following operations allow you to work with saved searches. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with saved searches, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [createSavedSearch](#create-a-saved-search)    | Create a saved search for the user that is available from the app. |
| [accessSavedSearch](#access-saved-searches)      | Access saved search configurations that you have permission to view. |
| [updateSavedSearch](#update-a-saved-search)      | Update a named saved search. |
| [shareSavedSearch](#share-a-saved-search)      | Share the saved search and change its permission. |
| [moveSavedSearch](#move-a-saved-search)      | Make the saved search available for all users and change the context to a different app which was previously available to all in the context of default app. |
| [deleteSavedSearch](#delete-a-saved-search)      | Delete a named saved search. |

### Operation details

This section provides more details on each of the operations.

#### Creating a saved search
The createSavedSearch operation creates a  saved search for the user that is available from the app.

**createSavedSearch**
```xml
<splunk.createSavedSearch>
    <appName>{$ctx:appName}</appName>
    <appUserName>{$ctx:appUserName}</appUserName>
    <searchName>{$ctx:searchName}</searchName>
    <searchQuery>{$ctx:searchQuery}</searchQuery>
</splunk.createSavedSearch>
```

**Properties**
* appName       : Required - Name of the default app
* appUserName   : Required - Owner of the app
* searchName    : Required - Name for the search
* searchQuery   : Required - The search query


**Sample request**

Following is a sample request that can be handled by the createSavedSearch operation.

```xml
<createSavedSearch>
  <username>shevindi</username>
  <password>n@v@nj@n@95</password>
  <hostname>localhost</hostname>
  <port>8089</port>
  <appName>splunkApp</appName>
  <appUserName>shevindi</appUserName>
  <searchName>Mysearch</searchName>
  <searchQuery>*</searchQuery>
</createSavedSearch>
```

**Related Splunk documentation**
http://docs.splunk.com/Documentation/Splunk/7.2.1/RESTREF/RESTsearch#saved.2Fsearches

####  Accessing saved searches

The accessSavedSearch operation allows you to access saved search configurations that you have permission to view.For an admin user, this include other user’s private saved searches. For a non admin user, you retrieve only saved searches that you have permission to view.

**accessSavedSearch**
```xml
<splunk.accessSavedSearch>
    <appName>{$ctx:appName}</appName>
    <appUserName>{$ctx:appUserName}</appUserName>
    <earliestTime>{$ctx:earliestTime}</earliestTime>
    <latestTime>{$ctx:latestTime}</latestTime>
    <listDefaultAction>{$ctx:listDefaultAction}</listDefaultAction>
    <orphanField>{$ctx:orphanField}</orphanField>
</splunk.accessSavedSearch>
```

**Properties**
* appName              : Required - Name of the default app
* appUserName          : Required - Owner of the app
* earliestTime         : For scheduled searches display all the scheduled times starting from this time
* latestTime           : For scheduled searches display all the scheduled times until this time
* listDefaultAction    : Indicates whether to list default actions
* orphanField          : Indicates whether response includes a boolean value for each saved search to show whether the search is orphaned, meaning that it has no valid owner


**Sample request**

Following is a sample request that can be handled by the accessSavedSearch operation.

```xml
<accessSavedSearch>
    <username>shevindi</username>
    <password>n@v@nj@n@95</password>
    <hostname>localhost</hostname>
    <port>8089</port>
    <appName>splunkApp</appName>
    <appUserName>shevindi</appUserName>
    <earliestTime></earliestTime>
    <latestTime></latestTime>
    <listDefaultAction>false</listDefaultAction>
    <orphanField>true</orphanField>
</accessSavedSearch>
```

**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTREF/RESTsearch#saved.2Fsearches

####  Update saved search

The updateSavedSearch operation update a named saved search.User can change or edit a saved search since by default a saved search is private to user.

**updateSavedSearch**
```xml
<splunk.updateSavedSearch>
    <appName>{$ctx:appName}</appName>
    <appUserName>{$ctx:appUserName}</appUserName>
    <searchName>{$ctx:searchName}</searchName>
    <searchQuery>{$ctx:searchQuery}</searchQuery>
</splunk.updateSavedSearch>
```
**Properties**
* appName       : Required - Name of the default app
* appUserName   : Required - Owner of the app
* searchName    : Required - Name for the search
* searchQuery   : Required - The search query 

**Sample request**

Following is a sample request that can be handled by the updateSavedSearch operation.

```xml
<updateSavedSearch>
    <username>shevindi</username>
    <password>n@v@nj@n@95</password>
    <hostname>localhost</hostname>
    <port>8089</port>
    <appName>splunkApp</appName>
    <appUserName>shevindi</appUserName>
    <searchName>Mysearch</searchName>
    <searchQuery>search index=_internal source=*/metrics.log</searchQuery>
</updateSavedSearch>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTREF/RESTsearch#saved.2Fsearches.2F.7Bname.7D


####  Share saved search

The shareSavedSearch operation share the saved search and change its permission.

**shareSavedSearch**
```xml
<splunk.shareSavedSearch>
    <appName>{$ctx:appName}</appName>
    <appUserName>{$ctx:appUserName}</appUserName>
    <searchName>{$ctx:searchName}</searchName>
    <enableChangePermission>{$ctx:enableChangePermission}</enableChangePermission>
    <enableAppShare>{$ctx:enableAppShare}</enableAppShare>
    <enableGlobalShare>{$ctx:enableGlobalShare}</enableGlobalShare>
    <enableUserShare>{$ctx:enableUserShare}</enableUserShare>
    <enableEditPermission>{$ctx:enableEditPermission}</enableEditPermission>
    <ownerName>{$ctx:ownerName}</ownerName>
    <readPermissionProperties>{$ctx:readPermissionProperties}</readPermissionProperties>
    <writePermissionProperties>{$ctx:readPermissionProperties}</readPermissionProperties>
    <isRemovable>{$ctx:isRemovable}</isRemovable>
    <resourceShareOption>{$ctx:resourceShareOption}</resourceShareOption>
</splunk.shareSavedSearch>
```
**Properties**
* appName       : Required - Name of the default app
* appUserName   : Required - Owner of the app
* searchName    : Required - Name for the search
* enableChangePermission   : Indicates if the active user can change permissions for this object
* enableAppShare   : Indicates if the active user can change sharing to app level 
* enableGlobalShare   :  Indicates if the active user can change sharing to system level
* enableUserShare   :  Indicates if the active user can change sharing to user level
* enableEditPermission   :  Indicates if the active user can edit this object
* ownerName   :  User name of resource owner
* readPermissionProperties   : Properties that indicate resource read permissions  
* writePermissionProperties  : Properties that indicate write permissions of the resource 
* isRemovable   :  Indicates whether an admin or user with sufficient permissions can delete the entity
* resourceShareOption  :  Indicates how the resource is shared. Required for updating any knowledge object ACL properties

**Sample request**

Following is a sample request that can be handled by the shareSavedSearch operation.

```xml
<shareSavedSearch>
    <username>shevindi</username>
    <password>n@v@nj@n@95</password>
    <hostname>localhost</hostname>
    <port>8089</port>
    <appName>splunkApp</appName>
    <appUserName>shevindi</appUserName>
    <searchName>Mysearch</searchName>
    <enableChangePermission>true</enableChangePermission>
    <enableAppShare>true</enableAppShare>
    <enableGlobalShare>true</enableGlobalShare>
    <enableUserShare>true</enableUserShare>
    <enableEditPermission>true</enableEditPermission>
    <ownerName>nobody</ownerName>
    <readPermissionProperties>*</readPermissionProperties>
    <writePermissionProperties></writePermissionProperties>
    <isRemovable>true</isRemovable>
    <resourceShareOption>app</resourceShareOption>
</shareSavedSearch>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTbasicexamples#Share_an_object_to_an_app.2C_modify_its_permissions.2C_and_edit_it


####  Move saved search

The moveSavedSearch operation make the saved search available for all users and change the context to a different app which was previously available to all in the context of default app.

**moveSavedSearch**
```xml
<splunk.moveSavedSearch>
    <appName>{$ctx:appName}</appName>
    <appUserName>{$ctx:appUserName}</appUserName>
    <searchName>{$ctx:searchName}</searchName>
    <appNameToMove>{$ctx:appNameToMove}</appNameToMove>
</splunk.moveSavedSearch>
```
**Properties**
* appName       : Required - Name of the default app
* appUserName   : Required - Owner of the app
* searchName    : Required - Name for the search
* appNameToMove : Required - The app where the saved search is going to move

**Sample request**

Following is a sample request that can be handled by the moveSavedSearch operation.

```xml
<moveSavedSearch>
    <username>shevindi</username>
    <password>n@v@nj@n@95</password>
    <hostname>localhost</hostname>
    <port>8089</port>
    <appName>splunkApp</appName>
    <appUserName>shevindi</appUserName>
    <searchName>Mysearch</searchName>
    <appNameToMove>splunk</appNameToMove>
</moveSavedSearch>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTbasicexamples#Move_an_object_to_a_different_app

####  Delete saved search

The deleteSavedSearch operation delete a saved search.

**deleteSavedSearch**
```xml
<splunk.deleteSavedSearch>
    <appName>{$ctx:appName}</appName>
    <appUserName>{$ctx:appUserName}</appUserName>
    <searchName>{$ctx:searchName}</searchName>
</splunk.deleteSavedSearch>
```
**Properties**
* appName       : Required - Name of the default app
* appUserName   : Required - Owner of the app
* searchName    : Required - Name for the search

**Sample request**

Following is a sample request that can be handled by the shareSavedSearch operation.

```xml
<deleteSavedSearch>
    <username>shevindi</username>
    <password>n@v@nj@n@95</password>
    <hostname>localhost</hostname>
    <port>8089</port>
    <appName>splunkApp</appName>
    <appUserName>shevindi</appUserName>
    <searchName>Mysearch</searchName>
</deleteSavedSearch>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTREF/RESTsearch#saved.2Fsearches.2F.7Bname.7D

#### Sample configuration

Following example illustrates how to connect to Splunk with the init operation and createSavedSearch operation.

1. Create a sample proxy as below :

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse" name="createSavedSearch"
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
            <property name="searchQuery" expression="//searchQuery/text()"/>
            <property name="searchName" expression="//searchName/text()"/>
            <splunk.init>
                <username>{$ctx:username}</username>
                <password>{$ctx:password}</password>
                <hostname>{$ctx:hostname}</hostname>
                <port>{$ctx:port}</port>
            </splunk.init>
            <splunk.createSavedSearch>
                <appName>{$ctx:appName}</appName>
                <appUserName>{$ctx:appUserName}</appUserName>
                <searchName>{$ctx:searchName}</searchName>
                <searchQuery>{$ctx:searchQuery}</searchQuery>
            </splunk.createSavedSearch>
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
2. Create a xml file named createSavedSearch.xml and copy the configurations given below to it:

```xml
<createSavedSearch>
  <username>shevindi</username>
  <password>n@v@nj@n@95</password>
  <hostname>localhost</hostname>
  <port>8089</port>
  <appName>splunkApp</appName>
  <appUserName>shevindi</appUserName>
  <searchName>Mysearch</searchName>
  <searchQuery>*</searchQuery>
</createSavedSearch>
```
3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8089/services/createSavedSearch -H "Content-Type: application/xml" -d @createSavedSearch.xml
```

5. Splunk returns a xml response similar to the one shown below:
 
```xml

<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>savedsearch</title>
  <id>https://localhost:8089/servicesNS/shevindi/splunkApp/saved/searches</id>
  <updated>2018-12-12T21:57:46+05:30</updated>
  <generator build="be11b2c46e23" version="7.2.1"/>
  <author>
    <name>Splunk</name>
  </author>
  <link href="/servicesNS/shevindi/splunkApp/saved/searches/_new" rel="create"/>
  <link href="/servicesNS/shevindi/splunkApp/saved/searches/_reload" rel="_reload"/>
  <link href="/servicesNS/shevindi/splunkApp/saved/searches/_acl" rel="_acl"/>
  <opensearch:totalResults>1</opensearch:totalResults>
  <opensearch:itemsPerPage>30</opensearch:itemsPerPage>
  <opensearch:startIndex>0</opensearch:startIndex>
  <s:messages/>
  <entry>
    <title>Mysearch</title>
    <id>https://localhost:8089/servicesNS/shevindi/splunkApp/saved/searches/Mysearch</id>
    <updated>2018-12-12T21:57:46+05:30</updated>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch" rel="alternate"/>
    <author>
      <name>shevindi</name>
    </author>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch" rel="list"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch/_reload" rel="_reload"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch" rel="edit"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch" rel="remove"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch/move" rel="move"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch/disable" rel="disable"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch/dispatch" rel="dispatch"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch/embed" rel="embed"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/Mysearch/history" rel="history"/>
 </entry>
</feed>

```
