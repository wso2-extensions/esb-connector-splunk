## Configuring Splunk Operations


[[Prerequisites]](#Prerequisites) [[Initializing the Connector]](#initializing-the-connector)

## Prerequisites

> NOTE: To work with the Splunk connector, you need to have a Splunk account and have to install the Splunk Enterprise software. If you do not have a Splunk account, go to [https://www.splunk.com/page/sign_up?redirecturl=https://www.splunk.com/](https://www.splunk.com/page/sign_up?redirecturl=https://www.splunk.com/) and create a Splunk account.
Download Splunk Enterprise for free and follow this [installation guide](https://docs.splunk.com/Documentation/Splunk/7.2.3/Installation) to install the splunk and follow the [documentation](https://docs.splunk.com/Documentation/Splunk/7.2.3/Installation/StartSplunkforthefirsttime) to start the splunk for the first time.


To use the Splunk connector, add the <splunk.init> element in your configuration before carrying out any other Splunk operations.

Splunk uses basic access authentication where user provides username and password that is specified during the installation of  Splunk Enterprise.

### Importing the Splunk Certificate

To access Splunk rest API over https, we have to import the self sign certificate.
 Follow the instructions below to import your Splunk certificate:

1. Copy the certificate "server.pem" by navigating to $SPLUNK_HOME/etc/auth . 
2. Place the certificate file in <EI_HOME>/repository/resources/security.
3. Open a command line terminal and execute the following command from <EI_HOME>/repository/resources/security location to import Splunk certificate into the keystore.
   Provide **wso2carbon** as the password.
   
```
    keytool -importcert -file server.pem -keystore client-truststore.jks -alias splunk
```

## Initializing the Connector
Specify the init method as follows:

**init**
```xml
<splunk.init>
    <username>{$ctx:username}</username>
    <password>{$ctx:password}</password>
    <hostname>{$ctx:hostname}</hostname>
    <port>{$ctx:port}</port>
</splunk.init>
```
**Properties**
* username: The username used for authentication.
* password: The password used for authentication.
* hostname: Use the hostname as localhost.
* port    : Use the management port specified during the start of the Splunk


Now that you have connected to Splunk, use the information in the following topics to perform various operations with the connector:

* To work with Saved Searches, See [Working with Saved Searches](managing_objects.md)
* To work with Search job, See [Working with Search Jobs](creating_searches.md)
* To work with Splunk Enterprise configurations, See [Accessing and updating Splunk Enterprise configurations](accessing_and_updating_SplunkEnterprise_configurations.md)
