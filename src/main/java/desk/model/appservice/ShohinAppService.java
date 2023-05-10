package main.java.desk.model.appservice;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;

import javax.net.ssl.SSLParameters;

import main.java.desk.http.HttpPort;
import main.java.desk.http.HttpSetting;

import java.net.http.HttpResponse;
public class ShohinAppService {

    private HttpPort httpPort = new HttpPort();
    private HttpClient httpClient;
    private int lastStatusCode;
    private HttpHeaders lastHeaders;
    private String lastBody;
    public int getLastStatusCode() {
        return lastStatusCode;
    }
    public HttpHeaders getLastHeaders() {
        return lastHeaders;
    }
    public String getLastBody() {
        return lastBody;
    }

    public ShohinAppService() {
        var sslParams = new SSLParameters();
        sslParams.setEndpointIdentificationAlgorithm("HTTPS"); //LDAPS
        sslParams.setProtocols(new String[] {HttpSetting.sslProtocol[3]});
        httpClient = HttpClient.newBuilder().sslParameters(sslParams)
                .connectTimeout(java.time.Duration.ofMillis(1000))
                .version(HttpClient.Version.HTTP_1_1).build();
                //.sslContext(TrustCertificate.CertificateThrough()) //証明書検証はスルーする場合
    }

    public void httpGet(String uriStr) throws BusinessAppException {
        URI uri = URI.create(uriStr);
        HttpRequest req = httpPort.requestSetting(HttpRequest.newBuilder().GET(), uri);
        HttpResponse<String> res = httpPort.httpRequest(httpClient, req);
        setLastStatus(res);
    }

    public void httpPost(String uriStr, String jsonStr) throws BusinessAppException {
        URI uri = URI.create(uriStr);
        HttpRequest req = httpPort.requestSetting(HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(jsonStr)), uri);
        HttpResponse<String> res = httpPort.httpRequest(httpClient, req);
        setLastStatus(res);
    }

    public void httpPut(String uriStr, String jsonStr) throws BusinessAppException {
        URI uri = URI.create(uriStr);
        HttpRequest req = httpPort.requestSetting(HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(jsonStr)), uri);
        HttpResponse<String> res = httpPort.httpRequest(httpClient, req);

        setLastStatus(res);
    }

    public void httpDelete(String uriStr) throws BusinessAppException {
        URI uri = URI.create(uriStr);
        HttpRequest req = httpPort.requestSetting(HttpRequest.newBuilder().DELETE(), uri);
        HttpResponse<String> res = httpPort.httpRequest(httpClient, req);
        setLastStatus(res);
    }

    private void setLastStatus(HttpResponse<String> response) {
        lastStatusCode = response.statusCode();
        lastHeaders = response.headers();
        lastBody = response.body();
    }

    public String createJsonStr(short code, String name, String remarks) {
        var builder = new StringBuilder();

        builder.append("{ \"shohinCode\":");
        builder.append(code);
        builder.append(", \"shohinName\": \"");
        builder.append(name);
        builder.append("\", \"note\": \"");
        builder.append(remarks);
        builder.append("\" }");

        return builder.toString();
    }

    /*private int lastStatusCode;
    private HttpHeaders lastHeaders;
    private String lastBody;
    public int getLastStatusCode() {
        return lastStatusCode;
    }
    public HttpHeaders getLastHeaders() {
        return lastHeaders;
    }
    public String getLastBody() {
        return lastBody;
    }

    private HttpResponse<String> httpRequest(HttpClient client, HttpRequest req) throws BusinessAppException {
        HttpResponse<String> response = null;
        //String resStr = "";

        try {
            response = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (HttpConnectTimeoutException ex) {
            //window.showDialog("サーバーに接続できませんでした。", "HTTP接続タイムアウト", JOptionPane.ERROR_MESSAGE);
            String cmethod = new Object(){}.getClass().getEnclosingMethod().getName();
            LastException.setLastException(cmethod, "", ex);
            LastException.logWrite();
            throw new BusinessAppException("オリジナル例外");
        } catch (IOException | InterruptedException ex) {
            String cmethod = new Object(){}.getClass().getEnclosingMethod().getName();
            LastException.setLastException(cmethod, "", ex);
            LastException.logWrite();
            throw new BusinessAppException("オリジナル例外");
        }
        lastStatusCode = response.statusCode();
        lastHeaders = response.headers();
        lastBody = response.body();

        return response;
    }

    private HttpRequest requestSetting(Builder builder, URI uri) {
        if (fAuthentication && Authentication.getUserID().equals("") == false) {
            if (authType == Authentication.BASIC) {
                //Basic認証
                String basicStr = Authentication.basicRequestHeader();
                builder.setHeader(AUTHORIZATION, basicStr);
            } else {
                //Digest認証
                String a1 = Authentication.digestResponseA1(Authentication.mapParam.get("realm"));
                String a2 = Authentication.digestResponseA2("GET", uri.toString());
                String res = Authentication.digestResponse(a1, a2, Authentication.mapParam.get("nonce"), Authentication.mapParam.get("qop"));
                String req = Authentication.digestRequest(res);
                builder.header(AUTHORIZATION, req);
            }
        }
        HttpRequest request = builder
                .uri(uri)
                .version(httpClient.version()) //コンストラクタで設定したhttpVer //HttpSetting.getHttpVer())
                .timeout(java.time.Duration.ofMillis(3000)) //タイムアウト3秒固定
                .setHeader(CONTENT_TYPE_NAME, CONTENT_TYPE_JSON).build();

        return request;
    }*/
}