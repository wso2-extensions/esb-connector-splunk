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

#### Create a saved search
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
  <searchName>mysearch</searchName>
  <searchQuery>*</searchQuery>
</createSavedSearch>
```



**Sample Response**

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
    <title>mysearch</title>
    <id>https://localhost:8089/servicesNS/shevindi/splunkApp/saved/searches/mysearch7</id>
    <updated>2018-12-12T21:57:46+05:30</updated>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch" rel="alternate"/>
    <author>
      <name>shevindi</name>
    </author>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch" rel="list"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/_reload" rel="_reload"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch" rel="edit"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch" rel="remove"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/move" rel="move"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/disable" rel="disable"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/dispatch" rel="dispatch"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/embed" rel="embed"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/history" rel="history"/>
    <!-- opensearch nodes elided for brevity. -->
    <content type="text/xml">
      <s:dict>
        <s:key name="action.email">0</s:key>
        <s:key name="action.email.auth_password"></s:key>
        <s:key name="action.email.auth_username"></s:key>
        <s:key name="action.email.bcc"></s:key>
        <s:key name="action.email.cc"></s:key>
        <s:key name="action.email.command"><![CDATA[$action.email.preprocess_results{default=""}$
          | sendemail "server=$action.email.mailserver{default=localhost}$"
          "use_ssl=$action.email.use_ssl{default=false}$"
          "use_tls=$action.email.use_tls{default=false}$" "to=$action.email.to$"
          "cc=$action.email.cc$" "bcc=$action.email.bcc$" "from=$action.email.from{default=splunk@localhost}$"
          "subject=$action.email.subject{recurse=yes}$" "format=$action.email.format{default=csv}$"
          "sssummary=Saved Search [$name$]: $counttype$($results.count$)" "sslink=$results.url$"
          "ssquery=$search$" "ssname=$name$" "inline=$action.email.inline{default=False}$"
          "sendresults=$action.email.sendresults{default=False}$" "sendpdf=$action.email.sendpdf{default=False}$"
          "pdfview=$action.email.pdfview$" "searchid=$search_id$" "graceful=$graceful{default=True}$"
          maxinputs="$action.email.maxresults{default=10000}$" maxtime="$action.email.maxtime{default=5m}$"]]>
        </s:key>
        <s:key name="action.email.format">html</s:key>
        <s:key name="action.email.from">splunk</s:key>
        <s:key name="action.email.hostname"></s:key>
        <s:key name="action.email.inline">0</s:key>
        <s:key name="action.email.mailserver">localhost</s:key>
        <s:key name="action.email.maxresults">10000</s:key>
        <s:key name="action.email.maxtime">5m</s:key>
        <s:key name="action.email.pdfview"></s:key>
        <s:key name="action.email.preprocess_results"></s:key>
        <s:key name="action.email.reportPaperOrientation">portrait</s:key>
        <s:key name="action.email.reportPaperSize">letter</s:key>
        <s:key name="action.email.reportServerEnabled">1</s:key>
        <s:key name="action.email.reportServerURL"></s:key>
        <s:key name="action.email.sendpdf">0</s:key>
        <s:key name="action.email.sendresults">0</s:key>
        <s:key name="action.email.subject">Splunk Alert: $name$</s:key>
        <s:key name="action.email.to"></s:key>
        <s:key name="action.email.track_alert">1</s:key>
        <s:key name="action.email.ttl">86400</s:key>
        <s:key name="action.email.use_ssl">0</s:key>
        <s:key name="action.email.use_tls">0</s:key>
        <s:key name="action.populate_lookup">0</s:key>
        <s:key name="action.populate_lookup.command">copyresults dest="$action.populate_lookup.dest$"  sid="$search_id$"</s:key>
        <s:key name="action.populate_lookup.dest"></s:key>
        <s:key name="action.populate_lookup.hostname"></s:key>
        <s:key name="action.populate_lookup.maxresults">10000</s:key>
        <s:key name="action.populate_lookup.maxtime">5m</s:key>
        <s:key name="action.populate_lookup.track_alert">0</s:key>
        <s:key name="action.populate_lookup.ttl">120</s:key>
        <s:key name="action.rss">0</s:key>
        <s:key name="action.rss.command">createrss "path=$name$.xml" "name=$name$" "link=$results.url$" "descr=Alert trigger:
          $name$, results.count=$results.count$ " "count=30" "graceful=$graceful{default=1}$" maxtime="$action.rss.maxtime{default=1m}$"
        </s:key>
        <s:key name="action.rss.hostname"></s:key>
        <s:key name="action.rss.maxresults">10000</s:key>
        <s:key name="action.rss.maxtime">1m</s:key>
        <s:key name="action.rss.track_alert">0</s:key>
        <s:key name="action.rss.ttl">86400</s:key>
        <s:key name="action.script">0</s:key>
        <s:key name="action.script.command">runshellscript "$action.script.filename$" "$results.count$" "$search$" "$search$" "$name$"
          "Saved Search [$name$] $counttype$($results.count$)" "$results.url$" "$deprecated_arg$" "$search_id$" "$results.file$"
          maxtime="$action.script.maxtime{default=5m}$"
        </s:key>
        <s:key name="action.script.filename"></s:key>
        <s:key name="action.script.hostname"></s:key>
        <s:key name="action.script.maxresults">10000</s:key>
        <s:key name="action.script.maxtime">5m</s:key>
        <s:key name="action.script.track_alert">1</s:key>
        <s:key name="action.script.ttl">600</s:key>
        <s:key name="action.summary_index">0</s:key>
        <s:key name="action.summary_index._name">summary</s:key>
        <s:key name="action.summary_index.command"><![CDATA[summaryindex spool=t uselb=t addtime=t index="$action.summary_index._name{required=yes}$"
          file="$name$_$#random$.stash_new" name="$name$" marker="$action.summary_index*{format=$KEY=\\\"$VAL\\\",
          key_regex="action.summary_index.(?!(?:command|inline|maxresults|maxtime|ttl|track_alert|(?:_.*))$)(.*)"}$"]]>
        </s:key>
        <s:key name="action.summary_index.hostname"></s:key>
        <s:key name="action.summary_index.inline">1</s:key>
        <s:key name="action.summary_index.maxresults">10000</s:key>
        <s:key name="action.summary_index.maxtime">5m</s:key>
        <s:key name="action.summary_index.track_alert">0</s:key>
        <s:key name="action.summary_index.ttl">120</s:key>
        <s:key name="alert.digest_mode">1</s:key>
        <s:key name="alert.expires">24h</s:key>
        <s:key name="alert.severity">3</s:key>
        <s:key name="alert.suppress"></s:key>
        <s:key name="alert.suppress.fields"></s:key>
        <s:key name="alert.suppress.period"></s:key>
        <s:key name="alert.track">auto</s:key>
        <s:key name="alert_comparator"></s:key>
        <s:key name="alert_condition"></s:key>
        <s:key name="alert_threshold"></s:key>
        <s:key name="alert_type">always</s:key>
        <s:key name="cron_schedule"></s:key>
        <s:key name="description"></s:key>
        <s:key name="disabled">0</s:key>
        <s:key name="dispatch.buckets">0</s:key>
        <s:key name="dispatch.earliest_time"></s:key>
        <s:key name="dispatch.latest_time"></s:key>
        <s:key name="dispatch.lookups">1</s:key>
        <s:key name="dispatch.max_count">500000</s:key>
        <s:key name="dispatch.max_time">0</s:key>
        <s:key name="dispatch.reduce_freq">10</s:key>
        <s:key name="dispatch.rt_backfill">0</s:key>
        <s:key name="dispatch.spawn_process">1</s:key>
        <s:key name="dispatch.time_format">%FT%T.%Q%:z</s:key>
        <s:key name="dispatch.ttl">2p</s:key>
        <s:key name="displayview"></s:key>
        <!-- eai:acl elided -->
        <s:key name="is_scheduled">0</s:key>
        <s:key name="is_visible">1</s:key>
        <s:key name="max_concurrent">1</s:key>
        <s:key name="next_scheduled_time"></s:key>
        <s:key name="qualifiedSearch">search  index=_internal source=*metrics.log</s:key>
        <s:key name="realtime_schedule">1</s:key>
        <s:key name="request.ui_dispatch_app"></s:key>
        <s:key name="request.ui_dispatch_view"></s:key>
        <s:key name="restart_on_searchpeer_add">1</s:key>
        <s:key name="run_on_startup">0</s:key>
        <s:key name="search">index=_internal source=*metrics.log</s:key>
        <s:key name="vsid"></s:key>
      </s:dict>
    </content>
  </entry>
</feed>
```


**Related Splunk documentation**
http://docs.splunk.com/Documentation/Splunk/7.2.1/RESTREF/RESTsearch#saved.2Fsearches

####  Access saved searches

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


**Sample Response**

```xml
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>savedsearch</title>
  <id>https://localhost:8089/servicesNS/shevindi/splunkApp/saved/searches</id>
  <updated>2018-12-18T12:53:15+05:30</updated>
  <generator build="be11b2c46e23" version="7.2.1"/>
  <author>
    <name>Splunk</name>
  </author>
  <link href="/servicesNS/shevindi/splunkApp/saved/searches/_new" rel="create"/>
  <link href="/servicesNS/shevindi/splunkApp/saved/searches/_reload" rel="_reload"/>
  <link href="/servicesNS/shevindi/splunkApp/saved/searches/_acl" rel="_acl"/>
  <opensearch:totalResults>21</opensearch:totalResults>
  <opensearch:itemsPerPage>30</opensearch:itemsPerPage>
  <opensearch:startIndex>0</opensearch:startIndex>
  <s:messages/>
  <entry>
    <title>Histogram of delay in seconds</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/saved/searches/Histogram%20of%20delay%20in%20seconds</id>
    <updated>1970-01-01T05:30:00+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/Histogram%20of%20delay%20in%20seconds" rel="alternate"/>
    <author>
      <name>nobody</name>
    </author>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/Histogram%20of%20delay%20in%20seconds" rel="list"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/Histogram%20of%20delay%20in%20seconds/_reload" rel="_reload"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/Histogram%20of%20delay%20in%20seconds" rel="edit"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/Histogram%20of%20delay%20in%20seconds/disable" rel="disable"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/Histogram%20of%20delay%20in%20seconds/dispatch" rel="dispatch"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/Histogram%20of%20delay%20in%20seconds/embed" rel="embed"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/Histogram%20of%20delay%20in%20seconds/history" rel="history"/>
    <content type="text/xml">
      <s:dict>
        <s:key name="qualifiedSearch">search index=sample | fields + delay, count | convert dur2sec(delay) | sort - delay | top delay limit=10</s:key>
      </s:dict>
    </content>
  </entry>
  <entry>
      <title>Top recipients by mailer - area chart</title>
      <id>https://localhost:8089/servicesNS/nobody/splunkApp/saved/searches/Top%20recipients%20by%20mailer%20-%20area%20chart</id>
      <updated>1970-01-01T05:30:00+05:30</updated>
      <link href="/servicesNS/nobody/splunkApp/saved/searches/Top%20recipients%20by%20mailer%20-%20area%20chart" rel="alternate"/>
      <author>
        <name>nobody</name>
      </author>
      <link href="/servicesNS/nobody/splunkApp/saved/searches/Top%20recipients%20by%20mailer%20-%20area%20chart" rel="list"/>
      <link href="/servicesNS/nobody/splunkApp/saved/searches/Top%20recipients%20by%20mailer%20-%20area%20chart/_reload" rel="_reload"/>
      <link href="/servicesNS/nobody/splunkApp/saved/searches/Top%20recipients%20by%20mailer%20-%20area%20chart" rel="edit"/>
      <link href="/servicesNS/nobody/splunkApp/saved/searches/Top%20recipients%20by%20mailer%20-%20area%20chart/disable" rel="disable"/>
      <link href="/servicesNS/nobody/splunkApp/saved/searches/Top%20recipients%20by%20mailer%20-%20area%20chart/dispatch" rel="dispatch"/>
      <link href="/servicesNS/nobody/splunkApp/saved/searches/Top%20recipients%20by%20mailer%20-%20area%20chart/embed" rel="embed"/>
      <link href="/servicesNS/nobody/splunkApp/saved/searches/Top%20recipients%20by%20mailer%20-%20area%20chart/history" rel="history"/>
      <content type="text/xml">
        <s:dict>
          <s:key name="qualifiedSearch">search index=sample | timechart count(to) by mailer</s:key>
        </s:dict>
      </content>
    </entry>
    .......
</feed>
```

**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTREF/RESTsearch#saved.2Fsearches

####  Update a saved search

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
    <searchName>mysearch</searchName>
    <searchQuery>search index=_internal source=*/metrics.log</searchQuery>
</updateSavedSearch>
```


**Sample Response**

```xml
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>savedsearch</title>
  <id>https://localhost:8089/servicesNS/shevindi/splunkApp/saved/searches</id>
  <updated>2018-12-12T23:55:59+05:30</updated>
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
    <title>mysearch</title>
    <id>https://localhost:8089/servicesNS/shevindi/splunkApp/saved/searches/mysearch</id>
    <updated>2018-12-12T23:55:59+05:30</updated>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch" rel="alternate"/>
    <author>
      <name>shevindi</name>
    </author>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch" rel="list"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/_reload" rel="_reload"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch" rel="edit"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch" rel="remove"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/move" rel="move"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/disable" rel="disable"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/dispatch" rel="dispatch"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/embed" rel="embed"/>
    <link href="/servicesNS/shevindi/splunkApp/saved/searches/mysearch/history" rel="history"/>
<!-- opensearch nodes elided for brevity. -->
    <content type="text/xml">
      <s:dict>
        <s:key name="action.email">0</s:key>
        <s:key name="action.email.auth_password"></s:key>
        <s:key name="action.email.auth_username"></s:key>
        <s:key name="action.email.bcc"></s:key>
        <s:key name="action.email.cc"></s:key>
        <s:key name="action.email.command"><![CDATA[$action.email.preprocess_results{default=""}$
          | sendemail "server=$action.email.mailserver{default=localhost}$"
          "use_ssl=$action.email.use_ssl{default=false}$"
          "use_tls=$action.email.use_tls{default=false}$" "to=$action.email.to$"
          "cc=$action.email.cc$" "bcc=$action.email.bcc$" "from=$action.email.from{default=splunk@localhost}$"
          "subject=$action.email.subject{recurse=yes}$" "format=$action.email.format{default=csv}$"
          "sssummary=Saved Search [$name$]: $counttype$($results.count$)" "sslink=$results.url$"
          "ssquery=$search$" "ssname=$name$" "inline=$action.email.inline{default=False}$"
          "sendresults=$action.email.sendresults{default=False}$" "sendpdf=$action.email.sendpdf{default=False}$"
          "pdfview=$action.email.pdfview$" "searchid=$search_id$" "graceful=$graceful{default=True}$"
          maxinputs="$action.email.maxresults{default=10000}$" maxtime="$action.email.maxtime{default=5m}$"]]>
        </s:key>
        <s:key name="action.email.format">html</s:key>
        <s:key name="action.email.from">splunk</s:key>
        <s:key name="action.email.hostname"></s:key>
        <s:key name="action.email.inline">0</s:key>
        <s:key name="action.email.mailserver">localhost</s:key>
        <s:key name="action.email.maxresults">10000</s:key>
        <s:key name="action.email.maxtime">5m</s:key>
        <s:key name="action.email.pdfview"></s:key>
        <s:key name="action.email.preprocess_results"></s:key>
        <s:key name="action.email.reportPaperOrientation">portrait</s:key>
        <s:key name="action.email.reportPaperSize">letter</s:key>
        <s:key name="action.email.reportServerEnabled">1</s:key>
        <s:key name="action.email.reportServerURL"></s:key>
        <s:key name="action.email.sendpdf">0</s:key>
        <s:key name="action.email.sendresults">0</s:key>
        <s:key name="action.email.subject">Splunk Alert: $name$</s:key>
        <s:key name="action.email.to"></s:key>
        <s:key name="action.email.track_alert">1</s:key>
        <s:key name="action.email.ttl">86400</s:key>
        <s:key name="action.email.use_ssl">0</s:key>
        <s:key name="action.email.use_tls">0</s:key>
        <s:key name="action.populate_lookup">0</s:key>
        <s:key name="action.populate_lookup.command">copyresults dest="$action.populate_lookup.dest$"  sid="$search_id$"</s:key>
        <s:key name="action.populate_lookup.dest"></s:key>
        <s:key name="action.populate_lookup.hostname"></s:key>
        <s:key name="action.populate_lookup.maxresults">10000</s:key>
        <s:key name="action.populate_lookup.maxtime">5m</s:key>
        <s:key name="action.populate_lookup.track_alert">0</s:key>
        <s:key name="action.populate_lookup.ttl">120</s:key>
        <s:key name="action.rss">0</s:key>
        <s:key name="action.rss.command">createrss "path=$name$.xml" "name=$name$" "link=$results.url$" "descr=Alert trigger:
          $name$, results.count=$results.count$ " "count=30" "graceful=$graceful{default=1}$" maxtime="$action.rss.maxtime{default=1m}$"
        </s:key>
        <s:key name="action.rss.hostname"></s:key>
        <s:key name="action.rss.maxresults">10000</s:key>
        <s:key name="action.rss.maxtime">1m</s:key>
        <s:key name="action.rss.track_alert">0</s:key>
        <s:key name="action.rss.ttl">86400</s:key>
        <s:key name="action.script">0</s:key>
        <s:key name="action.script.command">runshellscript "$action.script.filename$" "$results.count$" "$search$" "$search$" "$name$"
          "Saved Search [$name$] $counttype$($results.count$)" "$results.url$" "$deprecated_arg$" "$search_id$" "$results.file$"
          maxtime="$action.script.maxtime{default=5m}$"
        </s:key>
        <s:key name="action.script.filename"></s:key>
        <s:key name="action.script.hostname"></s:key>
        <s:key name="action.script.maxresults">10000</s:key>
        <s:key name="action.script.maxtime">5m</s:key>
        <s:key name="action.script.track_alert">1</s:key>
        <s:key name="action.script.ttl">600</s:key>
        <s:key name="action.summary_index">0</s:key>
        <s:key name="action.summary_index._name">summary</s:key>
        <s:key name="action.summary_index.command"><![CDATA[summaryindex spool=t uselb=t addtime=t index="$action.summary_index._name{required=yes}$"
          file="$name$_$#random$.stash_new" name="$name$" marker="$action.summary_index*{format=$KEY=\\\"$VAL\\\",
          key_regex="action.summary_index.(?!(?:command|inline|maxresults|maxtime|ttl|track_alert|(?:_.*))$)(.*)"}$"]]>
        </s:key>
        <s:key name="action.summary_index.hostname"></s:key>
        <s:key name="action.summary_index.inline">1</s:key>
        <s:key name="action.summary_index.maxresults">10000</s:key>
        <s:key name="action.summary_index.maxtime">5m</s:key>
        <s:key name="action.summary_index.track_alert">0</s:key>
        <s:key name="action.summary_index.ttl">120</s:key>
        <s:key name="alert.digest_mode">1</s:key>
        <s:key name="alert.expires">24h</s:key>
        <s:key name="alert.severity">3</s:key>
        <s:key name="alert.suppress"></s:key>
        <s:key name="alert.suppress.fields"></s:key>
        <s:key name="alert.suppress.period"></s:key>
        <s:key name="alert.track">auto</s:key>
        <s:key name="alert_comparator"></s:key>
        <s:key name="alert_condition"></s:key>
        <s:key name="alert_threshold"></s:key>
        <s:key name="alert_type">always</s:key>
        <s:key name="cron_schedule"></s:key>
        <s:key name="description"></s:key>
        <s:key name="disabled">0</s:key>
        <s:key name="dispatch.buckets">0</s:key>
        <s:key name="dispatch.earliest_time"></s:key>
        <s:key name="dispatch.latest_time"></s:key>
        <s:key name="dispatch.lookups">1</s:key>
        <s:key name="dispatch.max_count">500000</s:key>
        <s:key name="dispatch.max_time">0</s:key>
        <s:key name="dispatch.reduce_freq">10</s:key>
        <s:key name="dispatch.rt_backfill">0</s:key>
        <s:key name="dispatch.spawn_process">1</s:key>
        <s:key name="dispatch.time_format">%FT%T.%Q%:z</s:key>
        <s:key name="dispatch.ttl">2p</s:key>
        <s:key name="displayview"></s:key>
        <!-- eai:acl elided -->
        <s:key name="is_scheduled">0</s:key>
        <s:key name="is_visible">1</s:key>
        <s:key name="max_concurrent">1</s:key>
        <s:key name="next_scheduled_time"></s:key>
        <s:key name="qualifiedSearch">search  index=_internal source=*metrics.log</s:key>
        <s:key name="realtime_schedule">1</s:key>
        <s:key name="request.ui_dispatch_app"></s:key>
        <s:key name="request.ui_dispatch_view"></s:key>
        <s:key name="restart_on_searchpeer_add">1</s:key>
        <s:key name="run_on_startup">0</s:key>
        <s:key name="search">index=_internal source=*metrics.log</s:key>
        <s:key name="vsid"></s:key>
      </s:dict>
    </content>
  </entry>
</feed>
```

**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTREF/RESTsearch#saved.2Fsearches.2F.7Bname.7D


####  Share a saved search

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
    <searchName>mysearch</searchName>
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


**Sample Response**

```xml
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>savedsearch</title>
  <id>https://localhost:8089/servicesNS/shevindi/splunkApp/saved/searches</id>
  <updated>2018-12-13T11:34:21+05:30</updated>
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
    <title>mysearch2</title>
    <id>https://localhost:8089/servicesNS/nobody/splunkApp/saved/searches/mysearch2</id>
    <updated>2018-12-13T11:34:21+05:30</updated>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch" rel="alternate"/>
    <author>
      <name>navanjana</name>
    </author>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch" rel="list"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch/_reload" rel="_reload"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch" rel="edit"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch" rel="remove"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch/move" rel="move"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch/disable" rel="disable"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch/dispatch" rel="dispatch"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch/embed" rel="embed"/>
    <link href="/servicesNS/nobody/splunkApp/saved/searches/mysearch/history" rel="history"/>
    <content type="text/xml">
      <s:dict>
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
            <s:key name="owner">navanjana</s:key>
            <s:key name="perms">
              <s:dict>
                <s:key name="read">
                  <s:list>
                    <s:item>*</s:item>
                  </s:list>
                </s:key>
              </s:dict>
            </s:key>
            <s:key name="removable">1</s:key>
            <s:key name="sharing">app</s:key>
          </s:dict>
        </s:key>
      </s:dict>
    </content>
  </entry>
</feed>
```

**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTbasicexamples#Share_an_object_to_an_app.2C_modify_its_permissions.2C_and_edit_it


####  Move a saved search

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
    <searchName>mysearch</searchName>
    <appNameToMove>splunk</appNameToMove>
</moveSavedSearch>
```


**Sample Response**

```xml
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>savedsearch</title>
  <id>https://localhost:8089/servicesNS/nobody/splunkApp/saved/searches</id>
  <updated>2018-12-13T12:04:02+05:30</updated>
  <generator build="be11b2c46e23" version="7.2.1"/>
  <author>
    <name>Splunk</name>
  </author>
  <link href="/servicesNS/nobody/splunkApp/saved/searches/_new" rel="create"/>
  <link href="/servicesNS/nobody/splunkApp/saved/searches/_reload" rel="_reload"/>
  <link href="/servicesNS/nobody/splunkApp/saved/searches/_acl" rel="_acl"/>
  <opensearch:totalResults>1</opensearch:totalResults>
  <opensearch:itemsPerPage>30</opensearch:itemsPerPage>
  <opensearch:startIndex>0</opensearch:startIndex>
  <s:messages/>
  <entry>
    <title>mysearch</title>
    <id>https://localhost:8089/servicesNS/nobody/splunk/saved/searches/mysearch2</id>
    <updated>2018-12-13T11:34:21+05:30</updated>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch" rel="alternate"/>
    <author>
      <name>nobody</name>
    </author>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch" rel="list"/>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch/_reload" rel="_reload"/>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch" rel="edit"/>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch" rel="remove"/>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch/move" rel="move"/>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch/disable" rel="disable"/>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch/dispatch" rel="dispatch"/>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch/embed" rel="embed"/>
    <link href="/servicesNS/nobody/splunk/saved/searches/mysearch2/history" rel="history"/>
    <!-- opensearch nodes elided for brevity. -->
    <content type="text/xml">
      <s:dict>
        <s:key name="action.email">0</s:key>
        <s:key name="action.email.auth_password"></s:key>
        <s:key name="action.email.auth_username"></s:key>
        <s:key name="action.email.bcc"></s:key>
        <s:key name="action.email.cc"></s:key>
        <s:key name="action.email.command"><![CDATA[$action.email.preprocess_results{default=""}$
          | sendemail "server=$action.email.mailserver{default=localhost}$"
          "use_ssl=$action.email.use_ssl{default=false}$"
          "use_tls=$action.email.use_tls{default=false}$" "to=$action.email.to$"
          "cc=$action.email.cc$" "bcc=$action.email.bcc$" "from=$action.email.from{default=splunk@localhost}$"
          "subject=$action.email.subject{recurse=yes}$" "format=$action.email.format{default=csv}$"
          "sssummary=Saved Search [$name$]: $counttype$($results.count$)" "sslink=$results.url$"
          "ssquery=$search$" "ssname=$name$" "inline=$action.email.inline{default=False}$"
          "sendresults=$action.email.sendresults{default=False}$" "sendpdf=$action.email.sendpdf{default=False}$"
          "pdfview=$action.email.pdfview$" "searchid=$search_id$" "graceful=$graceful{default=True}$"
          maxinputs="$action.email.maxresults{default=10000}$" maxtime="$action.email.maxtime{default=5m}$"]]>
        </s:key>
        <s:key name="action.email.format">html</s:key>
        <s:key name="action.email.from">splunk</s:key>
        <s:key name="action.email.hostname"></s:key>
        <s:key name="action.email.inline">0</s:key>
        <s:key name="action.email.mailserver">localhost</s:key>
        <s:key name="action.email.maxresults">10000</s:key>
        <s:key name="action.email.maxtime">5m</s:key>
        <s:key name="action.email.pdfview"></s:key>
        <s:key name="action.email.preprocess_results"></s:key>
        <s:key name="action.email.reportPaperOrientation">portrait</s:key>
        <s:key name="action.email.reportPaperSize">letter</s:key>
        <s:key name="action.email.reportServerEnabled">1</s:key>
        <s:key name="action.email.reportServerURL"></s:key>
        <s:key name="action.email.sendpdf">0</s:key>
        <s:key name="action.email.sendresults">0</s:key>
        <s:key name="action.email.subject">Splunk Alert: $name$</s:key>
        <s:key name="action.email.to"></s:key>
        <s:key name="action.email.track_alert">1</s:key>
        <s:key name="action.email.ttl">86400</s:key>
        <s:key name="action.email.use_ssl">0</s:key>
        <s:key name="action.email.use_tls">0</s:key>
        <s:key name="action.populate_lookup">0</s:key>
        <s:key name="action.populate_lookup.command">copyresults dest="$action.populate_lookup.dest$"  sid="$search_id$"</s:key>
        <s:key name="action.populate_lookup.dest"></s:key>
        <s:key name="action.populate_lookup.hostname"></s:key>
        <s:key name="action.populate_lookup.maxresults">10000</s:key>
        <s:key name="action.populate_lookup.maxtime">5m</s:key>
        <s:key name="action.populate_lookup.track_alert">0</s:key>
        <s:key name="action.populate_lookup.ttl">120</s:key>
        <s:key name="action.rss">0</s:key>
        <s:key name="action.rss.command">createrss "path=$name$.xml" "name=$name$" "link=$results.url$" "descr=Alert trigger:
          $name$, results.count=$results.count$ " "count=30" "graceful=$graceful{default=1}$" maxtime="$action.rss.maxtime{default=1m}$"
        </s:key>
        <s:key name="action.rss.hostname"></s:key>
        <s:key name="action.rss.maxresults">10000</s:key>
        <s:key name="action.rss.maxtime">1m</s:key>
        <s:key name="action.rss.track_alert">0</s:key>
        <s:key name="action.rss.ttl">86400</s:key>
        <s:key name="action.script">0</s:key>
        <s:key name="action.script.command">runshellscript "$action.script.filename$" "$results.count$" "$search$" "$search$" "$name$"
          "Saved Search [$name$] $counttype$($results.count$)" "$results.url$" "$deprecated_arg$" "$search_id$" "$results.file$"
          maxtime="$action.script.maxtime{default=5m}$"
        </s:key>
        <s:key name="action.script.filename"></s:key>
        <s:key name="action.script.hostname"></s:key>
        <s:key name="action.script.maxresults">10000</s:key>
        <s:key name="action.script.maxtime">5m</s:key>
        <s:key name="action.script.track_alert">1</s:key>
        <s:key name="action.script.ttl">600</s:key>
        <s:key name="action.summary_index">0</s:key>
        <s:key name="action.summary_index._name">summary</s:key>
        <s:key name="action.summary_index.command"><![CDATA[summaryindex spool=t uselb=t addtime=t index="$action.summary_index._name{required=yes}$"
          file="$name$_$#random$.stash_new" name="$name$" marker="$action.summary_index*{format=$KEY=\\\"$VAL\\\",
          key_regex="action.summary_index.(?!(?:command|inline|maxresults|maxtime|ttl|track_alert|(?:_.*))$)(.*)"}$"]]>
        </s:key>
        <s:key name="action.summary_index.hostname"></s:key>
        <s:key name="action.summary_index.inline">1</s:key>
        <s:key name="action.summary_index.maxresults">10000</s:key>
        <s:key name="action.summary_index.maxtime">5m</s:key>
        <s:key name="action.summary_index.track_alert">0</s:key>
        <s:key name="action.summary_index.ttl">120</s:key>
        <s:key name="alert.digest_mode">1</s:key>
        <s:key name="alert.expires">24h</s:key>
        <s:key name="alert.severity">3</s:key>
        <s:key name="alert.suppress"></s:key>
        <s:key name="alert.suppress.fields"></s:key>
        <s:key name="alert.suppress.period"></s:key>
        <s:key name="alert.track">auto</s:key>
        <s:key name="alert_comparator"></s:key>
        <s:key name="alert_condition"></s:key>
        <s:key name="alert_threshold"></s:key>
        <s:key name="alert_type">always</s:key>
        <s:key name="cron_schedule"></s:key>
        <s:key name="description"></s:key>
        <s:key name="disabled">0</s:key>
        <s:key name="dispatch.buckets">0</s:key>
        <s:key name="dispatch.earliest_time"></s:key>
        <s:key name="dispatch.latest_time"></s:key>
        <s:key name="dispatch.lookups">1</s:key>
        <s:key name="dispatch.max_count">500000</s:key>
        <s:key name="dispatch.max_time">0</s:key>
        <s:key name="dispatch.reduce_freq">10</s:key>
        <s:key name="dispatch.rt_backfill">0</s:key>
        <s:key name="dispatch.spawn_process">1</s:key>
        <s:key name="dispatch.time_format">%FT%T.%Q%:z</s:key>
        <s:key name="dispatch.ttl">2p</s:key>
        <s:key name="displayview"></s:key>
        <!-- eai:acl elided -->
        <s:key name="is_scheduled">0</s:key>
        <s:key name="is_visible">1</s:key>
        <s:key name="max_concurrent">1</s:key>
        <s:key name="next_scheduled_time"></s:key>
        <s:key name="qualifiedSearch">search  index=_internal source=*metrics.log</s:key>
        <s:key name="realtime_schedule">1</s:key>
        <s:key name="request.ui_dispatch_app"></s:key>
        <s:key name="request.ui_dispatch_view"></s:key>
        <s:key name="restart_on_searchpeer_add">1</s:key>
        <s:key name="run_on_startup">0</s:key>
        <s:key name="search">index=_internal source=*metrics.log</s:key>
        <s:key name="vsid"></s:key>
      </s:dict>
    </content>
  </entry>
</feed>
```
**Related Splunk documentation**
https://docs.splunk.com/Documentation/Splunk/7.2.1/RESTTUT/RESTbasicexamples#Move_an_object_to_a_different_app

####  Delete a saved search

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


**Sample Response**

```xml
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:s="http://dev.splunk.com/ns/rest" xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/">
  <title>savedsearch</title>
  <id>https://localhost:8089/servicesNS/shevindi/splunkApp/saved/searches</id>
  <updated>2018-12-13T00:24:02+05:30</updated>
  <generator build="be11b2c46e23" version="7.2.1"/>
  <author>
    <name>Splunk</name>
  </author>
  <link href="/servicesNS/shevindi/splunkApp/saved/searches/_new" rel="create"/>
  <link href="/servicesNS/shevindi/splunkApp/saved/searches/_reload" rel="_reload"/>
  <link href="/servicesNS/shevindi/splunkApp/saved/searches/_acl" rel="_acl"/>
  <opensearch:totalResults>0</opensearch:totalResults>
  <opensearch:itemsPerPage>30</opensearch:itemsPerPage>
  <opensearch:startIndex>0</opensearch:startIndex>
  <s:messages/>
</feed>
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
