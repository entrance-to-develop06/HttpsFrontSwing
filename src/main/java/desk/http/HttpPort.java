package main.java.desk.http;

import main.java.desk.control.LastException;
import main.java.desk.model.appservice.BusinessAppException;

import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class HttpPort {

    private HttpClient httpClient;
    private boolean fAuthentication = true;
    private String authType = "Basic "; //Basic認証orDigest認証
    //private FrameAuthDesign dialog = new FrameAuthDesign();
    private static final String CONTENT_TYPE_NAME = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
    private static final String AUTHORIZATION = "Authorization";


    public HttpPort() {
        var sslParams = new SSLParameters();
        sslParams.setEndpointIdentificationAlgorithm("HTTPS"); //LDAPS
        sslParams.setProtocols(new String[] {HttpSetting.sslProtocol[3]});
        httpClient = HttpClient.newBuilder().sslParameters(sslParams)
                .connectTimeout(java.time.Duration.ofMillis(1000))
                .version(HttpClient.Version.HTTP_1_1).build();
                //.sslContext(TrustCertificate.CertificateThrough()) //証明書検証はスルーする場合
    }

    public HttpResponse<String> httpRequest(HttpClient client, HttpRequest req) throws BusinessAppException {
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

        return response;
    }

    public HttpRequest requestSetting(HttpRequest.Builder builder, URI uri) {
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
    }
}