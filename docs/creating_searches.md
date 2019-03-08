# Working with Search Jobs

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 

The following operations allow you to work with searches. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with searches, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [createSearchJob](#create-a-search-job)    | Start a new search. |
| [accessSearchJob](#access-a-search-job)      | Access the information of the {search_id} search job. |
| [getSearchResults](#get-search-results)      | Retrieve your search results. |

### Operation details

This section provides more details on each of the operations.

#### Create a search job
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


**Sample Response**

```xml
<response>
  <sid>1544769196.13</sid>
</response>
```

**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTsearches

####  Access a search job

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
  <searchId>1544770001.15</searchId>
</accessSearchJob>
```

**Sample Response**

```xml
<?xml-stylesheet type="text/xml" href="/static/atom.xsl"?>
<entry xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>search index=_internal source=*/metrics.log</title>
  <id>https://localhost:8089/services/search/jobs/1544770001.15</id>
  <updated>2018-12-14T12:17:51.899+05:30</updated>
  <link href="/services/search/jobs/1544770001.15" rel="alternate"/>
  <published>2018-12-14T12:16:41.000+05:30</published>
  <link href="/services/search/jobs/1544770001.15/search.log" rel="search.log"/>
  <link href="/services/search/jobs/1544770001.15/events" rel="events"/>
  <link href="/services/search/jobs/1544770001.15/results" rel="results"/>
  <link href="/services/search/jobs/1544770001.15/results_preview" rel="results_preview"/>
  <link href="/services/search/jobs/1544770001.15/timeline" rel="timeline"/>
  <link href="/services/search/jobs/1544770001.15/summary" rel="summary"/>
  <link href="/services/search/jobs/1544770001.15/control" rel="control"/>
  <author>
    <name>shevindi</name>
  </author>
  <content type="text/xml">
    <s:dict>
      <s:key name="canSummarize">0</s:key>
      <s:key name="cursorTime">1970-01-01T05:30:00.000+05:30</s:key>
      <s:key name="defaultSaveTTL">604800</s:key>
      <s:key name="defaultTTL">600</s:key>
      <s:key name="delegate"></s:key>
      <s:key name="diskUsage">2691072</s:key>
      <s:key name="dispatchState">DONE</s:key>
      <s:key name="doneProgress">1.00000</s:key>
      <s:key name="dropCount">0</s:key>
      <s:key name="earliestTime">2018-12-06T12:59:33.000+05:30</s:key>
      <s:key name="eventAvailableCount">50000</s:key>
      <s:key name="eventCount">53024</s:key>
      <s:key name="eventFieldCount">6</s:key>
      .............
    </s:dict>
  </content>
</entry>
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


**Sample Response**

```json

{ "init_offset" : 0,
  "messages" : [ { "text" : "base lispy: [ AND index::_internal source::*/metrics.log ]",
        "type" : "DEBUG"
      },
      { "text" : "search context: user=\"admin\", app=\"search\", bs-pathname=\"/Applications/splunk/etc\"",
        "type" : "DEBUG"
      }
    ],
  "preview" : false,
  "results" : [ { "index" : "_internal",
        "source" : "/Applications/splunk/var/log/splunk/metrics.log",
        "sourcetype" : "splunkd"
      },
      { "index" : "_internal",
        "source" : "/Applications/splunk/var/log/splunk/metrics.log",
        "sourcetype" : "splunkd"
      },
      { "index" : "_internal",
        "source" : "/Applications/splunk/var/log/splunk/metrics.log",
        "sourcetype" : "splunkd"
      }
    ]
}
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
