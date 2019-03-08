# Splunk EI Connector

The Splunk [Connector](https://docs.wso2.com/display/EI640/Working+with+Connectors) allows you for searching, monitoring, and analyzing machine-generated big data.
It allows you to access the [Splunk Enterprise REST API ](https://docs.splunk.com/Documentation/Splunk/latest/RESTTUT/RESTconfigurations) through WSO2 Enterprise Integrator (WSO2 EI).The Splunk Enterprise REST API provides methods for accessing every feature in the Splunk product

## Compatibility

| Connector version | Supported Splunk API version | Supported WSO2 ESB/EI version |
| ------------- | ------------- | ------------- |
| [1.0.0](https://github.com/wso2-extensions/esb-connector-splunk/releases/tag/org.wso2.carbon.esb.connector.splunk-1.0.0) | V7.2.3 |  EI 6.4.0    |

## Getting started

#### Download and install the connector

1. Download the connector from the [WSO2 Store](https://store.wso2.com/store/assets/esbconnector/details/069e4bad-b0f1-451c-bae9-6e7a3fbcd62a) by clicking the Download Connector button.
2. Then you can follow this [Documentation](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+the+Management+Console) to add and enable the connector via the Management Console in your EI instance.
3. For more information on using connectors and their operations in your EI configurations, see [Using a Connector](https://docs.wso2.com/display/EI640/Using+a+Connector).
4. If you want to work with connectors via EI tooling, see [Working with Connectors via Tooling](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+Tooling).

#### Configuring the connector operations

To get started with Splunk connector and their operations, see [Configuring Splunk Operations](docs/config.md).


## Building From the Source

Follow the steps given below to build the Splunk connector from the source code:

1. Get a clone or download the source from [Github](https://github.com/wso2-extensions/esb-connector-splunk).
2. Run the following Maven command from the `esb-connector-splunk` directory: `mvn clean install`.
3. The Splunk connector zip file is created in the `esb-connector-splunk/target` directory

## How You Can Contribute

As an open source project, WSO2 extensions welcome contributions from the community.
Check the [issue tracker](https://github.com/wso2-extensions/esb-connector-splunk/issues) for open issues that interest you. We look forward to receiving your contributions.
