# Working with Search Jobs

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 

The following operations allow you to work with searches. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with searches, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [createSearchJob](#create-a-search-job)    | Start a new search. |
| [accessSearchJob](#access-a-searche-job)      | Access the information of the {search_id} search job. |
| [getSearchResults](#get-search-results)      | Retrieve your search results. |

### Operation details

This section provides more details on each of the operations.

#### Creating a search job
The createSearchjob operation starts a new search.

**createSearchJob**
```xml
<splunk.createSearchJob>
    <searchQuery>{$ctx:searchQuery}</searchQuery>
    <adhocSearchLevel>{$ctx:adhocSearchLevel}</adhocSearchLevel>
    <searchId>{$ctx:searchId}</searchId>
    <maxCount>{$ctx:maxCount}</maxCount>
    <requiredFields>{$ctx:requiredFields}</requiredFields>
    <bucketStatus>{$ctx:bucketStatus}</bucketStatus>
    <searchMode>{$ctx:searchMode}</searchMode>
</splunk.createSearchJob>
```

**Properties**
* searchQuery       : Required - The search query
* adhocSearchLevel  : Use one of the following search modes.verbose,fast,smart
* searchId          : Optional string to specify the search ID
* maxCount          : The number of events that can be accessible in any given status bucket
* requiredFields    : Adds a required field to the search
* bucketStatus      : The most status buckets to generate
* searchMode        : Valid values: (normal | realtime)
 
**Sample request**

Following is a sample request that can be handled by the createSearchJob operation.

```xml
<createSearchJob>
  <username>shevindi</username>
  <password>n@v@nj@n@95</password>
  <hostname>localhost</hostname>
  <port>8089</port>
  <searchQuery>search index=_internal source=*/metrics.log</searchQuery>
  <adhocSearchLevel>verbose</adhocSearchLevel>
  <searchId></searchId>
  <maxCount>50000</maxCount>
  <requiredFields></requiredFields>
  <bucketStatus>300</bucketStatus>
  <searchMode>normal</searchMode>
</createSearchJob>
```

**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTsearches

####  Accessing a search job

The accessSearchJob operation allows you to access the information of the specific search job
**accessSearchJob**
```xml
<splunk.accessSearchJob>
    <searchId>{$ctx:searchId}</searchId>
</splunk.accessSearchJob>
```

**Properties**
* searchId          : Required - String to specify the search ID

**Sample request**

Following is a sample request that can be handled by the accessSearchJob operation.

```xml
<accessSearchJob>
  <username>shevindi</username>
  <password>n@v@nj@n@95</password>
  <hostname>localhost</hostname>
  <port>8089</port>
  <searchId>1549134185.31</searchId>
</accessSearchJob>
```

**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTsearches#Check_status_of_a_search

####  Get search results

The getSearchResults operation retrieve your search results. 

**getSearchResults**
```xml
<splunk.getSearchResults>
    <searchQuery>{$ctx:searchQuery}</searchQuery>
    <addSummaryToMetadata>{$ctx:addSummaryToMetadata}</addSummaryToMetadata>
    <searchId>{$ctx:searchId}</searchId>
    <resultCount>{$ctx:resultCount}</resultCount>
    <fields>{$ctx:fields}</fields>
    <fieldList>{$ctx:fieldList}</fieldList>
    <resultOffset>{$ctx:resultOffset}</resultOffset>
    <outputFormat>{$ctx:outputFormat}</outputFormat>
</splunk.getSearchResults>
```
**Properties**

* searchQuery   : The search query 
* searchId      : Required - String to specify the search ID 
* addSummaryToMetadata   : Set the value to 'true' to include field summary statistics in the response 
* resultCount   : The maximum number of results to return 
* fields        : A field to return for the event set 
* fieldList     : Specify a comma-separated list of the fields to return for the event set 
* resultOffset  : The first result (inclusive) from which to begin returning data 
* outputFormat  : Specifies the format for the returned output 


**Sample request**

Following is a sample request that can be handled by the getSearchResults operation.

```xml
<getSearchResults>
    <username>shevindi</username>
    <password>n@v@nj@n@95</password>
    <hostname>localhost</hostname>
    <port>8089</port>
    <searchQuery>search index=_internal source=*/metrics.log</searchQuery>
    <addSummaryToMetadata>true</addSummaryToMetadata>
    <searchId>1549134185.31</searchId>
    <resultCount>3</resultCount>
    <fields>index</fields>
    <fieldList>sourcetype,source</fieldList>
    <resultOffset>0</resultOffset>
    <outputFormat>json</outputFormat>
</getSearchResults>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTsearches#Get_search_results


#### Sample configuration

Following example illustrates how to connect to Splunk with the init operation and createSearchJob operation.

1. Create a sample proxy as below :

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse" name="createSearchJob"
       transports="https,http" statistics="disable" trace="disable"
       startOnLoad="true">
    <target>
        <inSequence>
                <property name="username" expression="//username/text()"/>
                <property name="password" expression="//password/text()"/>
                <property name="hostname" expression="//hostname/text()"/>
                <property name="port" expression="//port/text()"/>
                <property name="searchQuery" expression="//searchQuery/text()"/>
                <property name="adhocSearchLevel" expression="//adhocSearchLevel/text()"/>
                <property name="searchId" expression="//searchId/text()"/>
                <property name="maxCount" expression="/maxCount/text()"/>
                <property name="requiredFields" expression="//requiredFields/text()"/>
                <property name="bucketStatus" expression="//bucketStatus/text()"/>
                <property name="searchMode" expression="//searchMode/text()"/>
            <splunk.init>
                <username>{$ctx:username}</username>
                <password>{$ctx:password}</password>
                <hostname>{$ctx:hostname}</hostname>
                <port>{$ctx:port}</port>
            </splunk.init>
            <splunk.createSearchJob>
                <searchQuery>{$ctx:searchQuery}</searchQuery>
                <adhocSearchLevel>{$ctx:adhocSearchLevel}</adhocSearchLevel>
                <searchId>{$ctx:searchId}</searchId>
                <maxCount>{$ctx:maxCount}</maxCount>
                <requiredFields>{$ctx:requiredFields}</requiredFields>
                <bucketStatus>{$ctx:bucketStatus}</bucketStatus>
                <searchMode>{$ctx:searchMode}</searchMode>
            </splunk.createSearchJob>
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
2. Create a xml file named createSearchjob.xml and copy the configurations given below to it:

```xml
<createSearchJob>
  <username>shevindi</username>
  <password>n@v@nj@n@95</password>
  <hostname>localhost</hostname>
  <port>8089</port>
  <searchQuery>search index=_internal source=*/metrics.log</searchQuery>
  <adhocSearchLevel>verbose</adhocSearchLevel>
  <searchId></searchId>
  <maxCount>50000</maxCount>
  <requiredFields></requiredFields>
  <bucketStatus>300</bucketStatus>
  <searchMode>normal</searchMode>
</createSearchJob>
```
3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8089/services/createSearchJob -H "Content-Type: application/xml" -d @createSearchJob.xml
```

5. Splunk returns a xml response similar to the one shown below:
 
```xml
<response>
  <sid>1549134185.31</sid>
</response>
```
