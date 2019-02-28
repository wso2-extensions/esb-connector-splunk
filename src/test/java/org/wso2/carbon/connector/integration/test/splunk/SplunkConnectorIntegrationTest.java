/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.connector.integration.test.splunk;

import org.apache.axiom.om.OMElement;
import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Integration test for Splunk connector.
 */
public class SplunkConnectorIntegrationTest extends ConnectorIntegrationTestBase {

    private Map<String, String> eiRequestHeadersMap = new HashMap<>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<>();

    private String searchName, searchId, apiUrl;

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        addCertificatesToEIKeyStore("client-truststore.jks", "wso2carbon");

        String connectorName = System.getProperty("connector_name") + "-connector-" +
                System.getProperty("connector_version") + ".zip";

        init(connectorName);
        getApiConfigProperties();

        eiRequestHeadersMap.put("Content-Type", "application/xml");
        apiRequestHeadersMap.put("Content-Type", "text/plain");

        String token = connectorProperties.getProperty("username") + ":" + connectorProperties.getProperty("password");
        // encoding token into base 64
        byte[] encodedToken = Base64.encodeBase64(token.getBytes());
        apiRequestHeadersMap.put("Authorization", "Basic " + new String(encodedToken));

        apiUrl = "https://" + connectorProperties.getProperty("hostname") + ":" +
                connectorProperties.getProperty("port");

        connectorProperties.setProperty("searchName",
                System.currentTimeMillis() + connectorProperties.getProperty("searchName"));
        connectorProperties.setProperty("searchName1",
                System.currentTimeMillis() + connectorProperties.getProperty("searchName1"));
        connectorProperties.setProperty("configFileName",
                System.currentTimeMillis() + connectorProperties.getProperty("configFileName"));
        connectorProperties.setProperty("stanzaName",
                System.currentTimeMillis() + connectorProperties.getProperty("stanzaName"));
        connectorProperties.setProperty("stanzaName1",
                System.currentTimeMillis() + connectorProperties.getProperty("stanzaName1"));
    }

    /**
     * Positive test case for createSavedSearch with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {createSavedSearch} integration " +
            "test with mandatory parameters.")
    public void testCreateSavedSearchMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:createSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_createSavedSearch_mandatory.xml");

        searchName = getValueByExpression("//entry/title", eiRestResponse.getBody());

        String apiEndpoint = apiUrl + "/servicesNS/" + connectorProperties.getProperty("appUserName") + "/" +
                connectorProperties.getProperty("appName") + "/saved/searches/" +
                connectorProperties.getProperty("searchName");

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(searchName, getValueByExpression("//entry/title", apiRestResponse.getBody()));
    }

    /**
     * Negative test case for createSavedSearch method.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {createSavedSearch} integration test with " +
            "negative case.")
    public void testCreateSavedSearchWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:createSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_createSavedSearch_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS/" + connectorProperties.getProperty("appUserName") +
                "/" + connectorProperties.getProperty("appName") + "/saved/searches";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "POST",
                apiRequestHeadersMap, "api_createSavedSearch_invalid.txt", null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(getValueByExpression("//response/messages", eiRestResponse.getBody()),
                getValueByExpression("//response/messages", apiRestResponse.getBody()));
    }

    /**
     * Positive test case for accessSavedSearch with mandatory parameters
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {accessSavedSearch} integration " +
            "test with mandatory parameters.")
    public void testAccessSavedSearchMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:accessSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_accessSavedSearch_mandatory.xml");

        String apiEndpoint = apiUrl + "/servicesNS/" + connectorProperties.getProperty("appUserName") +
                "/" + connectorProperties.getProperty("appName") + "/saved/searches";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for accessSavedSearch with optional parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {accessSavedSearch} integration" +
            " test with optional parameters.")
    public void testAccessSavedSearchOptional() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:accessSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_accessSavedSearch_optional.xml");

        String apiEndpoint = apiUrl + "/servicesNS/" + connectorProperties.getProperty("appUserName") +
                "/" + connectorProperties.getProperty("appName") + "/saved/searches";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, "api_accessSavedSearch_optional.txt", null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for accessSavedSearch method.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {accessSavedSearch} integration test with " +
            "negative case.")
    public void testAccessSavedSearchWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:accessSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_accessSavedSearch_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS///saved/searches";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
        Assert.assertEquals(getValueByExpression("//response/messages", eiRestResponse.getBody()),
                getValueByExpression("//response/messages", apiRestResponse.getBody()));
    }

    /**
     * Positive test case for updateSavedSearch with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {updateSavedSearch} integration " +
            "test with mandatory parameters.", dependsOnMethods = {"testCreateSavedSearchMandatory"})
    public void testUpdateSavedSearchMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:updateSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_updateSavedSearch_mandatory.xml");

        searchName = getValueByExpression("//entry/title", eiRestResponse.getBody());

        String apiEndpoint = apiUrl + "/servicesNS/" + connectorProperties.getProperty("appUserName") +
                "/" + connectorProperties.getProperty("appName") + "/saved/searches/" + searchName;

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(getValueByExpression("//entry/title", eiRestResponse.getBody()),
                getValueByExpression("//entry/title", apiRestResponse.getBody()));
    }

    /**
     * Negative test case for updateSavedSearch method.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {updateSavedSearch} integration test with " +
            "negative case.")
    public void testUpdateSavedSearchWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:updateSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_updateSavedSearch_invalid.xml");
        searchName = getValueByExpression("//entry/title", eiRestResponse.getBody());

        String apiEndpoint = apiUrl + "/servicesNS/" + connectorProperties.getProperty("appUserName") +
                "/" + connectorProperties.getProperty("appName") + "/saved/searches/";
        RestResponse<OMElement> apiRestResponse =
                sendXmlRestRequestHTTPS(apiEndpoint, "POST", apiRequestHeadersMap,
                        "api_updateSavedSearch_invalid.txt", null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(getValueByExpression("//response/messages", eiRestResponse.getBody()),
                getValueByExpression("//response/messages", apiRestResponse.getBody()));
    }

    /**
     * Positive test case for shareSavedSearch with mandatory parameters
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {shareSavedSearch} integration " +
            "test with mandatory parameters.", dependsOnMethods = {"testCreateSavedSearchMandatory"})
    public void testShareSavedSearchMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:shareSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_shareSavedSearch_mandatory.xml");

        searchName = getValueByExpression("//entry/title", eiRestResponse.getBody());

        String apiEndpoint = apiUrl + "/servicesNS/" + connectorProperties.getProperty("appUserName") +
                "/" + connectorProperties.getProperty("appName") + "/saved/searches/" + searchName;

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(getValueByExpression("//author/name", eiRestResponse.getBody()),
                getValueByExpression("//author/name", apiRestResponse.getBody()));
    }

    /**
     * Positive test case for shareSavedSearch with optional parameters
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {shareSavedSearch} integration test with " +
            "optional parameters.")
    public void testShareSavedSearchOptional() throws Exception {

        String apiEndpointCreateSavedSearch = apiUrl + "/servicesNS/" + connectorProperties.getProperty("appUserName") +
                "/" + connectorProperties.getProperty("appName") + "/saved/searches";

        RestResponse<OMElement> apiRestResponseCreateSavedSearch = sendXmlRestRequestHTTPS(apiEndpointCreateSavedSearch,
                "POST", apiRequestHeadersMap, "api_createSavedSearch_to_move.txt",
                null, true);
        eiRequestHeadersMap.put("Action", "urn:shareSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_shareSavedSearch_optional.xml");

        searchName = getValueByExpression("//entry/title", eiRestResponse.getBody());

        String apiEndpoint = apiUrl + "/servicesNS/" + connectorProperties.getProperty("appUserName") +
                "/" + connectorProperties.getProperty("appName") + "/saved/searches/" + searchName;
        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(getValueByExpression("//author/name", eiRestResponse.getBody()),
                getValueByExpression("//author/name", apiRestResponse.getBody()));
    }

    /**
     * Negative test case for shareSavedSearch method.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {shareSavedSearch} integration test with " +
            "negative case.")
    public void testShareSavedSearchWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:shareSavedSearch");

        RestResponse<OMElement> eiRestResponse =
                sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "esb_shareSavedSearch_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS/" +
                connectorProperties.getProperty("appUserName") + "/" + connectorProperties.getProperty("appName")
                + "/saved/searches//acl";
        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "POST",
                apiRequestHeadersMap, "api_shareSavedSearch_invalid.txt", null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(getValueByExpression("//response/messages", eiRestResponse.getBody()),
                getValueByExpression("//response/messages", apiRestResponse.getBody()));
    }

    /**
     * Positive test case for moveSavedSearch with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, dependsOnMethods = {"testShareSavedSearchOptional"},
            description = "splunk {moveSavedSearch} integration test with mandatory parameters.")
    public void testMoveSavedSearchMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:moveSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_moveSavedSearch_mandatory.xml");

        String apiEndpoint = apiUrl + "/servicesNS/" +
                "nobody/" + connectorProperties.getProperty("appName") + "/saved/searches/"
                + connectorProperties.getProperty("searchName1");
        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Negative test case for moveSavedSearch method.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {moveSavedSearch} integration test with " +
            "negative case.")
    public void testMoveSavedSearchWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:moveSavedSearch");

        RestResponse<OMElement> eiRestResponse =
                sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "esb_moveSavedSearch_invalid.xml");
        String apiEndpoint = apiUrl + "/servicesNS/"
                + connectorProperties.getProperty("appUserName") + "/" + connectorProperties.getProperty("appName")
                + "/saved/searches//move";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "POST",
                apiRequestHeadersMap, "api_moveSavedSearch_invalid.txt", null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(getValueByExpression("//response/messages", eiRestResponse.getBody()),
                getValueByExpression("//response/messages", apiRestResponse.getBody()));
    }

    /**
     * Positive test case for deleteSavedSearch with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {deleteSavedSearch} integration test with " +
            "mandatory parameters.", dependsOnMethods = {"testCreateSavedSearchMandatory",
            "testUpdateSavedSearchMandatory", "testShareSavedSearchMandatory"})
    public void testDeleteSavedSearchMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:deleteSavedSearch");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_deleteSavedSearch_mandatory.xml");

        String apiEndpoint = apiUrl + "/servicesNS/"
                + connectorProperties.getProperty("appUserName") + "/" +
                connectorProperties.getProperty("appName") + "/saved/searches/" +
                connectorProperties.getProperty("searchName");

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for deleteSavedSearch method.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {deleteSavedSearch} integration test with " +
            "negative case.")
    public void testDeleteSavedSearchWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:deleteSavedSearch");

        RestResponse<OMElement> eiRestResponse =
                sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "esb_deleteSavedSearch_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS/"
                + connectorProperties.getProperty("appUserName") + "/" +
                connectorProperties.getProperty("appName") + "/saved/searches/";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "DELETE",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(getValueByExpression("//response/messages", eiRestResponse.getBody()),
                getValueByExpression("//response/messages", apiRestResponse.getBody()));
    }

    /**
     * Positive test case for createSearchJob with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {createSearchJob} integration test" +
            " with mandatory parameters.")
    public void testCreateSearchJobMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:createSearchJob");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_createSearchJob_mandatory.xml");

        searchId = getValueByExpression("//response/sid", eiRestResponse.getBody());
        connectorProperties.setProperty("searchId", searchId);
        String apiEndpoint = apiUrl + "/services/search/jobs/" + searchId;

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for createSearchJob with optional parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {createSearchJob} integration test with " +
            "mandatory parameters.")
    public void testCreateSearchJobOptional() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:createSearchJob");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_createSearchJob_optional.xml");

        searchId = getValueByExpression("//response/sid", eiRestResponse.getBody());
        String apiEndpoint = apiUrl + "/services/search/jobs/" + searchId;

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for createSearchJob.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {createSearchJob} integration test with " +
            "negative cases.")
    public void testCreateSearchJobWithNegativeCases() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:createSearchJob");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_createSearchJob_invalid.xml");

        String apiEndpoint = apiUrl + "/services/search/jobs";

        RestResponse<OMElement> apiRestResponse =
                sendXmlRestRequestHTTPS(apiEndpoint, "POST", apiRequestHeadersMap,
                        "api_createSearchJob_invalid.txt", null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);

    }

    /**
     * Positive test case for getSearchResults with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {getSearchResults} integration test with " +
            "mandatory parameters.")
    public void testGetSearchResultsMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getSearchResults");
        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_getSearchResults_mandatory.xml");

        String apiEndpoint = apiUrl + "/services/search/jobs/" + searchId + "/results";
        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);
        //Getting both status codes 200 and 204 randomly when successful.
        String eiStatusCode = Integer.toString(eiRestResponse.getHttpStatusCode());
        String apiStatusCode = Integer.toString(apiRestResponse.getHttpStatusCode());

        Assert.assertTrue(eiStatusCode.equals("200") || eiStatusCode.equals("204"));
        Assert.assertTrue(apiStatusCode.equals("200") || apiStatusCode.equals("204"));
    }

    /**
     * Positive test case for getSearchResults with optional parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {getSearchResults} integration test with " +
            "optional parameters.")
    public void testGetSearchResultsOptional() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getSearchResults");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_getSearchResults_optional.xml");

        String apiEndpoint = apiUrl + "/services/search/jobs/" + searchId + "/results";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, "api_getSearchResults_optional.txt", null, true);
        //Getting both status codes 200 and 204 randomly when successful.
        String eiStatusCode = Integer.toString(eiRestResponse.getHttpStatusCode());
        String apiStatusCode = Integer.toString(apiRestResponse.getHttpStatusCode());

        Assert.assertTrue(eiStatusCode.equals("200") || eiStatusCode.equals("204"));
        Assert.assertTrue(apiStatusCode.equals("200") || apiStatusCode.equals("204"));
    }

    /**
     * Negative test case for getSearchResults.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {getSearchResults} integration test with " +
            "negative cases.")
    public void testGetSearchResultsWithNegativeCases() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getSearchResults");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_getSearchResults_invalid.xml");

        String apiEndpoint = apiUrl + "/services/search/jobs//results";
        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for accessSearchJob with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, dependsOnMethods = {"testCreateSearchJobMandatory"}, description =
            "splunk {accessSearchJob} integration test with mandatory parameters.")
    public void testAccessSearchJobMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:accessSearchJob");
        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_accessSearchJob_mandatory.xml");

        String searchJobId = getValueByExpression("//entry/id", eiRestResponse.getBody());
        String apiEndpoint = apiUrl + "/services/search/jobs/" + connectorProperties.getProperty("searchId");

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for accessSearchJob.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {accessSearchJob} integration test for " +
            "negative cases.")
    public void testAccessSearchJobWithNegativeCases() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:accessSearchJob");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_accessSearchJob_invalid.xml");
        String apiEndpoint = apiUrl + "/services/search/jobs//";
        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for createConfigFile with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {createConfigFile} integration test with " +
            "mandatory parameters.")
    public void testCreateConfigFileMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:createConfigFile");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_createConfigFile_mandatory.xml");
        String apiEndpoint = apiUrl + "/servicesNS/nobody/" +
                connectorProperties.getProperty("appName") + "/properties/" +
                connectorProperties.getProperty("configFileName");
        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for createConfigFile.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {createConfigFile} integration test with" +
            " negative cases.")
    public void testCreateConfigFileWithNegativeCases() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:createConfigFile");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_createConfigFile_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody//properties";
        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "POST",
                apiRequestHeadersMap, "api_createConfigFile_invalid.txt", null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for addStanza with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {addStanza} integration test with " +
            "mandatory parameters.")
    public void testAddStanzaMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:addStanza");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_addStanza_mandatory.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody/" +
                connectorProperties.getProperty("appName") + "/properties/" +
                connectorProperties.getProperty("configFileName") + "/" + connectorProperties.getProperty("stanzaName");

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for addStanza with optional parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {addStanza} integration test with " +
            "optional parameters.")
    public void testAddStanzaOptional() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:addStanza");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "esb_addStanza_optional.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody/" +
                connectorProperties.getProperty("appName") + "/properties/" +
                connectorProperties.getProperty("configFileName") + "/" + connectorProperties.getProperty("stanzaName");

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for addStanza.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {addStanza} integration test with " +
            "negative cases.")
    public void testAddStanzaWithNegativeCases() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:addStanza");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_addStanza_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody//configs/conf-";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "POST",
                apiRequestHeadersMap, "api_addStanza_invalid.txt", null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for updateStanza with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {updateStanza} integration test with " +
            "mandatory parameters.")
    public void testUpdateStanzaMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:updateStanza");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_updateStanza_mandatory.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody/" +
                connectorProperties.getProperty("appName") + "/properties/" +
                connectorProperties.getProperty("configFileName") + "/" + connectorProperties.getProperty("stanzaName");

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for updateStanza
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {updateStanza} integration test with " +
            "negative cases.")
    public void testUpdateStanzaWithNegativeCases() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:updateStanza");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_updateStanza_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody//configs/conf-//";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "POST",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for getStanza with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {getStanza} integration test with " +
            "mandatory parameters.")
    public void testGetStanzaMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getStanza");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_getStanza_mandatory.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody/" +
                connectorProperties.getProperty("appName") + "/configs/conf-" +
                connectorProperties.getProperty("configFileName");

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for getStanza
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {getStanza} integration test with " +
            "negative cases.")
    public void testGetStanzaWithNegativeCases() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getStanza");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_getStanza_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody//configs/conf-";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint,
                "GET", apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for deleteStanza with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {deleteStanza} integration test with " +
            "mandatory parameters.")
    public void testDeleteStanzaMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:deleteStanza");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_deleteStanza_mandatory.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody/" +
                connectorProperties.getProperty("appName") + "/properties/" +
                connectorProperties.getProperty("configFileName") + "/" +
                connectorProperties.getProperty("stanzaName1");

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Negative test case for deleteStanza.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {deleteStanza} integration test with " +
            "negative cases.")
    public void testDeleteStanzaWithNegativeCases() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:deleteStanza");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_deleteStanza_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody//configs/conf-/";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "DELETE",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for getAppConfigFiles with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {getAppConfigFiles} integration " +
            "test with mandatory parameters.")
    public void testGetAppConfigFilesMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getAppConfigFiles");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_getAppConfigFiles_mandatory.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody/" +
                connectorProperties.getProperty("appName") + "/properties";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for getAppConfigFiles.
     */
    @Test(enabled = true, groups = {"wso2.ei"}, description = "splunk {getAppConfigFiles} integration test " +
            "with negative cases.")
    public void testGetAppConfigFilesWithNegativeCases() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getAppConfigFiles");

        RestResponse<OMElement> eiRestResponse = sendXmlRestRequest(proxyUrl, "POST",
                eiRequestHeadersMap, "esb_getAppConfigFiles_invalid.xml");

        String apiEndpoint = apiUrl + "/servicesNS/nobody//properties";

        RestResponse<OMElement> apiRestResponse = sendXmlRestRequestHTTPS(apiEndpoint, "GET",
                apiRequestHeadersMap, null, null, true);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }
}
