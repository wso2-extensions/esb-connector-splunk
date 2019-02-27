## Integration Tests: WSO2 EI Splunk Connector for Splunk Enterprise API Version v4.

### Pre-requisites:

    - Maven 3.x
    - Java 1.7 or above
    - The org.wso2.esb.integration.integration-base project is required. The test suite has been configured to download this project automatically. If the automatic download fails, download the following project and compile it using the mvn clean install command to update your local repository:
      https://github.com/wso2-extensions/esb-integration-base

### Tested Platforms:

    - Ubuntu 16.04
    - WSO2 EI 6.4.0
    - java 1.8


### Steps to follow in setting integration test.

1.Create a Splunk account and get Splunk Enterprise software according to below steps:

        i)     Navigate to "https://www.splunk.com/en_us/page/sign_up?redirecturl=" and create an splunk account.
        ii)    Download the Splunk Enterprise for free.
        iii)   To install Splunk Enterprise on a Linux system, expand the tar file into an appropriate directory using the tar command: tar xvzf splunk_package_name.tgz

2.Start the Splunk Enterprise from the command-line interface according to below steps:

        i)    Run the following command from $SPLUNK_HOME/bin directory, where $SPLUNK_HOME is the directory where Splunk is installed:
                "./splunk start"
        ii)   A command line window prompts you to create an administrator password. You will need this password for you initial Splunk Enterprise login.
        iii)  In a browser window, access Splunk Web at http://<hostname>:port.
                           hostname is the host machine.
                           port is the port you specified during the installation.
        iv)   Login using the username and the password that you specified when you installed Splunk Enterprise.

3.Create a Splunk app according to the following steps:

        i)     On the Splunk Web home page, click the Gears icon next to Apps.
        ii)    Click Create app.
        iii)   On the Add new page, fill out the [properties](http://dev.splunk.com/view/webframework-developapps/SP-CAAAEUC) of the new app.
        iv)    Click Save.
4.Download EI 6.4.0  by navigating to the following [URL](http://wso2.com/products/enterprise-service-bus/#).

5.The EI should be configured as below;
       
        i)  Please make sure that the below mentioned Axis configurations are enabled (/repository/conf/axis2/axis2.xml).
         <parameter name="HostnameVerifier">AllowAll</parameter>
        ii)Open a command line terminal and execute the following command from "$SPLUNK_HOME/etc/auth" to import the certificate to  client-truststore.jks:
                        "keytool -importcert -file server.pem -keystore {EI_HOME}/repository/resources/security/client-truststore.jks -alias splunk"
        iii) Deploy relevant patches, if applicable.

6.Compress modified EI as wso2ei-6.4.0.zip and copy that zip file in to location `<CONNECTOR_HOME>/repository/`.

7.Follow the below mentioned steps for adding valid certificate to access Splunk API over https

        i) Copy the certificate "server.pem" by navigating to $SPLUNK_HOME/etc/auth and place the certificate file in following location.
        "{SPLUNK_CONNECTOR_HOME}/src/repository"
        ii)Open a command line terminal and execute the following command from "$SPLUNK_HOME/etc/auth" to import the certificate to  client-truststore.jks and wso2carbon.jks:
            "keytool -importcert -file server.pem -keystore {SPLUNK_CONNECTOR_HOME}/src/test/resources/keystores/products/client-truststore.jks -alias splunk" and "keytool -importcert -file server.pem -keystore {SPLUNK_CONNECTOR_HOME}/src/test/resources/keystores/products/wso2carbon.jks -alias splunk"

8.Update the 'esb-connector-splunk.properties' file at the location "{SPLUNK_CONNECTOR_HOME}/repository" as below

        i)    username                  -		Use the username, step 2->(iii)
        ii)   password                  -		Use the password, step 2->(iii)
        iii)  hostname                  -		Use the hostname, localhost
        iii)  port                      -		Use the management port specified during the start of the Splunk
        iv)   appName                   -       Use the name of the app which is created in step 3
        v)    appUserName               -       Use the author name specified during the creation of the app in step 3
        vi)   appNameToMove             -       The app where the saved search is going to move |
    

9.Update the splunk properties file at location `<Connector_Home>/src/test/resources/artifacts/ESB/connector/config` as below.
<br/><br/>

| Property | Description |
| ------------- |-------------|
| searchName | Name of the search query |
| searchName1 | Name of the search query to move the saved search |
| searchQuery | Search string |
| searchQuery1 | Search string |
| listDefaultAction | Indicates whether to list default actions |
| orphanField | Indicates whether response includes a boolean value for each saved search to show whether the search is orphaned, meaning that it has no valid owner |
| earliestTime | For scheduled searches display all the scheduled times starting from this time |
| latestTime | For scheduled searches display all the scheduled times until this time |
| enableChangePermission | Indicates if the active user can change permissions for this object |
| ownerName | User name of resource owner |
| resourceShareOption | Indicates how the resource is shared |
| enableAppShare | Indicates if the active user can change sharing to app level |
| enableGlobalShare | Indicates if the active user can change sharing to system level |
| enableUserShare | Indicates if the active user can change sharing to user level |
| enableEditPermission | Indicates if the active user can edit this object |
| readPermissionProperties | Properties that indicate resource read permissions |
| writePermissionProperties | Properties that indicate write permissions of the resource |
| isRemovable | Indicates whether an admin or user with sufficient permissions can delete the entity |
| adhocSearchLevel | Use one of the following search modes.verbose,fast,smart |
| maxCount | The number of events that can be accessible in any given status bucket |
| requiredFields | Adds a required field to the search |
| bucketStatus | The most status buckets to generate |
| searchMode | Valid values: (normal | realtime) |
| addSummaryToMetadata | Set the value to 'true' to include field summary statistics in the response |
| resultCount | The maximum number of results to return |
| fields | A field to return for the event set |
| fieldList | Specify a comma-separated list of the fields to return for the event set |
| resultOffset | The first result (inclusive) from which to begin returning data |
| outputFormat | Valid values: (atom | csv | json | json_cols | json_rows | raw | xml) |
| configFileName | Name of the configuration file |
| stanzaName | Name of the stanza |
| stanzaName1 | Name of the stanza |
| dataFields | Arbitrary number of key/value pairs |

 10.Navigate to `{EI_Connector_Home}/` and run the following command.
             `$ mvn clean install -Dskip-tests=false`
